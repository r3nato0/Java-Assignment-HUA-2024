/* 
 * COSTUMER
 * DRIVER
 * TIME AND DATE
 * PRODUCTS
 * QUANTITY OF PRODUCTS
 * STATUS
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
public class Orders{
    private static int nextId = 001;
    private int Orderid;
    private String CostumerName;
    private String DriverName;
    private static final Scanner scanner = new Scanner(System.in);
    private String OrderDateTime;
    List<String> products = new ArrayList<>();
    List<Integer> quantity = new ArrayList<>();
    private Boolean Status;
    private int ShipAdress;


    public Orders( String CostumerName, String DriverName, String OrderDateTime, List<String> products, List<Integer> quantity, Boolean Status, int ShipAdress){
        this.CostumerName = CostumerName;
        this.DriverName = DriverName;
        this.OrderDateTime = OrderDateTime;
        this.products = products;
        this.quantity = quantity;
        this.Status = Status;
        this.ShipAdress = ShipAdress;
    }

    public int getOrderid() {
        return this.Orderid;
    }

    public int getShipAdress() {
        return this.ShipAdress;
    }
    public void setOrderid(int Orderid) {
        this.Orderid = Orderid;
    }

    public void setShipAdress(int ShipAdress) {
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

    public Boolean isStatus() {
        return this.Status;
    }

    public Boolean getStatus() {
        return this.Status;
    }

    public void setStatus(Boolean Status) {
        this.Status = Status;
    }

    public static void Create() {
        String DriverName = UserInterface.StringInput("Type Drivers First Name: ");
        String DriverLastName = UserInterface.StringInput("Type Drivers Last Name: ");
        Boolean IsValid = Drivers.CheckDriverExists(Drivers.GetDriverList(),DriverName,DriverLastName);
        if ( IsValid ){
            System.out.printf("The Driver allready Exsits, the rest of the fields are filled accordingly %n");
            Drivers SelectedDriver = Drivers.GetDriver(Drivers.GetDriverList(),DriverName);
            
        }
        else{
            System.out.printf("The Driver Name does not exist, continuing with the rest of the fields: ");
            String Adress = UserInterface.StringInput("Type Drivers Adress: ");
            String DriversEmail = UserInterface.StringInput("Type Drivers Email: ");
            Integer DriverSAFM =UserInterface.IntegerInputForAFM("Type Drivers AFM: ");
            String DriversPlateNumber = UserInterface.StringInput("Type Drivers PlateNumber: ");
            Drivers.AddNew(Drivers.GetDriverList(),DriverName,DriverLastName,Adress,DriversEmail,DriverSAFM,DriversPlateNumber);
        }
}




}