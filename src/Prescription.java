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
        System.out.format( "%-17s %s\n", "Medicijn naam: ", this.medicine.name);
        System.out.format( "%-17s %s\n", "Medicijn type: ", this.medicine.type.toString());
        System.out.format( "%-17s %s\n\n", "Dosering: ", this.dose);
    }
}
