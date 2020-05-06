package sample;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Client_Subject_Marks {
    private String rno ;
    private int i;
    private int s;

    public Client_Subject_Marks(String rno,int i,int sub)
    {
        this.rno=rno;
        this.i=i;
        this.s=sub;
    }

    public int run()
    {
        Name_Node_List list = new Name_Node_List();
        int j;
        for (j = 0; j < list.getName_node_list().getLen(); j++) {
            try {
                Socket tol = new Socket(list.getName_node_list().get_server_info(j).getAddress(), list.getName_node_list().get_server_info(j).getPort());
                OutputStreamWriter write = new OutputStreamWriter(tol.getOutputStream(), StandardCharsets.UTF_8);
                BufferedWriter writer = new BufferedWriter(write);
                InputStreamReader read = new InputStreamReader(tol.getInputStream(), StandardCharsets.UTF_8);
                BufferedReader reader = new BufferedReader(read);
                writer.write(6 + ":" + rno +":"+s+ "\n");
                writer.flush();
                String output = reader.readLine();
                //System.out.println(output);
                if(output.equals("-1"))
                {
                    continue;
                }
                String[] info=output.split(":");
                SubjectMarks.name[i].setText(info[0]);
                String[] marks=info[1].trim().split(",");
                int t=0,ijk;
                for(ijk=0;ijk<marks.length;ijk++)
                {
                    t+=Integer.parseInt(marks[ijk].trim());
                }
                SubjectMarks.mar[i].setText(t+"");
                int to=Student_info.Max;
                SubjectMarks.tot[i].setText(to+"");
                return 0;
            } catch (Exception e) {
                System.out.println("Connection refused by " + list.getName_node_list().get_server_info(j).getPort());
            }
        }
        return 1;
    }
}
