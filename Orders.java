/* 
 * COSTUMER
 * DRIVER
 * TIME AND DATE
 * PRODUCTS
 * QUANTITY OF PRODUCTS
 * STATUS
 */
    import java.util.ArrayList;
    import java.util.List;
    public class Orders{
        private static int nextId = 001;
        private int Orderid;
        private Costumers costumer;
        private Drivers Driver;
        private String OrderDateTime;
        private List<String> products = new ArrayList<>();
        private List<Integer> quantity = new ArrayList<>();
        private Boolean Status;
        private String ShipAdress;
        private String CostumerName;
        private String DriverName;
        private List<BucketShop> ProductsInBucket;

        public Orders( Costumers costumer, Drivers Driver, List<BucketShop> ProductsInBucket){
            this.
            this.costumer = costumer;
            this.Driver = Driver;
            this.ProductsInBucket = ProductsInBucket;
        }

    public int getOrderid() {
        return this.Orderid;
    }

    public String getShipAdress() {
        return this.ShipAdress;
    }
    public void setOrderid(int Orderid) {
        this.Orderid = Orderid;
    }

    public void setShipAdress(String ShipAdress) {
        this.ShipAdress = ShipAdress;
    }

    public String getCostumerName() {
        return this.CostumerName;
    }

    public void setCostumerName(String CostumerName) {
        this.CostumerName = CostumerName;
    }

    public String getDriverName() {
        return this.DriverName;
    }

    public void setDriverName(String DriverName) {
        this.DriverName = DriverName;
    }

    public String getOrderDateTime() {
        return this.OrderDateTime;
    }

    public void setOrderDateTime(String OrderDateTime) {
        this.OrderDateTime = OrderDateTime;
    }

    public List<String> getProducts() {
        return this.products;
    }

    public void setProducts(List<String> products) {
        this.products = products;
    }

    public List<Integer> getQuantity() {
        return this.quantity;
    }

    public void setQuantity(List<Integer> quantity) {
        this.quantity = quantity;
    }

    public Boolean getStatus() {
        return this.Status;
    }

    public void setStatus(Boolean Status) {
        this.Status = Status;
    }


}
