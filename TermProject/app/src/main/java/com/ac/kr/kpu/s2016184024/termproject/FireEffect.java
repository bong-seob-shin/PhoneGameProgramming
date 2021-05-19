package com.ac.kr.kpu.s2016184024.termproject;

import android.graphics.Canvas;

import com.ac.kr.kpu.s2016184024.termproject.framework.bitmap.AnimationGameBitmap;
import com.ac.kr.kpu.s2016184024.termproject.framework.bitmap.GameBitmap;
import com.ac.kr.kpu.s2016184024.termproject.framework.iface.GameObject;

public class FireEffect implements GameObject {

    private AnimationGameBitmap fireBitmap;
    private float x, y;

    FireEffect(float x, float y){
        this.x = x;
        this.y = y;
        this.fireBitmap = new AnimationGameBitmap(R.mipmap.fireshot, 15f, 8);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        this.fireBitmap.draw(canvas,this.x,this.y);
    }
}
