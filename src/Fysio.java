import java.util.Scanner;

public class Fysio extends User{

    private final int LUNG_CAPACITY = 6;
    public Fysio(int id, String name, String password){
        super(id, name, password, DepartmentName.Fysio);
    }

    public void viewPatientData(Patient patient) {
        System.out.format( "===== Patient id=%d ==============================\n", patient.getPatientId() );
        System.out.format( "%-17s %s\n", "Achternaam:", patient.getSurname() );
        System.out.format( "%-17s %s\n", "Voornaam:", patient.getFirstName() );
        System.out.format( "%-17s %s\n", "Leeftijd: ", patient.calcAge() );
        System.out.format( "%-17s %s\n", "Geboortedatum:", patient.getDateOfBirth());
        System.out.format( "%-17s %s kg\n", "Gewicht: ", patient.getWeight() );
        System.out.format( "%-17s %s m\n", "Lengte: ", ConversionHelper.addTwoDecimals(patient.getLength()) );
        System.out.format( "%-17s %s\n", "BMI: ", ConversionHelper.addTwoDecimals(patient.calcBMI()) );
        System.out.format( "%-17s %s\n", "Longinhoud: ", ConversionHelper.addTwoDecimals(patient.getLungCapacity()) );

        System.out.println("\nLijst van medicijnen: ");
        patient.getPrescriptions().forEach(prescription -> prescription.viewDataAsList());
    }

    public void menuEditPatient(Patient patient) {
        viewPatientEditableData(patient);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Voer de nummer van het veld in dat u wenst aan te passen:");
        int choice = scanner.nextInt();

        scanner = new Scanner(System.in);
        String input = "";

        switch (choice) {
            default:
                System.out.format("Kon veld %d niet vinden. \nProbeer het opnieuw.\n", choice);
                menuEditPatient(patient);
                break;
            case STOP:
                return;
            case SURNAME:
                System.out.println("Voer de nieuwe waarde in voor achternaam:");
                input = scanner.nextLine();
                patient.setSurname(input);
                System.out.format("Achternaam veranderd naar: %s", input);
                break;
            case FIRST_NAME:
                System.out.println("Voer de nieuwe waarde in voor voornaam:");
                input = scanner.nextLine();
                patient.setFirstName(input);
                System.out.format("Voornaam veranderd naar: %s", input);
                break;
            case DATE_OF_BIRTH:
                System.out.println("Voer de nieuwe waarde in voor geboortedatum. Gebruik dit formaat: yyyy-mm-dd:");
                input = scanner.nextLine();
                patient.setDateOfBirth(ConversionHelper.stringToLocalDate(input));
                System.out.format("Geboortedatum veranderd naar: %s", input);
                break;
            case WEIGHT:
                System.out.println("Voer de nieuwe waarde in voor het gewicht (kg): ");
                input = scanner.nextLine();
                patient.setWeight(Integer.parseInt(input));
                System.out.format("Gewicht veranderd naar: %s kg", input);
                break;
            case LENGTH:
                System.out.println("Voer de nieuwe waarde in voor de lengte (m): ");
                input = scanner.nextLine();
                patient.setLength(Float.parseFloat(input));
                System.out.format("Lengte veranderd naar: %s", input);
                break;
            case LUNG_CAPACITY:
                System.out.println("Voer de nieuwe waarde in voor longcapaciteit:");
                input = scanner.nextLine();
                patient.setLungCapacity(Float.parseFloat(input));
                System.out.format("Longcapaciteit veranderd naar: %s", input);

        }
    }
    protected void viewPatientEditableData(Patient patient) {
        System.out.format("===== Patient id=%d ==============================\n", patient.getPatientId());
        System.out.format("%d: Stop\n", STOP);
        System.out.format("%d: %-17s %s\n", SURNAME, "Achternaam:", patient.getSurname());
        System.out.format("%d: %-17s %s\n", FIRST_NAME, "Voornaam:", patient.getFirstName());
        System.out.format("%d: %-17s %s\n", DATE_OF_BIRTH, "Geboortedatum:", patient.getDateOfBirth());
        System.out.format("%d: %-17s %s kg\n", WEIGHT, "Gewicht: ", patient.getWeight());
        System.out.format("%d: %-17s %s m\n", LENGTH, "Lengte: ", ConversionHelper.addTwoDecimals(patient.getLength()));
        System.out.format("%d: %-17s %s\n", LUNG_CAPACITY, "Longinhoud: ", ConversionHelper.addTwoDecimals(patient.getLungCapacity()));
    }
}
