import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.*;

class Bike {
    private String bikeID;
    private boolean isAvailable;
    private int batteryLevel;
    private LocalDateTime lastUsedTime;
    private String location;

    public Bike(String bikeID, boolean isAvailable, int batteryLevel, LocalDateTime lastUsedTime, String location) {
        this.bikeID = bikeID;
        this.isAvailable = isAvailable;
        this.batteryLevel = batteryLevel;
        this.lastUsedTime = lastUsedTime;
        this.location = location;
    }

    public String getBikeID() { return bikeID; }
    public void setBikeID(String bikeID) { this.bikeID = bikeID; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }
    public int getBatteryLevel() { return batteryLevel; }
    public void setBatteryLevel(int batteryLevel) { this.batteryLevel = batteryLevel; }
    public LocalDateTime getLastUsedTime() { return lastUsedTime; }
    public void setLastUsedTime(LocalDateTime lastUsedTime) { this.lastUsedTime = lastUsedTime; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    @Override
    public String toString() {
        return "Bike{" + "bikeID='" + bikeID + '\'' + ", isAvailable=" + isAvailable +
                ", batteryLevel=" + batteryLevel + ", lastUsedTime=" + lastUsedTime +
                ", location='" + location + '\'' + '}';
    }
}

class ActiveRental {
    private String bikeID;
    private String userEmail;
    private LocalDateTime tripStartTime;

    public ActiveRental(String bikeID, String userEmail, LocalDateTime tripStartTime) {
        this.bikeID = bikeID;
        this.userEmail = userEmail;
        this.tripStartTime = tripStartTime;
    }

    public String getBikeID() { return bikeID; }
    public String getUserEmail() { return userEmail; }
    public LocalDateTime getTripStartTime() { return tripStartTime; }

    @Override
    public String toString() {
        return "ActiveRental{" + "bikeID='" + bikeID + '\'' + ", userEmail='" + userEmail + '\'' +
                ", tripStartTime=" + tripStartTime + '}';
    }
}

class RegisteredUsers {
    private String fullName;
    private String emailAddress;
    private String dateOfBirth;
    private long cardNumber;
    private String cardExpiryDate;
    private String cardProvider;
    private int cvv;
    private String userType;
    private String[] lastThreeTrips;

    public RegisteredUsers(String fullName, String emailAddress, String dateOfBirth,
                           long cardNumber, String cardExpiryDate, String cardProvider,
                           int cvv, String userType, String[] lastThreeTrips) {
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.dateOfBirth = dateOfBirth;
        this.cardNumber = cardNumber;
        this.cardExpiryDate = cardExpiryDate;
        this.cardProvider = cardProvider;
        this.cvv = cvv;
        this.userType = userType;
        this.lastThreeTrips = lastThreeTrips;
    }

    public double calculateFare(double baseFare) {
        return baseFare;
    }

    public void displayUserType() {
        System.out.println("Regular User");
    }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getEmailAddress() { return emailAddress; }
    public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }
    public String getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public long getCardNumber() { return cardNumber; }
    public void setCardNumber(long cardNumber) { this.cardNumber = cardNumber; }
    public String getCardExpiryDate() { return cardExpiryDate; }
    public void setCardExpiryDate(String cardExpiryDate) { this.cardExpiryDate = cardExpiryDate; }
    public String getCardProvider() { return cardProvider; }
    public void setCardProvider(String cardProvider) { this.cardProvider = cardProvider; }
    public int getCvv() { return cvv; }
    public void setCvv(int cvv) { this.cvv = cvv; }
    public String getUserType() { return userType; }
    public void setUserType(String userType) { this.userType = userType; }
    public String[] getLastThreeTrips() { return lastThreeTrips; }
    public void setLastThreeTrips(String[] lastThreeTrips) { this.lastThreeTrips = lastThreeTrips; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n===== Registered User Info =====");
        sb.append("\nFull Name: ").append(fullName);
        sb.append("\nEmail: ").append(emailAddress);
        sb.append("\nDOB: ").append(dateOfBirth);
        sb.append("\nUser Type: ").append(userType);
        sb.append("\nCard Provider: ").append(cardProvider);
        sb.append("\nCard Number: ****").append(String.valueOf(cardNumber).substring(String.valueOf(cardNumber).length()-4));
        sb.append("\nCard Expiry: ").append(cardExpiryDate);
        sb.append("\nCVV: ***");
        sb.append("\n\n--- Last 3 Trips ---");

        if (lastThreeTrips != null) {
            for (int i = 0; i < lastThreeTrips.length; i++) {
                sb.append("\nTrip ").append(i+1).append(": ").append(lastThreeTrips[i]);
            }
        } else {
            sb.append("\nNo trips yet.");
        }
        sb.append("\n================================\n");
        return sb.toString();
    }
}

class RegularUser extends RegisteredUsers {
    public RegularUser(String fullName, String emailAddress, String dateOfBirth,
                       long cardNumber, String cardExpiryDate, String cardProvider,
                       int cvv, String userType, String[] lastThreeTrips) {
        super(fullName, emailAddress, dateOfBirth, cardNumber, cardExpiryDate, cardProvider, cvv, userType, lastThreeTrips);
    }

    @Override
    public double calculateFare(double baseFare) {
        return super.calculateFare(baseFare);
    }

    @Override
    public void displayUserType() {
        System.out.println("Regular User");
    }
}

class VIPUser extends RegisteredUsers {
    public VIPUser(String fullName, String emailAddress, String dateOfBirth,
                   long cardNumber, String cardExpiryDate, String cardProvider,
                   int cvv, String userType, String[] lastThreeTrips) {
        super(fullName, emailAddress, dateOfBirth, cardNumber, cardExpiryDate, cardProvider, cvv, userType, lastThreeTrips);
    }

    @Override
    public double calculateFare(double baseFare) {
        return baseFare * 0.8;
    }

    @Override
    public void displayUserType() {
        System.out.println("VIP User");
    }
}

class ERyderLog {
    private String log;
    private String event;
    private LocalDateTime timeStamp;

    public ERyderLog(String log, String event, LocalDateTime timeStamp) {
        this.log = log;
        this.event = event;
        this.timeStamp = timeStamp;
    }

    public String getLog() { return log; }
    public String getEvent() { return event; }
    public LocalDateTime getTimeStamp() { return timeStamp; }

    @Override
    public String toString() {
        return log + " - " + event + " - " + timeStamp;
    }
}

class BikeRequest {
    private String userEmail;
    private String location;
    private LocalDateTime requestTime;

    public BikeRequest(String userEmail, String location, LocalDateTime requestTime) {
        this.userEmail = userEmail;
        this.location = location;
        this.requestTime = requestTime;
    }

    public String getUserEmail() { return userEmail; }
    public String getLocation() { return location; }
    public LocalDateTime getRequestTime() { return requestTime; }

    @Override
    public String toString() {
        return "User: " + userEmail + " | Location: " + location + " | Requested: " + requestTime;
    }
}

class BikeDatabase {
    public static ArrayList<Bike> bikes = new ArrayList<>();
    static {
        bikes.add(new Bike("BIKE001", true, 85, LocalDateTime.now().minusHours(2), "Newark"));
        bikes.add(new Bike("BIKE002", true, 90, LocalDateTime.now().minusHours(1), "Jersey City"));
        bikes.add(new Bike("BIKE003", false, 50, LocalDateTime.now(), "Newark"));
        bikes.add(new Bike("BIKE004", true, 75, LocalDateTime.now().minusHours(3), "Hoboken"));
        bikes.add(new Bike("BIKE005", true, 80, LocalDateTime.now().minusHours(4), "Newark"));
    }
}

class BikeService {
    private Stack<ERyderLog> logStack = new Stack<>();
    private int logId = 1;
    private Queue<BikeRequest> bikeRequest = new ArrayDeque<>();

    public String validateLocation(String location) {
        for (Bike bike : BikeDatabase.bikes) {
            if (bike.getLocation().equalsIgnoreCase(location) && bike.isAvailable()) {
                System.out.println("Bikes are available at your requested location.");
                return bike.getBikeID();
            }
        }
        System.out.println("Sorry, no bikes available at your requested location. Please try again later.");
        return null;
    }

    public LocalDateTime reserveBike(String bikeID, String userEmail, String location) {
        if (bikeID == null) return null;
        for (Bike bike : BikeDatabase.bikes) {
            if (bike.getBikeID().equals(bikeID)) {
                if (!bike.isAvailable()) {
                    bikeRequest.add(new BikeRequest(userEmail, location, LocalDateTime.now()));
                    System.out.println("Bike is currently unavailable. Request added to queue.");
                    return null;
                }
                LocalDateTime startTime = LocalDateTime.now();
                bike.setAvailable(false);
                bike.setLastUsedTime(startTime);
                logStack.push(new ERyderLog("BR"+logId++, "Bike "+bikeID+" rented from "+location+" by user "+userEmail, startTime));
                System.out.println("Reserving bike with ID: " + bikeID);
                return startTime;
            }
        }
        return null;
    }

    public void releaseBike(String bikeID) {
        for (Bike bike : BikeDatabase.bikes) {
            if (bike.getBikeID().equals(bikeID)) {
                bike.setAvailable(true);
                bike.setLastUsedTime(LocalDateTime.now());
                logStack.push(new ERyderLog("TR"+logId++, "Bike "+bikeID+" trip ended", LocalDateTime.now()));
                if (!bikeRequest.isEmpty()) {
                    BikeRequest next = bikeRequest.poll();
                    reserveBike(bikeID, next.getUserEmail(), next.getLocation());
                    System.out.println("Bike reassigned to next user: " + next.getUserEmail());
                }
                break;
            }
        }
    }

    public void viewSystemLogs() {
        System.out.println("\n===== System Logs =====");
        if (logStack.isEmpty()) System.out.println("No logs.");
        else for (ERyderLog log : logStack) System.out.println(log);
    }

    public void viewRequestQueue() {
        System.out.println("\n===== Pending Requests =====");
        if (bikeRequest.isEmpty()) System.out.println("Empty.");
        else for (BikeRequest r : bikeRequest) System.out.println(r);
    }

    public void removeFirstRequest() {
        if (!bikeRequest.isEmpty()) { bikeRequest.poll(); System.out.println("Removed first request."); }
        else System.out.println("Queue empty.");
    }
}

class RentalService {
    public static final double BASE_FARE = 3.0; 
    private final LinkedList<ActiveRental> activeRentalsList = new LinkedList<>();

    public void startRental(String bikeID, String email, LocalDateTime startTime) {
        activeRentalsList.add(new ActiveRental(bikeID, email, startTime));
    }

    public void terminateRental(String bikeID, RegisteredUsers user) { 
        Iterator<ActiveRental> it = activeRentalsList.iterator();
        while (it.hasNext()) {
            ActiveRental r = it.next();
            if (r.getBikeID().equals(bikeID)) {
                it.remove();
                break;
            }
        }

        double finalFare = user.calculateFare(BASE_FARE);
        System.out.printf("\n Trip Fare Calculated: $%.2f\n", finalFare);
        user.displayUserType();
    }

    public void viewActiveRentals() {
        if (activeRentalsList.isEmpty()) System.out.println("No active rentals.");
        else for (ActiveRental r : activeRentalsList) System.out.println(r);
    }
}

class UserService {
    private final List<RegisteredUsers> registeredUsersList = new ArrayList<>();
    private final Scanner sc = new Scanner(System.in);

    public RegisteredUsers addNewUsers() { 
        System.out.print("\nHow many users do you want to add: ");
        int count = Integer.parseInt(sc.nextLine());
        RegisteredUsers createdUser = null;

        for (int i = 1; i <= count; i++) {
            System.out.println("\n----- Adding User " + i + " -----");
            System.out.print("Full Name: "); String fullName = sc.nextLine();
            System.out.print("Email: "); String email = sc.nextLine();
            System.out.print("DOB (YYYY-MM-DD): "); String dob = sc.nextLine();
            System.out.print("Card Number: "); long cardNum = Long.parseLong(sc.nextLine());
            System.out.print("Card Provider: "); String provider = sc.nextLine();
            System.out.print("Card Expiry: "); String expiry = sc.nextLine();
            System.out.print("CVV: "); int cvv = Integer.parseInt(sc.nextLine());
            System.out.print("User Type (Regular/VIP): "); String type = sc.nextLine();

            String[] trips = new String[3];
            for (int t = 0; t < 3; t++) {
                System.out.println("\nTrip " + (t+1));
                System.out.print("Date: "); String d = sc.nextLine();
                System.out.print("Start: "); String s = sc.nextLine();
                System.out.print("End: "); String e = sc.nextLine();
                System.out.print("Fare: "); String f = sc.nextLine();
                System.out.print("Feedback: "); String fb = sc.nextLine();
                trips[t] = "Date:"+d+",From:"+s+",To:"+e+",Fare:"+f+",Feedback:"+(fb.isEmpty()?"None":fb);
            }

            if (type.equalsIgnoreCase("VIP")) {
                createdUser = new VIPUser(fullName, email, dob, cardNum, expiry, provider, cvv, type, trips);
            } else {
                createdUser = new RegularUser(fullName, email, dob, cardNum, expiry, provider, cvv, type, trips);
            }
            registeredUsersList.add(createdUser);
            System.out.println("User added!");
        }
        return createdUser;
    }

    public void viewRegisteredUsers() {
        if (registeredUsersList.isEmpty()) System.out.println("\nNo users.");
        else for (RegisteredUsers u : registeredUsersList) System.out.println(u);
    }

    public void removeRegisteredUsers() {
        System.out.print("Email to remove: ");
        String email = sc.nextLine();
        boolean removed = registeredUsersList.removeIf(u -> u.getEmailAddress().equals(email));
        System.out.println(removed ? "Removed." : "Not found.");
    }

    public void updateRegisteredUsers() {
        System.out.print("Email to update: ");
        String email = sc.nextLine();
        for (RegisteredUsers u : registeredUsersList) {
            if (u.getEmailAddress().equals(email)) {
                System.out.print("New Name (ENTER to skip): ");
                String fn = sc.nextLine(); if (!fn.isEmpty()) u.setFullName(fn);
                System.out.println("Updated.");
                return;
            }
        }
        System.out.println("Not found.");
    }
}

class AdminPanel {
    private final Scanner sc = new Scanner(System.in);
    private final UserService userService = new UserService();
    private final BikeService bikeService = new BikeService();
    private final RentalService rentalService = new RentalService();
    private final UserRegistration userRegistration = new UserRegistration();

    public void userManagementOptions() {
        while (true) {
            System.out.println("\n===== E-Ryder Admin =====");
            System.out.println("1. Add User");
            System.out.println("2. View Users");
            System.out.println("3. Remove User");
            System.out.println("4. Update User");
            System.out.println("5. Demo Rental");
            System.out.println("6. Check Brackets");
            System.out.println("7. View Logs");
            System.out.println("8. Manage Requests");
            System.out.println("9. Exit");
            System.out.print("Choice: ");

            String choice = sc.nextLine();
            switch (choice) {
                case "1": userService.addNewUsers(); break;
                case "2": userService.viewRegisteredUsers(); break;
                case "3": userService.removeRegisteredUsers(); break;
                case "4": userService.updateRegisteredUsers(); break;
                case "5": simulateRental(); break;
                case "6": new BracketChecker().checkBrackets(); break;
                case "7": bikeService.viewSystemLogs(); break;
                case "8": manageRequests(); break;
                case "9": System.out.println("Exiting."); return;
                default: System.out.println("Invalid.");
            }
        }
    }

    private void manageRequests() {
        while (true) {
            System.out.println("\n1. View Queue  2. Remove First  3. Back");
            String c = sc.nextLine();
            if (c.equals("1")) bikeService.viewRequestQueue();
            else if (c.equals("2")) bikeService.removeFirstRequest();
            else break;
        }
    }

    private void simulateRental() {
        System.out.print("\nRegistered user? (true/false): ");
        boolean isReg = Boolean.parseBoolean(sc.nextLine());
        System.out.print("Email: "); String email = sc.nextLine();
        System.out.print("Location: "); String location = sc.nextLine();

        RegisteredUsers currentUser = null;
        if (!isReg) {
            System.out.println("Registering...");
            userRegistration.registration();
        } else {
            currentUser = userService.addNewUsers();
        }

        String bikeID = bikeService.validateLocation(location);
        if (bikeID == null) return;

        LocalDateTime start = bikeService.reserveBike(bikeID, email, location);
        if (start == null) return;
        rentalService.startRental(bikeID, email, start);
        rentalService.viewActiveRentals();

        rentalService.terminateRental(bikeID, currentUser != null ? currentUser : new RegularUser("",email,"",0,"","",0,"",null));
        bikeService.releaseBike(bikeID);
        rentalService.viewActiveRentals();
    }
}

class BracketChecker {
    public void checkBrackets() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter brackets: ");
        String s = sc.nextLine();
        Deque<Character> stack = new ArrayDeque<>();
        boolean ok = true;
        for (char c : s.toCharArray()) {
            if (c=='('||c=='['||c=='{') stack.push(c);
            else if (c==')'||c==']'||c=='}') {
                if (stack.isEmpty()) { ok=false; break; }
                char top = stack.pop();
                if (!match(top,c)) { ok=false; break; }
            }
        }
        if (!stack.isEmpty()) ok=false;
        System.out.println(ok ? "Balanced" : "Not balanced");
    }
    private boolean match(char o, char c) {
        return (o=='('&&c==')')||(o=='['&&c==']')||(o=='{'&&c=='}');
    }
}

class Feedback {
    private String firstName, lastName, email, completeFeedback, reviewID;
    private boolean longFeedback;
    public Feedback(String fn, String ln, String e) { firstName=fn; lastName=ln; email=e; }
    public void analyseFeedback(boolean b, String s1,String s2,String s3,String s4,String s5) {
        completeFeedback = b ? (s1+s2+s3+s4+s5) : new StringBuilder().append(s1).append(s2).append(s3).append(s4).append(s5).toString();
        longFeedback = completeFeedback.length()>500;
        reviewID = (firstName+lastName).substring(2,6).toUpperCase() + completeFeedback.substring(10,15).replace(" ","") + completeFeedback.length() + "_" + System.currentTimeMillis();
    }
}

class UserRegistration {
    public void registration() { System.out.println("User registered (demo)."); }
}

class ERyder {
    public ERyder(String id, int b, boolean a, double d) {}
}

public class Main {
    public static void main(String[] args) {
        new AdminPanel().userManagementOptions();
    }
}