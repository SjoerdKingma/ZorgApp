package Users;

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
   private String password;

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

   public String getPassword(){
      return this.password;
   }

   //Constructor
   public User(int id, String name, String password, DepartmentName departmentName) {
      this.userID = id;
      this.userName = name;
      this.departmentName = departmentName;
      this.password = password;
   }

   //Methods
   public void viewPatientData(Patient patient) {
      patient.viewData(); //View all patient data without any restrictions from roles.
   }

   public void menuEditPatient(Patient patient) {
      viewPatientEditableData(patient);

      Scanner scanner = new Scanner(System.in);

      System.out.println("Voer het veldnummer in van het veld dat u wilt veranderen: ");
      int choice = scanner.nextInt();

      scanner = new Scanner(System.in);
      String input = "";

      switch (choice) {
         default:
            System.out.format("Could not find field number: %d.\nPlease try again.\n", choice);
            System.out.format("Kon het veld vor nummer: %d niet vinden. \nProbeer het opnieuw.\n", choice);
            menuEditPatient(patient);
            break;
         case STOP:
            return;
         case SURNAME:
            System.out.println("Voer de nieuwe waarde in voor de achternaam: ");
            input = scanner.nextLine();
            patient.setSurname(input);
            System.out.format("Achternaam veranderd naar: %s", input);
            break;
         case FIRST_NAME:
            System.out.println("Voer de nieuwe waarde in voor de voornaam:");
            input = scanner.nextLine();
            patient.setFirstName(input);
            System.out.format("Voornaam veranderd naar: %s", input);
            break;
         case DATE_OF_BIRTH:
            System.out.println("Voer de nieuwe waarde in voor geboortedatum. Gebruik dit formaat: yyyy-mm-dd:");
            input = scanner.nextLine();
            patient.setDateOfBirth(ConversionHelper.stringToLocalDate(input));
            System.out.format("Geboortedatum veranderd naar: %s", input);
            break;
         case WEIGHT:
            System.out.println("Voer de nieuwe waarde in voor het gewicht (kg):");
            input = scanner.nextLine();
            patient.setWeight(Integer.parseInt(input));
            System.out.format("Gewicht veranderd naar: %s kg", input);
            break;
         case LENGTH:
            System.out.println("Voer de nieuwe waarde in voor de lengte (m):");
            input = scanner.nextLine();
            patient.setLength(Float.parseFloat(input));
            System.out.format("Lengte veranderd naar: %s m", input);
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
      System.out.format("%d: %-17s %s\n", SURNAME, "Achternaam:", patient.getSurname());
      System.out.format("%d: %-17s %s\n", FIRST_NAME, "Voornaam:", patient.getFirstName());
      System.out.format("%d: %-17s %s\n", DATE_OF_BIRTH, "Geboortedatum:", patient.getDateOfBirth());
      System.out.format("%d: %-17s %s kg\n", WEIGHT, "Gewicht: ", patient.getWeight());
      System.out.format("%d: %-17s %s m\n", LENGTH, "Lengte: ", ConversionHelper.addTwoDecimals(patient.getLength()));
      System.out.format("%d: %-17s \n", MEDICINES, "Medicijnenlijst");
   }

   protected void menuEditPatientPrescriptions(ArrayList<Prescription> prescriptions) {

      //Print prescriptions on screen
      System.out.println("\nLijst van medicijnen: ");
      prescriptions.forEach(prescription -> prescription.viewDataAsEditableList());

      //Get user input for medicine selection
      Scanner scanner = new Scanner(System.in);
      System.out.println("Voer het ID in van de medicijn die u wilt aanpassen: ");
      int prescriptionId = scanner.nextInt();

      //Handle user input
      Prescription prescription = prescriptions.stream().filter(x -> x.id == prescriptionId).findFirst().get();

      //Print prescription editable fields on screen
      System.out.format("\n%d. STOP\n", STOP);
      System.out.format("%d. Medicijn naam: %s.\n", PRESCRIPTION_NAME, prescription.medicine.name);
      System.out.format("%d. Medicijn type: %s.\n", PRESCRIPTION_TYPE, prescription.medicine.type);
      System.out.format("%d. Medicijn dosering: %s\n", PRESCRIPTION_DOSE, prescription.dose);

      //Get user input for medicine field selection
      System.out.println("Voer het nummer in van het veld dat u went aan te passen: ");
      Scanner scanner2 = new Scanner(System.in);
      int fieldChoice = scanner2.nextInt();

      switch (fieldChoice) {
         default:
            System.out.println("Kon het opgegeven nummer niet vinden. Probeer het opnieuw.");
            menuEditPatientPrescriptions(prescriptions);
            break;
         case STOP:
            return;
         case PRESCRIPTION_NAME: {
            System.out.println("Voer de nieuwe naam in voor het medicijn: ");
            Scanner scanner3 = new Scanner(System.in);
            String medicineName = scanner3.nextLine();
            prescription.medicine.name = medicineName;
            System.out.format("\nMedicijn naam is veranderd naar:  %s", medicineName);
            break;
         }
         case PRESCRIPTION_TYPE: {
            System.out.println("Medicijn types:");
            System.out.println("1. Pijnstiller");
            System.out.println("2. Antibiotica");

            System.out.println("Selecteer de nieuwe type prescription: ");

            Scanner scanner4 = new Scanner(System.in);
            int prescriptionTypeChoice = scanner4.nextInt();
            switch (prescriptionTypeChoice) {
               case 0:
                  return;
               case 1:
               {
                  prescription.medicine.type = MedicineType.Pijnstiller;
                  System.out.format("\nMedicijn type veranderd naar: %s", MedicineType.Pijnstiller);
                  break;
               }
               case 2:
               {
                  prescription.medicine.type = MedicineType.Antibiotica;
                  System.out.format("\nMedicijn type veranderd naar: %s", MedicineType.Antibiotica);
                  break;
               }
            }
            break;
         }
         case PRESCRIPTION_DOSE:
         {
            System.out.println("Voer de nieuwe dosering in: ");
            Scanner scanner3 = new Scanner(System.in);
            String dose = scanner3.nextLine();
            prescription.dose = dose;
            System.out.format("\nMedicijn dosering veranderd naar: %s", dose);
            break;
         }
      }
   }
}

