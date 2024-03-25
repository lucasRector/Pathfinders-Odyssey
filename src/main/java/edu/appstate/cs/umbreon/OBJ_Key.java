package edu.appstate.cs.umbreon;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

// import src.main.GamePanel;

/**
 * Represents a key object in the game.
 */
public class OBJ_Key extends SuperObject {

    GamePanel gp;

    /**
     * Constructs a key object.
     * 
     * @param gp The GamePanel instance.
     */
    public OBJ_Key(GamePanel gp) {
        this.gp = gp;
        name = "Key"; // Set the name of the object
        try {
            File keyFile = new File("res/objects/key.png"); // Load the key image file
            image = ImageIO.read(keyFile); // Read the image
            // Scale the image to the tile size
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
            
        } catch (IOException e) {
            e.printStackTrace(); // Print error if image loading fails
        }
    }
}
