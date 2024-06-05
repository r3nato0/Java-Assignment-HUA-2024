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
        private Rating CostumerRating;
        private Integer costumerid;

        public Orders(Costumers costumer, Drivers Driver){
            this.Orderid=nextId++;
            this.costumer = costumer;
            this.Driver = Driver;
            this.costumerId = costumer.getCostumerid();
            this.CostumersFirstName = costumer.getName();
            this.CostumerLastName = costumer.getSurname();
            this.DriverFirstName = Driver.getName();
            this.DriverLastName = Driver.getSurname();
            this.costumerid = costumer.getId(); // will help for the ratins part
            this.OrderDateTime = OrderManager.CurrentDateTime();
            this.Status = "Pending";
            this.CostumerRating = new Rating(this);

            this.ItemsBought = new ArrayList<>(costumer.getProductsInBucket());
            costumer.clearBucket();

        }


    public Integer getCostumersID(){
        return this.costumerId;
    }

    public static List<Rating> getratinglist(){
        return ratinglist;
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

    public Integer getRatingID(){
        return CostumerRating.getRatingId();
    }

    public void setRatingId(Integer RatingId){
        CostumerRating.setRatingId(RatingId);
    }




    public void setRating(Integer Rating){
        if(this.Status.equals("Completed")){
            CostumerRating.setCostumerRating(Rating);
        }
        else{
            System.out.println("The Order is still Pending, cannot Leave A Review");
        }
    }

    public Integer getRating(){
        return CostumerRating.getCostumerRating();
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
        CostumerRating.setStatus("Pending");
    }


    public void setStatusCompleted(){
        this.Status = "Completed";
        CostumerRating.setStatus("Completed");
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


