import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Adam Maser
 * CSC420
 * Week 1 Assignment
 * Random Number Generator
 * 1.15.19
 */

public class Generator {

    public static void main(String[] args) {
        // display name and email address as first output
        System.out.println("Submitted by Adam Maser - masera1@csp.edu");
        System.out.println("I certify that this is my own work");

        // declare ArrayLists to store names
        ArrayList<String> firstNames;
        ArrayList<String> lastNames;

        // get ArrayLists from files and store in variables
        try {
            firstNames = getNames(new File("firstNames.txt"));
            lastNames = getNames(new File("lastNames.txt"));
        } catch (FileNotFoundException ex) {
            System.out.println("Error: File not found in project directory...");
        }
    }


    //loads first and last names from files and creates full names
    public static ArrayList<String> getNames(File nameFile) throws FileNotFoundException {
        // read files into arrays
        Scanner nameReader = new Scanner(nameFile);
        ArrayList<String> names = new ArrayList<>();
        while (nameReader.hasNextLine()) {
            names.add(nameReader.nextLine());
        }
        // DEBUG System.out.println(firstNames);
        return names;
    }
}
