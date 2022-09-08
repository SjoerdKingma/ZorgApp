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

   public String getSurname()
   {
      return surname;
   }
   public String getFirstName()
   {
      return firstName;
   }

   public int getWeight(){ return weight;}
   public void setWeight(int weight){this.weight = weight;}

   public float getLength(){return this.length;}
   public void setLength(int length){this.length = length;}

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
      System.out.format( "%-17s %s\n", "Age: ", age() );
      System.out.format( "%-17s %s\n", "Date of birth:", dateOfBirth );
      System.out.format( "%-17s %s\n", "Weight: ", getWeight() );

      String lengthStr = String.format(java.util.Locale.US,"%.2f", getLength()); //Shows 2 decimals for the length in meters.
      System.out.format( "%-17s %s\n", "Length: ", lengthStr );
   }

   // Shorthand for a Patient's full name
   public String fullName()
   {
      return String.format( "%s %s [%s]", firstName, surname, dateOfBirth.toString() );
   }

   //Calculates the Patient's age
   public int age()
   {
      Period timePeriod = Period.between(dateOfBirth, LocalDate.now());
      return timePeriod.getYears();
   }
}
