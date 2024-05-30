


public class Products {
    private static Integer nextId = 1;
    private Integer id;
    private long barcode;
    private String name;
    private String category;
    private String brand;
    private Integer AvailableQuantity;
    
    public Products( String name, String category, String brand, Integer AvailableQuantity) {
        this.id = nextId++;
        this.barcode = ProductManager.generateBarcode();
        this.name = name;
        this.category = category;
        this.brand = brand;
        this.AvailableQuantity = AvailableQuantity;
    }
 
    // Getters
    public Integer getId() {
        return id;
    }
    
    public long getBarcode() {
        return barcode;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getBrand() {
        return brand;
    }

    public Integer getAvailableQuantity() {
        return AvailableQuantity;
    }

    // Setters
    public void setBarcode(long barcode) {
        this.barcode = barcode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setBrandName(String brand) {
        this.brand = brand;
    }

    public void setAvailableQuantity( Integer AvailableQuantity) {

        this.AvailableQuantity = AvailableQuantity;
    }
}

