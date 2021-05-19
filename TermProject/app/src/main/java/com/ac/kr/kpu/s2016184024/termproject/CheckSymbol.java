package com.ac.kr.kpu.s2016184024.termproject;

import com.ac.kr.kpu.s2016184024.termproject.framework.object.ImageObject;
import com.ac.kr.kpu.s2016184024.termproject.framework.view.GameView;


public class CheckSymbol extends ImageObject {

    private static final float TILESIZE = 130;
    public static float TILE_WIDTH = TILESIZE * GameView.MULTIPLIER;
    public static float TILE_HEIGHT = TILESIZE* GameView.MULTIPLIER;


    public CheckSymbol(int resId, float x, float y){

        super(resId, x, y);

        dstRect.set(x-(TILE_WIDTH/2),y-(TILE_HEIGHT/2)-TILESIZE/2, x+(TILE_WIDTH/2), y+(TILE_HEIGHT/2)-TILESIZE/2);


    }


    public void setPos(float x, float y){
        dstRect.set(x-(TILE_WIDTH/2),y-(TILE_HEIGHT/2)-TILESIZE/2, x+(TILE_WIDTH/2), y+(TILE_HEIGHT/2)-TILESIZE/2);

    }

}
