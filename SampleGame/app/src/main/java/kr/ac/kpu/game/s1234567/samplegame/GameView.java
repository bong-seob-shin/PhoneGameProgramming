package kr.ac.kpu.game.s1234567.samplegame;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Choreographer;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Random;

public class GameView extends View {
    private static final String TAG = GameView.class.getSimpleName();
    public static final int BALL_COUNT = 10;
    private Bitmap bitmap;

    Player player;
//    ArrayList<Ball> balls = new ArrayList<>();

    ArrayList<GameObject> objects = new ArrayList<>();

    private long lastFrame;
    public static float frameTime;
    public static GameView view;
    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        GameView.view = this;
        initResources();
        startUpdating();
    }

    private void startUpdating() {
        doGameFrame();
    }

//    Handler handler = new Handler();

    private void doGameFrame() {
//        update();
        for(GameObject go: objects){
            go.update();
        }

//        draw();
        invalidate();

        Choreographer.getInstance().postFrameCallback(new Choreographer.FrameCallback() {
            @Override
            public void doFrame(long time) {
                if (lastFrame == 0) {
                    lastFrame = time;
                }
                frameTime = (float) (time - lastFrame) / 1_000_000_000;
                doGameFrame();
                lastFrame = time;
            }
        });
    }

    private void initResources() {

        player = new Player(100,100,0,0);
        objects.add(player);

        Random random = new Random();
        for (int i = 0; i< BALL_COUNT; i++){
            float x = random.nextInt(800)+100;
            float y = random.nextInt(900)+100;
            float dx = random.nextFloat()*1000 -500;
            float dy = random.nextFloat()*1000-500;
            Ball b = new Ball(x,y,dx,dy);
            objects.add(b);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for(GameObject go: objects){
            go.draw(canvas);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if(action == MotionEvent.ACTION_DOWN|| action == MotionEvent.ACTION_MOVE){
            player.moveTo(event.getX(), event.getY());
            return true;
        }
        return false;
    }
}













