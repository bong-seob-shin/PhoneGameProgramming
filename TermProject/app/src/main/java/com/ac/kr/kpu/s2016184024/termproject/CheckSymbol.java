package com.ac.kr.kpu.s2016184024.termproject;

import com.ac.kr.kpu.s2016184024.termproject.framework.object.ImageObject;
import com.ac.kr.kpu.s2016184024.termproject.framework.view.GameView;


public class CheckSymbol extends ImageObject {

    private static final float TILESIZE = 130;
    public static float TILE_WIDTH = TILESIZE * GameView.MULTIPLIER;
    public static float TILE_HEIGHT = TILESIZE* GameView.MULTIPLIER;
    float x, y;

    public CheckSymbol(int resId, float x, float y){

        super(resId, x, y);
        this.x = x;
        this.y = y;
        dstRect.set(this.x-(TILE_WIDTH/2),this.y-(TILE_HEIGHT/2)-TILESIZE/2, this.x+(TILE_WIDTH/2), this.y+(TILE_HEIGHT/2)-TILESIZE/2);


    }


    public void setPos(float x, float y){
        this.x =x;
        this.y =y;
        dstRect.set(this.x-(TILE_WIDTH/2),this.y-(TILE_HEIGHT/2)-TILESIZE/2, this.x+(TILE_WIDTH/2), this.y+(TILE_HEIGHT/2)-TILESIZE/2);

    }

}
