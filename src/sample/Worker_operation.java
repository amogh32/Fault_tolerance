package sample;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class Worker_operation
{
    private Socket client;
    private int port;

    public Worker_operation(Socket client,int port)
    {
        this.client=client;
        this.port=port;
    }

    public void run()
    {
        InputStreamReader read= null,read1=null;
        OutputStreamWriter write= null,write1=null;
        try {
            read = new InputStreamReader(client.getInputStream(), StandardCharsets.UTF_8);
            write = new OutputStreamWriter(client.getOutputStream(), StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(read);
            BufferedWriter writer = new BufferedWriter(write);
            String output = null;
            output = reader.readLine();
            //System.out.println(output);
            String[] info=output.split(":");
            if(info[0].equals("2")) {
                String put = info[2] + ":" + info[1] + ":"+info[3]+":"+info[4];
                File_operations.add_data(put,"port_"+port);
                writer.write("1\n");
                writer.flush();


            }
            else if(info[0].equals("9")) {
                String put = info[2] + ":" + info[1] + ":"+info[3]+":"+info[4];
                File_operations.add_data(put,"port_"+port);
                File_operations.merge_worker_file("port_"+port);
                writer.write("1\n");
                writer.flush();


            }
            else if(info[0].equals("5"))
            {
                System.out.println("request for "+info[1]+" received at "+port);
                String put=File_operations.worker_type_5("port_"+port,info[1]);
                //System.out.println(put);
                if(put.equals("-1"))
                {
                    writer.write("-1\n");
                    writer.flush();
                }
                else
                {
                    writer.write(put+"\n");
                    writer.flush();
                }
            }
            else if(info[0].equals("6"))
            {
                System.out.println("Request for "+info[1]+" for subject "+Student_info.short_form[Integer.parseInt(info[2])]+" received at port "+port);
                String put=File_operations.worker_type_5("port_"+port,info[1]);
                //System.out.println(put);
                if(put.equals("-1"))
                {
                    writer.write("-1\n");
                    writer.flush();
                }
                else
                {
                    String[] u=put.split(":");
                    put=u[0]+":"+u[1].split(",")[Integer.parseInt(info[2])]+":"+u[2];
                    writer.write(put+"\n");
                    writer.flush();
                }
            }
        }
        catch (Exception e)
        {

        }
    }
}