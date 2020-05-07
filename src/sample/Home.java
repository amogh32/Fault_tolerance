package sample;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home
{
    public static void main(String[] args)
    {
        new Home().start();
    }
    public void start()
    {
        Extras.cur=1;
        JFrame f=new JFrame();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        int w=(int)width;
        int h=(int)height;
        f.setSize(w,h);

        JLabel head=new JLabel("Home Page");
        head.setFont(new Font("Serif", Font.PLAIN, 50));
        head.setBounds((int)(w/2)-Extras.title_h,Extras.title_tv,2*Extras.title_h,2*Extras.title_v);
        f.add(head);

        String twoLines = "Subject wise marks\nof each student";
        JButton b =
                new JButton("<html>" + twoLines.replaceAll("\\n", "<br>") + "</html>");

        b.setBounds((int)(3*w/16),(int)(5*h/12)-25,200, 50);

        f.add(b);


        twoLines = "Total marks\nof each student";
        JButton b1 =
                new JButton("<html>" + twoLines.replaceAll("\\n", "<br>") + "</html>");

        b1.setBounds((int)(13*w/16)-200,(int)(5*h/12)-25,200, 50);

        f.add(b1);

        JButton b3 = new JButton("Add Student");

        b3.setBounds((int)(8*w/16)-100,(int)(5*h/12)-25,200, 50);

        f.add(b3);

        JButton b4 = new JButton("Modify student details");

        b4.setBounds((int)(8*w/16)-100,(int)(5*h/12)+100,200, 50);

        f.add(b4);

        b1.addActionListener(e->
        {

            new TotalMarks().start();
            f.dispose();

        });

        b3.addActionListener(e->
        {

            new AddStudent().start();
            f.dispose();

        });

        b.addActionListener(e->
        {

            new SelectSubject().start();
            f.dispose();

        });

        b4.addActionListener(e->
        {

            new SelectRollNo().start();
            f.dispose();

        });
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(null);
        f.setVisible(true);
    }
}