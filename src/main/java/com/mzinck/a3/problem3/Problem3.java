package com.mzinck.a3.problem3;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by mitchell on 10/17/2016.
 */
public class Problem3 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] input = scan.nextLine().split(" ");
        prefixEval(input);


//        for(int i = 0; i < input.length; i++) {
//            if(input[i].contains("*/-+")) {
//                ope[i][0] = input[i];
//                ope[i]
//            }
//        }
    }

    public static int prefixEval(String[] exp) {
        Stack<Integer> prefixStack = new Stack();//a stack of type int
        //the expression will be a string array

        int result;//a result to push onto the stack after an operation was done

        String[] r = new String[exp.length];

        for (int i = exp.length - 1; i > -1; i--) {
            r[(exp.length - 1) - i] = exp[i];
        }

        int n;
        for (String c : r)//for each string character in the array
        {
            if (!c.matches("[+/*-]"))//if the character can be converted to a number (operand)
            {
                prefixStack.push(Integer.parseInt(c));//push the number onto the stack
            }
            if (c.equals("+"))//handling of operators
            {
                int x = prefixStack.pop();
                int y = prefixStack.pop();
                result = x + y;//evaluate the values popped from the stack
                prefixStack.push(result);//push current result onto the stack
            }
            if (c.equals("-")) {
                int x = prefixStack.pop();
                int y = prefixStack.pop();
                result = y - x;
                prefixStack.push(result);
            }
            if (c.equals("*")) {
                int x = prefixStack.pop();
                int y = prefixStack.pop();
                result = x * y;
                prefixStack.push(result);
            }
            if (c.equals("/")) {
                int x = prefixStack.pop();
                int y = prefixStack.pop();
                result = y / x;
                prefixStack.push(result);
            }

        }
            /*write the final result of the expression,
             * which is at the top of the stack, so we use Peek()*/
        System.out.println("result of expression: " + prefixStack.peek() );
        return 0;
    }


}
