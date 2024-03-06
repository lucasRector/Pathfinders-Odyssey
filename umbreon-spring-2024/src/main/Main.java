package src.main;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args){
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Pathfinder’s Odyssey");
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();
        window.setLocation(250, 350);
        window.setVisible(true);
        gamePanel.setupGame();
        gamePanel.startGameThread();




    }




}
