import java.util.ArrayList;

public class ZorgApp
{
   public static void main( String[] args )
   {
      ArrayList<User> users = GenerateData.GenerateUsers();

      Administration administration = new Administration( users );
      administration.menu();
   }
}
