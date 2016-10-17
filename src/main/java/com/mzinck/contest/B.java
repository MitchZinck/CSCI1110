import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Mitchell Zinck <github.com/mitchzinck>
 *     This code sucks bad don't judge my redbull fueled code :(
 */
public class B {

    public static String alpha = "abcdefghijklmnopqrstuvwxyz";
    public static String[] words =

            {
                    "firstphrase", "secondphrase", "thirdphrase",
                    "fourthphrase", "fifthphrase", "sixthphrase",
                    "wordone", "wordtwo", "wordthree",
                    "wordfour", "wordfive", "wordsix"
            };

    public static void main(String[] args) {
        String[] input;
        String in;

        Scanner scan = new Scanner(System.in);
        int c = scan.nextInt();
        scan.nextLine();


        for(int count = 0; count < c; count++) {
            in = scan.nextLine();
            if(in == null) {
                break;
            }
            ArrayList<String> confirmed = new ArrayList<String>();
            input = in.split(" ");
            for(int z = 0; z < 2; z++) {
                for (int i = 0; i < words.length; i++) {
                    String r = decrypt(words[i], input[z]);
                    if(r == null) {
                        continue;
                    }
                    //System.out.println(r);
                    String[] spl = r.split(" ");
                    if(!spl[0].equals(input[0]) && !spl[0].equals(input[1])) {
                        continue;
                    }

                    confirmed.add(spl[0] + " " + spl[1]);
                }
            }

            String[] results = new String[2];
            String keyResult = "";

            for(String s : confirmed) {
                String[] spl = s.split(" ");
                for(String z : confirmed) {
                    String[] spl2 = z.split(" ");
                    if(spl2[0].equals(spl[0])) {
                        continue;
                    }
                    if(spl[1].equals(spl2[1])) {
                        results[0] = s;
                        results[1] = z;
                        keyResult = spl[1];
                    } else {
                        //System.out.println("hi");
                        if(spl[1].length() > spl2[1].length()) {
                            //System.out.println("hi");
                            if(spl[1].startsWith(spl2[1])) {
                                results[0] = s;
                                results[1] = z;
                                keyResult = spl[1];
                            }
                        } else {
                            //System.out.println(spl2[1] + " " + spl[1]);
                            if(spl2[1].startsWith(spl[1])) {
                                results[0] = s;
                                results[1] = z;
                                keyResult = spl2[1];
                            }
                        }
                    }
                }
                if(results[0] != null) {
                    break;
                }
            }

            if(results[0] == null) {
                if(count != c - 1) {
                    System.out.println("Key not found");
                } else {
                    System.out.print("Key not found");
                }
            } else {
                //System.out.println(results[0] + " " + results[1]);
                if(count != c - 1) {
                    System.out.println("Key = " + keyResult);
                } else {
                    System.out.print("Key = " + keyResult);
                }
            }
        }
    }

    public static String decrypt(String test, String test1) {
        if(test.length() > test1.length()) {
            return null;
        }
        String[] encrypted = {"", ""};
        String key = "";
        for (int i = 0; i < test.length(); i++) {
            for (int z = 0; z < 26; z++) {
                char a = alpha.charAt((alpha.indexOf(test.charAt(i)) + alpha.indexOf(alpha.charAt(z))) % 26);
                if (a == test1.charAt(i)) {
                    encrypted[0] += a;
                    key += alpha.charAt(z);
                    break;
                }
            }
        }

        return encrypted[0] + " " + key;
    }

}
