package sample;

import java.util.Scanner;

public class Tolerator
{
    private static Name_Node_List list;
    private static int len;

    public static int max=1;
    public static int closed=0;

    public static void main(String[] args)
    {
        list=new Name_Node_List();
        len=list.getName_node_list().getLen();
        int i;
        Tolerator_thread[] tolerators=new Tolerator_thread[len];
        Thread [] input=new Thread[len];
        for(i=0;i<len;i++)
        {
            tolerators[i]=new Tolerator_thread(list.getName_node_list().get_server_info(i).getPort(),list);
            input[i]=new Thread(tolerators[i]);
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
                            tolerators[p].start_server();
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
                            tolerators[p].stop_server();
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
            if(list.getName_node_list().get_server_info(i).getPort()==p)
            {
                return i;
            }
        }
        return -1;
    }
}