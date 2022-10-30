import java.time.LocalDateTime;

public class HuisartsConsult extends Consult{
    public HuisartsConsult(LocalDateTime date){
        super(ConsultVariant.Huisarts, date);
    }
}
