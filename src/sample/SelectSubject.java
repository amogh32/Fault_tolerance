package sample;

import javax.swing.*;
import java.awt.*;

public class SelectSubject
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

        JLabel head=new JLabel("Select Subject");
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

        int l= Student_info.getno(),i;
        JRadioButton[] btns=new JRadioButton[l];
        ButtonGroup g =new ButtonGroup();
        for(i=0;i<l;i++)
        {
            btns[i]=new JRadioButton(Student_info.subjects[i]);
            g.add(btns[i]);
            if(i==0)
            {
                btns[i].setSelected(true);
            }
            btns[i].setBounds((int)(w/2)-200,200+30*i,300,30);
            f.add(btns[i]);
        }

        JButton see =
                new JButton("See");

        see.setBounds((int)(w/2)-200,230+30*i,300,30);
        f.add(see);
        see.addActionListener(e->
        {

            String qual = " ";
            int k,sel=0;
            for(k=0; k< Student_info.getno(); k++)
            {
                if(btns[k].isSelected())
                {
                    sel=k;
                }
            }

            new SubjectMarks().start(sel);
            f.dispose();

        });
        f.setLayout(null);
        f.setVisible(true);
    }
}