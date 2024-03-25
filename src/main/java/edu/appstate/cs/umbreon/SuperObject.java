package edu.appstate.cs.umbreon;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

// import src.main.GamePanel;
// import src.main.UtilityTool;

/**
 * Represents a superclass for all objects in the game.
 */
public class SuperObject {

    public BufferedImage image; // The image of the object
    public String name; // The name of the object
    public boolean collision = false; // Flag indicating whether the object has collision
    public int worldX, worldY; // Position of the object in the world
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48); // Rectangle representing the solid area of the object
    public int solidAreaDefaultX = 0; // Default X position of the solid area
    public int solidAreaDefaultY = 0; // Default Y position of the solid area
    UtilityTool uTool = new UtilityTool(); // Utility tool for scaling images

    /**
     * Draws the object on the screen.
     * 
     * @param g2 The graphics context.
     * @param gp The GamePanel instance.
     */
    public void draw(Graphics2D g2, GamePanel gp) {
        // Calculate the screen coordinates of the object
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        
        // Draw the object if it is within the visible area of the screen
        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
            worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && 
            worldY + gp.tileSize  > gp.player.worldY - gp.player.screenY && 
            worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
            
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }
}
