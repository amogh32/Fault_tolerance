package sample;

import java.util.Arrays;

public class Student_info
{
    private int roll_no;
    private String name;
    public static int Max=50;
    public static String[] subjects={"Distributed algorithms","Network and System Security","Cloud Computing","Economics and Business Management"};
    public static String[] short_form={"DA","NSS","CC","EBM"};
    private int[] marks= new int[subjects.length];

    public Student_info(String name, int rno, int[] marks)
    {
        this.name=name;
        this.roll_no=rno;
        int i;
        for(i=0;i<subjects.length;i++)
        {
            this.marks[i]=marks[i];
        }
    }

    public int get_rno()
    {
        return roll_no;
    }

    public String getName()
    {
        return name;
    }

    public int getMarks(int i)
    {
        return marks[i];
    }

    public String summary()
    {
        String r= name+":"+roll_no+":"+ Arrays.toString(marks);
        return r;
    }

    public static int getno()
    {
        return subjects.length;
    }
}