package src.object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Door extends SuperObject {
    
        public OBJ_Door(){
        name = "Door";
        try {
            File doorFile = new File("res/objects/door.png");
            image = ImageIO.read(doorFile);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
