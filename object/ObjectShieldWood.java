package object;

import entity.Entity;
import main.GamePanel;

public class ObjectShieldWood extends Entity {

    public ObjectShieldWood(GamePanel gp) {
        super(gp);

        type = typeShield;
        name = "Wooden Shield";
        down1 = setup("objects/shield", gp.tileSize, gp.tileSize);
        defenseValue = 1;
        description = "[" + name + "]\nRusty but trusty shield.";
    }
    
}
