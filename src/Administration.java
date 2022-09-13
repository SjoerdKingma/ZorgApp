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
   private ArrayList<Patient> patientList;

   private UserManager userManager;
   private Scanner scanner;

   Administration( ArrayList<User> users )
   {
      scanner = new Scanner( System.in );  // User input via this scanner.
      UserManager userManager = new UserManager(users);



      this.patientList = GenerateData.GeneratePatients();
      this.currentPatient = patientList.get(0);
   }
   public void menu()
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
            default:
               System.out.println( "Please enter a *valid* digit" );
               break;

            case STOP: // interrupt the loop
               nextCycle = false;
               break;

            case VIEW_ALL_PATIENTS:
               viewAllPatients();
               break;

            case VIEW_CURRENT_PATIENT:
               currentUser.viewPatientData(currentPatient);
               break;

            case EDIT_PATIENT:
               currentUser.menuEditPatient(currentPatient);
               break;

            case CHANGE_PATIENT:
               menuChangePatient();
               break;

            case CHANGE_USER:
               userManager.showMenuChangeUser();
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

   private void menuChangePatient(){
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

      /* for loop vs stream
         If raw performance is your No 1 priority, then maybe you are better off with loops.

         But most people don’t want raw performance as their top priority.
         Remember that loops use an imperative style and Streams a declarative style, so Streams are likely to be much easier to maintain.*/

      //Get patient object by patientId Stream
      //result =  patientList.stream().filter(x->x.getPatientId() == patientId).findFirst().get();
      try{
         //Get patient object by patientId For-loop
         for(int i=0;i<patientList.size(); i++){
            if(patientList.get(i).getPatientId() == patientId){
               result = patientList.get(i);
            }
         }
      }
      catch(Exception ex){
         //Could not find the Patient in the list.
         System.out.format("Error finding patient with id %d", patientId);
      }

      currentPatient = result; //Change patent
      return result;
   }
}
