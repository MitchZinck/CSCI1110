package com.mzinck.a2.problem1;

import com.mzinck.a2.ImageRW;

import java.util.Scanner;

/**
 * @author Mitchell Zinck <github.com/mitchzinck>
 *         This program converts a image to grayscale.
 */
public class Problem1 {

    public static ImageRW imageRW;
    public static String[] loadSave; //load and save locations

    /**
     * Start of our program. Loads the image, converts each pixel to grayscale and saves it.
     *
     * @param args
     */
    public static void main(String[] args) {
        //Scan the load and save locations, then load the image.
        Scanner scan = new Scanner(System.in);
        loadSave = scan.nextLine().split(" ");
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
                //set the pixel with a grayscale RGB
                imageRW.setPixel(w, h, grayScaleRGB(rgb));
            }
        }

        save();
    }

    /**
     * Save the image.
     */
    public static void save() {
//commented out for spec
//        System.out.println("Where do you want to save this grayscale image?");
//        Scanner scan = new Scanner(System.in);
//        if (!imageRW.save(scan.nextLine())) {
//            System.out.println("Image could not be saved. Try again.");
//            save();
//        }
        imageRW.save(loadSave[1]);
    }

    /**
     * Set the RGB color to grayscale. Formula is (R + G + B) / 3
     *
     * @param rgb the RGB array to turn into grayscale
     * @return The grayscale RGB
     */
    public static int[] grayScaleRGB(int[] rgb) {
        int pixel = (rgb[0] + rgb[1] + rgb[2]) / 3;
        for (int i = 0; i < rgb.length; i++) {
            rgb[i] = pixel;
        }
        return rgb;
    }

    /**
     * Load the image.
     */
    public static void loadImage() {
//commented out for spec
//        System.out.println("Enter the local picture location: ");
//        Scanner scan = new Scanner(System.in);
//        String location = scan.nextLine();
//        if(imageRW.load(location)) {
//            System.out.println("Image loaded successfully!");
//        } else {
//            System.out.println("Image location invalid. Try again: ");
//            loadImage();
//        }
        imageRW.load(loadSave[0]);
    }

}
