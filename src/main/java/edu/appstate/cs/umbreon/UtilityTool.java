package edu.appstate.cs.umbreon;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * Utility class providing various helper methods.
 */
public class UtilityTool {
    
    /**
     * Scales the given image to the specified width and height.
     * 
     * @param original The original image to be scaled.
     * @param width The desired width of the scaled image.
     * @param height The desired height of the scaled image.
     * @return The scaled image.
     */
    public BufferedImage scaleImage(BufferedImage original, int width, int height){
        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original, 0, 0, width, height, null); 
        g2.dispose();

        return scaledImage;
    }
}
