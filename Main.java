
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Creating a lists to store the instances of the hard coded objects
        List<Products> productList = new ArrayList<>();
        List<Lockers> LockersList = new ArrayList<>();
        List<Costumers> costumersList = new ArrayList<>();
        //List<Orders> OrdersList = new ArrayList<>();
        // Creating Products objects and adding them to the list
        productList.add(new Products("AIM Family Protection Herbal Toothpaste 75ml", "Grooming & Hygiene", "AIM ", 10));
        productList.add(new Products("Oral-B Pro 3 3500 Cross Action Electric Toothbrush", "Grooming & Hygiene", "Oral-B ", 5));
        productList.add(new Products("Cif General Purpose Cleansing Cream 500ml", "Cleaning & Household Items", "Cif ", 4));
        productList.add(new Products("Swaz Professional General Purpose Cleaning Liquid 4lt", "Cleaning & Household Items", "Swaz", 2));
        productList.add(new Products("Amita Peach fruit drink 1000ml", "Drinks", "Amita", 12));

        Drivers.CreateDefaultDrivers();

        costumersList.add(new Costumers("Maria","Georgioy","Zionos 25","mariageorgioy@gmail.com"));
        costumersList.add(new Costumers("Izabela","Georgioy","Zionos 25","mariageorgioy@gmail.com"));

        LockersList.add(new Lockers("Sygkrou 150",30,2));
        LockersList.add(new Lockers("Beikoy 63",15,1));
        // Drivers.printTableDrivers(driverList);
        // Products.printTableProducts(productList);
        // Costumers.printTableCostumers(costumersList);
        Integer test = UserInterface.InputTypeAFM("HELLO");
        System.out.println("works");

    }


    
    private static void addNewProduct(List<Products> productList) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the name of the product:");
        String name = scanner.nextLine();

        System.out.println("Enter the category of the product:");
        String category = scanner.nextLine();

        System.out.println("Enter the brand of the product:");
        String brand = scanner.nextLine();

        System.out.println("Enter the quantity of the product:");
        int quantity = scanner.nextInt();

        productList.add(new Products(name, category, brand, quantity));
    }

    

}


