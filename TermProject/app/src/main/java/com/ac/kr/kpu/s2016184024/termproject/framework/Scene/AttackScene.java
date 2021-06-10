package com.ac.kr.kpu.s2016184024.termproject.framework.Scene;

import android.view.MotionEvent;

import com.ac.kr.kpu.s2016184024.termproject.Background;
import com.ac.kr.kpu.s2016184024.termproject.CheckSymbol;
import com.ac.kr.kpu.s2016184024.termproject.CustomButton;
import com.ac.kr.kpu.s2016184024.termproject.MainGame;
import com.ac.kr.kpu.s2016184024.termproject.Pair;
import com.ac.kr.kpu.s2016184024.termproject.PlayerPacket;
import com.ac.kr.kpu.s2016184024.termproject.R;
import com.ac.kr.kpu.s2016184024.termproject.Score;
import com.ac.kr.kpu.s2016184024.termproject.Tiles;
import com.ac.kr.kpu.s2016184024.termproject.framework.game.Scene;
import com.ac.kr.kpu.s2016184024.termproject.framework.iface.GameObject;
import com.ac.kr.kpu.s2016184024.termproject.framework.view.GameView;

public class AttackScene extends Scene {

    private Score score;
    private CheckSymbol symbol;
    private CustomButton selectButton;



    public enum Layer{
        bg, Tile,ui,symbol, LAYER_COUNT
    }

    public static AttackScene scene;
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

        symbol = MainGame.get().my_Symbol;
        add(Layer.symbol,symbol );



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



        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN :

                boolean btsCheck = checkButton(selectButton,event.getX(),event.getY());
                if(btsCheck){
                    selectButton.changeBitmap(R.mipmap.select_btn_clicked);


                   //공격위치 업데이트 후 씬 푸쉬
                }

                    Pair checkPos = checkClickTile(event.getX(),event.getY());
                    if(checkPos.getFirst()>0){
                        symbol.setPos(checkPos.getFirst(),checkPos.getSecond());
                    }

                break;

            case MotionEvent.ACTION_UP:

                btsCheck = selectButton.getIsSelected();
                if(btsCheck){

                    Pair p = MainGame.get().my_player.getPos();
                    Pair sp = symbol.getPos();
                    PlayerPacket pp = new PlayerPacket();
                    pp.writeNewUser("1", "1",3.0,p.getFirst(),p.getSecond(),sp.getFirst(),sp.getSecond(),
                            MainGame.get().my_player.getShieldItem(),MainGame.get().my_player.getRangeItem(),MainGame.get().my_player.getMoveItem());
                    MainGame.get().my_Symbol = symbol;
                    MainGame.get().push(new ResultScene());
                }
                break;


        }
        return  true;
    }
}
