package sample;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class File_operations {
    public static void create_file(String Filename, int type) {
        File f = new File(Filename);
        if (f.exists()) {

        } else {
            try {
                f.createNewFile();
                if (type == 1) {
                    FileWriter myWriter = new FileWriter(Filename);
                    myWriter.write("0\n");
                    myWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static int exists(int rno, String fname) {
        int  i;
        try {
            File f = new File(fname);
            Scanner re = new Scanner(f);

            int lines = Integer.parseInt(re.nextLine());
            Set<String> hash_Set = new HashSet<String>();
            for (i = 0; i < lines; i++) {

                    hash_Set.add(re.nextLine());

            }

            re.close();


            for(String a:hash_Set)
            {
                String[]arr= a.split(":");
                if(arr[0].equals(rno+""))
                {
                    return 1;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String exists_rno(int rno, String fname) {
        int  i;
        try {
            File f = new File(fname);
            Scanner re = new Scanner(f);

            int lines = Integer.parseInt(re.nextLine());
            Set<String> hash_Set = new HashSet<String>();
            for (i = 0; i < lines; i++) {

                hash_Set.add(re.nextLine());

            }

            re.close();


            for(String a:hash_Set)
            {
                String[]arr= a.split(":");
                if(arr[0].equals(rno+""))
                {
                    return a;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "0";
    }

    public static void add_server(String data, String fname) {
        int  i;
        try {
            File f = new File(fname);
            Scanner re = new Scanner(f);

            int lines = Integer.parseInt(re.nextLine());
            Set<String> hash_Set = new HashSet<String>();
            for (i = 0; i < lines; i++) {

                hash_Set.add(re.nextLine());

            }

            re.close();

            String r=""+(lines+1)+"\n";
            for(String a:hash_Set)
            {
                r+=a+"\n";
            }
            r+=data+"\n";
            FileWriter myWriter = new FileWriter(fname);
                myWriter.write(r);
                myWriter.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void add_data(String data, String fname) {
        int  i;
        try {
            BufferedWriter out = new BufferedWriter(
                    new FileWriter(fname, true));
            out.write(data+"\n");
            out.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public static void merge_files(String[] fname)
    {
        int l=fname.length,i,j;
        String write;


        try {
            File[] f=new File[l];
            Scanner[] re=new Scanner[l];
            for(i=0;i<l;i++)
            {
                f[i]=new File(fname[i]);
                re[i]=new Scanner(f[i]);
            }
            int[] lines=new int[l];
            for(i=0;i<l;i++)
            {
                lines[i]=Integer.parseInt(re[i].nextLine());
            }
            Map<String,String> hash_Set=new HashMap<String,String>();
            for(i=0;i<l;i++)
            {
                for(j=0;j<lines[i];j++)
                {
                    String found=re[i].nextLine();
                    String[] arr=found.split(":");
                    if(hash_Set.containsKey(arr[0]))
                    {
                        String[] found1=hash_Set.get(arr[0]).split(":");
                        String found2=found1[found1.length-1];
                        if(Long.parseLong(found2)<Long.parseLong(arr[arr.length-1]))
                        {
                            hash_Set.put(arr[0],found);
                        }
                    }
                    else
                    {
                        hash_Set.put(arr[0],found);
                    }
                }
            }
            Collection <String> hash=hash_Set.values();
            write=hash_Set.size()+"\n";

            for (String a:hash)
            {
                write+=a+"\n";
            }
            for(i=0;i<l;i++)
            {
                re[i].close();
            }
            FileWriter[] myWriter = new FileWriter[l];
            for(i=0;i<l;i++)
            {
                myWriter[i]=new FileWriter(fname[i]);
                myWriter[i].write(write);
                myWriter[i].close();
            }
        } catch (IOException e) {
            return;
        }
    }

    public static String type_4(String fname)
    {
        int  i;
        try {
            File f = new File(fname);
            Scanner re = new Scanner(f);

            int lines = Integer.parseInt(re.nextLine());
            String r="";
            for(i=0;i<lines-1;i++)
            {
                r+=re.nextLine()+"@";
            }
            r+=re.nextLine();
            re.close();

            return r;


        } catch (IOException e) {
            e.printStackTrace();
        }
        return  "";
    }

    public static String type_5(String fname,String rno)
    {
        int  i;
        try {
            File f = new File(fname);
            Scanner re = new Scanner(f);

            int lines = Integer.parseInt(re.nextLine());
            for(i=0;i<lines;i++)
            {
                String[] t=re.nextLine().split(":");
                if(t[0].equals(rno))
                {
                    return t[1];
                }
            }
            re.close();



        } catch (IOException e) {
            e.printStackTrace();
        }
        return  "-1";
    }

    public static String worker_type_5(String fname,String rno)
    {
        int  i;
        try {
            File f = new File(fname);
            Scanner re = new Scanner(f);

            while (re.hasNextLine())
            {
                String ab=re.nextLine();
                String[] t=ab.split(":");
                if(t[0].equals(rno))
                {
                    return t[1]+":"+t[2]+":"+t[3];
                }
            }
            re.close();



        } catch (IOException e) {
            e.printStackTrace();
        }
        return  "-1";
    }

    public static void merge_worker_file(String s)
    {
        int i,j;
        String write;


        try {
            File f=new File(s);
            Scanner re=new Scanner(f);
            Map<String,String> hash_Set=new HashMap<String,String>();
            while(re.hasNextLine())
            {
                String found=re.nextLine();
                String[] arr=found.split(":");
                if(hash_Set.containsKey(arr[0]))
                {
                    String[] found1=hash_Set.get(arr[0]).split(":");
                    String found2=found1[found1.length-1];
                    if(Long.parseLong(found2)<Long.parseLong(arr[arr.length-1]))
                    {
                        hash_Set.put(arr[0],found);
                    }
                }
                else
                {
                    hash_Set.put(arr[0],found);
                }
            }

            Collection <String> hash=hash_Set.values();
            write="";

            for (String a:hash)
            {
                write+=a+"\n";
            }
            re.close();
            FileWriter myWriter = new FileWriter(s);
            myWriter.write(write);
            myWriter.close();

        } catch (IOException e) {
            return;
        }
    }

    public static String modify_server_details(String s, String s1, String fname) {
        int i;
        String send="";
        try {
            File f = new File(fname);
            Scanner re = new Scanner(f);

            int lines = Integer.parseInt(re.nextLine());
            Map<String, String> hash_Set = new HashMap<String, String>();

            for (i = 0; i < lines; i++) {
                String found = re.nextLine();
                String[] arr = found.split(":");


                if (arr[0].equals(s)) {
                    found = found.substring(0, found.lastIndexOf(':') + 1) + s1;
                    send=found;
                }
                hash_Set.put(arr[0], found);

            }
            String write;

            Collection<String> hash = hash_Set.values();
            write = hash_Set.size() + "\n";

            for (String a : hash) {
                write += a + "\n";
            }

            re.close();

            FileWriter myWriter = new FileWriter(fname);
            myWriter.write(write);
            myWriter.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
        return send;
    }
}