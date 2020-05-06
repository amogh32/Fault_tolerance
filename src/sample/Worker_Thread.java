package sample;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Worker_Thread implements Runnable
{
    private int port;
    private boolean running=false;
    private ServerSocket server_soc;


    public Worker_Thread(int port)
    {
        this.port=port;
        File_operations.create_file("port_"+port,0);
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
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Server started at Port - "+port);
            if(Worker.closed>0)
            {
                Worker.closed--;
                System.out.println(Worker.closed+" servers closed till now");
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
            if(Worker.closed==Worker.max)
            {
                System.out.println("Max "+Worker.max+" servers can be closed");
                return;
            }
            try {
                server_soc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            running=false;
            Worker.closed++;
            System.out.println(Worker.closed+" servers closed till now");
            System.out.println("Server stopped at Port - "+port);
        }
    }

    public void run()
    {
        while(true)
        {
            if(server_soc.isClosed()==false)
            {


                    try {
                        Socket client=server_soc.accept();
                        new Worker_operation(client,port).run();
                    } catch (IOException e) {

                    }

            }
        }
    }
}