package main.java.ru.clevertec.check.util;

public class Constant {

    private Constant() {
    }

    public static final String PRODUCTS_CSV = "./src/main/resources/products.csv";
    public static final String DISCOUNT_CARDS_CSV = "./src/main/resources/discountCards.csv";
    public static final int WHOLESALE_DISCOUNT_IN_PERCENT = 10;
    public static final int CUSTOM_DISCOUNT_AMOUNT_IN_PERCENT = 2;
    public static final int WITHOUT_DISCOUNT = 0;
    public static final int QUANTITY_FOR_WHOLESALE = 5;
    public static final String RESULT_CSV = "result.csv";
    public static final String ERROR_CSV = "error.csv";
    public static final String EQUAL_REGEX = "=";
    public static final String SEMICOLON = ";";
    public static final String HYPHEN_REGEX = "-";
    public static final String POINT_REGEX = ".";
    public static final String COMMA_REGEX = ",";
    public static final String PERCENT_SIGN = "%";
    public static final String DATE_FORMAT = "dd.MM.yyyy";
    public static final String TIME_FORMAT = "HH:mm:ss";
    public static final String PRICE_FORMAT = "%.2f$";
}
