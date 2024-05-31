public class OrderManager{



    public static void Create() {
        
        Costumers SelectedCostumer;
        Drivers SelectedDriver;
        BucketShop CostumerBucket;
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

        CostumerBucket = new BucketShop(SelectedCostumer);
        while (true) {
            Products product = ProductManager.GetProductByNameORId();
            Integer quantity = ProductManager.GetSelectedProductsQuantity(product);
            
            CostumerBucket.addProductToBucket(product, quantity);
            
            boolean addAnotherProduct = UserInterface.InputTypeBoolean("Add another product to the bucket: ");
            if (!addAnotherProduct) {
                break;
            }
        }



}
}