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
   private static final int CHANGE_USER = 5;       // The currently selected patient

   private static final String ErrorNoInteger = "Please enter a valid number";

   private UserManager userManager;
   private PatientManager patientManager;

   Administration( ArrayList<User> users )
   {
      //Scanner scanner = new Scanner(System.in);
      userManager = new UserManager(users);
      patientManager = new PatientManager(GenerateData.GeneratePatients());

   }
   public void menu()
   {
      boolean nextCycle = true;
      while (nextCycle)
      {
         User currentUser = userManager.getCurrentUser();

         System.out.format( "%s\n", "=".repeat( 80 ) );
         System.out.format( "Current user: [%d] %s\n", currentUser.getUserID() , currentUser.getUserName() );
         System.out.format( "Current patient: [%d] %s\n", patientManager.currentPatient.getPatientId(), patientManager.currentPatient.fullName() );

         // Print menu on screen
         System.out.format( "%d:  STOP\n", STOP );
         System.out.format( "%d:  Patient overview\n", VIEW_ALL_PATIENTS );
         System.out.format( "%d:  View patient details\n", VIEW_CURRENT_PATIENT );
         System.out.format( "%d:  Edit current patient\n", EDIT_PATIENT );
         System.out.format( "%d:  Change active patient\n", CHANGE_PATIENT );
         System.out.format( "%d:  Change user\n", CHANGE_USER );

         System.out.print( "enter #choice: " );
         Scanner scanner = new Scanner(System.in);
         int choice = 0;
         try{
            choice = scanner.nextInt();
         }catch(Exception ex){
            System.out.println(ErrorNoInteger);
         }

         switch (choice)
         {
            default:
               System.out.println( "Please enter a *valid* number." );
               break;

            case STOP: // interrupt the loop
               nextCycle = false;
               break;

            case VIEW_ALL_PATIENTS:
               patientManager.viewAllPatients();
               break;

            case VIEW_CURRENT_PATIENT:
               currentUser.viewPatientData(patientManager.currentPatient);
               break;

            case EDIT_PATIENT:
               currentUser.menuEditPatient(patientManager.currentPatient);
               break;

            case CHANGE_PATIENT:
               patientManager.menuChangePatient();
               break;

            case CHANGE_USER:
               userManager.showMenuChangeUser();
               break;
         }
      }
   }
}
