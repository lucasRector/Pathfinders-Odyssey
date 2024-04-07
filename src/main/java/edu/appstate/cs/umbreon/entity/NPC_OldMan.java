package edu.appstate.cs.umbreon.entity;

import java.util.Random;

import edu.appstate.cs.umbreon.main.GamePanel;
import edu.appstate.cs.umbreon.main.Main;


/**
 * Represents an NPC entity in the game.
 * This class specifically represents an old man NPC.
 */
public class NPC_OldMan extends Entity {
    
    /**
     * Constructs an NPC_OldMan object.
     * 
     * @param gp The GamePanel instance.
     */
    public NPC_OldMan(GamePanel gp) {
        super(gp); // Call the constructor of the superclass
        setDialogue();
        direction = "down"; // Set initial direction of the old man
        speed = 1; // Set movement speed of the old man
        getImage(); // Load images for animation
    }

    /**
     * Loads images for the old man's animation.
     */
    public void getImage() {
        // Load images for different directions of movement
        up1 = setup(Main.BUILDDIR + "npc/oldman_up_1");
        up2 = setup(Main.BUILDDIR + "npc/oldman_up_2");
        down1 = setup(Main.BUILDDIR + "npc/oldman_down_1");
        down2 = setup(Main.BUILDDIR + "npc/oldman_down_2");
        left1 = setup(Main.BUILDDIR + "npc/oldman_left_1");
        left2 = setup(Main.BUILDDIR + "npc/oldman_left_2");
        right1 = setup(Main.BUILDDIR + "npc/oldman_right_1");
        right2 = setup(Main.BUILDDIR + "npc/oldman_right_2");
    }

    /**
     * Sets the action of the old man.
     * This method determines the movement direction of the old man.
     */
    public void setAction() {
        actionLockCounter++; // Increment action lock counter

        // Execute action if action lock counter reaches a certain value
        if (actionLockCounter == 120) {
            Random random = new Random(); // Create a Random object
            int i = random.nextInt(100) + 1; // Generate a random number between 1 and 100
            // Determine movement direction based on the random number
            if (i <= 25) {
                direction = "up";
            } else if (i <= 50) {
                direction = "down";
            } else if (i <= 75) {
                direction = "left";
            } else {
                direction = "right";
            }
            actionLockCounter = 0; // Reset action lock counter
        }
    }

    public void setDialogue(){
        dialogues[0] = "Hello! Welcome to Lil Saint John \nIsland";
        dialogues[1] = "Fill Some Story line here";
        dialogues[2] = "Fill Some Story line here";
        dialogues[3] = "Fill Some Story line here";

    
    
    }


    public void speak(){
        super.speak();
    }
}
