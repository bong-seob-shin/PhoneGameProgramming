package kr.ac.kpu.game.s2016184024.cookierun.game;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.nfc.Tag;
import android.util.Log;

import kr.ac.kpu.game.s2016184024.cookierun.R;
import kr.ac.kpu.game.s2016184024.cookierun.framework.iface.BoxCollidable;
import kr.ac.kpu.game.s2016184024.cookierun.framework.iface.GameObject;
import kr.ac.kpu.game.s2016184024.cookierun.framework.bitmap.IndexedAnimationGameBitmap;
import kr.ac.kpu.game.s2016184024.cookierun.framework.game.BaseGame;


public class Player implements GameObject, BoxCollidable {
    private static final float FIRE_INTERVAL = 1.0f/7.5f;
    private static final float GRAVITY = 2000f;
    private static final float JUMPPOWER = 1200f;
    private static final String TAG = Player.class.getSimpleName();
    private final IndexedAnimationGameBitmap charBitmap;


    private   float x,y;
    private  float ground_y;
;
    private float vertSpeed;

    private int[] ANIM_INDICES_RUNNING = {100, 101, 102, 103};
    private int[] ANIM_INDICES_JUMP = {7, 8};
    private int[] ANIM_INDICES_DOUBLEJUMP = {1, 2,3,4};

    private enum State{
        running, jump, doubleJump, slide, hit
    }

    public void setState(State state) {
        this.state = state;
        int[] indices = ANIM_INDICES_RUNNING;
        switch (state){
            case running: indices = ANIM_INDICES_RUNNING;break;
            case jump: indices = ANIM_INDICES_JUMP;break;
            case doubleJump: indices = ANIM_INDICES_DOUBLEJUMP;break;
        }
        charBitmap.setIndices(indices);
    }

    private  State state = State.running;
    public Player(float x, float y) {
        this.x = x;
        this.y = y;


        this.ground_y = y;
        this.charBitmap = new IndexedAnimationGameBitmap(R.mipmap.cookie, 7.5f, 0);

        setState(State.running);


    }



    public void update() {
        BaseGame game = BaseGame.get();
       if(state == State.jump || state == State.doubleJump){
           float y  = this.y + vertSpeed * game.frameTime;
           vertSpeed += GRAVITY * game.frameTime;
           if(y>= ground_y){
               y = ground_y;
               setState(State.running);
           }
           this.y = y;
       }
    }


    public void draw(Canvas canvas) {
        charBitmap.draw(canvas, this.x, this.y);
    }


    public void getBoundingRect(RectF rect) {
      //    planeBitmap.getBoundingRect(x,y,rect);
    }

    public void Jump() {
        //if(state != State.running &&state !=State.jump&&state != State.slide){
        if(state != State.running&&state != State.jump){
            return;
        }
        if(state == State.running) {
            setState(State.jump);
            vertSpeed = -JUMPPOWER;
        }
        else if(state == State.jump){
            setState(State.doubleJump);
            Log.d(TAG, "Jump");
            vertSpeed = -JUMPPOWER;

        }
    }
}
