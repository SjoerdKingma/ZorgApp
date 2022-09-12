import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;

public class Patient
{
   private static final int RETURN      = 0;
   private static final int SURNAME     = 1;
   private static final int FIRSTNAME   = 2;
   private static final int DATEOFBIRTH = 3;

   private int       id;
   private String    surname;
   private String    firstName;
   private LocalDate dateOfBirth;
   private int weight;
   private float length;

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

   Patient( int id, String surname, String firstName, LocalDate dateOfBirth, int weight, float length)
   {
      this.id = id;
      this.surname = surname;
      this.firstName = firstName;
      this.dateOfBirth = dateOfBirth;
      this.weight = weight;
      this.length = length;
   }
   // Display patient data.
   public void viewData()
   {
      System.out.format( "===== Patient id=%d ==============================\n", id );
      System.out.format( "%-17s %s\n", "Surname:", surname );
      System.out.format( "%-17s %s\n", "FirstName:", firstName );
      System.out.format( "%-17s %s\n", "Age: ", calcAge() );
      System.out.format( "%-17s %s\n", "Date of birth:", dateOfBirth );
      System.out.format( "%-17s %s\n", "Weight: ", getWeight() );
      System.out.format( "%-17s %s\n", "Length: ", addTwoDecimals(length) );

      System.out.format( "%-17s %s\n", "BMI: ", addTwoDecimals(calcBMI()) );
   }

   //View Patient data but only display editable fields.
   //Adds an index to each option so the user can make a selection.
   public void viewEditableData(){

      int columnHeader = 0; //Starting at 1 instead of 0 because 0 is reserved for 0. Stop

      System.out.format( "===== Patient id=%d ==============================\n", id );
      System.out.format("%d: Stop\n", columnHeader);
      columnHeader++;
      System.out.format( "%d: %-17s %s\n",columnHeader, "Surname:", surname );
      columnHeader++;
      System.out.format( "%d: %-17s %s\n",columnHeader, "FirstName:", firstName );
      columnHeader++;
      System.out.format( "%d: %-17s %s\n",columnHeader, "Date of birth:", dateOfBirth );
      columnHeader++;
      System.out.format( "%d: %-17s %s\n",columnHeader, "Weight: ", getWeight() );
      columnHeader++;
      System.out.format( "%d: %-17s %s\n",columnHeader, "Length: ", addTwoDecimals(length) );
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

   public String addTwoDecimals(float number){
      return String.format(java.util.Locale.US,"%.2f", number); //Shows 2 decimals for the length in meters. Using the US local time to avoid the result appearing in a comma instead of period symbol.
   }
}
