import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class Consult {
    private DepartmentName consultVariant;
    private LocalDateTime date;

    private ConsultType consultType;

    public Consult(DepartmentName consultVariant, LocalDateTime date, ConsultType consultType){
        this.consultVariant = consultVariant;
        this.date = date;
        this.consultType = consultType;
    }
    public void viewData(){
        System.out.format("%-17s - %-8s (%s)\n", this.date.format(ZorgApp.dateTimeFormatter), this.consultType.getName().toUpperCase(), this.consultVariant.toString());
    }
}