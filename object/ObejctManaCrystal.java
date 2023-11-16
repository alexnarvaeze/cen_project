package object;

import entity.Entity;
import main.GamePanel;

public class ObejctManaCrystal extends Entity{

    GamePanel gp;

    public ObejctManaCrystal(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "Mana Crystal";
        image = setup("objects/manacrystal", gp.tileSize, gp.tileSize);
        image2 = setup("objects/manacrystalempty", gp.tileSize, gp.tileSize);
    }
    
}
