package sample;

import javax.swing.*;
import java.awt.*;

public class SelectRollNo
{
    public void start()
    {
        JFrame f=new JFrame();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        int w=(int)width;
        int h=(int)height;
        f.setSize(w,h);

        JLabel head=new JLabel("Modify Details");
        head.setFont(new Font("Serif", Font.PLAIN, 20));
        head.setBounds((int)(w/2)- Extras.title_h, Extras.title_tv,2* Extras.title_h,2* Extras.title_v);
        f.add(head);

        JButton home =
                new JButton("Home");

        home.setBounds(w-500, Extras.title_tv+50,100, 25);

        f.add(home);
        home.addActionListener(e->
        {

            new Home().start();
            f.dispose();

        });
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int a1=w/7;
        JLabel in=new JLabel("Select Roll No");
        JTextField fi=new JTextField(16);
        in.setBounds(2*a1,220,a1,30);
        fi.setBounds(3*a1,220,2*a1,30);
        f.add(in);
        f.add(fi);

        JButton see =
                new JButton("Modify Details");

        see.setBounds(3*a1,300,300,30);
        f.add(see);
        see.addActionListener(e->
        {

            String qual = " ";
            int k,sel=0;
            if(fi.getText().trim().equals(""))
            {
                JOptionPane.showMessageDialog(null, "Enter roll number");
                return;
            }
            try
            {
                int r=Integer.parseInt(fi.getText().trim());
                String re;
                if(r<=0)
                {
                    JOptionPane.showMessageDialog(null, "Roll number must be a positive integer");
                    return;
                }
                else
                {
                    re=new Client_modify().start(r);
                    if(re.equals("0"))
                    {
                        JOptionPane.showMessageDialog(null, "Roll number is not added to database");
                        return;
                    }
                    else if(re.equals("-1"))
                    {
                        JOptionPane.showMessageDialog(null, "Servers unreachable");
                        return;
                    }
                    else
                    {
                        new ModifyStudent().start(re,r+"");
                        f.dispose();
                        //JOptionPane.showMessageDialog(null, re);
                    }
                }
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, "Roll number must be an integer");
                return;
            }


        });
        f.setLayout(null);
        f.setVisible(true);
    }
}
