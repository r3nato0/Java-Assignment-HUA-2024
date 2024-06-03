import java.util.ArrayList;
import java.util.List;


public class Lockers {
    private Integer nextid = 1;
    private Integer LockerId;
    private String address;
    private List<String> status = new ArrayList<>(List.of("Unavailable","Free"));
    private List<EachCompartmentOfLockers> compartmentsOfLocker;
    private Integer Spaces;


    public Lockers(String address, int Spaces) {
        this.LockerId = nextid++;
        this.address = address;
        this.compartmentsOfLocker = new ArrayList<>();
        for(int i=1;i<=Spaces;i++){
            this.compartmentsOfLocker.add(new EachCompartmentOfLockers(this, status.get(1), i));
        }

    }


    public Integer getLockerId(){
        return this.LockerId;
    }

    public void setLockerId(Integer LockerId){
         this.LockerId=LockerId;
    }

    public String getAddress(){
        return this.address;
    }

    public List<EachCompartmentOfLockers> getCompartmentsOfLocker() {
        return this.compartmentsOfLocker;
    }

    public Integer getSpaces(){
        return this.Spaces;
    }

    public void setminusOneSpace(){
        this.Spaces = this.getSpaces() - 1;
    }
    public void setAddOneSpace(){
        this.Spaces = this.getSpaces() + 1;
    }
}

