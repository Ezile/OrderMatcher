package se.edette.ordermatcher;

import java.util.Scanner;

/**
 * OrderMatcher
 *
 * Author: Elise Edette
 * Date: 2016-06-16
 */
public class Main {

    public static void main(String[] args) {
        InputHandler input = new InputHandler();
        input.doNormalizeCase(true);
        OrderBook book1 = new OrderBook();
        boolean isRunning = true;

        // Display welcome message
        say(Locale.WELCOME);

        while(isRunning) {
            input.poll();

            switch(input.getCommand()){
                case Locale.COMMAND.HELP:
                    say(Locale.HELP);
                    break;
                case Locale.COMMAND.QUIT:
                    isRunning = false;
                    break;
                case Locale.COMMAND.BUY:
                    try {
                        book1.addBuyOrder(input.getPrice(), input.getVolume());
                        say(Locale.ORDER_SUCCESS);
                    } catch (IllegalArgumentException e) {
                        say(e.getMessage());
                    }
                    break;
                case Locale.COMMAND.SELL:
                    try {
                        book1.addSellOrder(input.getPrice(), input.getVolume());
                        say(Locale.ORDER_SUCCESS);
                    } catch (IllegalArgumentException e) {
                        say(e.getMessage());
                    }
                    break;
                case Locale.COMMAND.PRINT:
                    say(book1.status());
                    break;
                default:
                    say(Locale.ERROR_COMMAND_UNKNOWN);
            }
        }

        // Display quit message
        say(Locale.GOODBYE);
    }

    private static void say(String message) {
        say(message, true);
    }
    private static void say(String message, boolean newline) {
        message += newline ? "\n" : "";
        System.out.print(message);
    }
}
