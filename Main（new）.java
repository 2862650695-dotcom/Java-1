public class Main {
    public static void main(String[] args) {
        ERyder bike1 = new ERyder("B001", 85, true, 120.5);
        bike1.printBikeDetails();
        bike1.printRideDetails(30);

        ERyder bike2 = new ERyder("B002", 60, true, 85.2, "user_john", "123-456-7890");
        bike2.ride();
        bike2.printBikeDetails();
        bike2.printRideDetails(45);

    }

}
