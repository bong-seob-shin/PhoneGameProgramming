package com.ac.kr.kpu.s2016184024.termproject;

import android.util.Log;
import android.view.MotionEvent;

import com.ac.kr.kpu.s2016184024.termproject.framework.game.BaseGame;
import com.ac.kr.kpu.s2016184024.termproject.framework.iface.GameObject;
import com.ac.kr.kpu.s2016184024.termproject.framework.view.GameView;

public class MainGame extends BaseGame {


    private static final String TAG = MainGame.class.getSimpleName();
    private Player player;
    private Score score;
    private CheckSymbol symbol;
    private boolean initialized;
    private float clickStartPosX =0;
    private float clickEndPosX =0;
    private float clickStartPosY =0;
    private float clickEndPosY =0;

    public enum Layer{
        bg, Tile,player,ui,symbol, controller, LAYER_COUNT
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



        add(Layer.bg, new Background(R.mipmap.background, w/2, h/2));


        float tw = w/2- Tiles.TILE_WIDTH*2;
        float ty = h/2- Tiles.TILE_HEIGHT*2;
        for(int i = 0; i<5; i++){
            for(int j = 0; j<5; j++){
                add(Layer.Tile, new Tiles(tw+Tiles.TILE_WIDTH*i, ty+Tiles.TILE_HEIGHT*j,i,j ));
            }
        }

        symbol = new CheckSymbol(R.mipmap.check, w/2, h/2);
        add(Layer.symbol,symbol );

        player = new Player(w/2, h/2);
        add(Layer.player, player);



        initialized = true;
        return true;
    }

    public Pair checkClickTile(float x, float y){
        int w = GameView.view.getWidth();
        int h = GameView.view.getHeight();
        float tileW =Tiles.TILE_WIDTH;
        float tileH = Tiles.TILE_HEIGHT;

        float tw = w/2- Tiles.TILE_WIDTH*2;
        float ty = h/2- Tiles.TILE_HEIGHT*2;

        for(int i = 0; i<5; i++){
            for(int j = 0; j<5; j++){
                if(tw + tileW * i - (tileW / 2) < x && tw + tileW * i + (tileW / 2) > x){
                    if(ty + tileH * j - (tileH / 2) < y && ty + tileH * j + (tileH / 2) > y){
                        Log.d(TAG,tw+tileW+" tw " +ty+tileH +" ty "+x+ " x " + y + " y ");
                        return  new Pair(tw+tileW*i, ty+tileH*j);
                    }
                }
            }
        }
        return  new Pair(-1,-1); //false value
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
                clickStartPosY = event.getY();
                Pair checkPos = checkClickTile(event.getX(),event.getY());
                if(checkPos.getFirst()>0){
                    symbol.setPos(checkPos.getFirst(),checkPos.getSecond());
                }
                break;

            case MotionEvent.ACTION_UP:
                clickEndPosX = event.getX();
                clickEndPosY = event.getY();
                float distX = clickEndPosX-clickStartPosX;
                float distY = clickEndPosY - clickStartPosY;
                if(Math.abs(distX)  > Math.abs(distY)){
                    if(distX>swipeDistance){
                       //rightMove
                      player.RightMove();
                    }
                    if(distX<-swipeDistance){
                      //leftMove
                     player.LeftMove();
                    }
                }
                else {

                    if(distY>swipeDistance){
                        //downMove
                        player.DownMove();

                    }
                    if(distY<-swipeDistance){
                        //upMove
                        player.UpMove();

                    }
                }
                clickStartPosX =0;
                clickEndPosX =0;
                clickStartPosY =0;
                clickEndPosY = 0;

                break;
        }
        return  true;
    }
}
