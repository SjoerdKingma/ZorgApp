import java.util.ArrayList;

public class    Department {
    private int id;
    private DepartmentName name;
    public PatientManager patientManager;
    public int getId(){
        return this.id;
    }
    public DepartmentName getName(){
        return this.name;
    }
    public Department(int id, DepartmentName department, PatientManager patientManager){
        this.id = id;
        this.name = department;
        this.patientManager = patientManager;
    }
}
