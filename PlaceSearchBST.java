import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class PlaceSearchBST {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        PlaceNameBST bst = null;

        while (true) {
            System.out.println("\n--- Place Search (BST) ---");
            System.out.println("1. Load dataset");
            System.out.println("2. Search for a place");
            System.out.println("3. Quit");
            System.out.print("Choose option: ");

            int choice = input.nextInt();
            input.nextLine(); // clear buffer

            if (choice == 1) {
                System.out.print("Enter filename: ");
                String filename = input.nextLine();

                System.out.print("Enter number of records to load (N): ");
                int N = input.nextInt();
                input.nextLine();

                bst = new PlaceNameBST();

                try {
                    Scanner fileScanner = new Scanner(new File(filename));

                    // skip header
                    if (fileScanner.hasNextLine()) {
                        fileScanner.nextLine();
                    }

                    int count = 0;

                    while (fileScanner.hasNextLine() && count < N) {
                        String line = fileScanner.nextLine();
                        String[] parts = line.split(",");

                        String placeName = parts[1];
                        String municipality = parts[2];
                        String province = parts[3];
                        int population = Integer.parseInt(parts[4]);

                        PlaceNameEntry entry = new PlaceNameEntry(
                                placeName, municipality, province, population);

                        bst.insert(entry);
                        count++;
                    }

                    fileScanner.close();
                    System.out.println("Loaded " + count + " records.");

                } catch (FileNotFoundException e) {
                    System.out.println("File not found.");
                }

            } else if (choice == 2) {
                if (bst == null) {
                    System.out.println("Load data first!");
                    continue;
                }

                System.out.print("Enter place name: ");
                String name = input.nextLine();

                PlaceNameEntry result = bst.search(name);

                if (result != null) {
                    System.out.println("Found: " + result);
                } else {
                    System.out.println("Not found.");
                }

                System.out.println("Comparisons: " + bst.getComparisons());

            } else if (choice == 3) {
                System.out.println("Goodbye!");
                break;
            }
        }

        input.close();
    }
}