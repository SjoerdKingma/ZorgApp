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
   private static final int ERROR = -1;
   private static final int STOP = 0;
   private static final int VIEW_ALL_PATIENTS = 1;
   private static final int VIEW_CURRENT_PATIENT = 2;
   private static final int EDIT_PATIENT = 3;
   private static final int CHANGE_PATIENT = 4;
   private static final int CHANGE_USER = 5;

   private final ArrayList<Department> departments; //Also contains patients
   private Department currentDepartment;
   private final UserManager userManager;

   Administration(ArrayList<User> users)
   {
      userManager = new UserManager(users);
      this.departments = GenerateData.GenerateDepartmentsAndPatients();
      currentDepartment = departments.stream().filter(x -> x.getName() == userManager.getCurrentUser().getDepartmentName()).findAny().get();
      updateDepartmentAndPatients();
   }
   public void menu()
   {
      boolean nextCycle = true;
      while (nextCycle)
      {
         User currentUser = userManager.getCurrentUser();

         System.out.format( "\n%s\n\n", "=".repeat( 80 ) );
         System.out.format( "Current user: [%d] %s [%s]\n", currentUser.getUserID() , currentUser.getUserName(),currentUser.getDepartmentName().toString());
         System.out.format( "Current patient: [%d] %s\n", currentDepartment.patientManager.currentPatient.getPatientId(), currentDepartment.patientManager.currentPatient.fullName() );

         // Print menu on screen
         System.out.format( "%d:  STOP\n", STOP );
         System.out.format( "%d:  Patient overview\n", VIEW_ALL_PATIENTS );
         System.out.format( "%d:  View patient details\n", VIEW_CURRENT_PATIENT );
         System.out.format( "%d:  Edit current patient\n", EDIT_PATIENT );
         System.out.format( "%d:  Change active patient\n", CHANGE_PATIENT );
         System.out.format( "%d:  Change user\n", CHANGE_USER );

         System.out.print( "enter #choice: " );

         nextCycle = handleInput(); //Handle Input via this method
      }
   }

   private boolean handleInput() {

      boolean hasNextCycle = true;

      int choice = validateInput();

      switch (choice)
      {
         default:
         case ERROR:
            System.out.println( "Please enter a *valid* number." );
            break;

         case STOP: // interrupt the loop
            hasNextCycle = false;
            break;

         case VIEW_ALL_PATIENTS:
            System.out.println("");
            currentDepartment.patientManager.viewAllPatients();
            break;

         case VIEW_CURRENT_PATIENT:
            userManager.getCurrentUser().viewPatientData(currentDepartment.patientManager.currentPatient);
            break;

         case EDIT_PATIENT:
            userManager.getCurrentUser().menuEditPatient(currentDepartment.patientManager.currentPatient);
            break;

         case CHANGE_PATIENT:
            currentDepartment.patientManager.menuChangePatient();
            break;

         case CHANGE_USER:
            userManager.menuChangeUser();
            updateDepartmentAndPatients(); //After changing the user, the departments with patients needs to be updated.
            break;
      }
      return hasNextCycle;
   }

   private int validateInput() {
      //Validate User Input
      Scanner scanner = new Scanner(System.in);
      int choice = 0;

      try{
         choice = scanner.nextInt();
      }catch(Exception ex){
         System.out.println("You entered text where a number was expected. Please enter a valid number for your choice.");
         System.out.println("Press ENTER key to continue.");
         try{
            System.in.read();
         }
         catch(Exception exc){}

         menu(); //Restart menu
      }

      return choice;
   }
   
   private void updateDepartmentAndPatients(){
      DepartmentName departmentName = userManager.getCurrentUser().getDepartmentName();
      this.currentDepartment = departments.stream().filter(x -> x.getName() == departmentName).findAny().get();
      //ArrayList<Patient> patients = departments.stream().filter(x -> x.getName() == departmentName).findFirst().get().patientManager.patients;
   }
}
