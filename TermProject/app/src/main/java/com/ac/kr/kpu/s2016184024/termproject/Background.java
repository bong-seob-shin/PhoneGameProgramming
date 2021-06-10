package com.ac.kr.kpu.s2016184024.termproject;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import com.ac.kr.kpu.s2016184024.termproject.framework.bitmap.GameBitmap;
import com.ac.kr.kpu.s2016184024.termproject.framework.game.BaseGame;
import com.ac.kr.kpu.s2016184024.termproject.framework.iface.GameObject;
import com.ac.kr.kpu.s2016184024.termproject.framework.object.ImageObject;
import com.ac.kr.kpu.s2016184024.termproject.framework.view.GameView;


public class Background extends ImageObject {

    private static final float TILESIZE = 800;
    public static float TILE_WIDTH = TILESIZE * GameView.MULTIPLIER;
    public static float TILE_HEIGHT = TILESIZE* GameView.MULTIPLIER;


    public Background(int resId, float x,  float y, int type ){

        super(resId, x, y);

        if(type == 0)
            dstRect.set(0,GameView.view.getTop(), GameView.view.getRight(), GameView.view.getBottom());

        if(type == 1)
            dstRect.set( x-(TILE_WIDTH/2), y-(TILE_HEIGHT/2)-TILESIZE/2,  x+(TILE_WIDTH/2),  y+(TILE_HEIGHT/2)-TILESIZE/2);

    }


}
