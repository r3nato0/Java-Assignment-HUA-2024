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



    public static List<Costumers> GetCostumersList(){
        return CostumersList;
    }




        // Method to print Products table
    public static void printTableCostumers(List<Costumers> costumersList) {
            // Printing table header
            System.out.printf("%-5s %-17s %-20s %-30s %-20s%n ", "ID","Name", "lastName", "Adress", "Email");
    
            // Printing each product row
            for (int i = 0; i < costumersList.size(); i++) {
                Costumers costumer = costumersList.get(i);
                System.out.printf("%-5d %-17s %-20s %-30s %-20s%n",costumer.getCostumerid(),costumer.getName(),costumer.getSurname(),costumer.getAdress(),costumer.getEmail());
            }
        }

    
    public static void AddNew(List<Costumers> CostumersList,String name, String surname, String address, String email) {
            CostumersList.add(new Costumers (name,surname,address,email ));
        }
}
