public class Prescription {
    public int id;
    public Medicine medicine;
    public String dose;

    public Prescription(int id, String dose, Medicine medicine){
        this.id = id;
        this.dose = dose;
        this.medicine = medicine;
    }

    public void viewData(){
        System.out.format("Medicine type: %s.\n",this.medicine.type.toString());
        System.out.format("Medicine name: %s.\n",this.medicine.name);
        System.out.format("Dose: %s\nj",this.dose);
    }
}
