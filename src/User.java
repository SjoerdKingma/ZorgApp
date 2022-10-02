import java.util.Scanner;
public class User
{
   private final int EXIT = 0;
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

   public void menuEditPatient(Patient currentPatient) {
      currentPatient.viewEditableData();

      Scanner scanner = new Scanner(System.in);

      System.out.println("Choose which field number you'd like to edit, or enter 0 to quit: ");
      int choice = scanner.nextInt();
      System.out.format("Enter the new value for field %d.\n", choice);
      if(choice == 3){ //User wants to edit the Patient.dateOfBirth. So we have to tell the user which date format to use.
         System.out.println("Use this date format: YYYY-MM-DD.");
      }
      scanner = new Scanner(System.in);
      String inputValue = scanner.nextLine();

      switch(choice){
         default:
            System.out.format("Could not find field number: %d.\nPlease try again.\n", choice );
            menuEditPatient(currentPatient);
            break;
         case EXIT:
            return;
         case SURNAME:
            currentPatient.setSurname(inputValue);
            break;
         case FIRST_NAME:
            currentPatient.setFirstName(inputValue);
            break;
         case DATE_OF_BIRTH:
            System.out.println("Please use this format: yyyy-mm-dd.");
            currentPatient.setDateOfBirth(ConversionHelper.stringToLocalDate(inputValue));
            break;
         case WEIGHT:
            int weightInt = Integer.parseInt(inputValue);
            currentPatient.setWeight(weightInt);
            break;
         case LENGTH:
            float lengthFloat = Float.parseFloat(inputValue);
            currentPatient.setLength(lengthFloat);
            break;
      }
      System.out.format("Patient updated with new value: %s for field %d\n", inputValue, choice);
   }
}

