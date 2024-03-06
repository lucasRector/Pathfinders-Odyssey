package src.object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Boots extends SuperObject{

    public OBJ_Boots(){
        name = "Boots";
        try {
            File bootsFile = new File("res/objects/boots.png");
            image = ImageIO.read(bootsFile);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
