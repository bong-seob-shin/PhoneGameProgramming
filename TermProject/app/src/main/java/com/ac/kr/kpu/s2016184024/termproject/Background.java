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

    private static final float TILESIZE = 10000;



    public Background(int resId, float x,  float y){

        super(resId, x, y);

        dstRect.set(0,GameView.view.getTop(), GameView.view.getRight(), GameView.view.getBottom());

    }


}
