package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class ObjectKey extends SuperObject{
    public ObjectKey() {
        
        name = "Key";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
        } catch(IOException e) {
            e.printStackTrace(); 
        }
    }
}
