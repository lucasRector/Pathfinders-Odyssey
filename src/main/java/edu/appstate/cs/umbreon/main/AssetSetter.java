package edu.appstate.cs.umbreon.main;

import edu.appstate.cs.umbreon.entity.NPC_OldMan;
import edu.appstate.cs.umbreon.object.OBJ_Boots;
import edu.appstate.cs.umbreon.object.OBJ_Chest;
import edu.appstate.cs.umbreon.object.OBJ_Door;
import edu.appstate.cs.umbreon.object.OBJ_Key;

// import src.entity.NPC_OldMan;
// import src.object.OBJ_Boots;
// import src.object.OBJ_Chest;
// import src.object.OBJ_Door;
// import src.object.OBJ_Key;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){

        //gp.obj[5] = new OBJ_Door(gp);
       // gp.obj[5].worldX = 12 * gp.tileSize;
        //gp.obj[5].worldY = 23 * gp.tileSize;
    
       // gp.obj[6] = new OBJ_Chest(gp);
        //gp.obj[6].worldX = 10 * gp.tileSize;
        //gp.obj[6].worldY = 8 * gp.tileSize;
    
        //gp.obj[7] = new OBJ_Boots(gp);
        //gp.obj[7].worldX = 37 * gp.tileSize;
        //gp.obj[7].worldY = 43 * gp.tileSize;
            
    }

    public void setNPC(){
        gp.npc[0] = new NPC_OldMan(gp);
        gp.npc[0].worldX = gp.tileSize * 21;
        gp.npc[0].worldY = gp.tileSize * 21;
    }


}
