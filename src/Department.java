import java.util.ArrayList;
public class Department {
    private int id;
    private String name;

    private ArrayList<Patient> patients;

    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public Department(int id, String name, ArrayList<Patient> patients){
        this.id = id;
        this.name = name;
    }
}
