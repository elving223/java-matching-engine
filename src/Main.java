public class Main {

    public static void main(String[] args) {

        System.out.println("Matching engine project started!");

        MatchingEngine engine = new MatchingEngine();

        Order order1 = new Order(1, "SELL", 15.0f, 3000, System.currentTimeMillis());
        Order order2 = new Order(2, "SELL", 15.5f, 4000, System.currentTimeMillis() + 1);
        Order order3 = new Order(3, "SELL", 15.5f, 2000, System.currentTimeMillis() + 2);
        Order order4 = new Order(4, "SELL", 16.0f, 5000, System.currentTimeMillis() + 3);

        engine.addOrder(order1);
        engine.addOrder(order2);
        engine.addOrder(order3);
        engine.addOrder(order4);

        System.out.println("\nORDER BOOK BEFORE INCOMING ORDER:");
        engine.printOrderBook();

        System.out.println("\nEXECUTING INCOMING BUY ORDER...");
        engine.executeIncomingOrder(15.5f, 10000, true);

        System.out.println("\nORDER BOOK AFTER INCOMING ORDER:");
        engine.printOrderBook();
    }
}