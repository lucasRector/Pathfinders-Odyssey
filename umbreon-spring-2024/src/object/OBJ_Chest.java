package src.object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Chest extends SuperObject{

        public OBJ_Chest(){
        name = "Chest";
        try {
            File chestFile = new File("res/objects/chest.png");
            image = ImageIO.read(chestFile);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
