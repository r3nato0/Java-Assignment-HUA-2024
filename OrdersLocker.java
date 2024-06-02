import java.util.ArrayList;
import java.util.List;

public class OrdersLocker extends Orders{
    private String CostumerName;
    private String CostumerSurrname;
    private String DriverName;
    private String DriverLastName;
    private String DateTime;
    private String address;
    private String Status;
    private BucketShop CostumersBucket;
    private List<ProductsInBucket> ItemsBought;
    private Integer CompartmentNumber;


    public OrdersLocker(Integer OrderId,Costumers costumer, Drivers Driver,Lockers locker,EachCompartmentOfLockers compartment) {
        super(OrderId,costumer, Driver);
        this.CostumerName = costumer.getName();
        this.CostumerSurrname = costumer.getSurname();
        this.DriverName = Driver.getName();
        this.DriverLastName = Driver.getSurname();
        this.address = locker.getAddress();
        this.CompartmentNumber = compartment.getLockerNumber();
        this.Status = costumer.getAddress();
        this.ItemsBought = new ArrayList<>(costumer.getProductsInBucket());
        costumer.clearBucket();

    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order ID: ").append(getOrderId()).append("\n");
        sb.append("Customer Name: ").append(CostumerName).append("\n");
        sb.append("Customer Surname: ").append(CostumerSurrname).append("\n");
        sb.append("Driver Name: ").append(DriverName).append("\n");
        sb.append("Driver Last Name: ").append(DriverLastName).append("\n");
        sb.append("Date & Time: ").append(DateTime).append("\n");
        sb.append("Address: ").append(address).append("\n");
        sb.append("Status: ").append(Status).append("\n");
        sb.append("Products Bought:\n");
        for (ProductsInBucket product : ItemsBought) {
            sb.append("  - ").append(product.getName()).append(", Quantity: ").append(product.getQuantity()).append("\n");
            // Add more details of the product if needed
        }
        return sb.toString();
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

    public String getDateTime() {
        return this.DateTime;
    }

    public void setDateTime(String DateTime) {
        this.DateTime = DateTime;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return this.Status;
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