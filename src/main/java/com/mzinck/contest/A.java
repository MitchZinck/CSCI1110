package com.mzinck.contest;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by mitchell on 10/1/2016.
 */
public class A {

    public static Scanner scan;
    public static String result = "";

    public static void main(String[] args) {
        int testCases;
        scan = new Scanner(System.in);
        testCases = scan.nextInt();

        for (int i = 0; i < testCases; i++) {
            findConnections();
        }

        result = result.substring(0, result.length() - 1);
        System.out.print(result);
    }

    public static void findConnections() {
        scan.nextInt();
        int total = scan.nextInt();
        scan.nextLine();
        Map<String, Person> persons = new ConcurrentHashMap<String, Person>();
        String[][] network = new String[total][2];

        for (int i = 0; i < total; i++) {
            String[] input = scan.nextLine().split(" ");
            network[i][0] = input[0];
            network[i][1] = input[1];
        }


        for (int z = 0; z < network.length; z++) {
            Person p;
            Person pp;
            if (persons.get(network[z][0]) == null) {
                p = new Person(network[z][0]);
                persons.put(network[z][0], p);
            } else {
                p = persons.get(network[z][0]);
            }

            if (persons.get(network[z][1]) == null) {
                pp = new Person(network[z][1]);
                persons.put(network[z][1], pp);
            } else {
                pp = persons.get(network[z][1]);
            }

            p.getLvl1().add(pp.getName());
            p.getDone().put(pp.getName(), 1);
            p.incLevel1();
            pp.getLvl1().add(p.getName());
            pp.getDone().put(p.getName(), 1);
            pp.incLevel1();
        }

        for (Map.Entry<String, Person> entry : persons.entrySet()) {
            findConnections(entry.getValue(), persons);
            result += entry.getValue().getName() + " " + entry.getValue().getLevel1()
                    + " " + entry.getValue().getLevel2() + " " + entry.getValue().getLevel3()
                    + " " + entry.getValue().getLevel4() + " " + entry.getValue().getLevel5()
                    + " " + entry.getValue().getLevel6() + "\n";
        }
    }

    public static void findConnections(Person p, Map<String, Person> persons) {
        for (String s : p.getLvl1()) {
            findConnections(p, persons.get(s), persons, 2);
        }

        for(int i = 0; i < 6; i++) {
            if(p.getLevels()[i] == 0 && i != 0) {
                for(int z = i; z < 6; z++) {
                    p.getLevels()[z] = p.getLevels()[z + 1];
                }
            }
        }
    }

    public static void findConnections(Person original, Person p, Map<String, Person> persons, int l) {
        for (String s : p.getLvl1()) {
            //System.out.println(p.getName() + " " + s + " " + l);
            if (original.getDone().get(s) == null && !s.equals(original.getName())) {
                original.getDone().put(s, l);
                original.incLevel(l);
                if (l < 6) {
                    l++;
                }
                findConnections(original, persons.get(s), persons, l);
                if(l < 7) {
                    l--;
                }
            } else if ((!s.equals(original.getName()) && original.getDone().get(s) != null) && original.getDone().get(s) > l) {
                //System.out.println(s + " " + original.getDone().get(s) + " " + l);
                original.decLevel(original.getDone().get(s));
                original.incLevel(l);
                original.getDone().put(s, l);
            }
        }
    }

}

class Person {

    public String name;
    public ArrayList<String> lvl1;
    public ConcurrentHashMap<String, Integer> done;
    public int[] levels;

    public Person(String name) {
        this.name = name;
        levels = new int[7];
        lvl1 = new ArrayList<String>();
        done = new ConcurrentHashMap<String, Integer>();
    }

    public ArrayList<String> getLvl1() {
        return lvl1;
    }

    public ConcurrentHashMap<String, Integer> getDone() {
        return done;
    }

    public void incLevel1() {
        levels[0]++;
    }

    public void incLevel(int level) {
        switch (level) {
            case 1:
                levels[0]++;
                break;
            case 2:
                levels[1]++;
                break;
            case 3:
                levels[2]++;
                break;
            case 4:
                levels[3]++;
                break;
            case 5:
                levels[4]++;
                break;
            case 6:
                levels[5]++;
                break;
        }
    }

    public void decLevel(int level) {
        switch (level) {
            case 1:
                levels[0]--;
                break;
            case 2:
                levels[1]--;
                break;
            case 3:
                levels[2]--;
                break;
            case 4:
                levels[3]--;
                break;
            case 5:
                levels[4]--;
                break;
            case 6:
                levels[5]--;
                break;
        }
    }

    public String getName() {
        return name;
    }

    public int[] getLevels() { return levels;}

    public int getLevel1() {
        return levels[0];
    }

    public int getLevel2() {
        return levels[1];
    }

    public int getLevel3() {
        return levels[2];
    }

    public int getLevel4() {
        return levels[3];
    }

    public int getLevel5() {
        return levels[4];
    }

    public int getLevel6() {
        return levels[5];
    }
}
