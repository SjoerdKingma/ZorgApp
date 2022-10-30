import java.time.LocalDateTime;
import java.util.ArrayList;

public class HuisartsConsult extends Consult{
    public static ArrayList<ConsultType> prices;
    public HuisartsConsult(LocalDateTime date, ConsultType price){
        super(DepartmentName.Huisarts, date, price);
    }
}
