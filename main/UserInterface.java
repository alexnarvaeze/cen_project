package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import object.ObjectKey;

public class UserInterface {
    GamePanel gp;
    Font timesNewRoman_40;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;

    public UserInterface(GamePanel gp) {
        this.gp = gp;
        timesNewRoman_40 = new Font("Times New Roman", Font.PLAIN, 40);
        ObjectKey key = new ObjectKey(gp);
        keyImage = key.image;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        if (gameFinished == true) {
            g2.setFont(timesNewRoman_40);
            g2.setColor(Color.white);

            String text;
            int textLength;
            int x;
            int y;

            text = "You unlock the chest, obtaining armor.";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            x = gp.screenWidth / 2 - textLength / 2;
            y = gp.screenHeight / 2 - (gp.tileSize * 3);

            g2.drawString(text, x, y);
            gp.gameThread = null;
        } else {
            g2.setFont(timesNewRoman_40);
            g2.setColor(Color.white);
            g2.drawImage(keyImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
            g2.drawString("x " + gp.player.hasKey, 74, 65);

            // Message
            if (messageOn == true) {
                g2.setFont(g2.getFont().deriveFont(30f));
                g2.drawString(message, gp.tileSize / 2, gp.tileSize * 5);
                messageCounter++;

                if (messageCounter > 90) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
    }
}
