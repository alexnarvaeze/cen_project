package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class ObjectBoots extends SuperObject {
    GamePanel gp;

    public ObjectBoots(GamePanel gp) {
        this.gp = gp;
        
        name = "Boots";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/boots.png"));
            uTools.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch(IOException e) {
            e.printStackTrace(); 
        }
    }
}
