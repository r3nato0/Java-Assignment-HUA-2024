import java.util.ArrayList;
import java.util.List;
public class Drivers extends  Person{
    private static int nextId = 1;
    private int Driverid;
    private int DriverAFM;
    private String PlateNumber;
    private static List<Drivers> driverList = new ArrayList<>();

    public Drivers(String Name,String Surname,String Adress,String Email,int DriverAFM , String PlateNumber){
        super(Name,Surname,Adress,Email);
        this.Driverid = nextId++;
        this.DriverAFM = DriverAFM;
        this.PlateNumber= PlateNumber;
        
    }

    //Getters
    public int getDriverAFM() {
        return this.DriverAFM;
    }

    public String getPlateNumber() {
        return this.PlateNumber;
    }
    public int getDriverid() {
        return this.Driverid;
    }
    public static List<Drivers> GetDriverList() {
        return driverList;
    }
    
    //Setters
    public void setDriverAFM(int DriverAFM) {
        this.DriverAFM = DriverAFM;
    }
    public void setPlateNumber(String PlateNumber) {
        this.PlateNumber = PlateNumber;
    }
    public void setDriverid(int Driverid) {
        this.Driverid = Driverid;
    }

    public static void printTableDrivers(List<Drivers> driverList) {
        // Printing table header
        System.out.printf("%-5s %-17s %-20s %-30s %-20s %-19s %-10s%n ", "ID","Name", "lastName", "Adress", "Email", "AFM","Plate Number");
    
        // Printing each product row
        for (int i = 0; i < driverList.size(); i++) {
            Drivers drivers = driverList.get(i);
            System.out.printf("%-5d %-17s %-20s %-30s %-20s %-19d %-10s%n",drivers.getDriverid(),drivers.getName(),drivers.getSurname(),drivers.getAdress(),drivers.getEmail(),drivers.getDriverAFM(),drivers.getPlateNumber());
        }
    }

    public static void CreateDefaultDrivers(List<Drivers> driverList){
        driverList.add(new Drivers("Renato","Nake","Aglaurou 10","nakerenato@gmail.com",16215021,"YMN4946"));
        driverList.add(new Drivers("George","Papadopoylos","dimitrakopoulou 105","georgepapadopoylos@gmail.com",13231021,"IZN5236"));
        driverList.add(new Drivers("Anthoni","Tsouklas","Hrakleous 70","ATsouklas@gmail.com",17231021,"IZN5236"));
    }
    public static boolean CheckDriverExists(List<Drivers> driverList,String DriverName,String DriverLastName){
        boolean isValid = false;
    
        for (Drivers driver : driverList) {
            if (DriverName.equals(driver.getName()) && DriverLastName.equals(driver.getSurname()) ) {
                isValid = true;
                
                break;
            }
        }
    
        return isValid;
    }

    public static Drivers GetDriver(List<Drivers> driverList, String drivername){
        //this method will return the object in case that object has the "data" parametere as data in its fields
        for(Drivers driver : driverList){
            if (drivername.equals(driver.getName())){
                return driver;
            }
            }
        return null;
        }



    public static void AddNew(List<Drivers> driverList,String name, String surname, String address, String email,Integer DriverAFM,String PlateNumber) {
        driverList.add(new Drivers(name,surname,address,email,DriverAFM,PlateNumber));
    }

}
