package object;

import entity.Entity;
import main.GamePanel;

public class ObjectHeart extends Entity {

    public ObjectHeart(GamePanel gp) {
        super(gp);
        
        name = "Heart";
        image = setup("/objects/fullheart");
        image2 = setup("/objects/halfheart");
        image3 = setup("/objects/emptyheart");
    }
}
