


public class Orders{
        protected static Integer nextId = 001;
        protected Integer Orderid;
        private Costumers costumer;
        private Drivers Driver;
        private String OrderDateTime;
        private String Status;
        private String DriverFirstName;
        private String DriverLastName;
        private String CostumersFirstName;
        private String CostumerSurrname;
        private String Address;



        public Orders(Costumers costumer, Drivers Driver){
            this.Orderid=nextId++;
            this.costumer = costumer;
            this.Driver = Driver;
            this.CostumersFirstName = costumer.getName();
            this.CostumerSurrname = costumer.getSurname();
            this.DriverFirstName = Driver.getName();
            this.DriverLastName = Driver.getSurname();
            this.OrderDateTime = OrderManager.CurrentDateTime();
            this.Status = "Pending";
            this.Address = costumer.getAddress();

        }


    //GETTERS
    public String getAddress(){
        return this.Address;
    }
    public String getCostumerFirstName(){
        return this.CostumersFirstName;
    }
    public String getCostumerSurrname(){
        return this.CostumerSurrname;
    }
    public String getStatus(){
        return this.Status;
    }
    public Integer getOrderid() {
        return this.Orderid;
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


    //SETTERS
    public void setCostumerFirstName(String CostumersFirstName){
        this.CostumersFirstName = CostumersFirstName;
    }
    public void setCostumerSurrname(String CostumerSurrname){
        this.CostumerSurrname = CostumerSurrname;
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
        this.CostumerSurrname = costumer.getSurname();

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

}
