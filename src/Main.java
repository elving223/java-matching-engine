public class Main {

    public static void main(String[] args) {

        System.out.println("Matching engine project started!");

        MatchingEngine engine = new MatchingEngine();

        Order order1 = new Order(1, "BUY", 100, 10, System.currentTimeMillis());
        Order order2 = new Order(2, "SELL", 99, 5, System.currentTimeMillis() + 1);
        Order order3 = new Order(3, "SELL", 100, 3, System.currentTimeMillis() + 2);
        Order order4 = new Order(4, "BUY", 101, 4, System.currentTimeMillis() + 3);

        engine.addOrder(order1);
        engine.addOrder(order2);
        engine.addOrder(order3);
        engine.addOrder(order4);

        System.out.println("\nORDER BOOK BEFORE MATCHING:");
        engine.printOrderBook();

        System.out.println("\nMATCHING ORDERS...");
        engine.matchOrders();

        System.out.println("\nORDER BOOK AFTER MATCHING:");
        engine.printOrderBook();
    }
}