package edu.appstate.cs.umbreon.tile;
//Imports
import java.awt.Graphics2D;
import java.io.File;
import java.util.HashMap;

import javax.imageio.ImageIO;

import edu.appstate.cs.umbreon.main.UtilityTool;
import edu.appstate.cs.umbreon.tile.mapgen.MapGen;
import edu.appstate.cs.umbreon.main.GamePanel;
import edu.appstate.cs.umbreon.main.Main;



// import src.main.GamePanel;
// import src.main.UtilityTool;

/**
 * Manages tiles in the game world.
 */
public class TileManager {
    MapGen mg;
    GamePanel gp; // Reference to the game panel
    public Tile[] tile; // Array to hold tile objects
    public int mapTileNum[][]; // Array to hold tile numbers for the map
    public HashMap<String, Integer> nameMap;
 
    /**
     * Constructor for TileManager class.
     * 
     * @param gp The GamePanel instance.
     */
    public TileManager(GamePanel gp) {
        
        this.nameMap = new HashMap<String, Integer>();
        this.mg = new MapGen(gp);
        this.gp = gp;
        tile = new Tile[50]; // Initialize tile array with a size of 50
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow]; // Initialize mapTileNum array with dimensions from GamePanel
        getTileImage();
        loadMap(Main.BUILDDIR + "maps/worldV2.txt"); // Load map from file
         // Load tile images
    }

    /**
     * Loads the images for each tile type.
     */
    public void getTileImage() {
        //  Placeholder to ensure double digit map creation
        for (int i = 0; i < 10; i++) {
            setup(i, "grass00", false); // Set up placeholder tiles
        }
        

        // Set up various tile types (grass, water, road, earth, wall, tree)
        
        setup(11, "grass01", false);
        setup(12, "water00", true);
        setup(13, "water01", true);
        setup(14, "water02", true);
        setup(15, "water03", true);
        setup(16, "water04", true);
        setup(17, "water05", true);
        setup(18, "water06", true);
        setup(19, "water07", true);
        setup(20, "water08", true);
        setup(21, "water09", true);
        setup(22, "water10", true);
        setup(23, "water11", true);
        setup(24, "water12", true);
        setup(25, "water13", true);
        setup(26, "road00", false);
        setup(27, "road01", false);
        setup(28, "road02", false); // down
        setup(29, "road03", false);
        setup(30, "road04", false); // right
        setup(31, "road05", false); // left
        setup(32, "road06", false);
        setup(33, "road07", false);
        setup(34, "road08", false);
        setup(35, "road09", false);
        setup(36, "road10", false);
        setup(37, "road11", false);
        setup(38, "road12", false);
        setup(39, "earth", false);
        setup(40, "wall", true);
        setup(41, "tree", true);
        setup(42, "floor01", false);
        
    }

    /**
     * Set up a tile with specified index, image path, and collision status.
     * 
     * @param index     The index of the tile in the tile array.
     * @param imagePath The path to the image file for the tile.
     * @param collision Whether the tile has collision or not.
     */
    public void setup(int index, String imagePath, boolean collision) {
        UtilityTool uTool = new UtilityTool(); // Utility tool for scaling images
        try {
            tile[index] = new Tile(); // Create new Tile object
            File file = new File(Main.BUILDDIR + "tiles/" + imagePath + ".png"); // Get file for tile image
            tile[index].image = ImageIO.read(file); // Read image file
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize); // Scale image
            tile[index].collision = collision; // Set collision status for the tile
            nameMap.put(imagePath, index);
        } catch (Exception e) {
            e.printStackTrace(); // Print error if image loading fails
        }

    }




    /**
     * Loads the map layout from a text file.
     * 
     * @param filePath The path to the map file.
     */
    public void loadMap(String filePath) {
        mg.generate(mapTileNum);
  
        }
    

    /**
     * Draws the tiles onto the graphics context.
     * 
     * @param g2 The Graphics2D context to draw on.
     */
    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        // Iterate through each tile in the map
        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = mapTileNum[worldCol][worldRow]; // Get tile number
            int worldX = worldCol * gp.tileSize; // Calculate world X position
            int worldY = worldRow * gp.tileSize; // Calculate world Y position
            int screenX = worldX - gp.player.worldX + gp.player.screenX; // Calculate screen X position
            int screenY = worldY - gp.player.worldY + gp.player.screenY; // Calculate screen Y position

            // Check if tile is within the player's view
            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX
                    && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
                    && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY
                    && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

                // Draw the tile on the screen
                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }
            worldCol++;
            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
