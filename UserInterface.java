import java.sql.Driver;
import java.util.List;
import java.util.Scanner;
// rerourcses used : 
// https://www.javatpoint.com/java-int-to-string
// https://www.javatpoint.com/java-string-length
//https://docs.oracle.com/javase/8/docs/api/java/lang/String.html
//no specific part of code has beeing copied , onlt the methods are beeing used 
public class UserInterface{



    public static String StringInput(String Message) {
        Scanner scanner = new Scanner(System.in);
        String UserInput = "";
    
        while (true) {
            System.out.printf("%s",Message);
            UserInput = scanner.nextLine();
    
            boolean isValid = true; 
            for (int i = 0; i < UserInput.length(); i++) {
                char ch = UserInput.charAt(i);
                if (!Character.isLetter(ch)) {
                    isValid = false;
                    break;
                }
            }
    
            if (isValid && !UserInput.isEmpty()) {
                break;
            } else {
                System.out.println("Please only enter Characters.");
            }
        }
    
    
        System.out.println(UserInput);

        return UserInput;
    }

    public static Integer InputTypeAFM(String Message){
        Scanner scanner = new Scanner(System.in);

        String StringNumber ;
        System.out.printf("%s",Message);
        while(true){
            boolean isValid = false;
            StringNumber = scanner.nextLine();
            if( StringNumber.length()==8){
                for (int i=0; i < StringNumber.length();i++){
                    char c = StringNumber.charAt(i);
                    if(Character.isDigit(c)){
                        isValid=true;
                    }
                    else{
                        break;
                    }
                }
            }
            if (isValid){
                return Integer.parseInt(StringNumber);
            }
    }
}

    public static String InputTypePlateNumber(String Message){
    
        Scanner scanner = new Scanner(System.in);
       
        System.out.println(Message);
        while (true) {
            String PlateNumber = scanner.nextLine();
            System.out.println(PlateNumber.length());
            boolean isValidLetters=false;
            boolean isValidNumbers=false;
            Integer CharsPart = 2;
            Integer NumsPart = 6;

            if (PlateNumber.length() == 7){
                for (int i =0,j =3;(i <= CharsPart && j <= NumsPart);i++,j++){
                    if(PlateNumber.charAt(i) >='A' && PlateNumber.charAt(i) <='Z'){
                        isValidLetters=true;
                    }
                    else{
                        isValidLetters=false;
                        break;
                    }
                    if(PlateNumber.charAt(j) >= '0' && PlateNumber.charAt(j) <= '9'){
                        isValidNumbers=true;
                    }
                    else{
                        isValidNumbers=false;
                        break;
                        
                    }
                }
            }

            if(isValidLetters && isValidNumbers){
                return PlateNumber;
            }else{
                System.out.println("Please kindly provide a correct Plate Number example: SSSNNNN where S=LETTER AND N=number: ");
            }
        }
    }

    public static String InputTypeStringWithSpace(String Message){ 

        Scanner scanner = new Scanner(System.in);
        String FirstPart="";
        String SecodPart="";
        System.out.printf("%s",Message);
        while (true) {
            String Input = scanner.nextLine();
            Integer SpacePos = Input.indexOf(" ");

            if ((Input.length()>=3) && (SpacePos!=-1) && (SpacePos< Input.length()-1)){
                FirstPart = Input.substring(0, SpacePos);
                SecodPart = Input.substring(SpacePos+1,Input.length());
            }
            if (FirstPart.matches("[a-zA-Z]+") && SecodPart.matches("[a-zA-Z]+") ) {
                return Input;
            } 
        } 
    }

    public static Integer InputTypeNumberSingle(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        while (true) {
            String input = scanner.nextLine();
            if (input.length() == 1) {
                char ch = input.charAt(0);
                if (ch >= '0' && ch <= '9') {
                    return Integer.parseInt(input);
                } else {
                    System.out.println("Please enter a single digit number:");
                }
            } else {
                System.out.println("Please enter a single digit number:");
            }
        }
    }

    public static String InputTypeEmail(String Message) {
        Scanner scanner = new Scanner(System.in);

        System.err.printf("%s", Message);

        while (true) {
            String Email = scanner.nextLine();
            
            // Check for invalid sequences
            boolean IsValidSequence = (
                !Email.contains("@.") &&
                !Email.contains("..") &&
                !Email.contains(".@") &&
                !Email.startsWith(".") &&
                !Email.startsWith("@") &&
                !Email.endsWith(".") &&
                !Email.endsWith("@")
            );

            // Check if email contains exactly one @
            int atPosition = Email.indexOf('@');
            boolean validat = atPosition != -1 && Email.indexOf('@', atPosition + 1) == -1;

            // Check if email contains at least one dot after the @
            boolean ValiddotPOS = atPosition != -1 && Email.indexOf('.', atPosition + 1) != -1;

            // If all conditions are met
            if (IsValidSequence && ValiddotPOS && validat) {
                return Email;
            } else {
                System.err.printf("Invalid email address. Please enter again:  ");
            }
        }
    }

    public static String InputTypeAdress(String Message){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(Message);
            String Input = scanner.nextLine();
            String StreetAdress = "" ;
            String StreetNumber = "";
            boolean isValidStreetAdress = false;
            boolean isValidSStreetNumber = false;
            Integer Separator = Input.indexOf(" ") ;
            if ((Input.length() >= 3) && (Separator != -1) && (Separator < Input.length()-1)){
                StreetAdress = Input.substring(0, Separator);
                StreetNumber = Input.substring(Separator+1, Input.length());
            }
            if (StreetAdress.matches("[a-zA-Z]+")) {
                isValidStreetAdress = true;

            } 
            for(int i =0 ; i < StreetNumber.length();i++){
                char c = StreetNumber.charAt(i);
                if(Character.isDigit(c)){
                    isValidSStreetNumber=true;

                } 
                else{
                    break;
                }
            }
            if(isValidSStreetNumber&& isValidStreetAdress){
                return Input;
            }
        }
    }

    public static boolean InputTypeBoolean(String Message){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(Message);
            String Input = scanner.nextLine(); 
            if (Input.equals("yes") || Input.equals("Yes") || Input.equals("YES") || Input.equals("y") || Input.equals("Y")){
                return true;
            }
            if (Input.equals("no") || Input.equals("No") || Input.equals("NO") || Input.equals("n")){
                return false;
            }
        }
    }
    public static Integer InputTypeLockerORHome(String Message){
        Scanner scanner = new Scanner(System.in);
        System.out.println(Message);
        while (true) {
            String input = scanner.nextLine();
            if (input.length() == 1) {
                char c = input.charAt(0);
                if(c=='1'){
                    return 1;
                }
                if(c=='2'){
                    return 2;
                }
            } else {
                System.out.println("Please enter (1) For Costumers Home Address Or (2) For A locker Delivery:");
            }
        }
    }
    public static String PrintOnly(String str, int maxLength) {
        if (str == null) {
            return null;
        }
        if (str.length() > maxLength) {
            return str.substring(0, maxLength) + "...";
        } else {
            return str;
        }
    }

    //all characters are alowed
    public static String InputTypeAllCharactersAllowed(String Message){
        System.out.println(Message);
        Scanner scanner = new Scanner(System.in);
        String ProductName = scanner.nextLine();
        return ProductName;

    }

    public static  Integer InputTypeProductCategory(List<String> CategoryList){
        Integer number=null;
        Scanner scanner = new Scanner(System.in);
        for(int i =1 ; i <= CategoryList.size();i++){
            System.out.println("Press " + i + " To seelect Category: " + CategoryList.get(i-1) );
        }
        boolean isNumb = true;
        while (true) {

        String Selection = scanner.nextLine();   
            if(!Selection.isEmpty()){
                try {
                    number = Integer.parseInt(Selection);
                    if(number>=1 && number<=CategoryList.size()){
                        isNumb=true;

                    }
                    else{
                        isNumb=false;
                        System.out.println("please provide a number from 1 until " + CategoryList.size());
                    }
                } catch (NumberFormatException e) {
                    System.out.println("please provide a number from 1 until " + CategoryList.size());
                    isNumb=false;
                }
            }else{
                System.out.println("please provide a number from 1 until " + CategoryList.size());
            }
            if(isNumb){
                return number;
            }
        }   

    }
    public static Integer InputTypeIntegerNoLimit(String Message){
        Integer number=null;
        Scanner scanner = new Scanner(System.in);
        System.out.println(Message);
        boolean isNumb = false;
        while (true) {
            String input = scanner.nextLine();
            if(!input.isEmpty()){
                try {
                    number = Integer.parseInt(input);
                    if(number > 0){
                        isNumb=true;
                    }
                } catch (NumberFormatException e) {
                    isNumb=false;
                    System.out.println("Only numbers allowed, try again: ");
                }
            }
            if(isNumb){
                break;
            }
        }
        return number;
    }

    public static Integer SelectAction(Integer Max){
        Scanner scanner = new Scanner(System.in);
        Integer SelectedOption;

    
        while (true) {
            
            String StringNumber = scanner.nextLine();
            try {
                SelectedOption = Integer.parseInt(StringNumber);
                if(SelectedOption > 1 && SelectedOption<=Max){

                    break;
                }
            } catch (NumberFormatException e) {

                System.out.println("Only numbers allowed, try again: ");
            }
        }
        return SelectedOption;

    }


    public static void ShowMenu(){
        System.out.println("Choose by typing number");
        System.out.println("1) Add New Driver");
        System.out.println("2) Add New Customer");
        System.out.println("3) Add new Locker");
        System.out.println("4) Add new Order");
        System.out.println("5)-Admin- Change Order's Driver ");
        System.out.println("6) -Customer- Change Order's Address(Only HomeOrders)");
        System.out.println("7) Finish an Order");
        System.out.println("8) Leave an Review on the order");
        System.out.println("9)Show Order's Details By ID Or Costumer's Name");
        System.out.println("10)Show Order's Details With Product's Category and Barcode");
        System.out.println("11)Show All Orders By Driver and Address(Lockers or HomeAddress)");
        System.out.println("12)Show Average reviews of Delivery System, And Each Costumer's Lowest and Highest Review rating");
        Integer Action = SelectAction(7);
        switch (Action) {
            case 1:
                DriverManager.AddNewDriver();
                ShowMenu();
                break;
            case 2:
                CostumerManager.AddNewCostumer();
                ShowMenu();
                break;
            case 3:
                LockerManager.AddNewLocker();
                ShowMenu();
                break;
            case 4:
                OrderManager.Create();
                ShowMenu();
                break;
            case 5:
                OrderManager.ChangeDriver();
                ShowMenu();
                break;
            case 6:
                OrderManager.ChangeOrdersAddress();
                break;
            case 7:
                OrderManager.CompleteOrder();
                break;
            // case 8:
            //     OrderManager.LeaveReview();
            //     break;
            // case 9:
            //     OrderManager.ShowOrdersDetailsByIdandCostumer();
            //     break;
            // case 10:
            //     OrderManager.ShowOrdersByProduct();
            //     break;
            // case 11:
            //     OrderManager.ShowAllOrdersByDriverAddress();
            //     break;
            // case 12:
            //    OrderManager.ShowAverageReviews();
            //     break;


            default:
                break;
        }
    }
}

