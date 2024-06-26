import java.util.ArrayList;
import java.util.List;

public class BucketShop {
    private List<ProductsInBucket> customerBucket;
    private Costumers costumers;


    public BucketShop(Costumers customer) {
        this.costumers = customer;
        this.customerBucket = new ArrayList<>();
    }

    public Costumers getCostumer(){
       return this.costumers;
    }

    public void setCostumer(Costumers costumers){
        this.costumers=costumers;
     }



    public void addProductToBucket(Products product, int quantity) {
        customerBucket.add(new ProductsInBucket(product, quantity,this.costumers));
    }

    // Method to get the list of products in the bucket
    public List<ProductsInBucket> getProductsBucket() {
        return this.customerBucket;
    }

    
    public void clearBucket() {
        customerBucket.clear();
    }
}
