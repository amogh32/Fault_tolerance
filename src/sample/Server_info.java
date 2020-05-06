package sample;

public class Server_info
{
    private String address;
    private int port;

    public Server_info(String address,int port)
    {
        this.address=address;
        this.port=port;
    }

    public String getAddress()
    {
        return address;
    }
    public int getPort()
    {
        return port;
    }

}