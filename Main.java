import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create a new Locker
        Lockers newLocker = new Lockers("Example Address", 10, 3);

        // Get the compartments of the locker
        ArrayList<Integer> compartments = newLocker.getCompartmentsLockers();

        // Print the compartments
        System.out.println("Compartments of the locker:");
        for (int compartment : compartments) {
            System.out.println(compartment);
        }
    }
}