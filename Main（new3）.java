class Main {
    public static void main(String[] args) {
        ERyder bike1 = new ERyder("B001", 85, true, 120.5);
        bike1.printBikeDetails();
        bike1.printRideDetails(30);

        ERyder bike2 = new ERyder("B002", 60, true, 85.2, "user_john", "123-456-7890");
        bike2.ride();
        bike2.printBikeDetails();
        bike2.printRideDetails(45);

        System.out.println("\n========== Feedback test ==========\n");
        String sent1 = "I was very satisfied with the service.";
        String sent2 = "The e-Bike is quite comfortable to ride.";
        String sent3 = "The battery life of the e-Bike is impressive.。";
        String sent4 = "The customer support was helpful and responsive.";
        String sent5 = "I would recommend this electric bike to my friends and family.";

        Feedback feedback = new Feedback("Zhang", "San", "zhangsan@example.com");
        feedback.analyseFeedback(true, sent1, sent2, sent3, sent4, sent5);
        System.out.println(feedback);

        System.out.println("\n========== User Registration ==========");
        UserRegistration user = new UserRegistration();
        user.registration();
        System.out.println(user);
    }
}