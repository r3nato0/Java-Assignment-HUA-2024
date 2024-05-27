import java.util.Random;
import java.util.List;

public class Products {
    private static int nextId = 1;
    private int id;
    private long barcode;
    private String name;
    private String category;
    private String brand;
    private int quantity;
    
    public Products( String name, String category, String brand, int quantity) {
        this.id = nextId++;
        this.barcode = generateBarcode();
        this.name = name;
        this.category = category;
        this.brand = brand;
        this.quantity = quantity;
    }

     // Generate random 13-digit barcode
     private long generateBarcode() {
        Random random = new Random();
        long barcode = 0;
        for (int i = 0; i < 13; i++) {
            barcode = barcode * 10 + random.nextInt(10);
        }
        return barcode;
    }

    // Getters
    public int getId() {
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

    public int getQuantity() {
        return quantity;
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

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public static void printTableProducts(List<Products> productList) {
        // Printing table header
        System.out.printf("%-5s %-17s %-70s %-50s %-20s %-10s%n", "ID","Barcode", "Name", "Category", "Brand", "Quantity");

        // Printing each product row
        for (int i = 0; i < productList.size(); i++) {
            Products product = productList.get(i);
            System.out.printf("%-5d %-17s %-70s %-50s %-20s %-10d%n", product.getId(), String.format("%013d", product.getBarcode()), product.getName(), product.getCategory(), product.getBrand(), product.getQuantity());
        }
    }
}

