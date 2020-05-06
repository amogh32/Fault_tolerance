package sample;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Client_input
{
    private Name_Node_List list;

    private int build_connection(int i,String to_send)
    {
        int r=0;
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
            if(output.equals("1"))
            {
                return 1;
            }
            else
            {
                return 0;
            }
        } catch (IOException e) {
            return 2;
        }

    }


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
            return output;
        } catch (IOException e) {
            System.out.println("Port "+list.getName_node_list().get_server_info(i).getPort()+ " unreachable");
        }
        return "";
    }

    public int start(String to_send)
    {
        int i;
        String b = null,c;
        boolean a=false;
        list=new Name_Node_List();
        int [] sa=new int[list.getName_node_list().getLen()];
        for(i=0;i<list.getName_node_list().getLen();i++)
        {
            sa[i]=build_connection(i,"1:"+to_send);
            if(sa[i]==1)
            {
                a=true;
            }
        }
        if(a==true)
        {
            return 1;
        }
        else
        {
            for(i=0;i<list.getName_node_list().getLen();i++)
            {
                b=add_data(i,"2:"+to_send);
                if(!b.equals(""))
                {
                    break;
                }
            }
            i++;
            for(;i<list.getName_node_list().getLen();i++)
            {
                c=add_data(i,"3:"+b);
            }
            return 0;
        }

    }
}
