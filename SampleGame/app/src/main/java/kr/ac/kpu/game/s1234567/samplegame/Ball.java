package kr.ac.kpu.game.s1234567.samplegame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Ball implements GameObject{
    private static int imageWidth;
    private static int imageHeight;
    private   float x,y;
    private  float dx, dy;
    private static Bitmap bitmap;


    public Ball(float x, float y, float dx, float dy) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        if(bitmap ==null){
        Resources res = GameView.view.getResources();
        bitmap = BitmapFactory.decodeResource(res, R.mipmap.soccer_ball_240);
        imageWidth = bitmap.getWidth();
        imageHeight = bitmap.getHeight();
        }
    }

    public void update() {
        this.x += dx * GameView.frameTime;
        this.y += dy * GameView.frameTime;
        int w = GameView.view.getWidth();
        int h = GameView.view.getHeight();
        if(x<imageWidth/2 || x>w-imageWidth/2){
            dx = -dx;
        }
        if(y<imageHeight/2||y>h-imageHeight/2){
            dy = -dy;
        }

    }

    public void draw(Canvas canvas)
    {
        float left =this.x-imageWidth/2;
        float top = this.y-imageWidth/2;
        canvas.drawBitmap(bitmap,left,top,null);
    }
}