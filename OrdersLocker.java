

public class OrdersLocker extends Orders{
    private Integer CompartmentNumber;
    private String address;
    private Lockers locker;
    private EachCompartmentOfLockers compartment;

    public OrdersLocker(Costumers costumer, Drivers Driver,Lockers locker,EachCompartmentOfLockers compartment) {
        super(costumer, Driver);
        this.address = locker.getAddress();
        this.locker = locker;
        this.CompartmentNumber = compartment.getLockerNumber();
        this.compartment =compartment;
        compartment.setStatusUnavailable();
        locker.setminusOneSpace();
        compartment.setOrderId(super.getOrderId()); 
    
    }



    public EachCompartmentOfLockers getCompartment(){
        return this.compartment;
    }

    public void LockerAddSpace(){
        locker.setAddOneSpace();
    }


    public void setCompartmentAvailable(){
        compartment.setStatusAvailable();
    }


    public Integer getCompartmentNumber() {
        return this.CompartmentNumber;
    }

    public void setCompartmentNumber(Integer CompartmentNumber) {
        this.CompartmentNumber = CompartmentNumber;
    }

    public String getAddress() {
        return this.address;
    }

    public Lockers getLocker() {
        return this.locker;
    }
    public void setLocker(Lockers locker) {
        this.locker=locker;
    }
    public void setAddress(String Address) {
        this.address = Address;
    }




}