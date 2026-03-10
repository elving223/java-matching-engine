public class Order {

    String side;
    int price;
    int quantity;
    long timestamp;
    int orderId;

    public Order(int orderId, String side, int price, int quantity, long timestamp) {
        this.orderId = orderId;
        this.side = side;
        this.price = price;
        this.quantity = quantity;
        this.timestamp = timestamp;
    }

    public void printOrder() {
        System.out.println(
                "Order ID: " + orderId +
                        ", Side: " + side +
                        ", Price: " + price +
                        ", Quantity: " + quantity +
                        ", Timestamp: " + timestamp
        );
    }
}