import java.util.Scanner;
public class User
{
   private final int STOP = 0;
   private final int SURNAME = 1;
   private final int FIRST_NAME = 2;
   private final int DATE_OF_BIRTH = 3;
   private final int WEIGHT = 4;
   private final int LENGTH = 5;
   private String userName;
   private int userID;

   private DepartmentName departmentName;
   public String getUserName()
   {
      return userName;
   }
   public int getUserID()
   {
      return userID;
   }

   public DepartmentName getDepartmentName() {
      return this.departmentName;
   }

   public User( int id, String name, DepartmentName departmentName)
   {
      this.userID   = id;
      this.userName = name;
      this.departmentName = departmentName;
   }

   public void viewPatientData( Patient patient )
   {
      patient.viewData();
   }

   public void menuEditPatient(Patient patient) {
      patient.viewEditableData();

      Scanner scanner = new Scanner(System.in);

      System.out.println("Enter which field number you'd like to edit:");
      int choice = scanner.nextInt();

      scanner = new Scanner(System.in);
      String input = "";

      switch(choice){
         default:
            System.out.format("Could not find field number: %d.\nPlease try again.\n", choice );
            menuEditPatient(patient);
            break;
         case STOP:
            return;
         case SURNAME:
            System.out.println("Enter the new value for surname:");
            input = scanner.nextLine();
            patient.setSurname(input);
            System.out.format("Patient surname was set to: %s", input);
            break;
         case FIRST_NAME:
            System.out.println("Enter the new value for first name:");
            input = scanner.nextLine();
            patient.setFirstName(input);
            System.out.format("Patient first name was set to: %s", input);
            break;
         case DATE_OF_BIRTH:
            System.out.println("Using this format: yyyy-mm-dd, enter the new value for date of birth:");
            input = scanner.nextLine();
            patient.setDateOfBirth(ConversionHelper.stringToLocalDate(input));
            System.out.format("Patient date of birth was set to: %s", input);
            break;
         case WEIGHT:
            System.out.println("Enter the new value for weight:");
            input = scanner.nextLine();
            patient.setWeight(Integer.parseInt(input));
            System.out.format("Patient weight was set to: %s", input);
            break;
         case LENGTH:
            System.out.println("Enter the new value for length:");
            input = scanner.nextLine();
            patient.setLength(Float.parseFloat(input));
            System.out.format("Patient length was set to: %s", input);
            break;
      }
   }
}

