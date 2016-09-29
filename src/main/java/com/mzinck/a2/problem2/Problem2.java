package com.mzinck.a2.problem2;

import com.mzinck.a2.ImageRW;

import java.util.Scanner;

/**
 * @author Mitchell Zinck <github.com/mitchzinck>
 *         This program converts a grayscale image into an image that represents edges.
 */
public class Problem2 {

    public static ImageRW imageRW;
    public static String[] loadSave; //load and save locations
    public static int threshold;

    /**
     * Start of our program. Loads the image, makes an outline of the edges.
     *
     * @param args
     */
    public static void main(String[] args) {
        //Scan the load and save locations, then load the image.
        Scanner scan = new Scanner(System.in);
        loadSave = scan.nextLine().split(" ");
        threshold = Integer.parseInt(loadSave[2]);
        imageRW = new ImageRW();
        loadImage();

        int height = imageRW.getHeight();
        int width = imageRW.getWidth();

        //Loops through every pixel in the image
        for (int w = 0; w < width - 1; w++) {
            for (int h = 0; h < height - 1; h++) {
                //Set an rgb array, get the current pixel
                int[] rgb = new int[3];
                imageRW.getPixel(w, h, rgb);
                //set the pixel as an edge or black
                imageRW.setPixel(w, h, pixelDetect(w, h, rgb));
            }
        }

        save();
    }

    /**
     * Detects if the pixel is an edge or not
     * @param x The x coord of the pixel
     * @param y The y coord of the pixel
     * @param rgb The RGB of the pixel
     * @return
     */
    public static int[] pixelDetect(int x, int y, int[] rgb) {
        //get p(x + 1, y)
        int[] rgbx1 = new int[3];
        imageRW.getPixel(x + 1, y, rgbx1);

        //get p(x, y+1)
        int[] rgby1 = new int[3];
        imageRW.getPixel(x, y + 1, rgby1);

        //retrieves the pixel formula
        double d = pixelFormula(rgb[0], rgbx1[0], rgby1[0]);
        //if d > threshold return a white value, else return a black value
        return d > threshold ? new int[] {255, 255, 255} : new int[] {0, 0, 0};
    }

    /**
     * unsquared pixel detection formula
     * @param rgb
     * @param rgbx1
     * @param rgby1
     * @return
     */
    public static double pixelFormula(int rgb, int rgbx1, int rgby1) {
        return Math.sqrt(Math.pow(rgb - rgbx1, 2) + Math.pow(rgb - rgby1, 2));
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
