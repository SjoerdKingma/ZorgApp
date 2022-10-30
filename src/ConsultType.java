public class ConsultType {
    private String name;
    private float price;

    public String getName(){
        return this.name;
    }

    public float getPrice(){
        return this.price;
    }

    public ConsultType(String name, float price){
        this.name = name;
        this.price = price;
    }
}
