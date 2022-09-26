import java.util.Scanner;

enum Role{
   Fysio,
   Tandarts,
   Huisarts
}

public class User
{
   private String userName;
   private int userID;
   public String getUserName()
   {
      return userName;
   }
   public int getUserID()
   {
      return userID;
   }

   private Role role;
   public Role getRole(){
      return this.role;
   }

   public User( int id, String name, Role role)
   {
      this.userID   = id;
      this.userName = name;
      this.role = role;
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
         case 0://Exit prompt
            return;
         case 1://Surname3
            currentPatient.setSurname(inputValue);
            break;
         case 2://First name
            currentPatient.setFirstName(inputValue);
            break;
         case 3://Date of birth
            System.out.println("Please use this format: yyyy-mm-dd.");
            currentPatient.setDateOfBirth(ConversionHelper.stringToLocalDate(inputValue));
            break;
         case 4://Weight
            int weightInt = Integer.parseInt(inputValue);
            currentPatient.setWeight(weightInt);
            break;
         case 5://Length
            float lengthFloat = Float.parseFloat(inputValue);
            currentPatient.setLength(lengthFloat);
            break;
      }
      System.out.format("Patient updated with new value: %s for field %d\n", inputValue, choice);
   }
}

