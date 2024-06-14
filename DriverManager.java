import java.util.ArrayList;
import java.util.List;
public class DriverManager{
    private static List<Drivers> driverList = new ArrayList<>();


    public static List<Drivers> getDriverList(){
        return driverList;
    }

    public static Drivers GetCurrentDriverByFullName( String DriverFullName){
        //searches the driverlist and if the driver with the given (and splited name ) is found, it returnes it
        Drivers SelectedDriver=null;
        Integer SpacePos = DriverFullName.indexOf(" ");
        String DriverFirstName = DriverFullName.substring(0, SpacePos);
        String DriverLastName = DriverFullName.substring(SpacePos+1, DriverFullName.length());
        for(Drivers driver : driverList){
            if (DriverFirstName.equalsIgnoreCase(driver.getName()) && DriverLastName.equalsIgnoreCase(driver.getSurname())){
                SelectedDriver = driver;
            }
        }
        return SelectedDriver;
    }

    // check driverlist if the driver with the given name exists
    //the name is splited to first name and surrname are validated 
    public static boolean CheckDriverExists(String DriverFullName){
        boolean isValid = false;// if its not found the default value will be returned
        Integer SpacePos = DriverFullName.indexOf(" ");
        String DriverFirstName = DriverFullName.substring(0, SpacePos);
        String DriverLastName = DriverFullName.substring(SpacePos+1, DriverFullName.length());
            for (Drivers driver : driverList) {
                if (DriverFirstName.equalsIgnoreCase(driver.getName()) && DriverLastName.equalsIgnoreCase(driver.getSurname()) ) {
                    isValid = true;// was found
                    
                    break;
                }
            }
        
            return isValid; 
        }    


    //creates the defauilt drivers
    public static void CreateDefaultDrivers(){
        for (String[] DriversData : Constants.DRIVERS_DATA) {
            String fname = DriversData[0];
            String lname = DriversData[1];
            String address =DriversData[2];
            String email=DriversData[3];
            Integer afm = Integer.parseInt(DriversData[4]);
            String PlateNumber = DriversData[5];
            String OrderCategory = DriversData[6];
            driverList.add( new Drivers (fname,lname,address,email,afm,PlateNumber,OrderCategory));
        }
        }



    public static void printTableDrivers() {
            // Printing table header
            if(driverList.isEmpty()){ // a small statement that will print the details only if there are drivers

                System.out.println("There are no Drivers in the system yet!");
                return ;
            }
            System.out.printf("%-5s %-17s %-20s %-30s %-20s %-19s %-10s%n ", "ID","Name", "lastName", "Adress", "Email", "AFM","Plate Number","Orders Type");
        
            // Printing each product row
            for (int i = 0; i < driverList.size(); i++) {
                Drivers drivers = driverList.get(i);
                System.out.printf("%-5d %-17s %-20s %-30s %-20s %-19d %-10s %-20s \n",drivers.getDriverid(),drivers.getName(),drivers.getSurname(),drivers.getAddress(),drivers.getEmail(),drivers.getDriverAFM(),drivers.getPlateNumber(),drivers.getType());
            }
        }


        // this method is used only in CreateSecond, it will be run in case the user typed a Driver name that does not exist, 
    public static List<String> NotFoundAddNew(String DriverFullName,String OrderType){ // the ordertype is the type of the order the user choose from the createSecond method
        // where he first selected the type of the order

        List<String> ActionRecorder=new ArrayList<>(); // this list will store the option that the user selected later on
        // drivername is the first index, and in case he chooses to change the ordertype, the ordertype is the second index

        Integer SpacePos = DriverFullName.indexOf(" ");
        String DriverFirstName = DriverFullName.substring(0, SpacePos);
        String DriverLastName = DriverFullName.substring(SpacePos+1, DriverFullName.length()); //we split the driverfull name to first and last name

        //ask for the rest of the details
        String address = UserInterface.InputTypeAdress("Type Drivers Adress: ");
        String DriversEmail = UserInterface.InputTypeEmail("Type Drivers Email: ");
        Integer DriverSAFM =UserInterface.InputTypeAFM("Type Drivers AFM: ");
        String DriversPlateNumber = UserInterface.InputTypePlateNumber("Type Drivers PlateNumber: ");
        System.out.println("Choose the Driver's Category for the Orders");

        // will print and help the user to select the correct delviery type for the driver he is creating

        if(OrderType.equalsIgnoreCase(Constants.DELIVERYHOME)){ //in case the user had  selected Delivery to home from the CreateSecond method 
            System.out.printf(" %s %s %s %s\n %s \n","Press 1) To Put Driver to ",Constants.DELIVERYHOME,
            " Category. Recomended since you choose: ",OrderType,"Press 2) To Put Driver to LockerDelivery Category");


        }else{//in case the user had selected Delivery to lockers from the CreateSecond method 
            System.out.printf(" %s\n %s %s %s %s\n ","Press 1) To Put Drivers to HomeDelivery Category",
            "Press 2) To Put Driver to ",Constants.LOCKERDELIVERY," Category. Recomended since you choose: ",OrderType);
        }

        // this option does both, so is always recomended for all drivers
        System.out.printf("%s %s %s\n"," Press 3) To Put Driver to ", Constants.HOMEANDLOCKER, "Category. (Recommended) "); 

         // the option of the driver 
        Integer SelectedOption = UserInterface.SelectNumber(3); 


        // we get the string based on the integer provided
        String DriversType;
        if(SelectedOption==1){
            DriversType=Constants.DELIVERYHOME; 
        }else if(SelectedOption==2){
            DriversType=Constants.LOCKERDELIVERY; 
        }else{
            DriversType=Constants.HOMEANDLOCKER; 
            // the new driver can do both type of orders, so he is created and returned with hes driversfullname, where another method gets the object based on the that name
            driverList.add(new Drivers(DriverFirstName,DriverLastName,address,DriversEmail,DriverSAFM,DriversPlateNumber,DriversType));
            ActionRecorder.add(DriverFullName);
            return ActionRecorder; // returned since the user choose the drivertype for both orders and the driver is not restricted from the ordertype
        }
        


        // in case the Delivery type and the Driver delviery type are valid from the above selection 
        // then the driver is created
        if(OrderType.equalsIgnoreCase(Constants.DELIVERYHOME)&& (DriversType.equalsIgnoreCase(Constants.DELIVERYHOME) || DriversType.equalsIgnoreCase(Constants.HOMEANDLOCKER))){
            driverList.add(new Drivers(DriverFirstName,DriverLastName,address,DriversEmail,DriverSAFM,DriversPlateNumber,DriversType));
            System.out.println("Driver Added Sucesfully, continuing with order");
            ActionRecorder.add(DriverFullName);// the drivername is added 
            return ActionRecorder; // and returned, with lenght of 1
        }



        // same as above but for the Lockers delivery, in case the user choose the lockers delivery but also the locker delivery for the order
        else if(OrderType.equalsIgnoreCase(Constants.LOCKERDELIVERY)&&(DriversType.equalsIgnoreCase(Constants.LOCKERDELIVERY) || DriversType.equalsIgnoreCase(Constants.HOMEANDLOCKER))){
            driverList.add(new Drivers(DriverFirstName,DriverLastName,address,DriversEmail,DriverSAFM,DriversPlateNumber,DriversType));
            System.out.println("Driver Added Sucesfully, continuing with order");
            ActionRecorder.add(DriverFullName);
            return ActionRecorder;




        //MissMatch Will be Sorted Here
        // in case the user did not choose the correct delivery type for the driver, he will have to select an option from bellow, to handle the missmatch
        }else{ // printing the user the options
            System.out.println("You had chosen "+OrderType+ " But you are trying to add Driver to a different category ");
            System.out.println("Before We continue we need to sort this MissMatch. Your option are listed bellow");
            System.out.println(" 1)Change Driver to "+OrderType+ " and continue with order.");
            System.out.println(" 2)Change Order to "+DriversType+ " and continue with order.");
            System.out.println(" 3)Abort adding this driver, and create a new one With the same Order type category");
            Integer Option;

            // in case only when there are Drivers by the type of the order he choose,
            // he will be able to select one of those driver
            if(getCounterByType(OrderType)>0){
                System.out.println(" 4)Select a different Driver from the list");
                Option=UserInterface.SelectNumber(4);
            }
            else{ // there are not drivers with that type of order type avaible, so the user will not be prompted to select from the existing drivers
                Option=UserInterface.SelectNumber(3);
            }



            switch (Option) {


                // the user selected to change the driver Delivery type
                // driver is created with the same ordertype as he selected on the CreateSecond method and then the name is added to the list, so the CreateSecond can procced
                case 1:
                driverList.add(new Drivers(DriverFirstName,DriverLastName,address,DriversEmail,DriverSAFM,DriversPlateNumber,OrderType));
                ActionRecorder.add(DriverFirstName+ " " +DriverLastName);
                break;

                // the user selected to change the delivery type of the order, accordingly to the drivers first OrderType he entered 

                case 2:// driver created as the first option he gave
                    driverList.add(new Drivers(DriverFirstName,DriverLastName,address,DriversEmail,DriverSAFM,DriversPlateNumber,DriversType));
                    ActionRecorder.add(DriverFirstName+ " " +DriverLastName); // adding the newly created driversfullname 
                    // adding the oposite of the option he gave from the CreateSecond method ( getting it from the passed variable <OrderType> in this method)
                    if(OrderType.equalsIgnoreCase(Constants.DELIVERYHOME)){
                        ActionRecorder.add(Constants.LOCKERDELIVERY);//adding that to the action recorder
                    }else if(OrderType.equalsIgnoreCase(Constants.LOCKERDELIVERY)){
                        ActionRecorder.add(Constants.DELIVERYHOME); //adding that to the action recorder
                    }
                    break;
                    //action recorder in this case has length of 2
                    //so the CreateSecond method has an if statement
                    //where if the lenghth is 2
                    // the drivers name is taken from index 0 and the ordertype that will be take and then changed from index 1
                    // the order type then is stored in the same variable as the one he first choose the delivery method 


                // he choose to abort this current driver creation
                //so a new driver is prompted to be created
                //this new driver will have the user's selected OrderType he first choose in the OrderCreation (prefilled)
                case 3:
                    String NewDriverFullName =  AddNewDriverFromNewOrder(OrderType);
                    ActionRecorder.add(NewDriverFullName); //the new driverfull name is added to the ActionRecorder[0]
                    break;


                case 4:
                    String SelectedDriverFullName = SelectDriver(OrderType); //the user choose to select a new driver, in case this option was given 
                    ActionRecorder.add(SelectedDriverFullName); //the new driverfull name is added to the ActionRecorder[0]
                    break;
            }
            

        }
        return ActionRecorder; // the list is returned, holding at least at index 0 the driverfullname and in case the switch 2 was run it also holds at index 1 the new value of the ordertype he switched
        
    }


    //this is run only from the above method, in case the user wanted to abort the creation of that driver and wanted a new one
    public static String AddNewDriverFromNewOrder(String OrderType){
        String DriverFirstName = UserInterface.StringInput("Enter The First name of the Driver: ");
        String DriverLastName = UserInterface.StringInput("Enter The Last name of the Driver: ");
        String address = UserInterface.InputTypeAdress("Type Drivers Adress: ");
        String Email = UserInterface.InputTypeEmail("Type Costumes Email: ");
        Integer AFM = UserInterface.InputTypeAFM("Enter Driver's AFM:");
        String PlateNumber = UserInterface.InputTypePlateNumber("Enter Driver's Plate Number:");
        System.out.printf("%s %s\n %s\n %s\n","Choose By Typing :","1)For Home Deliveries","2)locker Deliveries","3)Home & Locker Deliveries");
        driverList.add(new Drivers (DriverFirstName,DriverLastName,address,Email,AFM,PlateNumber,OrderType));
        System.out.println("Driver succesfuly Added!");
        return DriverFirstName+" "+DriverLastName;
    }

    // used sot he user can add a new driver from the main menu
    public static void AddNewDriver(){

        String DriverFirstName = UserInterface.StringInput("Enter The First name of the Driver: ");
        String DriverLastName = UserInterface.StringInput("Enter The Last name of the Driver: ");
        String address = UserInterface.InputTypeAdress("Type Drivers Adress: ");
        String Email = UserInterface.InputTypeEmail("Type Costumes Email: ");
        Integer AFM = UserInterface.InputTypeAFM("Enter Driver's AFM ( 8 digits):");
        String PlateNumber = UserInterface.InputTypePlateNumber("Enter Driver's Plate Number:");
        System.out.printf("%s\n %s\n %s\n %s\n","Choose By Typing :","1)For Home Deliveries","2)locker Deliveries","3)Home & Locker Deliveries");
        Integer SelectDeliveryType = UserInterface.SelectNumber(3);
        String TYPE="";
        switch (SelectDeliveryType) {
            case 1:
                TYPE=Constants.DELIVERYHOME;
                break;
            case 2:
                TYPE=Constants.LOCKERDELIVERY;
                break;
            case 3:
                TYPE=Constants.HOMEANDLOCKER;
                break;
        }
        Drivers Driver = new Drivers(DriverFirstName,DriverLastName,address,Email,AFM,PlateNumber,TYPE);
        System.out.printf("%s\n %s %d\n %s %s\n%s %s\n %s %s\n%s %s\n %s %d\n%s %s %s" ,"The Driver Was Added Successfully",
        "Driver ID: ",Driver.getId(),
        "Driver First Name: ",Driver.getName(),
        " Driver Last Name: ",Driver.getSurname(),
        "Driver's Address: ",Driver.getAddress(),
        " Driver's Email: ",Driver.getEmail(),
        "Driver's AFM: ",Driver.getDriverAFM(),
        " Driver's PLateNumber: ",Driver.getPlateNumber(),"\n");
    }
    //we will pass as parameters the search criteria ,First parameter the status we search, and second parameter the instance of the order

    public static String  SelectDriver(String Type){
        Drivers Selecteddriver;
        // this will prompt the user to selectdriver based on the type of the Delivery method passed (Type)
        System.out.println("Only Drivers That Support the Delivery Method are shown and able to get selected!");
        System.out.printf("%-5s %-17s %-20s %-30s %-20s %-19s %-10s %-20s%n ", "ID","Name", "lastName", "Adress", "Email", "AFM","Plate Number","Orders Type");
        for(Drivers driver:driverList){
            if(driver.getType().equalsIgnoreCase(Type) || driver.getType().equalsIgnoreCase(Constants.HOMEANDLOCKER)){
                System.out.printf("%-5d %-17s %-20s %-30s %-20s %-19d %-10s %-20s \n",driver.getDriverid(),driver.getName(),driver.getSurname(),driver.getAddress(),driver.getEmail(),driver.getDriverAFM(),driver.getPlateNumber(),driver.getType());
            }
        }//printes driver for reference based on the passed variable from the parameters

        while (true) {
            String DriversFullName = UserInterface.InputTypeStringWithSpace("Select The Driver by typing the full name as Shown Above");
            boolean Exists = CheckDriverExists(DriversFullName);
            Selecteddriver = GetCurrentDriverByFullName(DriversFullName);
            if(Exists && (Selecteddriver.getType().equalsIgnoreCase(Type)||Selecteddriver.getType().equalsIgnoreCase(Constants.HOMEANDLOCKER))){
                System.out.println("The Driver Succesfuly Selected");
                break;//the driver sellected and exists
            }else if(!Exists){
                System.out.println("The Driver Does not Exist");
                System.out.println("Select driver again");
            }else{
                System.out.println("The Driver you selected Does not Support this Type of Delivery"); // the driver exists but does not do the delivery that was passed, 
                //also those types of drivers are not shown from the for loop that was run, just adding this else for best practise and error handling
            }

            
        }
        return Selecteddriver.getFullname(); // returnes the name of the driver
    }

    public static boolean DriverByTypeExists(String Type){
        // Checks if there is a driver that does the type of orders that is passed in the method
        boolean isValid = false;
        for(Drivers driver:driverList){
            if (driver.getType().equalsIgnoreCase(Type)||driver.getType().equalsIgnoreCase(Constants.HOMEANDLOCKER)){
                isValid = true;
                break;
            }
        }return isValid;
    }   

    public static Integer getCounterByType(String Type){
        //counts the amount of drivers that do the type of delviery passed in the parameters
        Integer Counter = 0;
        for(Drivers driver:driverList){
            if (driver.getType().equalsIgnoreCase(Type)||driver.getType().equalsIgnoreCase(Constants.HOMEANDLOCKER)){
                Counter+=1;

            }
        }return Counter;
    }   
}