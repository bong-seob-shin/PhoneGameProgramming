package com.ac.kr.kpu.s2016184024.termproject;

import android.service.quicksettings.TileService;

import com.ac.kr.kpu.s2016184024.termproject.framework.object.ImageObject;
import com.ac.kr.kpu.s2016184024.termproject.framework.view.GameView;

public class Tiles extends ImageObject {
    private static final float TILESIZE = 140;
    public static float TILE_WIDTH = TILESIZE * GameView.MULTIPLIER;
    public static float TILE_HEIGHT = TILESIZE* GameView.MULTIPLIER;
    public Tiles(int resId, float x , float y){
        super(resId,x,y);

        dstRect.set(x-(TILE_WIDTH/2),y-(TILE_HEIGHT/2), x+(TILE_WIDTH/2), y+(TILE_HEIGHT/2));
    }

}
