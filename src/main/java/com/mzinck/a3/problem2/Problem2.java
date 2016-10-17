package com.mzinck.a3.problem2;

import java.util.*;

/**
 * Created by Mitchell <github.com/mitchzinck>
 * Program that sorts words based on their alphabetical order. Uses bubble sort.
 */
public class Problem2 {

    public static String[] result;

    /**
     * The start of our program. Reads input and passes it to our bubble_sort method.
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        scan.useDelimiter("[^a-zA-Z]+" );

        ArrayList<String> words = new ArrayList<String>();
        while(scan.hasNext()) {
            words.add(scan.next().toLowerCase());
        }

        bubble_sort(words.toArray(new String[0]), words.size());

        LinkedHashMap<String, Integer> count = new LinkedHashMap<String, Integer>();

        for(int i = 0; i < result.length; i++) {
            int z = count.get(result[i]) != null ? count.get(result[i]) + 1 : 1;
            count.put(result[i], z);
        }

        String output = "";
        for(Map.Entry<String, Integer> entry : count.entrySet()) {
            output += entry.getKey() + " " + entry.getValue() + "\n";
        }
        output = output.substring(0, output.length() - 1);
        System.out.print(output);
    }

    /**
     * bubble sorts the words using the bubble sort algorithm and javas compareTo method.
     * @param arr the array of words to sort.
     * @param length the length of the array, gradually decreasing as the array is more sorted.
     */
    public static void bubble_sort(String[] arr, int length) {
        if (length != 0) {
            int n = 0;
            for (int i = 1; i < length; i++) {
                if (arr[i - 1].compareTo(arr[i]) > 0) {
                    String x;
                    x = arr[i - 1];
                    arr[i - 1] = arr[i];
                    arr[i] = x;
                    n = i;
                }
            }
            bubble_sort(arr, n);
        } else {
            result = arr;
        }
    }

}
