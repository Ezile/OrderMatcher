package se.edette.ordermatcher;

import java.util.Set;
import java.util.TreeSet;

/**
 * OrderMatcher - OrderBook
 *
 * Author: Elise Edette
 * Date: 2016-06-16
 */
public class OrderBook {
    private Set<Order> sellOrders;
    private Set<Order> buyOrders;

    public OrderBook() {
        sellOrders = new TreeSet<>();
        buyOrders = new TreeSet<>();
    }

    public boolean addBuyOrder(int price, int volume) {
        if (price <= 0) throw new IllegalArgumentException(Locale.ERROR_ARGUMENT_PRICE);
        if (volume <= 0) throw new IllegalArgumentException(Locale.ERROR_ARGUMENT_VOLUME);

        buyOrders.add(new Order(price, volume));

        return true;
    }

    public boolean addSellOrder(int price, int volume) {
        if (price <= 0) throw new IllegalArgumentException(Locale.ERROR_ARGUMENT_PRICE);
        if (volume <= 0) throw new IllegalArgumentException(Locale.ERROR_ARGUMENT_VOLUME);

        sellOrders.add(new Order(price, volume));

        return true;
    }

    public String status() {
        String retval = "--- " + Locale.SELL + " ---\n";

        if (sellOrders.size() > 0) {
            for (Order order : sellOrders) {
                retval += Locale.SELL + " " + order.getVolume() + "@" + order.getPrice() + "\n";
            }
        } else {
            retval += Locale.NO_ORDERS + "\n";
        }

        retval += "--- " + Locale.BUY + " ---\n";

        if (buyOrders.size() > 0) {
            for (Order order : buyOrders) {
                retval += Locale.BUY + " " + order.getVolume() + "@" + order.getPrice() + "\n";
            }
        } else {
            retval += Locale.NO_ORDERS + "\n";
        }

        return retval;
    }
}
