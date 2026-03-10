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