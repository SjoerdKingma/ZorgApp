package Helpers;

import java.util.Scanner;

public final class ScannerHelper {

    private static final String ErrorNoInteger = "Please enter a valid number";

    private ScannerHelper(){

    }

    //Reads an integer from user input.
    //Returns -1 if the user input could not be converted to a number
    public int readInt(String question){
        int result = 0;

        System.out.println(question);
        Scanner scanner = new Scanner(System.in);

        try{
            result = scanner.nextInt();
            return result;
        }
        catch(Exception ex){
            return -1;
        }
    }
}
