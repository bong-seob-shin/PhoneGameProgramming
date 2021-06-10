package com.ac.kr.kpu.s2016184024.termproject.framework.object;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import com.ac.kr.kpu.s2016184024.termproject.framework.bitmap.GameBitmap;
import com.ac.kr.kpu.s2016184024.termproject.framework.iface.GameObject;
import com.ac.kr.kpu.s2016184024.termproject.framework.view.GameView;


public class ImageObject implements GameObject {
    protected  Bitmap bitmap;


    protected Rect srcRect = new Rect();
    protected RectF dstRect = new RectF();

    protected  ImageObject(){}
    protected  ImageObject(int resId, float x, float y){
        init(resId,x,y);
    }

    protected void init(int resId, float x, float y){
        bitmap = GameBitmap.load(resId);
        int w = bitmap.getWidth();
        int h  = bitmap.getHeight();
        srcRect.set(0,0,w,h);
        float l = x - w/2* GameView.MULTIPLIER;
        float t = y - h/2* GameView.MULTIPLIER;
        float r = x+w/2* GameView.MULTIPLIER;
        float b = y + h/2* GameView.MULTIPLIER;
        dstRect.set(l,t,r,b);
    }
    @Override
    public void update() {

    }
    public void changeBitmap(int resId)
    {
        bitmap = GameBitmap.load(resId);

    }
    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, srcRect, dstRect, null);
    }

    public float getDstWidth() {
        return dstRect.width();
    }
    public float getDstHeight(){
        return dstRect.height();
    }
    public float getRight(){ return dstRect.right; }
}
