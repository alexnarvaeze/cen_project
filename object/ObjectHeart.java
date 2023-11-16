package object;

import entity.Entity;
import main.GamePanel;

public class ObjectHeart extends Entity {

    public ObjectHeart(GamePanel gp) {
        super(gp);
        
        name = "Heart";
        image = setup("objects/fullheart", gp.tileSize, gp.tileSize);
        image2 = setup("objects/halfheart", gp.tileSize, gp.tileSize);
        image3 = setup("objects/emptyheart", gp.tileSize, gp.tileSize);
    }
}
