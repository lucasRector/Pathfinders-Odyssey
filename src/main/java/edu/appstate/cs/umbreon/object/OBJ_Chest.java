package edu.appstate.cs.umbreon.object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import edu.appstate.cs.umbreon.main.GamePanel;

/**
 * Represents a chest object in the game.
 */
public class OBJ_Chest extends SuperObject {

    GamePanel gp;

    /**
     * Constructs a chest object.
     * 
     * @param gp The GamePanel instance.
     */
    public OBJ_Chest(GamePanel gp) {
        this.gp = gp;
        name = "Chest"; // Set the name of the object
        try {
            File chestFile = new File("res/objects/chest.png"); // Load the chest image file
            image = ImageIO.read(chestFile); // Read the image
            // Scale the image to the tile size
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        } catch (IOException e) {
            e.printStackTrace(); // Print error if image loading fails
        }
    }
}
