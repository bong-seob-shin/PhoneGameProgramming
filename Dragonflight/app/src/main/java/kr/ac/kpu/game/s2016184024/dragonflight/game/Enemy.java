package kr.ac.kpu.game.s2016184024.dragonflight.game;

import android.graphics.Canvas;
import android.graphics.RectF;

import kr.ac.kpu.game.s2016184024.dragonflight.R;
import kr.ac.kpu.game.s2016184024.dragonflight.framework.AnimationGameBitmap;
import kr.ac.kpu.game.s2016184024.dragonflight.framework.BoxCollidable;
import kr.ac.kpu.game.s2016184024.dragonflight.framework.GameBitmap;
import kr.ac.kpu.game.s2016184024.dragonflight.framework.GameObject;
import kr.ac.kpu.game.s2016184024.dragonflight.ui.view.GameView;

public class Enemy implements GameObject, BoxCollidable {
    private static final float FRAMES_PER_SECOND = 8.0f;
    private final AnimationGameBitmap bitmap;
    private float x;
    private float y;
    private final int speed;

    public Enemy(float x, float y, int speed){
        this.x = x;
        this.y = y;
        this.speed = speed;

        this.bitmap = new AnimationGameBitmap(R.mipmap.enemy_01, FRAMES_PER_SECOND, 0);

    }

    @Override
    public void update() {
        MainGame game = MainGame.get();
        y += speed * game.frameTime;

        if(y > GameView.view.getHeight()) {
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