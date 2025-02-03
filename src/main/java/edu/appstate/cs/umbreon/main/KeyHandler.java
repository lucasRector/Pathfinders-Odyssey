package edu.appstate.cs.umbreon.main;

import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
    public boolean instructionsScreenDisplayed = false;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getExtendedKeyCode();
        // Title Scren
        if (gp.gameState == gp.titleState) {
            if (code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 2;
                }
            }

            if (code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 2) {
                    gp.ui.commandNum = 0;
                }
            }

        }

        // ran into a bug where you cannot return to the title screen
        // if (instructionsScreenDisplayed && code != KeyEvent.VK_ENTER) {
        // return;
        // }
        if (instructionsScreenDisplayed && code == KeyEvent.VK_ESCAPE) {
            gp.ui.showInstructions = false;
            instructionsScreenDisplayed = false;
        }

        if (gp.gameState == gp.playState) {
            if (code == KeyEvent.VK_W) {
                upPressed = true;
            }

            if (code == KeyEvent.VK_S) {
                downPressed = true;
            }

            if (code == KeyEvent.VK_A) {
                leftPressed = true;
            }

            if (code == KeyEvent.VK_D) {
                rightPressed = true;
            }

            if (code == KeyEvent.VK_P) {
                if (gp.gameState == gp.playState) {
                    gp.gameState = gp.pauseState;
                } else if (gp.gameState == gp.pauseState) {
                    gp.gameState = gp.playState;
                }
            }

            if (code == KeyEvent.VK_ENTER) {
                enterPressed = true;
            }

        }

        else if (gp.gameState == gp.pauseState) {
            if (code == KeyEvent.VK_P) {
                gp.gameState = gp.playState;
            }
        }

        else if (gp.gameState == gp.dialogueState) {
            if (code == KeyEvent.VK_ENTER) {
                gp.gameState = gp.playState;
            }
        }

    }

    public void handleTitleMenuSelection(int code) {
        if (code == KeyEvent.VK_ENTER) {
            if (gp.ui.commandNum == 0) {
                // Start a new game
                gp.gameState = gp.playState;
                gp.playMusic(0);
            } else if (gp.ui.commandNum == 1) {
                // Show instructions
                gp.ui.showInstructions = !gp.ui.showInstructions;
                instructionsScreenDisplayed = gp.ui.showInstructions;
            } else if (gp.ui.commandNum == 2) {
                // Quit the game
                System.exit(0);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        // To access the instructions screen
        if (instructionsScreenDisplayed && code == KeyEvent.VK_ENTER) {
            gp.ui.showInstructions = false;
            instructionsScreenDisplayed = false;
        }

        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }

        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }

        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }

        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }

        if (gp.gameState == gp.titleState) {
            if (gp.ui.showInstructions && code == KeyEvent.VK_ENTER) {
                gp.ui.showInstructions = false;
            }
        } else if (gp.gameState == gp.playState) {
            if (code == KeyEvent.VK_ENTER) {
                enterPressed = true;
            }
        } else if (gp.gameState == gp.pauseState) {
            if (code == KeyEvent.VK_P) {
                gp.gameState = gp.playState;
            }
        } else if (gp.gameState == gp.dialogueState) {
            if (code == KeyEvent.VK_ENTER) {
                gp.gameState = gp.playState;
            }
        }

        // This stops the user from navigating the title screen by accident
        // while reading the instuctions
        if (gp.gameState == gp.titleState) {
            if (gp.keyH.upPressed == true || gp.keyH.downPressed == true) {
                gp.keyH.upPressed = false;
                gp.keyH.downPressed = false;
                gp.ui.commandNum += (gp.keyH.downPressed) ? 1 : -1;
                gp.ui.commandNum = (gp.ui.commandNum < 0) ? 2 : (gp.ui.commandNum > 2) ? 0 : gp.ui.commandNum;
            }
            if (code == KeyEvent.VK_ENTER) {
                handleTitleMenuSelection(code);
            }
        }
    }
}
