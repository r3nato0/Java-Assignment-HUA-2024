import java.util.ArrayList;
import java.util.List;

public class Costumers extends Person {
    private int Costumerid;
    private BucketShop BucketShop;
    private List<Orders> itemsBought;


    public Costumers(String Name,String Surname,String Address,String Email){
        super(Name,Surname,Address,Email);
        this.Costumerid = super.getId();
        this.BucketShop = new BucketShop(this);
        // this.itemsBought = new ArrayList<>();

    }


    // public List<Orders> getItemsBought(){
    //     return this.itemsBought;
    // }

    // public void setItemsBought(List<Orders> itemsBought){
    //     this.itemsBought =itemsBought;
    // }

    
    public BucketShop getBucketShop(){
        return this.BucketShop;
    }

    public String getName(){
        return super.getName();
    }

    public String getSurname(){
        return super.getSurname();
    }

    public String getCostumersFullName(){
        return super.getName() +" "+super.getSurname();
    }
    public String getEmail(){
        return super.getEmail();
    }
    public String getAddress() {
        return super.getAdress(); 
    }

    public int getCostumerid() {
        return this.Costumerid;
    }

    public void setCostumerid(int Costumerid) {
        this.Costumerid = Costumerid;
    }

    public void setName(String Name){
        super.setName(Name);
    }

    public void setSurname(String Surname){
        super.setSurname(Surname);
    }

    public void setEmail(String Email){
        super.setEmail(Email);
    }
    public void setAddress(String Address){
        super.setAdress(Address);
    }
    public void addProductToBucket(Products product, int quantity) {
        BucketShop.addProductToBucket(product, quantity);
    }
    
    public List<ProductsInBucket> getProductsInBucket() {
        return BucketShop.getProductsBucket();
    }
    public void clearBucket() {
        BucketShop.clearBucket();
    }
}
