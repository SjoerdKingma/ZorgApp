import java.io.*;

public final class SettingsEditor {

    private final static String SettingsPath = "src/CurrentUser.txt";

    private SettingsEditor(){

    }

    public static int GetCurrentUserId(){

        try{
            BufferedReader reader = new BufferedReader(new FileReader(SettingsPath));
            int userId = 0;
            try{
                userId = Integer.parseInt(reader.readLine());
            }
            catch(NumberFormatException ex){
                ex.printStackTrace();
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
            BufferedWriter writer = new BufferedWriter(new FileWriter(SettingsPath));
            writer.write(String.valueOf(userId));
            writer.close();
            System.out.println("UserId succesfully changed!");
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
    /*public static void ChangeUser(int userId){
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run(){
                //save data here
                int CurrentUserId = userId;
                System.out.println("Saving userId: "+ userId);
            }
        });
    }*/

    /*public static void ChangeUserJson(int userId){
        String settingsPath = "Settings.json";

        String path = "/app/json/companies.json";

        JSONObject json = new JSONObject();
        try {
            json.put("name", "Google");
            json.put("employees", 140000);
            json.put("offices", List.of("Mountain View", "Los Angeles", "New York"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try (PrintWriter out = new PrintWriter(new FileWriter(path))) {
            out.write(json.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

