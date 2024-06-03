import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class OrderManager{
    private static List<Orders> AllOrdersList = new ArrayList<>();
    private final static Integer MaxProductsPrintLength = 15; // Just a Limit of characters from products name to get printed, will be passed to UserInterface.PrintOnly(Name,Max...)
    

    public static void Create() {
        Lockers RandomLocker;
        EachCompartmentOfLockers RandomCompartment;
        Costumers SelectedCostumer;
        Drivers SelectedDriver;
        String CostumerFullName =UserInterface.InputTypeStringWithSpace("Please Provide the full name of the Costumer (upper cases will be ignored)  (example: nikos papadopoylos): ");
        if(CostumerManager.CheckCostumerExists(CostumerFullName)){
            System.out.printf("The Costumer allready Exsits, the rest of the fields are filled accordingly %n");
        }
        else{
            System.out.println("The Costumer does not exits, Creating new Entry");
            CostumerManager.NotFoundAddNew(CostumerFullName); // Other Filleds are prompted and filled via the NotFoundAddNew Method
        }

        String DriverFullName = UserInterface.InputTypeStringWithSpace("Please Provide the full name of the driver (upper cases will be ignored)  (example: nikos papadopoylos): ");
        if (DriverManager.CheckDriverExists(DriverFullName)){
            System.out.printf("The Driver allready Exsits, the rest of the fields are filled accordingly %n");
        }
        else{
            System.out.println("The Driver does not exits, Creating new Entry");
            DriverManager.NotFoundAddNew(DriverFullName); // Other Filleds are prompted and filled via the NotFoundAddNew Method
        }

        SelectedDriver = DriverManager.GetCurrentDriverByFullName(DriverFullName); 
        SelectedCostumer = CostumerManager.GetCurrentCostumerByFullName(CostumerFullName);
        System.out.println(SelectedCostumer.getName());
        while (true) {
            Products product = ProductManager.GetProductByNameORId();
            Integer quantity = ProductManager.GetSelectedProductsQuantity(product);
            
            SelectedCostumer.addProductToBucket(product, quantity);
            
            boolean addAnotherProduct = UserInterface.InputTypeBoolean("Add another product to the bucket;  ");
            if (!addAnotherProduct) {
                break;
            }
        }
        
        Integer HomeOrLocker = UserInterface.InputTypeLockerORHome("Press 1 to ship to the Costumers Adress,Press 2 to ship to A Locker Location");
        if(HomeOrLocker == 1){
            OrdersHome NewOrderHome = new OrdersHome(SelectedCostumer, SelectedDriver);
            AllOrdersList.add(NewOrderHome);
        }
        else{
            RandomLocker = LockerManager.getRandomFreeLocker();
            if(RandomLocker!=null){
                RandomCompartment = LockerManager.getRandomCompartmentOfLocker(RandomLocker);
                OrdersLocker OrdersLocker = new OrdersLocker(SelectedCostumer, SelectedDriver,RandomLocker,RandomCompartment);
                AllOrdersList.add(OrdersLocker);

            }else{
                System.out.println("There are no Empty Lockers at the moments, sorry the order has been canceled");
            }
        }

}


public static void printAllOrders() {
    // header
    System.err.println("--------------------------------------------------------------------------------------------------Orders In the costumer's Address");
    System.out.printf("%-10s %-20s %-25s %-20s %-10s\n", "Order ID", "CustomerName","Address", "ProductName", "Quantity");
    System.out.println("----------------------------------------------------------------------------------------------------------------------------------");

    for (Orders order : AllOrdersList) {
        if (order instanceof OrdersHome) {
            OrdersHome ordersHome = (OrdersHome) order;
            Integer orderId = ordersHome.getOrderId();
            String CostumerFullName = ordersHome.getCostumerFirstName() + " " + ordersHome.getCostumerSurrname();
            List<ProductsInBucket> productsInOrder = ordersHome.getProductsInOrder();
            String Address = ordersHome.getAddress();
            //customer details first
            if (!productsInOrder.isEmpty()) {
                ProductsInBucket firstProduct = productsInOrder.get(0);
                
                System.out.printf("%-10d %-20s %-25s %-20s %-10d\n", orderId, CostumerFullName,Address,UserInterface.PrintOnly(firstProduct.getName(), MaxProductsPrintLength), firstProduct.getQuantity());

                // remaining products
                for (int i = 1; i < productsInOrder.size(); i++) {
                    ProductsInBucket product = productsInOrder.get(i);
                    System.out.printf("%-10s %-20s %-25s %-20s %-10d\n", "", "","", UserInterface.PrintOnly(product.getName(), MaxProductsPrintLength), product.getQuantity());
                }
            }
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------");}
        }
            System.out.println();
            System.out.println();
            System.out.println("------------------------------------------------------------------------------------------------------Orders In the Locker Address");

    for (Orders order : AllOrdersList) {
        if (order instanceof OrdersLocker) {
            OrdersLocker orderslocker = (OrdersLocker) order;
            Integer orderId = orderslocker.getOrderId();
            String CostumerFullName = orderslocker.getCostumerName() + " " + orderslocker.getCostumerSurrname();
            List<ProductsInBucket> productsInOrder = orderslocker.getProductsInOrder();
            String Address = orderslocker.getAddress();
            Integer CompartmentNumber = orderslocker.getCompartmentNumber();
            
            // Print customer details first
            if (!productsInOrder.isEmpty()) {
                ProductsInBucket firstProduct = productsInOrder.get(0);
                
                System.out.printf("%-10d %-20s %-25s %-20s %-10d\n", orderId, CostumerFullName,"locker: " + Address,UserInterface.PrintOnly(firstProduct.getName(), MaxProductsPrintLength), firstProduct.getQuantity());
                System.out.printf("%-10s %-20s %-25s ", "","","Compartment: " + CompartmentNumber);
                // Print remaining products
                for (int i = 1; i < productsInOrder.size(); i++) {
                    ProductsInBucket product = productsInOrder.get(i);
                    System.out.printf("%-20s %-10d\n", UserInterface.PrintOnly(product.getName(), MaxProductsPrintLength), product.getQuantity());
                }
            }
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------");}
        }
    }

    //lybrary used  https://docs.oracle.com/javase%2F8%2Fdocs%2Fapi%2F%2F/java/time/format/DateTimeFormatter.html
    //&& https://docs.oracle.com/javase%2F8%2Fdocs%2Fapi%2F%2F/java/time/LocalDateTime.html
    // will return the current Date And time all together. for the orders fields as String
    //we also take only the date or only the time, example.substring(0,example.indexof(" ")); and the time example.substring(example.indexof(" ")+1,example.length());
    public static String CurrentDateTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
        LocalDateTime now = LocalDateTime.now();
        String formattedDateTime = now.format(formatter);
        return formattedDateTime;
    }
 


    public static void CreateDefaultOrders(){

        //Creating HomeOrders
        Drivers SelectedDriver = DriverManager.GetCurrentDriverByFullName("Renato Nake"); 
        Costumers SelectedCostumer = CostumerManager.GetCurrentCostumerByFullName("Maria Georgioy");
        for(int i =1;i <=2;i++){ //getting 2 products, id:1 and id:2, and adding quantity by 2

            Products product = ProductManager.GetProductById(i);
            SelectedCostumer.addProductToBucket(product, 2);
        }
        OrdersHome NewOrderHome = new OrdersHome(SelectedCostumer, SelectedDriver);

        
        Drivers SelectedDriverSecond = DriverManager.GetCurrentDriverByFullName("Anthoni Tsouklas"); 
        Costumers SelectedCostumerSecond = CostumerManager.GetCurrentCostumerByFullName("Izabel Georgioy");
        for(int i =2;i <=3;i++){ //getting 2 products, id:1 and id:2, and adding quantity by 2

            Products product = ProductManager.GetProductById(i);
            SelectedCostumerSecond.addProductToBucket(product, 1);
        }
        OrdersHome NewOrderHomeSecond = new OrdersHome(SelectedCostumerSecond, SelectedDriverSecond);
        AllOrdersList.add(NewOrderHomeSecond);
        AllOrdersList.add(NewOrderHome);
        //Creating LockerOrders
        Drivers SelectedDriverThird = DriverManager.GetCurrentDriverByFullName("Renato Nake"); 
        Costumers SelectedCostumerThird = CostumerManager.GetCurrentCostumerByFullName("Maria Georgioy");
        for(int i =1;i <=2;i++){ //getting 2 products, id:1 and id:2, and adding quantity by 2

            Products product = ProductManager.GetProductById(i);
            SelectedCostumerThird.addProductToBucket(product, 2);
        }
        Lockers RandomLocker = LockerManager.getRandomFreeLocker();
        EachCompartmentOfLockers RandomCompartment = LockerManager.getRandomCompartmentOfLocker(RandomLocker);
        OrdersLocker OrdersLockerFirst = new OrdersLocker(SelectedCostumerThird, SelectedDriverThird,RandomLocker,RandomCompartment);

        
        Drivers SelectedDriverForth = DriverManager.GetCurrentDriverByFullName("Anthoni Tsouklas"); 
        Costumers SelectedCostumerForth = CostumerManager.GetCurrentCostumerByFullName("Izabel Georgioy");
        for(int i =2;i <=3;i++){ //getting 2 products, id:1 and id:2, and adding quantity by 2

            Products product = ProductManager.GetProductById(i);
            SelectedCostumerForth.addProductToBucket(product, 1);
        }
        Lockers RandomLockerSecond = LockerManager.getRandomFreeLocker();
        EachCompartmentOfLockers RandomCompartmentSecond = LockerManager.getRandomCompartmentOfLocker(RandomLocker);
        OrdersLocker OrdersLockerSecond = new OrdersLocker(SelectedCostumerForth, SelectedDriverForth,RandomLockerSecond,RandomCompartmentSecond);
        AllOrdersList.add(OrdersLockerFirst);
        AllOrdersList.add(OrdersLockerSecond);

    }

}