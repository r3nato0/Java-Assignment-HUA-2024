


public class Orders{
        protected static Integer nextId = 001;
        protected Integer Orderid;
        private Costumers costumer;
        private Drivers Driver;
        private String OrderDateTime;
        private String Status;

        public Orders(Costumers costumer, Drivers Driver){
            this.Orderid=nextId++;
            this.costumer = costumer;
            this.Driver = Driver;
            this.OrderDateTime = OrderManager.CurrentDateTime();
            this.Status = "Pending";
        }


    public String getStatus(){
        return this.Status;
    }

    public void setStatusPending(){
        this.Status = "Pending";
    }

    public void setStatusCompleted(){
        this.Status = "Completed";
    }

    public String getOrderDateTime(){
        return this.OrderDateTime;
    }

    public void setOrderDateTime(String OrderDateTime){
        this.OrderDateTime = OrderDateTime;
    }

    public Integer getOrderid() {
        return this.Orderid;
    }


    public void setOrderid(Integer Orderid) {
        this.Orderid = Orderid;
    }

    public Costumers getCostumer() {
        return this.costumer;
    }

    public void setCostumer(Costumers costumer) {
        this.costumer = costumer;
    }

    public Drivers getDriver() {
        return this.Driver;
    }

    public void setDriver(Drivers Driver) {
        this.Driver = Driver;
    }

    
}
