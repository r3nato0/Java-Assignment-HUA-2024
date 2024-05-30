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
            Integer checker = 0;
            if (PlateNumber.length() == 7){
                for (int i =0,j =3;(i <= CharsPart && j <= NumsPart);i++,j++){
                    if(PlateNumber.charAt(i) >='A' && PlateNumber.charAt(i) <='Z'){
                        isValidLetters=true;
                        System.out.printf("%s","first if ",++checker);
                    }
                    else{
                        System.out.println("GOT BREAK HERE1");
                        isValidLetters=false;
                        break;
                    }
                    if(PlateNumber.charAt(j) >= '0' && PlateNumber.charAt(j) <= '9'){
                        isValidNumbers=true;
                        System.out.printf("%s","Second ");
                    }
                    else{
                        isValidNumbers=false;
                        System.out.println("GOT BREAK HERE2");
                        break;
                        
                    }
                }
            }
            System.out.println("it got here");
            if(isValidLetters && isValidNumbers){
                return PlateNumber;
            }
        }
    }

    public static String InputTypeStringWithSpace(String Message){ 

        Scanner scanner = new Scanner(System.in);
        String FirstPart="";
        String SecodPart="";
        while (true) {
            System.out.printf("%s",Message);
            String Input = scanner.nextLine();
            System.out.println(Message);
            Integer SpacePos = Input.indexOf(" ");

            if ((Input.length()>=3) && (SpacePos!=-1) && (SpacePos< Input.length()-1)){
                FirstPart = Input.substring(0, SpacePos);
                SecodPart = Input.substring(SpacePos+1,Input.length());
            }
            if (FirstPart.matches("[a-zA-Z]+") && SecodPart.matches("[a-zA-Z]+") ) {
                System.out.println(FirstPart);
                System.out.println(SecodPart);
                return Input;
            } 
        } 
    }

    public static Integer InputTypeNumber(String message) {
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
                System.out.println(StreetAdress);
                System.out.println(StreetNumber);
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
                if(c==1){
                    return 1;
                }
                if(c==2){
                    return 2;
                }
            } else {
                System.out.println("Please enter (1) For Costumers Home Address Or (2) For A locker Delivery:");
            }
        }
    }
}

