import java.util.ArrayList;
import java.util.List;

public class OrdersHome extends Orders{
    private String CostumerName;
    private String CostumerSurrname;
    private String DriverName;
    private String DriverLastName;
    private String OrderTime;
    private String address;
    private String Status;
    private BucketShop CostumersBucket;
    private List<ProductsInBucket> ItemsBought;



    public OrdersHome(Costumers costumer, Drivers Driver) {
        super(costumer, Driver);
        this.Orderid = super.getOrderid();
        this.Status = super.getStatus();
        this.OrderTime = super.getOrderDateTime();
        this.CostumerName = costumer.getName();
        this.CostumerSurrname = costumer.getSurname();
        this.DriverName = Driver.getName();
        this.DriverLastName = Driver.getSurname();
        this.address = costumer.getAddress();
        this.ItemsBought = new ArrayList<>(costumer.getProductsInBucket());
        costumer.clearBucket();
        

    }

    public String getStatus(){
        return super.getStatus();
    }

    public void setStatusPending(){
        super.setStatusPending();
    }
    
    public void setStatusCompleted(){
        super.setStatusCompleted();
    }

    public String getOrderDateTime(){
        return  super.getOrderDateTime();
    }

    public void setOrderTime(String OrderTime){
        super.setOrderDateTime(OrderTime);
    }
    
    public Integer getOrderId(){
        return super.getOrderid();
    }

    public String getCostumerName() {
        return this.CostumerName;
    }

    public void setCostumerName(String CostumerName) {
        this.CostumerName = CostumerName;
    }

    public String getCostumerSurrname() {
        return this.CostumerSurrname;
    }

    public void setCostumerSurrname(String CostumerSurrname) {
        this.CostumerSurrname = CostumerSurrname;
    }

    public String getDriverName() {
        return this.DriverName;
    }

    public void setDriverName(String DriverName) {
        this.DriverName = DriverName;
    }

    public String getDriverLastName() {
        return this.DriverLastName;
    }

    public void setDriverLastName(String DriverLastName) {
        this.DriverLastName = DriverLastName;
    }


    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public void setStatus(String Status) {
        this.Status = Status;
    }

    public BucketShop getCostumersBucket() {
        return this.CostumersBucket;
    }

    public void setCostumersBucket(BucketShop CostumersBucket) {
        this.CostumersBucket = CostumersBucket;
    }

    public List<ProductsInBucket> getProductsInOrder() {
        return this.ItemsBought;
    }

    public void setProductsInOrder(List<ProductsInBucket> ItemsBought) {
        this.ItemsBought = ItemsBought;
    }

}