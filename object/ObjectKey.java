package object;
import java.io.IOException;
import javax.imageio.ImageIO;

import main.GamePanel;
public class ObjectKey extends SuperObject {
    GamePanel gp;

    public ObjectKey(GamePanel gp) {
        this.gp = gp;
        
        name = "Key";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/key.png"));
            uTools.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch(IOException e) {
            e.printStackTrace(); 
        }
    }
}