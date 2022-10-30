import java.time.LocalDateTime;

public class FysioConsult extends Consult{
    public FysioConsult(LocalDateTime date){
        super(ConsultVariant.Fysio, date);
    }
}
