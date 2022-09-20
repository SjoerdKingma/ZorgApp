import java.util.ArrayList;
import java.util.Scanner;

public class UserManager {

    //Field: Users
    private ArrayList<User> users;
    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    //Field: Current user
    private User currentUser;
    public User getCurrentUser() {
        return this.currentUser;
    }

    public UserManager(ArrayList<User> userList){
        this.users = userList;

        //Get current user
        int currentUserId = SettingsHelper.GetCurrentUserId();
        if(currentUserId == -1){ //UserId could not be found
            showMenuChangeUser(); //Force the user to login
        }
        currentUser = getUserFromUserId(currentUserId);
        if(currentUser == null){ //If there is no current user
            showMenuChangeUser(); //Force the user to login
        }
    }

    public void showMenuChangeUser(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your user Id to log in:");
        int userId = 0;
        try{
            userId = scanner.nextInt();
        }
        catch(Exception ex){
            //System.out.println("Exception error! Message: " + ex);
            System.out.println("Please enter a valid number.");
            showMenuChangeUser();
        }

        changeUser(userId);
    }
    private void changeUser(int userId){
        if (getUserFromUserId(userId) != null){ //If user exists
            SettingsHelper.UpdateCurrentUser(userId); //Save userId to file
            currentUser = getUserFromUserId((userId)); //Store current user in a variable
        }
        else{ //If user doesn't exist
            System.out.println("Error. No such user could be found. Please try again.");
            showMenuChangeUser();
        }
    }

    private User getUserFromUserId(int userId){
        try{
            return this.users.stream().filter(x->x.getUserID() == userId).findFirst().get();
        }
        catch(Exception ex){
            //Could not find the user. Returning null.
            return null;
        }
    }
}
