

public class Main {


    public static void main(String[] args) {
        ProductManager.CreateDefaultProducts();
        DriverManager.CreateDefaultDrivers();
        CostumerManager.CreateDefaultCostumers();
        LockerManager.CreateDefaultLockers();
        OrderManager.CreateDefaultOrders();;
        UserInterface.ShowMenu();
    }
}