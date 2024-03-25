package src.tile;

import java.awt.image.BufferedImage;

/**
 * Represents a tile in the game world.
 */
public class Tile {
    /** The image representing the tile. */
    public BufferedImage image;
    
    /** Indicates whether the tile has collision or not. */
    public boolean collision = false;
}
