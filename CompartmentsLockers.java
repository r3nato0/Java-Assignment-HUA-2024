import java.util.ArrayList;


public class CompartmentsLockers {
    private ArrayList<Integer> compartmentsOfLockers;

    public CompartmentsLockers(ArrayList<Integer> spaces, ArrayList<Integer> unavailableSpaces) {
        this.compartmentsOfLockers = new ArrayList<>();

        for (int i = 1; i <= spaces.size(); i++) {
            if (unavailableSpaces.contains(i)) {
                compartmentsOfLockers.add(-1); // Unavailable from the start
            } else if (spaces.contains(i)) {
                compartmentsOfLockers.add(1); // Empty and available
            } else {
                compartmentsOfLockers.add(0); // To be shipped or has orders inside
            }
        }
    }





    public ArrayList<Integer> getCompartmentsLockers(){
        return this.compartmentsOfLockers;
    }






}