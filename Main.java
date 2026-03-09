public class Main {
    public static void main(String[] args) {
        ERyder bike1 = new ERyder();
        bike1.printBikeDetails();

        System.out.println("------------------------");

        ERyder bike2 = new ERyder("EB-001", 75, true, 125.5);
        bike2.ride();
        bike2.printBikeDetails();
    }

}
