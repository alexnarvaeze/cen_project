package object;

import entity.Entity;
import main.GamePanel;
public class ObjectKey extends Entity {

    public ObjectKey(GamePanel gp) {
        super(gp);
        
        name = "Key";
        down1 = setup("/objects/key");
    }
}