package kr.ac.kpu.game.s2016184024.cookierun.game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;

import kr.ac.kpu.game.s2016184024.cookierun.R;
import kr.ac.kpu.game.s2016184024.cookierun.framework.BoxCollidable;
import kr.ac.kpu.game.s2016184024.cookierun.framework.GameBitmap;
import kr.ac.kpu.game.s2016184024.cookierun.framework.GameObject;
import kr.ac.kpu.game.s2016184024.cookierun.framework.IndexedAnimationGameBitmap;
import kr.ac.kpu.game.s2016184024.cookierun.framework.MainGame;


public class Player implements GameObject, BoxCollidable {
    private static final int BULLET_SPEED = 1500;
    private static final float FIRE_INTERVAL = 1.0f/7.5f;
    private static final float LASER_DURATION = FIRE_INTERVAL/3;
    private final IndexedAnimationGameBitmap charBitmap;

    private float fireTime;

    private   float x,y;
    private float tx,ty;
    private float speed;
    private float angle;

    public Player(float x, float y) {
        this.x = x;
        this.y = y;
        this.tx = x;
        this.ty = 0;
        this.speed = 800;
        this.charBitmap = new IndexedAnimationGameBitmap(R.mipmap.cookie, 4.5f, 0);
        charBitmap.setIndices(100,101,102,103);

        this.fireTime = 0.0f;

    }

    public void moveTo(float x, float y){
          this.tx =x;


    }

    public void update() {
        MainGame game = MainGame.get();
        float dx = speed * game.frameTime;
        if(tx < x){
            //move left
            dx = -dx;
        }
        x+=dx;

        if((dx>0&& x>tx)||(dx<0&& x<tx)){
            x =tx;
        }

        fireTime += game.frameTime;
        if(fireTime >= FIRE_INTERVAL){
//            fireBullet();
            fireTime -= FIRE_INTERVAL;
        }
//
    }

//    private void fireBullet() {
//        Bullet bullet = Bullet.get(this.x, this.y,BULLET_SPEED);
//        MainGame game = MainGame.get();
//        game.add(MainGame.Layer.bullet, bullet);
//    }

    public void draw(Canvas canvas) {
        charBitmap.draw(canvas, this.x, this.y);
    }


    public void getBoundingRect(RectF rect) {
      //    planeBitmap.getBoundingRect(x,y,rect);
    }
}
