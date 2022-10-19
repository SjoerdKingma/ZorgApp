import java.util.Scanner;

public class Tandarts extends User{

    private final int STOP = 0;
    private final int SURNAME = 1;
    private final int FIRST_NAME = 2;
    private final int DATE_OF_BIRTH = 3;
    public Tandarts(int id, String name){
        super(id, name, DepartmentName.Tandarts);
    }

    public void viewPatientData(Patient patient){
        System.out.format( "===== Patient id=%d ==============================\n", patient.getPatientId());
        System.out.format( "%-17s %s\n", "Surname:", patient.getSurname() );
        System.out.format( "%-17s %s\n", "FirstName:", patient.getFirstName() );
        System.out.format( "%-17s %s\n", "Age: ", patient.calcAge() );
        System.out.format( "%-17s %s\n", "Date of birth:", patient.getDateOfBirth() );

        System.out.format("\nPatiënt medicijnen:\n\n");
        patient.getPrescriptions().forEach( prescription -> prescription.viewDataAsList());
    }

    public void menuEditPatient(Patient patient) {
        viewPatientEditableData(patient);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter which field number you'd like to edit:");
        int choice = scanner.nextInt();

        scanner = new Scanner(System.in);
        String input = "";

        switch(choice){
            default:
                System.out.format("Could not find field number: %d.\nPlease try again.\n", choice );
                menuEditPatient(patient);
                break;
            case STOP:
                return;
            case SURNAME:
                System.out.println("Enter the new value for surname:");
                input = scanner.nextLine();
                patient.setSurname(input);
                System.out.format("Patient surname was set to: %s", input);
                break;
            case FIRST_NAME:
                System.out.println("Enter the new value for first name:");
                input = scanner.nextLine();
                patient.setFirstName(input);
                System.out.format("Patient first name was set to: %s", input);
                break;
            case DATE_OF_BIRTH:
                System.out.println("Using this format: yyyy-mm-dd, enter the new value for date of birth:");
                input = scanner.nextLine();
                patient.setDateOfBirth(ConversionHelper.stringToLocalDate(input));
                System.out.format("Patient date of birth was set to: %s", input);
                break;
            case MEDICINES:
                menuEditPatientPrescriptions(patient.getPrescriptions());
                break;
        }
    }

    protected void viewPatientEditableData(Patient patient){
        System.out.format("===== Patient id=%d ==============================\n", patient.getPatientId());
        System.out.format("%d: Stop\n", STOP);
        System.out.format("%d: %-17s %s\n", SURNAME, "Surname:", patient.getSurname());
        System.out.format("%d: %-17s %s\n", FIRST_NAME, "FirstName:", patient.getFirstName());
        System.out.format("%d: %-17s %s\n", DATE_OF_BIRTH, "Date of birth:", patient.getDateOfBirth());
        System.out.format("%d: %-17s \n", MEDICINES, "Medicine list");
    }
}
