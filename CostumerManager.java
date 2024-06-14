
import java.util.ArrayList;
import java.util.List;
public class CostumerManager{
    private static List<Costumers> costumersList = new ArrayList<>();


    public static List<Costumers> getCostumersList(){
        return costumersList;
    }


    // the method will be run after the CheckCostumerExists method first gets completed.
    public static Costumers GetCurrentCostumerByFullName( String CostumerFullName){
        // the passed String in the parameters 
        Costumers SelectedCostumer = null;
        //gets split to first name and last name
        String FirstName= CostumerFullName.substring(0,CostumerFullName.indexOf(" "));
        String LastName= CostumerFullName.substring((CostumerFullName.indexOf(" ")+1),CostumerFullName.length());
        //get searched
        for(Costumers costumer : costumersList){
            if (FirstName.equalsIgnoreCase(costumer.getName()) && LastName.equalsIgnoreCase(costumer.getSurname()) ) {
                SelectedCostumer = costumer;
            }

        }// and then returned
        return SelectedCostumer;
    }
    

    
    public static boolean CheckCostumerExists(String CostumerFullName){
        boolean isValid=false;
        Integer SpacePos = CostumerFullName.indexOf(" ");
        String FirstName= CostumerFullName.substring(0, SpacePos);
        String LastName= CostumerFullName.substring(SpacePos+1, CostumerFullName.length());// we split the CostumerFullname to first name and lastname
        System.out.println(CostumerFullName);
        for(Costumers costumer : costumersList){// search in the least 
            if (FirstName.equalsIgnoreCase(costumer.getName()) && LastName.equalsIgnoreCase(costumer.getSurname()) ) {
                isValid=true;// it was found
                break;
            }

        }
        return isValid; // returns calue, if was not found from the above for loop, then the default value false is retunred.
    }


    // this is needed for when a user creates a new order, but the costumer was not found, then a new costumer is created, where the name is passed and filled without the need to get asked again
    public static void NotFoundAddNew(String CostumerFullName){
        Integer SpacePos = CostumerFullName.indexOf(" ");
        String CostumerFIrstName = CostumerFullName.substring(0, SpacePos);
        String CostumerLastName = CostumerFullName.substring(SpacePos+1, CostumerFullName.length());
        String address = UserInterface.InputTypeAdress("Type Costumers Adress: ");
        String Email = UserInterface.InputTypeEmail("Type Costumes Email: ");
        costumersList.add(new Costumers(CostumerFIrstName,CostumerLastName,address,Email));
    }


    // this is in case the user wants to add a new costumer from the main menu
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

    // created the default costumers, calues are stored in Constants class
    public static void CreateDefaultCostumers(){
        for (String[] CostumerData : Constants.CUSTOMER_DATA) {
            String fname = CostumerData[0];
            String lname = CostumerData[1];
            String address =CostumerData[2];
            String email=CostumerData[3];
            costumersList.add( new Costumers (fname,lname,address,email));
        }
        }


    public static void printTableCostumers() {
        if(costumersList.isEmpty()){// a small if statement that checkes if there are costumer, in there there are not , the header tag does not get printed empty
            System.out.println("There are no Costumers in the system yet!");
            return;
        }
        //header to be printed
        System.out.printf("%-5s %-17s %-20s %-30s %-20s%n ", "ID","Name", "lastName", "Adress", "Email");
        //loop to print every costuer in a specific way
        for (int i = 0; i < costumersList.size(); i++) {
            Costumers costumer = costumersList.get(i);
                System.out.printf("%-5d %-17s %-20s %-30s %-20s%n",costumer.getCostumerid(),costumer.getName(),costumer.getSurname(),costumer.getAddress(),costumer.getEmail());
            }
    }


    public static void AddNew(String name, String surname, String address, String email) {
        costumersList.add(new Costumers (name,surname,address,email ));
        }

        
    public static Integer getCostumersTotalOrders(Integer CostumersId){
        //small for loop that returns the total orders of the costumer, in case he has none, thee default value 0 will be returned
            Integer OrdersCounter=0;
                for (Orders order : OrderManager.GetAllOrdersList()){
                    if(order.getCostumerId()==CostumersId){
                        OrdersCounter++;
                    }
                }
            return OrdersCounter; }
}