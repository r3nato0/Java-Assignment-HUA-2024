

public class OrdersLocker extends Orders{
    private Integer CompartmentNumber;
    private String address;
    private Lockers locker;


    public OrdersLocker(Costumers costumer, Drivers Driver,Lockers locker,EachCompartmentOfLockers compartment) {
        super(costumer, Driver);
        this.address = locker.getAddress();
        this.CompartmentNumber = compartment.getLockerNumber();
        compartment.setStatusUnavailable();
        compartment.setOrderId(super.getOrderId()); 
    
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