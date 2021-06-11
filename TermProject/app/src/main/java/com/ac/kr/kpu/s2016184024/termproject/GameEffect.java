package com.ac.kr.kpu.s2016184024.termproject;

import android.graphics.Canvas;

import com.ac.kr.kpu.s2016184024.termproject.framework.bitmap.AnimationGameBitmap;
import com.ac.kr.kpu.s2016184024.termproject.framework.bitmap.GameBitmap;
import com.ac.kr.kpu.s2016184024.termproject.framework.iface.GameObject;

public class GameEffect implements GameObject {

    private AnimationGameBitmap fireBitmap;
    private float x, y;

    public GameEffect(float x, float y, int resId, int framecount){
        this.x = x;
        this.y = y;
        this.fireBitmap = new AnimationGameBitmap(resId, framecount*2, framecount);
    }

    public void SetPos(float x, float y){
        this.x = x;
        this.y = y;
    }
    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        this.fireBitmap.draw(canvas,this.x,this.y);
    }
}
