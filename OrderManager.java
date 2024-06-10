import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Iterator;
import java.sql.Driver;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class OrderManager{
    private static List<Orders> AllOrdersList = new ArrayList<>();
    private final static Integer MaxProductsPrintLength = 15; // Just a Limit of characters from products name to get printed, will be passed to UserInterface.PrintOnly(Name,Max...)
    

    public static List<Orders> GetAllOrdersList(){
        return AllOrdersList;
    }


   public static void CreateSecond() {
        
        List<String> ActionRecorder=null;
        String HomeOrLocker = UserInterface.InputTypeLockerORHome("Press : 1) For Home delivery\nPress : 2) For Lockers Delivery");
        Drivers SelectedDriver=null;
        String DriverFullName;
        while (true) {
            DriverFullName = UserInterface.InputTypeStringWithSpace("Please Provide the full name of the driver (upper cases will be ignored)  (example: nikos papadopoylos): ");
            boolean DriverOld=DriverManager.CheckDriverExists(DriverFullName);
            if (DriverOld){
                if(DriverManager.GetCurrentDriverByFullName(DriverFullName).getType().equals(HomeOrLocker)){
                    System.out.println("The Driver allready Exsits. The rest of the fields are filled accordingly %n");
                    System.out.println("The Selected Driver also does "+HomeOrLocker);
                    
                    break;
                }else{
                    System.out.println("Driver Exists but does not do the type of delivery you selected");
                    System.out.println("Select Another ");
                }
    
            }
            else{
                System.out.println("The Driver does not exits, Creating new Entry");
                ActionRecorder = DriverManager.NotFoundAddNew(DriverFullName,HomeOrLocker); // Other Filleds are prompted and filled via the NotFoundAddNew Method
                break;
            }            
        }

        if(ActionRecorder!=null){
            if((ActionRecorder.size()==2)){
                HomeOrLocker = ActionRecorder.get(1);
            }else{
                System.out.println(ActionRecorder.get(0));
                DriverFullName = ActionRecorder.get(0);
                
            }
            }

        SelectedDriver = DriverManager.GetCurrentDriverByFullName(DriverFullName);
        

        //Costumer Selection Part
        Costumers SelectedCostumer;
        String CostumerFullName =UserInterface.InputTypeStringWithSpace("Please Provide the full name of the Costumer (upper cases will be ignored)  (example: nikos papadopoylos): ");
        if(CostumerManager.CheckCostumerExists(CostumerFullName)){
            System.out.printf("The Costumer allready Exsits, the rest of the fields are filled accordingly %n");
        }
        else{
            System.out.println("The Costumer does not exits, Creating new Entry");
            CostumerManager.NotFoundAddNew(CostumerFullName); // Other Fildes are prompted and filled via the NotFoundAddNew Method
        }
        SelectedCostumer = CostumerManager.GetCurrentCostumerByFullName(CostumerFullName);
        


        //Selection of Products part
        // The exercises requires 2 product bought max, i did it for unmetered amount
        while (true) {
            Products product = ProductManager.GetProductByNameORId();
            if(product.getAvailableQuantity()>0){
                Integer quantity = ProductManager.GetSelectedProductsQuantity(product);
            
                SelectedCostumer.addProductToBucket(product, quantity);
            }else{
                System.out.println("The product has 0 avaiable quantity,please select another prodduct");
            }
            
            boolean addAnotherProduct = UserInterface.InputTypeBoolean("Add another product to the bucket;  ");

            if (!addAnotherProduct) {
                break;//user want no other products, we continue
            }
        }


        // a Case where the first product the costumer wants has 0 quantity, but he also does not wish any other product to buy
        if (SelectedCostumer.getProductsInBucket().size()==0){
            System.out.println("The Costumer does not want any product, the order has been canceled,returning to Main Menu");
            UserInterface.ShowMenu();
        }


        //Delivery to Home
        if(HomeOrLocker.equals(Constants.DELIVERYHOME)){
            OrdersHome NewOrderHome = new OrdersHome(SelectedCostumer, SelectedDriver);
            AllOrdersList.add(NewOrderHome);
            System.out.println("The order has been created successfully, Details of the order:");
            PrintOrder(NewOrderHome);
        }


        //Delivery to lockers

        else{
            Lockers RandomLocker;
            EachCompartmentOfLockers RandomCompartment;
            RandomLocker = LockerManager.getRandomFreeLocker();
            if(RandomLocker!=null){
                RandomCompartment = LockerManager.getRandomCompartmentOfLocker(RandomLocker);
                OrdersLocker OrdersLocker = new OrdersLocker(SelectedCostumer, SelectedDriver,RandomLocker,RandomCompartment);
                AllOrdersList.add(OrdersLocker);
                System.out.println("The order has been created successfully, Details of the order:");
                PrintOrder(OrdersLocker);
            }else{
                System.out.println("There are no avaiable Lockers, The order has been canceled");
            }}


}


public static void printAllOrders() {
    // header
    System.out.println("-----------------------------------------------------------------------------------------------------------------------Order In the costumer's Address");
    System.out.println();
    System.out.printf("%-10s %-20s %-20s %-25s %-20s %-10s %-10s %-10s\n", "Order ID", "CustomerName","DriversName","Address", "ProductName", "Quantity","Status","Rating");
    System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");

    for (Orders order : AllOrdersList) {
        if (order instanceof OrdersHome) {
            OrdersHome ordersHome = (OrdersHome) order;
            Integer orderId = ordersHome.getOrderId();
            String CostumerFullName = ordersHome.getCostumerFirstName() + " " + ordersHome.GetCostumerLastName();
            String DriverFullName = ordersHome.getDriverFirstName() + " " + ordersHome.getDriverSurrname();
            List<ProductsInBucket> productsInOrder = ordersHome.getProductsInOrder();
            String Address = ordersHome.getAddress();
            String Status = ordersHome.getStatus();
            Integer rating = ordersHome.getRating();
            String Rating;
            if (rating == null&& ordersHome.getStatus().equals(Constants.COMPLETED)) {
                Rating = "Not Rated yet!";
                }else if (rating == null&& ordersHome.getStatus().equals(Constants.PENDING)){
                    Rating = "Order Not finished";
                }
                 else {
                Rating = String.valueOf(rating);
                }
            //customer details first
            if (!productsInOrder.isEmpty()) {
                ProductsInBucket firstProduct = productsInOrder.get(0);
                
                System.out.printf("%-10d %-20s %-20s %-25s %-20s %-10d %-10s %-10s \n", orderId, CostumerFullName,DriverFullName,"locker: " + Address,UserInterface.PrintOnly(firstProduct.getName(), MaxProductsPrintLength), firstProduct.getQuantity(),Status,Rating);

                // remaining products
                for (int i = 1; i < productsInOrder.size(); i++) {
                    ProductsInBucket product = productsInOrder.get(i);
                    System.out.printf("%-10s %-20s %-20s %-25s %-20s %-10d \n", "", "","","", UserInterface.PrintOnly(product.getName(), MaxProductsPrintLength), product.getQuantity());
                }
            }
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");}
            
        }
        System.out.println();
        System.out.println("--------------------------------------------------------------------------------------------------------------------------Order In the Locker's Address");
        System.out.println();
        System.out.printf("%-10s %-20s %-20s %-25s %-20s %-10s %-10s %-10s\n", "Order ID", "CustomerName","DriversName","Address", "ProductName", "Quantity","Status","Rating");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");;

    for (Orders order : AllOrdersList) {
        if (order instanceof OrdersLocker) {
            OrdersLocker orderslocker = (OrdersLocker) order;
            Integer orderId = orderslocker.getOrderId();
            String CostumerFullName = orderslocker.getCostumerFirstName() + " " + orderslocker.GetCostumerLastName();
            List<ProductsInBucket> productsInOrder = orderslocker.getProductsInOrder();
            String Address = orderslocker.getAddress();
            Integer CompartmentNumber = orderslocker.getCompartmentNumber();
            String DriverFullName = orderslocker.getDriverFirstName() + " " + orderslocker.getDriverSurrname();
            String Status = orderslocker.getStatus();
            Integer rating = orderslocker.getRating();
            String Rating;
            if (rating == null&& orderslocker.getStatus().equals(Constants.COMPLETED)) {
                Rating = "Not Rated yet!";
                }else if (rating == null&& orderslocker.getStatus().equals(Constants.PENDING)){
                    Rating = "Order Not finished";
                }
                 else {
                Rating = String.valueOf(rating);
                }
            // Print customer details first
            if (!productsInOrder.isEmpty()) {
                ProductsInBucket firstProduct = productsInOrder.get(0);
                
                System.out.printf("%-10d %-20s %-20s %-25s %-20s %-10d %-10s %-10s\n", orderId, CostumerFullName,DriverFullName,"locker: " + Address,UserInterface.PrintOnly(firstProduct.getName(), MaxProductsPrintLength), firstProduct.getQuantity(),Status,Rating);
                System.out.printf("%-10s %-20s %-20s %-25s ", "","","","Compartment: " + CompartmentNumber);
                // Print remaining products
                for (int i = 1; i < productsInOrder.size(); i++) {
                    ProductsInBucket product = productsInOrder.get(i);
                    System.out.printf("%-20s %-10d\n", UserInterface.PrintOnly(product.getName(), MaxProductsPrintLength), product.getQuantity());
                }
            }
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");}
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

        //"First Order"
        Products product = ProductManager.GetProductById(1);
        CostumerManager.GetCurrentCostumerByFullName("Maria Papadopoyloy").addProductToBucket(product, 2);
        OrdersHome NewOrderHome = new OrdersHome(CostumerManager.GetCurrentCostumerByFullName("Maria Papadopoyloy"), 
        DriverManager.GetCurrentDriverByFullName("Renato Nake"));
        



        
        //"Second Order"
        for(int i =2;i <=3;i++){ //getting 2 products, id:1 and id:2, and adding quantity by 2
            Products productSecond = ProductManager.GetProductById(i);
            CostumerManager.GetCurrentCostumerByFullName("Izabel Georgioy").addProductToBucket(productSecond, 1);
        }
        OrdersHome NewOrderHomeSecond = new OrdersHome(CostumerManager.GetCurrentCostumerByFullName("Izabel Georgioy"),
        DriverManager.GetCurrentDriverByFullName("Anthoni Mantellos"));
        NewOrderHome.setStatusCompleted();
        NewOrderHomeSecond.setStatusCompleted();
        NewOrderHome.setRating(10);
        AllOrdersList.add(NewOrderHomeSecond); //adding them to the list
        AllOrdersList.add(NewOrderHome);


        //Creating LockerOrders
        Drivers SelectedDriverThird = DriverManager.GetCurrentDriverByFullName("Renato Nake"); 
        Costumers SelectedCostumerThird = CostumerManager.GetCurrentCostumerByFullName("Maria Papadopoyloy");
        for(int i =1;i <=2;i++){ //getting 2 products, id:1 and id:2, and adding quantity by 2

            Products productThird = ProductManager.GetProductById(i);
            SelectedCostumerThird.addProductToBucket(productThird, 2);
        }
        Lockers RandomLocker = LockerManager.getRandomFreeLocker();
        EachCompartmentOfLockers RandomCompartment = LockerManager.getRandomCompartmentOfLocker(RandomLocker);
        OrdersLocker OrdersLockerFirst = new OrdersLocker(SelectedCostumerThird, SelectedDriverThird,RandomLocker,RandomCompartment);

        
        Drivers SelectedDriverForth = DriverManager.GetCurrentDriverByFullName("Anthoni Mantellos"); 
        Costumers SelectedCostumerForth = CostumerManager.GetCurrentCostumerByFullName("Izabel Georgioy");
        for(int i =2;i <=3;i++){ //getting 2 products, id:1 and id:2, and adding quantity by 2

            Products productForth = ProductManager.GetProductById(i);
            SelectedCostumerForth.addProductToBucket(productForth, 1);
        }
        Lockers RandomLockerSecond = LockerManager.getRandomFreeLocker();
        EachCompartmentOfLockers RandomCompartmentSecond = LockerManager.getRandomCompartmentOfLocker(RandomLocker);
        OrdersLocker OrdersLockerSecond = new OrdersLocker(SelectedCostumerForth, SelectedDriverForth,RandomLockerSecond,RandomCompartmentSecond);
        AllOrdersList.add(OrdersLockerFirst);
        AllOrdersList.add(OrdersLockerSecond);
        OrdersLockerSecond.setStatusCompleted();
        OrdersLockerSecond.setCompartmentAvailable();
        OrdersLockerSecond.LockerAddSpace();
        OrdersLockerSecond.setRating(5);



    }

    public static void ChangeDriver(){
        //First We need to make sure there are Pending Order
        //We need to make sure that there are Drivers that do the type of delivery
        //Choosing the same driver for the Change of DriverOrder is unecessary, so it will not be allowed
        Orders SelectedOrder=null;
        System.out.println("Showing Only Pending Orders that can be changed"); //There is no need to show all orders, in case there are 100 orders or example it would become annoying
        System.out.println();
        boolean PendingOrders=false;
        for(Orders order: AllOrdersList){
            if(order.getStatus().equals(Constants.PENDING)){
                PrintOrder(order); //printing the pending order
                PendingOrders=true;
            }
        }


        while (PendingOrders) {
            SelectedOrder = SelectOrder();
            if(SelectedOrder.getStatus().equals(Constants.PENDING)){  // we select and order from above
                break;
            }
            else{
                System.out.println("The Selected Order has been completed, Please only select from the Pending Orders above"); // in case the user uses an id of an order that has been completed, even tho is not shown above
            }
        }


        String TypeOrder;

        if(SelectedOrder instanceof OrdersHome){
            TypeOrder=Constants.DELIVERYHOME;
        }else{
            TypeOrder=Constants.LOCKERDELIVERY;
        } // we get the order type, we could have used a getter from the order, but i think this way is better

        
        boolean loopCompleted = false; // in case there are no drivers with The OrderType Selected // we print in the end that the there are no avaiable drivers to select
        Integer ShowDriverCounter=1;
        while (PendingOrders && (DriverManager.getCounterByType(TypeOrder)>1)) {
            if(ShowDriverCounter==1){
                System.out.println("----------------------------------------------------------------------------------------------------------------------------------Showing Only Drivers That can do "+TypeOrder);
                System.out.printf("%-5s %-17s %-20s %-25s %-35s %-10s %-20s %-20s \n", "ID","Name", "lastName", "Adress", "Email", "AFM","Plate Number","Orders Type");
                System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                for(Drivers drivers:DriverManager.getDriverList()){
                    if((drivers.getType().equals(TypeOrder)|| drivers.getType().equals(Constants.HOMEANDLOCKER))&& (!SelectedOrder.getDriverFullName().equals(drivers.getDriverFullName()))){
                        System.out.printf("%-5d %-17s %-20s %-25s %-35s %-10d %-20s %-20s \n",drivers.getDriverid(),drivers.getName(),drivers.getSurname(),drivers.getAdress(),drivers.getEmail(),drivers.getDriverAFM(),drivers.getPlateNumber(),drivers.getType());
                    }
                }
                System.out.println("Select Driver, Look The Abode drivers For reference.\n");
                System.out.println();
                ShowDriverCounter++;
            }


            String DriverName = UserInterface.InputTypeStringWithSpace("Type the Drivers Full name :");
            if(DriverManager.CheckDriverExists(DriverName)){
                Drivers driver = DriverManager.GetCurrentDriverByFullName(DriverName);
                if((driver.getType().equals(TypeOrder)|| driver.getType().equals(Constants.HOMEANDLOCKER))&& (!SelectedOrder.getDriverFullName().equals(driver.getDriverFullName()))){
                    SelectedOrder.setNewDriver(driver);
                    PrintOrder(SelectedOrder);
                    System.out.println();
                    System.out.println("The Driver Has Been Changed");
                    loopCompleted=true;
                    break;
                }else if ((SelectedOrder.getDriverFullName().equals(driver.getDriverFullName()))){
                    System.out.println("You gave the name of the Current Driver, Select another one");
                }else{
                    System.out.println("The Driver cannot be selected he does "+ TypeOrder);
                }
            }else{
                System.out.println("That Driver Does Not Exist");
            }
            
        }

        if(!PendingOrders){
            System.out.println("All Orders have been completed");
        }else if (!loopCompleted){
            System.out.println("There are no drivers avaiable that can do "+ TypeOrder + " Except the current one");
            System.out.println("Returning to Menu");
        }
        
    }


    public static Orders SelectOrder(){
        Orders SelectedOrder=null;
        boolean ValidOrder =false;
        Integer OrderId;


        while (true) {
            OrderId = UserInterface.InputTypeIntegerNoLimit("Please type the number id of the order:");
            if(OrderId<=AllOrdersList.size()){
                ValidOrder =true;
                break;
            }else{
                System.out.println("No such Order Exists");
            }
        }
        if(ValidOrder){
            for(Orders order : AllOrdersList){
                if(order.getOrderId()==OrderId){
                    SelectedOrder =order;
                }
            }
        }

        return SelectedOrder;
    
}

    public static void ChangeOrdersAddress(){
        boolean isvalid = false;
        OrdersHome ordersHome=null;
        String NewAddress=null;
        boolean TryAgain = true;
        while (TryAgain) {
        Orders order = SelectOrder();
        if (order instanceof OrdersHome) {
                ordersHome = (OrdersHome) order;
                NewAddress = UserInterface.InputTypeAdress("Please Type in The new address:");
                isvalid = true;
                break;}
        else{
                System.out.println("The order you selected is a Locker Order, the address cannot be changed, sorry");
                TryAgain = UserInterface.InputTypeBoolean("Try Again? type (y/yes/Y/YES) for yes or (N/n/no/NO) for no");
                

                
            }
        }
        if(isvalid){
            ordersHome.setAddress(NewAddress);
            System.out.println("The Order's Address has been changed: ");
            PrintOrder(ordersHome);
        }
    }



    public static void CompleteOrder(){
        Orders order = SelectOrder();
        if(order instanceof OrdersHome ){
            OrdersHome ordersHome = (OrdersHome) order;
            String status = ordersHome.getStatus();
            if(status.equals(Constants.COMPLETED)){
                System.out.println("The order has already been completed");
            }else{
                ordersHome.setStatusCompleted();
                System.out.println("The Order Has been Completed!");
                PrintOrder(order);
            }

        }else{
            OrdersLocker OrdersLocker = (OrdersLocker) order;
            String status = OrdersLocker.getStatus();
            if(status.equals(Constants.COMPLETED)){
                System.out.println("The order has already been completed");
            }else{
                OrdersLocker.setStatusCompleted();
                OrdersLocker.setCompartmentAvailable();
                OrdersLocker.LockerAddSpace();
                System.out.println("The Order Has been Completed!");
                PrintOrder(order);
            }

        }

        

    }



    public static void PrintOrder(Orders order){
            if (order instanceof OrdersHome) {
                System.out.println("-----------------------------------------------------------------------------------------------------------------------Order In the costumer's Address");
                System.out.println();
                System.out.printf("%-10s %-20s %-20s %-25s %-20s  %-10s %-10s %-10s\n", "Order ID", "CustomerName","DriversName","Address", "ProductName", "Quantity","Status","Rating");
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");
                OrdersHome SelectedOrder = (OrdersHome) order;
                Integer orderId = SelectedOrder.getOrderId();
                String CostumerFullName = SelectedOrder.getCostumerFirstName() + " " + SelectedOrder.GetCostumerLastName();
                String DriverFullName = SelectedOrder.getDriverFirstName() + " " + SelectedOrder.getDriverSurrname();
                List<ProductsInBucket> productsInOrder = SelectedOrder.getProductsInOrder();
                String Address = SelectedOrder.getAddress();
                String Status = SelectedOrder.getStatus();
                Integer rating = SelectedOrder.getRating();
                String Rating;
                if (rating == null&& SelectedOrder.getStatus().equals(Constants.COMPLETED)) {
                    Rating = "Not Rated yet!";
                    }else if (rating == null&& SelectedOrder.getStatus().equals(Constants.PENDING)){
                        Rating = "Order Not finished";
                    }
                     else {
                    Rating = String.valueOf(rating);
                    }
                //customer details first
                if (!productsInOrder.isEmpty()) {
                    ProductsInBucket firstProduct = productsInOrder.get(0);
                    
                    System.out.printf("%-10d %-20s %-20s %-25s %-20s %-10d %-10s %-10s\n", orderId, CostumerFullName,DriverFullName,Address,UserInterface.PrintOnly(firstProduct.getName(), MaxProductsPrintLength), firstProduct.getQuantity(),Status,Rating);
    
                    // remaining products
                    for (int i = 1; i < productsInOrder.size(); i++) {
                        ProductsInBucket product = productsInOrder.get(i);
                        System.out.printf("%-10s %-20s %-20s %-25s %-20s %-10d\n", "", "","","", UserInterface.PrintOnly(product.getName(), MaxProductsPrintLength), product.getQuantity());
                    }
                }
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");}
            else{
                System.out.println("--------------------------------------------------------------------------------------------------------------------------Order In the Locker's Address");
                System.out.println();
                System.out.printf("%-10s %-20s %-20s %-25s %-20s %-10s %-10s %-10s\n", "Order ID", "CustomerName","DriversName","Address", "ProductName", "Quantity","Status","Rating");
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");
                OrdersLocker orderslocker = (OrdersLocker) order;
                Integer orderId = orderslocker.getOrderId();
                String CostumerFullName = orderslocker.getCostumerFirstName() + " " + orderslocker.GetCostumerLastName();
                List<ProductsInBucket> productsInOrder = orderslocker.getProductsInOrder();
                String Address = orderslocker.getAddress();
                Integer CompartmentNumber = orderslocker.getCompartmentNumber();
                String DriverFullName = orderslocker.getDriverFirstName() + " " + orderslocker.getDriverSurrname();
                String Status = orderslocker.getStatus();
                Integer rating = orderslocker.getRating();
                String Rating;
                if (rating == null&& orderslocker.getStatus().equals(Constants.COMPLETED)) {
                Rating = "Not Rated yet!";
                }else if (rating == null&& orderslocker.getStatus().equals(Constants.PENDING)){
                    Rating = "Order Not finished";
                }
                 else {
                Rating = String.valueOf(rating);
                }
                // Print customer details first
                if (!productsInOrder.isEmpty()) {
                    ProductsInBucket firstProduct = productsInOrder.get(0);
                    
                    System.out.printf("%-10d %-20s %-20s %-25s %-20s %-10d %-10s %-10s\n", orderId, CostumerFullName,DriverFullName,"locker: " + Address,UserInterface.PrintOnly(firstProduct.getName(), MaxProductsPrintLength), firstProduct.getQuantity(),Status,Rating);
                    System.out.printf("%-10s %-20s %-20s %-25s ", "","","","Compartment: " + CompartmentNumber);
                    // Print remaining products
                    for (int i = 1; i < productsInOrder.size(); i++) {
                        ProductsInBucket product = productsInOrder.get(i);
                        System.out.printf("%-20s %-10d\n", UserInterface.PrintOnly(product.getName(), MaxProductsPrintLength), product.getQuantity());
                    }
                }
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");}

        }

        public static void LeaveReview(){
            Orders order = SelectOrder();
            System.out.println("Rate between 1 and 10: ");
            if(order.getStatus().equals(Constants.COMPLETED) && order.getRating()==null){
                Integer RatingNumb = UserInterface.SelectNumber(10);
                order.setRating(RatingNumb);
                System.out.println("Thank you for rating our service");
                PrintOrder(order);
                
            }else if(order.getStatus().equals(Constants.COMPLETED) && order.getRating()!=null){
                System.out.println("The Order Has already been rated, do you wish to change the rating?");
                boolean ChangeRating = UserInterface.InputTypeBoolean("Type Yes/y/yes/YES for yes and N/no/NO/No for no");
                if(ChangeRating){
                    Integer RatingNumb = UserInterface.SelectNumber(10);
                    order.setRating(RatingNumb);
                    System.out.println("The rating of the order was succesfully changed");
                    PrintOrder(order);
                }
            }else{
                System.out.println("The order is still Pending, cannot Rate the order now");
            }


        }

    
public static void ShowAverageReviews() {
    double avg = 0.0;
    List<Orders> ratedOrders = new ArrayList<>();
    Set<String> customerNames = new HashSet<>(); // so we dont get duplicates we wil use set, source : https://docs.oracle.com/javase%2F8%2Fdocs%2Fapi%2F%2F/java/util/Set.html
    List<Integer> customerHighestR = new ArrayList<>();
    List<Integer> customerLowestR = new ArrayList<>();

    // we get the 
    for (Orders order : AllOrdersList) {
        if (order.getStatus().equals(Constants.COMPLETED) && order.getRating() != null) {
            ratedOrders.add(order);
            customerNames.add(order.getCostumerFullName()); 
        }
    }

    // Calculate highest and lowest rating for each customer
    for (String customerName : customerNames) {
        int highest = -1; // the rating cant be lower than 1 or bigger than 10, so we use this values 
        int lowest = 11;
        for (Orders order : ratedOrders) {
            if (order.getCostumerFullName().equals(customerName)) {
                int rating = order.getRating();
                if (rating > highest) {
                    highest = rating;
                }
                if (rating < lowest) {
                    lowest = rating;
                }
            }
        }
        customerHighestR.add(highest);
        customerLowestR.add(lowest);
    }

    // Getting the average
    if (!ratedOrders.isEmpty()) {
        for (Orders order : ratedOrders) {
            avg += order.getRating();
        }
        avg /= ratedOrders.size();
    }

    //print results
    int index = 0;
    for (String customerName : customerNames) {
        System.out.printf("%s with Highest Rating of %d and Lowest Rating of %d%n", customerName, customerHighestR.get(index), customerLowestR.get(index));
        index++;
    }

    //print average
    System.out.printf("Average Rating: %.2f%n", avg);
    //we clear the arrays
    ratedOrders.clear();
    customerNames.clear();
    customerHighestR.clear();
    customerLowestR.clear();


}

public static void ProductsBoughtSummary() {
    List<ProductsInBucket> productsBought = new ArrayList<>(); // A list to store all the elements of all products bought
    Set<String> barcodesPerCostumer = new HashSet<>();
    Set<String> barcodesALL = new HashSet<>();
    // Collect all products bought from all orders
    System.out.printf("%-30s %-30s %-30s %-30s \n","Costumer's Name","Costumer's Total Orders","Products Bought By Barcode","Quantity");
    for (Orders order : AllOrdersList) {
        List<ProductsInBucket> itemsBought = order.getProductsInOrder();
        productsBought.addAll(itemsBought);
    }


    barcodesALL.clear();
    for(Costumers costumer:CostumerManager.getCostumersList()){
        int totalOrderQuantity = 0;
        barcodesPerCostumer.clear();
        String CurrentCostumer=costumer.getCostumersFullName();
        Integer CurrentCostumerOrdersCount = CostumerManager.getCostumersTotalOrders(costumer.getId());


        
        for(ProductsInBucket item:productsBought){

            //we store all the barcodes to the set(all of them)
            barcodesALL.add(String.valueOf(item.getBarcode()));

            //we store the costumers barcode to the set
            if(costumer.getCostumersFullName().equals(item.getCostumersFullName())){
                barcodesPerCostumer.add(String.valueOf(item.getBarcode()));
            }
        }




        System.out.println("--------------------------------------------------------------------------------------------------------------");
        //for every barcode we will get its quantity bought from the costumer
        boolean FirstLoop=true;
        for(String barcodes:barcodesPerCostumer ){
            Integer Quantity=0;
            for(ProductsInBucket product:productsBought){
                //if the user has bought the product 
                if(costumer.getCostumersFullName().equals(product.getCostumersFullName()) && barcodes.equals(String.valueOf(product.getBarcode()))){
                    Quantity+=product.getQuantity();
                    totalOrderQuantity+=product.getQuantity();
                }
            }

            if(FirstLoop){
                System.out.printf("%-30s %-30d %-30s %-30d \n",CurrentCostumer,CurrentCostumerOrdersCount,barcodes,Quantity);
                FirstLoop=false;
            }
            else{
                System.out.printf("%-30s %-30s %-30s %-30d \n","","",barcodes,Quantity);
            }

        }
        System.out.println("--------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-30s %-30s %-30s %-30d \n","Totals of Costumer","--------","----------",totalOrderQuantity);
        System.out.println();
        System.out.println();
        System.out.println();
        //System.out.printf(" %-30d\n ",totalOrderQuantity);
    }

    System.out.println();
    System.out.println();
    Integer TotalCounter=0;
    System.out.printf("---------------------------------------------------------------------------------------------All Orders Totals\n");
    System.out.printf(" %-30s %-30s %-30s\n","Total Orders","Barcodes","Quantity of each");
    System.out.println("--------------------------------------------------------------------------------------------------------------");
    boolean FirstLoop=true;
    for(String barcodes:barcodesALL ){
    Integer Counter=0;
    for(ProductsInBucket product:productsBought){
        if(barcodes.equals(String.valueOf(product.getBarcode()))){
            Counter+=product.getQuantity();
            TotalCounter+=product.getQuantity();
        }
    }
    if(FirstLoop){
        System.out.printf(" %-30d %-30s %-30d \n",AllOrdersList.size(),barcodes,Counter);
        FirstLoop=false;
    }else{
        System.out.printf(" %-30s %-30s %-30d \n","",barcodes,Counter);
    }
    }
    System.out.println("--------------------------------------------------------------------------------------------------------------");
    System.out.printf(" %-30s %-30s %-30d \n","Total Products Bought","",TotalCounter);
    System.out.println();
    System.out.println();
  
}

public static void ShowDriverOrdes() {
    //HEADER OF THE PRINT
    System.out.println();
    System.out.printf(" %-25s %-25s %-25s %-25s %-25s %-25s %-25s | %-15s\n","Driver's ID","DriverCategory",
    "Driver's Name","Pending Home Orders","Completed Home Orders",
    "Pending Lockers Orders","Completed Lockers Orders",
    "Total Per Driver");
    System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

    Integer AllOrderCount =0;
    Integer PendingHomeOrdersTotal = 0;
    Integer CompletedHomeOrdersTotal =0;
    Integer PendingLockersOrdersTotal =0 ;
    Integer CompletedLockersOrdersTotal=0;

    for (Drivers driver : DriverManager.getDriverList()) {
        Integer PendingHomeOrders = 0;
        Integer CompletedHomeOrders = 0;
        Integer PendingLockersOrders = 0;
        Integer CompletedLockersOrders = 0;
        Integer TotalperDriver=0;
        for (Orders orders : AllOrdersList) {
            if (orders.getDriverFullName().equals(driver.getDriverFullName())) {
                if (orders.getStatus().equals(Constants.PENDING)) {
                    if (orders instanceof OrdersHome) {
                        PendingHomeOrders++;
                    } else if (orders instanceof OrdersLocker) {
                        PendingLockersOrders++;
                    }
                } else if (orders.getStatus().equals(Constants.COMPLETED)) {
                    if (orders instanceof OrdersHome) {
                        CompletedHomeOrders++;
                    } else if (orders instanceof OrdersLocker) {
                        CompletedLockersOrders++;
                    }
                }
            }
        }
        PendingHomeOrdersTotal+=PendingHomeOrders;
        CompletedHomeOrdersTotal+=CompletedHomeOrders;
        PendingLockersOrdersTotal+=PendingLockersOrders;
        CompletedLockersOrdersTotal+=CompletedLockersOrders;
        TotalperDriver=PendingHomeOrders+CompletedHomeOrders+PendingLockersOrders+CompletedLockersOrders;
        AllOrderCount+=TotalperDriver;
        //i will not show drivers that have no orders,(my though processis that if there are for example 100 drivers, there is no need to show the ones without orders)
        if(TotalperDriver>0){
            System.out.printf(" %-25d %-25s %-25s %-25d %-25d %-25d %-25d | %-15d\n",driver.getDriverid(),driver.getType(), driver.getDriverFullName(),  PendingHomeOrders,
            CompletedHomeOrders,
            PendingLockersOrders,
            CompletedLockersOrders,TotalperDriver);
        }
    }
    if(AllOrderCount>0){
        System.out.println();
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf(" %-25s %-25s %-25s %-25d %-25d %-25d %-25d | %-25d\n","Total Of All Orders:","--------","--------",PendingHomeOrdersTotal,CompletedHomeOrdersTotal,
        PendingLockersOrdersTotal,CompletedLockersOrdersTotal,AllOrderCount);
        System.out.println();
    }else{
        System.out.println("There are no Orders Places yet");
    }

}
    

}


    