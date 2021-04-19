package kr.ac.kpu.game.s2016184024.dragonflight.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Choreographer;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import kr.ac.kpu.game.s2016184024.dragonflight.framework.Sound;
import kr.ac.kpu.game.s2016184024.dragonflight.game.MainGame;

public class GameView extends View {
    private static final String TAG = GameView.class.getSimpleName();

    public static final  float MULTIPLIER = 2;


    private long lastFrame;

    public static GameView view;
    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        GameView.view = this;
        Sound.init(context);
        //startUpdating();
    }

//    private void startUpdating() {
//        doGameFrame();
//    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Log.d(TAG, "OnSize" + w +","+ h);
        MainGame game = MainGame.get();

        boolean justInitialized = game.initResources();
        if(justInitialized){
            requestCallback();
        }
        //super.onSizeChanged(w, h, oldw, oldh);
    }
    private void update() {
        MainGame game = MainGame.get();
        game.update();

        invalidate();
    }

    //    Handler handler = new Handler();

//    private void doGameFrame() {
//        MainGame game = MainGame.get();
//        game.update();
//        invalidate();
//
//        Choreographer.getInstance().postFrameCallback(new Choreographer.FrameCallback() {
//            @Override
//            public void doFrame(long time) {
//                if (lastFrame == 0) {
//                    lastFrame = time;
//                }
//                game.frameTime = (float) (time - lastFrame) / 1_000_000_000;
//                doGameFrame();
//                lastFrame = time;
//            }
//        });
//    }

    private void requestCallback() {
        Choreographer.getInstance().postFrameCallback(new Choreographer.FrameCallback() {
            @Override
            public void doFrame(long time) {
                if (lastFrame == 0) {
                    lastFrame = time;
                }
                MainGame game = MainGame.get();
                game.frameTime = (float) (time - lastFrame) / 1_000_000_000;
                update();
                lastFrame = time;
                requestCallback();
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        MainGame game = MainGame.get();
        game.update();
        game.draw(canvas);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        MainGame game = MainGame.get();
        return game.onTouchEvent(event);

    }
}













