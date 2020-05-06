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
                String put = info[2] + ":" + info[1] + ":"+info[3];
                File_operations.add_data(put,"port_"+port);
                writer.write("1\n");
                writer.flush();


            }
        }
        catch (Exception e)
        {

        }
    }
}