package wordnumber;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class CurrencyIntoWords {
    private static final String[] tensNames = new String[]{"", " Ten", " Twenty", " Thirty", " Forty", " Fifty", " Sixty", " Seventy", " Eighty", " Ninety"};
    private static final String[] numNames = new String[]{" ", " One", " Two", " Three", " Four", " Five", " Six", " Seven", " Eight", " Nine", " Ten", " Eleven", " Twelve", " Thirteen", " Fourteen", " Fifteen", " Sixteen", " Seventeen", " Eighteen", " Nineteen"};

    private static String convertLessThanOneThousand(int number) {
        String soFar;
        if (number % 100 < 20) {
            soFar = numNames[number % 100];
            number /= 100;
        } else {
            soFar = numNames[number % 10];
            soFar = tensNames[(number /= 10) % 10] + soFar;
            number /= 10;
        }
        if (number == 0) {
            return soFar;
        }
        return numNames[number] + " Hundred" + soFar;
    }

    public static String convert(BigDecimal number) {
        String tradBillions;
        String tradHundredThousands;
        String tradMillions;
        String strNum = number.toString();
        int decimalDigit = 0;
        String strDecimalDigit = null;
        String[] numberArr = strNum.split("\\.");
        long wholeDigit = Long.parseLong(numberArr[0]);
        if (numberArr.length > 1) {
            decimalDigit = Integer.parseInt(numberArr[1]);
            strDecimalDigit = numberArr[1].toString();
        }
        if (wholeDigit <= 0 && decimalDigit <= 0) {
            return " ";
        }
        String snumber = Long.toString(wholeDigit);
        String mask = "000000000000";
        DecimalFormat df = new DecimalFormat(mask);
        snumber = df.format(wholeDigit);
        int billions = Integer.parseInt(snumber.substring(0, 3));
        int millions = Integer.parseInt(snumber.substring(3, 6));
        int hundredThousands = Integer.parseInt(snumber.substring(6, 9));
        int thousands = Integer.parseInt(snumber.substring(9, 12));
        switch (billions) {
            case 0: {
                tradBillions = "";
                break;
            }
            case 1: {
                tradBillions = CurrencyIntoWords.convertLessThanOneThousand(billions) + " Billion ";
                break;
            }
            default: {
                tradBillions = CurrencyIntoWords.convertLessThanOneThousand(billions) + " Billion ";
            }
        }
        String result = tradBillions;
        switch (millions) {
            case 0: {
                tradMillions = "";
                break;
            }
            case 1: {
                tradMillions = CurrencyIntoWords.convertLessThanOneThousand(millions) + " Million ";
                break;
            }
            default: {
                tradMillions = CurrencyIntoWords.convertLessThanOneThousand(millions) + " Million ";
            }
        }
        result = result + tradMillions;
        switch (hundredThousands) {
            case 0: {
                tradHundredThousands = "";
                break;
            }
            case 1: {
                tradHundredThousands = "One Thousand ";
                break;
            }
            default: {
                tradHundredThousands = CurrencyIntoWords.convertLessThanOneThousand(hundredThousands) + " Thousand ";
            }
        }
        result = result + tradHundredThousands;
        String tradThousand = CurrencyIntoWords.convertLessThanOneThousand(thousands);
        result = result + tradThousand;
        result = numberArr.length > 1 && decimalDigit > 0 ? (wholeDigit <= 0 ? strDecimalDigit + "/100 Cents Only" : result + " Pesos and " + strDecimalDigit + "/100 Cents Only") : result + " Pesos Only";
        return result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
    }

    public static String getTest() {
        String x = "xmen";
        return x;
    }
}