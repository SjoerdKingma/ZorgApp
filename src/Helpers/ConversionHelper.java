package Helpers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class ConversionHelper {
    private ConversionHelper(){}
    public static LocalDate stringToLocalDate(String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        return LocalDate.parse(dateString, formatter);
    }
    public static String addTwoDecimals(float number){
        return String.format(java.util.Locale.US,"%.2f", number); //Shows 2 decimals for a float value. Using the US local time to avoid the result appearing in a comma instead of period symbol.
    }
}
