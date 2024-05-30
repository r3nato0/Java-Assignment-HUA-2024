public class Drivers extends  Person{
    private static int nextId = 1;
    private int Driverid;
    private int DriverAFM;
    private String PlateNumber;
    

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

}
