package src.tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


import javax.imageio.ImageIO;

import src.main.GamePanel;




public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow]; 
        loadMap("res/maps/world01.txt");
        getTileImage();
        
    }

    public void getTileImage(){
        System.out.println("Image loading started");
        try {
            tile[0] = new Tile();
            File grassFile = new File("res/tiles/grass.png");
            tile[0].image = ImageIO.read(grassFile);

            tile[1] = new Tile();
            File wallFile = new File("res/tiles/wall.png"); 
            tile[1].image = ImageIO.read(wallFile);
            tile[1].collision = true;

            tile[2] = new Tile();
            File waterFile = new File("res/tiles/water.png"); 
            tile[2].image = ImageIO.read(waterFile);
            tile[2].collision = true;

            tile[3] = new Tile();
            File earthFile = new File("res/tiles/earth.png");
            tile[3].image = ImageIO.read(earthFile);

            tile[4] = new Tile();
            File treeFile = new File("res/tiles/tree.png");
            tile[4].image = ImageIO.read(treeFile);
            tile[4].collision = true;

            tile[5] = new Tile();
            File sandFile = new File("res/tiles/sand.png");
            tile[5].image = ImageIO.read(sandFile);
            

            } catch (IOException e) {
                
            e.printStackTrace();
        }
        System.out.println("Image loading Complete");
    }
        public void loadMap(String filePath){
            try{
                //InputStream is = getClass().getResourceAsStream(filePath);
                //BufferedReader br = new BufferedReader(new InputStreamReader(is));
                BufferedReader br = new BufferedReader(new FileReader(filePath));
                
                int col = 0;
                int row = 0;
                System.out.println(filePath);
                
                while(col < gp.maxWorldCol && row < gp.maxWorldRow){
                    System.out.println("working loop");
                    String line = br.readLine();
                    while(col < gp.maxWorldCol){
                        String numbers[] = line.split(" ");
                        int num = Integer.parseInt(numbers[col]);
                        System.out.println("working loop");
                        mapTileNum[col][row] = num;
                        col++;
                    }
                    if(col == gp.maxWorldCol){
                        col = 0;
                        row++;
                    }
                    
                }
                br.close();

            }catch(Exception e){
                e.printStackTrace();

            }
        }

        public void draw(Graphics2D g2) {

            int worldCol = 0;
            int worldRow = 0; 

            while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){

                int tileNum = mapTileNum[worldCol][worldRow];
                int worldX = worldCol * gp.tileSize;
                int worldY = worldRow * gp.tileSize;
                int screenX = worldX - gp.player.worldX + gp.player.screenX;
                int screenY = worldY - gp.player.worldY + gp.player.screenY;
                
                if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && 
                worldY + gp.tileSize  > gp.player.worldY - gp.player.screenY && 
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
                    
                    g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);

                }
                worldCol++;
                if(worldCol == gp.maxWorldCol){
                    worldCol = 0; 
                    worldRow++;
                }
            }
    }
}
