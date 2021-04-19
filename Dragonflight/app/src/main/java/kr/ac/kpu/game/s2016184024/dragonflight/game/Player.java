package kr.ac.kpu.game.s2016184024.dragonflight.game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;

import kr.ac.kpu.game.s2016184024.dragonflight.R;
import kr.ac.kpu.game.s2016184024.dragonflight.framework.BoxCollidable;
import kr.ac.kpu.game.s2016184024.dragonflight.framework.GameBitmap;
import kr.ac.kpu.game.s2016184024.dragonflight.framework.GameObject;
import kr.ac.kpu.game.s2016184024.dragonflight.framework.Sound;
import kr.ac.kpu.game.s2016184024.dragonflight.ui.view.GameView;

public class Player implements GameObject , BoxCollidable {
    private static final int BULLET_SPEED = 1500;
    private static final float FIRE_INTERVAL = 1.0f/7.5f;
    private float fireTime;

    private  int imageWidth;
    private  int imageHeight;

    private   float x,y;
    private  float dx, dy;
    private GameBitmap bitmap;
    private float tx,ty;
    private float speed;
    private float angle;

    public Player(float x, float y) {
        this.x = x;
        this.y = y;
        this.tx = x;
        this.ty = 0;
        this.speed = 800;
        this.bitmap = new GameBitmap(R.mipmap.fighter);
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
            fireBullet();
            fireTime -= FIRE_INTERVAL;
        }
//
    }

    private void fireBullet() {
        Bullet bullet = new Bullet(this.x, this.y,BULLET_SPEED);
        MainGame game = MainGame.get();
        game.add(bullet);
    }

    public void draw(Canvas canvas) {

        bitmap.draw(canvas, this.x, this.y);

    }

    public RectF getBoundingRect() {
        return  bitmap.getBoundingRect(x,y);
    }
}
