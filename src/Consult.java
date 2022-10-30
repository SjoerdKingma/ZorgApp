import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class Consult {
    private ConsultVariant consultVariant;
    private LocalDateTime date;
    public static ArrayList<ConsultPrice> prices;

    public Consult(ConsultVariant consultVariant, LocalDateTime date){
        this.consultVariant = consultVariant;
        this.date = date;
    }
}

enum ConsultVariant{
    Tandarts,
    Fysio,
    Huisarts
}