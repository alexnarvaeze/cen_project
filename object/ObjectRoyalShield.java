package object;

import entity.Entity;
import main.GamePanel;

public class ObjectRoyalShield extends Entity {

    public ObjectRoyalShield(GamePanel gp) {
        super(gp);

        type = typeShield;
        name = "Royal Shield";
        down1 = setup("objects/royalshield", gp.tileSize, gp.tileSize);
        defenseValue = 2;
        description = "[" + name + "]\nA shield only for the \nhonorable.";
    }
    
}
