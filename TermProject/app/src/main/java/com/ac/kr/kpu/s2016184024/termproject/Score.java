package com.ac.kr.kpu.s2016184024.termproject;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import com.ac.kr.kpu.s2016184024.termproject.framework.bitmap.GameBitmap;
import com.ac.kr.kpu.s2016184024.termproject.framework.iface.GameObject;
import com.ac.kr.kpu.s2016184024.termproject.framework.view.GameView;


public class Score implements GameObject {

    private final Bitmap bitmap;
    private final int right;
    private final int top;
    private int score, displayScore;
    private Rect src = new Rect();
    private RectF dst = new RectF();
    public Score(int right, int top){
        bitmap = GameBitmap.load(R.mipmap.number_24x32);
        this.right = right;
        this.top = top;
    }
    public  void setScore(int score){
        this.score = score;
        this.displayScore = score;
    }
    public void addScore(int amount) {
        score += amount;
    }
    @Override
    public void update() {
        if(displayScore< score){
            displayScore++;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        int value = this.displayScore;
        int nw = bitmap.getWidth()/10;
        int nh = bitmap.getHeight();
        int x = right;
        int dw = (int)(nw*2* GameView.MULTIPLIER);
        int dh = (int)(nh *2* GameView.MULTIPLIER);


        while(value>0){
            int digit = value%10;
            src.set(digit*nw, 0, (digit+1)*nw, nh);
            x -= dw;
            dst.set(x, top, x+dw, top+dh);
            canvas.drawBitmap(bitmap,src,dst,null);
            value /= 10;
        }
    }


}
