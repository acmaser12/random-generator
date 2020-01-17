import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
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
        ArrayList<String> firstNames = new ArrayList<>();
        ArrayList<String> lastNames = new ArrayList<>();

        // get ArrayLists from files and store in variables
        try {
            firstNames = getNames(new File("firstNames.txt"));
            lastNames = getNames(new File("lastNames.txt"));
        } catch (FileNotFoundException ex) {
            System.out.println("Error: File not found in project directory...");
        }

        //randomly assign first names to last names
        HashMap<String, String> fullNames = getFullNames(firstNames, lastNames);
    }

    // loads first and last names from files and creates full names
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

    // generates pairs of first and last names
    public static HashMap<String, String> getFullNames(ArrayList<String> firstNames, ArrayList<String> lastNames) {
        // HashMap container
        HashMap<String, String> fullNames = new HashMap<>();

        // loop through and create full names until limit is met
        Random r = new Random();
        for (int i = 0; i < 20; i++) {
            // generate random indices
            int randomFirstName = r.nextInt(firstNames.size());
            int randomLastName = r.nextInt(lastNames.size());

            // get full name from params and store in HashMap
            fullNames.put(lastNames.get(randomLastName), firstNames.get(randomFirstName));
        }

        // return full names
        return fullNames;
    }
}
