package edu.appstate.cs.umbreon.tile.mapgen;

import java.util.ArrayList;
import java.util.Random;
import java.util.Iterator;

import edu.appstate.cs.umbreon.main.GamePanel;
import edu.appstate.cs.umbreon.object.OBJ_Boots;
import edu.appstate.cs.umbreon.object.OBJ_Key;
import edu.appstate.cs.umbreon.object.SuperObject;
import edu.appstate.cs.umbreon.tile.TileManager;

public class MapGen {
    GamePanel gp;
    RoadTile[][] rt;
    ArrayList<SuperObject> objects;
    int objIndex = 10;

    public MapGen(GamePanel gp) {
      this.gp = gp;
      rt = new RoadTile[gp.maxWorldCol / 3][gp.maxWorldRow / 3];
      for (int i = 0; i < rt.length; i++) {
        for (int j = 0; j < rt[i].length; j++) {
            rt[i][j] = new RoadTile();  
        }
      }
      objects = new ArrayList<>();
      objects.add(new OBJ_Boots(gp));
      for (int i = 0; i < 10; i++) {
        objects.add(new OBJ_Key(gp));
      } 
      
      
    }

    public void generate(int[][] tiles){
      for (int i = 0; i < tiles.length ; i++) {
        for (int j = 0; j < tiles[i].length ; j++) {
            tiles[i][j] = 41;
        }
      }
      //this is relfected acroos y=x
      int[][] startingArea = {
        {41, 41, 41},
        { 40, 42, 42, 42, 42, 42, 42,},
        { 40, 40, 40, 42, 42, 42, 42},
        { 42, 42, 42, 42, 42, 42, 42},
        { 42, 42, 42, 42, 42, 42, 42},
        { 42, 42, 42, 42, 42, 42, 42},
        { 42, 42, 42, 42, 42, 42, 42},
        { 42, 42, 42, 42, 42, 42, 42},
        { 42, 42, 42, 42, 42, 42, 42},
        { 42, 42, 42, 42, 42, 42, 42}
      };

      int forkLimit = 6; 
      int middleCol = (gp.maxWorldCol / 3) / 2; 
      int middleRow = (gp.maxWorldRow / 3) / 2; 
      makePath(middleCol - 1, middleRow, "left", forkLimit); // change numbers to change start of path(cell space)
      makePath(middleCol + 1, middleRow, "right", forkLimit);
      makePath(middleCol,     middleRow - 1, "up", forkLimit);
      makePath(middleCol,     middleRow + 1, "down", forkLimit);

      for (int i = 0; i < rt.length; i++) {
        for (int j = 0; j < rt[i].length; j++) {
          if(rt[i][j] != null){
            int[][] roadTiles = rt[i][j].getTiles();
            if (roadTiles != null){
              copyBlock(tiles, roadTiles, i * 3, j * 3);
            }
          }
        }
      }
      copyBlock(tiles, startingArea, gp.maxWorldCol / 2 - 3, gp.maxWorldRow / 2 - 3);
      
      if (objects.size() > 0) {
        System.err.println("WARNING: DID NOT PLACE EVERY OBJECT. RAISE OBJECT CHANCE");
      }
    }

    public static void copyBlock(int[][] dest, int[][] source, int x, int y) {
        for (int i = 0; i < source.length; i++) {
            for (int j = 0; j < source[i].length; j++) {
                dest[i + x][j +y] = source[i][j];
            }
        }
    }

    public void disAllowRoads(int x, int y, int width, int height){
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
              int cellX = (x + i) / 3;
              int cellY = (y + j) / 3;
              rt[cellX][cellY] = null;
            }
        }
    }

    public void makePath(int cellX, int cellY, String direction, int forkLimit){
        float turnChanceIncrease = 6 / 100f; // 6%
        int maxIterations = 37;
        float forkChance = 5 / 100f; // 5%
        float objectChance = 5 / 100f; //5%
        
        int prevX = cellX, prevY = cellY;
        
        int iterations = 0;
        float turnChance = 0;

        Random rng = new Random();
        ArrayList<String> directions = new ArrayList<>();
        while (true) {
          prevX = cellX;
          prevY = cellY;

          if (iterations++ > maxIterations) {
            placeObject(cellX, cellY);
            break;
          }

          directions.clear();
          directions.add(direction);
          if (rng.nextFloat() < .5f) {
            directions.add(turnLeft(direction));
            directions.add(turnRight(direction));
          } else {
            directions.add(turnRight(direction));
            directions.add(turnLeft(direction));
          }

          Iterator<String> iterator = directions.iterator();
          while (iterator.hasNext()) {
            String dir = (String)iterator.next();
            if (!canStep(cellX, cellY, dir) || hasParallelPath(cellX, cellY, dir)) {
              iterator.remove();
            }
          }

          if (directions.size() > 1 && rng.nextFloat() < turnChance) {
            directions.remove(0); // skip what should be the straight option. if it's not, it'll be the other turn and that's fine
          }

          if (directions.size() == 0) {
            placeObject(cellX, cellY);
            break;
          }

          if (!direction.equals(directions.get(0))) {
            turnChance = 0;
            direction = directions.get(0);
            if (directions.size() > 1 && forkLimit > 0) {
              if (rng.nextFloat() < forkChance) {
                makePath(cellX, cellY, directions.get(1), forkLimit - 1);
              }
            }
          } else {
            turnChance += turnChanceIncrease;
          }

          if (direction == "up") {
            cellY--;
            rt[prevX][prevY].up = true;
            rt[cellX][cellY].down = true;

          } else if (direction == "down") {
            cellY++; 
            rt[prevX][prevY].down = true;
            rt[cellX][cellY].up = true;
             
          } else if (direction == "left") {
            cellX--;
            rt[prevX][prevY].left = true;
            rt[cellX][cellY].right = true;

          } else if (direction == "right") {
            cellX++; 
            rt[prevX][prevY].right = true;
            rt[cellX][cellY].left = true; 
  
          }

          if (rng.nextFloat() < objectChance) {
            placeObject(cellX, cellY);
          }
          
        }
    }

    private void placeObject(int x, int y) {
      if (objects.size() > 0) {
        SuperObject key = objects.remove(0);
        gp.obj[objIndex] = key;
        objIndex++;
        key.worldX = (x * 3 + 1) * gp.tileSize;
        key.worldY = (y * 3 + 1) * gp.tileSize;
      }
    }

    private boolean hasParallelPath(int x, int y, String direction) {
      if (direction == "left" || direction == "right") {
        return inRTBounds(x, y + 1) && (rt[x][y + 1].left || rt[x][y + 1].right)
            || inRTBounds(x, y - 1) && (rt[x][y - 1].left || rt[x][y - 1].right);
      }
      else if (direction == "up" || direction == "down") {
        return inRTBounds(x + 1, y) && (rt[x + 1][y].up || rt[x + 1][y].down)
            || inRTBounds(x - 1, y) && (rt[x - 1][y].up || rt[x - 1][y].down);
      }
      return false;
    }

    private boolean canStep(int x, int y, String direction) {
        if (direction == "up") {
          return inRTBounds(x, y - 1);
        } else if (direction == "down") {
          return inRTBounds(x, y + 1);
        } else if (direction == "left") {
          return inRTBounds(x - 1, y);
        } else if (direction == "right") {
          return inRTBounds(x + 1, y);
        }
        return false;
    }

    private boolean inRTBounds(int x, int y) {
      return x >= 0 && x < rt.length && y >= 0 && y < rt[0].length && rt[x][y] != null;
    }

    private String turnLeft(String direction) {
      switch (direction) {
        case "up":
          return "left";
        case "down":
          return "right";
        case "left":
          return "down";
        case "right":
          return "up";
      }
      return null;
    }
    private String turnRight(String direction) {
      switch (direction) {
        case "up":
          return "right";
        case "down":
          return "left";
        case "left":
          return "up";
        case "right":
          return "down";
      }
      return null;
    }
}
