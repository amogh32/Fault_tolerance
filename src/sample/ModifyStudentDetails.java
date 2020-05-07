package sample;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ModifyStudentDetails
{
    private Name_Node_List list;

    private String add_data(int i,String to_send)
    {
        try {
            Socket tolerator=new Socket(list.getName_node_list().get_server_info(i).getAddress(),list.getName_node_list().get_server_info(i).getPort());
            OutputStreamWriter write= new OutputStreamWriter(tolerator.getOutputStream(), StandardCharsets.UTF_8);
            BufferedWriter writer = new BufferedWriter(write);
            InputStreamReader read=new InputStreamReader(tolerator.getInputStream(),StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(read);

            //System.out.println(to_send);
            writer.write(to_send + "\n");
            writer.flush();

            String output = reader.readLine();
            //System.out.println(output);
            return output;
        } catch (IOException e) {
            System.out.println("Port "+list.getName_node_list().get_server_info(i).getPort()+ " unreachable");
            return "2";
        }

    }

    public int start(String to_send)
    {
        int i;
        String b = null,c;
        boolean a=false;
        list=new Name_Node_List();
        String [] sa=new String[list.getName_node_list().getLen()];
        for(i=0;i<list.getName_node_list().getLen();i++)
        {
            sa[i]=add_data(i,"9:"+to_send);
            if(sa[i].equals("-1")||sa[i].equals("2"))
            {

            }
            else
            {
                a=true;
                break;
            }
        }
        if(a==false)
        {
            return -1;
        }
        else
        {
            String ab;
            to_send=sa[i];
            i++;
            for(;i<list.getName_node_list().getLen();i++)
            {
                ab=add_data(i,"10:"+to_send);
            }
            return 1;
        }

    }
}