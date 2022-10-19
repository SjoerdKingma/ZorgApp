import java.util.Scanner;

public class Tandarts extends User{

    private final int STOP = 0;
    private final int SURNAME = 1;
    private final int FIRST_NAME = 2;
    private final int DATE_OF_BIRTH = 3;
    public Tandarts(int id, String name, String password){
        super(id, name, "Pietje", DepartmentName.Tandarts);
    }

    public void viewPatientData(Patient patient){
        System.out.format( "===== Patient id=%d ==============================\n", patient.getPatientId());
        System.out.format( "%-17s %s\n", "Achternaam:", patient.getSurname() );
        System.out.format( "%-17s %s\n", "Voornaam:", patient.getFirstName() );
        System.out.format( "%-17s %s\n", "Leeftijd: ", patient.calcAge() );
        System.out.format( "%-17s %s\n", "Geboortedatum:", patient.getDateOfBirth() );

        System.out.format("\nPatiënt medicijnen:\n\n");
        patient.getPrescriptions().forEach( prescription -> prescription.viewDataAsList());
    }

    public void menuEditPatient(Patient patient) {
        viewPatientEditableData(patient);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Voer het veldnummer in van het veld dat u wenst aan te passen: ");
        int choice = scanner.nextInt();

        scanner = new Scanner(System.in);
        String input = "";

        switch(choice){
            default:
                System.out.format("Kon het veld met veldnummer: %d niet vinden.\nProbeer het opnieuw.\n", choice );
                menuEditPatient(patient);
                break;
            case STOP:
                return;
            case SURNAME:
                System.out.println("Voer de nieuwe waarde in voor de achternaam:");
                input = scanner.nextLine();
                patient.setSurname(input);
                System.out.format("Achternaam veranderd naar: %s", input);
                break;
            case FIRST_NAME:
                System.out.println("Voer de nieuwe waarde in voor de voornaam: ");
                input = scanner.nextLine();
                patient.setFirstName(input);
                System.out.format("Voornaam veranderd naar: %s", input);
                break;
            case DATE_OF_BIRTH:
                System.out.println("Voer de nieuwe waarde in voor geboortedatum. Gebruik dit formaat: yyyy-mm-dd: ");
                input = scanner.nextLine();
                patient.setDateOfBirth(ConversionHelper.stringToLocalDate(input));
                System.out.format("Geboortedatum veranderd naar: %s", input);
                break;
            case MEDICINES:
                menuEditPatientPrescriptions(patient.getPrescriptions());
                break;
        }
    }

    protected void viewPatientEditableData(Patient patient){
        System.out.format("===== Patient id=%d ==============================\n", patient.getPatientId());
        System.out.format("%d: Stop\n", STOP);
        System.out.format("%d: %-17s %s\n", SURNAME, "Achternaam:", patient.getSurname());
        System.out.format("%d: %-17s %s\n", FIRST_NAME, "Voornaam:", patient.getFirstName());
        System.out.format("%d: %-17s %s\n", DATE_OF_BIRTH, "Geboortedatum:", patient.getDateOfBirth());
        System.out.format("%d: %-17s \n", MEDICINES, "Medicijnenlijst");
    }
}
