import java.util.ArrayList;
import java.util.List;
public class DriverManager{
    private static List<Drivers> driverList = new ArrayList<>();


    public static List<Drivers> getDriverList(){
        return driverList;
    }

    public static Drivers GetCurrentDriverByFullName( String DriverFullName){
        //this method will return the object in case that object has the "data" parametere as data in its fields
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

    
    public static boolean CheckDriverExists(String DriverFullName){
        boolean isValid = false;
        Integer SpacePos = DriverFullName.indexOf(" ");
        String DriverFirstName = DriverFullName.substring(0, SpacePos);
        String DriverLastName = DriverFullName.substring(SpacePos+1, DriverFullName.length());
            for (Drivers driver : driverList) {
                if (DriverFirstName.equalsIgnoreCase(driver.getName()) && DriverLastName.equalsIgnoreCase(driver.getSurname()) ) {
                    isValid = true;
                    
                    break;
                }
            }
        
            return isValid;
        }    



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
            System.out.printf("%-5s %-17s %-20s %-30s %-20s %-19s %-10s%n ", "ID","Name", "lastName", "Adress", "Email", "AFM","Plate Number","Orders Type");
        
            // Printing each product row
            for (int i = 0; i < driverList.size(); i++) {
                Drivers drivers = driverList.get(i);
                System.out.printf("%-5d %-17s %-20s %-30s %-20s %-19d %-10s %-20s \n",drivers.getDriverid(),drivers.getName(),drivers.getSurname(),drivers.getAdress(),drivers.getEmail(),drivers.getDriverAFM(),drivers.getPlateNumber(),drivers.getType());
            }
        }
    public static List<String> NotFoundAddNew(String DriverFullName,String OrderType){
        List<String> ActionRecorder=new ArrayList<>();
        Integer SpacePos = DriverFullName.indexOf(" ");
        String DriverFirstName = DriverFullName.substring(0, SpacePos);
        String DriverLastName = DriverFullName.substring(SpacePos+1, DriverFullName.length());
        String address = UserInterface.InputTypeAdress("Type Drivers Adress: ");
        String DriversEmail = UserInterface.InputTypeEmail("Type Drivers Email: ");
        Integer DriverSAFM =UserInterface.InputTypeAFM("Type Drivers AFM: ");
        String DriversPlateNumber = UserInterface.InputTypePlateNumber("Type Drivers PlateNumber: ");
        System.out.println("Choose the Driver's Category for the Orders");
        if(OrderType.equalsIgnoreCase(Constants.DELIVERYHOME)){
            System.out.printf(" %s %s %s %s\n %s \n","Press 1) To Put Driver to ",Constants.DELIVERYHOME," Category. Recomended since you choose: ",OrderType,"Press 2) To Put Driver to LockerDelivery Category");
        }else{//Now we Will Handle MissMatch of Driver and Order delivery option
            System.out.printf(" %s\n %s %s %s %s\n ","Press 1) To Put Drivers to HomeDelivery Category",
            "Press 2) To Put Driver to ",Constants.LOCKERDELIVERY," Category. Recomended since you choose: ",OrderType);
        }
        System.out.printf("%s %s %s\n"," Press 3) To Put Driver to ", Constants.HOMEANDLOCKER, "Category. (Recommended) ");
        Integer SelectedOption = UserInterface.SelectNumber(3);
        String DriversType;
        if(SelectedOption==1){
            DriversType=Constants.DELIVERYHOME;
        }else if(SelectedOption==2){
            DriversType=Constants.LOCKERDELIVERY;
        }else{
            DriversType=Constants.HOMEANDLOCKER;
            driverList.add(new Drivers(DriverFirstName,DriverLastName,address,DriversEmail,DriverSAFM,DriversPlateNumber,DriversType));
            ActionRecorder.add(DriverFullName);
            return ActionRecorder;
        }
        
        if(OrderType.equalsIgnoreCase(Constants.DELIVERYHOME)&& (DriversType.equalsIgnoreCase(Constants.DELIVERYHOME) || DriversType.equalsIgnoreCase(Constants.HOMEANDLOCKER))){
            driverList.add(new Drivers(DriverFirstName,DriverLastName,address,DriversEmail,DriverSAFM,DriversPlateNumber,DriversType));
            System.out.println("Driver Added Sucesfully, continuing with order");
            ActionRecorder.add(DriverFullName);
            return ActionRecorder;
        }
        else if(OrderType.equalsIgnoreCase(Constants.LOCKERDELIVERY)&&(DriversType.equalsIgnoreCase(Constants.LOCKERDELIVERY) || DriversType.equalsIgnoreCase(Constants.HOMEANDLOCKER))){
            driverList.add(new Drivers(DriverFirstName,DriverLastName,address,DriversEmail,DriverSAFM,DriversPlateNumber,DriversType));
            System.out.println("Driver Added Sucesfully, continuing with order");
            ActionRecorder.add(DriverFullName);
            return ActionRecorder;
        }else{//MissMatch Will be Sorted Here
            System.out.println("You had chosen "+OrderType+ " But you are trying to add Driver to a different category ");
            System.out.println("Before We continue we need to sort this MissMatch. Your option are listed bellow");
            System.out.println(" 1)Change Driver to "+OrderType+ " and continue with order.");
            System.out.println(" 2)Change Order to "+DriversType+ " and continue with order.");
            System.out.println(" 3)Abort adding this driver, and create a new one With the same Order type category");
            Integer Option;
            if(getCounterByType(OrderType)>0){
                System.out.println(" 4)Select a different Driver from the list");
                Option=UserInterface.SelectNumber(4);
            }
            else{
                Option=UserInterface.SelectNumber(3);
            }
            switch (Option) {

                case 1:
                driverList.add(new Drivers(DriverFirstName,DriverLastName,address,DriversEmail,DriverSAFM,DriversPlateNumber,OrderType));
                ActionRecorder.add(DriverFirstName+ " " +DriverLastName);
                    break;


                case 2:
                    driverList.add(new Drivers(DriverFirstName,DriverLastName,address,DriversEmail,DriverSAFM,DriversPlateNumber,OrderType));
                    ActionRecorder.add(DriverFirstName+ " " +DriverLastName);
                    if(OrderType.equalsIgnoreCase(Constants.DELIVERYHOME)){
                        ActionRecorder.add(Constants.LOCKERDELIVERY);
                    }else if(OrderType.equalsIgnoreCase(Constants.LOCKERDELIVERY)){
                        ActionRecorder.add(Constants.DELIVERYHOME);
                    }
                    break;



                case 3:
                    String NewDriverFullName =  AddNewDriverFromNewOrder(OrderType);
                    ActionRecorder.add(NewDriverFullName);
                    break;
                case 4:
                    String SelectedDriverFullName = SelectDriver(OrderType);
                    ActionRecorder.add(SelectedDriverFullName);
                    break;
            }
            

        }
        return ActionRecorder;
    }

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
    public static void AddNewDriver(){
        String DriverFirstName = UserInterface.StringInput("Enter The First name of the Driver: ");
        String DriverLastName = UserInterface.StringInput("Enter The Last name of the Driver: ");
        String address = UserInterface.InputTypeAdress("Type Drivers Adress: ");
        String Email = UserInterface.InputTypeEmail("Type Costumes Email: ");
        Integer AFM = UserInterface.InputTypeAFM("Enter Driver's AFM:");
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
        System.out.println("Only Drivers That Support the Delivery Method are shown and able to get selected!");
        System.out.printf("%-5s %-17s %-20s %-30s %-20s %-19s %-10s %-20s%n ", "ID","Name", "lastName", "Adress", "Email", "AFM","Plate Number","Orders Type");
        for(Drivers driver:driverList){
            if(driver.getType().equalsIgnoreCase(Type) || driver.getType().equalsIgnoreCase(Constants.HOMEANDLOCKER)){
                System.out.printf("%-5d %-17s %-20s %-30s %-20s %-19d %-10s %-20s \n",driver.getDriverid(),driver.getName(),driver.getSurname(),driver.getAdress(),driver.getEmail(),driver.getDriverAFM(),driver.getPlateNumber(),driver.getType());
            }
        }

        while (true) {
            String DriversFullName = UserInterface.InputTypeStringWithSpace("Select The Driver by typing the full name as Shown Above");
            boolean Exists = CheckDriverExists(DriversFullName);
            Selecteddriver = GetCurrentDriverByFullName(DriversFullName);
            if(Exists && (Selecteddriver.getType().equalsIgnoreCase(Type)||Selecteddriver.getType().equalsIgnoreCase(Constants.HOMEANDLOCKER))){
                System.out.println("The Driver Succesfuly Selected");
                break;
            }else if(!Exists){
                System.out.println("The Driver Does not Exist");
                System.out.println("Select driver again");
            }else{
                System.out.println("The Driver you selected Does not Support this Type of Delivery");
            }

            
        }
        return Selecteddriver.getDriverFullName();
    }

    public static boolean DriverByTypeExists(String Type){
        boolean isValid = false;
        for(Drivers driver:driverList){
            if (driver.getType().equalsIgnoreCase(Type)||driver.getType().equalsIgnoreCase(Constants.HOMEANDLOCKER)){
                isValid = true;
                break;
            }
        }return isValid;
    }   

    public static Integer getCounterByType(String Type){
        Integer Counter = 0;
        for(Drivers driver:driverList){
            if (driver.getType().equalsIgnoreCase(Type)||driver.getType().equalsIgnoreCase(Constants.HOMEANDLOCKER)){
                Counter+=1;

            }
        }return Counter;
    }   
}