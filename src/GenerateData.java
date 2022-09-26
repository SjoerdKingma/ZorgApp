import java.time.LocalDate;
import java.util.ArrayList;

public final class GenerateData {
    private GenerateData(){}

    public static ArrayList<User> GenerateUsers(){
        ArrayList<User> result = new ArrayList<>();
        result.add(new User( 1, "Mart ElCamera", Role.Huisarts));
        result.add(new User( 2, "Roger Federer", Role.Fysio ));
        result.add(new User( 3, "Rafael Nadal", Role.Huisarts));
        return result;
    }

    public static ArrayList<Patient> GeneratePatients(){
        ArrayList<Patient> result = new ArrayList<Patient>();

        Patient p1 = new Patient(
                1, "Van Puffelen", "Pierre",
                LocalDate.of( 2000, 2, 29 ),
                75, 1.80f
                );

        Patient p2 = new Patient(2, "Veen", "Halbe",
                  LocalDate.of(1972, 3, 24),
                    105, 1.94f
                );

        Patient p3 = new Patient(3, "Poppinga", "Kees",
                LocalDate.of(1954, 6, 23),
                75, 1.67f
        );

        Patient p4 = new Patient(4, "Boukema", "Atje",
                LocalDate.of(1972, 3, 24),
                105, 1.94f
        );

        Patient p5 = new Patient(5, "Postma", "Piebe",
                LocalDate.of(2002, 8, 2),
                107, 1.68f
        );

        result.add((p1));
        result.add((p2));
        result.add((p3));
        result.add((p4));
        result.add((p5));

        return result;
    }
}
