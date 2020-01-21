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

        // create map grouped by first name
        System.out.println("\nCombine all names by First Name");
        HashMap<String, ArrayList<String>> groupedFullNames = groupNames(fullNames);

        // loop through map and output
        Set<String> keys = groupedFullNames.keySet();
        for (String key : keys) {
            System.out.println("\t" + key);
            ArrayList<String> values = groupedFullNames.get(key);
            values.sort(new SortByFirstName()); // sorts the last names before output
            for (String value : values) {
                System.out.println("\t\t" + value);
            }
        }
    }

    /**
     * @param nameFile file of names, separated by newline character
     * @return ArrayList of names, taken from file
     * @throws FileNotFoundException if file is not found,
     * exception thrown
     */
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

    /**
     * @param names ArrayList of full names
     * @return HashMap of names grouped into keys of first names
     * values that are in ArrayLists
     */
    public static HashMap<String, ArrayList<String>> groupNames(ArrayList<String> names) {
        // HashMap container
        HashMap<String, ArrayList<String>> fullNames = new HashMap<>();

        // loop through and create full names until limit is met
        for (int i = 0; i < 20; i++) {
            // get full name from params and store in HashMap
            String[] name = names.get(i).split(" ");
            String firstName = name[0];
            String lastName = name[1];

            // if fullNames map contains the first name, add to last name ArrayList
            // else, add new key and ArrayList
            if (fullNames.containsKey(firstName)) {
                // add last name to ArrayList at key of first name in Map
                fullNames.get(firstName).add(lastName);
            } else {
                // create new ArrayList and put ArrayList at new first name key in Map
                ArrayList<String> tempArrayList = new ArrayList<>();
                tempArrayList.add(lastName);
                fullNames.put(firstName, tempArrayList);
            }
        }
        return fullNames;
    }

    /**
     * @param firstNames ArrayList<String> of first names from file
     * @param lastNames ArrayList<String> of last names from file
     * @return list of randomly-generated full names in ArrayList<String>
     */
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
