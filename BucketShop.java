import java.util.ArrayList;
import java.util.List;

public class BucketShop {
    private List<ProductsInBucket> customerBucket;
    private Costumers costumers;
    private Integer customerID;

    public BucketShop(Costumers customer) {
        this.costumers = customer;
        this.customerID = customer.getCostumerid();
        this.customerBucket = new ArrayList<>();
    }

    // Method to add a product to the bucket with quantity
    public void addProductToBucket(Products product, int quantity) {
        ProductsInBucket ProductBucket = new ProductsInBucket(product, quantity);
        this.customerBucket.add(ProductBucket);
    }

    // Method to get the list of products in the bucket
    public List<ProductsInBucket> getProductsBucket() {
        return this.customerBucket;
    }
}
