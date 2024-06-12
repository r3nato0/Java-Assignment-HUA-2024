import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
public class ProductManager{
    private static List<Products> productList = new ArrayList<>();
    private static List<String> CategoryList = Constants.CategoryList;




    //returns the list of the products that have been created (all products are created via the list)
    public static List<String> getCategoryList(){
        return CategoryList;
    }

    //creates default products
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
        System.out.printf("Specify Quantiy of product");
        SelectedQuantity = UserInterface.SelectNumber(SelectedProduct.getAvailableQuantity());// will ask user for quantity to select

        if(SelectedQuantity>0){
            Integer NewAvailableQuantity = SelectedProduct.getAvailableQuantity() - SelectedQuantity;
            SelectedProduct.setAvailableQuantity(NewAvailableQuantity); //sets the new avaible quantity based on the users input
            break;
        }else{
            System.out.printf("Please Provide a quantity that is Valid");// in case of wrong input
        }

        }
        return SelectedQuantity;
    }



    //will ask user to select product
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
                String ProductName = Selection.substring(5, Selection.length());// in case the user gave a name then we get the product based ont he substring
                for (Products product:productList){
                    if(product.getName().equalsIgnoreCase(ProductName) ){
                        isValid=true;
                        SelectedProduct=product;
                        break;}}                    }

                        if (Selection.startsWith(CommandById)) { //if he gave an id
                            try { //we check if its a numer
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
                            } catch (NumberFormatException e) { // if its not a number, we print an error. example id:CocaCola
                            
                                System.out.println("Invalid product ID format.");
                            }}

            if(isValid ){
                return SelectedProduct;}
            else{
                System.out.println("Please specify the search criteria " + "name:" + "or" + "id: (example: name:ProductName id:ProductId)" );//we help the user with this print
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

    // genarates random 13 digit code for barcodes
    public static long generateBarcode() {
        Random random = new Random();
        long barcode = 0;
        for (int i = 0; i < 13; i++) {
            barcode = barcode * 10 + random.nextInt(10);
        }
        return barcode;
        }
    
    //gets the product by id passed in the parrameter
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
    //adds new product, from main menu
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


    //gets the category based on the barcode
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
        return "No Barcode!";
    }

    //we will return the gategory based on the product name
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
