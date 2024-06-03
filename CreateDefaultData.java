public class CreateDefaultData{




    public static void CreateDefaultInstances(){
        ProductManager.CreateDefaultProducts();
        DriverManager.CreateDefaultDrivers();
        CostumerManager.CreateDefaultCostumers();
        LockerManager.CreateDefaultLockers();
        OrderManager.CreateDefaultOrders();
    }
}