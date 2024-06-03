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
        this.CostumerName = super.getCostumerFirstName();
        this.CostumerSurrname = super.getCostumerSurrname();
        this.DriverName = super.getDriverFirstName();
        this.DriverLastName = super.getCostumerSurrname();
        this.address = super.getAddress();
        this.ItemsBought = new ArrayList<>(costumer.getProductsInBucket());
        costumer.clearBucket();
        

    }



    //GETTERS
    public String getStatus(){
        return super.getStatus();
    }
    public String getOrderDateTime(){
        return  super.getOrderDateTime();
    }
    public Integer getOrderId(){
        return super.getOrderid();
    }
    
    public String getCostumerFirstName() {
        return super.getCostumerFirstName();
    }
    public String getCostumerSurrname() {
        return super.getCostumerSurrname();
    }
    public String getDriverFirstName() {
        return super.getCostumerFirstName();
    }
    public String getDriverLastName() {
        return super.getDriverSurrname();
    }
    public String getAddress() {
        return super.getAddress();
    }
    public BucketShop getCostumersBucket() {
        return this.CostumersBucket;
    }
    public List<ProductsInBucket> getProductsInOrder() {
        return this.ItemsBought;
    }

    //SETTERS
    public void setStatusPending(){
        super.setStatusPending();
    }
    public void setStatusCompleted(){
        super.setStatusCompleted();
    }
    public void setOrderTime(String OrderTime){
        super.setOrderDateTime(OrderTime);
    }
    
    public void setCostumerName(String CostumerName) {
        this.CostumerName = CostumerName;
    }
    public void setCostumerSurrname(String CostumerSurrname) {
        this.CostumerSurrname = CostumerSurrname;
    }
    public void setDriverName(String DriverName) {
        this.DriverName = DriverName;
    }
    public void setDriverLastName(String DriverLastName) {
        this.DriverLastName = DriverLastName;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setStatus(String Status) {
        this.Status = Status;
    }
    public void setCostumersBucket(BucketShop CostumersBucket) {
        this.CostumersBucket = CostumersBucket;
    }
    public void setProductsInOrder(List<ProductsInBucket> ItemsBought) {
        this.ItemsBought = ItemsBought;
    }

}