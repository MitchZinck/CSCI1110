import java.util.Scanner;

/**
 * Created by Mitchell <github.com/mitchzinck>
 */
public class TestHarnassCircularArray {

    /**
     * Tests our CircularArray class
     * @param args
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        CircularArray c = new CircularArray(1);

        String s = "";
        String output = "";
        while (!(s = scan.nextLine()).equals("end")) {
            String[] split = s.split(" ");
            if (s.startsWith("addat")) {
                c.add(Integer.parseInt(split[1]), Integer.parseInt(split[2]));
            } else if (s.startsWith("add")) {
                c.add(Integer.parseInt(split[1]));
            } else if (s.startsWith("remove")) {
                c.remove(Integer.parseInt(split[1]));
            } else if (s.equals("print")) {
                if (c.content.length != 1 || c.content[0] != 0) {
                    for (int i = c.front; i <= c.size; i++) {
                        if(i == c.size && c.back < c.front) {
                            i = 0;
                        }
                        if(i == c.back) {
                            break;
                        }
                        output += c.content[i] + " ";
                    }
                    output += c.content[c.back] + "\n";
                }
                
                for (int i = 0; i < c.content.length; i++) {
                    if (i == c.front) {
                        output += "f:";
                    }
                    if (i == c.back) {
                        output += "e:";
                    }
                    if (c.content[i] == 0) {
                        output += ". ";
                    } else {
                        output += c.content[i] + " ";
                    }
                }
                output += "\n";
            }
        }

        output = output.substring(0, output.length() - 1);
        System.out.print(output);
    }

}
