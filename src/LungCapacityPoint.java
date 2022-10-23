import java.time.LocalDate;

public class LungCapacityPoint {
    public LocalDate date;
    public float lungCapacity;

    public LungCapacityPoint(float lungCapacity) {
        this.lungCapacity = lungCapacity;
        this.date = LocalDate.now();
    }

    public LungCapacityPoint(float lungCapacity, LocalDate date) {
        this.lungCapacity = lungCapacity;
        this.date = date;
    }
}
