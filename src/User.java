import java.util.ArrayList;
import java.util.Scanner;
public abstract class User {
   //Global menu fields
   protected final int STOP = 0;

   //Patient menu fields
   protected final int SURNAME = 1;
   protected final int FIRST_NAME = 2;
   protected final int DATE_OF_BIRTH = 3;
   protected final int WEIGHT = 4;
   protected final int LENGTH = 5;
   protected final int MEDICINES = 6;

   //Prescription menu fields
   protected final int PRESCRIPTION_NAME = 1;
   protected final int PRESCRIPTION_TYPE = 2;
   protected final int PRESCRIPTION_DOSE = 3;

   //Fields
   private String userName;
   private int userID;
   private DepartmentName departmentName;

   //Getters and Setters
   public String getUserName() {
      return userName;
   }

   public int getUserID() {
      return userID;
   }

   public DepartmentName getDepartmentName() {
      return this.departmentName;
   }

   //Constructor
   public User(int id, String name, DepartmentName departmentName) {
      this.userID = id;
      this.userName = name;
      this.departmentName = departmentName;
   }

   //Methods
   public void viewPatientData(Patient patient) {
      patient.viewData(); //View all patient data without any restrictions from roles.
   }

   public void menuEditPatient(Patient patient) {
      viewPatientEditableData(patient);

      Scanner scanner = new Scanner(System.in);

      System.out.println("Enter which field number you'd like to edit:");
      int choice = scanner.nextInt();

      scanner = new Scanner(System.in);
      String input = "";

      switch (choice) {
         default:
            System.out.format("Could not find field number: %d.\nPlease try again.\n", choice);
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
   protected void viewPatientEditableData(Patient patient) {
      System.out.format("===== Patient id=%d ==============================\n", patient.getPatientId());
      System.out.format("%d: Stop\n", STOP);
      System.out.format("%d: %-17s %s\n", SURNAME, "Surname:", patient.getSurname());
      System.out.format("%d: %-17s %s\n", FIRST_NAME, "FirstName:", patient.getFirstName());
      System.out.format("%d: %-17s %s\n", DATE_OF_BIRTH, "Date of birth:", patient.getDateOfBirth());
      System.out.format("%d: %-17s %s\n", WEIGHT, "Weight: ", patient.getWeight());
      System.out.format("%d: %-17s %s\n", LENGTH, "Length: ", ConversionHelper.addTwoDecimals(patient.getLength()));
      System.out.format("%d: %-17s \n", MEDICINES, "Medicine list");
   }

   private void menuEditPatientPrescriptions(ArrayList<Prescription> prescriptions) {

      //Print prescriptions on screen
      System.out.println("\nList of medicines: ");
      prescriptions.forEach(prescription -> prescription.viewDataAsEditableList());

      //Get user input
      Scanner scanner = new Scanner(System.in);
      System.out.println("Choose the id of the medicine you'd like to edit: ");
      int prescriptionId = scanner.nextInt();

      //Handle user input
      Prescription prescription = prescriptions.stream().filter(x -> x.id == prescriptionId).findFirst().get();

      //Print prescription editable fields on screen
      System.out.format("\n%d. STOP\n", STOP);
      System.out.format("%d. Medicine naam: %s.\n", PRESCRIPTION_NAME, prescription.medicine.name);
      System.out.format("%d. Medicine type: %s.\n", PRESCRIPTION_TYPE, prescription.medicine.type);
      System.out.format("%d. Medicine dosering: %s\n", PRESCRIPTION_DOSE, prescription.dose);

      System.out.println("Enter the number of the field you'd like to edit: ");

      //Get user input
      Scanner scanner2 = new Scanner(System.in);
      int fieldChoice = scanner2.nextInt();

      switch (fieldChoice) {
         default:
            System.out.println("Could not find the number of the field. Please try again.");
            menuEditPatientPrescriptions(prescriptions);
            break;
         case STOP:
            return;
         case PRESCRIPTION_NAME: {
            System.out.println("Enter the new name for the medicine: ");
            Scanner scanner3 = new Scanner(System.in);
            String medicineName = scanner3.nextLine();
            prescription.medicine.name = medicineName;
            System.out.format("\nMedicine name succesfully updated to value: %s", medicineName);
            break;
         }
         case PRESCRIPTION_TYPE: {
            System.out.println("Medicine types:");
            System.out.println("1. Pijnstiller");
            System.out.println("2. Antibiotica");

            System.out.println("Select the number of which type you'd like to update the medicine with: ");

            Scanner scanner4 = new Scanner(System.in);
            int prescriptionTypeChoice = scanner4.nextInt();
            switch (prescriptionTypeChoice) {
               case 0:
                  return;
               case 1:
               {
                  prescription.medicine.type = MedicineType.Pijnstiller;
                  System.out.format("\nMedicine type succesfully updated to value: %s", MedicineType.Pijnstiller.toString());
                  break;
               }
               case 2:
               {
                  prescription.medicine.type = MedicineType.Antibiotica;
                  System.out.format("\nMedicine type succesfully updated to value: %s", MedicineType.Antibiotica.toString());
                  break;
               }
            }
            break;
         }
         case PRESCRIPTION_DOSE:
         {
            System.out.println("Enter the new dose: ");
            Scanner scanner3 = new Scanner(System.in);
            String dose = scanner3.nextLine();
            prescription.dose = dose;
            System.out.format("\nMedicine dose succesfully updated to value: %s", dose);
            break;
         }
      }
   }
}

