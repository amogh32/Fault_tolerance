package sample;

public class Name_Node_List
{
    private Server_List name_node_list;

    public Name_Node_List()
    {
        name_node_list=new Server_List();
        name_node_list.add(new Server_info("localhost",10000));
        name_node_list.add(new Server_info("localhost",11000));
    }

    public Server_List getName_node_list()
    {
        return name_node_list;
    }
}