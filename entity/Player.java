package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        attackArea.width = 36;
        attackArea.height = 36;


        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        // worldX = gp.tileSize * 10;
        // worldY = gp.tileSize * 13;
        speed = 4;
        direction = "down";

        // Player Status
        maxLife = 6; // a half heart is 1 life, so 6 lives is 3 hearts
        life = maxLife;
    }

    public void getPlayerImage() {
        up1 = setup("player/player_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("player/player_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("player/player_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("player/player_down_2", gp.tileSize, gp.tileSize);
        right1 = setup("player/player_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("player/player_right_2", gp.tileSize, gp.tileSize);
        left1 = setup("player/player_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("player/player_left_2", gp.tileSize, gp.tileSize);
    }

    public void getPlayerAttackImage() {
        attackUp1 = setup("player/player_up_attack1", gp.tileSize, gp.tileSize*2);
        attackUp2 = setup("player/player_up_attack2", gp.tileSize, gp.tileSize*2);
        attackDown1 = setup("player/player_down_attack1", gp.tileSize, gp.tileSize*2);
        attackDown2 = setup("player/player_down_attack2", gp.tileSize, gp.tileSize*2);
        attackRight1 = setup("player/player_right_attack1", gp.tileSize*2, gp.tileSize);
        attackRight2 = setup("player/player_right_attack2", gp.tileSize*2, gp.tileSize);
        attackLeft1 = setup("player/player_left_attack1", gp.tileSize*2, gp.tileSize);
        attackLeft2 = setup("player/player_left_attack2", gp.tileSize*2, gp.tileSize);
    }

    public void update() {

        if(attacking == true){
            attacking();
        } else if (keyH.upPress == true || keyH.downPress == true || keyH.leftPress == true || keyH.rightPress == true || keyH.enterPressed == true) {
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

            // Check NPC collision
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            // Check monster collision
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);
            // Check event
            gp.eHandler.checkEvent();

            // If collision is false, player can move
            if (collisionOn == false && keyH.enterPressed == false) {
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

            gp.keyH.enterPressed = false;

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
        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void attacking() {
        spriteCounter++;

        if(spriteCounter <= 5){
            spriteNum =1;
        }
        if(spriteCounter > 5 && spriteCounter <= 25){
            spriteNum =2;
            
            // save current world position
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            // adjust players worldx/y for attackArea
            switch(direction) {
                case "up": worldY -= attackArea.height; break;
                case "down": worldY += attackArea.height; break;
                case "right": worldX += attackArea.width; break;
                case "left": worldX -= attackArea.width; break;
            }

            // attackArea becomes solidArea
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;
            // Check mosnter collision
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            damageMonster(monsterIndex);

            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }
        if(spriteCounter > 25){
            spriteNum =1;
            spriteCounter = 0;
            attacking = false;
        }
    }

    public void pickUpObj(int i) {
        if (i != 999) {

        }
    }

    public void interactNPC(int i) {
        if(gp.keyH.enterPressed) {
            if(i != 999) {
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            } else {
                attacking = true;
            }
        }
    }

    public void contactMonster(int i) {
        if (i != 999) {
            if (invincible == false) {
                life -= 1;
                invincible = true;
            }

        }
    }

    public void damageMonster(int i) {
        if(i != 999) {
            if(gp.monster[i].invincible == false){
                gp.monster[i].life--;
                gp.monster[i].invincible = true;

                if(gp.monster[i].life <= 0){
                    gp.monster[i] = null;
                }
            }
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch (direction) {
            case "up":
            if(attacking == false){
                if (spriteNum == 1) {image = up1;}
                if (spriteNum == 2) {image = up2;}
            }
            if(attacking == true){
                tempScreenY = screenY - gp.tileSize;
                if (spriteNum == 1) {image = attackUp1;}
                if (spriteNum == 2) {image = attackUp2;}
            }
            break;
            case "down":
                if(attacking == false){
                if (spriteNum == 1) {image = down1;}
                if (spriteNum == 2) {image = down2;}
            }
            if(attacking == true){
                if (spriteNum == 1) {image = attackDown1;}
                if (spriteNum == 2) {image = attackDown2;}
            }
            break;
            case "right":
                if(attacking == false){
                if (spriteNum == 1) {image = right1;}
                if (spriteNum == 2) {image = right2;}
            }
            if(attacking == true){
                if (spriteNum == 1) {image = attackRight1;}
                if (spriteNum == 2) {image = attackRight2;}
            }
            break;
            case "left":
                if(attacking == false){
                if (spriteNum == 1) {image = left1;}
                if (spriteNum == 2) {image = left2;}
            }
            if(attacking == true){
                tempScreenX = screenX - gp.tileSize;
                if (spriteNum == 1) {image = attackLeft1;}
                if (spriteNum == 2) {image = attackLeft2;}
            }
            break;
        }
        if (invincible == true) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }

        g2.drawImage(image, tempScreenX, tempScreenY, null);

        // reset alpha
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        // debug
        // g2.setFont(new Font("Arial", Font.PLAIN, 26));
        // g2.setColor(Color.white);
        // g2.drawString("Invincible:" + invincibleCounter, 10, 400);
    }

}
