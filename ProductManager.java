import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
public class ProductManager{
    private static List<Products> productList = new ArrayList<>();
    private static List<String> CategoryList = new ArrayList<>(Arrays.asList("hygiene", "detergent", "Drinks","Food"));





    public static List<String> getCategoryList(){
        return CategoryList;
    }

    
    public static void CreateDefaultProducts(){
        for (String[] ProductsDATA : Constants.PRODUCTS_DATA) {
            String name = ProductsDATA[0];
            String Category = ProductsDATA[1];
            String brand =ProductsDATA[2];
            Integer quantity = Integer.parseInt(ProductsDATA[3]);
            productList.add( new Products (name,Category,brand,quantity));
        }
        }


    public static Integer GetSelectedProductsQuantity(Products SelectedProduct){
        Scanner scanner = new Scanner(System.in);
        Integer SelectedQuantity ;
        while (true) {
        System.out.printf("Specify Quantiy of product, ranging from 1 to %d \n",SelectedProduct.getAvailableQuantity());
        SelectedQuantity = scanner.nextInt();

        if((SelectedQuantity>0) && (SelectedQuantity<=SelectedProduct.getAvailableQuantity())){
            Integer NewAvailableQuantity = SelectedProduct.getAvailableQuantity() - SelectedQuantity;
            SelectedProduct.setAvailableQuantity(NewAvailableQuantity);
            break;
        }else{
            System.out.printf("Please Provide a quantity that is Valid, ranging from 1 to %d \n",SelectedProduct.getAvailableQuantity());
        }

        }
        return SelectedQuantity;
    }


    public static Products GetProductByNameORId(){
        printTableProducts();
        System.out.println("For Reference look the product's table above!");
        System.out.println("Start with name: and then the product name OR id: and then the product id" );
        Scanner scanner = new Scanner(System.in);
        Products SelectedProduct=null;
        //System.out.println(Message);
        String CommandByname = "name:";
        String CommandById = "id:";
        boolean isValid=false;
        while (true) {
            System.out.printf("Select Product name:/id: ");
            String Selection = scanner.nextLine().trim();
            if (Selection.startsWith(CommandByname)){
                String ProductName = Selection.substring(5, Selection.length());
                for (Products product:productList){
                    if(product.getName().equalsIgnoreCase(ProductName) ){
                        isValid=true;
                        SelectedProduct=product;
                        break;}}                    }

                        if (Selection.startsWith(CommandById)) {
                            try {
                                String ProductId = Selection.substring(CommandById.length());
                                int parsedProductId = Integer.parseInt(ProductId);
                        
                                boolean found = false;
                                for (Products product : productList) {
                                    if (product.getId() == parsedProductId) {
                                        isValid = true;
                                        SelectedProduct = product;
                                        found = true;
                                        break;
                                    }
                                }
                        
                                if (!found) {
                        
                                    System.out.println("No product found with the given ID.");
                                }
                            } catch (NumberFormatException e) {
                            
                                System.out.println("Invalid product ID format.");
                            }}

            if(isValid ){
                return SelectedProduct;}
            else{
                System.out.println("Please specify the search criteria " + "name:" + "or" + "id: (example: name:ProductName id:ProductId)" );
            }
        }
    }






    public static void printTableProducts() {
        // Printing table header
        System.out.printf("%-5s %-17s %-70s %-50s %-20s %-10s%n", "ID","Barcode", "Name", "Category", "Brand", "Quantity");

        // Printing each product row
        for (int i = 0; i < productList.size(); i++) {
            Products product = productList.get(i);
            System.out.printf("%-5d %-17s %-70s %-50s %-20s %-10d%n", product.getId(), String.format("%013d", product.getBarcode()), product.getName(), product.getCategory(), product.getBrand(), product.getAvailableQuantity());
        }
    }

    // Generate random 13-digit barcode
    public static long generateBarcode() {
        Random random = new Random();
        long barcode = 0;
        for (int i = 0; i < 13; i++) {
            barcode = barcode * 10 + random.nextInt(10);
        }
        return barcode;
        }
    
        
    public static Products GetProductById(Integer id){
        Products SelectedProduct=null;
        for(Products product : productList){
            if(product.getId()==id){
                SelectedProduct = product;
                break;
            }
        }
        return SelectedProduct;
    }

    public static void AddNewProduct(){
        String ProductName = UserInterface.InputTypeAllCharactersAllowed("Give The product's Name:");
        Integer StockQuanity = UserInterface.InputTypeIntegerNoLimit("Give the product's quantity to set");
        Integer ProductCategory = UserInterface.InputTypeProductCategory(CategoryList);
        String Productbrand = UserInterface.InputTypeAllCharactersAllowed("Give The product's Brand:");
        Products product = new Products(ProductName, CategoryList.get(ProductCategory), Productbrand, StockQuanity);
        System.out.printf("%s\n %s %d\n %s %s\n %s %d\n %s %s\n %s %s","Success you created the new product",
        "ID:",product.getId(),
        "Name:",product.getName(),
        "With stock Quanity :",product.getAvailableQuantity(),
        "brand:",product.getBrand(),
        "Category:",product.getCategory());

    }



    public static String getCategoryByBarcode(String barcode){
        String category=null;
        for(Products product:productList){
            if(barcode.equalsIgnoreCase(String.valueOf(product.getBarcode()))){
                category= product.getCategory();
                break;
            }
        }
        return category;
    }


    // we will return a string for some error handling in case the product for some reason has no barcode
    public static String getBarcodeByName(String Name) {
        for (Products product : productList) {
            if (product.getName().equalsIgnoreCase(Name)) {
                return String.valueOf(product.getBarcode());
            }
        }
        // the error handling
        return "This product has no barcode for some reason!";
    }

        
        public static String getCategoryByName(String Name) {
            for (Products product : productList) {
                if (product.getName().equalsIgnoreCase(Name)) {
                    return product.getCategory();
                }
            }
            // some error handling
            return "This product has no barcode for some reason!";
        }
}
