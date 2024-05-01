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

        if (up && left) {
            tiles[0][0] = 34;
        } else if (!up && left) {
            tiles[0][0] = 33;

        } else if (up && !left) {
            tiles[0][0] = 31;

        } else {
            tiles[0][0] = 35;
        }
        
        if (down && left) {
            tiles[0][2] = 29;
        } else if (!down && left) {
            tiles[0][2] = 28;

        } else if (down && !left) {
            tiles[0][2] = 31;

        } else {
            tiles[0][2] = 37;
        }
        
        if (up && right) {
            tiles[2][0] = 32;
        } else if (!up && right) {
            tiles[2][0] = 33;
        } else if (up && !right) {
            tiles[2][0] = 30;
        } else {
            tiles[2][0] = 36;
        }
        



        if (down && right) {
            tiles[2][2] = 27;
        } else if (!down && right) {
            tiles[2][2] = 28;

        } else if (down && !right) {
            tiles[2][2] = 30;

        } else {
            tiles[2][2] = 38;
        }
        
        return tiles;
        
        
    }
}
