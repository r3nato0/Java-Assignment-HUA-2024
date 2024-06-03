import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
public class ProductManager{
    private static List<Products> productList = new ArrayList<>();
   private static List<String> CategoryList = new ArrayList<>(Arrays.asList("hygiene item", "detergent", "Drinks","Food"));



    public static void CreateDefaultProducts(){
        productList.add(new Products("AIM Family Protection Herbal Toothpaste 75ml", CategoryList.get(0), "AIM ", 10));
        productList.add(new Products("Oral-B Pro 3 Cross Action Electric Toothbrush", CategoryList.get(0), "Oral-B ", 5));
        productList.add(new Products("Cif General Purpose Cleansing Cream 500ml", CategoryList.get(1), "Cif ", 4));
        productList.add(new Products("Swaz Professional General Purpose Cleaning Liquid 4lt", CategoryList.get(2), "Swaz", 2));
        productList.add(new Products("Amita Peach fruit drink 1000ml", "Drinks", CategoryList.get(3), 12));
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

        Products SelectedProduct=null;
        //System.out.println(Message);
        String CommandByname = "name:";
        String CommandById = "id:";
        boolean isValid=false;
        while (true) {
            System.out.printf("Select Product name:/id: ");
            Scanner scanner = new Scanner(System.in);
            String Selection = scanner.nextLine();
            if (Selection.startsWith(CommandByname)){
                String ProductName = Selection.substring(5, Selection.length());
                for (Products product:productList){
                    if(product.getName().equalsIgnoreCase(ProductName)){
                        System.out.println("test");
                        isValid=true;
                        SelectedProduct=product;
                        break;}}                    }

            if (Selection.startsWith(CommandById)){
                String ProductId = Selection.substring(CommandById.length(), Selection.length());
                for(Products product:productList){
                    if(product.getId()==Integer.parseInt(ProductId)){
                        isValid=true;
                        SelectedProduct=product;
                        break;}}                }
            
            if(isValid){
                return SelectedProduct;}
            else{
                System.out.println("Please specify the search criteria " + "name:" + "or" + "id: (example: name:ProductName id:ProductId)2" );
                System.out.println("No such product Avaiable!");}}}


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

}
