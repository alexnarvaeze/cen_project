package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class ObjectDoor extends SuperObject {
    GamePanel gp;

    public ObjectDoor(GamePanel gp) {
        this.gp = gp;

        name = "Door";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/door.png"));
            uTools.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch(IOException e) {
            e.printStackTrace(); 
        }
        collision = true;
    }
}
