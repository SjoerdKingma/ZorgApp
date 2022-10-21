import java.util.ArrayList;
import java.util.Scanner;

public class UserManager {

    private final boolean rememberUser = false;

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

        if(rememberUser){
            //Automatically log in the last user
            int currentUserId = SettingsHelper.GetCurrentUserId();
            if(currentUserId == -1){ //UserId could not be found
                menuChangeUser(); //Force the user to login
            }
            currentUser = getUserFromUserId(currentUserId);
            if(currentUser == null){ //If there is no current user
                menuChangeUser(); //Force the user to login
            }
        }
        else{
            //Manually log in the user
            menuChangeUser();
        }
    }

    public void menuChangeUser(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Voer je gebruikers ID in om in te loggen:");
        int userId = 0;
        try{
            userId = scanner.nextInt();
        }
        catch(Exception ex){
            //System.out.println("Exception error! Message: " + ex);
            System.out.println("Voer een geldig *nummer* in voor je gebruikers ID.");
            menuChangeUser();
            return;
        }

        User newUser = getUserFromUserId(userId);

        System.out.println("Voer je wachtwoord in:");
        Scanner scanner2 = new Scanner(System.in);
        String passwordInput = scanner2.nextLine();

        if(passwordInput.equals(newUser.getPassword())){
            changeUser(userId);
        }
        else{
            System.out.println("Het wachtwoord komt niet overeen. Probeer het opnieuw.");
            menuChangeUser();
        }


    }
    private void changeUser(int userId){
        if (getUserFromUserId(userId) != null){ //If user exists
            SettingsHelper.UpdateCurrentUser(userId); //Save userId to file
            currentUser = getUserFromUserId((userId)); //Store current user in a variable
        }
        else{ //If user doesn't exist
            System.out.println("Error. Kon de opgegeven gebruiker niet vinden. Probeer het opnieuw.");
            menuChangeUser();
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
