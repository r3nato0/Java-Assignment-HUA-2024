import java.util.ArrayList;


public class Lockers {
    private String address;
    private ArrayList<Integer> spaces;
    private ArrayList<Integer> unavailableSpaces;
    private CompartmentsLockers compartmentsLockers;

    public Lockers(String address, int spacesCount, int unavailableSpacesCount) {
        this.address = address;
        this.spaces = LockerManager.createNumberList(1, spacesCount);
        this.unavailableSpaces = LockerManager.createNumberList(1, unavailableSpacesCount);
        this.compartmentsLockers = new CompartmentsLockers(spaces, unavailableSpaces);
    }

    public String getAddress(){
        return this.address;
    }

    public ArrayList<Integer> getSpaces(){
        return this.spaces;
    }
    public ArrayList<Integer> getUnavailableSpaces(){
        return this.unavailableSpaces;
    }
    
    public ArrayList<Integer> getCompartmentsLockers(){
        return compartmentsLockers.getCompartmentsLockers();
    }
}

