public class  Person{
    private String Name;
    private String Surname;
    private String Adress;
    private String Email;
    public Person(String Name,String Surname,String Adress,String Email){

        this.Name = Name;
        this.Surname = Surname;
        this.Adress = Adress;
        this.Email = Email;
    }
    //Geters

    public String getName() {
        return Name;
    }
    public String getSurname() {
        return Surname;
    }
    public String getAdress() {
        return Adress;
    }
    public String getEmail() {
        return Email;
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
