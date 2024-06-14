import java.util.List;
public class Costumers extends Person {
    private static int nextId = 1;
    private int Costumerid;
    private BucketShop BucketShop;



    public Costumers(String Name,String Surname,String Address,String Email){
        super(Name,Surname,Address,Email);
        this.Costumerid = nextId++;
        this.BucketShop = new BucketShop(this);


    }


    @Override
    public Integer getId(){
        return this.Costumerid;
    }

    @Override
    public void setId(Integer ID){
        this.Costumerid = ID;
    }
    
    public BucketShop getBucketShop(){
        return this.BucketShop;
    }

    public int getCostumerid() {
        return this.Costumerid;
    }

    public void setCostumerid(int Costumerid) {
        this.Costumerid = Costumerid;
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
