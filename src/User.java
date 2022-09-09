public class User
{
   private String userName;
   private int deleteMe;
   private int userID;
   public String getUserName()
   {
      return userName;
   }
   public int getUserID()
   {
      return userID;
   }
   public User( int id, String name )
   {
      this.userID   = id;
      this.userName = name;
   }
   public void viewPatientData( Patient patient )
   {
      patient.viewData();
   }
}
