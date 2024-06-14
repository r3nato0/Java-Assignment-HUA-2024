public class Drivers extends  Person{
    private static Integer nextId = 1;
    private Integer Driverid;
    private int DriverAFM;
    private String PlateNumber;
    private String Type;

    public Drivers(String Name,String Surname,String Address,String Email,int DriverAFM , String PlateNumber,String Type){ //pass 1,2 or 3 in Select and the type driver will be filled acordingly inside the
        super(Name,Surname,Address,Email);
        this.Driverid = nextId++;
        this.DriverAFM = DriverAFM;
        this.PlateNumber= PlateNumber;
        this.Type =Type;
    }



    @Override
    public Integer getId(){
        return this.Driverid;
    }

    @Override
    public void setId(Integer ID){
        this.Driverid = ID;
    }


    public String getType(){
        return this.Type;
    }

    public void setType(String Type){
        this.Type = Type;
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
