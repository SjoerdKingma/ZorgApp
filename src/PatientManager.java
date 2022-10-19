import java.util.ArrayList;
import java.util.Scanner;

public class PatientManager {
    //Field: patients
    ArrayList<Patient> patients; //Only contains patients from the current department.

    public static ArrayList<Patient> allPatients;
    Patient currentPatient;

    public PatientManager(ArrayList<Patient> patientList){
        this.patients = patientList;
        this.currentPatient = patients.get(0); //Just pick the first patient in the list
    }

    public void viewAllPatients() {
        System.out.println("Lijst van patiënten.");

        for(int i=0; i<patients.size(); i++){
            Patient p = patients.get(i);
            System.out.format("%-3d %-20s %-16s Patiënt ID: %s\n", i, p.getSurname(), p.getDateOfBirth(), p.getPatientId());
        }
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
}
