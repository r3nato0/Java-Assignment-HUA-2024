

import java.util.List;

public class Orders{
        protected static Integer nextId = 001;
        protected Integer Orderid;
        private Costumers costumer;
        private Drivers Driver;
        private String OrderDateTime;


        public Orders(Integer OrderId,Costumers costumer, Drivers Driver){
            this.Orderid=nextId++;
            this.costumer = costumer;
            this.Driver = Driver;

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

    public String getOrderDateTime() {
        return this.OrderDateTime;
    }

    public void setOrderDateTime(String OrderDateTime) {
        this.OrderDateTime = OrderDateTime;
    }
    
}
