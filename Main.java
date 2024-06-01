import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CreateDefaultData.CreateDefaultInstances();
    List<Orders> ordersList = new ArrayList<>();

        // Add orders to the list

        // Print all orders
    Orders.printAllOrders(ordersList);
    OrderManager.Create();
    Orders.printAllOrders(ordersList);
    OrderManager.Create();
    }
}