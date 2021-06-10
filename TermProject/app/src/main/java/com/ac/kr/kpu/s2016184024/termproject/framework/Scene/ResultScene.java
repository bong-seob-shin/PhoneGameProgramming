package com.ac.kr.kpu.s2016184024.termproject.framework.Scene;

import android.util.Log;
import android.view.MotionEvent;

import com.ac.kr.kpu.s2016184024.termproject.Background;
import com.ac.kr.kpu.s2016184024.termproject.CustomButton;

import com.ac.kr.kpu.s2016184024.termproject.FireEffect;
import com.ac.kr.kpu.s2016184024.termproject.MainGame;
import com.ac.kr.kpu.s2016184024.termproject.PacketReader;
import com.ac.kr.kpu.s2016184024.termproject.Pair;
import com.ac.kr.kpu.s2016184024.termproject.Player;
import com.ac.kr.kpu.s2016184024.termproject.R;
import com.ac.kr.kpu.s2016184024.termproject.Score;
import com.ac.kr.kpu.s2016184024.termproject.Tiles;
import com.ac.kr.kpu.s2016184024.termproject.framework.game.Scene;
import com.ac.kr.kpu.s2016184024.termproject.framework.iface.GameObject;
import com.ac.kr.kpu.s2016184024.termproject.framework.view.GameView;

public class ResultScene extends Scene {

    private static final String TAG = ResultScene.class.getSimpleName();
    private Score score;
    private Player other;
    private CustomButton selectButton;
    public  PacketReader pr = new PacketReader();


    public enum Layer{
        bg, PacketReader,Tile,player,ui,fire, LAYER_COUNT
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
        other =new Player();
        initLayers(Layer.LAYER_COUNT.ordinal());
        add(Layer.bg, new Background(R.mipmap.background, w/2, h/2,0));


        float tw = w/2- Tiles.TILE_WIDTH*2;
        float ty = h/2- Tiles.TILE_HEIGHT*2;
        for(int i = 0; i<5; i++){
            for(int j = 0; j<5; j++){
                add(Layer.Tile, new Tiles(tw+Tiles.TILE_WIDTH*i, ty+Tiles.TILE_HEIGHT*j,i,j ));
            }
        }


        add(Layer.PacketReader.ordinal(),pr);

    

        if(pr.packetRead){
            Log.d(TAG, "update?: true");
            Player player = MainGame.get().my_player;
            Pair attackPair = new Pair((float)pr.attackPosX, (float) pr.attackPosY);
            Pair PosPair = new Pair((float)pr.posX, (float) pr.posY);

            add(Layer.fire, new FireEffect(attackPair.getFirst(), attackPair.getSecond()));

            other.setPlayerInfo(PosPair.getFirst(), PosPair.getSecond(), R.mipmap.tank_enemy);
            add(ResultScene.Layer.player.ordinal(), other);

        }
        else{
            Log.d(TAG, "update?: true");
            Player player = MainGame.get().my_player;
            Pair attackPair = new Pair((float)pr.attackPosX, (float) pr.attackPosY);
            Pair PosPair = new Pair((float)pr.posX, (float) pr.posY);

            add(Layer.fire, new FireEffect(attackPair.getFirst(), attackPair.getSecond()));

            other.setPlayerInfo(PosPair.getFirst(), PosPair.getSecond(), R.mipmap.tank_enemy);
            add(ResultScene.Layer.player.ordinal(), other);
//            Log.d(TAG, "update?: true");
//            Player player = MainGame.get().my_player;
//            Pair attackPair = new Pair((float)pr.attackPosX, (float) pr.attackPosY);
//            Pair PosPair = new Pair((float)pr.posX, (float) pr.posY);
//            add(Layer.fire, new FireEffect(w/2, h/2));
//
//            other.setPlayerInfo(w/2, h/2, R.mipmap.tank_enemy);
//            add(Layer.player.ordinal(), other);
        }








        selectButton = new CustomButton(R.mipmap.button, w/2, h-200);
        add(Layer.ui, selectButton);

        score = new Score(w/2+100,  GameView.view.getTop()+100);
        score.setScore(10);
        add(Layer.ui, score);




    }


    void drawResult(){
        if(pr.packetRead){
            Log.d(TAG, "update?: true");
            Player player = MainGame.get().my_player;
            Pair attackPair = new Pair((float)pr.attackPosX, (float) pr.attackPosY);
            Pair PosPair = new Pair((float)pr.posX, (float) pr.posY);

            add(Layer.fire, new FireEffect(attackPair.getFirst(), attackPair.getSecond()));

            other.setPlayerInfo(PosPair.getFirst(), PosPair.getSecond(), R.mipmap.tank_enemy);
            add(ResultScene.Layer.player.ordinal(), other);

        }
    }

    public boolean checkButton(CustomButton bts,float x, float y){
        if(pr.packetRead){
            Log.d(TAG, "checkButton: true!!");
            drawResult();
        }
        else{
            Log.d(TAG, "checkButton: false!!");

        }
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



                }

                break;
            case MotionEvent.ACTION_UP:

                btsCheck = selectButton.getIsSelected();
                if(btsCheck){
                    MainGame.get().my_Symbol.setPos(100000,100000);
                    MainGame.get().my_player.setMoveCount(4);
                    MainGame.get().popTwoScene();
                }
                break;

        }
        return  true;
    }
}
