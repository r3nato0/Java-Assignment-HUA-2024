import java.util.ArrayList;
import java.util.List;

public class OrdersLocker extends Orders{
    private String CostumerName;
    private String CostumerSurrname;
    private String DriverName;
    private String DriverLastName;
    private String OrderTime;
    private String address;
    private String StatusOrder;
    private BucketShop CostumersBucket;
    private List<ProductsInBucket> ItemsBought;
    private Integer CompartmentNumber;
    private String Status;


    public OrdersLocker(Costumers costumer, Drivers Driver,Lockers locker,EachCompartmentOfLockers compartment) {
        super(costumer, Driver);
        this.Orderid = super.getOrderid();
        this.Status = super.getStatus();
        this.CostumerName = costumer.getName();
        this.CostumerSurrname = costumer.getSurname();
        this.DriverName = Driver.getName();
        this.DriverLastName = Driver.getSurname();
        this.address = locker.getAddress();
        this.CompartmentNumber = compartment.getLockerNumber();
        this.StatusOrder = costumer.getAddress();
        this.ItemsBought = new ArrayList<>(costumer.getProductsInBucket());
        costumer.clearBucket();
        compartment.setStatusUnavailable();
        compartment.setOrderId(super.getOrderid()); 
        this.OrderTime = super.getOrderDateTime();
    
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

    public String getOrderTime(){
        return this.OrderTime;
    }

    public void setOrderTime(String OrderTime){
        super.setOrderDateTime(OrderTime);
    }

    public Integer getCompartmentNumber(){
        return this.CompartmentNumber;
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

    public String getStatusOrder() {
        return this.StatusOrder;
    }

    public void setStatusOrder(String StatusOrder) {
        this.StatusOrder = StatusOrder;
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