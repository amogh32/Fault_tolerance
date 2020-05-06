package sample;

import java.util.ArrayList;

public class Server_List
{
    private ArrayList<Server_info> servers=new ArrayList<Server_info>();
    private int len;

    public Server_List(ArrayList<Server_info>servers)
    {
        this.servers=servers;
        len=servers.size();
    }

    public Server_List()
    {

    }

    public ArrayList<Server_info> getServers()
    {
        return servers;
    }

    public int getLen()
    {
        return len;
    }

    public Server_info get_server_info(int i)
    {
        return servers.get(i);
    }

    public void add(Server_info info)
    {
        servers.add(info);
        len++;
    }
}