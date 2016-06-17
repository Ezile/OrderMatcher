package se.edette.ordermatcher;

/**
 * OrderMatcher
 *
 * Author: Elise Edette
 * Date: 2016-06-17
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
                        // Add the buy order.
                        book1.addBuyOrder(input.getPrice(), input.getVolume());

                        // Report success.
                        //say(Locale.ORDER_SUCCESS);    // Commented away to conform to spec.

                        // Execute trading and display results if any.
                        String tradingResult = book1.trade();
                        if (tradingResult != null)
                            say(tradingResult);
                    } catch (IllegalArgumentException e) {
                        say(e.getMessage());
                    }
                    break;
                case Locale.COMMAND.SELL:
                    try {
                        // Add the sell order to the book.
                        book1.addSellOrder(input.getPrice(), input.getVolume());

                        // Report success.
                        //say(Locale.ORDER_SUCCESS);    // Commented away to conform to spec.

                        // Execute trading and display results if any.
                        String tradingResult = book1.trade();
                        if (tradingResult != null)
                            say(tradingResult);
                    } catch (IllegalArgumentException e) {
                        say(e.getMessage());
                    }
                    break;
                case Locale.COMMAND.PRINT:
                    say(book1.status());
                    break;
                case "debug":
                    say(book1.toString());
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
