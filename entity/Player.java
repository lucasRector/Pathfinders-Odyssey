package edu.appstate.cs.umbreon.entity;


import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import edu.appstate.cs.umbreon.main.GamePanel;
import edu.appstate.cs.umbreon.main.KeyHandler;
import edu.appstate.cs.umbreon.main.Main;



// import src.main.GamePanel;
// import src.main.KeyHandler;

public class Player extends Entity {
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public int hasKey = 0;



    public Player(GamePanel gp, KeyHandler keyH){
        super(gp);
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
        worldX = gp.worldWidth/2; 
        worldY =  gp.worldHeight/2;
        speed = 10;
        direction = "down";
    }

    public void getPlayerImage(){
      
            up1 = setup(Main.BUILDDIR + "player/boyup1");
            
            up2 = setup(Main.BUILDDIR + "player/boyup2");

            down1 = setup(Main.BUILDDIR + "player/boydown1");

            down2 = setup(Main.BUILDDIR + "player/boydown2");

            left1 = setup(Main.BUILDDIR + "player/boyleft1");

            left2 = setup(Main.BUILDDIR + "player/boyleft2");

            right1 = setup(Main.BUILDDIR + "player/boyright1");

            right2 = setup(Main.BUILDDIR + "player/boyright2");
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

            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);
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
                    gp.playSE(2);
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

    public void interactNPC(int i){
        if(i != 999){
            if(gp.keyH.enterPressed == true){
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }

        }
        gp.keyH.enterPressed = false;
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
        g2.drawImage(image, screenX, screenY, null);
    }


}
