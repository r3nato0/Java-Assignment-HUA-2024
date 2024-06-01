import java.util.ArrayList;
import java.util.List;

public class OrderManager{
    private static List<Orders> AllOrdersList = new ArrayList<>();
    private final static Integer MaxProductsPrintLength = 15; // Just a Limit of characters from products name to get printed, will be passed to UserInterface.PrintOnly(Name,Max...)

    public static void Create() {
        
        Costumers SelectedCostumer;
        Drivers SelectedDriver;
        String CostumerFullName ="Maria Georgioy";//UserInterface.InputTypeStringWithSpace("Please Provide the full name of the Costumer (upper cases will be ignored)  (example: nikos papadopoylos): ");
        if(CostumerManager.CheckCostumerExists(CostumerFullName)){
            System.out.printf("The Costumer allready Exsits, the rest of the fields are filled accordingly %n");
        }
        else{
            System.out.println("The Costumer does not exits, Creating new Entry");
            CostumerManager.NotFoundAddNew(CostumerFullName); // Other Filleds are prompted and filled via the NotFoundAddNew Method
        }

        String DriverFullName = "Renato Nake";//UserInterface.InputTypeStringWithSpace("Please Provide the full name of the driver (upper cases will be ignored)  (example: nikos papadopoylos): ");
        if (DriverManager.CheckDriverExists(DriverFullName)){
            System.out.printf("The Driver allready Exsits, the rest of the fields are filled accordingly %n");
        }
        else{
            System.out.println("The Driver does not exits, Creating new Entry");
            DriverManager.NotFoundAddNew(DriverFullName); // Other Filleds are prompted and filled via the NotFoundAddNew Method
        }

        SelectedDriver = DriverManager.GetCurrentDriverByFullName(DriverFullName); 
        SelectedCostumer = CostumerManager.GetCurrentCostumerByFullName(CostumerFullName);
        System.out.println(SelectedCostumer.getName());
        while (true) {
            Products product = ProductManager.GetProductByNameORId();
            Integer quantity = ProductManager.GetSelectedProductsQuantity(product);
            
            SelectedCostumer.addProductToBucket(product, quantity);
            
            boolean addAnotherProduct = UserInterface.InputTypeBoolean("Add another product to the bucket;  ");
            if (!addAnotherProduct) {
                break;
            }
        }
        
        boolean HomeDelivery = UserInterface.InputTypeBoolean("Ship to the Costumer's address? if no is selected, the order will be shiped to a Locker location  ");
        if(HomeDelivery){
            OrdersHome NewOrderHome = new OrdersHome(null, SelectedCostumer, SelectedDriver);
            AllOrdersList.add(NewOrderHome);
        }

}


public static void printAllOrders() {
    // Print header
    System.out.printf("%-10s %-15s %-20s %-10s\n", "Order ID", "CustomerName", "Product Name", "Quantity");

    for (Orders order : AllOrdersList) {
        if (order instanceof OrdersHome) {
            OrdersHome ordersHome = (OrdersHome) order;
            Integer orderId = ordersHome.getOrderId();
            String CostumerFullName = ordersHome.getCostumerName() + " " + ordersHome.getCostumerSurrname();
            List<ProductsInBucket> productsInOrder = ordersHome.getProductsInOrder();

            // Print customer details first
            if (!productsInOrder.isEmpty()) {
                ProductsInBucket firstProduct = productsInOrder.get(0);
                
                System.out.printf("%-10d %-15s %-20s %-10d\n", orderId, CostumerFullName,UserInterface.PrintOnly(firstProduct.getName(), MaxProductsPrintLength), firstProduct.getQuantity());

                // Print remaining products
                for (int i = 1; i < productsInOrder.size(); i++) {
                    ProductsInBucket product = productsInOrder.get(i);
                    System.out.printf("%-10s %-15s %-20s %-10d\n", "", "", UserInterface.PrintOnly(product.getName(), MaxProductsPrintLength), product.getQuantity());
                }
            }
            System.err.println("------------------------------------------------------------------------------------------------------------------------");}
        }
    }

}