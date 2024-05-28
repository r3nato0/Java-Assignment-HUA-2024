import java.util.Scanner;
// rerourcses used : 
// https://www.javatpoint.com/java-int-to-string
// https://www.javatpoint.com/java-string-length
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
        char[] nums = {'0','1','2','3','4','5','6','7','8','9'};
        String StringNumber ;
        System.out.printf("%s",Message);
        while(true){
            boolean isValid = false;


            StringNumber = scanner.nextLine();
            outerLoop:
            for (int i = 0; i < StringNumber.length()-1; i++) {
                for(int j =0; j <nums.length;j++){
                    System.out.println(StringNumber.charAt(i));
                    System.out.println(nums[j]);
                    if(StringNumber.charAt(i)==nums[j] ){
                        System.out.println("OKEYA");
                        isValid =true;
                        break outerLoop;

                    } else{
                        isValid =false;
                        System.out.printf("Please enter your 8-Digit tax code corectly: ");
 
                    }
                    
                }
            
            }   
         
        if (StringNumber.length() == 8 && isValid && !StringNumber.isEmpty()){
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

    public static String InputTypeStringWithSpaces(){
        return "hello";
    }

    public static Integer InputTypeNumber(){
        return 0;
    }
}
