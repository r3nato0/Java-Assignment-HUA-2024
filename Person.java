public abstract class  Person{
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


    public abstract void setId(Integer id);

    public abstract Integer getId();
    
    public String getFullname(){
        return this.Name+ " " +this.Surname;
    }


    //Geters

    public String getName() {
        return this.Name;
    }
    public String getSurname() {
        return this.Surname;
    }
    public String getAddress() {
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
