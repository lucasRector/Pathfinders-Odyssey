package src.object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import src.main.GamePanel;

/**
 * Represents a door object in the game.
 */
public class OBJ_Door extends SuperObject {
    
    GamePanel gp;

    /**
     * Constructs a door object.
     * 
     * @param gp The GamePanel instance.
     */
    public OBJ_Door(GamePanel gp) {
        this.gp = gp;
        name = "Door"; // Set the name of the object
        try {
            File doorFile = new File("res/objects/door.png"); // Load the door image file
            image = ImageIO.read(doorFile); // Read the image
            // Scale the image to the tile size
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        } catch (IOException e) {
            e.printStackTrace(); // Print error if image loading fails
        }
        collision = true; // Set collision to true for the door
    }
}
