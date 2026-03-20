import java.util.*;
import java.io.*;

public class QueryGenerator {

    public static String[] generateQueries(String filename, int numQueries) {
        ArrayList<String> names = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File(filename));

            if (scanner.hasNextLine()) scanner.nextLine(); // skip header

            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(",", -1);
                if (parts.length >= 2) {
                    names.add(parts[1].trim());
                }
            }

            scanner.close();
        } catch (Exception e) {
            System.out.println("Error reading file");
        }

        Collections.shuffle(names);

        String[] queries = new String[numQueries];
        for (int i = 0; i < numQueries; i++) {
            queries[i] = names.get(i);
        }

        return queries;
    }
}