package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.ObjectRock;

public class MON_GreenSlime extends Entity {

    GamePanel gp;

    public MON_GreenSlime(GamePanel gp) {
        super(gp);
        this.gp = gp;
        
        type = typeMonster;
        name = "Green Slime";
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 4;
        life = maxLife;
        attack = 2;
        defense = 0;
        exp = 2;
        projectile = new ObjectRock(gp);

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        getImage();
    }

    public void getImage() {

        up1 = setup("monster/slime_1", gp.tileSize, gp.tileSize);
        up2 = setup("monster/slime_2", gp.tileSize, gp.tileSize);
        down1 = setup("monster/slime_1", gp.tileSize, gp.tileSize);
        down2 = setup("monster/slime_2", gp.tileSize, gp.tileSize);
        left1 = setup("monster/slime_1", gp.tileSize, gp.tileSize);
        left2 = setup("monster/slime_2", gp.tileSize, gp.tileSize);
        right1 = setup("monster/slime_1", gp.tileSize, gp.tileSize);
        right2 = setup("monster/slime_2", gp.tileSize, gp.tileSize);

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

        // monster rock throw
        int i = new Random().nextInt(100)+1;
        if(i > 99 && projectile.alive == false && shotAvailableCounter == 30) {
            projectile.set(worldX, worldY, direction, true, this);

            for(int ii = 0; ii < gp.projectile[1].length; ii++) {
                if(gp.projectile[gp.currentMap][ii] == null) {
                    gp.projectile[gp.currentMap][ii] = projectile;
                    break;
                }
            }

            shotAvailableCounter = 0;
        }
    }

    public void damageReaction() {
        actionCounter = 0;
        direction = gp.player.direction;
    }
}
