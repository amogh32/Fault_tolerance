package sample;


import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;

public class SubjectMarks
{
    static JLabel[] rno=new JLabel[Extras.page+1];
    static JLabel [] name=new JLabel[Extras.page+1];
    static JLabel [] mar=new JLabel[Extras.page+1];
    static JLabel [] tot=new JLabel[Extras.page+1];

    public void start(int pos)
    {
        JFrame f=new JFrame();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        int w=(int)width;
        int h=(int)height;
        f.setSize(w,h);

        JLabel head=new JLabel(Student_info.subjects[pos]+" Marks");
        head.setFont(new Font("Serif", Font.PLAIN, 15));
        head.setBounds((int)(w/2)- Extras.title_h, Extras.title_tv,450,2* Extras.title_v);
        f.add(head);

        JButton home =
                new JButton("Home");

        home.setBounds(w-300, Extras.title_tv+50,100, 25);

        f.add(home);
        home.addActionListener(e->
        {

            new Home().start();
            f.dispose();

        });
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int max = 0,count = 0;

        Name_Node_List list=new Name_Node_List();

        Set<String> ab=new HashSet<String>();

        for(int i=0;i<list.getName_node_list().getLen();i++)
        {
            try {
                Socket tolerator=new Socket(list.getName_node_list().get_server_info(i).getAddress(),list.getName_node_list().get_server_info(i).getPort());
                OutputStreamWriter write= new OutputStreamWriter(tolerator.getOutputStream(), StandardCharsets.UTF_8);
                BufferedWriter writer = new BufferedWriter(write);
                InputStreamReader read=new InputStreamReader(tolerator.getInputStream(),StandardCharsets.UTF_8);
                BufferedReader reader = new BufferedReader(read);

                //System.out.println(to_send);
                writer.write("4\n");
                writer.flush();

                String o=reader.readLine();
                //System.out.println(o);
                String []output = o.split("@");
                //System.out.println(Arrays.toString(output));
                for(int j=0;j<output.length;j++)
                {
                    ab.add(output[j].split(":")[0]);
                }


            } catch (IOException e) {
                System.out.println("Port "+list.getName_node_list().get_server_info(i).getPort()+ " unreachable");
            }
        }

        ArrayList<Integer> abc=new ArrayList<Integer>();
        for (String z:ab)
        {
            abc.add(Integer.parseInt(z));
        }
        Collections.sort(abc);
        count=abc.size();

        max=count/ Extras.page;
        if(count% Extras.page!=0)
        {
            max++;
        }
        JButton prev =
                new JButton("Previous Page");

        if(Extras.cur==1)
        {
            prev.setEnabled(false);
        }

        prev.addActionListener(e->
        {

            Extras.cur--;
            new SubjectMarks().start(pos);
            f.dispose();

        });

        JButton next =
                new JButton("Next Page");

        if(Extras.cur==max)
        {
            next.setEnabled(false);
        }
        next.addActionListener(e->
        {

            Extras.cur++;
            new SubjectMarks().start(pos);
            f.dispose();

        });

        int a1=(int)(w/9);
        rno[0]=new JLabel("Roll No");
        rno[0].setBounds(a1,200,a1,50);
        f.add(rno[0]);

        name[0]=new JLabel("Name");
        name[0].setBounds(3*a1,200,a1,50);
        f.add(name[0]);

        mar[0]=new JLabel("Marks");
        mar[0].setBounds(5*a1,200,a1,50);
        f.add(mar[0]);

        tot[0]=new JLabel("Total Marks");
        tot[0].setBounds(7*a1,200,a1,50);
        f.add(tot[0]);

        int i,j=1;

        for(i=(Extras.cur-1)* Extras.page+1; i<=Math.min(Extras.cur* Extras.page,count); i++)
        {
            rno[j]=new JLabel(abc.get(i-1)+"");
            rno[j].setBounds(a1,200+50*j,a1,50);
            f.add(rno[j]);

            name[j]=new JLabel("");
            name[j].setBounds(3*a1,200+50*j,a1,50);
            f.add(name[j]);

            mar[j]=new JLabel("");
            mar[j].setBounds(5*a1,200+50*j,a1,50);
            f.add(mar[j]);

            tot[j]=new JLabel("");
            tot[j].setBounds(7*a1,200+50*j,a1,50);
            f.add(tot[j]);

            int x=new Client_Subject_Marks(abc.get(i-1)+"",j,pos).run();
            if(x==1)
            {
                JOptionPane.showMessageDialog(null, "Roll Number "+ abc.get(i-1) +" cannot be found from server");
            }

            j++;
        }

        prev.setBounds(2*a1,200+50*j,2*a1,50);
        f.add(prev);

        next.setBounds(6*a1,200+50*j,2*a1,50);
        f.add(next);

        f.setLayout(null);
        f.setVisible(true);
    }
}