import java.util.ArrayList;

public class    Department {
    private int id;
    private DepartmentName name;
    public ArrayList<Patient> patients;
    public int getId(){
        return this.id;
    }
    public DepartmentName getName(){
        return this.name;
    }
    public Department(int id, DepartmentName department, ArrayList<Patient> patients){
        this.id = id;
        this.name = department;
        this.patients = patients;
    }
}
