import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

// class Administration represents the core of the application by showing
// the main menu, from where all other functionality is accessible, either
// directly or via sub-menus.
//
// An Adminiatration instance needs a User as input, which is passed via the
// constructor to the data member 'çurrentUser'.
// The patient data is available via the data member çurrentPatient.
public class Administration
{
   private static final int STOP = 0;
   private static final int VIEW_ALL_PATIENTS = 1;
   private static final int VIEW_CURRENT_PATIENT = 2;
   private static final int EDIT_PATIENT = 3;
   private static final int CHANGE_PATIENT = 4;
   private static final int CHANGE_USER = 5;

   private Patient currentPatient;            // The currently selected patient
   private User    currentUser;               // the current user of the program.

   private final ArrayList<User> userList;
   private ArrayList<Patient> patientList;

   private Scanner scanner;

   Administration( ArrayList<User> users )
   {
      scanner = new Scanner( System.in );  // User input via this scanner.

      userList = users;

      int currentUserId = SettingsHelper.GetCurrentUserId();
      currentUser    = getUserFromUserId(currentUserId);
      if(currentUser == null){
         menuChangeUser(); //Force the user to login
      }

      this.patientList = GenerateData.GeneratePatients();
      this.currentPatient = patientList.get(0);

   }
   void menu()
   {
      boolean nextCycle = true;
      while (nextCycle)
      {
         System.out.format( "%s\n", "=".repeat( 80 ) );
         System.out.format( "Current user: [%d] %s\n", currentUser.getUserID() , currentUser.getUserName() );
         System.out.format( "Current patient: [%d] %s\n", currentPatient.getPatientId(),currentPatient.fullName() );

         // Print menu on screen
         System.out.format( "%d:  STOP\n", STOP );
         System.out.format( "%d:  Patient overview\n", VIEW_ALL_PATIENTS );
         System.out.format( "%d:  View patient details\n", VIEW_CURRENT_PATIENT );
         System.out.format( "%d:  Edit current patient\n", EDIT_PATIENT );
         System.out.format( "%d:  Change active patient\n", CHANGE_PATIENT );
         System.out.format( "%d:  Change user\n", CHANGE_USER );

         System.out.print( "enter #choice: " );
         int choice = scanner.nextInt();
         switch (choice)
         {
            case STOP: // interrupt the loop
               nextCycle = false;
               break;

            case VIEW_ALL_PATIENTS:
               viewAllPatients();
               break;

            case VIEW_CURRENT_PATIENT:
               currentUser.viewPatientData( currentPatient );
               break;

            case EDIT_PATIENT:
               currentUser.menuEditPatient(currentPatient);
               break;

            case CHANGE_PATIENT:
               menuChangePatient();
               break;

            case CHANGE_USER:
               menuChangeUser();
               break;

            default:
               System.out.println( "Please enter a *valid* digit" );
               break;
         }
      }
   }

   private void viewAllPatients() {
      System.out.println("List of patients.");

      for(int i=0; i<patientList.size(); i++){
         Patient p = patientList.get(i);
         System.out.format("%d. %s %s\n", p.getPatientId(), p.getSurname(), p.getDateOfBirth());
      }
   }

   void menuChangeUser(){
      System.out.println("Enter your user Id to log in:");
      int userId = 0;
      try{
         userId = scanner.nextInt();
      }
      catch(Exception ex){
         //System.out.println("Exception error! Message: " + ex);
         System.out.println("Please enter a valid number.");
         menuChangeUser();
      }

      changeUser(userId);
   }

   void changeUser(int userId){
      if (getUserFromUserId(userId) != null){ //If user exists
         SettingsHelper.UpdateCurrentUser(userId); //Save to file
         currentUser = getUserFromUserId((userId)); //Store current user in a variable
      }
      else{ //If user doesn't exist
         System.out.println("Error. No such user could be found. Please try again.");
         menuChangeUser();
      }
   }

   User getUserFromUserId(int userId){
      try{
         return userList.stream().filter(x->x.getUserID() == userId).findFirst().get();
      }
      catch(Exception ex){
         //Could not find the user. Returning null.
         return null;
      }
   }

   void menuChangePatient(){
      System.out.println("Enter the patient ID:");
      int patientId = 0;
      try{
         patientId = scanner.nextInt();
      }
      catch(Exception ex){
         //System.out.println("Exception error! Message: " + ex);
         System.out.println("Please enter a valid number.");
         menuChangePatient();
      }

      changePatient(patientId);
   }
   Patient changePatient(int patientId){
      Patient result = null;
      try{
         //Get patient object by patientId
         result =  patientList.stream().filter(x->x.getPatientId() == patientId).findFirst().get();
      }
      catch(Exception ex){
         //Could not find the Patient in the list.
         System.out.format("Error finding patient with id %d", patientId);
      }

      currentPatient = result; //Change patent
      return result;
   }
}
