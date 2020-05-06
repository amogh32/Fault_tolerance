package sample;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

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
            Set<String> hash_Set = new HashSet<String>();
            for(i=0;i<l;i++)
            {
                for(j=0;j<lines[i];j++)
                {
                    hash_Set.add(re[i].nextLine());
                }
            }
            write=hash_Set.size()+"\n";
            for (String a:hash_Set)
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
                    return t[1]+":"+t[2];
                }
            }
            re.close();



        } catch (IOException e) {
            e.printStackTrace();
        }
        return  "-1";
    }
}