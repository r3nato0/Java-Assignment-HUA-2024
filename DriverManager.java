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
            if (DriverFirstName.equals(driver.getName()) && DriverLastName.equals(driver.getSurname())){
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
                if (DriverFirstName.equals(driver.getName()) && DriverLastName.equals(driver.getSurname()) ) {
                    isValid = true;
                    
                    break;
                }
            }
        
            return isValid;
        }    



    public static void CreateDefaultDrivers(){
        driverList.add(new Drivers("Renato","Nake","Aglaurou 10","nakerenato@gmail.com",16215021,"YMN4946",1));
        driverList.add(new Drivers("George","Papadopoylos","dimitrakopoulou 105","georgepapadopoylos@gmail.com",13231021,"IZN5236",1));
        driverList.add(new Drivers("Anthoni","Mantellos","Hrakleous 70","AMantellos@gmail.com",17231021,"IZN5236",1));
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
    public static void NotFoundAddNew(String DriverFullName){
        Integer SpacePos = DriverFullName.indexOf(" ");
        String DriverFirstName = DriverFullName.substring(0, SpacePos);
        String DriverLastName = DriverFullName.substring(SpacePos+1, DriverFullName.length());
        String address = UserInterface.InputTypeAdress("Type Drivers Adress: ");
        String DriversEmail = UserInterface.InputTypeEmail("Type Drivers Email: ");
        Integer DriverSAFM =UserInterface.InputTypeAFM("Type Drivers AFM: ");
        String DriversPlateNumber = UserInterface.InputTypePlateNumber("Type Drivers PlateNumber: ");
        System.out.printf("%s %s\n %s\n %s\n","Choose By Typing :","1)For Home Deliveries","2)locker Deliveries","3)Home & Locker Deliveries");
        Integer DriversType = UserInterface.SelectNumber(3);
        driverList.add(new Drivers(DriverFirstName,DriverLastName,address,DriversEmail,DriverSAFM,DriversPlateNumber,DriversType));
    }

    public static void AddNewDriver(){
        String DriverFirstName = UserInterface.StringInput("Enter The First name of the Driver: ");
        String DriverLastName = UserInterface.StringInput("Enter The Last name of the Driver: ");
        String address = UserInterface.InputTypeAdress("Type Drivers Adress: ");
        String Email = UserInterface.InputTypeEmail("Type Costumes Email: ");
        Integer AFM = UserInterface.InputTypeAFM("Enter Driver's AFM:");
        String PlateNumber = UserInterface.InputTypePlateNumber("Enter Driver's Plate Number:");
        System.out.printf("%s %s\n %s\n %s\n","Choose By Typing :","1)For Home Deliveries","2)locker Deliveries","3)Home & Locker Deliveries");
        Integer DriversType = UserInterface.SelectNumber(3);
        Drivers Driver = new Drivers(DriverFirstName,DriverLastName,address,Email,AFM,PlateNumber,DriversType);
        System.out.printf("%s\n %s %d\n %s %s\n%s %s\n %s %s\n%s %s\n %s %d\n%s %s" ,"The Driver Was Added Successfully",
        "Driver ID: ",Driver.getId(),
        "Driver First Name: ",Driver.getName(),
        " Driver Last Name: ",Driver.getSurname(),
        "Driver's Address: ",Driver.getAddress(),
        " Driver's Email: ",Driver.getEmail(),
        "Driver's AFM: ",Driver.getDriverAFM(),
        " Driver's PLateNumber: ",Driver.getPlateNumber());
    }

    //we will pass as parameters the search criteria ,First parameter the status we search, and second parameter the instance of the order

    public static Drivers  SelectDriver(String Type){
        Drivers Selecteddriver;
        System.out.println("Only Drivers That Support the elivery Method are shown and able to get selected!");
        System.out.printf("%-5s %-17s %-20s %-30s %-20s %-19s %-10s %-20s%n ", "ID","Name", "lastName", "Adress", "Email", "AFM","Plate Number","Orders Type");
        for(Drivers driver:driverList){
            if(driver.getType().equals(Type) || driver.getType().equals(Constants.HOMEANDLOCKER)){
                System.out.printf("%-5d %-17s %-20s %-30s %-20s %-19d %-10s %-20s \n",driver.getDriverid(),driver.getName(),driver.getSurname(),driver.getAdress(),driver.getEmail(),driver.getDriverAFM(),driver.getPlateNumber(),driver.getType());
            }
        }

        while (true) {
            String DriversFullName = UserInterface.InputTypeStringWithSpace("Select The Driver by typing the full name as Shown Above");
            boolean Exists = CheckDriverExists(DriversFullName);
            Selecteddriver = GetCurrentDriverByFullName(DriversFullName);
            if(Exists && (Selecteddriver.getType().equals(Type)||Selecteddriver.getType().equals(Constants.HOMEANDLOCKER))){
                System.out.println("The Driver Succesfuly Selected");
                break;
            }else if(!Exists){
                System.out.println("The Driver Does not Exist");
                System.out.println("Select driver again");
            }else{
                System.out.println("The Driver you selected Does not Support this Type of Delivery");
            }

            
        }
        return Selecteddriver;
    }

    public static boolean DriverByTypeExists(String Type){
        boolean isValid = false;
        for(Drivers driver:driverList){
            if (driver.getType().equals(Type)||driver.getType().equals(Constants.HOMEANDLOCKER)){
                isValid = true;
                break;
            }
        }return isValid;
    }   

    public static Integer getCounterByType(String Type){
        Integer Counter = 0;
        for(Drivers driver:driverList){
            if (driver.getType().equals(Type)||driver.getType().equals(Constants.HOMEANDLOCKER)){
                Counter+=1;

            }
        }return Counter;
    }   
}