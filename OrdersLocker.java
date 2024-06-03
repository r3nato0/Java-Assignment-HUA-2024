import java.util.ArrayList;
import java.util.List;

public class OrdersLocker extends Orders{
    private String CostumersFirstName;
    private String CostumerSurrname;
    private String DriverFirstName;
    private String DriverLastName;
    private String OrderTime;
    private String address;
    private String StatusOrder;
    private BucketShop CostumersBucket;
    private List<ProductsInBucket> ItemsBought;
    private Integer CompartmentNumber;
    private String Status;
    private Drivers Driver;


    public OrdersLocker(Costumers costumer, Drivers Driver,Lockers locker,EachCompartmentOfLockers compartment) {
        super(costumer, Driver);
        this.Orderid = super.getOrderid();
        this.Status = super.getStatus();
        this.DriverFirstName = super.getDriverFirstName();
        this.DriverLastName = super.getDriverSurrname();
        this.CostumersFirstName = super.getCostumerFirstName();
        this.CostumerSurrname = super.getCostumerSurrname();
        this.address = locker.getAddress();
        this.CompartmentNumber = compartment.getLockerNumber();
        this.StatusOrder = costumer.getAddress();
        this.ItemsBought = new ArrayList<>(costumer.getProductsInBucket());
        costumer.clearBucket();
        compartment.setStatusUnavailable();
        compartment.setOrderId(super.getOrderid()); 
        this.OrderTime = super.getOrderDateTime();
    
    }


    
    //GETTERS
    public String getOrderTime(){
        return this.OrderTime;
    }
    public Integer getCompartmentNumber(){
        return this.CompartmentNumber;
    }
    public String getCostumerName() {
        return this.CostumersFirstName;
    }
    public String getCostumerSurrname() {
        return this.CostumerSurrname;
    }
    public String getAddress() {
        return this.address;
    }
    public String getStatusOrder() {
        return this.StatusOrder;
    }
    public String getDriverLastName() {
        return this.DriverLastName;
    }
    public List<ProductsInBucket> getProductsInOrder() {
        return this.ItemsBought;
    }
    public BucketShop getCostumersBucket() {
        return this.CostumersBucket;
    }

    //SUPER GETTERS
    public Drivers getDriver(){
        return super.getDriver();
    }
    public String getDriverSurrname() {
        return super.getDriverSurrname();
    }
    public String getDriverName() {
        return super.getDriverFirstName();
    }

    public String getCostumerFirstName() {
        return super.getCostumerFirstName();
    }
    public Integer getOrderId(){
        return super.getOrderid();
    }
    public String getStatus(){
        return super.getStatus();
    }
    
    //SETTERS



    public void setAddress(String address) {
        this.address = address;
    }
    public void setStatusOrder(String StatusOrder) {
        this.StatusOrder = StatusOrder;
    }
    public void setCostumersBucket(BucketShop CostumersBucket) {
        this.CostumersBucket = CostumersBucket;
    }
    public void setProductsInOrder(List<ProductsInBucket> ItemsBought) {
        this.ItemsBought = ItemsBought;
    }


    //SUPER SETTERS

    public void setCostumerFirstName(String CostumersFirstName) {
        super.setCostumerFirstName(CostumersFirstName);
    }
    public void setCostumerSurrname(String CostumerSurrname) {
        super.setCostumerSurrname(CostumerSurrname);
    }
    public void setDriversFirsName(String DriverFirstName){
        super.setDriverFirstName(DriverFirstName);
    }
    public void setDriverLastName(String DriverLastName) {
        super.setDriverLastName(DriverLastName);
    }

    public void setNewDriver(Drivers Driver){
        super.setNewDriver(Driver);
    }
    public void setDriver(Drivers Driver){ // will be never used, as the setNewDriver does all the work
        super.setDriver(Driver);
    }
 
    public void setStatusPending(){
        super.setStatusPending();
    }
    public void setStatusCompleted(){
        super.setStatusCompleted();
    }
    public void setOrderTime(String OrderTime){
        super.setOrderDateTime(OrderTime);
    }

}