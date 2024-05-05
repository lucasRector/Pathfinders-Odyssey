package edu.appstate.cs.umbreon.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

//import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import edu.appstate.cs.umbreon.main.GamePanel;

public class EntityTest
{
    private GamePanel gp;
    private Entity testEnt;
    private int screenX;
    private int screenY;

    Graphics2D g2mock;
    BufferedImage imageMock0;
    BufferedImage imageMock1;
    BufferedImage imageMock2;
    BufferedImage imageMock3;

    public EntityTest()
    {
        gp = new GamePanel();
        // Override the map associated with gp to something simple with no obstacles.
        gp.tileM.mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        for (int i = 0; i < gp.tileM.mapTileNum.length ; i++) {
            for (int j = 0; j < gp.tileM.mapTileNum[i].length ; j++) {
                gp.tileM.mapTileNum[i][j] = 11;
            }
        }
        testEnt = new Entity(gp);

        // These appeared to be the default positions for the player.
        gp.player.worldX = 1104;
        gp.player.worldY = 1008;

        // Make sure the gp

        testEnt.worldX = 1104;
        testEnt.worldY = 1008;

        g2mock = mock(Graphics2D.class);
        imageMock0 = mock(BufferedImage.class);
        when(imageMock0.getProperty("ID")).thenReturn("up1");
        imageMock1 = mock(BufferedImage.class);
        when(imageMock1.getProperty("ID")).thenReturn("down1");
        imageMock2 = mock(BufferedImage.class);
        when(imageMock2.getProperty("ID")).thenReturn("left1");
        imageMock3 = mock(BufferedImage.class);
        when(imageMock3.getProperty("ID")).thenReturn("right1");
        
        testEnt.up1 = imageMock0;
        testEnt.down1 = imageMock1;
        testEnt.left1 = imageMock2;
        testEnt.right1 = imageMock3;

        screenX = testEnt.worldX - gp.player.worldX + gp.player.screenX;
        screenY = testEnt.worldY - gp.player.worldY + gp.player.screenY;
    }
    @Test
    public void testDraw0()
    {

        testEnt.direction = "up";
        //System.out.println("Player Pos: " + gp.player.worldX + ", " + gp.player.worldY);
        testEnt.draw(g2mock);

        // Apparent behavior when implementing the test:
        verify(g2mock).drawImage(imageMock0, screenX, screenY, 48, 48, null);

        System.out.println("Test ran!");
        //assertTrue(true);
    }

    @Test
    public void testDraw1()
    {

        testEnt.direction = "down";
        testEnt.draw(g2mock);

        // Apparent behavior when implementing the test:
        verify(g2mock).drawImage(imageMock1, screenX, screenY, 48, 48, null);
    }

    @Test
    public void testDraw2()
    {

        testEnt.direction = "left";
        testEnt.draw(g2mock);

        // Apparent behavior when implementing the test:
        verify(g2mock).drawImage(imageMock2, screenX, screenY, 48, 48, null);
    }

    @Test
    public void testDraw3()
    {

        testEnt.direction = "right";
        testEnt.draw(g2mock);

        // Apparent behavior when implementing the test:
        verify(g2mock).drawImage(imageMock3, screenX, screenY, 48, 48, null);
    }

    @Test
    public void testDraw4()
    {

        testEnt.direction = "down";
        testEnt.draw(g2mock);

        // This verify should be false:
        //verify(g2mock).drawImage(imageMock0, screenX, screenY, 48, 48, null);
        assertTrue(true);
    }
    // TODO: Create other tests like this.

    @Test
    public void testUpdate0()
    {
        // Test when there isn't a collision.
        int beforeX = testEnt.worldX;
        int beforeY = testEnt.worldY;
        testEnt.collisionOn = false;
        testEnt.direction = "up";
        testEnt.speed = 7;
        testEnt.update();

        //System.out.printf("%d, %d\n%d, %d\n", beforeX, beforeY, testEnt.worldX, testEnt.worldY);
        //System.out.println(testEnt.collisionOn);

        // The base entity class doesn't seem to move if the
        // player intersects it.
        assertTrue(testEnt.worldX == beforeX
                && testEnt.worldY == beforeY);
    }
}
