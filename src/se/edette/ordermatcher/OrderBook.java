package se.edette.ordermatcher;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * OrderMatcher - OrderBook
 *
 * Author: Elise Edette
 * Date: 2016-06-17
 */
public class OrderBook {
    private Set<Order> sellOrders;
    private Set<Order> buyOrders;

    public OrderBook() {
        sellOrders = new TreeSet<>();
        // The natural ordering of an Order is: price ASC, date ASC
        // Let's override it for buyOrders as: price DESC, date ASC with a comparator
        buyOrders = new TreeSet<>((a, b) -> Integer.compare(b.getPrice(), a.getPrice()) != 0 ? Integer.compare(b.getPrice(), a.getPrice()) : Long.compare(a.getTimeEpochMS(), b.getTimeEpochMS()));
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
        /**
         * Returns the status (that is the list of sell/buy orders).
         * Used with the PRINT command.
         * */

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

    public String trade() {
        /**
         * Executes trades if there are matching buy and sell orders.
         * Returns a string of trades if any, else null.
         * */

        int tradeCount = 0;
        boolean endOfTrades = false;
        String tradeOutput = "";

        if (buyOrders.size() > 0 && sellOrders.size() > 0) {
            Iterator<Order> sellIterator = sellOrders.iterator();
            while (sellIterator.hasNext() && !endOfTrades) {
                Order sellOrder = sellIterator.next();

                Iterator<Order> buyIterator = buyOrders.iterator();
                while (buyIterator.hasNext() && !endOfTrades) {
                    Order buyOrder = buyIterator.next();

                    if (sellOrder.getPrice() <= buyOrder.getPrice()) {
                        int tradePrice, tradeVolume;

                        if (sellOrder.getVolume() == buyOrder.getVolume()) {
                            tradePrice = sellOrder.getPrice();
                            tradeVolume = sellOrder.getVolume();
                        } else if (sellOrder.getVolume() > buyOrder.getVolume()) {
                            tradePrice = sellOrder.getPrice();
                            tradeVolume = buyOrder.getVolume();
                        } else {
                            tradePrice = sellOrder.getPrice();
                            tradeVolume = sellOrder.getVolume();
                        }

                        // Trade the orders
                        sellOrder.trade(tradeVolume);
                        buyOrder.trade(tradeVolume);

                        // Check if orders have been exhausted and remove those.
                        if (sellOrder.getVolume() == 0) {
                            sellIterator.remove();
                            // The outer loop (sellOrders) must be iterated forward at this point
                            // as the inner loop might have more items to match
                            if (sellIterator.hasNext()) {
                                if (buyIterator.hasNext())
                                    sellOrder = sellIterator.next();
                            } else {
                                endOfTrades = true;
                            }
                        }
                        if (buyOrder.getVolume() == 0) buyIterator.remove();

                        tradeCount++;
                        tradeOutput += Locale.TRADE + " " + tradeVolume + "@" + tradePrice + "\n";
                    } else {
                        endOfTrades = true;
                    }
                }
            }
        }

        if (tradeCount > 0)
            return tradeOutput;
        else
            return null;
    }

    @Override
    public String toString() {
        String retval = "";

        retval += Locale.BOOK + " " + Locale.SELL + " " + Locale.ORDER + " (" + sellOrders.size() + "):\n";

        if (sellOrders.size() > 0) {
            for (Order order : sellOrders) {
                retval += " " + order.toString() + "\n";
            }
        } else {
            retval += Locale.NO_ORDERS + "\n";
        }

        retval += Locale.BOOK + " " + Locale.BUY + " " + Locale.ORDER + " (" + buyOrders.size() + "):\n";

        if (buyOrders.size() > 0) {
            for (Order order : buyOrders) {
                retval += " " + order.toString() + "\n";
            }
        } else {
            retval += Locale.NO_ORDERS + "\n";
        }

        return retval;
    }
}
