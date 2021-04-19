package kr.ac.kpu.game.s2016184024.dragonflight.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

import kr.ac.kpu.game.s2016184024.dragonflight.R;
import kr.ac.kpu.game.s2016184024.dragonflight.framework.BoxCollidable;
import kr.ac.kpu.game.s2016184024.dragonflight.framework.GameBitmap;
import kr.ac.kpu.game.s2016184024.dragonflight.framework.GameObject;
import kr.ac.kpu.game.s2016184024.dragonflight.ui.view.GameView;

public class Bullet implements GameObject, BoxCollidable {

    private final GameBitmap bitmap;
    private float x;
    private float y;
    private final int speed;

    public Bullet(float x, float y, int speed){
        this.x = x;
        this.y = y;
        this.speed = speed;

        this.bitmap = new GameBitmap(R.mipmap.laser_1);

    }

    @Override
    public void update() {

        MainGame game = MainGame.get();
        y -= speed * game.frameTime;

        if(y < 0) {
            game.remove(this);
        }
    }

    @Override
    public void draw(Canvas canvas) {
      bitmap.draw(canvas,x,y);


    }

    @Override
    public RectF getBoundingRect() {
        return  bitmap.getBoundingRect(x,y);
    }
}
