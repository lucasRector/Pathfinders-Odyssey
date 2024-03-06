package src.object;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class OBJ_Key extends SuperObject {

    public OBJ_Key(){
        name = "Key";
        try {
            File keyFile = new File("res/objects/key.png");
            image = ImageIO.read(keyFile);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
