import java.util.ArrayList;
import java.util.List;
public class LockerManager{
    private static List<Lockers> LockersList = new ArrayList<>();

    public static void CreateDefaultLockers(){
        LockersList.add(new Lockers("Sygkrou 150",30,2));
        LockersList.add(new Lockers("Beikoy 63",15,1));
    }
}