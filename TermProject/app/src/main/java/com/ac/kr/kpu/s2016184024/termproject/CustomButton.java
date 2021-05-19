package com.ac.kr.kpu.s2016184024.termproject;

import com.ac.kr.kpu.s2016184024.termproject.framework.object.ImageObject;
import com.ac.kr.kpu.s2016184024.termproject.framework.view.GameView;


public class CustomButton extends ImageObject {

    private static final float TILESIZE = 300;
    public static float TILE_WIDTH = TILESIZE * GameView.MULTIPLIER;
    public static float TILE_HEIGHT = TILESIZE* GameView.MULTIPLIER;

    boolean isSelected = false;

    float x, y;

    public CustomButton(int resId, float x, float y){

        super(resId, x, y);

        this.x = x;
        this.y = y;
        dstRect.set( this.x-(TILE_WIDTH/2), this.y-(TILE_HEIGHT/2)-TILESIZE/2,  this.x+(TILE_WIDTH/2),  this.y+(TILE_HEIGHT/2)-TILESIZE/2);


    }

    public boolean getIsSelected(){
        return isSelected;
    }

    public void setIsSelected(boolean onOff){
        isSelected = onOff;
    }

    public Pair getPos(){
        return new Pair(this.x, this.y);
    }

}
