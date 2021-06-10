package com.ac.kr.kpu.s2016184024.termproject.framework.Scene;

import android.view.MotionEvent;

import com.ac.kr.kpu.s2016184024.termproject.Background;
import com.ac.kr.kpu.s2016184024.termproject.CustomButton;

import com.ac.kr.kpu.s2016184024.termproject.FireEffect;
import com.ac.kr.kpu.s2016184024.termproject.MainGame;
import com.ac.kr.kpu.s2016184024.termproject.Pair;
import com.ac.kr.kpu.s2016184024.termproject.R;
import com.ac.kr.kpu.s2016184024.termproject.Score;
import com.ac.kr.kpu.s2016184024.termproject.Tiles;
import com.ac.kr.kpu.s2016184024.termproject.framework.game.Scene;
import com.ac.kr.kpu.s2016184024.termproject.framework.iface.GameObject;
import com.ac.kr.kpu.s2016184024.termproject.framework.view.GameView;

public class ResultScene extends Scene {

    private Score score;

    private CustomButton selectButton;



    public enum Layer{
        bg, Tile,ui,fire, LAYER_COUNT
    }

    public static ResultScene scene;
    public void add(Layer layer, GameObject obj){
        add(layer.ordinal(), obj);
    }

    @Override
    public void start(){
        scene =this;
        super.start();
        int w = GameView.view.getWidth();
        int h = GameView.view.getHeight();

        initLayers(Layer.LAYER_COUNT.ordinal());
        add(Layer.bg, new Background(R.mipmap.background, w/2, h/2,0));


        float tw = w/2- Tiles.TILE_WIDTH*2;
        float ty = h/2- Tiles.TILE_HEIGHT*2;
        for(int i = 0; i<5; i++){
            for(int j = 0; j<5; j++){
                add(Layer.Tile, new Tiles(tw+Tiles.TILE_WIDTH*i, ty+Tiles.TILE_HEIGHT*j,i,j ));
            }
        }


        Pair firePos =  MainGame.get().my_Symbol.getPos();
        add(Layer.fire, new FireEffect(firePos.getFirst(),firePos.getSecond() ));


        selectButton = new CustomButton(R.mipmap.button, w/2, h-200);
        add(Layer.ui, selectButton);

        score = new Score(w/2+100,  GameView.view.getTop()+100);
        score.setScore(10);
        add(Layer.ui, score);




    }


    public boolean checkButton(CustomButton bts,float x, float y){

        Pair btsPos = bts.getPos();

        if(btsPos.getFirst()-CustomButton.TILE_WIDTH/2<x&&btsPos.getFirst()+CustomButton.TILE_WIDTH/2>x){
            if(btsPos.getSecond()-CustomButton.TILE_HEIGHT/2<y&&btsPos.getSecond()+CustomButton.TILE_HEIGHT/2>y){
                return true;
            }
        }

        return  false;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN :

                boolean btsCheck = checkButton(selectButton,event.getX(),event.getY());
                if(btsCheck){
                   MainGame.get().popTwoScene();
                }

                break;


        }
        return  true;
    }
}
