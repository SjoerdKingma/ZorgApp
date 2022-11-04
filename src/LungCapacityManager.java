import Helpers.ConversionHelper;

public class LungCapacityManager {

    private static final int MAX_STARS = 10;
    LungCapacityInfo lungCapacityInfo;
    public  LungCapacityManager(LungCapacityInfo lungCapacityInfo){
        this.lungCapacityInfo = lungCapacityInfo;
    }

    public void printStarGraph(){

        //Gets the highest lung capacity value in the list
        float maxCapacity = lungCapacityInfo.getMaxValue();

        //Print star graph
        for(LungCapacityPoint point : lungCapacityInfo.getData()){
            int amountOfStars = (int)(MAX_STARS / maxCapacity * point.lungCapacity);
            System.out.format("\n[%s] %-10s (%s)", point.date, "*".repeat(amountOfStars), ConversionHelper.addTwoDecimals(point.lungCapacity));
        }
        System.out.println("");
    }

    public void addLungCapacityPoint(float value){
        LungCapacityPoint newPoint = new LungCapacityPoint(value);
        this.lungCapacityInfo.addData(newPoint);
    }
}
