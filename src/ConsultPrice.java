public class ConsultPrice {
    private String consultType;
    private float price;

    public String getConsultType(){
        return this.consultType;
    }

    public float getPrice(){
        return this.price;
    }

    public ConsultPrice(String consultType, float price){
        this.consultType = consultType;
        this.price = price;
    }
}
