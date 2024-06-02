import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class LockerManager{
    
    private static List<Lockers> LockersList = new ArrayList<>();
    

    public static void CreateDefaultLockers(){
        LockersList.add(new Lockers("Sygkrou 150",30));
        LockersList.add(new Lockers("Beikoy 63",15));
    }

//will return the single compartment of the compartmentNumber provided based on the locker instance, if compartmentNumber > than the size , null will be returned
    public EachCompartmentOfLockers getSingleCompartment(Lockers locker, Integer compartmentNumber) {
        EachCompartmentOfLockers selectedCompartment = null; // Initialize with default value
    
        for (EachCompartmentOfLockers compartment : locker.getCompartmentsOfLocker()) {
            if (compartment.getLockerNumber() == compartmentNumber) {
                selectedCompartment = compartment; 
                break;
            }
        }
    
        return selectedCompartment;
    }

//Will return the SIZE of FreeSpaces, if non is free will return 0
    public static Integer getLockerFreeSpaces(Lockers locker){
        Integer Counter =0;
        for(EachCompartmentOfLockers compartment : locker.getCompartmentsOfLocker()){
            if(compartment.getStatus().equals("Free")){
                Counter++;
            }
        }
        return Counter;
    }


//Will return a random  free Locker (has at least 1 compartment empty) instance, if non exists will return null
    public static Lockers getRandomFreeLocker(){
        List<Lockers> FreeLockers = new ArrayList<>();
        Random random = new Random();

        for(Lockers locker : LockersList){
            if(getLockerFreeSpaces(locker)!=0){
                FreeLockers.add(locker);
            }
        }

        if (FreeLockers.isEmpty()) {
            return null; // No free lockers 
        }

        Integer RandomNumbLocker =  random.nextInt(FreeLockers.size());
        Lockers locker = FreeLockers.get(RandomNumbLocker);
        FreeLockers.clear();
        return locker;
    }


    public static EachCompartmentOfLockers getRandomCompartmentOfLocker(Lockers locker){
  
        List<EachCompartmentOfLockers> FreeCompartments= new ArrayList<>();
        Random random = new Random();
        for(EachCompartmentOfLockers compartment : locker.getCompartmentsOfLocker()){
            if(compartment.getStatus().equals("Free")){
                FreeCompartments.add(compartment);
            }
        }

        if (FreeCompartments.isEmpty()) {
            return null; // this will never be true becouse we will always call first the method: getRandomCompartmentOfLocker, just adding it for good practise
        }

        Integer RandomNumbCompartment = random.nextInt(FreeCompartments.size());
        EachCompartmentOfLockers compartment = FreeCompartments.get(RandomNumbCompartment);
        FreeCompartments.clear();



        return compartment;
    }
}