import java.util.ArrayList;
import java.util.List;
public class DriverManager{
    private static List<Drivers> driverList = new ArrayList<>();


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

    public static void AddNew(String name, String surname, String address, String email,Integer DriverAFM,String PlateNumber) {
            driverList.add(new Drivers(name,surname,address,email,DriverAFM,PlateNumber));
        }

    public static void CreateDefaultDrivers(){
        driverList.add(new Drivers("Renato","Nake","Aglaurou 10","nakerenato@gmail.com",16215021,"YMN4946"));
        driverList.add(new Drivers("George","Papadopoylos","dimitrakopoulou 105","georgepapadopoylos@gmail.com",13231021,"IZN5236"));
        driverList.add(new Drivers("Anthoni","Tsouklas","Hrakleous 70","ATsouklas@gmail.com",17231021,"IZN5236"));
        }

    public static void printTableDrivers() {
            // Printing table header
            System.out.printf("%-5s %-17s %-20s %-30s %-20s %-19s %-10s%n ", "ID","Name", "lastName", "Adress", "Email", "AFM","Plate Number");
        
            // Printing each product row
            for (int i = 0; i < driverList.size(); i++) {
                Drivers drivers = driverList.get(i);
                System.out.printf("%-5d %-17s %-20s %-30s %-20s %-19d %-10s%n",drivers.getDriverid(),drivers.getName(),drivers.getSurname(),drivers.getAdress(),drivers.getEmail(),drivers.getDriverAFM(),drivers.getPlateNumber());
            }
        }
    public static void NotFoundAddNew(String DriverFullName){
        Integer SpacePos = DriverFullName.indexOf(" ");
        String DriverFirstName = DriverFullName.substring(0, SpacePos);
        String DriverLastName = DriverFullName.substring(SpacePos+1, DriverFullName.length());
        System.out.printf("The Driver Name does not exist, continuing with the rest of the fields: ");
        String address = UserInterface.StringInput("Type Drivers Adress: ");
        String DriversEmail = UserInterface.StringInput("Type Drivers Email: ");
        Integer DriverSAFM =UserInterface.InputTypeAFM("Type Drivers AFM: ");
        String DriversPlateNumber = UserInterface.InputTypePlateNumber("Type Drivers PlateNumber: ");
        driverList.add(new Drivers(DriverFirstName,DriverLastName,address,DriversEmail,DriverSAFM,DriversPlateNumber));
    }
}