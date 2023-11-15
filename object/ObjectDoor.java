package object;

import entity.Entity;
import main.GamePanel;

public class ObjectDoor extends Entity {

    public ObjectDoor(GamePanel gp) {
        super(gp);

        name = "Door";
        down1 = setup("/objects/door");
        collision = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
