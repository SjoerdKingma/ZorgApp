enum MedicineType{
    Pijnstiller,
    Antibiotica
}

public class Medicine {
    public int id;
    public String name;
    public MedicineType type;

    public Medicine(int id, String name, MedicineType type){
        this.id = id;
        this.name = name;
        this.type = type;
    }
}
