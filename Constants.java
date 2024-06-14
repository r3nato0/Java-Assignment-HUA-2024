import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class Constants {
    
    public static final String PENDING = "Pending";
    public static final String COMPLETED = "Completed";
    public static final String DELIVERYHOME = "HomeDelivery";
    public static final String HOMEANDLOCKER = "Home&Lockers";
    public static final String LOCKERDELIVERY = "LockersDelivery";
    public static final List<String> CategoryList = new ArrayList<>(Arrays.asList("hygiene", "detergent", "Drinks","Food"));

    

    public static final List<String[]> CUSTOMER_DATA = Arrays.asList(
        new String[]{"george","tsoulas", "Dimitraopoyloy 105", "george@yahoo.com"},
        new String[]{"giannhs","ntalas", "ionos 25", "giannhs@hello.com"},
        new String[]{"dimitris","latsi", "benizelou 35", "dimitris@something.com"},
        new String[]{"maria","papadopoyloy","Zionos 25","mariageorgioy@gmail.com"},
        new String[]{"izabel","georgioy","Aglayrou 13","izabelageorgioy@gmail.com"}
    );


    public static final List<String[]> DRIVERS_DATA = Arrays.asList(
        new String[]{"renato","nake","Aglaurou 10","nakerenato@gmail.com","16215021","YMN4946",Constants.DELIVERYHOME},
        new String[]{"george","papadopoylos","dimitrakopoulou 105","georgepapadopoylos@gmail.com","12345123","DFT9643",Constants.LOCKERDELIVERY},
        new String[]{"sofia","alekou", "benizelou 35", "charlie@example.com","13254634","DSQ7434",Constants.LOCKERDELIVERY},
        new String[]{"anthoni","mantellos","Hrakleous 70","AMantellos@gmail.com","12547542","IEM1231",Constants.HOMEANDLOCKER},
        new String[]{"nikos","alekou","Aglaurou 10","nakerenato@gmail.com","54315643","IDM1242",Constants.DELIVERYHOME}
    );

    public static final List<String[]> PRODUCTS_DATA = Arrays.asList(
        new String[]{"AIM Family Protection Herbal Toothpaste 75ml", CategoryList.get(0), "AIM ", "10"},
        new String[]{"Oral-B Pro 3 Cross Action Electric Toothbrush", CategoryList.get(0), "Oral-B ", "5"},
        new String[]{"Cif General Purpose Cleansing Cream 500ml", CategoryList.get(1), "Cif ", "4"},
        new String[]{"Swaz Professional General Purpose Cleaning Liquid 4lt", CategoryList.get(2), "Swaz", "2"},
        new String[]{"Amita Peach fruit drink 1000ml", "Drinks", CategoryList.get(3), "12"}
    );

    public static final List<String[]> LOCKERS_DATA = Arrays.asList(
        new String[]{"Sygkrou 150","30"},
        new String[]{"Beikoy 63", "15"}
    );


}