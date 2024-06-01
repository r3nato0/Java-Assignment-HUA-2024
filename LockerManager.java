import java.util.ArrayList;
import java.util.List;
public class LockerManager{
    private static List<Lockers> LockersList = new ArrayList<>();
    

    public static void CreateDefaultLockers(){
        LockersList.add(new Lockers("Sygkrou 150",30,0));
        LockersList.add(new Lockers("Beikoy 63",15,0));
    }

    public static ArrayList<Integer> createNumberList(int start, int end) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            list.add(i);
        }
        return list;
    }
    
    
    
}