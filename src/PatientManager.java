import java.util.*;
import java.util.stream.Collectors;

public class PatientManager {

    private static final int STOP = 0;
    private static final int SORT_LIST_BY_SURNAME = 1;
    private static final int SORT_LIST_BY_DATE_OF_BIRTH = 2;

    //Field: patients
    ArrayList<Patient> patients; //Only contains patients from the current department.

    public static ArrayList<Patient> allPatients;
    Patient currentPatient;

    public PatientManager(ArrayList<Patient> patientList){
        this.patients = patientList;
        this.currentPatient = patients.get(0); //Just pick the first patient in the list
    }

    public void menuPatientList(){
        viewAllPatients();

        // Print menu on screen
        System.out.format( "%d:  STOP\n", STOP );
        System.out.format( "%d:  Sorteer patiëntenlijst op achternaam\n", SORT_LIST_BY_SURNAME );
        System.out.format( "%d:  Sorteer patiëntenlijst op geboortedatum\n", SORT_LIST_BY_DATE_OF_BIRTH );

        System.out.print( "Uw keuze: " );
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        switch(choice){
            case STOP:
                return;
            case SORT_LIST_BY_SURNAME:
                sortPatients(SortableFields.Surname);
                break;
            case SORT_LIST_BY_DATE_OF_BIRTH:
                sortPatients(SortableFields.DateOfBirth);
                break;
        }
        menuPatientList();
    }
    private void viewAllPatients() {
        System.out.println("\nLijst van patiënten.");

        for(int i=0; i<patients.size(); i++){
            Patient p = patients.get(i);
            System.out.format("%d. %-20s %-16s Patiënt ID: %s\n", i, p.getSurname(), p.getDateOfBirth(), p.getPatientId());
        }

        System.out.println("");
    }

    public void menuChangePatient(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Voer de patiënt ID in:");
        int patientId = 0;
        try{
            patientId = scanner.nextInt();
        }
        catch(Exception ex){
            System.out.println("Voer een geldig *nummer* in.");
            menuChangePatient(); //Show the menu again
        }

        try{
            updateActivePatient(patientId);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    private Patient updateActivePatient(int patientId) throws Exception
    {
        Patient result = null;

        //Get patient object by patientId For-loop
        for(int i=0;i<patients.size(); i++){
            if(patients.get(i).getPatientId() == patientId){
                result = patients.get(i);
            }
        }
        if(result == null){
            throw new Exception("Error! Kon de opgegeven patiënt niet vinden. Controleer of de patiënt ID juist is.");
        }

        currentPatient = result; //Update current patent
        return result;
    }

    public Patient getPatientByPatientId(int patientId){
        try{
            return this.patients.stream().filter(x->x.getPatientId() == patientId).findFirst().get();
        }
        catch(Exception ex){
            //Could not find the user. Returning null.
            return null;
        }
    }

    public void sortPatients(SortableFields field){
        Comparator<Patient> comparator = null;

        switch(field){
            default:
                return;
            case Surname:
                comparator = Comparator.comparing(Patient::getSurname);
                break;
            case DateOfBirth:
                comparator = Comparator.comparing(Patient::getDateOfBirth);
                break;
        }

        List<Patient> sortedList = this.patients.stream().sorted(comparator).toList();
        this.patients = new ArrayList<>();
        this.patients.addAll(sortedList);
    }
}

enum SortableFields{
    Surname,
    DateOfBirth
}
