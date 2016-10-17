package com.mzinck.a3.problem1;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Mitchell <github.com/mitchzinck>
 * This program sorts an array with the Bubble Sort algorithm.
 * Below is pseudocode describing an efficient BubbleSort algorithm that only sorts
 * items that haven't been placed in their final position, increasing efficiency by at least 50%.
 *
     //     procedure bubbleSort(A : list of sortable items, length = length(A))
     //        if length != 0 then
     //            n = 0;
     //            for i = 2; i <= length; i++
     //                if (arr[i - 1] > arr[i])
     //                    x = arr[i - 1];
     //                    arr[i - 1] = arr[i];
     //                    arr[i] = x;
     //                    n = i;
     //                end if
     //            end for
     //            repeat bubbleSort(A, length(A));
     //         end if
     //     end procedure
     //
 */
public class Problem1 {

    /**
     * The starting point of our program. Reads input, converts to an integer array and sends
     * off to our bubble_sort algorithm.
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] line;
        while (!(line = scan.nextLine().split(" "))[0].equals("0")) {
            System.out.print("Starting array: ");
            for (int i = 1; i < line.length; i++) {
                System.out.print(line[i] + " ");
            }
            System.out.println();
            System.out.println("Bubble sort:");
            bubble_sort(Arrays.asList(line).stream().mapToInt(Integer::parseInt).toArray(), line.length - 1);
        }
    }

    /**
     * A method that sorts through an integer array efficiently, only
     * sorting numbers that haven't reached their final position yet.
     * @param arr the array to sort.
     * @param length the length, starting at the size of the arr length and becoming smaller
     *               as the final position of numbers are reached, eventually reaching 0;
     */
    public static void bubble_sort(int[] arr, int length) {
        if (length != 0) {
            int n = 0;
            for (int i = 2; i <= length; i++) {
                if (arr[i - 1] > arr[i]) {
                    int x;
                    x = arr[i - 1];
                    arr[i - 1] = arr[i];
                    arr[i] = x;
                    n = i;
                }
            }
            print_array(arr);
            bubble_sort(arr, n);
        }
    }

    /**
     * A method for printing arrays.
     * @param arr the array to print.
     */
    public static void print_array(int[] arr) {
        System.out.print(" ");
        for (int i = 1; i < arr.length; i++) {
            if (i == arr.length - 1) {
                System.out.println(arr[i]);
            } else {
                System.out.print(arr[i] + " ");
            }
        }
    }

}
