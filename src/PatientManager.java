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
            //System.out.println("Exception error! Message: " + ex);
            System.out.println("Please enter a valid number.");
            menuChangePatient(); //Show the menu again
        }

        changePatient(patientId);
    }
    Patient changePatient(int patientId){
        Patient result = null;

        try{
            //Get patient object by patientId For-loop
            for(int i=0;i<patients.size(); i++){
                if(patients.get(i).getPatientId() == patientId){
                    result = patients.get(i);
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
