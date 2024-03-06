package src.main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import src.object.OBJ_Key;

import java.awt.Color;
import java.awt.Font;


public class UI {
    GamePanel gp;
    Font font_40, font_80B;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp){
        this.gp = gp;
        font_40 = new Font("Arial", Font.PLAIN, 40);
        font_80B = new Font("Arial", Font.BOLD, 80);

        OBJ_Key key = new OBJ_Key();
        keyImage = key.image;
    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2){

        if (gameFinished == true){
            g2.setFont(font_40);
            g2.setColor(Color.white);

            String text;
            int textLength;
            int x;
            int y;

            text = "You found the treasure!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - textLength /2;
            y = gp.screenHeight / 2 - gp.tileSize * 3;
            g2.drawString(text, x, y);

            text = "Your time was: " + dFormat.format(playTime) + "!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - textLength /2;
            y = gp.screenHeight / 2 + gp.tileSize * 4;
            g2.drawString(text, x, y);

            g2.setFont(font_80B);
            g2.setColor(Color.yellow);
            text = "Congratulations!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - textLength /2;
            y = ((gp.screenHeight / 2) + 25) + gp.tileSize * 2;
            g2.drawString(text, x, y);


            gp.gameThread = null;
        }
        else{
            g2.setFont(font_40);
            g2.setColor(Color.white);
            g2.drawImage(keyImage, gp.tileSize /2, gp.tileSize /2, gp.tileSize, gp.tileSize, null);
            g2.drawString("= " + gp.player.hasKey, 74, 70);

            playTime +=(double)1 / 60;
            g2.drawString("Time: " + dFormat.format(playTime), gp.tileSize * 11, 65);
    
            if(messageOn == true){
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, gp.tileSize / 2, gp.tileSize * 6);
                messageCounter ++;
    
                if(messageCounter > 80){
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
    }
    
}
