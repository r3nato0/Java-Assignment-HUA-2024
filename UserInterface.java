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
        scanner.close();
        return UserInput;
    }


    public static Integer IntegerInputForAFM(String Message){
        Scanner scanner = new Scanner(System.in);
        Integer Number ;

        while(true){
            boolean isValid = false;
            System.out.printf("%s",Message);

            Number = scanner.nextInt();
            String StringNumber = String.valueOf(Number);

            
            for (int i = 0; i < StringNumber.length(); i++) {
                int numericValue = Character.getNumericValue(StringNumber.charAt(i));
                if (numericValue >= 0 && numericValue <= 9) {
                    isValid = true;
                }

            }

        if (StringNumber.length() == 8 && isValid){
            break;
        }
    }

    scanner.close();

    return Number;
}
}