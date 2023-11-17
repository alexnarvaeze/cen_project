package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.ObjectRock;

public class MON_Bat extends Entity {

    GamePanel gp;

    public MON_Bat(GamePanel gp) {
        super(gp);
        this.gp = gp;
        
        type = typeMonster;
        name = "Bat";
        speed = 7;
        maxLife = 7;
        life = maxLife;
        attack = 2;
        defense = 0;
        exp = 7;
        projectile = new ObjectRock(gp);

        solidArea.x = 3;
        solidArea.y = 15;
        solidArea.width = 42;
        solidArea.height = 21;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        getImage();
    }

    public void getImage() {

        up1 = setup("monster/bat_1", gp.tileSize, gp.tileSize);
        up2 = setup("monster/bat_2", gp.tileSize, gp.tileSize);
        down1 = setup("monster/bat_1", gp.tileSize, gp.tileSize);
        down2 = setup("monster/bat_2", gp.tileSize, gp.tileSize);
        left1 = setup("monster/bat_1", gp.tileSize, gp.tileSize);
        left2 = setup("monster/bat_2", gp.tileSize, gp.tileSize);
        right1 = setup("monster/bat_1", gp.tileSize, gp.tileSize);
        right2 = setup("monster/bat_2", gp.tileSize, gp.tileSize);

    }

    public void setAction() {
        actionCounter++;

        if (actionCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1; // picks number from 1 to 100

            if (i <= 25) {
                direction = "up";
            } else if (i > 25 && i <= 50) {
                direction = "down";
            } else if (i > 50 && i <= 75) {
                direction = "left";
            } else if (i > 75 && i <= 100) {
                direction = "right";
            }

            actionCounter = 0;
            
        }
    }

    public void damageReaction() {
        actionCounter = 0;
        direction = gp.player.direction;
    }
}
