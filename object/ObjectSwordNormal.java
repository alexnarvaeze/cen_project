package object;

import entity.Entity;
import main.GamePanel;

public class ObjectSwordNormal extends Entity {

    public ObjectSwordNormal(GamePanel gp) {
        super(gp);

        type = typeSword;
        name = "Normal Sword";
        down1 = setup("objects/sword", gp.tileSize, gp.tileSize);
        attackValue = 1;
        attackArea.width = 36;
        attackArea.height = 36;
        description = "[" + name + "]\nYour trusty sword.";
    }
    
}
