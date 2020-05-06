package sample;

public class Worker_list
{
    private Server_List worker_list;

    public Worker_list()
    {
        worker_list=new Server_List();
        worker_list.add(new Server_info("localhost",12000));
        worker_list.add(new Server_info("localhost",13000));
        worker_list.add(new Server_info("localhost",14000));
        worker_list.add(new Server_info("localhost",15000));
        worker_list.add(new Server_info("localhost",16000));
    }

    public Server_List getWorker_list()
    {
        return worker_list;
    }
}