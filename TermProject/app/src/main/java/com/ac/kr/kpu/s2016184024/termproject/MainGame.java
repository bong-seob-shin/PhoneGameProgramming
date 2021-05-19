package com.ac.kr.kpu.s2016184024.termproject;

import android.nfc.Tag;
import android.util.Log;
import android.view.MotionEvent;

import androidx.appcompat.view.menu.MenuView;

import com.ac.kr.kpu.s2016184024.termproject.framework.game.BaseGame;
import com.ac.kr.kpu.s2016184024.termproject.framework.iface.GameObject;
import com.ac.kr.kpu.s2016184024.termproject.framework.view.GameView;

public class MainGame extends BaseGame {


    private static final String TAG = MainGame.class.getSimpleName();
    private Player player;
    private Score score;
    private boolean initialized;
    private float clickStartPosX =0;
    private float clickEndPosX =0;
    public enum Layer{
        bg, Tile,item,player,ui, controller, LAYER_COUNT
    }

    public void add(Layer layer, GameObject obj){
        add(layer.ordinal(), obj);
    }

    @Override
    public boolean initResources() {
        if(initialized){
            return false;
        }
        int w = GameView.view.getWidth();
        int h = GameView.view.getHeight();

        initLayers(Layer.LAYER_COUNT.ordinal());




        float tw = w/2- Tiles.TILE_WIDTH*2;
        float ty = h/2- Tiles.TILE_HEIGHT*2;
        for(int i = 0; i<5; i++){
            for(int j = 0; j<5; j++){
                add(Layer.Tile, new Tiles(tw+Tiles.TILE_WIDTH*i, ty+Tiles.TILE_HEIGHT*j,i,j ));
            }
        }

        player = new Player(w/2, h/2);
        add(Layer.player, player);

        int margin = (int) (20*GameView.MULTIPLIER);
        score = new Score(w - margin, margin);
        score.setScore(0);
        add(Layer.ui, score);


        initialized = true;
        return true;
    }

    @Override
    public void update() {
        super.update();

        //collision detect
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        int swipeDistance = 100;
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN :
                clickStartPosX = event.getX();

                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                clickEndPosX = event.getX();
                if(clickEndPosX-clickStartPosX>swipeDistance){
                    //rightMove
                    player.RightMove();
                }
                if(clickEndPosX-clickStartPosX<-swipeDistance){
                    //leftMove
                    player.LeftMove();
                }
                clickStartPosX =0;
                clickEndPosX =0;
                break;
        }
        return  true;
    }
}
