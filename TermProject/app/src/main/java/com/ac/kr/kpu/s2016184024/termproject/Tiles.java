package com.ac.kr.kpu.s2016184024.termproject;

import com.ac.kr.kpu.s2016184024.termproject.framework.object.ImageObject;
import com.ac.kr.kpu.s2016184024.termproject.framework.view.GameView;

public class Tiles extends ImageObject {
    private static final float TILESIZE = 140;
    public static float TILE_WIDTH = TILESIZE * GameView.MULTIPLIER;
    public static float TILE_HEIGHT = TILESIZE* GameView.MULTIPLIER;
    public Tiles( float x , float y, int indexX, int indexY){

        if(indexX == 0){
            if(indexY == 0){
                init(R.mipmap.left_top_tile,x,y);
            }
            else if(indexY == 4){
                init(R.mipmap.left_bottom_tile,x,y);
            }
            else{
                init(R.mipmap.left_tile,x,y);
            }
        }
        else if(indexX == 4){
            if(indexY == 0){
                init(R.mipmap.right_top_tile,x,y);
            }
            else if(indexY == 4){
                init(R.mipmap.right_bottom_tile,x,y);
            }
            else{
                init(R.mipmap.right_tile,x,y);
            }
        }
        else{
            if(indexY == 0){
                init(R.mipmap.top_tile,x,y);
            }
            else if(indexY == 4){
                init(R.mipmap.bottom_tile,x,y);
            }
            else{
                init(R.mipmap.center_tile,x,y);
            }
        }


        dstRect.set(x-(TILE_WIDTH/2),y-(TILE_HEIGHT/2), x+(TILE_WIDTH/2), y+(TILE_HEIGHT/2));
    }

}
