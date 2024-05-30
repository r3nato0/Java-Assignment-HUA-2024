public class Costumers extends Person {
    private static int nextId = 1;
    private int Costumerid;


    public Costumers(String Name,String Surname,String Address,String Email){
        super(Name,Surname,Address,Email);
        this.Costumerid = nextId++;
    }


    public String getName(){
        return super.getName();
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

    public int getCostumerid() {
        return this.Costumerid;
    }

    public void setCostumerid(int Costumerid) {
        this.Costumerid = Costumerid;
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
}
