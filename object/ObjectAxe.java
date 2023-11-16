package object;

import entity.Entity;
import main.GamePanel;

public class ObjectAxe extends Entity {

    public ObjectAxe(GamePanel gp) {
        super(gp);
        
        type = typeAxe;
        name = "Woodcutter's Axe";
        down1 = setup("objects/axe", gp.tileSize, gp.tileSize);
        attackValue = 2;
        attackArea.width = 30;
        attackArea.height = 30;
        description = "[" + name + "]\nCan be used to chop \ntrees down.";
    }
    
}
