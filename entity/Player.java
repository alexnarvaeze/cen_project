package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTools;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public int hasKey = 0;

    public Player(GamePanel gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle();
        solidArea.x = 16;
        solidArea.y = 18;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 14;
        solidArea.height = 16; 

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        up1 = setup("player_up_1");
        up2 = setup("player_up_2");
        down1 = setup("player_down_1");
        down2 = setup("player_down_2");
        right1 = setup("player_right_1");
        right2 = setup("player_right_2");
        left1 = setup("player_left_1");
        left2 = setup("player_left_2");
    }

    public BufferedImage setup(String imageName) {
        UtilityTools uTools = new UtilityTools();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/player/" + imageName + ".png"));
            image = uTools.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public void update() {
        if (keyH.upPress == true || keyH.downPress == true || keyH.leftPress == true || keyH.rightPress == true) {
            if (keyH.upPress == true) {
                direction = "up";
            } else if (keyH.downPress == true) {
                direction = "down";
            } else if (keyH.leftPress == true) {
                direction = "left";
            } else if (keyH.rightPress == true) {
                direction = "right";
            }

            // Check tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // Check object collision
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObj(objIndex);

            // If collision is false, player can move
            if(collisionOn == false) {
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
                
                    default:
                        break;
                }
            }

            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }

    }

    public void pickUpObj(int i) {
        if (i != 999) {
            String objName = gp.obj[i].name;

            switch(objName) {
                case "Key":
                    gp.soundEffect.play(1, false);
                    hasKey++;
                    gp.obj[i] = null;
                    gp.ui.showMessage("You found a key.");
                    break;
                case "Door":
                    if (hasKey > 0) {
                        gp.soundEffect.play(3, false);
                        gp.obj[i] = null;
                        hasKey--;
                        gp.ui.showMessage("The door opened.");
                    } else
                        gp.ui.showMessage("There appears to be a keyhole.");
                    break;
                case "Boots":
                    gp.soundEffect.play(2, false);
                    speed += 1;
                    gp.obj[i] = null;
                    gp.ui.showMessage("Hermes has blessed you.");
                    break;
                case "Chest":
                    gp.ui.gameFinished = true;
                    gp.music.stop();
                    gp.soundEffect.play(4, false);
                    break;
            }
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, null);
    }

}
