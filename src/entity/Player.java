package src.entity;


import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import src.main.GamePanel;
import src.main.KeyHandler;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public int hasKey = 0;



    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle(8, 14, 30, 30);
        solidArea.x = 8;
        solidArea.y = 14;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY =solidArea.y;
        solidArea.width = 30;
        solidArea.height = 30;

        setDefaultValues(); 
        getPlayerImage();

    }

    public void setDefaultValues(){
        worldX = gp.tileSize * 23; 
        worldY =  gp.tileSize * 21;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage(){
        try {
            File boyup1 = new File("res/player/boyup1.png");
            up1 = ImageIO.read(boyup1);
            File boyup2 = new File("res/player/boyup2.png");
            up2 = ImageIO.read(boyup2);
            File boydown1 = new File("res/player/boydown1.png");
            down1 = ImageIO.read(boydown1);
            File boydown2 = new File("res/player/boydown2.png");
            down2 = ImageIO.read(boydown2);
            File boyleft1 = new File("res/player/boyleft1.png");
            left1 = ImageIO.read(boyleft1);
            File boyleft2 = new File("res/player/boyleft2.png");
            left2 = ImageIO.read(boyleft2);
            File boyright1 = new File("res/player/boyright1.png");
            right1 = ImageIO.read(boyright1);
            File boyright2 = new File("res/player/boyright2.png");
            right2 = ImageIO.read(boyright2);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(){
        if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true){
            if(keyH.upPressed == true){
                direction = "up";
                
            }
            else if(keyH.downPressed == true){
               direction = "down";
               
            }
            else if(keyH.leftPressed == true){
                direction = "left";
                
            }
            else if(keyH.rightPressed == true){
                direction = "right";
                
            }

            collisionOn = false;
            gp.cChecker.checkTile(this);

            int objIndex = gp.cChecker.checkObject(this, true);
            pickupObject(objIndex);

            if(collisionOn == false){
                switch (direction) {
                    case "up":
                    worldY -= speed;    
                        break;
                
                    case "down":
                    worldY += speed;
                        break;

                    case "left":
                    worldX -= speed;
                        break;

                    case "right":
                    worldX += speed;
                        break;

                }
            }

            spriteCounter++;
            if(spriteCounter > 12){
                if(spriteNum == 1){
                    spriteNum = 2;
                }
                else if(spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
        }

            
        }
    }

    public void pickupObject(int i){
        if(i != 999){

            String objectName = gp.obj[i].name;

            switch (objectName) {
                case "Key":
                    gp.playSE(1);
                    hasKey++;
                    gp.obj[i] = null;
                    gp.ui.showMessage("You got a key:)");
                    break;
                case "Door":
                    if(hasKey > 0){
                        gp.playSE(3);
                        gp.obj[i] = null;
                        hasKey--;
                        gp.ui.showMessage("You opened the door");
                    }
                    else{
                        gp.ui.showMessage("You need a key:(");

                    }
                    break;
                case "Boots":
                gp.ui.showMessage("Speed Up!!!");
                    gp.playSE(i);
                    speed += 2;
                    gp.obj[i] = null;
                        break;
                case "Chest":
                    gp.ui.gameFinished = true;
                    gp.stopMusic();
                    gp.playSE(4);
                    break;
            
               
            }
        }
    }

    public void draw(Graphics2D g2){

        BufferedImage image = null;
        switch(direction){
            case "up":
            if(spriteNum == 1){
                image = up1;
            }
            if(spriteNum == 2){
                image = up2;
            }
            break;
            case "down":
            if(spriteNum == 1){
                image = down1;
            }
            if(spriteNum == 2){
                image = down2;
            }
            break;
            case "left":
            if(spriteNum == 1){
                image = left1;
            }
            if(spriteNum == 2){
                image = left2;
            }
            break;
            case "right":
            if(spriteNum == 1){
                image = right1;
            }
            if(spriteNum == 2){
                image = right2;
            }
            break;

        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }


}
