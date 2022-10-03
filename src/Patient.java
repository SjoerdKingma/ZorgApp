import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class Patient
{
   private static final int STOP      = 0;
   private static final int SURNAME     = 1;
   private static final int FIRSTNAME   = 2;
   private static final int DATEOFBIRTH = 3;
   private static final int WEIGHT = 4;
   private static final int LENGTH = 5;

   private int       id;
   private String    surname;
   private String    firstName;
   private LocalDate dateOfBirth;
   private int weight;
   private float length;
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
      System.out.format( "===== Patient id=%d ==============================\n", id );
      System.out.format( "%-17s %s\n", "Surname:", surname );
      System.out.format( "%-17s %s\n", "FirstName:", firstName );
      System.out.format( "%-17s %s\n", "Age: ", calcAge() );
      System.out.format( "%-17s %s\n", "Date of birth:", dateOfBirth );
      System.out.format( "%-17s %s\n", "Weight: ", getWeight() );
      System.out.format( "%-17s %s\n", "Length: ", ConversionHelper.addTwoDecimals(length) );
      System.out.format( "%-17s %s\n", "BMI: ", ConversionHelper.addTwoDecimals(calcBMI()) );

      System.out.format("\nPatiënt medicijnen:\n\n");
      prescriptions.forEach(x -> x.viewData());
   }

   //View Patient data but only display editable fields.
   //Adds an index to each option so the user can make a selection.
   public void viewEditableData(){
      System.out.format( "===== Patient id=%d ==============================\n", id );
      System.out.format("%d: Stop\n", STOP);
      System.out.format( "%d: %-17s %s\n",SURNAME, "Surname:", surname );
      System.out.format( "%d: %-17s %s\n",FIRSTNAME, "FirstName:", firstName );
      System.out.format( "%d: %-17s %s\n",DATEOFBIRTH, "Date of birth:", dateOfBirth );
      System.out.format( "%d: %-17s %s\n",WEIGHT, "Weight: ", getWeight() );
      System.out.format( "%d: %-17s %s\n",LENGTH, "Length: ", ConversionHelper.addTwoDecimals(getLength()) );
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
