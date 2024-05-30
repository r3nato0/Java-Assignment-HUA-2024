import java.util.ArrayList;
import java.util.List;
public class Costumers extends Person {
    private static int nextId = 1;
    private int Costumerid;
    private static List<Costumers> CostumersList = new ArrayList<>();

    public Costumers(String Name,String Surname,String Adress,String Email){
        super(Name,Surname,Adress,Email);
        this.Costumerid = nextId++;
    }




    public int getCostumerid() {
        return this.Costumerid;
    }

    public void setCostumerid(int Costumerid) {
        this.Costumerid = Costumerid;
    }

}
