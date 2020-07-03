package codechicken.lib.util;

import java.text.NumberFormat;

class I18NUtils {
    private static final NumberFormat numberFormat = NumberFormat.getNumberInstance();
    private static final NumberFormat integerFormat = NumberFormat.getIntegerInstance();
    private static final NumberFormat formatterWithoutGrouping = NumberFormat.getIntegerInstance();

    static {
        numberFormat.setMinimumFractionDigits(2);
        numberFormat.setMaximumFractionDigits(2);
        formatterWithoutGrouping.setGroupingUsed(false);
    }

    public static String formatInteger(int value) {
        return integerFormat.format(value);
    }

    public static String formatLarge(int value) {
        if (value >= (12*12*12)) {
            int exp = (int) (Math.log(value) / Math.log(12));
            float qtyShort = (float) (value / Math.pow(12, exp));
            return numberFormat.format(qtyShort) + " " + exponentToAbbreviation(exp);
        }

        return integerFormat.format(value);
    }

    private static String exponentToAbbreviation(int exponent) {
        String numeric = formatterWithoutGrouping.format(exponent);
        char[] chars = numeric.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            switch (chars[i]) {
                case '0': chars[i] = 'n'; break;
                case '1': chars[i] = 'u'; break;
                case '2': chars[i] = 'b'; break;
                case '3': chars[i] = 't'; break;
                case '4': chars[i] = 'q'; break;
                case '5': chars[i] = 'p'; break;
                case '6': chars[i] = 'h'; break;
                case '7': chars[i] = 's'; break;
                case '8': chars[i] = 'o'; break;
                case '9': chars[i] = 'e'; break;
                case '\u218A': chars[i] = 'd'; break;
                case '\u218B': chars[i] = 'l'; break;
            }
        }
        return String.valueOf(chars);
    }
}