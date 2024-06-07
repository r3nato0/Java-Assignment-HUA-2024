public class Drivers extends  Person{
    private static int nextId = 1;
    private int Driverid;
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


    public String getType(){
        return this.Type;
    }

    public void getType(String Type){
        this.Type = Type;
    }

    
    public String getName(){
        return super.getName();
    }

    public String getDriverFullName(){
        return super.getName()+ " " + super.getSurname();
    }

    public String getSurname(){
        return super.getSurname();
    }
    public String getEmail(){
        return super.getEmail();
    }
    public String getAddress() {
        return super.getAdress(); 
    }

    public void setName(String Name){
        super.setName(Name);
    }

    public void setSurname(String Surname){
        super.setSurname(Surname);
    }

    public void setEmail(String Email){
        super.setEmail(Email);
    }
    public void setAddress(String Address){
        super.setAdress(Address);
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
