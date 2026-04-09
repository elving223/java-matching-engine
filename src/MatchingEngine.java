import java.util.LinkedList;
import java.util.TreeMap;

public class MatchingEngine {

    TreeMap<Float, LinkedList<Order>> buyBook;
    TreeMap<Float, LinkedList<Order>> sellBook;
    int nextOrderId;

    public MatchingEngine() {
        buyBook = new TreeMap<>();
        sellBook = new TreeMap<>();
        nextOrderId = 1;
    }

    public void addOrder(Order order) {
        if (order.side.equals("BUY")) {
            buyBook.putIfAbsent(order.price, new LinkedList<>());
            buyBook.get(order.price).add(order);
        } else if (order.side.equals("SELL")) {
            sellBook.putIfAbsent(order.price, new LinkedList<>());
            sellBook.get(order.price).add(order);
        }
    }

    public void executeIncomingOrder(float price, int quantity, Boolean buyOrder) {

        long currentTime = System.currentTimeMillis();

        if (buyOrder) {
            while (quantity > 0 && !sellBook.isEmpty()) {
                float bestSellPrice = sellBook.firstKey();

                if (bestSellPrice > price) {
                    break;
                }

                LinkedList<Order> sellOrdersAtPrice = sellBook.get(bestSellPrice);
                Order restingSell = sellOrdersAtPrice.getFirst();

                int tradeQuantity = Math.min(quantity, restingSell.quantity);

                System.out.println("TRADE EXECUTED:");
                System.out.println("Incoming BUY matched with Sell Order ID: " + restingSell.orderId +
                        ", Price: " + bestSellPrice +
                        ", Quantity: " + tradeQuantity);

                quantity -= tradeQuantity;
                restingSell.quantity -= tradeQuantity;

                if (restingSell.quantity == 0) {
                    sellOrdersAtPrice.removeFirst();
                }

                if (sellOrdersAtPrice.isEmpty()) {
                    sellBook.remove(bestSellPrice);
                }
            }

            if (quantity > 0) {
                Order remainingBuy = new Order(nextOrderId++, "BUY", price, quantity, currentTime);
                addOrder(remainingBuy);

                System.out.println("Remaining BUY added to book:");
                remainingBuy.printOrder();
            }

        } else {
            while (quantity > 0 && !buyBook.isEmpty()) {
                float bestBuyPrice = buyBook.lastKey();

                if (bestBuyPrice < price) {
                    break;
                }

                LinkedList<Order> buyOrdersAtPrice = buyBook.get(bestBuyPrice);
                Order restingBuy = buyOrdersAtPrice.getFirst();

                int tradeQuantity = Math.min(quantity, restingBuy.quantity);

                System.out.println("TRADE EXECUTED:");
                System.out.println("Incoming SELL matched with Buy Order ID: " + restingBuy.orderId +
                        ", Price: " + bestBuyPrice +
                        ", Quantity: " + tradeQuantity);

                quantity -= tradeQuantity;
                restingBuy.quantity -= tradeQuantity;

                if (restingBuy.quantity == 0) {
                    buyOrdersAtPrice.removeFirst();
                }

                if (buyOrdersAtPrice.isEmpty()) {
                    buyBook.remove(bestBuyPrice);
                }
            }

            if (quantity > 0) {
                Order remainingSell = new Order(nextOrderId++, "SELL", price, quantity, currentTime);
                addOrder(remainingSell);

                System.out.println("Remaining SELL added to book:");
                remainingSell.printOrder();
            }
        }
    }

    public void printOrderBook() {
        System.out.println("BUY ORDERS:");

        for (Float price : buyBook.descendingKeySet()) {
            LinkedList<Order> ordersAtPrice = buyBook.get(price);
            for (Order order : ordersAtPrice) {
                order.printOrder();
            }
        }

        System.out.println("SELL ORDERS:");

        for (Float price : sellBook.keySet()) {
            LinkedList<Order> ordersAtPrice = sellBook.get(price);
            for (Order order : ordersAtPrice) {
                order.printOrder();
            }
        }
    }
}