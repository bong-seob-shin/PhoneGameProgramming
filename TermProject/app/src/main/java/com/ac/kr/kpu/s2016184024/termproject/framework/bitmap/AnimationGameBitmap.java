package com.ac.kr.kpu.s2016184024.termproject.framework.bitmap;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.ac.kr.kpu.s2016184024.termproject.framework.view.GameView;


public class AnimationGameBitmap extends GameBitmap {
    private static final int PIXEL_MULTIPLIER = 4;
    private final int imageWidth;
    protected final int imageHeight;
    protected int frameWidth;
    protected final long createdOn;
    protected int frameIndex;
    protected final float framesPerSecond;
    protected int frameCount;

    protected  Rect srcRect = new Rect();

    public AnimationGameBitmap(int resId, float framePerSecond, int frameCount){
        super(resId);
        imageWidth = bitmap.getWidth();
        imageHeight = bitmap.getHeight();

        if(frameCount == 0 ){
            frameCount = imageWidth/imageHeight;
        }
        frameWidth = imageWidth / frameCount;

        this.framesPerSecond = framePerSecond;
        this.frameCount = frameCount;
        createdOn = System.currentTimeMillis();
        frameIndex = 0;
    }

    //    public void Update(){
//        int elapsed = (int)(System.currentTimeMillis() - createdOn);
//        frameIndex = Math.round(elapsed *0.001f* framesPerSecond) % frameCount;
//    }

    public void draw(Canvas canvas, float x, float y){
        int elapsed = (int)(System.currentTimeMillis() - createdOn);
        frameIndex = Math.round(elapsed *0.001f* framesPerSecond) % frameCount;

        int fw = frameWidth;
        int h = imageHeight;
        float hw = fw /2* GameView.MULTIPLIER;
        float hh = imageHeight/2 *GameView.MULTIPLIER ;
        srcRect.set(fw*frameIndex,0,fw*frameIndex+fw, h);
        dstRect.set(x-hw, y -hh, x+hw,y+hh);

        canvas.drawBitmap(bitmap, srcRect, dstRect, null);

    }

    public int getWidth(){
        return frameWidth;
    }

    public int getHeight(){
        return imageHeight;
    }
}
