public class Main {

    public static void main(String[] args) {

        System.out.println("Matching engine project started!");

        Order order1 = new Order("BUY", 100, 10, System.currentTimeMillis());

        order1.printOrder();

    }

}