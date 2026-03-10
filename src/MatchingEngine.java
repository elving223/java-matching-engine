import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MatchingEngine {

    ArrayList<Order> buyOrders;
    ArrayList<Order> sellOrders;

    public MatchingEngine() {
        buyOrders = new ArrayList<>();
        sellOrders = new ArrayList<>();
    }

    public void addOrder(Order order) {
        if (order.side.equals("BUY")) {
            buyOrders.add(order);
            sortBuyOrders();
        } else if (order.side.equals("SELL")) {
            sellOrders.add(order);
            sortSellOrders();
        }
    }

    public void sortBuyOrders() {
        Collections.sort(buyOrders, new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                if (o1.price != o2.price) {
                    return o2.price - o1.price;
                }
                return Long.compare(o1.timestamp, o2.timestamp);
            }
        });
    }

    public void sortSellOrders() {
        Collections.sort(sellOrders, new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                if (o1.price != o2.price) {
                    return o1.price - o2.price;
                }
                return Long.compare(o1.timestamp, o2.timestamp);
            }
        });
    }

    public void matchOrders() {
        while (!buyOrders.isEmpty() && !sellOrders.isEmpty()) {

            Order bestBuy = buyOrders.get(0);
            Order bestSell = sellOrders.get(0);

            if (bestBuy.price >= bestSell.price) {

                int tradeQuantity = Math.min(bestBuy.quantity, bestSell.quantity);
                int tradePrice = bestSell.price;

                System.out.println("TRADE EXECUTED:");
                System.out.println("Buy Order ID: " + bestBuy.orderId +
                        ", Sell Order ID: " + bestSell.orderId +
                        ", Price: " + tradePrice +
                        ", Quantity: " + tradeQuantity);

                bestBuy.quantity -= tradeQuantity;
                bestSell.quantity -= tradeQuantity;

                if (bestBuy.quantity == 0) {
                    buyOrders.remove(0);
                }

                if (bestSell.quantity == 0) {
                    sellOrders.remove(0);
                }

            } else {
                break;
            }
        }
    }

    public void printOrderBook() {
        System.out.println("BUY ORDERS:");
        for (Order order : buyOrders) {
            order.printOrder();
        }

        System.out.println("SELL ORDERS:");
        for (Order order : sellOrders) {
            order.printOrder();
        }
    }
}