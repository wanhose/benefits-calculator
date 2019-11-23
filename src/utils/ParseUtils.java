package utils;

public abstract class ParseUtils {

    public static Double parseDouble(String sentence) {
        Double result = 0.0;

        try {
            result = Double.parseDouble(sentence);
        } catch (NumberFormatException nfe) {
            System.out.println("\nHE2ERROR. A critical error has ocurred. Please contact administrator for more information.\n");
        }

        return result;
    }

    public static int parseInt(String sentence) {
        int result = 0;

        try {
            result = Integer.parseInt(sentence);
        } catch (NumberFormatException nfe) {
            System.out.println("\nERROR. A critical error has ocurred. Please contact administrator for more information.\n");
        }

        return result;
    }

    public static String parseQuantityString(String sentence) {
        return sentence.replace(".", "");
    }

    public static String parsePriceString(String sentence) {
        return sentence.substring(0, sentence.length() - 1).replace(".", "").replace(",", ".");
    }
}