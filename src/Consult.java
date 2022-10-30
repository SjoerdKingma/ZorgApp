import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Consult {
    private ConsultVariant consultVariant;
    private LocalDate date;
    public static ArrayList<ConsultPrice> prices;

    public Consult(ConsultVariant consultVariant, LocalDate date){
        this.consultVariant = consultVariant;
        this.date = date;
    }
}

enum ConsultVariant{
    Tandarts,
    Fysio,
    Huisarts
}