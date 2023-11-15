package object;

import entity.Entity;
import main.GamePanel;

public class ObjectChest extends Entity {

    public ObjectChest(GamePanel gp) {
        super(gp);

        name = "Chest";
        down1 = setup("/objects/chest", gp.tileSize, gp.tileSize);
    }
}
