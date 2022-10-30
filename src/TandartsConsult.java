import java.time.LocalDate;

public class TandartsConsult extends Consult{
    public TandartsConsult(LocalDate date){
        super(ConsultVariant.Tandarts, date);
    }
}
