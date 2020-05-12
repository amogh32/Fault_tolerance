package sample;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class Tolerator_operation {
    private Socket client;
    private int port;

    public Tolerator_operation(Socket client, int port) {
        this.client = client;
        this.port = port;
    }

    public void run() {
        InputStreamReader read = null, read1 = null;
        OutputStreamWriter write = null, write1 = null;
        try {
            read = new InputStreamReader(client.getInputStream(), StandardCharsets.UTF_8);
            write = new OutputStreamWriter(client.getOutputStream(), StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(read);
            BufferedWriter writer = new BufferedWriter(write);
            String output = null;
            output = reader.readLine();
            String[] info = output.split(":");
            if (info[0].equals("1")) {
                int a = File_operations.exists(Integer.parseInt(info[2]), "port_" + port);
                System.out.println("Request for " + info[2] + " received at port " + port);
                //System.out.println(a);
                writer.write(a + "\n");
                writer.flush();
            } else if (info[0].equals("2")) {
                Worker_list list1 = new Worker_list();
                int l = list1.getWorker_list().getLen();
                System.out.println("Request for " + info[2] + " received at port " + port);
                Random ra = new Random();
                int st = ra.nextInt(l), j, k, d = 0;
                String put = "";
                for (j = 0; j < l; j++) {
                    k = (st + j) % l;
                    try {
                        Socket worker_socket = new Socket(list1.getWorker_list().get_server_info(k).getAddress(), list1.getWorker_list().get_server_info(k).getPort());
                        OutputStreamWriter write11 = new OutputStreamWriter(worker_socket.getOutputStream(), StandardCharsets.UTF_8);
                        BufferedWriter writer11 = new BufferedWriter(write11);
                        InputStreamReader read11 = new InputStreamReader(worker_socket.getInputStream(), StandardCharsets.UTF_8);
                        BufferedReader reader11 = new BufferedReader(read11);

                        writer11.write(output + "\n");
                        writer11.flush();

                        String output1 = reader11.readLine();

                        d++;
                        put += "," + list1.getWorker_list().get_server_info(k).getAddress() + "," + list1.getWorker_list().get_server_info(k).getPort();
                        if (d == Extras.data_replication) {
                            break;
                        }
                    } catch (Exception e) {
                        System.out.println("Port " + list1.getWorker_list().get_server_info(k).getPort() + " unreachable");
                    }

                }
                put = put.substring(1);
                put = info[2] + ":" + put + ":" + info[4];
                File_operations.add_server(put, "port_" + port);
                writer.write(put + "\n");
                writer.flush();
            } else if (info[0].equals("3")) {
                File_operations.add_server(output.substring(2), "port_" + port);
                writer.write("1\n");
                writer.flush();
            } else if (info[0].equals("4")) {
                String se = File_operations.type_4("port_" + port);
                writer.write(se + "\n");
                writer.flush();
            } else if (info[0].equals("5")) {
                System.out.println("Request for " + info[1] + " received at port " + port);
                String se = File_operations.type_5("port_" + port, info[1]);
                //System.out.println(se);
                String[] arrr1=se.split(":");
                se=arrr1[0];
                if (se.equals("-1")) {
                    writer.write(se + "\n");
                    writer.flush();
                } else {
                    String[] arr = se.split(",");
                    int i;
                    int vote_count = 0;
                    long max = -1;
                    String put1 = "";

                    Random ra = new Random();
                    int st = ra.nextInt(Extras.data_replication), j, k;
                    String put = "";
                    for (j = 0; j < Extras.data_replication; j++) {
                        k = (st + j) % Extras.data_replication;
                        try {
                            Socket worker_socket = new Socket(arr[2 * k].trim(), Integer.parseInt(arr[2 * k + 1].trim()));
                            OutputStreamWriter write11 = new OutputStreamWriter(worker_socket.getOutputStream(), StandardCharsets.UTF_8);
                            BufferedWriter writer11 = new BufferedWriter(write11);
                            InputStreamReader read11 = new InputStreamReader(worker_socket.getInputStream(), StandardCharsets.UTF_8);
                            BufferedReader reader11 = new BufferedReader(read11);


                            writer11.write(output + "\n");
                            writer11.flush();

                            String output1 = reader11.readLine();
                            if (output1.equals("-1")) {

                            } else {
                                vote_count++;
                                //System.out.println(vote_count);
                                String[] ar1 = output1.split(":");
                                if (Long.parseLong(ar1[ar1.length - 1]) > max) {
                                    max = Long.parseLong(ar1[ar1.length - 1]);
                                    put1 = output1;
                                }

                            }

                        } catch (Exception e) {
                            System.out.println("Port " + arr[2 * k + 1] + " unreachable");
                        }

                    }
                    String[] ar2=put1.split(":");
                    //System.out.println(ar2[ar2.length-1]);
                    //System.out.println(arrr1[1]);
                    if (vote_count >= Extras.vote&&ar2[ar2.length-1].equals(arrr1[1])) {
                        writer.write(put1 + "\n");
                        writer.flush();
                    } else {

                        writer.write("-1\n");
                        writer.flush();
                    }

                }

            } else if (info[0].equals("6")) {
                System.out.println("Request for " + info[1] + " for subject " + Student_info.short_form[Integer.parseInt(info[2])] + " received at port " + port);
                String se = File_operations.type_5("port_" + port, info[1]);
                //System.out.println(se);
                String[] arrr1=se.split(":");
                se=arrr1[0];
                if (se.equals("-1")) {
                    writer.write(se + "\n");
                    writer.flush();
                } else {
                    int vote_count = 0;
                    long max = -1;
                    String put1 = "";
                    String[] arr = se.split(",");
                    int i;

                    Random ra = new Random();
                    int st = ra.nextInt(Extras.data_replication), j, k;
                    String put = "";
                    for (j = 0; j < Extras.data_replication; j++) {
                        k = (st + j) % Extras.data_replication;
                        try {
                            Socket worker_socket = new Socket(arr[2 * k].trim(), Integer.parseInt(arr[2 * k + 1].trim()));
                            OutputStreamWriter write11 = new OutputStreamWriter(worker_socket.getOutputStream(), StandardCharsets.UTF_8);
                            BufferedWriter writer11 = new BufferedWriter(write11);
                            InputStreamReader read11 = new InputStreamReader(worker_socket.getInputStream(), StandardCharsets.UTF_8);
                            BufferedReader reader11 = new BufferedReader(read11);


                            writer11.write(output + "\n");
                            writer11.flush();

                            String output1 = reader11.readLine();
                            if (output1.equals("-1")) {

                            } else {
                                vote_count++;
                                //System.out.println(vote_count);
                                String[] ar1 = output1.split(":");
                                if (Long.parseLong(ar1[ar1.length - 1]) > max) {
                                    max = Long.parseLong(ar1[ar1.length - 1]);
                                    put1 = output1;
                                }
                            }

                        } catch (Exception e) {
                            System.out.println("Port " + arr[2 * k + 1] + " unreachable");
                        }

                    }
                    String[] ar2=put1.split(":");

                    if (vote_count >= Extras.vote&&ar2[ar2.length-1].equals(arrr1[1])) {
                        writer.write(put1 + "\n");
                        writer.flush();
                    } else {

                        writer.write("-1\n");
                        writer.flush();
                    }

                }

            } else if (info[0].equals("7")) {
                String a = File_operations.exists_rno(Integer.parseInt(info[1]), "port_" + port);
                System.out.println("Request for " + info[1] + " received at port " + port);
                //System.out.println(a);
                writer.write(a + "\n");
                writer.flush();
            } else if (info[0].equals("8")) {
                System.out.println("Request for " + info[1] + " received at port " + port);
                String se = info[2];
                //System.out.println(se);
                if (se.equals("-1")) {
                    writer.write(se + "\n");
                    writer.flush();
                } else {
                    String[] arr = se.split(",");
                    int i;
                    int vote_count = 0;
                    long max = -1;
                    String put1 = "";

                    Random ra = new Random();
                    int st = ra.nextInt(Extras.data_replication), j, k;
                    String put = "";
                    for (j = 0; j < Extras.data_replication; j++) {
                        k = (st + j) % Extras.data_replication;
                        try {
                            Socket worker_socket = new Socket(arr[2 * k].trim(), Integer.parseInt(arr[2 * k + 1].trim()));
                            OutputStreamWriter write11 = new OutputStreamWriter(worker_socket.getOutputStream(), StandardCharsets.UTF_8);
                            BufferedWriter writer11 = new BufferedWriter(write11);
                            InputStreamReader read11 = new InputStreamReader(worker_socket.getInputStream(), StandardCharsets.UTF_8);
                            BufferedReader reader11 = new BufferedReader(read11);


                            //System.out.println("efe");
                            writer11.write("5:" + info[1] + "\n");
                            writer11.flush();

                            String output1 = reader11.readLine();
                            if (output1.equals("-1")) {

                            } else {
                                vote_count++;
                                //System.out.println(vote_count);
                                String[] ar1 = output1.split(":");
                                if (Long.parseLong(ar1[ar1.length - 1]) > max) {
                                    max = Long.parseLong(ar1[ar1.length - 1]);
                                    put1 = output1;
                                }

                            }

                        } catch (Exception e) {
                            System.out.println("Port " + arr[2 * k + 1] + " unreachable");
                        }

                    }
                    if (vote_count >= Extras.vote&&max==Long.parseLong(info[3])) {
                        //System.out.println(put1);
                        writer.write(put1 + "\n");
                        writer.flush();
                    } else {

                        writer.write("-1\n");
                        writer.flush();
                    }

                }
            } else if (info[0].equals("9")) {
                System.out.println("Request for " + info[2] + " received at port " + port);
                String se = File_operations.type_5("port_" + port, info[2]);
                //System.out.println(info[2]);
                String[] arrr1=se.split(":");
                se=arrr1[0];
                if (se.equals("-1")) {
                    writer.write(se + "\n");
                    writer.flush();
                } else {
                    String[] arr = se.split(",");
                    int i;
                    int vote_count = 0;

                    Random ra = new Random();
                    int st = ra.nextInt(Extras.data_replication), j, k;
                    String put = "";
                    for (j = 0; j < Extras.data_replication; j++) {
                        k = (st + j) % Extras.data_replication;
                        try {
                            Socket worker_socket = new Socket(arr[2 * k].trim(), Integer.parseInt(arr[2 * k + 1].trim()));
                            OutputStreamWriter write11 = new OutputStreamWriter(worker_socket.getOutputStream(), StandardCharsets.UTF_8);
                            BufferedWriter writer11 = new BufferedWriter(write11);
                            InputStreamReader read11 = new InputStreamReader(worker_socket.getInputStream(), StandardCharsets.UTF_8);
                            BufferedReader reader11 = new BufferedReader(read11);


                            writer11.write(output + "\n");
                            writer11.flush();

                            String output1 = reader11.readLine();
                            if (output1.equals("-1")) {

                            } else {
                                vote_count++;
                                //put += "," + arr[2 * k].trim() + "," + arr[2 * k + 1].trim();

                            }

                        } catch (Exception e) {
                            System.out.println("Port " + arr[2 * k + 1] + " unreachable");
                        }

                    }
                    if (vote_count >= Extras.vote) {

                        String ret=File_operations.modify_server_details(info[2],info[4], "port_" + port);
                        //System.out.println(ret);

                        writer.write(ret + "\n");
                        writer.flush();
                    } else {

                        writer.write("-1\n");
                        writer.flush();
                    }

                }
            }
            else if (info[0].equals("10")) {
                File_operations.add_server(output.substring(3), "port_" + port);
                String[] ab=new String[1];
                ab[0]="port_"+port;
                File_operations.merge_files(ab);
                writer.write("1\n");
                writer.flush();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}