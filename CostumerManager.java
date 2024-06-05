
import java.util.ArrayList;
import java.util.List;
public class CostumerManager{
    private static List<Costumers> costumersList = new ArrayList<>();

    public static Costumers GetCurrentCostumerByFullName( String CostumerFullName){
        Costumers SelectedCostumer = null;
        String FirstName= CostumerFullName.substring(0,CostumerFullName.indexOf(" "));
        String LastName= CostumerFullName.substring((CostumerFullName.indexOf(" ")+1),CostumerFullName.length());
        for(Costumers costumer : costumersList){
            if (FirstName.equals(costumer.getName()) && LastName.equals(costumer.getSurname()) ) {
                SelectedCostumer = costumer;
            }

        }
        return SelectedCostumer;
    }
    


    public static boolean CheckCostumerExists(String CostumerFullName){
        boolean isValid=false;
        Integer SpacePos = CostumerFullName.indexOf(" ");
        String FirstName= CostumerFullName.substring(0, SpacePos);
        String LastName= CostumerFullName.substring(SpacePos+1, CostumerFullName.length());
        System.out.println(CostumerFullName);
        for(Costumers costumer : costumersList){
            if (FirstName.equals(costumer.getName()) && LastName.equals(costumer.getSurname()) ) {
                isValid=true;
                break;
            }

        }
        return isValid;
    }



    public static void NotFoundAddNew(String CostumerFullName){
        Integer SpacePos = CostumerFullName.indexOf(" ");
        String CostumerFIrstName = CostumerFullName.substring(0, SpacePos);
        String CostumerLastName = CostumerFullName.substring(SpacePos+1, CostumerFullName.length());
        String address = UserInterface.InputTypeAdress("Type Costumers Adress: ");
        String Email = UserInterface.InputTypeEmail("Type Costumes Email: ");
        costumersList.add(new Costumers(CostumerFIrstName,CostumerLastName,address,Email));
    }
    public static void AddNewCostumer(){
        String CostumerFirstName = UserInterface.StringInput("Enter The First name of the Costumer: ");
        String CostumerLastName = UserInterface.StringInput("Enter The Last name of the Costumer: ");
        String address = UserInterface.InputTypeAdress("Type Costumers Adress: ");
        String Email = UserInterface.InputTypeEmail("Type Costumes Email: ");
        Costumers costumer = new Costumers(CostumerFirstName,CostumerLastName,address,Email);
        System.out.printf("%s\n %s %d\n %s %s\n%s %s\n %s %s\n%s %s\n","The Costumer Was Added Successfully",
        "Costumer ID: ",costumer.getId(),
        "Costumer First Name: ",costumer.getName(),
        " Costumer Last Name: ",costumer.getSurname(),
        "Costumer's Address: ",costumer.getAddress(),
        " Costumer's Email: ",costumer.getEmail());
    }


    public static void CreateDefaultCostumers(){
        costumersList.add(new Costumers("Maria","Papadopoyloy","Zionos 25","mariageorgioy@gmail.com"));
        costumersList.add(new Costumers("Izabel","Georgioy","Aglayrou 13","izabelageorgioy@gmail.com"));
    }


    public static void printTableCostumers() {
        System.out.printf("%-5s %-17s %-20s %-30s %-20s%n ", "ID","Name", "lastName", "Adress", "Email");
        for (int i = 0; i < costumersList.size(); i++) {
            Costumers costumer = costumersList.get(i);
                System.out.printf("%-5d %-17s %-20s %-30s %-20s%n",costumer.getCostumerid(),costumer.getName(),costumer.getSurname(),costumer.getAdress(),costumer.getEmail());
            }
    }


    public static void AddNew(String name, String surname, String address, String email) {
        costumersList.add(new Costumers (name,surname,address,email ));
        }

}