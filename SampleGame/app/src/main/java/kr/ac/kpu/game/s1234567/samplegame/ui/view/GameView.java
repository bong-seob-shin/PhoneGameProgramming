package kr.ac.kpu.game.s1234567.samplegame.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Choreographer;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import kr.ac.kpu.game.s1234567.samplegame.game.MainGame;

public class GameView extends View {
    private static final String TAG = GameView.class.getSimpleName();
    private Bitmap bitmap;


    private long lastFrame;

    public static GameView view;
    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        GameView.view = this;

        startUpdating();
    }

    private void startUpdating() {
        doGameFrame();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Log.d(TAG, "OnSize" + w +","+ h);
        MainGame game = MainGame.get();
        game.initResources();
        //super.onSizeChanged(w, h, oldw, oldh);
    }


    //    Handler handler = new Handler();

    private void doGameFrame() {
        MainGame game = MainGame.get();
        game.update();
        invalidate();

        Choreographer.getInstance().postFrameCallback(new Choreographer.FrameCallback() {
            @Override
            public void doFrame(long time) {
                if (lastFrame == 0) {
                    lastFrame = time;
                }
                game.frameTime = (float) (time - lastFrame) / 1_000_000_000;
                doGameFrame();
                lastFrame = time;
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













