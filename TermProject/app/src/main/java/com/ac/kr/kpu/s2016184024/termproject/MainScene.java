package com.ac.kr.kpu.s2016184024.termproject;

import android.view.MotionEvent;

import com.ac.kr.kpu.s2016184024.termproject.framework.game.Scene;
import com.ac.kr.kpu.s2016184024.termproject.framework.iface.GameObject;
import com.ac.kr.kpu.s2016184024.termproject.framework.view.GameView;

public class MainScene extends Scene {
    private Player player;
    private Score score;
    private CheckSymbol symbol;
    private CustomButton selectButton;
    private float clickStartPosX =0;
    private float clickEndPosX =0;
    private float clickStartPosY =0;
    private float clickEndPosY =0;
    private int selectLevel =0;

    public enum Layer{
        bg, Tile,player,ui,symbol,fire, LAYER_COUNT
    }

    public static MainScene scene;
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
        add(Layer.bg, new Background(R.mipmap.background, w/2, h/2));


        float tw = w/2- Tiles.TILE_WIDTH*2;
        float ty = h/2- Tiles.TILE_HEIGHT*2;
        for(int i = 0; i<5; i++){
            for(int j = 0; j<5; j++){
                add(Layer.Tile, new Tiles(tw+Tiles.TILE_WIDTH*i, ty+Tiles.TILE_HEIGHT*j,i,j ));
            }
        }

        symbol = new CheckSymbol(R.mipmap.check, 100000, 100000);
        add(Layer.symbol,symbol );

        player = new Player(w/2, h/2);
        add(Layer.player, player);

        selectButton = new CustomButton(R.mipmap.button, w/2, h-200);
        add(Layer.ui, selectButton);

        score = new Score(w/2+100,  GameView.view.getTop()+100);
        score.setScore(10);
        add(Layer.ui, score);




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
                        return  new Pair(tw+tileW*i, ty+tileH*j);
                    }
                }
            }
        }
        return  new Pair(-1,-1); //false value
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
        if(selectLevel == 2){
            Pair firePos = symbol.getPos();
            add(Layer.fire, new FireEffect(firePos.getFirst(),firePos.getSecond() ));

            remove(symbol);
            selectLevel++;

        }

        int swipeDistance = 100;
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN :
                clickStartPosX = event.getX();
                clickStartPosY = event.getY();
                boolean btsCheck = checkButton(selectButton,event.getX(),event.getY());
                if(btsCheck){
                    selectLevel++;
                }
                if(selectLevel == 1){
                    Pair checkPos = checkClickTile(event.getX(),event.getY());
                    if(checkPos.getFirst()>0){
                        symbol.setPos(checkPos.getFirst(),checkPos.getSecond());
                    }
                }
                break;

            case MotionEvent.ACTION_UP:
                if(selectLevel ==0) {
                    clickEndPosX = event.getX();
                    clickEndPosY = event.getY();
                    float distX = clickEndPosX - clickStartPosX;
                    float distY = clickEndPosY - clickStartPosY;
                    if (Math.abs(distX) > Math.abs(distY)) {
                        if (distX > swipeDistance) {
                            //rightMove
                            player.RightMove();
                        }
                        if (distX < -swipeDistance) {
                            //leftMove
                            player.LeftMove();
                        }
                    } else {

                        if (distY > swipeDistance) {
                            //downMove
                            player.DownMove();

                        }
                        if (distY < -swipeDistance) {
                            //upMove
                            player.UpMove();

                        }
                    }
                    clickStartPosX = 0;
                    clickEndPosX = 0;
                    clickStartPosY = 0;
                    clickEndPosY = 0;
                }
                break;
        }
        return  true;
    }
}
