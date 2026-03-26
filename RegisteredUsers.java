import java.time.LocalDate;
import java.time.Period;
import java.util.*;

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

class AdminPanel {
    private final List<RegisteredUsers> registeredUsersList = new ArrayList<>();
    private final Scanner sc = new Scanner(System.in);

    public void userManagementOptions() {
        while (true) {
            System.out.println("\n===== Welcome to E-Ryder Admin Panel =====");
            System.out.println("1. Add New User");
            System.out.println("2. View Registered Users");
            System.out.println("3. Remove Registered User");
            System.out.println("4. Update Registered User");
            System.out.println("5. Exit");
            System.out.print("Please enter your choice: ");

            String choice = sc.nextLine();
            switch (choice) {
                case "1": addNewUsers(); break;
                case "2": viewRegisteredUsers(); break;
                case "3": removeRegisteredUsers(); break;
                case "4": updateRegisteredUsers(); break;
                case "5":
                    System.out.println("Exiting Admin Panel...");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private void addNewUsers() {
        System.out.print("\nHow many users do you want to add: ");
        int count = Integer.parseInt(sc.nextLine());

        for (int i = 1; i <= count; i++) {
            System.out.println("\n----- Adding User " + i + " -----");

            System.out.print("Enter Full Name: ");
            String fullName = sc.nextLine();

            System.out.print("Enter Email: ");
            String email = sc.nextLine();

            System.out.print("Enter DOB (YYYY-MM-DD): ");
            String dob = sc.nextLine();

            System.out.print("Enter Card Number: ");
            long cardNum = Long.parseLong(sc.nextLine());

            System.out.print("Enter Card Provider (VISA/MasterCard/AE): ");
            String provider = sc.nextLine();

            System.out.print("Enter Card Expiry (MM/YY): ");
            String expiry = sc.nextLine();

            System.out.print("Enter CVV: ");
            int cvv = Integer.parseInt(sc.nextLine());

            System.out.print("Enter User Type (Regular/VIP): ");
            String type = sc.nextLine();

            String[] trips = new String[3];
            System.out.println("\n----- Enter Last 3 Trips -----");
            for (int t = 0; t < 3; t++) {
                System.out.println("\nTrip " + (t+1) + ":");
                System.out.print("Date (YYYY-MM-DD): ");
                String date = sc.nextLine();

                System.out.print("Start Location: ");
                String start = sc.nextLine();

                System.out.print("End Location: ");
                String end = sc.nextLine();

                System.out.print("Fare: ");
                String fare = sc.nextLine();

                System.out.print("Feedback (can be empty): ");
                String feedback = sc.nextLine();

                StringBuilder trip = new StringBuilder();
                trip.append("Date: ").append(date)
                    .append(", From: ").append(start)
                    .append(", To: ").append(end)
                    .append(", Fare: ").append(fare)
                    .append(", Feedback: ").append(feedback.isEmpty() ? "None" : feedback);

                trips[t] = trip.toString();
            }

            RegisteredUsers user = new RegisteredUsers(
                    fullName, email, dob, cardNum, expiry, provider, cvv, type, trips
            );
            registeredUsersList.add(user);
            System.out.println("User " + i + " added successfully!");
        }
    }

    private void viewRegisteredUsers() {
        if (registeredUsersList.isEmpty()) {
            System.out.println("\nNo registered users to display.");
            return;
        }

        System.out.println("\n===== All Registered Users =====");
        for (RegisteredUsers user : registeredUsersList) {
            System.out.println(user);
        }
    }

    private void removeRegisteredUsers() {
        if (registeredUsersList.isEmpty()) {
            System.out.println("\nNo users to remove.");
            return;
        }

        System.out.print("\nEnter email of user to remove: ");
        String email = sc.nextLine();
        boolean found = false;

        Iterator<RegisteredUsers> iterator = registeredUsersList.iterator();
        while (iterator.hasNext()) {
            RegisteredUsers user = iterator.next();
            if (user.getEmailAddress().equals(email)) {
                iterator.remove();
                found = true;
                System.out.println("User removed successfully!");
                break;
            }
        }

        if (!found) {
            System.out.println("User with this email not found.");
        }
    }

    private void updateRegisteredUsers() {
        if (registeredUsersList.isEmpty()) {
            System.out.println("\nNo users to update.");
            return;
        }

        System.out.print("\nEnter email of user to update: ");
        String email = sc.nextLine();
        RegisteredUsers target = null;

        for (RegisteredUsers u : registeredUsersList) {
            if (u.getEmailAddress().equals(email)) {
                target = u;
                break;
            }
        }

        if (target == null) {
            System.out.println("User not found.");
            return;
        }

        System.out.println("\n----- Update User Info (Press ENTER to keep old value) -----");

        System.out.print("New Full Name: ");
        String fn = sc.nextLine();
        if (!fn.isEmpty()) target.setFullName(fn);

        System.out.print("New Email: ");
        String em = sc.nextLine();
        if (!em.isEmpty()) target.setEmailAddress(em);

        System.out.print("New DOB: ");
        String dob = sc.nextLine();
        if (!dob.isEmpty()) target.setDateOfBirth(dob);

        System.out.print("New Card Provider: ");
        String p = sc.nextLine();
        if (!p.isEmpty()) target.setCardProvider(p);

        System.out.print("New Card Expiry: ");
        String exp = sc.nextLine();
        if (!exp.isEmpty()) target.setCardExpiryDate(exp);

        System.out.print("New User Type: ");
        String ut = sc.nextLine();
        if (!ut.isEmpty()) target.setUserType(ut);

        System.out.print("New Card Number (0 = no change): ");
        String cn = sc.nextLine();
        if (!cn.equals("0")) target.setCardNumber(Long.parseLong(cn));

        System.out.print("New CVV (0 = no change): ");
        String cv = sc.nextLine();
        if (!cv.equals("0")) target.setCvv(Integer.parseInt(cv));

        System.out.println("User updated successfully!");
    }
}

public class Feedback {
    private String firstName;
    private String lastName;
    private String email;
    private String completeFeedback;
    private String reviewID;
    private boolean longFeedback;

    public Feedback(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public void analyseFeedback(boolean isConcatenation,
                                String sent1, String sent2, String sent3, String sent4, String sent5) {
        if (isConcatenation) {
            completeFeedback = feedbackUsingConcatenation(sent1, sent2, sent3, sent4, sent5);
        } else {
            completeFeedback = feedbackUsingStringBuilder(sent1, sent2, sent3, sent4, sent5);
        }
        longFeedback = checkFeedbackLength(completeFeedback);
        createReviewID(firstName, lastName, completeFeedback);
    }

    private String feedbackUsingConcatenation(String s1, String s2, String s3, String s4, String s5) {
        return s1 + s2 + s3 + s4 + s5;
    }

    private String feedbackUsingStringBuilder(String s1, String s2, String s3, String s4, String s5) {
        StringBuilder sb = new StringBuilder();
        sb.append(s1).append(s2).append(s3).append(s4).append(s5);
        return sb.toString();
    }

    private boolean checkFeedbackLength(String feedback) {
        return feedback.length() > 500;
    }

    private void createReviewID(String firstName, String lastName, String completeFeedback) {
        String namePart = (firstName + lastName).substring(2, 6).toUpperCase();
        String feedbackPart = completeFeedback.substring(10, 15).toLowerCase();
        int length = completeFeedback.length();
        long timeStamp = System.currentTimeMillis();
        String id = namePart + feedbackPart + length + "_" + timeStamp;
        id = id.replace(" ", "");
        this.reviewID = id;
    }

    @Override
    public String toString() {
        return "=== Feedback Info ===" +
                "\nFirst Name: " + firstName +
                "\nLast Name: " + lastName +
                "\nEmail: " + email +
                "\nComplete Feedback: " + completeFeedback +
                "\nIs Long Feedback (>500 chars): " + longFeedback +
                "\nReview ID: " + reviewID +
                "\n======================";
    }

    public String getCompleteFeedback() { return completeFeedback; }
    public boolean isLongFeedback() { return longFeedback; }
    public String getReviewID() { return reviewID; }
}

class UserRegistration {
    private static final double VIP_DISCOUNT_UNDER_18_BIRTHDAY = 25.0;
    private static final double VIP_DISCOUNT_UNDER_18 = 20.0;
    private static final double VIP_BASE_FEE = 100.0;

    private String fullName;
    private String emailAddress;
    private String dateOfBirth;
    private long cardNumber;
    private String cardProvider;
    private String cardExpiryDate;
    private double feeToCharge;
    private int cvv;

    private String userType;
    private boolean emailValid;
    private boolean minorAndBirthday;
    private boolean minor;
    private boolean ageValid;
    private boolean cardNumberValid;
    private boolean cardStillValid;
    private boolean validCVV;

    Scanner sc = new Scanner(System.in);

    public void registration() {
        minorAndBirthday = false;
        minor = false;

        System.out.println("\n===== ERyder Registration =====");
        System.out.println("1. Register as a Regular User");
        System.out.println("2. Register as a VIP User");
        System.out.print("Enter choice: ");
        String choice = sc.next(); sc.nextLine();

        if (choice.equals("1")) userType = "Regular User";
        else userType = "VIP User";

        System.out.print("Full Name: "); fullName = sc.nextLine();
        System.out.print("Email: "); emailAddress = sc.nextLine();
        emailValid = analyseEmail(emailAddress);

        System.out.print("DOB (YYYY-MM-DD): "); dateOfBirth = sc.nextLine();
        LocalDate dob = LocalDate.parse(dateOfBirth);
        ageValid = analyseAge(dob);

        System.out.print("Card Number: "); cardNumber = sc.nextLong();
        cardNumberValid = analyseCardNumber(cardNumber);

        System.out.print("Expiry (MM/YY): "); cardExpiryDate = sc.next();
        cardStillValid = analyseCardExpiryDate(cardExpiryDate);

        System.out.print("CVV: "); cvv = sc.nextInt();
        validCVV = analyseCVV(cvv);

        finalCheckpoint();
        sc.close();
    }

    private boolean analyseEmail(String email) {
        if (email.contains("@") && email.contains(".")) return true;
        System.out.println("Invalid email! Restarting..."); registration(); return false;
    }

    private boolean analyseAge(LocalDate dob) {
        LocalDate now = LocalDate.now();
        int years = Period.between(dob, now).getYears();
        boolean isBirthday = dob.getMonth()==now.getMonth() && dob.getDayOfMonth()==now.getDayOfMonth();

        if (userType.equals("VIP User") && years>12 && years<=18) {
            if (isBirthday) { System.out.println("25% discount!"); minorAndBirthday=true; }
            else { System.out.println("20% discount!"); minor=true; }
        }

        if (years<=12 || years>120) {
            System.out.println("Age not allowed."); System.exit(0);
        }
        return true;
    }

    private boolean analyseCardNumber(long cardNum) {
        String s = String.valueOf(cardNum); int len = s.length();
        if (len<2) { System.out.println("Invalid card!"); registration(); return false; }

        if ((len==13||len==16)&&s.startsWith("4")) { cardProvider="VISA"; return true; }
        else if (len==16 && ((Integer.parseInt(s.substring(0,2))>=51&&Integer.parseInt(s.substring(0,2))<=55)||(Integer.parseInt(s.substring(0,4))>=2221&&Integer.parseInt(s.substring(0,4))<=2720))) { cardProvider="MasterCard"; return true; }
        else if (len==15 && (s.startsWith("34")||s.startsWith("37"))) { cardProvider="American Express"; return true; }
        else { System.out.println("Card not supported!"); registration(); return false; }
    }

    private boolean analyseCardExpiryDate(String expiry) {
        int m = Integer.parseInt(expiry.substring(0,2));
        int y = 2000+Integer.parseInt(expiry.substring(3,5));
        LocalDate now = LocalDate.now();
        if (y>now.getYear() || (y==now.getYear()&&m>=now.getMonthValue())) return true;
        System.out.println("Card expired!"); registration(); return false;
    }

    private boolean analyseCVV(int cvv) {
        String s = String.valueOf(cvv);
        boolean ok = (cardProvider.equals("American Express")&&s.length()==4) || ((cardProvider.equals("VISA")||cardProvider.equals("MasterCard"))&&s.length()==3);
        if (ok) return true;
        System.out.println("Invalid CVV!"); registration(); return false;
    }

    private void finalCheckpoint() {
        if (emailValid&&ageValid&&cardNumberValid&&cardStillValid&&validCVV) chargeFees();
        else { System.out.println("Registration failed!"); registration(); }
    }

    private void chargeFees() {
        if (minorAndBirthday) feeToCharge = VIP_BASE_FEE*0.75;
        else if (minor) feeToCharge = VIP_BASE_FEE*0.8;
        else feeToCharge = VIP_BASE_FEE;
        System.out.printf("Charged: %.2f%n", feeToCharge);
    }

    @Override
    public String toString() {
        String c = String.valueOf(cardNumber);
        return "\n===== Registration Success =====" +
                "\nName: "+fullName+"\nEmail: "+emailAddress+
                "\nCard: ****"+c.substring(c.length()-4)+
                "\n=================================";
    }
}

class ERyder {
    private String bikeId;
    private int battery;
    private boolean available;
    private double distance;
    private String currentUser;
    private String contact;

    public ERyder(String bikeId, int battery, boolean available, double distance) {
        this.bikeId = bikeId; this.battery = battery; this.available = available; this.distance = distance;
    }
    public ERyder(String bikeId, int battery, boolean available, double distance, String user, String contact) {
        this(bikeId, battery, available, distance); this.currentUser = user; this.contact = contact;
    }
    public void ride() { available = false; }
    public void printBikeDetails() {
        System.out.println("Bike: "+bikeId+" Battery: "+battery+"% Available: "+available);
    }
    public void printRideDetails(int min) {
        System.out.println("Ride time: "+min+" mins, Distance: "+distance);
    }
}

class Main {
    public static void main(String[] args) {
        AdminPanel admin = new AdminPanel();
        admin.userManagementOptions();
    }
}