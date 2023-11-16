package object;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

public class ObjectRock extends Projectile{

    GamePanel gp;

    public ObjectRock(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "Rock";
        speed = 3;
        maxLife = 50;
        life = maxLife;
        attack = 2;
        useCost = 0;
        alive = false;
        getImage();
    }
    
    public void getImage() {
        up1 = setup("projectile/rock", gp.tileSize, gp.tileSize);
        up2 = setup("projectile/rock", gp.tileSize, gp.tileSize);
        down1 = setup("projectile/rock", gp.tileSize, gp.tileSize);
        down2 = setup("projectile/rock", gp.tileSize, gp.tileSize);
        right1 = setup("projectile/rock", gp.tileSize, gp.tileSize);
        right2 = setup("projectile/rock", gp.tileSize, gp.tileSize);
        left1 = setup("projectile/rock", gp.tileSize, gp.tileSize);
        left2 = setup("projectile/rock", gp.tileSize, gp.tileSize);
    }

    public boolean haveResource(Entity user) {
        boolean haveResource = false;
        if(user.ammo >= useCost) {
            haveResource = true;
        }
        return haveResource;
    }

    public void substractResource(Entity user) {
        user.ammo -= useCost;
    }
}
