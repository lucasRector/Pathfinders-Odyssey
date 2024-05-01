package edu.appstate.cs.umbreon.tile.mapgen;

public class RoadTile {
    public boolean up;
    public boolean down;
    public boolean left;
    public boolean right;

    public boolean hasRoad() {
        return up || down || left || right;
    }

    public int[][] getTiles(){
        int[][] tiles = new int[3][3];
        
        if(!(up || down || left || right)){
            return null;
        }
        tiles[1][1] = 26;

        if(up){
            tiles[1][0] = 26;
        }else{
            tiles[1][0] = 33;
        }

        if(down){
            tiles[1][2] = 26;
        }else{
            tiles[1][2] = 28;
        }

        if(left){
            tiles[0][1] = 26;
        }else{
            tiles[0][1] = 31;
        }

        if(right){
            tiles[2][1] = 26;
        }else{
            tiles[2][1] = 30;
        }
        
        return tiles;
        
        
    }
}
