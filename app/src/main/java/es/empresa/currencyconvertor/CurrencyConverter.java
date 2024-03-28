package es.empresa.currencyconvertor;

public class CurrencyConverter {
    private static final double CONVERSION_RATE_USD_TO_EUR = 0.85;

    public static double convertUSDToEUR(double amountInUSD) {
        return amountInUSD * CONVERSION_RATE_USD_TO_EUR;
    }

    public static double convertEURToUSD(double amountInEUR) {
        return amountInEUR / CONVERSION_RATE_USD_TO_EUR;
    }
}
