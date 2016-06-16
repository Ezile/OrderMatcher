package se.edette.ordermatcher;

import java.util.Date;

/**
 * OrderMatcher - Order
 *
 * Author: Elise Edette
 * Date: 2016-06-16
 */
public class Order implements Comparable<Order>{
    private static int id = 0;      // unique order id.
    private int price, volume;
    private long timeCreated;

    public Order(int price, int volume) {
        id++;
        timeCreated = System.currentTimeMillis();
        this.price = price;
        this.volume = volume;
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public int getVolume() {
        return volume;
    }

    public Date getDate() {
        return new Date(timeCreated);
    }

    @Override
    public String toString() {
        String head = "Order";
        String tail = ".";
        String date = getDate().toString();

        return head + " (" + getId() + "): Price: " + getPrice() + ", Volume: " + getVolume() + " @ " + date + tail;
    }

    @Override
    public int compareTo(Order other) {
        // Order by price, date.
        if (Integer.compare(price, other.price) != 0) {
            return Integer.compare(price, other.price);
        } else {
            return Long.compare(timeCreated, other.timeCreated);
        }
    }
}
