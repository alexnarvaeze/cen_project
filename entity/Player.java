package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.GamePanel;
import main.KeyHandler;
import object.ObjectFireball;
import object.ObjectKey;
import object.ObjectRock;
import object.ObjectShieldWood;
import object.ObjectSwordNormal;

public class Player extends Entity {
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public boolean attackCancel = false;
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int maxInventorySize = 20;

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

        // attackArea.width = 36;
        // attackArea.height = 36;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
        setItems();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        // worldX = gp.tileSize * 10;
        // worldY = gp.tileSize * 13;
        speed = 4;
        direction = "down";

        // Player Status
        level = 1;
        maxLife = 6; // a half heart is 1 life, so 6 lives is 3 hearts
        life = maxLife;
        maxMana = 4;
        mana = maxMana;
        strength = 1; // more strength = more damage given
        dexterity = 1; // more dexterity = less damage taken
        exp = 0;
        nextLevelExp = 5;
        coin = 0;
        currentWeapon = new ObjectSwordNormal(gp);
        currentShield = new ObjectShieldWood(gp);
        projectile = new ObjectFireball(gp);
        attack = getAttack(); // total attack value is decided by strength * weapon
        defense = getDefense(); // total defense value is decided by dexterity * shield
    }

    public void setDefaultPositions() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        direction = "down";

    }

    public void restoreLifeAndMana() {

        life = maxLife;
        mana = maxMana;
        invincible = false;
    }

    public void setItems() {
        inventory.clear();
        inventory.add(currentWeapon);
        inventory.add(currentShield);
        inventory.add(new ObjectKey(gp));
    }

    public int getAttack() {
        attackArea = currentWeapon.attackArea;
        return attack = strength * currentWeapon.attackValue;
    }

    public int getDefense() {
        return defense = dexterity * currentShield.defenseValue;
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
        if (currentWeapon.type == typeSword) {
            attackUp1 = setup("player/player_up_attack1", gp.tileSize, gp.tileSize * 2);
            attackUp2 = setup("player/player_up_attack2", gp.tileSize, gp.tileSize * 2);
            attackDown1 = setup("player/player_down_attack1", gp.tileSize, gp.tileSize * 2);
            attackDown2 = setup("player/player_down_attack2", gp.tileSize, gp.tileSize * 2);
            attackRight1 = setup("player/player_right_attack1", gp.tileSize * 2, gp.tileSize);
            attackRight2 = setup("player/player_right_attack2", gp.tileSize * 2, gp.tileSize);
            attackLeft1 = setup("player/player_left_attack1", gp.tileSize * 2, gp.tileSize);
            attackLeft2 = setup("player/player_left_attack2", gp.tileSize * 2, gp.tileSize);
        }
        if (currentWeapon.type == typeAxe) {
            attackUp1 = setup("player/player_axe_up1", gp.tileSize, gp.tileSize * 2);
            attackUp2 = setup("player/player_axe_up2", gp.tileSize, gp.tileSize * 2);
            attackDown1 = setup("player/player_axe_down1", gp.tileSize, gp.tileSize * 2);
            attackDown2 = setup("player/player_axe_down2", gp.tileSize, gp.tileSize * 2);
            attackRight1 = setup("player/player_axe_right1", gp.tileSize * 2, gp.tileSize);
            attackRight2 = setup("player/player_axe_right2", gp.tileSize * 2, gp.tileSize);
            attackLeft1 = setup("player/player_axe_left1", gp.tileSize * 2, gp.tileSize);
            attackLeft2 = setup("player/player_axe_left2", gp.tileSize * 2, gp.tileSize);
        }
    }

    public void update() {

        if (attacking == true) {
            attacking();
        } else if (keyH.upPress == true || keyH.downPress == true || keyH.leftPress == true || keyH.rightPress == true
                || keyH.enterPressed == true) {
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

            if (keyH.enterPressed == true && attackCancel == false) {
                gp.soundEffect.play(7, false);
                attacking = true;
                spriteCounter = 0;
            }

            attackCancel = false;

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

        if (gp.keyH.shotKeyPressed == true && projectile.alive == false
                && shotAvailableCounter == 30 && projectile.haveResource(this) == true) {
            // set default coordinates, direction and user
            projectile.set(worldX, worldY, direction, true, this);

            // substract the cost
            projectile.substractResource(this);

            // add it to the list
            gp.projectileList.add(projectile);
            shotAvailableCounter = 0;
        }

        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }

        if (shotAvailableCounter < 30) {
            shotAvailableCounter++;
        }
        if(life > maxLife) {
            life = maxLife;
        }
        if(mana > maxMana) {
            mana = maxMana;
        }
        if (life <= 0) {
            gp.gameState = gp.gameOverState;
            gp.ui.commandNum = -1;
            gp.soundEffect.play(10, false);
        }
    }

    public void attacking() {
        spriteCounter++;

        if (spriteCounter <= 5) {
            spriteNum = 1;
        }
        if (spriteCounter > 5 && spriteCounter <= 25) {
            spriteNum = 2;

            // save current world position
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            // adjust players worldx/y for attackArea
            switch (direction) {
                case "up":
                    worldY -= attackArea.height;
                    break;
                case "down":
                    worldY += attackArea.height;
                    break;
                case "right":
                    worldX += attackArea.width;
                    break;
                case "left":
                    worldX -= attackArea.width;
                    break;
            }

            // attackArea becomes solidArea
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;
            // Check mosnter collision
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            damageMonster(monsterIndex, attack);

            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }
        if (spriteCounter > 25) {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }

    public void pickUpObj(int i) {
        if (i != 999) {
            String text;

            if (inventory.size() != maxInventorySize) {
                inventory.add(gp.obj[i]);
                gp.soundEffect.play(1, false);
                text = "Obtained a " + gp.obj[i].name + ".";
            } else {
                text = "Your inventory is full, squire!";
            }
            gp.ui.addMessage(text);
            gp.obj[i] = null;
        }
    }

    public void interactNPC(int i) {
        if (gp.keyH.enterPressed) {
            if (i != 999) {
                attackCancel = true;
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
        }
    }

    public void contactMonster(int i) {
        if (i != 999) {
            if (invincible == false && gp.monster[i].dying == false) {
                gp.soundEffect.play(6, false);
                int damage = gp.monster[i].attack - defense;
                if (damage < 0) {
                    damage = 0;
                }
                life -= damage;
                invincible = true;
            }

        }
    }

    public void damageMonster(int i, int attack) {
        if (i != 999) {
            if (gp.monster[i].invincible == false) {
                gp.soundEffect.play(5, false);
                int damage = attack - gp.monster[i].defense;
                if (damage < 0)
                    damage = 0;
                gp.monster[i].life -= damage;
                gp.ui.addMessage(damage + " damage!");

                gp.monster[i].invincible = true;
                gp.monster[i].damageReaction();

                if (gp.monster[i].life <= 0) {
                    gp.monster[i].dying = true;
                    gp.ui.addMessage("You have slain the " + gp.monster[i].name + ".");
                    gp.ui.addMessage("You have gained " + gp.monster[i].exp + " exp.");
                    exp += gp.monster[i].exp;
                    checkLevelUp();
                }
            }
        }
    }

    public void checkLevelUp() {
        if (exp >= nextLevelExp) {
            level++;
            exp = exp - nextLevelExp;
            nextLevelExp = nextLevelExp * 2;
            maxLife += 2;
            strength++;
            dexterity++;
            attack = getAttack();
            defense = getDefense();

            gp.soundEffect.play(8, false);
            gp.gameState = gp.dialogueState;
            gp.ui.currentDialogue = "You are now level " + level + ".\nYou feel stronger.";
        }
    }

    public void selectItem() {
        int itemIndex = gp.ui.getItemIndexOnSlot();

        if (itemIndex < inventory.size()) {
            Entity selectedItem = inventory.get(itemIndex);

            if (selectedItem.type == typeSword || selectedItem.type == typeAxe) {
                currentWeapon = selectedItem;
                attack = getAttack();
                getPlayerAttackImage();
            }

            if (selectedItem.type == typeShield) {
                currentShield = selectedItem;
                defense = getDefense();
            }

            if (selectedItem.type == typeConsumable) {
                selectedItem.use(this);
                inventory.remove(itemIndex);
            }
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch (direction) {
            case "up":
                if (attacking == false) {
                    if (spriteNum == 1) {
                        image = up1;
                    }
                    if (spriteNum == 2) {
                        image = up2;
                    }
                }
                if (attacking == true) {
                    tempScreenY = screenY - gp.tileSize;
                    if (spriteNum == 1) {
                        image = attackUp1;
                    }
                    if (spriteNum == 2) {
                        image = attackUp2;
                    }
                }
                break;
            case "down":
                if (attacking == false) {
                    if (spriteNum == 1) {
                        image = down1;
                    }
                    if (spriteNum == 2) {
                        image = down2;
                    }
                }
                if (attacking == true) {
                    if (spriteNum == 1) {
                        image = attackDown1;
                    }
                    if (spriteNum == 2) {
                        image = attackDown2;
                    }
                }
                break;
            case "right":
                if (attacking == false) {
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                }
                if (attacking == true) {
                    if (spriteNum == 1) {
                        image = attackRight1;
                    }
                    if (spriteNum == 2) {
                        image = attackRight2;
                    }
                }
                break;
            case "left":
                if (attacking == false) {
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                }
                if (attacking == true) {
                    tempScreenX = screenX - gp.tileSize;
                    if (spriteNum == 1) {
                        image = attackLeft1;
                    }
                    if (spriteNum == 2) {
                        image = attackLeft2;
                    }
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
