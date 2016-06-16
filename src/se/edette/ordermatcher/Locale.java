package se.edette.ordermatcher;

import java.io.File;

/**
 * OrderMatcher - Locale
 * Handles localization of the program.
 * (loading of locale data out of scope for project)
 *
 * Author: Elise Edette
 * Date: 2016-06-16
 */
public class Locale {
    private Locale() {}

    static final String WELCOME = " ### Order Matcher v1.0 ###\n     Order book created. Ready for use.\n     For help type 'help'\n\n";
    static final String GOODBYE = "Thank you for using Order Matcher!";
    static final String HELP = "Available commands:\n" +
            "BUY [volume]@[price]: Creates a buy order with the specified volume at the specified price.\n" +
            "SELL [volume]@[price]: Creates a sell order with the specified volume at the specified price.\n" +
            "PRINT: displays all pending orders.\n" +
            "HELP: displays this help message.\n" +
            "QUIT: quits Order matcher\n";
    static final String ERROR_ARGUMENT_VOLUME = "The volume of the order must be positive.";
    static final String ERROR_ARGUMENT_PRICE = "The price of the order must be positive.";
    static final String ERROR_COMMAND_UNKNOWN = "Unknown command.";
    static final String BUY = "BUY";
    static final String SELL = "SELL";
    static final String NO_ORDERS = " < no records > ";
    static final String ORDER_SUCCESS = "Order placed successfully.";

    static class COMMAND{
        static final String HELP = "help";
        static final String QUIT = "quit";
        static final String BUY = "buy";
        static final String SELL = "sell";
        static final String PRINT = "print";
    }
}
