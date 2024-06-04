
public class OrdersHome extends Orders{
    private String address;



    public OrdersHome(Costumers costumer, Drivers Driver) {
        super(costumer, Driver);
        this.address = costumer.getAddress();


    }

    public String getAddress() {
        return this.address;
    }


    public void setAddress(String address) {
        this.address = address;
    }


}