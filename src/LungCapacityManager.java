import java.lang.reflect.Array;
import java.util.ArrayList;

public class LungCapacityManager {

    private static final int MaxStars = 10;
    LungCapacityInfo lungCapacityInfo;
    public  LungCapacityManager(LungCapacityInfo lungCapacityInfo){
        this.lungCapacityInfo = lungCapacityInfo;
    }

    public void printStarGraph(){
        for(LungCapacityPoint point : lungCapacityInfo.getData()){
            float maxCapacity = lungCapacityInfo.getMaxValue();
            int amountOfStars = (int)(MaxStars / maxCapacity * point.lungCapacity);
            System.out.format("\n[%s] %-10s (%s)", point.date, "*".repeat(amountOfStars), ConversionHelper.addTwoDecimals(point.lungCapacity));
        }
        System.out.println("");
    }

    public void addLungCapacityPoint(float value){
        LungCapacityPoint newPoint = new LungCapacityPoint(value);
        this.lungCapacityInfo.addData(newPoint);
    }
}
