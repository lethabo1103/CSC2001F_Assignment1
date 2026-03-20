import java.util.*;
import java.io.*;

public class Experiment {

    public static void main(String[] args) {

        String csvFile = "SAPlaceNames.csv";
        String optimalFile = "SAPlaceNamesOptimal.txt";

        int[] sizes = {1000,2000,3000,4000,5000,6000,7000,8000,9000,10000};

        String[] queries = QueryGenerator.generateQueries(csvFile, 100);

        // Header
        System.out.printf("%-6s %-12s %-15s %-15s %-15s%n",
                "N", "Array", "BST-Random", "BST-Sorted", "BST-Optimal");

        for (int N : sizes) {

            // ===== ARRAY =====
            PlaceNameArray array = new PlaceNameArray(N);
            array.loadFromFile(csvFile, N);

            int totalArray = 0;
            for (String q : queries) {
                array.search(q);
                totalArray += array.getComparisons();
            }
            double avgArray = (double) totalArray / queries.length;

            // ===== BST RANDOM =====
            PlaceNameBST bstRandom = new PlaceNameBST();
            loadBST(bstRandom, csvFile, N, true);

            int totalRandom = 0;
            for (String q : queries) {
                bstRandom.search(q);
                totalRandom += bstRandom.getComparisons();
            }
            double avgRandom = (double) totalRandom / queries.length;

            // ===== BST SORTED (WORST CASE) =====
            PlaceNameBST bstSorted = new PlaceNameBST();
            loadSortedBST(bstSorted, csvFile, N);

            int totalSorted = 0;
            for (String q : queries) {
                bstSorted.search(q);
                totalSorted += bstSorted.getComparisons();
            }
            double avgSorted = (double) totalSorted / queries.length;

            // ===== BST OPTIMAL (BEST CASE) =====
            PlaceNameBST bstOptimal = new PlaceNameBST();
            loadOptimalBST(bstOptimal, optimalFile, N);

            int totalOptimal = 0;
            for (String q : queries) {
                bstOptimal.search(q);
                totalOptimal += bstOptimal.getComparisons();
            }
            double avgOptimal = (double) totalOptimal / queries.length;

            // Print row with doubles
            System.out.printf("%-6d %-12.2f %-15.2f %-15.2f %-15.2f%n",
                    N, avgArray, avgRandom, avgSorted, avgOptimal);
        }
    }

    // ================= HELPER METHODS =================

    public static void loadBST(PlaceNameBST bst, String filename, int N, boolean hasHeader) {
        try {
            Scanner scanner = new Scanner(new File(filename));

            if (hasHeader && scanner.hasNextLine()) {
                scanner.nextLine();
            }

            int count = 0;
            while (scanner.hasNextLine() && count < N) {
                String[] parts = scanner.nextLine().split(",", -1);
                if (parts.length < 5) continue;

                String placeName = parts[1].trim();
                String municipality = parts[2].trim();
                String province = parts[3].trim();
                int population = Integer.parseInt(parts[4].trim());

                bst.insert(new PlaceNameEntry(placeName, municipality, province, population));
                count++;
            }

            scanner.close();

        } catch (Exception e) {
            System.out.println("Error loading BST");
        }
    }

    public static void loadSortedBST(PlaceNameBST bst, String filename, int N) {
        ArrayList<PlaceNameEntry> list = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File(filename));

            if (scanner.hasNextLine()) scanner.nextLine();

            int count = 0;
            while (scanner.hasNextLine() && count < N) {
                String[] parts = scanner.nextLine().split(",", -1);
                if (parts.length < 5) continue;

                list.add(new PlaceNameEntry(
                        parts[1].trim(), parts[2].trim(), parts[3].trim(), Integer.parseInt(parts[4].trim())
                ));
                count++;
            }

            scanner.close();
        } catch (Exception e) {}

        // sort alphabetically → skewed BST (worst case)
        list.sort(Comparator.comparing(PlaceNameEntry::getPlaceName));

        for (PlaceNameEntry entry : list) {
            bst.insert(entry);
        }
    }

    public static void loadOptimalBST(PlaceNameBST bst, String filename, int N) {
        try {
            Scanner scanner = new Scanner(new File(filename));

            int count = 0;
            while (scanner.hasNextLine() && count < N) {
                String placeName = scanner.nextLine().trim();

                PlaceNameEntry entry = new PlaceNameEntry(
                        placeName, "Unknown", "Unknown", 0
                );
                bst.insert(entry);
                count++;
            }

            scanner.close();
        } catch (Exception e) {
            System.out.println("Error loading optimal BST");
        }
    }
}