package sample;

import javax.swing.*;
import java.awt.*;

public class AddStudent
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

        JLabel head=new JLabel("Add Student");
        head.setFont(new Font("Serif", Font.PLAIN, 20));
        head.setBounds((int)(w/2)-Extras.title_h,Extras.title_tv,2*Extras.title_h,2*Extras.title_v);
        f.add(head);

        JButton home =
                new JButton("Home");

        home.setBounds(w-500,Extras.title_tv+50,100, 25);

        f.add(home);
        home.addActionListener(e->
        {

            new Home().start();
            f.dispose();

        });
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int l=Student_info.getno(),i;
        JLabel[] la=new JLabel[l+2];
        JTextField[] in=new JTextField[l+2];
        la[0]=new JLabel("Name");
        la[1]=new JLabel("Roll No");
        int a1=w/7;
        for(i=0;i<=l+1;i++)
        {
            in[i]=new JTextField();
            if(i>1)
            {
                la[i]=new JLabel(Student_info.short_form[i-2]+" Marks");
            }
            la[i].setBounds(2*a1,200+50*i,a1,30);
            in[i].setBounds(3*a1,200+50*i,2*a1,30);
            f.add(in[i]);
            f.add(la[i]);

        }


        JButton add =
                new JButton("Add");

        add.setBounds(3*a1,230+50*i,300,30);
        f.add(add);
        add.addActionListener(e->
        {
            int ij;
            for(ij=0;ij<=l+1;ij++)
            {
                if(in[ij].getText().trim().equals(""))
                {
                    JOptionPane.showMessageDialog(null, la[ij].getText()+" is empty");
                    return;
                }
            }
            for(ij=1;ij<=l+1;ij++)
            {
                int k;
                try
                {
                      k=Integer.parseInt(in[ij].getText().trim());
                      if(ij==1)
                      {
                          if(k<1)
                          {
                              JOptionPane.showMessageDialog(null, la[ij].getText()+" must be positive integer");
                              return;
                          }
                      }
                      else
                      {
                          if(k<0||k>Student_info.Max)
                          {
                              JOptionPane.showMessageDialog(null, la[ij].getText()+" must be between 0 and "+Student_info.Max);
                              return;
                          }
                      }
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null, la[ij].getText()+" must be an integer");
                    return;
                }
            }
            String to_send;
            to_send=in[0].getText().trim()+":"+in[1].getText().trim()+":";
            for(ij=2;ij<l+1;ij++)
            {
                to_send+=in[ij].getText()+",";
            }
            to_send+=in[l+1].getText()+":"+System.currentTimeMillis();
            int q=new Client_input().start(to_send);
            if(q==0)
            {
                JOptionPane.showMessageDialog(null,"Student successfully added");
            }
            else if(q==1)
            {
                JOptionPane.showMessageDialog(null, "Student already added");
            }
            else if(q==2)
            {
                JOptionPane.showMessageDialog(null, "Error connecting to servers");
            }

            new Home().start();
            f.dispose();

        });
        f.setLayout(null);
        f.setVisible(true);
    }
}