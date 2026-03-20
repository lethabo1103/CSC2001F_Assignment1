import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class PlaceSearchArray {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PlaceNameArray placeArray = null;
        boolean running = true;

        while (running) {
            System.out.println("\n--- Place Search Array Menu ---");
            System.out.println("1. Load dataset");
            System.out.println("2. Search for a place name");
            System.out.println("3. Quit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter filename: ");
                    String filename = scanner.nextLine();
                    System.out.print("Enter number of records to load: ");
                    int N = scanner.nextInt();
                    scanner.nextLine();
                    placeArray = new PlaceNameArray(N);
                    placeArray.loadFromFile(filename, N);
                    break;

                case 2:
                    if (placeArray == null) {
                        System.out.println("Please load data first.");
                        break;
                    }
                    System.out.print("Enter place name to search: ");
                    String name = scanner.nextLine();
                    PlaceNameEntry entry = placeArray.search(name);
                    if (entry != null) {
                        System.out.println("Found: " + entry);
                        System.out.println("Comparisons made: " + placeArray.getComparisons());
                    } else {
                        System.out.println("Place name not found.");
                        System.out.println("Comparisons made: " + placeArray.getComparisons());
                    }
                    break;

                case 3:
                    running = false;
                    System.out.println("Exiting program.");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }

        scanner.close();
    }
}