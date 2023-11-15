package main.monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;

public class MON_GreenSlime extends Entity {

    GamePanel gp;

    public MON_GreenSlime(GamePanel gp) {
        super(gp);
        this.gp = gp;
        
        type = 2;
        name = "Green Slime";
        speed = 1;
        maxLife = 4;
        life = maxLife;

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
    }

}
