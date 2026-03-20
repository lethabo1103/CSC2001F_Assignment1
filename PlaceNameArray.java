import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PlaceNameArray {

    private PlaceNameEntry[] data;
    private int size;
    private int comparisons;

    // Constructor
    public PlaceNameArray(int capacity) {
        data = new PlaceNameEntry[capacity];
        size = 0;
        comparisons = 0;
    }

   
    public void add(PlaceNameEntry entry) {      
        // check for duplicates
        for (int i = 0; i < size; i++) {
            if (data[i].getPlaceName().equals(entry.getPlaceName())) {
                return; // skip duplicate
            }
        }

        if (size < data.length) {
            data[size] = entry;
            size++;
        }
    }

    // LOAD FROM FILE
    public void loadFromFile(String filename, int N) {
    size = 0; // reset

    try {
        File file = new File(filename);
        Scanner fileScanner = new Scanner(file);

        // skip header
        if (fileScanner.hasNextLine()) {
            fileScanner.nextLine();
        }

        int count = 0;

        while (fileScanner.hasNextLine() && count < N) {
            String line = fileScanner.nextLine();

            String[] parts = line.split(",", -1);

            if (parts.length < 5) continue;

            try {
                String placeName = parts[1].trim();
                String municipality = parts[2].trim();
                String province = parts[3].trim();
                int population = Integer.parseInt(parts[4].trim());

                PlaceNameEntry entry = new PlaceNameEntry(
                        placeName, municipality, province, population
                );

                add(entry);
                count++;

            } catch (Exception e) {
                // skip bad rows
            }
        }

        fileScanner.close();

    } catch (FileNotFoundException e) {
        System.out.println("File not found!");
    }
}
    // SEARCH METHOD
 
    public PlaceNameEntry search(String name) {
        comparisons = 0; // reset comparisons

        for (int i = 0; i < size; i++) {
            comparisons++;
            if (data[i].getPlaceName().equals(name)) {
                return data[i];
            }
        }

        return null; // not found
    }

    // GET COMPARISONS
   public int getComparisons() {
        return comparisons;
    }
}