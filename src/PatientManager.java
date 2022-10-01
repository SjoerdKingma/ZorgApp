import java.util.ArrayList;
import java.util.Scanner;

public class PatientManager {
    //Field: patients
    ArrayList<Patient> patients;
    Patient currentPatient;

    public PatientManager(ArrayList<Patient> patientList){
        this.patients = patientList;
        this.currentPatient = patients.get(0); //Just pick the first patient in the list
    }

    public void viewAllPatients() {
        System.out.println("List of patients.");

        for(int i=0; i<patients.size(); i++){
            Patient p = patients.get(i);
            System.out.format("%d. %s %s\n", p.getPatientId(), p.getSurname(), p.getDateOfBirth());
        }
    }

    public void menuChangePatient(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the patient ID:");
        int patientId = 0;
        try{
            patientId = scanner.nextInt();
        }
        catch(Exception ex){
            System.out.println("Please enter a valid number.");
            menuChangePatient(); //Show the menu again
        }

        try{
            changePatient(patientId);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    Patient changePatient(int patientId) throws Exception
    {
        Patient result = null;

        //Get patient object by patientId For-loop
        for(int i=0;i<patients.size(); i++){
            if(patients.get(i).getPatientId() == patientId){
                result = patients.get(i);
            }
        }
        if(result == null){
            throw new Exception("Error! Could not find the Patient. Please check if the provided Patient ID is correct.");
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
