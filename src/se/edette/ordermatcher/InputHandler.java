package se.edette.ordermatcher;

import java.util.Scanner;

/**
 * OrderMatcher - InputHandler
 * Takes input from the user and parses it into usable data.
 *
 * Author: Elise Edette
 * Date: 2016-06-16
 */
public class InputHandler {
    private Scanner userInput;
    private String rawCommand, command, arguments;
    private int price, volume;
    private boolean normalizeCase = false;

    public InputHandler() {
        userInput = new Scanner(System.in);
    }

    public void poll() {
        rawCommand = userInput.nextLine();
        parse();
    }

    public void doNormalizeCase(boolean bNormalize) {
        /**
         * Normalize the case of the input (command) to lower case.
         * */
        normalizeCase = bNormalize;
    }

    public String getCommand() {
        return normalizeCase ? command.toLowerCase() : command;
    }

    public int getPrice() {
        return price;
    }

    public int getVolume() {
        return volume;
    }

    private void parse() {
        /**
         * Parse the raw input into the expected pieces.
         * */

        // Get the command part of the input.
        String[] bits = rawCommand.split("\\s+", 2);
        command = bits[0];

        // Try to extract the arguments
        if(bits.length > 1) {
            arguments = bits[1];
            bits = arguments.split("@");

            try {
                volume = Integer.parseInt(bits[0]);
            } catch (Exception e) {
                // Failed to parse, default to zero
                volume = 0;
            }

            try {
                price = Integer.parseInt(bits[1]);
            } catch (Exception e) {
                // Failed to parse, default to zero
                price = 0;
            }
        }
    }
}
