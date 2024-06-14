Doc 
Ονοματεπώνυμο: Ρενάτο Νάκε
ΑM: it2023099

Περιγραφή Κλάσεων 

Στην εργασία υπάρχουν οι παρακάτω Κλάσεις:

Person, Customers, Drivers, Products, Lockers, EachCompartmentOfLockers, BucketShop, ProductsInBucket,Rating,Orders,OrdersHome,OrdersLocker,UserInterface,Constants,OrderManager
CostumerManager,DriverManager,LockerManager,ProductsManager
It2023099.

Αρχίζοντας από τις κλάσεις Person (οπου είναι abstract superclass) και Drivers/Customers,
που ειναι subclasses του Person. Οι subclasses Drivers/Customers περιέχουν τέσσερα κοινά στοιχεία 
όνομα,επώνυμο,διεύθυνση,e-mail. H κλάση Drivers επεκτείνει την superclass Person και συμπληρώνει τις ιδιότητες DriverAFM(Integer), PlateNumber(String) και κατηγορία του οδηγου Type(String) όπου μπορει να λάβει τρεις τιμές HomeDelivery, LockersDelivery και Home&Lockers τους τυπους παράδοσης της παραγγελίας, H κλάση Drivers έχει τα getters/setters για τις επιπλέον αυτές ιδιότητες και στην κλάση Person βρίσκονται οι υπόλοιποι getters/setters (όνομα,επώνυμο…) οπου και κληρονομούνται.Η κλάση DriverManager είναι μια ανεξάρτητη κλάση από την Drivers, αλλα όμως περιέχει μεθόδους του διαχειρίζεται το πρόγραμμα για τους Drivers.

Η κλάση Customers ειναι και αυτη Subclass του Person δεν εχει καποιες παραπάνω ιδιότητες εκτος απο το Costumerid (οπου ήθελα διαφορετικό id για κάθε πελάτη, οπως και για τον οδηγό).
Μέσα στον constructor του Customer εχει και την ιδιοτητα BucketShop, Κάθε φορά που δημιουργείται ένας νέος πελάτης (Customer), δημιουργείται επίσης ένα νέο στιγμιότυπο της κλάσης BucketShop, το οποίο συνδέεται με αυτόν τον πελάτη. Αυτό επιτυγχάνεται με την ακόλουθη εντολή στον κατασκευαστή της κλάσης Customer: this.BucketShop = new BucketShop(this); .
Η κλάση BucketShop έχει το σκεπτικό οπως ενα καλαθι που βρίσκουμε σε ένα ηλεκτρονικό κατάστημα,
έτσι λοιπόν κατα την δημιουργία του πελάτη που επεται η δημιουργία του καλαθιού του. Ο constructor του BucketShop δημιουργει μια κενή λίστα κλάσης ProductsInBucket του πελάτη, αυτό επιτυγχάνεται με την ακόλουθη εντολή this.customerBucket = new ArrayList<>(); 
όπου το customerBucket δηλώνεται στα χαρακτηριστικά της κλάσης ως εξης  
private List<ProductsInBucket> customerBucket;.
Η κλάση ProductsInBucket αντιπροσωπεύει τα ίδια τα προϊόντα που βρίσκονται στο καλάθι του πελάτη . Η μέθοδος του BucketShop addProductToBucket(Products product, int quantity)
λειτουργεί ως ενδιάμεσος διαμεσολαβητής για την προσθήκη προϊόντων στο καλάθι. Αντί να απευθύνεται απευθείας στο BucketShop, ο πελάτης μπορεί να καλέσει τη μέθοδο addProductToBucket από το δικό του αντικείμενο Costumers.
Με αυτόν τον τρόπο, οι πελάτες μπορούν να διαχειρίζονται το καλάθι τους με προϊόντα μέσω του δικού τους αντικειμένου Customers, ενώ η λειτουργικότητα της προσθήκης στο καλάθι υλοποιείται στο BucketShop.
Επισημαίνεται ότι, κατα την ολοκλήρωση μια παραγγελίας μέσω των κλάσεων Orders (abstract superclass) με τις subclasses OrdersHome και OrdesLocker, στην κλάση Orders μέσα στον constructor
τα προϊόντα (Στιγμιότυπα) του ProductsInBucket αντιγράφονται σε μια λίστα ίδιου τύπου και έπειτα η λίστα του πελάτη αδειάζει  .
this.ItemsBought = new ArrayList<>(costumer.getProductsInBucket());
costumer.clearBucket();
ώστε να είναι έτοιμη(κενή) για την επόμενη παραγγελία.

Η κλάση Orders χρησιμεύει ως βάση για τους δύο διάφορους τύπους παραγγελιών.
Στον κονστράκτορα του δέχεται τον πελάτη και τον οδηγό των προαναφερόμενων κλάσεων και έπειτα με χρήση των getters αυτόν των κλάσεων λαμβάνει τα δεδομένα τους (π.χ this.DriverFirstName = Driver.getName(); κλπ.) Οι υποκλασεις OrdersHome/OrdesLocker δέχονται τις ιδιότητες της κλάσεις Orders με τη χρήση κληρονομικότητας, οι κοινές ιδιότητες και συμπεριφορές των παραγγελιών είναι κεντρικές στην κλάση Orders, ενώ οι συγκεκριμένες συμπεριφορές για την παράδοση στο σπίτι και στις θυρίδες διαχειρίζονται στις αντίστοιχες παράγωγες κλάσεις.
Η κλάση OrdersHome προσθέτη την διεύθυνση παράδοσης αυτή του πελάτη καθώς και αντιπροσωπεύει αυτες τις είδους παραγγελίες.

Η κλάση OrdesLocker επεκτείνει την Orders για παραγγελίες παράδοσης σε θυρίδες αλλά επίσης δέχεται δύο παραμέτρους παραπάνω τα στιγμιότυπα Lockers και EachCompartmentOfLockers,Που αντιπροσωπεύουν τις θυρίδες (EachCompartmentOfLockers) αλλα και ολόκληρη την εγκατάσταση (Lockers). Ετσι μπορούμε να προσθέσουμε σαν διεύθυνση παραγγελίας την διεύθυνση του locker, έπειτα να μειώσουμε κατα ένα αριθμό τις διαθέσιμες θυρίδες του locker (locker.setminusOneSpace(); )  να λάβουμε τον αριθμό αναγνώρισης της θυρίδα  (this.CompartmentNumber = compartment.getLockerNumber(); ) και να θέσουμε την θυρίδα compartment.setStatusUnavailable();. H κλάση OrderManager περιέχει μεθόδους όπου το πρόγραμμα διαχειρίζεται τις παραγγελίες.

Η κλάση EachCompartmentOfLockers αντιπροσωπεύει μια μεμονωμένη θυρίδα μέσα σε μια εγκατάσταση θυρίδων Lockers, οπού κατα την δημιουργία του ενός στιγμιότυπου Lockers στον κονστρακτορ του θέτουμε (String address, int Spaces) όπου Spaces είναι οι συνολικές θυρίδες που διαθέτει, ετσι στην συνέχεια με έναν βρόχο for, δημιουργούνται οι θυρίδες της εγκατάστασης. Για κάθε θυρίδα, δημιουργείται ένα νέο στιγμιότυπο της κλάσης EachCompartmentOfLockers με την τρέχουσα εγκατάσταση(Lockers) (this), μια προκαθορισμένη κατάσταση (Free) και τον αριθμό της θυρίδας (i). οπου (i) ειναι κάθε θυρίδα έως και το σύνολο των Spaces. Η κλάση LockerManager περιέχει μεθόδους όπου το πρόγραμμα διαχειρίζεται τις θυρίδες και τα compartments του.

        for(int i=1;i<=Spaces;i++){
            this.compartmentsOfLocker.add(new EachCompartmentOfLockers(this, status.get(1), i));
        }
Η κλάση Products δέχεται στον κονστρακτορ ( String name, String category, String brand, Integer AvailableQuantity) και στο σώμα του μέσα θέτει ένα barcode this.barcode = ProductManager.generateBarcode(); για κάθε προϊόν. Η κλάση ProductManager περιέχει μεθόδους όπου το πρόγραμμα διαχειρίζεται τα προϊόντα.

Η κλάση Rating δέχεται στον κονστρακτορ ένα στιγμιότυπο μιας παραγγελίας, έπειτα αρχικοποιεί μια τιμή this.CostumerRating = null;  που λειτουργει σαν Placeholder για την μελλοντική κριτική του πελάτη, ολες οι υπόλοιπες ιδιότητες λαμβανονται μεσα στον κονστρακτορ με χρήση των getters των κλάσεων Orders  (π.χ this.CostumerFullName =  order.getCostumerFullName();
this.status = order.getStatus(); κτλ.) 
Το στιγμιότυπο Rating  δημιουργείται αυτόματα με καθε παραγγελία, αυτό επιτυγχάνεται με την ακόλουθη εντολή this.CostumerRating = new Rating(this);  που βρίσκεται μεσα στον κονστρακτορ του Orders, έτσι κάθε παραγγελία έχει ένα στιγμιότυπο Rating κι όταν επειτα ολοκληρωθεί, θα μπορει και να κριθεί.

Η κλάση UserInterface έχει μεθόδους που κυρίως εχουν σκοπό την αλληλεπίδραση του χρήστη με το πρόγραμμα με στοχευμένη δομή την σωστή είσοδο των δεδομένων.

Η κλάση Constants έχει σταθερά μεταβλητές που χρησιμοποιούνται σε όλο το πρόγραμμα με σκοπό την άμεση αλλαγη αυτόν τον δεδομένων αλλά και αποφυγή τυπογραφικού λάθους σε συνθήκες.




 
 

 


 
