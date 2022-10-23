import java.lang.reflect.Array;
import java.util.ArrayList;

public class LungCapacityManager {

    private static final int MaxStars = 10;
    LungCapacityInfo lungCapacityInfo;
    public  LungCapacityManager(LungCapacityInfo lungCapacityInfo){
        this.lungCapacityInfo = lungCapacityInfo;
    }

    public void printStarGraph(LungCapacityInfo lungCapacityInfo){
        for(LungCapacityPoint point : lungCapacityInfo.data){
            float maxCapacity = lungCapacityInfo.getMaxValue();
            int amountOfStars = (int)(MaxStars / maxCapacity * point.lungCapacity);
            System.out.format("[%s] %s (%s)", point.date, "*".repeat(amountOfStars), ConversionHelper.addTwoDecimals(point.lungCapacity));
        }
    }
}
