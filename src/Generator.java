import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Adam Maser
 * CSC420
 * Week 1 Assignment
 * Random Number Generator
 * 1.20.2020
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

        // get an ArrayList of full names and print
        System.out.println("\nGenerated Names:");
        ArrayList<String> fullNames = generateRandomNames(firstNames, lastNames);

        // sort by first names and output
        System.out.println("\nSorted by First Name");
        fullNames.sort(new SortByFirstName());
        for (String name: fullNames) {
            System.out.println("\t" + name);
        }

        // sort by last names and output
        System.out.println("\nSorted by Last Name");
        fullNames.sort(new SortByLastName());
        for (String name: fullNames) {
            System.out.println("\t" + name);
        }

    }

    // loads first and last names from files and creates full names
    public static ArrayList<String> getNames(File nameFile) throws FileNotFoundException {
        // read files into arrays
        Scanner nameReader = new Scanner(nameFile);
        ArrayList<String> names = new ArrayList<>();
        while (nameReader.hasNextLine()) {
            names.add(nameReader.nextLine());
        }
        return names;
    }

    // generates pairs of first and last names
    public static TreeMap<String, ArrayList<String>> getFullNameMap(ArrayList<String> firstNames, ArrayList<String> lastNames) {
        // HashMap container
        TreeMap<String, ArrayList<String>> fullNames = new TreeMap<>();

        // loop through and create full names until limit is met
        Random r = new Random();
        for (int i = 0; i < 20; i++) {
            // generate random indices
            int randomFirstName = r.nextInt(firstNames.size());
            int randomLastName = r.nextInt(lastNames.size());

            // get full name from params and store in HashMap
            if (fullNames.containsKey(firstNames.get(randomFirstName))) {

                // adds last name to ArrayList at key of first name in HashMap
                fullNames.get(firstNames.get(randomFirstName)).add(lastNames.get(randomLastName));
            } else {

                // create new ArrayList and put ArrayList at new first name key in HashMap
                ArrayList<String> tempArrayList = new ArrayList<>();
                tempArrayList.add(lastNames.get(randomLastName));
                fullNames.put(firstNames.get(randomFirstName), tempArrayList);
            }
        }

        // return full names
        return fullNames;
    }

    // pass in first and last name lists from file
    // generate full name list
    // print out generated names
    // return full name list
    public static ArrayList<String> generateRandomNames(ArrayList<String> firstNames, ArrayList<String> lastNames) {
        // create container for full names
        ArrayList<String> fullNames = new ArrayList<>(20);

        // generate random first name and last name
        // store in fullNames container
        Random r = new Random();
        for (int i = 0; i < 20; i++) {
            // generate random indices
            int randomFirstName = r.nextInt(firstNames.size());
            int randomLastName = r.nextInt(lastNames.size());

            // add to fullNames container and print
            fullNames.add(firstNames.get(randomFirstName) + " " + lastNames.get(randomLastName));
            System.out.println("\t" + (i + 1) + ". " + fullNames.get(i));
        }
        return fullNames;
    }
}

// Comparator for sorting by first names
class SortByFirstName implements Comparator<String> {
    @Override
    public int compare(String name1, String name2) {
         return name1.compareTo(name2);
    }
}

// Comparator for sorting by last names
class SortByLastName implements Comparator<String> {
    @Override
    public int compare(String name1, String name2) {
        // split names and save second item in array (last name) to lastName variables
        String lastName1 = name1.split(" ")[1];
        String lastName2 = name2.split(" ")[1];

        // return comparison of last names
        return lastName1.compareTo(lastName2);
    }
}
