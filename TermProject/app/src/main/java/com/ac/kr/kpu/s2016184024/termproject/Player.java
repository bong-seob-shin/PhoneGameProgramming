package com.ac.kr.kpu.s2016184024.termproject;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.Log;

import com.ac.kr.kpu.s2016184024.termproject.framework.bitmap.GameBitmap;
import com.ac.kr.kpu.s2016184024.termproject.framework.game.BaseGame;
import com.ac.kr.kpu.s2016184024.termproject.framework.iface.GameObject;
import com.ac.kr.kpu.s2016184024.termproject.framework.view.GameView;


public class Player implements GameObject {


    private static final String TAG = Player.class.getSimpleName();
    private   float x,y;
    private GameBitmap characterBitmap;

    private float tx,ty;
    private float speed;
    private float dir; //0 top 1 bottom 2 left 3 right

    public String id;
    private boolean shieldItem;
    private boolean rangeItem;
    private boolean moveItem;
    public double HP;

    private int moveCount =4;

    public Player() {


    }

    public void setPlayerInfo(float x, float y, int resId){
        this.x = x;
        this.y = y;
        this.tx = x;
        this.ty = y;
        this.speed = 800;
        this.characterBitmap = new GameBitmap(resId);
        this.dir =0;
        this.shieldItem = true;
        this.rangeItem = true;
        this.moveItem = true;
        this.HP =0;
    }

    public boolean getShieldItem(){
        return shieldItem;
    }

    public boolean getRangeItem(){
        return rangeItem;
    }
    public boolean getMoveItem(){
        return moveItem;
    }

    public Pair getPos(){

        return new Pair(x, y);
    }
    public void setPlayerId(String id){
        this.id = id;
    }
    public void RightMove(){
        if(GameView.view.getWidth()/2+Tiles.TILE_WIDTH*2>this.tx){
            this.tx = this.tx + Tiles.TILE_WIDTH;
            Log.d(TAG, " Right Move");
            dir = 1;
        }
    }
    public void LeftMove(){
        if(GameView.view.getWidth()/2-Tiles.TILE_WIDTH*2<this.tx){
            this.tx = this.tx -Tiles.TILE_WIDTH;
            Log.d(TAG, " Left Move");

            dir = 3;
        }
    }
    public void UpMove() {
        if(GameView.view.getHeight()/2-Tiles.TILE_WIDTH*2<this.ty) {

            this.ty = this.ty - Tiles.TILE_HEIGHT;
            Log.d(TAG, " Up Move");

            dir = 0;
        }

    }

    public void DownMove() {
        if(GameView.view.getHeight()/2+Tiles.TILE_WIDTH*2>this.ty) {
            this.ty = this.ty + Tiles.TILE_HEIGHT;
            Log.d(TAG, " Down Move");
            dir =2;
        }
    }

    public void update() {
        BaseGame game = BaseGame.get();
        float dx = speed * game.frameTime;
        float dy = speed * game.frameTime;
        if(tx < x){
            //move left
            dx = -dx;
        }
        if(ty < y){
            //move left
            dy = -dy;
        }
        x+=dx;
        y += dy;

        if((dx>0&& x>tx)||(dx<0&& x<tx)){
            x =tx;
        }


    }



    public void draw(Canvas canvas) {


        canvas.save();
        canvas.rotate(dir*90,x,y);

        characterBitmap.draw(canvas, this.x, this.y);

        canvas.restore();
    }

    public void getBoundingRect(RectF rect) {
        characterBitmap.getBoundingRect(x,y,rect);
    }

    public int getMoveCount() {
        return moveCount;
    }

    public void setMoveCount(int moveCount) {
        this.moveCount = moveCount;
    }
}
