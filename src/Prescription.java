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
        System.out.format("%d, %s (%s) %s\n", this.id, this.medicine.name, this.dose);
    }

    public void viewDataAsList(){
        System.out.format( "%-17s %s\n", "Medicijn id: ", this.medicine.id);
        System.out.format( "%-17s %s\n", "Medicijn naam: ", this.medicine.name);
        System.out.format( "%-17s %s\n", "Medicijn type: ", this.medicine.type.toString());
        System.out.format( "%-17s %s\n\n", "Dosering: ", this.dose);
    }
}
