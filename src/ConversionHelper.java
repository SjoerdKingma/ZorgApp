import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class ConversionHelper {
    private ConversionHelper(){}
    public static LocalDate stringToLocalDate(String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        return LocalDate.parse(dateString, formatter);
    }
}
