import java.util.List;

public class OrderHome extends Orders{
    private String address;



    public OrdersHome(Costumers costumer, Drivers Driver, List<BucketShop> ProductsInBucket,String address) {
        super(costumer, Driver, ProductsInBucket);
        this.address = costumer.getAddress();
    }

    public int getOrderid() {
        return super.getOrderid();
    }

    public int getShipAdress() {
        return super.getShipAdress();
    }
    public void setOrderid(int Orderid) {
        super.setOrderid(Orderid);
    }

    public void setShipAdress(int ShipAdress) {
        super.setShipAdress(ShipAdress);
    }

    public String getCostumerName() {
        return super.getCostumerName();
    }

    public void setCostumerName(String CostumerName) {
        super.setCostumerName(CostumerName);
    }

    public String getDriverName() {
        return super.getDriverName();
    }

    public void setDriverName(String DriverName) {
        super.setDriverName(DriverName);
    }

    public String getOrderDateTime() {
        return super.getOrderDateTime();
    }

    public void setOrderDateTime(String OrderDateTime) {
        super.setOrderDateTime(OrderDateTime);
    }

    public List<String> getProducts() {
        return super.products;
    }

    public void setProducts(List<String> products) {
        super.setProducts(products);
    }

    public List<Integer> getQuantity() {
        return super.getQuantity();
    }

    public void setQuantity(List<Integer> quantity) {
        super.setQuantity(quantity);
    }


    public Boolean getStatus() {
        return super.getStatus();
    }

    public void setStatus(Boolean Status) {
        super.setStatus(Status);
    }







}