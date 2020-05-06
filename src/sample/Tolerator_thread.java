package sample;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Tolerator_thread implements Runnable
{
    private int port;
    private boolean running=false;
    private ServerSocket server_soc;
    private Name_Node_List list;
    private int len;
    private String[] ports;


    public Tolerator_thread(int port,Name_Node_List list)
    {
        this.port=port;
        this.list=list;
        len=list.getName_node_list().getLen();
        ports=new String[len];
        for(int i=0;i<len;i++)
        {
            ports[i]="port_"+list.getName_node_list().get_server_info(i).getPort();
        }
        File_operations.create_file("port_"+port,1);
        start_server();
    }

    public void start_server()
    {
        if(running==true)
        {
            System.out.println("Port "+port+" already running");
        }
        else
        {
            try {
                server_soc=new ServerSocket(port);
                File_operations.merge_files(ports);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(Tolerator.closed>0)
            {
                Tolerator.closed--;
                System.out.println(Tolerator.closed+" servers closed till now");
            }

            running=true;
        }
    }

    public void stop_server()
    {
        if(running==false)
        {
            System.out.println("Port "+port+" already stopped");
        }
        else
        {
            if(Tolerator.closed==Tolerator.max)
            {
                System.out.println("Max "+Tolerator.max+" servers can be closed");
                return;
            }
            try {
                server_soc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            running=false;
            Tolerator.closed++;
            System.out.println(Tolerator.closed+" servers closed till now");
            System.out.println("Server stopped at Port - "+port);
        }
    }

    public void run()
    {
        while(true)
        {
            if(server_soc.isClosed()==false)
            {
                System.out.println("Server started at Port - "+port);


                try {
                    Socket client=server_soc.accept();
                    new Tolerator_operation(client,port).run();
                } catch (IOException e) {

                }

            }
        }
    }
}
