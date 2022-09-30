import java.util.ArrayList;

public class ZorgApp
{
   public static void main( String[] args )
   {
      ArrayList<User> users = GenerateData.GenerateUsers();
      ArrayList<Department> departments = GenerateData.GenerateDepartments();

      Administration administration = new Administration(users, departments);
      administration.menu();
   }
}
