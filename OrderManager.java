import java.util.ArrayList;
import java.util.List;
public class OrderManager{
    private static List<Products> TempOrderCreationProduct= new ArrayList<>();
    private static List<Integer>  TempOrderCreationQuantity= new ArrayList<>();



    public static void Create() {
        
        Costumers SelectedCostumer;
        Drivers SelectedDriver;
        String DeliveryLocation;
        TempOrderCreationProduct.clear();
        TempOrderCreationQuantity.clear();
        String CostumerFullName = UserInterface.InputTypeStringWithSpace("Please Provide the full name of the driver (upper cases will be ignored)  (example: nikos papadopoylos): ");
        if(CostumerManager.CheckCostumerExists(CostumerFullName)){
            System.out.printf("The Costumer allready Exsits, the rest of the fields are filled accordingly %n");
            SelectedCostumer = CostumerManager.GetCurrentCostumerByFullName(CostumerFullName);
        }
        else{
            System.out.println("The Costumer does not exits, Creating new Entry");
            DriverManager.NotFoundAddNew(CostumerFullName); // Other Filleds are prompted and filled via the NotFoundAddNew Method
            SelectedCostumer = CostumerManager.GetCurrentCostumerByFullName(CostumerFullName);
        }

        String DriverFullName = UserInterface.InputTypeStringWithSpace("Please Provide the full name of the driver (upper cases will be ignored)  (example: nikos papadopoylos): ");
        if (DriverManager.CheckDriverExists(DriverFullName)){
            System.out.printf("The Driver allready Exsits, the rest of the fields are filled accordingly %n");
            SelectedDriver = DriverManager.GetCurrentDriverByFullName(DriverFullName); 
        }
        else{
            System.out.println("The Driver does not exits, Creating new Entry");
            DriverManager.NotFoundAddNew(DriverFullName); // Other Filleds are prompted and filled via the NotFoundAddNew Method
            SelectedDriver = DriverManager.GetCurrentDriverByFullName(DriverFullName); 
        }

        TempOrderCreationProduct.add(ProductManager.GetProductByNameORId());
        TempOrderCreationQuantity.add(ProductManager.GetSelectedProductsQuantity(TempOrderCreationProduct.get(0)));
        boolean AnotherProduct = UserInterface.InputTypeBoolean("Add Another product to the bucket; ");
        if(AnotherProduct){
            TempOrderCreationProduct.add(ProductManager.GetProductByNameORId());
            TempOrderCreationQuantity.add(ProductManager.GetSelectedProductsQuantity(TempOrderCreationProduct.get(1)));
        }

        Integer DeliveryOption =UserInterface.InputTypeLockerORHome("Press (1) for Costumer's Home Address or (2) For Locker Delivery ");
        if(DeliveryOption==1){
            DeliveryLocation = SelectedCostumer.getAddress();
        }
        else{
            DeliveryLocation =
        }

}
}