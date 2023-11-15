package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class ObjectHeart extends SuperObject {
    GamePanel gp;

    public ObjectHeart(GamePanel gp) {
        this.gp = gp;
        
        name = "Heart";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/fullheart.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/res/objects/halfheart.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/res/objects/emptyheart.png"));
            image = uTools.scaleImage(image, gp.tileSize, gp.tileSize);
            image2 = uTools.scaleImage(image2, gp.tileSize, gp.tileSize);
            image3 = uTools.scaleImage(image3, gp.tileSize, gp.tileSize);
        } catch(IOException e) {
            e.printStackTrace(); 
        }
    }
}
