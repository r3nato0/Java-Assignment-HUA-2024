

public class EachCompartmentOfLockers {
    private Lockers locker;
    private String status;
    private Integer LockerNumber;


    public EachCompartmentOfLockers(Lockers locker,String status,Integer LockerNumber ) {
        this.locker = locker;
        this.status = status;
        this.LockerNumber = LockerNumber;
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

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getLockerNumber() {
        return this.LockerNumber;
    }

    public void setLockerNumber(Integer LockerNumber) {
        this.LockerNumber = LockerNumber;
    }

}