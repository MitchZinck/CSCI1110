package com.mzinck.a2.problem3;

import com.mzinck.a2.ImageRW;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Mitchell Zinck <github.com/mitchzinck>
 *         This program removes artifacts in a grayscale image that are smaller than 2x2.
 */
public class Problem3 {

    public static ImageRW imageRW;
    public static String[] loadSave; //load and save locations
    public static int height;
    public static int width;
    public static double artiCount = 0;

    /**
     * Start of our program. Loads the image, checks for artifacts.
     */
    public static void main(String[] args) {
        //Scan the load and save locations, then load the image.
        Scanner scan = new Scanner(System.in);
        loadSave = scan.nextLine().split(" ");
        imageRW = new ImageRW();
        loadImage();

        height = imageRW.getHeight();
        width = imageRW.getWidth();

        //Loops through every pixel in the image
        Random rand = new Random();
        for (int w = 1; w < width - 1; w ++) {
            for (int h = 1; h < height - 1; h ++) {
                if(isArtifact(w, h)) { //checks if its an artifact
                    //imageRW.setPixel(w, h, new int[] {rand.nextInt(250), rand.nextInt(250), 123});
                    imageRW.setPixel(w, h, new int[] {0, 0, 0}); //sets the artifact to black
                }
            }
        }

        save();
        System.out.println((int) artiCount);
    }

    /**
     * Checks every pixel touching the specified pixel to find out if it is also white.
     * If it finds enough pixels to determine the artifact to be bigger than 2x2
     * then it returns false. Otherwise it returns true.
     * @param x The x coordinate of the pixel
     * @param y The y coordinate of the pixel
     * @return  false if the pixel is not part of an artifact, true if it is
     */
    public static boolean isArtifact(int x, int y) {
        int count = 0;
        int[] rgb = new int[3];
        imageRW.getPixel(x, y, rgb);
        if(rgb[0] == 0) {
            return false;
        }
        double szs = 0;
        LinkedHashMap<Integer, Integer> prevCoordMap = new LinkedHashMap<Integer, Integer>();
        for(int xx = x - 1; xx < x + 2; xx++) {
            for(int yy = y - 1; yy < y + 2; yy++) {
                if(xx == x && yy == y) {
                    continue;
                }
                imageRW.getPixel(xx, yy, rgb);
                if(rgb[0] == 255) {
                    count++;
                    prevCoordMap.put(x, y);
                    if(!isArtifact(xx, yy, prevCoordMap)) {
                        return false;
                    }
                }

                szs += prevCoordMap.size() > 0 ? prevCoordMap.size() + 1 : 0;
                prevCoordMap.clear();
            }
        }
        if(count >= 2) {
            return false;
        }

        artiCount += szs;
        return true;
    }

    /**
     * Continues to check if a touching pixel to the original pixel is part of an artifact.
     * @param x Coordinate of touching pixel
     * @param y Coordinate of touching pixel
     * @param prevCoordMap Map containing previous coordinates of already checked pixels
     * @return true if it is part of an artifact, false if it is not
     */
    public static boolean isArtifact(int x, int y, LinkedHashMap<Integer, Integer> prevCoordMap) {
        int[] rgb = new int[3];
        for(int xx = x - 1; xx < x + 2; xx++) {
            for(int yy = y - 1; yy < y + 2; yy++) {
                boolean b = false;
                for(Map.Entry<Integer, Integer> entry : prevCoordMap.entrySet()) {
                    if(xx == entry.getKey() && yy == entry.getValue()) {
                        b = true;
                    }
                }
                if((xx == x && yy == y) || b == true) {
                    continue;
                }
                imageRW.getPixel(xx, yy, rgb);
                if(rgb[0] == 255) {
                    prevCoordMap.put(xx, yy);
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * Save the image.
     */
    public static void save() {
        imageRW.save(loadSave[1]);
    }

    /**
     * Load the image.
     */
    public static void loadImage() {
        imageRW.load(loadSave[0]);
    }

}
