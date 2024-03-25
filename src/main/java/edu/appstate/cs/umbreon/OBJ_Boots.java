package edu.appstate.cs.umbreon;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

// import src.main.GamePanel;

/**
 * Represents a pair of boots object in the game.
 */
public class OBJ_Boots extends SuperObject {

    GamePanel gp;

    /**
     * Constructs a pair of boots object.
     * 
     * @param gp The GamePanel instance.
     */
    public OBJ_Boots(GamePanel gp) {
        this.gp = gp;
        name = "Boots"; // Set the name of the object
        try {
            File bootsFile = new File("res/objects/boots.png"); // Load the boots image file
            image = ImageIO.read(bootsFile); // Read the image
            // Scale the image to the tile size
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        } catch (IOException e) {
            e.printStackTrace(); // Print error if image loading fails
        }
    }
}
