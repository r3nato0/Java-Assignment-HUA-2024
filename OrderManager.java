import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.Iterator;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class OrderManager{
    private static List<Orders> AllOrdersList = new ArrayList<>();
    private final static Integer MaxProductsPrintLength = 15; // Just a Limit of characters from products name to get printed, will be passed to UserInterface.PrintOnly(Name,Max...)
    //Καταλαθος κατάλαβα οτι εργασία ζητούσε και την ποσότητα για κάθε προιον σε κάθε παραγελία, έτσι καθε προιόν εχει και μια διαθέσιμη ποσοτητα στο πρόγραμα
    //Επισης την εργασία την έγραψα σε visual studio code
    //και μετα την εβαλα στο netbeans, συγνώμη αμα υπαρξει κάποιο λάθος σε κατα την μετατροπή


    public static List<Orders> GetAllOrdersList(){
        return AllOrdersList;
    }


   public static void CreateSecond() {
        // i named it CreateSecond, since is my second varient of this method, in this varient i will ask the user first to pick the type of order he wants
        List<String> ActionRecorder=null; // will store the return values from the NotFoundAddNew, and we check the values after and act accordingly.
        String HomeOrLocker = UserInterface.InputTypeLockerORHome("Press : 1) For Home delivery\nPress : 2) For Lockers Delivery");
        Drivers SelectedDriver=null;
        String DriverFullName;
        // we get in the loop bellow, getting the name of the Driver
        while (true) {
            DriverFullName = UserInterface.InputTypeStringWithSpace("Please Provide the full name of the driver (upper cases will be ignored)  (example: FirstName Lastname): ");
            boolean DriverOld=DriverManager.CheckDriverExists(DriverFullName); // return true or false
            if (DriverOld){ // if the driver exists,
                if(DriverManager.GetCurrentDriverByFullName(DriverFullName).getType().equalsIgnoreCase(HomeOrLocker)){ // we check if he also does the same order type the user selected
                    System.out.println("The Driver allready Exsits. The rest of the fields are filled accordingly ");
                    System.out.println("The Selected Driver also does "+HomeOrLocker);
                    SelectedDriver = DriverManager.GetCurrentDriverByFullName(DriverFullName); // in that case we get the driver instance selected
                    
                    break;//then we break from the loop
                }else{
                    // the driver does not do the type of orders the user selected so he will be prompted to enter another Driver, he can still create a new one or select another one
                    System.out.println("Driver Exists but does not do the type of delivery you selected");
                    System.out.println("Select Another "); 
                }
    
            }
            else{ // in case the driver does not exists we create the driver based on the provided Name
                System.out.println("The Driver does not exits, Creating new Entry");
                ActionRecorder = DriverManager.NotFoundAddNew(DriverFullName,HomeOrLocker); // Other Fillds are prompted and filled via the NotFoundAddNew Method
                break;
            }            
        }
        // if the list is not null, meaning the above NotFoundAddNew method was launched, we get the values from the list
        if(ActionRecorder!=null){
            if((ActionRecorder.size()==2)){ // if there are 2 values returned, then a specific part of the NotFoundAddNew was run, 
                //meaning the user selected to change the ordertype,so we get the changed value
                HomeOrLocker = ActionRecorder.get(1); // we store it in the same varriable as the first input so at the of this method we create the order accordingly 
                DriverFullName = ActionRecorder.get(0);// also we get that new drivers name
            }else{
                System.out.println(ActionRecorder.get(0)); // if the ActionRecorder lenght is not 2 but also is not null(first statement) then only the new driversname exusts unside 
                DriverFullName = ActionRecorder.get(0);
                
            }
            // we get the new driver/old driver 
            SelectedDriver = DriverManager.GetCurrentDriverByFullName(DriverFullName);
        }


        

        //Costumer Selection Part
        Costumers SelectedCostumer;
        String CostumerFullName =UserInterface.InputTypeStringWithSpace("Please Provide the full name of the Costumer (upper cases will be ignored)  (example: FirstName Lastname): ");
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
            
            boolean addAnotherProduct = UserInterface.InputTypeBoolean("Add another product to the bucket; yes/YES/y No/NO/n ");

            if (!addAnotherProduct) {
                break;//user want no other products, we continue
            }
        }


        // a Case where the first product the costumer wants has 0 quantity, but he also does not wish any other product to buy
        if (SelectedCostumer.getProductsInBucket().size()==0){
            System.out.println("The Costumer does not want any product, the order has been canceled,returning to Main Menu");
            return;
        }


        //Delivery to Home
        if(HomeOrLocker.equalsIgnoreCase(Constants.DELIVERYHOME)){
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
                //since the order is canceled, we need to clear the costumer's bucket. so in the next order he does not have the orders in the bucket.
                //but we also need to set the products quantity back to the previous values, before the order started taking place.
                //this values are stored in the costumer's backet
                for(ProductsInBucket product :SelectedCostumer.getProductsInBucket()){
                    for(Products productsInStock:ProductManager.getProductsList()){
                        if(product.getProductid().equals(productsInStock.getId())){
                            productsInStock.addAvailableQuantity(product.getQuantity());
                        }
                    }
                }
                SelectedCostumer.clearBucket();
            }}


}


public static void printAllOrders() {
    // header
    System.out.println("-----------------------------------------------------------------------------------------------------------------------Order In the costumer's Address");
    System.out.println();
    System.out.printf("%-10s %-20s %-20s %-25s %-20s %-10s %-10s %-10s\n", "Order ID", "CustomerName","DriversName","Address", "ProductName", "Quantity","Status","Rating");
    System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");
    if(AllOrdersList.isEmpty()){ // in any case that there are no order, jsut adding it for best practise, since we will always start the program with default orders
        System.out.println("There are no orders placed yet!");
        return;
    }
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
            if (rating == null&& ordersHome.getStatus().equalsIgnoreCase(Constants.COMPLETED)) {
                Rating = "Not Rated yet!";
                }else if (rating == null&& ordersHome.getStatus().equalsIgnoreCase(Constants.PENDING)){
                    Rating = "Order Not finished";
                }
                 else {
                Rating = String.valueOf(rating);
                }
            //customer details first
            if (!productsInOrder.isEmpty()) {
                ProductsInBucket firstProduct = productsInOrder.get(0);
                
                System.out.printf("%-10d %-20s %-20s %-25s %-20s %-10d %-10s %-10s \n", orderId, CostumerFullName,DriverFullName,Address,UserInterface.PrintOnly(firstProduct.getName(), MaxProductsPrintLength), firstProduct.getQuantity(),Status,Rating);

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
            if (rating == null&& orderslocker.getStatus().equalsIgnoreCase(Constants.COMPLETED)) {
                Rating = "Not Rated yet!";
                }else if (rating == null&& orderslocker.getStatus().equalsIgnoreCase(Constants.PENDING)){
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


    public static String CurrentDateTime(){
        // a simple method that will get the current datetime the instance of the order is created
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm"); // we modify the pattern 
        LocalDateTime now = LocalDateTime.now();
        String formattedDateTime = now.format(formatter);
        return formattedDateTime; // we return it as a string
    }
 


    public static void CreateDefaultOrders(){

        //Creating HomeOrders

        //"First Order"
        Products product = ProductManager.GetProductById(1);
        CostumerManager.GetCurrentCostumerByFullName("maria papadopoyloy").addProductToBucket(product, 2);
        OrdersHome NewOrderHome = new OrdersHome(CostumerManager.GetCurrentCostumerByFullName("maria papadopoyloy"), 
        DriverManager.GetCurrentDriverByFullName("renato nake"));
        
        //"First Order"
        Products productother = ProductManager.GetProductById(1);
        CostumerManager.GetCurrentCostumerByFullName("dimitris latsi").addProductToBucket(productother, 1);
        OrdersHome NewOrderHomeother = new OrdersHome(CostumerManager.GetCurrentCostumerByFullName("dimitris latsi"), 
        DriverManager.GetCurrentDriverByFullName("nikos alekou"));
        AllOrdersList.add(NewOrderHomeother);

        
        //"Second Order"
        for(int i =2;i <=3;i++){ //getting 2 products, id:1 and id:2, and adding quantity by 2
            Products productSecond = ProductManager.GetProductById(i);
            CostumerManager.GetCurrentCostumerByFullName("izabel georgioy").addProductToBucket(productSecond, 1);
        }
        OrdersHome NewOrderHomeSecond = new OrdersHome(CostumerManager.GetCurrentCostumerByFullName("izabel georgioy"),
        DriverManager.GetCurrentDriverByFullName("anthoni mantellos"));
        NewOrderHome.setStatusCompleted();
        NewOrderHomeSecond.setStatusCompleted();
        NewOrderHome.setRating(10);
        AllOrdersList.add(NewOrderHomeSecond); //adding them to the list
        AllOrdersList.add(NewOrderHome);


        //Creating LockerOrders
        Drivers SelectedDriverThird = DriverManager.GetCurrentDriverByFullName("anthoni mantellos"); 
        Costumers SelectedCostumerThird = CostumerManager.GetCurrentCostumerByFullName("giannhs ntalas");
        for(int i =1;i <=2;i++){ //getting 2 products, id:1 and id:2, and adding quantity by 2

            Products productThird = ProductManager.GetProductById(i);
            SelectedCostumerThird.addProductToBucket(productThird, 2);
        }
        Lockers RandomLocker = LockerManager.getRandomFreeLocker();
        if(RandomLocker!=null){
            EachCompartmentOfLockers RandomCompartment = LockerManager.getRandomCompartmentOfLocker(RandomLocker);
            OrdersLocker OrdersLockerFirst = new OrdersLocker(SelectedCostumerThird, SelectedDriverThird,RandomLocker,RandomCompartment);
            AllOrdersList.add(OrdersLockerFirst);
        }else{
            System.out.println("Error Creating Default Locker Order 1/2, no free Lockers avaiable!");
        }


        
        Drivers SelectedDriverForth = DriverManager.GetCurrentDriverByFullName("sofia alekou"); 
        Costumers SelectedCostumerForth = CostumerManager.GetCurrentCostumerByFullName("dimitris latsi");
        for(int i =2;i <=3;i++){ //getting 2 products, id:1 and id:2, and adding quantity by 2

            Products productForth = ProductManager.GetProductById(i);
            SelectedCostumerForth.addProductToBucket(productForth, 1);
        }


        Lockers RandomLockerSecond = LockerManager.getRandomFreeLocker();
        if(RandomLockerSecond!=null){
            EachCompartmentOfLockers RandomCompartmentSecond = LockerManager.getRandomCompartmentOfLocker(RandomLockerSecond);
            OrdersLocker OrdersLockerSecond = new OrdersLocker(SelectedCostumerForth, SelectedDriverForth,RandomLockerSecond,RandomCompartmentSecond);
            AllOrdersList.add(OrdersLockerSecond);
            OrdersLockerSecond.setStatusCompleted();
            OrdersLockerSecond.setCompartmentAvailable();
            OrdersLockerSecond.LockerAddSpace();
            OrdersLockerSecond.setRating(5);
        }else{
            System.out.println("Error Creating Default Locker Order 2/2, no free Lockers avaiable!");
        }
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
            if(order.getStatus().equalsIgnoreCase(Constants.PENDING)){
                PrintOrder(order); //printing the pending order
                PendingOrders=true;
            }
        }


        while (PendingOrders) {
            SelectedOrder = SelectOrder(false);
            if(SelectedOrder.getStatus().equalsIgnoreCase(Constants.PENDING)){  // we select and order from above
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
                    if((drivers.getType().equalsIgnoreCase(TypeOrder)|| drivers.getType().equalsIgnoreCase(Constants.HOMEANDLOCKER))&& (!SelectedOrder.getDriverFullName().equalsIgnoreCase(drivers.getFullname()))){
                        System.out.printf("%-5d %-17s %-20s %-25s %-35s %-10d %-20s %-20s \n",drivers.getDriverid(),drivers.getName(),drivers.getSurname(),drivers.getAddress(),drivers.getEmail(),drivers.getDriverAFM(),drivers.getPlateNumber(),drivers.getType());
                    }
                }
                System.out.println("Select Driver, Look The Abode drivers For reference.\n");
                System.out.println();
                ShowDriverCounter++;
            }


            String DriverName = UserInterface.InputTypeStringWithSpace("Type the Drivers Full name :");
            if(DriverManager.CheckDriverExists(DriverName)){
                Drivers driver = DriverManager.GetCurrentDriverByFullName(DriverName);
                if((driver.getType().equalsIgnoreCase(TypeOrder)|| driver.getType().equalsIgnoreCase(Constants.HOMEANDLOCKER))&& (!SelectedOrder.getDriverFullName().equalsIgnoreCase(driver.getFullname()))){
                    SelectedOrder.setNewDriver(driver);
                    PrintOrder(SelectedOrder);
                    System.out.println();
                    System.out.println("The Driver Has Been Changed");
                    loopCompleted=true;
                    break;
                }else if ((SelectedOrder.getDriverFullName().equalsIgnoreCase(driver.getFullname()))){
                    System.out.println("You gave the name of the Current Driver, Select another one");
                }else{
                    System.out.println("The Driver cannot be selected he does "+ driver.getType());
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

    // will select an order by the input of the user
    public static Orders SelectOrder(boolean PrintOrders){
        if(PrintOrders){
            printAllOrders();
        }
        
        Orders SelectedOrder=null;
        boolean isValid=false;;
        Integer OrderId;
        

        while (true) {
            OrderId = UserInterface.InputTypeNumber("Please type the number id of the order:");// gets a integer from the user, and also check for any input
            for(Orders order : AllOrdersList){// we search the order
                if(order.getOrderId()==OrderId){
                    SelectedOrder =order;
                    isValid=true;// in case it was found
                    break;
                }
            }
            if(isValid){// we return it if was found
                return SelectedOrder;
            }else{//it was not found, prompt user
                System.out.println("That order does not exists: Try again:");
            }
    
}
    }

    public static void ChangeOrdersAddress(){
        printAllOrders(); //printing all orders for reference to the user
        System.out.println("Type id: and then the id of the order ");
        System.out.println("Type name: and then the name of the costumer to search for");

        String CommandId= "id:";
        String CommandName= "name:";
        while (true) {
            Integer parsedProductId=null;
            Orders Selectedorder=null;
            String ProductId=null;
            boolean isvalid = false;
            System.out.println();
            System.out.printf("Type cancel/id:/name: ");
            String Input=UserInterface.scanner.nextLine().trim();

            //id: command
            if(Input.equalsIgnoreCase("cancel")){
                return; //in case the user regreted the action to change driver
            }

            // first we implemend the logic for searching by id
            if(Input.startsWith(CommandId)){ //we get the commmad from the input and parse the substring left from it,
                //if the substring is an integer , we get the order by that integerand break 
                try {
                    ProductId = Input.substring(CommandId.length());
                    parsedProductId = Integer.parseInt(ProductId);
                    
                    for(Orders order:AllOrdersList){
                        if(order.getOrderId()==parsedProductId){
                            Selectedorder=order;
                            isvalid=true;
                            break;
                        }
                    }
                } catch (NumberFormatException e) {
                        System.out.println("Invalid product ID format."); // here, it checks in case the user typed "id:something"
                    }
                }
                    // now if we searched the orders, but the parsedProductId is still null (from the start), order does not exist
                if (!isvalid && parsedProductId!=null) {
                    System.out.println("No product found with the given ID."); //
                    // if the selected order is orderlocker instance but also the order exists
                    }else if(Selectedorder!=null && Selectedorder instanceof OrdersLocker){
                        System.out.println("Cannot Change the the Selected Orders Adress, is a LockersOrder");
                        if(Selectedorder.getStatus().equalsIgnoreCase(Constants.COMPLETED)){
                            System.out.println("The order is Also Completed!");
                        }
                        // in case there is a selected order and is instance of ordershome
                    }else if(Selectedorder!=null && Selectedorder instanceof OrdersHome){
                        OrdersHome homeorder = (OrdersHome) Selectedorder;
                        if(homeorder.getStatus().equalsIgnoreCase(Constants.COMPLETED)){ //we check if its completed, if it we do nothing
                            System.out.println("The order you Selected Has been Completed Cannot Change The Address");
                        }else{ // the order is pending then, and we do then folowing:
                            System.out.printf("Enter New Address: ");
                            String AddressInput=UserInterface.InputTypeAdress("");
                            homeorder.setAddress(AddressInput);
                            System.out.println("The order's Address has been changed Succesfuly");
                            PrintOrder(homeorder);
                            break;
                        }
                    }

            //now the part that the user searched by name:
            else if (Input.startsWith(CommandName)){
                String SelectedCostumerName = Input.substring(CommandName.length());
                List<Orders> CostumerOrders=new ArrayList<>();
                for(Orders order:AllOrdersList){
                    if (order.getCostumerFullName().equalsIgnoreCase(SelectedCostumerName)){
                        CostumerOrders.add(order);
                    }
                }

                //In case the user has one order 
                if(CostumerOrders.size()==1){
                    Selectedorder=CostumerOrders.get(0);

                        if (Selectedorder instanceof OrdersLocker){
                            System.out.println("Cannot change the Address, its a Locker order");
                            if(Selectedorder.getStatus().equalsIgnoreCase(Constants.COMPLETED)){
                                System.out.println("Also the order has been already completed!");
                            }
                        }else{
                            OrdersHome homeorder = (OrdersHome) Selectedorder;
                            System.out.printf("Enter New Address: ");
                            String AddressInput=UserInterface.InputTypeAdress("");
                            homeorder.setAddress(AddressInput);
                            System.out.println("The order's Address has been changed Succesfuly");
                            PrintOrder(homeorder);
                            break;
                        }
                    }else if(CostumerOrders.size()>1){ // if he has more than 1 orders 
                        // We Will remove from the list all the Lockers order and the Completed orders
                        Iterator<Orders> iterator = CostumerOrders.iterator();
                        while (iterator.hasNext()) {
                            Orders order = iterator.next();
                            if (order instanceof OrdersLocker || order.getStatus().equalsIgnoreCase(Constants.COMPLETED)) {
                                iterator.remove();
                            }
                        }

                        //if after that there is only one order, Then thats the one the user wants
                        if(CostumerOrders.size()==1){
                            Selectedorder=CostumerOrders.get(0);
                            OrdersHome homeorder = (OrdersHome) Selectedorder;
                            System.out.printf("Enter New Address: ");
                            String AddressInput=UserInterface.InputTypeAdress("");
                            homeorder.setAddress(AddressInput);
                            System.out.println("The order's Address has been changed Succesfuly");
                            PrintOrder(homeorder);
                            break;

                        //if after the removes the user has more than one order that he can change the address off
                        //we need to ask him to select from all his orders by id 
                        }else if(CostumerOrders.size()>1){
                            for(Orders order:CostumerOrders){
                                PrintOrder(order);
                            }
                            System.out.println("Please Select from the above orders by id, Becouse the user has more than 1 HomeOrders Pending");

                            while (true) {// 
                                boolean found = false;
                                Integer Selection = UserInterface.InputTypeNumber("Order id:");
                                for(Orders order:CostumerOrders){
                                    if(order.getOrderId()==Selection){
                                        Selectedorder=order;
                                        found = true;
                                        break;
                                    }
                                }
                                if (found) break;
                            }
                            //after the above loop break the user selected a valid order, from the filtered list CostumerOrders
                            OrdersHome homeorder = (OrdersHome) Selectedorder;
                            System.out.printf("Enter New Address: ");
                            String AddressInput=UserInterface.InputTypeAdress("");
                            homeorder.setAddress(AddressInput);
                            System.out.println("The order's Address has been changed Succesfuly");
                            PrintOrder(homeorder);
                            break;

                        }else{
                            System.out.println("The User Does not have any HomeOrders Pending so he can Change the Address of");
                            break;
                        }
                    }else{
                        System.out.println("The User Does not have any HomeOrders Pending so he can Change the Address of");
                        break;
                    }

                }else{ // in case the user provides a wrong input
                    System.out.println("Please Type id:number name:CostumerNAME or cancel for cancel");
                }

                }
            }
        
        



    public static void CompleteOrder(){

        boolean OrderToCompelte=false;
        Orders order=null;
        //prints all the pending orders, but also sets the OrderToCompelte =true, if left false there are no others to complete
        for(Orders orders:AllOrdersList){
            if (orders.getStatus().equalsIgnoreCase(Constants.PENDING)){
                PrintOrder(orders);
                OrderToCompelte=true;
            }
        }
        
 
        if(OrderToCompelte){
            System.out.println("Printed all "+ Constants.PENDING +" Orders for reference");
            order = SelectOrder(false); //will be promted to select order
        }
        else{
            System.out.println("All the orders in the system have been completed!");
        }
        //if there is an order and that order is an instance of ordershome the bellow statement will be run 
        if(OrderToCompelte && order instanceof OrdersHome ){
            OrdersHome ordersHome = (OrdersHome) order; // we make it a OrdersHome 
            String status = ordersHome.getStatus();

            if(status.equalsIgnoreCase(Constants.COMPLETED)){
                System.out.println("The order has already been completed"); //even after the printing, if the user gave other id than those shown, we show this message also
            }else{
                ordersHome.setStatusCompleted();
                System.out.println("The Order Has been Completed!"); 
                PrintOrder(order);
            }
            //same logic as above
        }else if (OrderToCompelte && order instanceof OrdersLocker ){
            OrdersLocker OrdersLocker = (OrdersLocker) order;
            String status = OrdersLocker.getStatus();
            if(status.equalsIgnoreCase(Constants.COMPLETED)){
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
            //in case the order is OrdersHome we will print the bellow format, 
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
                if (rating == null&& SelectedOrder.getStatus().equalsIgnoreCase(Constants.COMPLETED)) {
                    Rating = "Not Rated yet!";
                    }else if (rating == null&& SelectedOrder.getStatus().equalsIgnoreCase(Constants.PENDING)){
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
                //else its OrdersLockers, no other instance can it be
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
                if (rating == null&& orderslocker.getStatus().equalsIgnoreCase(Constants.COMPLETED)) {
                Rating = "Not Rated yet!";
                }else if (rating == null&& orderslocker.getStatus().equalsIgnoreCase(Constants.PENDING)){
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
                System.out.println();
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");}

        }

    public static void LeaveReview() {

    boolean orderExists = false;// will check if an order that can be completed exists

    for (Orders order : AllOrdersList) {
        if (order.getStatus().equalsIgnoreCase(Constants.COMPLETED) && order.getRating() == null) {
            orderExists = true; //it exists 
            PrintOrder(order); //print the valid order(s)
            }
        }
    
    if (!orderExists) {
            System.out.println("There are no orders available for rating"); //no orders exists, method ends
            return;
        }
    
    Orders selectedOrder = SelectOrder(false);
    
    if (selectedOrder == null) {
            System.out.println("No valid order was selected"); // a simple error handling (there is no point really, just adding it for good practise)
            return;
        }
    
    if (selectedOrder.getStatus().equalsIgnoreCase(Constants.COMPLETED) && selectedOrder.getRating() == null) {// prompt for rating
            System.out.println("Rate between 1 and 10: ");
            Integer ratingNumber = UserInterface.SelectNumber(10);
            selectedOrder.setRating(ratingNumber);
            System.out.println("Thank you for rating our service");
            PrintOrder(selectedOrder);
        } else { // order not finished or already rated
            System.out.println("The order is not eligible for rating.");
        }
    }
    
        
    

    
public static void ShowAverageReviews() {
    double avg = 0.0;
    Integer Ratings=null;
    List<Orders> ratedOrders = new ArrayList<>();
    Set<String> customerNames = new HashSet<>(); // so we dont get duplicates we wil use sets
    List<Integer> customerHighestR = new ArrayList<>();
    List<Integer> customerLowestR = new ArrayList<>();
    List<Integer> totalRatings = new ArrayList<>();
    // we get the costumer names
    for (Orders order : AllOrdersList) {
        if (order.getStatus().equalsIgnoreCase(Constants.COMPLETED) && order.getRating() != null) {
            ratedOrders.add(order);
            customerNames.add(order.getCostumerFullName()); 
        }
    }

    // calculate highest and lowest rating for each customer
    for (String customerName : customerNames) {
        Integer highest = -1; // the rating cant be lower than 1 or bigger than 10, so we use this values 
        Integer lowest = 11;
        Ratings=0;
        for (Orders order : ratedOrders) {
            if (order.getCostumerFullName().equalsIgnoreCase(customerName)) {
                Integer rating = order.getRating();
                Ratings++;
                if (rating > highest) {
                    highest = rating;
                }
                if (rating < lowest) {
                    lowest = rating;
                }
            }
        }
        totalRatings.add(Ratings);
        customerHighestR.add(highest);
        customerLowestR.add(lowest);
    }

    // getting the average
    if (!ratedOrders.isEmpty()) {
        for (Orders order : ratedOrders) {
            avg += order.getRating();
        }
        avg /= ratedOrders.size();
    }


    // printing header
    System.out.printf("| %-30s | %-30s | %-30s | %-30s \n","Costumer's Name","Total Ratings","Costumer's Highest Rating","Costumer's Lowest Rating");
    System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
    //print results
    int index = 0;
    for (String customerName : customerNames) {
        System.out.printf("| %-30s | %-30s | %-30d | %-30d \n", customerName,totalRatings.get(index), customerHighestR.get(index), customerLowestR.get(index));
        index++;
        System.out.println();
    }

    //print average
    System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
    System.out.printf("| Average Rating: %.2f |%n", avg);
    System.out.println("------------------------");
    //we clear the arrays
    ratedOrders.clear();
    customerNames.clear();
    customerHighestR.clear();
    customerLowestR.clear();


}



    // I will print for every costumer a summary of items bought, first for by barcode and then by category
    // and in the end i will print from all the orders the total items bought by barcode/category
public static void ProductsBoughtSummary() {

    List<ProductsInBucket> productsBought = new ArrayList<>(); // A list to store all the elements of all products bought
    Set<Costumers> CostumersWithOrders = new HashSet<>();//will store costumers that have orders only
    //will use set so i dont store duplicates
    Set<String> barcodesPerCostumer = new HashSet<>(); 
    Set<String> barcodesALL = new HashSet<>();
    Set<String> CategoryPerCostumer = new HashSet<>();
    Set<String> CategoryALL = new HashSet<>();



    // Collect all products bought from all orders
    for (Orders order : AllOrdersList) {
        List<ProductsInBucket> itemsBought = order.getProductsInOrder();
        productsBought.addAll(itemsBought);
        for(Costumers costumer:CostumerManager.getCostumersList()){
            if(costumer.getFullname().equalsIgnoreCase(order.getCostumerFullName())){
                CostumersWithOrders.add(costumer);//used set so i dont have to make a separate loop for adding the costumers
            }
        }
    }

    


    // will clear any values if exist in the sets, before we fill them
    CategoryALL.clear();
    barcodesALL.clear();

    //for every costumer now

    for(Costumers costumer:CostumersWithOrders){
        Integer totalOrderQuantity = 0;

        //get the name
        String CurrentCostumer=costumer.getFullname();
        Integer CurrentCostumerOrdersCount = CostumerManager.getCostumersTotalOrders(costumer.getId()); //get the total orders of the costumer

        //clear any previous values of the costumer before (if not the second ...third costumers will have the barcodes/categorys of the costumers before)
        barcodesPerCostumer.clear();
        CategoryPerCostumer.clear();



        //now for every costumer (we are inside the loop)
        //we will add the barcodes/categorys bought
        //but also we will add the barcodes/categorys to the sets of all costumers for the final total needed at the end
        for(ProductsInBucket item:productsBought){
            //we store all the barcodes to the set(all of them)
            barcodesALL.add(String.valueOf(item.getBarcode()));
            CategoryALL.add(item.getProductCategory());
            //we store the costumers barcode to the set
            if(costumer.getFullname().equalsIgnoreCase(item.getCostumersFullName())){
                barcodesPerCostumer.add(String.valueOf(item.getBarcode()));
            }
            if(costumer.getFullname().equalsIgnoreCase(item.getCostumersFullName())){
                CategoryPerCostumer.add(item.getProductCategory());
            }
        }


        System.out.println();
        System.out.println("----------------------------------------------------------------------------------------------------Current Costumer: "+ CurrentCostumer);
        System.out.println();
        System.out.println();
        // after we stored the costumers barocodes/categorys we will count their quantitys, first by barcode
        boolean FirstLoop=true;
        System.out.printf("%-30s %-30s %-30s %-30s \n","Costumer's Name","Costumer's Total Orders","Products Bought By Barcode","Quantity Barcode","Products Bought By Category","Quantity Category");
        System.out.println("--------------------------------------------------------------------------------------------------------------");
        for(String barcodes:barcodesPerCostumer ){
            Integer QuantityBarcode=0;
            for(ProductsInBucket product:productsBought){
                //if the user has bought the product 
                if(costumer.getFullname().equalsIgnoreCase(product.getCostumersFullName()) && barcodes.equalsIgnoreCase(String.valueOf(product.getBarcode()))){
                    QuantityBarcode+=product.getQuantity();
                    totalOrderQuantity+=product.getQuantity();

                }
            }
            // a simple statement that will help for the first print line, and then it will not print the costumers name again or orders total
            if(FirstLoop){
                System.out.printf("%-30s %-30d %-30s %-30d \n",CurrentCostumer,CurrentCostumerOrdersCount,barcodes,QuantityBarcode);
                FirstLoop=false;
            }else{
                System.out.printf("%-30s %-30s %-30s %-30d \n","","",barcodes,QuantityBarcode);
            }}




        //finished by printing the costumers items by barcode bought, now we do the same for the category
        System.out.println("--------------------------------------------------------------------------------------------------------------");
        System.out.println();
        System.out.println();
        System.out.printf("%-30s %-30s %-30s %-30s \n","Costumer's Name","Costumer's Total Orders","Products Bought By Category","Quantity Category");
        System.out.println("--------------------------------------------------------------------------------------------------------------");
        boolean FLoop=true; 
        Integer TotalQuantityCategory=0;
        for(String Category:CategoryPerCostumer){
            Integer QuantityCategory=0;
            for(ProductsInBucket product:productsBought){
                if(costumer.getFullname().equalsIgnoreCase(product.getCostumersFullName()) && Category.equalsIgnoreCase(product.getProductCategory())){
                    QuantityCategory+=product.getQuantity();
                    TotalQuantityCategory+=product.getQuantity();}
            }
            

            if(FLoop){
                System.out.printf("%-30s %-30d %-30s %-30d \n",CurrentCostumer,CurrentCostumerOrdersCount,Category,QuantityCategory);
                FLoop=false;
            }
            else{
                System.out.printf("%-30s %-30s %-30s %-30d \n","","",Category,QuantityCategory);
            }
        }
        
    //finished by printing the costumers items by category bought





    // now we will print their total items bought.
    System.out.println("--------------------------------------------------------------------------------------------------------------");
    System.out.printf("%-30s %-30s %-30s %-30d \n","Totals of Costumer","--------","----------",totalOrderQuantity);
    System.out.println();
    System.out.println();
    System.out.println();
    //System.out.printf(" %-30d\n ",totalOrderQuantity);
    
    }

    // after the end of the above loop (costumers) we printed all the costumers products bought by barcode and category
    //what remains now is printing from all the orders the categorys/barcodes bought
    System.out.println();
    System.out.printf("------------------------------------------------------------------------------------------------------------------------------------All Orders Totals\n");
    System.out.println();
    System.out.println();
    //printing all orders summary by barcode
    System.out.println();
    System.out.println();
    Integer TotalCounter=0;
    System.out.printf("---------------------------------------------------------------------------------------------All Orders by Barcode\n");
    System.out.printf(" %-30s %-30s %-30s\n","Total Orders","Barcodes","Quantity of each");
    System.out.println("--------------------------------------------------------------------------------------------------------------");
    boolean Ffloop=true;
    for(String barcodes:barcodesALL ){
    Integer Counter=0;
    for(ProductsInBucket product:productsBought){
        if(barcodes.equalsIgnoreCase(String.valueOf(product.getBarcode()))){
            Counter+=product.getQuantity();
            TotalCounter+=product.getQuantity();
        }
    }
    if(Ffloop){
        System.out.printf(" %-30d %-30s %-30d \n",AllOrdersList.size(),barcodes,Counter);
        Ffloop=false;
    }else{
        System.out.printf(" %-30s %-30s %-30d \n","",barcodes,Counter);
    }}
    
    System.out.println("--------------------------------------------------------------------------------------------------------------");
    System.out.printf(" %-30s %-30s %-30d \n","Total Products Bought","",TotalCounter);
    System.out.println();
    System.out.println();



    //printing all orders summary by Category, same logic as above
    System.out.println();
    System.out.println();
    Integer TotalCounterCategory=0;
    System.out.printf("---------------------------------------------------------------------------------------------All Orders by Category\n");
    System.out.printf(" %-30s %-30s %-30s\n","Total Orders","Category","Quantity of each");
    System.out.println("--------------------------------------------------------------------------------------------------------------");
    boolean Floop=true;
    for(String category:CategoryALL ){
    Integer CounterCategory=0;
    for(ProductsInBucket product:productsBought){
        if(category.equalsIgnoreCase(product.getProductCategory())){
            CounterCategory+=product.getQuantity();
            TotalCounterCategory+=product.getQuantity();
        }
    }
    if(Floop){
        System.out.printf(" %-30d %-30s %-30d \n",AllOrdersList.size(),category,CounterCategory);
        Floop=false;
    }else{
        System.out.printf(" %-30s %-30s %-30d \n","",category,CounterCategory);
    }
    }
    System.out.println("--------------------------------------------------------------------------------------------------------------");
    System.out.printf(" %-30s %-30s %-30d \n","Total Products Bought","",TotalCounter);
    System.out.println();
    System.out.println();

    // now we printed for every coustumer in case he has an order hes total orders, by barcode and category
    //and also for all the orders all the products bought by category and barcode.
  
}

public static void ShowDriverOrdes() {
    //HEADER OF THE PRINT
    System.out.println();
    System.out.printf(" %-15s %-25s %-25s %-25s %-25s %-25s %-25s | %-15s\n","Driver's ID","DriverCategory",
    "Driver's Name","Pending Home Orders","Completed Home Orders",
    "Pending Lockers Orders","Completed Lockers Orders",
    "Total Per Driver");
    System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    //the bellow variables will store the total for each attribute that is named
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
        //in every loop we need to set the above values to 0 , so the next driver gets calculated hes own values
        for (Orders orders : AllOrdersList) {
            if (orders.getDriverFullName().equalsIgnoreCase(driver.getFullname())) {
                if (orders.getStatus().equalsIgnoreCase(Constants.PENDING)) {
                    if (orders instanceof OrdersHome) {
                        PendingHomeOrders++;
                    } else if (orders instanceof OrdersLocker) {
                        PendingLockersOrders++;
                    }
                } else if (orders.getStatus().equalsIgnoreCase(Constants.COMPLETED)) {
                    if (orders instanceof OrdersHome) {
                        CompletedHomeOrders++;
                    } else if (orders instanceof OrdersLocker) {
                        CompletedLockersOrders++;
                    }
                }
            }
        }
        //we get the values inside the loop
        PendingHomeOrdersTotal+=PendingHomeOrders;
        CompletedHomeOrdersTotal+=CompletedHomeOrders;
        PendingLockersOrdersTotal+=PendingLockersOrders;
        CompletedLockersOrdersTotal+=CompletedLockersOrders;
        TotalperDriver=PendingHomeOrders+CompletedHomeOrders+PendingLockersOrders+CompletedLockersOrders;
        AllOrderCount+=TotalperDriver;
        //i will not show drivers that have no orders,(my though processis that if there are for example 100 drivers, there is no need to show the ones without orders)
        if(TotalperDriver>0){
            System.out.printf(" %-15d %-25s %-25s %-25d %-25d %-25d %-25d | %-15d\n",driver.getDriverid(),driver.getType(), driver.getFullname(),  PendingHomeOrders,
            CompletedHomeOrders,
            PendingLockersOrders,
            CompletedLockersOrders,TotalperDriver);
        }
    }
    if(AllOrderCount>0){ // will print the totals of all orders
        System.out.println();
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf(" %-15s %-20s %-25s %-25d %-25d %-25d %-25d | %-25d\n","Total Of All Orders:","-","-",PendingHomeOrdersTotal,CompletedHomeOrdersTotal,
        PendingLockersOrdersTotal,CompletedLockersOrdersTotal,AllOrderCount);
        System.out.println();
    }else{
        System.out.println("There are no Orders Places yet");
    }

}

public static void ShowOrdersDetailsByIdOrCostumer() {
        List<Orders> CostumerOrders = new ArrayList<>(); // will store each costumers orders

        Integer Orderid = null;
        String CostumerName = null;
        printAllOrders();//for reference
        while (true) {
            System.out.println("Enter an order id or Costumer's Name: ");
            System.out.println("Or Enter cancel to cancel");
            String Input = UserInterface.scanner.nextLine().trim();

            if (Input.equalsIgnoreCase("cancel")) {
                return; //if user typed cancel, he will be promted to return to main menu
            }

            try {
                Orderid = Integer.parseInt(Input);// if it gets pasred then its a number and we seach order ids by that number
                System.out.println("You entered an id. Searching the orders by id...");
                for (Orders order : AllOrdersList) {
                    if (order.getOrderId().equals(Orderid)) {
                        CostumerOrders.add(order); //adding to list, 1 will be added
                        break;
                    }
                }
            } catch (NumberFormatException e) {
                CostumerName = Input; //else is string and searching by costumersname
                for (Orders order : AllOrdersList) {
                    if (order.getCostumerFullName().equalsIgnoreCase(CostumerName)) {
                        CostumerOrders.add(order); //adding to list
                    }
                }
            }

            
            if (CostumerOrders.isEmpty()) {// if there are not orders from the above adding
                if (Orderid != null) {
                    System.out.println("There are no Orders with that id.");
                } else if (CostumerName != null){
                    System.out.println("There are no Orders with that Costumer's Name.");
                }
                
                Orderid = null;
                CostumerName = null;
                CostumerOrders.clear();
            } else {
                break; // is not empty, then we continue with the printing
            }
        }

        if (CostumerOrders.size() > 1) { // if is above 1(we searched with the costumer's name)
            for (Orders order : CostumerOrders) {
                PrintOrder(order);
            }
        } else if (CostumerOrders.size() == 1) {
            PrintOrder(CostumerOrders.get(0)); // its one only becouse we searched with id
        }
    }

}


    