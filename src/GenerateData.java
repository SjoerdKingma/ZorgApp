import Consult.Consult;
import Consult.ConsultType;
import LungCapacity.LungCapacityInfo;
import LungCapacity.LungCapacityPoint;
import Users.Fysio;
import Users.Huisarts;
import Users.Tandarts;
import Users.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;

public final class GenerateData {
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
        ArrayList<Consult> consults = GenerateConsults();

        Patient p1 = new Patient(
                1, "Van Puffelen", "Pierre",
                LocalDate.of( 2000, 2, 29 ),
                75, 1.80f, prescriptions,
                lungCapacity, consults
                );

        Patient p2 = new Patient(2, "Veen", "Halbe",
                  LocalDate.of(1972, 3, 24),
                    105, 1.94f, prescriptions,
                lungCapacity, consults
                );

        Patient p3 = new Patient(3, "Poppinga", "Kees",
                LocalDate.of(1954, 6, 23),
                75, 1.67f, prescriptions,
                lungCapacity, consults
        );

        Patient p4 = new Patient(4, "Boukema", "Atje",
                LocalDate.of(1972, 3, 24),
                105, 1.94f, prescriptions,
                lungCapacity, consults
        );

        Patient p5 = new Patient(5, "Postma", "Piebe",
                LocalDate.of(2002, 8, 2),
                107, 1.68f, prescriptions,
                lungCapacity, consults
        );

        Patient p6 = new Patient(6, "Visser", "Tjitske",
                LocalDate.of(1983, 2, 12),
                80, 1.89f, prescriptions,
                lungCapacity, consults
        );

        Patient p7 = new Patient(7, "Hoekstra", "Piebe",
                LocalDate.of(2003, 2, 24),
                95, 2.03f, prescriptions,
                lungCapacity, consults
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
                new LungCapacityPoint(5.8f, LocalDate.of(2017, 3, 21)),
                new LungCapacityPoint(4.1f, LocalDate.of(2018, 6, 3)),
                new LungCapacityPoint(5.6f, LocalDate.of(2019, 2, 25)),
                new LungCapacityPoint(4.5f, LocalDate.of(2020, 8, 14)),
                new LungCapacityPoint(5.2f, LocalDate.of(2021, 4, 7))));

        LungCapacityInfo result = new LungCapacityInfo(points);
        return result;
    }

    private static ArrayList<Consult> GenerateConsults() {
        SetConsultPrices();

        ArrayList<Consult> result = new ArrayList<>();

        LocalDateTime cDate = LocalDateTime.of(2015, Month.APRIL, 20, 10, 30);
        FysioConsult c1 = new FysioConsult(cDate, FysioConsult.prices.get(0));
        result.add(c1);

        cDate = LocalDateTime.of(2016, Month.FEBRUARY, 5, 16, 30);
        TandartsConsult c2 = new TandartsConsult(cDate, TandartsConsult.prices.get(0));
        result.add(c2);

        cDate = LocalDateTime.of(2017, Month.DECEMBER, 23, 14, 0);
        TandartsConsult c3 = new TandartsConsult(cDate, TandartsConsult.prices.get(1));
        result.add(c3);

        cDate = LocalDateTime.of(2018, Month.SEPTEMBER, 12, 10, 15);
        TandartsConsult c4 = new TandartsConsult(cDate, TandartsConsult.prices.get(2));
        result.add(c4);

        cDate = LocalDateTime.of(2018, Month.OCTOBER, 11, 11, 15);
        FysioConsult c5 = new FysioConsult(cDate, FysioConsult.prices.get(2));
        result.add(c5);

        cDate = LocalDateTime.of(2019, Month.APRIL, 25, 12, 30);
        TandartsConsult c6 = new TandartsConsult(cDate, TandartsConsult.prices.get(2));
        result.add(c6);

        cDate = LocalDateTime.of(2019, Month.MAY, 25, 10, 15);
        HuisartsConsult c7 = new HuisartsConsult(cDate, HuisartsConsult.prices.get(1));
        result.add(c7);

        return result;
    }

    private static void SetConsultPrices(){
        FysioConsult.prices = GenerateFysioPrices();
        TandartsConsult.prices = GenerateTandartsPrices();
        HuisartsConsult.prices = GenerateHuisartsPrices();
    }

    private static ArrayList<ConsultType> GenerateTandartsPrices(){
        ArrayList<ConsultType> tandartsPrices = new ArrayList<>();
        tandartsPrices.add(new ConsultType("Default", 20f));
        tandartsPrices.add(new ConsultType("Simple", 30f));
        tandartsPrices.add(new ConsultType("Complex", 55f));
        return tandartsPrices;
    }

    private static ArrayList<ConsultType> GenerateFysioPrices(){
        ArrayList<ConsultType> fysioPrices = new ArrayList<>();
        fysioPrices.add(new ConsultType("Default", 17.5f));
        fysioPrices.add(new ConsultType("Short", 22.5f));
        fysioPrices.add(new ConsultType("Extended", 45f));
        fysioPrices.add(new ConsultType("Facilities", 5f));
        return fysioPrices;
    }

    private static ArrayList<ConsultType> GenerateHuisartsPrices(){
        ArrayList<ConsultType> huisartsPrices = new ArrayList<>();
        huisartsPrices.add(new ConsultType("Default", 17.5f));
        huisartsPrices.add(new ConsultType("Extended", 43));
        return huisartsPrices;
    }
}
