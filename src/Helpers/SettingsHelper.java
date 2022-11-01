package Helpers;

import java.io.*;

public final class SettingsHelper {

    private final static String CurrentUserPath = "src/CurrentUser.txt";

    private SettingsHelper(){}

    public static int GetCurrentUserId(){

        try{
            BufferedReader reader = new BufferedReader(new FileReader(CurrentUserPath));
            int userId = -1;
            try{
                userId = Integer.parseInt(reader.readLine());
            }
            catch(NumberFormatException ex){
                /*ex.printStackTrace();*/
            }
            reader.close();
            return userId;

        }catch(IOException e){
            e.printStackTrace();
            return 0;
        }
    }

    public static void UpdateCurrentUser(int userId) {
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(CurrentUserPath));
            writer.write(String.valueOf(userId));
            writer.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}


