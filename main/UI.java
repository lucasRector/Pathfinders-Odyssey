package edu.appstate.cs.umbreon.main;



import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import edu.appstate.cs.umbreon.object.OBJ_Key;

// import src.object.OBJ_Key;

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
    Graphics2D g2;
    public String currentDialogue = "";

    // Add a boolean flag to control whether the game is paused
    private boolean isPaused = false;

    public UI(GamePanel gp) {
        this.gp = gp;
        font_40 = new Font("Arial", Font.PLAIN, 40);
        font_80B = new Font("Arial", Font.BOLD, 80);

        OBJ_Key key = new OBJ_Key(gp);
        keyImage = key.image;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
    
        this.g2 = g2;

        if (gameFinished == true) {
            g2.setFont(font_40);
            g2.setColor(Color.white);

            String text;
            int textLength;
            int x;
            int y;

            text = "You found the treasure!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - textLength / 2;
            y = gp.screenHeight / 2 - gp.tileSize * 3;
            g2.drawString(text, x, y);

            text = "Your time was: " + dFormat.format(playTime) + "!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - textLength / 2;
            y = gp.screenHeight / 2 + gp.tileSize * 4;
            g2.drawString(text, x, y);

            g2.setFont(font_80B);
            g2.setColor(Color.yellow);
            text = "Congratulations!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - textLength / 2;
            y = ((gp.screenHeight / 2) + 25) + gp.tileSize * 2;
            g2.drawString(text, x, y);


            gp.gameThread = null;
        } else {
            g2.setFont(font_40);
            g2.setColor(Color.white);
            g2.drawImage(keyImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
            g2.drawString("= " + gp.player.hasKey, 74, 70);

            // Check if the game is paused
            if (!isPaused) {
                playTime += (double) 1 / 60;
            }

            g2.drawString("Time: " + dFormat.format(playTime), gp.tileSize * 11, 65);

            if (gp.gameState == gp.pauseState) {
                drawPauseScreen(g2);
                isPaused = true; // Pause the game loop
            } else {
                isPaused = false; // Resume the game loop
            }

            if (messageOn == true) {
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, gp.tileSize / 2, gp.tileSize * 6);
                messageCounter ++;

                if (messageCounter > 80){
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
        if (gp.gameState == gp.dialogueState){
            drawDialogueScreen();
            isPaused = false;
        }
    }

    public void drawPauseScreen(Graphics2D g2) {
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        int x = getXForCenteredText(text, g2);
        int y = gp.screenHeight / 2;

        g2.drawString(text, x, y);
    }

    public int getXForCenteredText(String text, Graphics2D g2) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }

    public void drawDialogueScreen(){
        int x = 0;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth;
        int height = gp.tileSize * 4;
        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28));
        x += gp.tileSize;
        y += gp.tileSize;

        for (String line : currentDialogue.split("\n")){
            g2.drawString(line, x, y);
            y += 40;

        }
    }

    public void drawSubWindow(int x, int y, int width, int height){
        Color c = new Color(0, 0, 0); // add 4th number for opacity
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }

}


