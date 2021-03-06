package com.ac.kr.kpu.s2016184024.termproject.framework.bitmap;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.ac.kr.kpu.s2016184024.termproject.framework.view.GameView;


public class IndexedGameBitmap extends GameBitmap {

    private int width,height, xcount, border, spacing;

    public IndexedGameBitmap(int resId, int width, int height,int xcount, int border, int spacing ) {
        super(resId);
        this.width = width;
        this.height = height;
        this.xcount = xcount;
        this.border = border;
        this.spacing = spacing;
    }

    protected Rect srcRect = new Rect();
    public void setIndex(int index){


            int x = index % xcount;
            int y = index / xcount;
            int l = border+x *(width+spacing);
            int t = border+y * (width+spacing);
            int r = l +width;
            int b = t+height;
            srcRect.set(l,t,r,b);


    }

    @Override
    public void draw(Canvas canvas, float x, float y) {
       float hw = width/2 * GameView.MULTIPLIER;
       float hh = height/2 * GameView.MULTIPLIER;

        dstRect.set(x-hw, y -hh, x+hw,y+hh);

        canvas.drawBitmap(bitmap, srcRect, dstRect, null);

    }
}
