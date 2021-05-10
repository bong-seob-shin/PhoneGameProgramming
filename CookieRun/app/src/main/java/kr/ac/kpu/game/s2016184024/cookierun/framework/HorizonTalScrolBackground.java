package kr.ac.kpu.game.s2016184024.cookierun.framework;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import kr.ac.kpu.game.s2016184024.cookierun.framework.view.GameView;


public class HorizonTalScrolBackground implements GameObject {
    private final Bitmap bitmap;
    private final  float speed;
    private  float  scroll;
    private Rect srcRect = new Rect();
    private RectF dstRect = new RectF();

    HorizonTalScrolBackground(int resId, int speed){
        this.speed = speed* GameView.MULTIPLIER;
        bitmap = GameBitmap.load(resId);
        int w = bitmap.getWidth();
        int h  = bitmap.getHeight();
        srcRect.set(0,0,w,h);
        float l = 0;
        float t = 0;

        float r = GameView.view.getWidth();
        float b = r*h/w;
        dstRect.set(l,t,r,b);
    }
    @Override
    public void update() {
        MainGame game = MainGame.get();
        float amount = speed  * game.frameTime;
        scroll += amount;
    }

    @Override
    public void draw(Canvas canvas){
        int vw = GameView.view.getWidth();
        int vh = GameView.view.getHeight();
        int iw = bitmap.getWidth();
        int ih = bitmap.getHeight();
        int dh = vh * ih/iw;


        int curr = (int)scroll % dh;

        if(curr>0) curr -= dh;

        while( curr < vw){
            dstRect.set(curr,0 , curr + dh,vh );
            canvas.drawBitmap(bitmap, srcRect, dstRect, null);

            curr += dh;
        }


    }
}
