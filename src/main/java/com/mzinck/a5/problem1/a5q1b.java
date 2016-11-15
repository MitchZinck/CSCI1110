import java.util.*;

/**
 * Created by Mitchell <github.com/mitchzinck>
 * Program that sorts words based on their alphabetical order. UCollections.sort()
 */
public class a5q1b {

    /**
     * The start of our program. Reads input and passes it to our bubble_sort method.
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        scan.useDelimiter("[^a-zA-Z]+");

        ArrayList<String> words = new ArrayList<>();
        while (scan.hasNext()) {
            words.add(scan.next().toLowerCase());
        }

        Collections.sort(words);

        LinkedHashMap<String, Integer> count = new LinkedHashMap<>();

        for (int i = 0; i < words.size(); i++) {
            int z = count.get(words.get(i)) != null ? count.get(words.get(i)) + 1 : 1;
            count.put(words.get(i), z);
        }

        String output = "";
        for (Map.Entry<String, Integer> entry : count.entrySet()) {
            output += entry.getKey() + " " + entry.getValue() + "\n";
        }
        output = output.substring(0, output.length() - 1);
        System.out.print(output);
    }
}
