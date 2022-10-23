import java.util.ArrayList;
import java.util.Comparator;

public class LungCapacityInfo {
    public ArrayList<LungCapacityPoint> data;
    private float maxValue;

    public float getMaxValue(){
        return this.maxValue;
    }

    public LungCapacityInfo(ArrayList<LungCapacityPoint> data){
        this.data = data;
        this.maxValue = calculateMaxValue(data);
    }

    private float calculateMaxValue(ArrayList<LungCapacityPoint> data){
        return data.stream().max(Comparator.comparing(x -> x.lungCapacity)).get().lungCapacity;
    }
}
