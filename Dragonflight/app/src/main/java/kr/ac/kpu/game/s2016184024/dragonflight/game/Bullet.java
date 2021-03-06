package kr.ac.kpu.game.s2016184024.dragonflight.game;

import android.graphics.Canvas;
import android.graphics.RectF;

import java.util.ArrayList;

import kr.ac.kpu.game.s2016184024.dragonflight.R;
import kr.ac.kpu.game.s2016184024.dragonflight.framework.BoxCollidable;
import kr.ac.kpu.game.s2016184024.dragonflight.framework.GameBitmap;
import kr.ac.kpu.game.s2016184024.dragonflight.framework.GameObject;
import kr.ac.kpu.game.s2016184024.dragonflight.framework.Recyclable;

public class Bullet implements GameObject, BoxCollidable, Recyclable {

    private final GameBitmap bitmap;
    private float x;
    private float y;
    private int speed;

    private Bullet(float x, float y, int speed){
        this.x = x;
        this.y = y;
        this.speed = -speed;

        this.bitmap = new GameBitmap(R.mipmap.laser_1);

    }

    public static Bullet get(float x , float y , int speed){
        MainGame game =MainGame.get();
        Bullet bullet = (Bullet) game.get(Bullet.class);
        if(bullet ==null){
            return new Bullet(x,y,speed);
        }
        bullet.init(x,y,speed);
        return  bullet;
    }

    private void init(float x, float y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = -speed;
    }

    @Override
    public void update() {

        MainGame game = MainGame.get();
        y += speed * game.frameTime;

        if(y < 0) {
            game.remove(this);
            recycle();
        }
    }

    @Override
    public void draw(Canvas canvas) {
      bitmap.draw(canvas,x,y);


    }
    @Override
    public void getBoundingRect(RectF rect) {
        bitmap.getBoundingRect(x,y,rect);
    }

    @Override
    public void recycle(){
    }
}
