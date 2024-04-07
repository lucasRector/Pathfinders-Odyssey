package edu.appstate.cs.umbreon.object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import edu.appstate.cs.umbreon.main.GamePanel;
import edu.appstate.cs.umbreon.main.Main;


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
        System.out.println("Boots?: " + Main.BUILDDIR + "objects/boots.png");
        try {
            File bootsFile = new File(Main.BUILDDIR + "objects/boots.png"); // Load the boots image file
            image = ImageIO.read(bootsFile); // Read the image
            // Scale the image to the tile size
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        } catch (IOException e) {
            e.printStackTrace(); // Print error if image loading fails
        }
    }
}
