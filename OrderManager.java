import java.util.ArrayList;
import java.util.List;

public class OrderManager{
    private static List<Orders> AllOrdersList = new ArrayList<>();


    public static void Create() {
        
        Costumers SelectedCostumer;
        Drivers SelectedDriver;
        String CostumerFullName =UserInterface.InputTypeStringWithSpace("Please Provide the full name of the Costumer (upper cases will be ignored)  (example: nikos papadopoylos): ");
        if(CostumerManager.CheckCostumerExists(CostumerFullName)){
            System.out.printf("The Costumer allready Exsits, the rest of the fields are filled accordingly %n");
        }
        else{
            System.out.println("The Costumer does not exits, Creating new Entry");
            CostumerManager.NotFoundAddNew(CostumerFullName); // Other Filleds are prompted and filled via the NotFoundAddNew Method
        }

        String DriverFullName = UserInterface.InputTypeStringWithSpace("Please Provide the full name of the driver (upper cases will be ignored)  (example: nikos papadopoylos): ");
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
            System.out.println(NewOrderHome);
            AllOrdersList.add(NewOrderHome);
            for (Orders order : AllOrdersList) {
                if (order instanceof OrdersHome) {
                    OrdersHome ordersHome = (OrdersHome) order;
                    System.out.println(ordersHome.getOrderId());
                    System.out.println(ordersHome.getDriverName());
                    System.out.println(ordersHome.getDriverLastName());
                    List<ProductsInBucket> productsInOrder = ordersHome.getProductsInOrder();
                    for (ProductsInBucket product : productsInOrder) {
                        System.out.println(product.getName());
                        System.out.println(product.getQuantity());
                        // Add more details of the product if needed
                    }
                    // Now you can access methods or fields specific to OrdersHome
                }
            }
        }

}
}