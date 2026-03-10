public class Order {

    String side;
    int price;
    int quantity;
    long timestamp;

    public Order(String side, int price, int quantity, long timestamp) {
        this.side = side;
        this.price = price;
        this.quantity = quantity;
        this.timestamp = timestamp;
    }

    public void printOrder() {
        System.out.println(
                "Side: " + side +
                        ", Price: " + price +
                        ", Quantity: " + quantity +
                        ", Timestamp: " + timestamp
        );
    }
}