package com.mzinck.a1;

import java.util.Scanner;

/**
 * Created by mitchell on 9/27/2016.
 */
public class Problem3 {

    /**
     * Reads an integer, checks if it is prime and then adds its calculation result to an output string.
     * Output is printed at the end of the program.
     * @param args
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String output = "";
        int read;
        while ((read = scan.nextInt()) > 0) {
            if (!isPrime(read)) {
                output += read + " is not prime\n";
            } else {
                output += read + " is prime\n";
            }
        }

        System.out.println(output);
    }

    /**
     * Calculates if a number is prime. Checks by modulo.
     * @param number checks if this is prime
     * @return false if it is not prime, true if it is
     */
    public static boolean isPrime(int number) {
        for(int start = number - 1; start > 1; start--) {
            if(number % start == 0) {
                return false;
            }
        }
        return true;
    }

}
