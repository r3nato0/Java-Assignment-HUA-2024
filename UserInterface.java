import java.sql.Driver;
import java.util.List;
import java.util.Scanner;
// rerourcses used : 
// https://www.javatpoint.com/java-int-to-string
// https://www.javatpoint.com/java-string-length
//https://docs.oracle.com/javase/8/docs/api/java/lang/String.html
//no specific part of code has beeing copied , onlt the methods are beeing used 
public class UserInterface{


    // gets a string from the user, it checks if is a character and also trims the input
    public static String StringInput(String Message) {
        Scanner scanner = new Scanner(System.in);
        String UserInput = ""; 
        
        while (true) {
            System.out.printf("%s",Message);
            UserInput = scanner.nextLine().trim();
    
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

        return UserInput;
    }



    public static Integer InputTypeAFM(String Message){
        Scanner scanner = new Scanner(System.in);

        String StringNumber ;
        while(true){
            System.out.printf("%s",Message);
            boolean isValid = false;
            StringNumber = scanner.nextLine().trim();
            if( StringNumber.length()==8){
                //we check if it has a lenght of 8(the length that the number should be)
                //and also if every number is a digit
                //if not it breaks from the while loop
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
            }else{//in case it broke from the while loop and the input is invalid it will print the bellow message
                System.out.println("Invalid Input");
            }
    }
}

    public static String InputTypePlateNumber(String Message){
    
        Scanner scanner = new Scanner(System.in);
       
        System.out.printf("%s",Message);
        while (true) {
            String PlateNumber = scanner.nextLine().trim(); //triming any whitespaces at the strat or the end of the input
            boolean isValidLetters=false;
            boolean isValidNumbers=false;
            Integer CharsPart = 2;
            Integer NumsPart = 6;

            if (PlateNumber.length() == 7){
                //if there are 7 characters the first 3 are letters and the other are nummbers the boolean values will become true and at the end then
                // if will break the while loop returning a formated input
                for (int i =0,j =3;(i <= CharsPart && j <= NumsPart);i++,j++){
                    if(((PlateNumber.charAt(i) >='a' && PlateNumber.charAt(i) <='z') ||
                        (PlateNumber.charAt(i) >='A' && PlateNumber.charAt(i) <='Z')) &&
                        (PlateNumber.charAt(j) >= '0' && PlateNumber.charAt(j) <= '9'))
                     {
                        isValidLetters=true;
                        isValidNumbers=true;
                    }
                    else{
                        isValidLetters=false;
                        isValidNumbers=false;
                        break;
                    }
                    }
                }

            if(isValidLetters && isValidNumbers){
                //will format the input so the letters are returned in uppercase, it does not matter in any way for comparing strings later, since we use .equalsignorecase, but its a better aproach
                String formatplatenumber = PlateNumber.substring(0, CharsPart+1).toUpperCase()+PlateNumber.substring(CharsPart+1, PlateNumber.length());
                System.out.println(formatplatenumber);
                return formatplatenumber;
            }else{
                //user did not provide a valid platenumber
                System.out.println("Please kindly provide a correct Plate Number example: ( 'YMZ4946' 'ymz4946') ");
            }
        }
    }

    public static String InputTypeStringWithSpace(String Message){ 
        //will usually be used for a full name String value
        Scanner scanner = new Scanner(System.in);
        String FirstPart="";
        String SecodPart="";
        String formatedSecondpart="";
        //first part is the costumers name
        //second part is the persons surname
        while (true) {
            System.out.printf("%s",Message);
            String Input = scanner.nextLine().trim();
            Integer SpacePos = Input.indexOf(" ");//we get the index of the single space character

            if ((Input.length()>=3) && (SpacePos!=-1) && (SpacePos< Input.length()-1)){
                FirstPart = Input.substring(0, SpacePos);
                //we get the first part without the space
                SecodPart = Input.substring(SpacePos+1,Input.length());
                // we get the second part(remainder of the Input) and we trim any remaining whitespace character
                formatedSecondpart=SecodPart.trim();
            }
            //[a-zA-Z] checks if the value is from a-z or A-Z 
            //https://stackoverflow.com/questions/26722496/regex-difference-between-a-za-z-vs-a-za-z
            //source that i found this out is above
            
            if (FirstPart.matches("[a-zA-Z]+") && formatedSecondpart.matches("[a-zA-Z]+") ) {
                //we format the name again, since we have removed any space characted and return the firstname part and the second name part that is trimmed
                return FirstPart+" "+formatedSecondpart;
            } else{
                System.out.println("Invalid Input, try again");
            }
        } 
    }

    //we get a number from the user, also prints the message
    public static Integer InputTypeNumber(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.length()!=0) {
                char ch = input.charAt(0);
                if (ch >= '0' && ch <= '9') {
                    return Integer.parseInt(input);
                } else {
                    System.out.println("Invalid Input try again:");
                }
            } else {
                System.out.println("Invalid Input try again:");
            }
        }
    }

    public static String InputTypeEmail(String Message) {
        Scanner scanner = new Scanner(System.in);

        System.out.printf("%s", Message);
        while (true) {
            String Email = scanner.nextLine().trim();

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
                return Email;}
            else {
              System.out.printf("Invalid email address. Please enter again: ");
            }
          }
    }

    public static String InputTypeAdress(String Message){
        // i will get an String from the user, then i will get the position of the first number (street number)
        //i will separate the streen number and the street name
        // check if they are valid (no characters and no string in the number)
        //then i will return the input separated by a space
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.printf("%s",Message);
            String Input = scanner.nextLine().trim();
            String FormatedInput="";
            String StreetName="";
            String StreetNumber="";
            Integer pos=-1;
            boolean ValidName=false;
            boolean ValidNumber=false;
            Integer StreetNumberformated=null;

            // in case user gave a address separating the parts with space, we will remove the space and get address while also triming any access whitespaces
            if(Input.indexOf(" ")!=-1){
                FormatedInput =Input.substring(0, Input.indexOf(" ")) + Input.substring(Input.indexOf(" ")+1, Input.length()).trim();
            }else{
                FormatedInput=Input;// the user did not give any space
            }

            // we check if there is any number
            for(int i =0;i<FormatedInput.length();i++)
            {
                if(Character.isDigit(FormatedInput.charAt(i))){
                    pos=i;
                    break;//we captured the index and break
                }
            }


            //if the user has given a streenumber we separate the parts
            if(pos!=-1)
        {
            //separate the parts
            StreetName = FormatedInput.substring(0, pos);
            StreetNumber = FormatedInput.substring(pos, FormatedInput.length());
            try {
                    StreetNumberformated = Integer.parseInt(StreetNumber);
                    ValidNumber=true;
            } catch (NumberFormatException e)
            {
                    //Nothing to do//
            }
        }



            if(StreetName.matches("[a-zA-Z]+"))
            {
                    ValidName=true;
            }

            if(ValidName && ValidNumber && pos!=-1)
            {

                return StreetName + " "+ String.valueOf(StreetNumberformated); //we return the address with a space between
            }
            else{
                System.out.println("Invalid address, try again: (example aglaurou 13 or aglarou13)");
            }
        }
    }
    

    //returns a boolean value
    public static boolean InputTypeBoolean(String Message){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(Message);
            String Input = scanner.nextLine().trim(); 
            if (Input.equalsIgnoreCase("yes") || Input.equalsIgnoreCase("Yes") || Input.equalsIgnoreCase("YES") || Input.equalsIgnoreCase("y") || Input.equalsIgnoreCase("Y")){
                return true;
            }
            if (Input.equalsIgnoreCase("no") || Input.equalsIgnoreCase("No") || Input.equalsIgnoreCase("NO") || Input.equalsIgnoreCase("n")){
                return false;
            }
        }
    }

    //is used for the user to select a locker or home delivery, and then returns the string of the type
    public static String InputTypeLockerORHome(String Message){
        Scanner scanner = new Scanner(System.in);
        System.out.println(Message);
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.length() == 1) {
                char c = input.charAt(0);
                if(c=='1'){
                    return Constants.DELIVERYHOME;
                }
                else if(c=='2'){
                    return Constants.LOCKERDELIVERY;
                }
            } else {
                System.out.println("Please enter (1) For Costumers Home Address Or (2) For A locker Delivery:");
            }
        }
    }


    // a small method that will format the String based on the maxlenght provided and also adding 3 dots, is used when displaying the product names
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

    //all characters are alowed, is used for named the product
    public static String InputTypeAllCharactersAllowed(String Message){
        System.out.println(Message);
        Scanner scanner = new Scanner(System.in);
        String ProductName = scanner.nextLine().trim();
        return ProductName;

    }


    //this merhod helps when creating a new product. selecting the category based on the list
    public static  Integer InputTypeProductCategory(List<String> CategoryList){
        Integer number=null;
        Scanner scanner = new Scanner(System.in);
        for(int i =1 ; i <= CategoryList.size();i++){
            System.out.println("Press " + i + " To seelect Category: " + CategoryList.get(i-1) );
        }
        boolean isNumb = true;
        while (true) {

        String Selection = scanner.nextLine().trim();   
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


    // no limit for the inteder, will help adding the quantity of the new product created
    public static Integer InputTypeIntegerNoLimit(String Message){
        Integer number=null;
        Scanner scanner = new Scanner(System.in);
        System.out.println(Message);
        boolean isNumb = false;
        while (true) {
            String input = scanner.nextLine().trim();
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


    //the user is promted to select a numer with max value to the one passed in 
    public static Integer SelectNumber(Integer Max){
        Scanner scanner = new Scanner(System.in);
        Integer SelectedOption;
        while (true) {
            
            String StringNumber = scanner.nextLine().trim();
            try {//error handling as the other methods
                SelectedOption = Integer.parseInt(StringNumber);
                if(SelectedOption > 0 && SelectedOption<=Max){

                    break;
                }else{
                    System.out.println("Please Input a valid value");
                }
            } catch (NumberFormatException e) {

                System.out.println("Only numbers allowed, try again: ");
            }
        }
        return SelectedOption;

    }

    // The menu method
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
        Integer Action = SelectNumber(12);
        switch (Action) {
            case 1:
                DriverManager.AddNewDriver();
                pressEnterKeyToContinue();
                ShowMenu(); // recursion call
                break;
            case 2:
                CostumerManager.AddNewCostumer();
                pressEnterKeyToContinue();
                ShowMenu();
                break;
            case 3:
                LockerManager.AddNewLocker();
                pressEnterKeyToContinue();
                ShowMenu();
                break;
            case 4:
                OrderManager.CreateSecond();
                pressEnterKeyToContinue();
                ShowMenu();
                break;
            case 5:
                OrderManager.ChangeDriver();
                pressEnterKeyToContinue();
                ShowMenu();
                break;
            case 6:
                OrderManager.ChangeOrdersAddress();
                pressEnterKeyToContinue();
                ShowMenu();
                break;
            case 7:
                OrderManager.CompleteOrder();
                pressEnterKeyToContinue();
                ShowMenu();
                break;
            case 8:
                OrderManager.LeaveReview();
                pressEnterKeyToContinue();
                ShowMenu();
                break;
            case 9:
                OrderManager.ShowOrdersDetailsByIdOrCostumer();
                pressEnterKeyToContinue();
                ShowMenu();
                break;
            case 10:
                OrderManager.ProductsBoughtSummary();
                pressEnterKeyToContinue();
                ShowMenu();
                break;
            case 11:
                OrderManager.ShowDriverOrdes();
                pressEnterKeyToContinue();
                ShowMenu();
                break;
            case 12:
               OrderManager.ShowAverageReviews();
               pressEnterKeyToContinue();
               ShowMenu();
                break;


            default:
                break;
        }
    }
    // helps by passing the run of the program
    public static void pressEnterKeyToContinue() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press Enter key to continue...");
        scanner.nextLine().trim();
    }
    
}

