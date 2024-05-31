public class ProductsInBucket {
    private Products product;
    private int quantity;
    private String ProductName;
    private String ProductCategory;
    private Integer Productid;
    private String ProductBrand;


    public ProductsInBucket(Products product, int quantity) {
        this.Productid = product.getId();
        this.ProductName = product.getName();
        this.ProductCategory = product.getCategory();
        this.ProductBrand = product.getBrand();
        this.quantity = quantity;
    }

    public Products getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
}
