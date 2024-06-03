public class  Person{
    private static int nextId = 1;
    private int PersonId;
    private String Name;
    private String Surname;
    private String Adress;
    private String Email;


    public Person(String Name,String Surname,String Adress,String Email){
        this.PersonId=nextId++;
        this.Name = Name;
        this.Surname = Surname;
        this.Adress = Adress;
        this.Email = Email;
    }


    public void setPersonId(int PersonId){
        this.PersonId = PersonId;
    }
    public Integer getId() {
        return this.PersonId;
    }


    //Geters

    public String getName() {
        return this.Name;
    }
    public String getSurname() {
        return this.Surname;
    }
    public String getAdress() {
        return this.Adress;
    }
    public String getEmail() {
        return this.Email;
    }
    //Setters

    public void setName(String Name) {
        this.Name = Name;
    }
    public void setSurname(String Surname) {
        this.Surname = Surname;
    }
    public void setAdress(String Adress) {
        this.Adress = Adress;
    }
    public void setEmail(String Email) {
        this.Email = Email;
    }


}
