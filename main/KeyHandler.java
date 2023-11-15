package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPress, downPress, leftPress, rightPress, enterPressed;
    GamePanel gp;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        // title state
        if(gp.gameState == gp.titleState) {
            if (code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 2;
                }
            }
            if (code == KeyEvent.VK_S) {
                downPress = true;
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 2) {
                    gp.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                if(gp.ui.commandNum == 0) {
                    gp.gameState = gp.playState;
                    // should start music too
                }
                if(gp.ui.commandNum == 1) {
                    // add later
                }
                if(gp.ui.commandNum == 2) {
                    System.exit(0);
                }
            }

        }
        
        // play state
        else if(gp.gameState == gp.playState){
            if (code == KeyEvent.VK_W) {
                upPress = true;
            }
            if (code == KeyEvent.VK_S) {
                downPress = true;
            }
            if (code == KeyEvent.VK_A) {
                leftPress = true;
            }
            if (code == KeyEvent.VK_D) {
                rightPress = true;
            }
            if (code == KeyEvent.VK_P) {
                gp.gameState = gp.pauseState;
            }
            if (code == KeyEvent.VK_ENTER) {
                enterPressed = true;
            }
        }
        // pause state
        else if(gp.gameState == gp.pauseState) {
            if(code == KeyEvent.VK_P) {
                gp.gameState = gp.playState;
            }
        }
        // dialogue state
        if(gp.gameState == gp.dialogueState) {
            if(code == KeyEvent.VK_ENTER) {
                gp.gameState = gp.playState;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPress = false;
        }
        if (code == KeyEvent.VK_S) {
            downPress = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPress = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPress = false;
        }

    }

}
