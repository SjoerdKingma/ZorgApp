import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ZorgApp
{
   public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
   public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

   public static void main( String[] args )
   {
      ArrayList<User> users = GenerateData.GenerateUsers();

      Administration administration = new Administration(users);
      administration.menu();
   }
}
