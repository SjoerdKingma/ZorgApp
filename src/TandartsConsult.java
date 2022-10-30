import java.time.LocalDateTime;

public class TandartsConsult extends Consult{
    public TandartsConsult(LocalDateTime date){
        super(ConsultVariant.Tandarts, date);
    }
}
