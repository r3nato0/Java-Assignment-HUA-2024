import java.util.ArrayList;
import java.util.List;

public abstract class Orders{
        private static Integer nextId = 1;
        private Integer Orderid;
        private Costumers costumer;
        private Drivers Driver;
        private String OrderDateTime;
        private String Status;
        private String DriverFirstName;
        private String DriverLastName;
        private String CostumersFirstName;
        private String CostumerLastName;
        private Integer costumerId;
        private static List<Rating> ratinglist =  new ArrayList<>();;
        private List<ProductsInBucket> ItemsBought;
        private BucketShop CostumersBucket;
        


        public Orders(Costumers costumer, Drivers Driver){
            this.Orderid=nextId++;
            this.costumer = costumer;
            this.Driver = Driver;
            this.costumerId = costumer.getCostumerid();
            this.CostumersFirstName = costumer.getName();
            this.CostumerLastName = costumer.getSurname();
            this.DriverFirstName = Driver.getName();
            this.DriverLastName = Driver.getSurname();
            this.OrderDateTime = OrderManager.CurrentDateTime();
            this.Status = "Pending";
            ratinglist.add(new Rating(this));
            this.ItemsBought = new ArrayList<>(costumer.getProductsInBucket());
            costumer.clearBucket();

        }

    public List<ProductsInBucket> getProductsInOrder() {
        return this.ItemsBought;
    }
    public void setProductsInOrder(List<ProductsInBucket> ItemsBought) {
        this.ItemsBought = ItemsBought;
    }

    public static List<Rating> getOrdersRating(){
        return ratinglist;
    }

    //GETTERS
    public String getCostumerFirstName(){
        return this.CostumersFirstName;
    }
    public String GetCostumerLastName(){
        return this.CostumerLastName;
    }
    public String getStatus(){
        return this.Status;
    }

    public String getOrderDateTime(){
        return this.OrderDateTime;
    }
    public Costumers getCostumer() {
        return this.costumer;
    }
    public Drivers getDriver() {
        return this.Driver;
    }
    
    public String getDriverFirstName(){
        return this.DriverFirstName;
    }
    public String getDriverSurrname(){
        return this.DriverLastName;
    }
    public Integer getCostumerId(){
        return this.costumerId;
    }
    public String getCostumerFullName(){
        return this.CostumersFirstName + " " + this.CostumerLastName;
    }
    public String getDriverFullName(){
        return this.DriverFirstName + " " + this.DriverLastName;
    }

    public Integer getOrderId(){
        return this.Orderid;
    }


    //SETTERS
    public void setCostumerFirstName(String CostumersFirstName){
        this.CostumersFirstName = CostumersFirstName;
    }
    public void setCostumerLastName(String CostumerLastName){
        this.CostumerLastName = CostumerLastName;
    }
    public void setDriverFirstName(String DriverFirstName){
        this.DriverFirstName = DriverFirstName; 
    }
    public void setDriverLastName(String DriverLastName){
        this.DriverLastName=DriverLastName;
    }

    public void setNewCostumer(Costumers costumer){
        this.costumer = costumer;
        this.CostumersFirstName = costumer.getName();
        this.CostumerLastName = costumer.getSurname();

    }
    public void setStatusPending(){
        this.Status = "Pending";
    }


    public void setStatusCompleted(){
        this.Status = "Completed";
    }
    public void setOrderDateTime(String OrderDateTime){
        this.OrderDateTime = OrderDateTime;
    }
    public void setOrderid(Integer Orderid) {
        this.Orderid = Orderid;
    }
    public void setCostumer(Costumers costumer) {
        this.costumer = costumer;
    }
    public void setDriver(Drivers Driver){
        this.Driver = Driver;
    }
    public void setNewDriver(Drivers Driver){
        this.Driver = Driver;
        this.DriverFirstName = Driver.getName();
        this.DriverLastName= Driver.getSurname();
    }

    public BucketShop getCostumersBucket() {
        return this.CostumersBucket;
    }
    public void setCostumersBucket(BucketShop CostumersBucket) {
        this.CostumersBucket = CostumersBucket;
    }



}


