import java.util.ArrayList;
import java.util.List;


public class Lockers {
    private String address;
    private List<String> status = new ArrayList<>(List.of("Unavailable","Free"));
    private List<EachCompartmentOfLockers> compartmentsOfLocker;





    public Lockers(String address, int Spaces) {
        this.address = address;
        this.compartmentsOfLocker = new ArrayList<>();
        for(int i=1;i<=Spaces;i++){
            this.compartmentsOfLocker.add(new EachCompartmentOfLockers(this, status.get(1), i));
        }

    }

    public String getAddress(){
        return this.address;
    }

    public List<EachCompartmentOfLockers> getCompartmentsOfLocker() {
        return this.compartmentsOfLocker;
    }


}

