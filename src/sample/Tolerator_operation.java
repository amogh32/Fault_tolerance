package sample;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class Tolerator_operation
{
    private Socket client;
    private int port;

    public Tolerator_operation(Socket client,int port)
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
            String[] info=output.split(":");
            if (info[0].equals("1"))
            {
                int a=File_operations.exists(Integer.parseInt(info[2]),"port_"+port);
                System.out.println("Request for "+info[2]+" received");
                //System.out.println(a);
                writer.write(a+"\n");
                writer.flush();
            }
            else if(info[0].equals("2"))
            {
                Worker_list list1=new Worker_list();
                int l=list1.getWorker_list().getLen();
                System.out.println("Request for "+info[2]+" received");
                Random ra=new Random();
                int st=ra.nextInt(l),j,k,d=0;
                String put="";
                for(j=0;j<l;j++)
                {
                    k=(st+j)%l;
                    try
                    {
                        Socket worker_socket=new Socket(list1.getWorker_list().get_server_info(k).getAddress(),list1.getWorker_list().get_server_info(k).getPort());
                        OutputStreamWriter write11= new OutputStreamWriter(worker_socket.getOutputStream(), StandardCharsets.UTF_8);
                        BufferedWriter writer11 = new BufferedWriter(write11);
                        InputStreamReader read11=new InputStreamReader(worker_socket.getInputStream(),StandardCharsets.UTF_8);
                        BufferedReader reader11 = new BufferedReader(read11);

                        writer11.write(output + "\n");
                        writer11.flush();

                        String output1 = reader11.readLine();

                        d++;
                        put+=","+list1.getWorker_list().get_server_info(k).getAddress()+","+list1.getWorker_list().get_server_info(k).getPort();
                        if(d==Extras.data_replication)
                        {
                            break;
                        }
                    }
                    catch(Exception e)
                    {
                        System.out.println("Port "+list1.getWorker_list().get_server_info(k).getPort()+" unreachable");
                    }

                }
                put=put.substring(1);
                put=info[2]+":"+put;
                File_operations.add_server(put,"port_"+port);
                writer.write(put+"\n");
                writer.flush();
            }
            else if(info[0].equals("3"))
            {
                File_operations.add_server(output.substring(2),"port_"+port);
                writer.write("1\n");
                writer.flush();
            }
            else if(info[0].equals("4"))
            {
                String se=File_operations.type_4("port_"+port);
                writer.write(se+"\n");
                writer.flush();
            }
            else if(info[0].equals("5"))
            {
                String se=File_operations.type_5("port_"+port,info[1]);
                if(se.equals("-1"))
                {
                    writer.write(se+"\n");
                    writer.flush();
                }
                else
                {
                    String[] arr=se.split(",");
                    int i;
                    for(i=0;i<Extras.data_replication;i++)
                    {
                        try
                        {
                            Socket worker_socket=new Socket(arr[2*i].trim(),Integer.parseInt(arr[2*i+1].trim()));
                            OutputStreamWriter write11= new OutputStreamWriter(worker_socket.getOutputStream(), StandardCharsets.UTF_8);
                            BufferedWriter writer11 = new BufferedWriter(write11);
                            InputStreamReader read11=new InputStreamReader(worker_socket.getInputStream(),StandardCharsets.UTF_8);
                            BufferedReader reader11 = new BufferedReader(read11);

                            writer11.write(output + "\n");
                            writer11.flush();

                            String output1 = reader11.readLine();

                            d++;
                            put+=","+list1.getWorker_list().get_server_info(k).getAddress()+","+list1.getWorker_list().get_server_info(k).getPort();
                            if(d==Extras.data_replication)
                            {
                                break;
                            }
                        }
                        catch(Exception e)
                        {
                            System.out.println("Port "+list1.getWorker_list().get_server_info(k).getPort()+" unreachable");
                        }
                    }
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}