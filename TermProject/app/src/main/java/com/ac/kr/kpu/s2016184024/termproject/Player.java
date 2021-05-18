package com.ac.kr.kpu.s2016184024.termproject;

import android.graphics.Canvas;
import android.graphics.RectF;

import com.ac.kr.kpu.s2016184024.termproject.framework.bitmap.GameBitmap;
import com.ac.kr.kpu.s2016184024.termproject.framework.game.BaseGame;
import com.ac.kr.kpu.s2016184024.termproject.framework.iface.BoxCollidable;
import com.ac.kr.kpu.s2016184024.termproject.framework.iface.GameObject;


public class Player implements GameObject, BoxCollidable {


    private   float x,y;
    private GameBitmap characterBitmap;
    private GameBitmap fireBitmap;
    private float tx,ty;
    private float speed;
    private float angle;

    public Player(float x, float y) {
        this.x = x;
        this.y = y;
        this.tx = x;
        this.ty = 0;
        this.speed = 800;
        this.characterBitmap = new GameBitmap(R.mipmap.tank);


    }

    public void moveTo(float x, float y){
          this.tx =x;


    }

    public void update() {
        BaseGame game = BaseGame.get();
        float dx = speed * game.frameTime;
        if(tx < x){
            //move left
            dx = -dx;
        }
        x+=dx;

        if((dx>0&& x>tx)||(dx<0&& x<tx)){
            x =tx;
        }


    }



    public void draw(Canvas canvas) {

        characterBitmap.draw(canvas, this.x, this.y);

    }

    public void getBoundingRect(RectF rect) {
        characterBitmap.getBoundingRect(x,y,rect);
    }
}
