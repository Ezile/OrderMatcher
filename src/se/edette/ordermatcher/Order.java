package se.edette.ordermatcher;

import java.util.Date;

/**
 * OrderMatcher - Order
 *
 * Author: Elise Edette
 * Date: 2016-06-17
 */
public class Order implements Comparable<Order>{
    private static int idc = 0;      // instance counter used for the id.
    private int price, volume, id;
    private long timeCreated;

    public Order(int price, int volume) {
        idc++;
        id = idc;
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

    public int trade(int volumeToTrade) {
        /**
         * Deducts the specified amount from the volume of this order.
         * Returns the remaining volume after the trade.
         *
         * */

        if (volumeToTrade > volume)
            throw new IllegalArgumentException(Locale.ERROR_VOLUME_EXCEEDS);

        volume -= volumeToTrade;

        return volume;
    }

    @Override
    public String toString() {
        String head = Locale.ORDER;
        String tail = ".";
        String date = getDate().toString();

        return head + " (id:" + getId() + "): Volume: " + getVolume() + ", Price: " + getPrice() + " [total: " + getPrice() * getVolume() + "]" + " @ " + date + tail;
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
