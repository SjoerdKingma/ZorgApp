import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public final class GenerateData {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private GenerateData(){}

    public static ArrayList<User> GenerateUsers(){
        ArrayList<User> result = new ArrayList<>();
        result.add(new Fysio(0, "Miguel Angelo", "Pietje"));
        result.add(new Fysio( 1, "Mart ElCamera", "Pietje"));
        result.add(new Huisarts( 2, "Roger Federer", "Pietje"));
        result.add(new Huisarts(3, "Tom Braaksma", "Pietje"));
        result.add(new Tandarts( 4, "Rafael Nadal", "Pietje"));
        result.add(new Tandarts( 5, "Timo Stoepen", "Pietje"));

        return result;
    }

    public static ArrayList<Department> GenerateDepartmentsAndPatients(){

        ArrayList<Department> result = new ArrayList<>();

        ArrayList<Patient> patients = GeneratePatients();
        PatientManager.allPatients = patients;

        ArrayList<Patient> huisartsPatients = new ArrayList<>();
        huisartsPatients.add(patients.get(0));
        huisartsPatients.add(patients.get(1));
        huisartsPatients.add(patients.get(2));
        huisartsPatients.add(patients.get(3));

        ArrayList<Patient> tandartsPatients = new ArrayList<>();
        tandartsPatients.add(patients.get(0));
        tandartsPatients.add(patients.get(1));
        tandartsPatients.add(patients.get(3));
        tandartsPatients.add(patients.get(6));

        ArrayList<Patient> fysioPatients = new ArrayList<>();
        fysioPatients.add(patients.get(0));
        fysioPatients.add(patients.get(2));
        fysioPatients.add(patients.get(4));
        fysioPatients.add(patients.get(5));

        result.add(new Department(0, DepartmentName.Huisarts,  new PatientManager(huisartsPatients)));
        result.add(new Department(1, DepartmentName.Tandarts, new PatientManager(tandartsPatients)));
        result.add(new Department(2, DepartmentName.Fysio, new PatientManager(fysioPatients)));
        return result;
    }

    public static ArrayList<Patient> GeneratePatients(){
        ArrayList<Patient> result = new ArrayList<>();

        ArrayList<Prescription> prescriptions = GeneratePrescriptions();
        LungCapacityInfo lungCapacity = GenerateLungCapacityInfo();

        Patient p1 = new Patient(
                1, "Van Puffelen", "Pierre",
                LocalDate.of( 2000, 2, 29 ),
                75, 1.80f, prescriptions,
                dateTimeFormatter, lungCapacity
                );

        Patient p2 = new Patient(2, "Veen", "Halbe",
                  LocalDate.of(1972, 3, 24),
                    105, 1.94f, prescriptions,
                dateTimeFormatter, lungCapacity
                );

        Patient p3 = new Patient(3, "Poppinga", "Kees",
                LocalDate.of(1954, 6, 23),
                75, 1.67f, prescriptions,
                dateTimeFormatter, lungCapacity
        );

        Patient p4 = new Patient(4, "Boukema", "Atje",
                LocalDate.of(1972, 3, 24),
                105, 1.94f, prescriptions,
                dateTimeFormatter, lungCapacity
        );

        Patient p5 = new Patient(5, "Postma", "Piebe",
                LocalDate.of(2002, 8, 2),
                107, 1.68f, prescriptions,
                dateTimeFormatter, lungCapacity
        );

        Patient p6 = new Patient(6, "Visser", "Tjitske",
                LocalDate.of(1983, 2, 12),
                80, 1.89f, prescriptions,
                dateTimeFormatter, lungCapacity
        );

        Patient p7 = new Patient(7, "Hoekstra", "Piebe",
                LocalDate.of(2003, 2, 24),
                95, 2.03f, prescriptions,
                dateTimeFormatter, lungCapacity
        );

        result.add((p1));
        result.add((p2));
        result.add((p3));
        result.add((p4));
        result.add((p5));
        result.add((p6));
        result.add((p7));

        return result;
    }

    private static ArrayList<Prescription> GeneratePrescriptions() {
        ArrayList<Prescription> result = new ArrayList<>();

        ArrayList<Medicine> medicines = GenerateMedicines();

        Prescription pres1 = new Prescription(0, "1 per 2 dagen", medicines.get(0));
        result.add(pres1);

        Prescription pres2 = new Prescription(1, "3 per dag", medicines.get(1));
        result.add(pres2);

        Prescription pres3 = new Prescription(2, "1 per week", medicines.get(2));
        result.add(pres3);

        return result;
    }

    private static ArrayList<Medicine> GenerateMedicines(){
        ArrayList<Medicine> result = new ArrayList<>();
        result.add(new Medicine(0, "Advil", MedicineType.Pijnstiller));
        result.add(new Medicine(1, "Panadol", MedicineType.Pijnstiller));
        result.add(new Medicine(2, "Amoxil", MedicineType.Pijnstiller));
        result.add(new Medicine(3, "Cipro", MedicineType.Antibiotica));
        result.add(new Medicine(4, "Zithromax", MedicineType.Antibiotica));
        return result;
    }

    private static LungCapacityInfo GenerateLungCapacityInfo(){

        ArrayList<LungCapacityPoint> points = new ArrayList<>(Arrays.asList(
                new LungCapacityPoint(4.6f, LocalDate.of(2017, 3, 21)),
                new LungCapacityPoint(4.3f, LocalDate.of(2018, 6, 3)),
                new LungCapacityPoint(4.4f, LocalDate.of(2019, 2, 25)),
                new LungCapacityPoint(4.2f, LocalDate.of(2020, 8, 14)),
                new LungCapacityPoint(4.1f, LocalDate.of(2021, 4, 7))));


        /*LungCapacityPoint p1 = new LungCapacityPoint(4.6f, LocalDate.of(2017, 3, 21));
        LungCapacityPoint p2 = new LungCapacityPoint(4.3f, LocalDate.of(2018, 6, 3));
        LungCapacityPoint p3 = new LungCapacityPoint(4.4f, LocalDate.of(2019, 2, 25));
        LungCapacityPoint p4 = new LungCapacityPoint(4.2f, LocalDate.of(2020, 8, 14));
        LungCapacityPoint p5 = new LungCapacityPoint(4.1f, LocalDate.of(2021, 4, 7));*/

        LungCapacityInfo result = new LungCapacityInfo(points);
        return result;
    }
}
