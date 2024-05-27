public class Lockers {
    private static int nextId = 1;
    private int LockerId;
    private String LockerAdress;
    private int LockerSpaces;
    private int LockerSpacesUsed;
    private int LockerSpacesFree;

    public Lockers(String LockerAdress,int LockerSpaces,int LockerSpacesUsed){
        this.LockerId=nextId++;
        this.LockerAdress = LockerAdress;
        this.LockerSpaces = LockerSpaces;
        this.LockerSpacesUsed = LockerSpacesUsed;
        this.LockerSpacesFree = this.LockerSpaces - this.LockerSpacesUsed;
    }

    //Getters
    public int getLockerId(){
        return LockerId;
    }
    public String getLockerAdress(){
        return LockerAdress;
    }
    public int getLockerSpaces(){
        return LockerSpaces;
    }
    public int getLockerSpacesUsed(){
        return LockerSpacesUsed;
    }
    public int getLockerSpacesFree(){
        return LockerSpacesFree;
    }

    //Setters

    public void setLockerAdress(String LockerAdress) {
        this.LockerAdress = LockerAdress;
    }
    public void setLockerSpaces(int LockerSpaces) {
        this.LockerSpaces = LockerSpaces;
    }
    public void setLockerSpacesUsed(int LockerSpacesUsed) {
        this.LockerSpacesUsed = LockerSpacesUsed;
    }
    public void setLockerSpacesFree(int LockerSpacesFree) {
        this.LockerSpacesFree = this.LockerSpaces - this.LockerSpacesUsed;
    }
}


