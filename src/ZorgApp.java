import java.util.ArrayList;

public class ZorgApp
{
   public static void main( String[] args )
   {
      ArrayList<User> users = new ArrayList<User>();

      users.add(new User( 1, "Mart ElCamera" ));
      users.add(new User( 2, "Roger Federer" ));

      Administration administration = new Administration( users );
      administration.menu();
   }
}
