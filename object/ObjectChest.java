package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class ObjectChest extends SuperObject {
    GamePanel gp;

    public ObjectChest(GamePanel gp) {
        this.gp = gp;

        name = "Chest";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/chest.png"));
            uTools.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch(IOException e) {
            e.printStackTrace(); 
        }
    }
}
