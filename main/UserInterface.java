package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class UserInterface {
    GamePanel gp;
    Graphics2D g2;
    Font timesNewRoman_40;
    // BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;

    public UserInterface(GamePanel gp) {
        this.gp = gp;
        timesNewRoman_40 = new Font("Times New Roman", Font.PLAIN, 40);
        // ObjectKey key = new ObjectKey(gp);
        // keyImage = key.image;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(timesNewRoman_40);
        g2.setColor(Color.white);

        if (gp.gameState == gp.playState) {
            // do playstate stuff later
        }

        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }
    }

    public void drawPauseScreen() {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        int x = getCenteredText(text);
        int y = gp.screenHeight / 2;

        g2.drawString(text, x, y);
    }

    public int getCenteredText(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;

        return x;
    }
}
