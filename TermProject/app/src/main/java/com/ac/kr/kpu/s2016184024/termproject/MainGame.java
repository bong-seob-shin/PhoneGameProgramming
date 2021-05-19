package com.ac.kr.kpu.s2016184024.termproject;

import android.view.MotionEvent;

import com.ac.kr.kpu.s2016184024.termproject.framework.game.BaseGame;
import com.ac.kr.kpu.s2016184024.termproject.framework.iface.GameObject;
import com.ac.kr.kpu.s2016184024.termproject.framework.view.GameView;

public class MainGame extends BaseGame {



    private Player player;
    private Score score;
    private boolean initialized;

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


        player = new Player(400, h-400);
        add(Layer.player, player);
        //add(Layer.controller, new EnemyGenerator());

        float tw = w/2;
        float ty = h/2;
        add(Layer.Tile, new Tiles(R.mipmap.centerTile,tw, ty ));
        add(Layer.Tile, new Tiles(R.mipmap.centerTile,tw- Tiles.TILE_WIDTH, ty ));
        add(Layer.Tile, new Tiles(R.mipmap.centerTile,tw- Tiles.TILE_WIDTH*2, ty ));
        add(Layer.Tile, new Tiles(R.mipmap.centerTile,tw+ Tiles.TILE_WIDTH, ty ));
        add(Layer.Tile, new Tiles(R.mipmap.centerTile,tw+ Tiles.TILE_WIDTH*2, ty ));

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
        int action = event.getAction();
        if(action == MotionEvent.ACTION_DOWN|| action == MotionEvent.ACTION_MOVE){
            player.moveTo(event.getX(), event.getY());
            return true;
        }

        return  false;
    }
}
