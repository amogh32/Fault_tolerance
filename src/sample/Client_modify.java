package sample;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Client_modify
{
    private Name_Node_List list;

    private String build_connection(int i,String to_send)
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
            if(output.equals("0"))
            {
                return "0";
            }
            else
            {
                return output;
            }
        } catch (IOException e) {
            return "2";
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
            //System.out.println(output);
            return output;
        } catch (IOException e) {
            System.out.println("Port "+list.getName_node_list().get_server_info(i).getPort()+ " unreachable");
        }
        return "";
    }

    public String start(int rno)
    {
        int i;
        String b = null,c;
        boolean a=false;
        list=new Name_Node_List();
        String [] sa=new String[list.getName_node_list().getLen()];
        for(i=0;i<list.getName_node_list().getLen();i++)
        {
            sa[i]=build_connection(i,"7:"+rno);
            if(sa[i].equals("0")||sa[i].equals("2"))
            {

            }
            else
            {
                a=true;
                break;
            }
        }
        //System.out.println(i);
        if(a==false)
        {
            return "0";
        }
        else
        {
            return add_data(i,"8:"+sa[i]);
        }



    }
}