

public class EachCompartmentOfLockers {
    private Integer nextid = 1;
    private Integer CompartementId;
    private Lockers locker;
    private String status;
    private Integer LockerNumber;
    private Integer OrderId; // just adding this for best practise, usually you need a reference of the order that is holding or is going to get shipped.

    public EachCompartmentOfLockers(Lockers locker,String status,Integer LockerNumber ) {
        this.CompartementId = nextid++;
        this.locker = locker;
        this.status = status;
        this.LockerNumber = LockerNumber;
        this.OrderId = null;
    }


    public Integer getOrderId(){
        return this.OrderId;
    }

    public void setOrderId(Integer OrderId){
         this.OrderId=OrderId;
    }
    
    public Integer getCompartementId(){
        return this.CompartementId;
    }

    public void setCompartementId(Integer CompartementId){
         this.CompartementId=CompartementId;
    }
    public Lockers getLocker() {
        return this.locker;
    }

    public void setLocker(Lockers locker) {
        this.locker = locker;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatusUnavailable() {
        this.status = "Unavailable";
    }

    public void setStatusAvailable() {
        this.status = "Free";
    }

    public Integer getLockerNumber() {
        return this.LockerNumber;
    }

    public void setLockerNumber(Integer LockerNumber) {
        this.LockerNumber = LockerNumber;
    }

}