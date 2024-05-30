
import java.util.ArrayList;
import java.util.List;
public class CostumerManager{
    private static List<Costumers> costumersList = new ArrayList<>();




    public static Costumers GetCurrentCostumerByFullName( String CostumerFullName){
        Costumers SelectedCostumer = null;
        String FirstName= CostumerFullName.substring(0,CostumerFullName.indexOf(" "));
        String LastName= CostumerFullName.substring((CostumerFullName.indexOf(" ")+1),CostumerFullName.length());
        for(Costumers costumer : costumersList){
            if(costumer.getName()==FirstName && costumer.getSurname()==LastName){
                SelectedCostumer = costumer;
            }

        }
        return SelectedCostumer;
    }
    


    public static boolean CheckCostumerExists(String CostumerFullName){
        boolean isValid=false;
        String FirstName= CostumerFullName.substring(0,CostumerFullName.indexOf(" "));
        String LastName= CostumerFullName.substring((CostumerFullName.indexOf(" ")+1),CostumerFullName.length());
        for(Costumers costumer : costumersList){
            if(costumer.getName()==FirstName && costumer.getSurname()==LastName){
                isValid=true;
            }

        }
        return isValid;
    }



    public static void NotFoundAddNew(String CostumerFullName){
        Integer SpacePos = CostumerFullName.indexOf(" ");
        String DriverFirstName = CostumerFullName.substring(0, SpacePos);
        String DriverLastName = CostumerFullName.substring(SpacePos+1, CostumerFullName.length());
        System.out.printf("The Driver Name does not exist, continuing with the rest of the fields: ");
        String address = UserInterface.StringInput("Type Drivers Adress: ");
        String DriversEmail = UserInterface.StringInput("Type Drivers Email: ");
        Integer DriverSAFM =UserInterface.InputTypeAFM("Type Drivers AFM: ");
        String DriversPlateNumber = UserInterface.InputTypePlateNumber("Type Drivers PlateNumber: ");
        costumersList.add(new Costumers(DriverFirstName,DriverLastName,address,DriversEmail));
    }





    public static void CreateDefaultCostumers(){}{
        costumersList.add(new Costumers("Maria","Georgioy","Zionos 25","mariageorgioy@gmail.com"));
        costumersList.add(new Costumers("Izabela","Georgioy","Zionos 25","mariageorgioy@gmail.com"));
    }


    public static void printTableCostumers() {
        System.out.printf("%-5s %-17s %-20s %-30s %-20s%n ", "ID","Name", "lastName", "Adress", "Email");
        for (int i = 0; i < costumersList.size(); i++) {
            Costumers costumer = costumersList.get(i);
                System.out.printf("%-5d %-17s %-20s %-30s %-20s%n",costumer.getCostumerid(),costumer.getName(),costumer.getSurname(),costumer.getAdress(),costumer.getEmail());
            }
    }


    public static void AddNew(List<Costumers> CostumersList,String name, String surname, String address, String email) {
        CostumersList.add(new Costumers (name,surname,address,email ));
        }

}