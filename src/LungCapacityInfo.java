import java.util.ArrayList;
import java.util.Comparator;

public class LungCapacityInfo {
    private ArrayList<LungCapacityPoint> data;
    private float maxValue;
    public ArrayList<LungCapacityPoint> getData() {
        return data;
    }
    public float getMaxValue(){
        return this.maxValue;
    }

    public LungCapacityInfo(){
        new LungCapacityInfo(new ArrayList<LungCapacityPoint>());
    }

    public LungCapacityInfo(ArrayList<LungCapacityPoint> data){
        this.data = data;
        this.maxValue = calculateMaxValue(data);
    }
    public void addData(LungCapacityPoint item) {
        this.data.add(item);

        if (item.lungCapacity > maxValue){
            maxValue = item.lungCapacity; //Set new max value
        }
    }

    private float calculateMaxValue(ArrayList<LungCapacityPoint> data){
        return data.stream().max(Comparator.comparing(x -> x.lungCapacity)).get().lungCapacity;
    }
}
