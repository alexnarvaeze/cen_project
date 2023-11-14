package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.IOException;
import java.io.InputStream;

public class UserInterface {
    GamePanel gp;
    Graphics2D g2;
    Font Vengeance;
    // BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;

    public UserInterface(GamePanel gp) {
        this.gp = gp;

        try {
            InputStream is = getClass().getResourceAsStream("/res/font/Vengeance.ttf");
            Vengeance = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // ObjectKey key = new ObjectKey(gp);
        // keyImage = key.image;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(Vengeance);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.white);

        // title state
        if(gp.gameState == gp.titleState) {
            drawTitleScreen();
        }
        
        // play state
        if (gp.gameState == gp.playState) {
            // do playstate stuff later
        }

        // pause state
        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }

        // dialogue state
        if(gp.gameState == gp.dialogueState) {
            drawDialogueScreen();
        }
    }

    public void drawTitleScreen() {

        g2.setColor(new Color(0,0,0));
        g2.fillRect(0,0, gp.screenWidth, gp.screenHeight);

        // set title name
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,80F));
        String text = "Big Game";
        int x = getCenteredText(text);
        int y = gp.tileSize*3;

        // shadow
        g2.setColor(Color.gray);
        g2.drawString(text, x+3, y+3);
        // draw title
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        // player icon
        x = gp.screenWidth/2 - (gp.tileSize*2/2);
        y += gp.tileSize*2;
        g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);

        // menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));

        text = "NEW GAME";
        x = getCenteredText(text);
        y += gp.tileSize*3.5;
        g2.drawString(text, x, y);
        if(commandNum == 0) {
            g2.drawString(">", x-gp.tileSize, y);
        }
    
        text = "LOAD GAME";
        x = getCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum == 1) {
            g2.drawString(">", x-gp.tileSize, y);
        }

        text = "QUIT";
        x = getCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum == 2) {
            g2.drawString(">", x-gp.tileSize, y);
        }
    }

    public void drawPauseScreen() {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        int x = getCenteredText(text);
        int y = gp.screenHeight / 2;

        g2.drawString(text, x, y);
    }

    public void drawDialogueScreen() {

        // draw dialogue window
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize*4);
        int height = gp.tileSize*4;

        drawSubWindow(x, y, width, height);
    }

    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(0,0,0,200);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255,255,255,200);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
        x += gp.tileSize;
        y += gp.tileSize;

        for(String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
    }

    public int getCenteredText(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;

        return x;
    }
}
