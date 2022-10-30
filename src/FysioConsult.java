import java.time.LocalDateTime;
import java.util.ArrayList;

public class FysioConsult extends Consult{

    public static ArrayList<ConsultType> prices;
    public FysioConsult(LocalDateTime date, ConsultType price){
        super(DepartmentName.Fysio, date, price);
    }
}
