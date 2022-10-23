import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class Patient
{
   private int       id;
   private String    surname;
   private String    firstName;
   private LocalDate dateOfBirth;
   private int weight;
   private float length;

   private float lungCapacity;

   private ArrayList<Prescription> prescriptions;

   public int getPatientId(){return id;}
   public String getSurname()
   {
      return surname;
   }
   public void setSurname(String value)
   {
      this.surname = value;
   }
   public String getFirstName()
   {
      return firstName;
   }
   public void setFirstName(String value)
   {
      this.firstName = value;
   }

   public int getWeight(){ return weight;}
   public void setWeight(int weight){this.weight = weight;}

   public float getLength(){return this.length;}
   public void setLength(float length){this.length = length;}

   public LocalDate getDateOfBirth(){return this.dateOfBirth;}

   public void setDateOfBirth(LocalDate dateOfBirth){this.dateOfBirth = dateOfBirth;}

   public ArrayList<Prescription> getPrescriptions(){
      return this.prescriptions;
   }

   public float getLungCapacity(){
      return this.lungCapacity;
   }

   public void  setLungCapacity(float lungCapacity){
      this.lungCapacity = lungCapacity;
   }

   Patient( int id, String surname, String firstName, LocalDate dateOfBirth, int weight, float length, ArrayList<Prescription> prescriptions)
   {
      this.id = id;
      this.surname = surname;
      this.firstName = firstName;
      this.dateOfBirth = dateOfBirth;
      this.weight = weight;
      this.length = length;
      this.prescriptions = prescriptions;
   }
   // View patient data.
   public void viewData()
   {
      System.out.format( "===== Patiënt id=%d ==============================\n", id );
      System.out.format( "%-17s %s\n", "Achternaam:", surname );
      System.out.format( "%-17s %s\n", "Voornaam:", firstName );
      System.out.format( "%-17s %s\n", "Leeftijd: ", calcAge() );
      System.out.format( "%-17s %s\n", "Geboortedatum:", dateOfBirth );
      System.out.format( "%-17s %s kg\n", "Gewicht: ", getWeight() );
      System.out.format( "%-17s %s m\n", "Lengte: ", ConversionHelper.addTwoDecimals(length) );
      System.out.format( "%-17s %s\n", "BMI: ", ConversionHelper.addTwoDecimals(calcBMI()) );

      System.out.println("\nMedicijnen lijst: ");
      this.prescriptions.forEach(prescription -> prescription.viewDataAsList());
   }

   // Shorthand for a Patient's full name
   public String fullName()
   {
      return String.format( "%s %s [%s]", firstName, surname, dateOfBirth.toString() );
   }

   //Calculates the Patient's age
   public int calcAge()
   {
      Period timePeriod = Period.between(dateOfBirth, LocalDate.now());
      return timePeriod.getYears();
   }

   public float calcBMI(){
      return weight / (length * length);
   }
}
