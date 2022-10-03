import java.util.ArrayList;
import java.util.Scanner;
public abstract class User
{
   //Menu options
   private final int STOP = 0;
   private final int SURNAME = 1;
   private final int FIRST_NAME = 2;
   private final int DATE_OF_BIRTH = 3;
   private final int WEIGHT = 4;
   private final int LENGTH = 5;
   private final int MEDICINES = 6;

   //Fields
   private String userName;
   private int userID;
   private DepartmentName departmentName;

   //Getters and Setters
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

   //Constructor
   public User( int id, String name, DepartmentName departmentName)
   {
      this.userID   = id;
      this.userName = name;
      this.departmentName = departmentName;
   }

   //Methods
   public void viewPatientData( Patient patient )
   {
      patient.viewData(); //View all patient data without any restrictions from roles.
   }

   public void menuEditPatient(Patient patient) {
      viewPatientEditableData(patient);

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
         case MEDICINES:
            menuEditPatientPrescriptions(patient.getPrescriptions());
            break;
      }
   }

   //View Patient data but only display editable fields.
   private void viewPatientEditableData(Patient patient){
      System.out.format( "===== Patient id=%d ==============================\n", patient.getPatientId() );
      System.out.format("%d: Stop\n", STOP);
      System.out.format( "%d: %-17s %s\n",SURNAME, "Surname:", patient.getSurname() );
      System.out.format( "%d: %-17s %s\n",FIRST_NAME, "FirstName:", patient.getFirstName() );
      System.out.format( "%d: %-17s %s\n",DATE_OF_BIRTH, "Date of birth:", patient.getDateOfBirth() );
      System.out.format( "%d: %-17s %s\n",WEIGHT, "Weight: ", patient.getWeight() );
      System.out.format( "%d: %-17s %s\n",LENGTH, "Length: ", ConversionHelper.addTwoDecimals(patient.getLength()) );
      System.out.format( "%d: %-17s \n",MEDICINES, "Medicinelist ");
   }

   private void menuEditPatientPrescriptions(ArrayList<Prescription> prescriptions){
      prescriptions.forEach(prescription -> prescription.viewData()); //Print prescriptions on screen

      Scanner scanner = new Scanner(System.in);
      System.out.println("Choose the id of the medicine you'd like to edit: ");
      int choice = scanner.nextInt();

      //TODO: Handle input
   }
}

