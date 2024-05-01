package edu.appstate.cs.umbreon.main;

import javax.swing.JFrame;

/**
 * The main class responsible for starting the game.
 */
public class Main {
    /**
     * The main method that starts the game.
     * 
     * @param args Command-line arguments (not used).
     */
    public static String BUILDDIR = "./target/classes/";
    public static void main(String[] dargs) {
        //System.out.println("Other Dir: " + System.getProperty("outputDirectory"));
        //System.out.println("User dir: " + System.getProperty("user.dir"));
        // Create a new JFrame window
        JFrame window = new JFrame();
        // Set the default close operation to exit on close
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Make the window non-resizable
        window.setResizable(true);
        // Set the title of the window
        window.setTitle("Pathfinder’s Odyssey");

        // Create a new instance of GamePanel
        GamePanel gamePanel = new GamePanel();
        // Add the game panel to the window
        window.add(gamePanel);
        // Pack the components in the window
        window.pack();
        // Set the location of the window
        window.setLocation(300, 100);
        // Make the window visible
        window.setVisible(true);

        // Setup the game
        gamePanel.setupGame();
        // Start the game thread
        gamePanel.startGameThread();
    }
}
