public class ProductsInBucket {
    private Products product;
    private int quantity;
    private String ProductName;
    private String ProductCategory;
    private Integer Productid;
    private String ProductBrand;
    private String barcode;
    private Costumers costumer;
    
    public ProductsInBucket(Products product, int quantity,Costumers costumer) {
        this.Productid = product.getId();
        this.ProductName = product.getName();
        this.ProductCategory = product.getCategory();
        this.ProductBrand = product.getBrand();
        this.quantity = quantity;
        this.barcode=product.getBarcode();
        this.costumer=costumer;
    }

    public String getCostumersFullName(){
        return costumer.getFullname();
    }

    public String getBarcode(){
    return this.barcode;
    }

    
    public String getName() {
        return this.ProductName;
    }

    public Products getProduct() {
        return this.product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductName() {
        return this.ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public String getProductCategory() {
        return this.ProductCategory;
    }

    public void setProductCategory(String ProductCategory) {
        this.ProductCategory = ProductCategory;
    }

    public Integer getProductid() {
        return this.Productid;
    }

    public void setProductid(Integer Productid) {
        this.Productid = Productid;
    }

    public String getProductBrand() {
        return this.ProductBrand;
    }

    public void setProductBrand(String ProductBrand) {
        this.ProductBrand = ProductBrand;
    }

}
