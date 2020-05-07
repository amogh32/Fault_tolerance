package sample;

import java.util.Scanner;

public class Worker
{
    private static Worker_list list;
    private static int len;


    public static int max;
    public static int closed=0;

    public static void main(String[] args)
    {
        list=new Worker_list();
        len=list.getWorker_list().getLen();
        max=Math.min((Extras.data_replication-1)/2,len-Extras.data_replication);
        int i;
        Worker_Thread[] workers=new Worker_Thread[len];
        Thread [] input=new Thread[len];
        for(i=0;i<len;i++)
        {
            workers[i]=new Worker_Thread(list.getWorker_list().get_server_info(i).getPort());
            input[i]=new Thread(workers[i]);
            input[i].start();
        }
        Scanner myObj = new Scanner(System.in);
        String command;
        int p;
        while (true)
        {
            command=myObj.nextLine();
            String[]arr=command.split(" ");
            if(arr.length>2||arr.length<2)
            {
                System.out.println("Invalid command");
            }
            else
            {
                if(arr[0].equals("start"))
                {
                    try
                    {
                        p=get_pos(Integer.parseInt(arr[1]));
                        if(p==-1)
                        {
                            System.out.println("Invalid port number");
                        }
                        else
                        {
                            workers[p].start_server();
                        }
                    }
                    catch(Exception e)
                    {
                        System.out.println("No number entered");
                    }
                }
                else if(arr[0].equals("stop"))
                {
                    try
                    {
                        p=get_pos(Integer.parseInt(arr[1]));
                        if(p==-1)
                        {
                            System.out.println("Invalid port number");
                        }
                        else
                        {
                            workers[p].stop_server();
                        }
                    }
                    catch(Exception e)
                    {
                        System.out.println("No number entered");
                    }

                }
                else
                {
                    System.out.println("Invalid command");
                }
            }
        }
    }

    private static int get_pos(int p)
    {
        int i;
        for(i=0;i<len;i++)
        {
            if(list.getWorker_list().get_server_info(i).getPort()==p)
            {
                return i;
            }
        }
        return -1;
    }
}