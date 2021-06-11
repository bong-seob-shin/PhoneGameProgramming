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
import com.ac.kr.kpu.s2016184024.termproject.PlayerPacket;
import com.ac.kr.kpu.s2016184024.termproject.R;
import com.ac.kr.kpu.s2016184024.termproject.Score;
import com.ac.kr.kpu.s2016184024.termproject.Tiles;
import com.ac.kr.kpu.s2016184024.termproject.framework.game.BaseGame;
import com.ac.kr.kpu.s2016184024.termproject.framework.game.Scene;
import com.ac.kr.kpu.s2016184024.termproject.framework.iface.GameObject;
import com.ac.kr.kpu.s2016184024.termproject.framework.view.GameView;

public class ResultScene extends Scene {

    private static final String TAG = ResultScene.class.getSimpleName();
    private Score score;
    private Player other;
    private CustomButton selectButton;
    private CustomButton resultButton;
    private CustomButton loseButton;

    public  PacketReader pr = new PacketReader();

    int w = GameView.view.getWidth();
    int h = GameView.view.getHeight();
    public enum Layer{
        bg, PacketReader,Tile,player,ui,fire, LAYER_COUNT
    }


    public void add(Layer layer, GameObject obj){
        add(layer.ordinal(), obj);
    }

    @Override
    public void start(){

        super.start();

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

    



        resultButton = new CustomButton(R.mipmap.result_button, w/2-300, h-200);
        add(Layer.ui, resultButton);
        selectButton = new CustomButton(R.mipmap.button, w/2+300, h-200);
        add(Layer.ui, selectButton);
        score = new Score(w/2+100,  GameView.view.getTop()+100);

        add(Layer.ui, score);




    }


    void drawResult(){
        if(pr.packetRead){

            Pair attackPair = new Pair(MainGame.get().my_Symbol.getPos().getFirst(), MainGame.get().my_Symbol.getPos().getSecond());
            Pair PosPair = new Pair((float)pr.posX, (float) pr.posY);

            add(Layer.fire, new FireEffect(attackPair.getFirst(), attackPair.getSecond()));
            Log.d(TAG, "drawResult: "+pr.posX+"   "+pr.posY);
            Log.d(TAG, "drawResult: "+attackPair.getFirst()+"   "+attackPair.getSecond());
            if(attackPair.equals(PosPair)){
                Log.d(TAG, "drawResult: nonoonononoo");

                MainGame.get().my_player.HP++;
                Pair p = MainGame.get().my_player.getPos();
                PlayerPacket pp = new PlayerPacket();
                if(MainGame.get().my_player.id.equals("1")){
                    pp.writeNewUser("1", "1",MainGame.get().my_player.HP, (double)p.getFirst(), (double)p.getSecond(),
                            MainGame.get().my_player.getShieldItem(),MainGame.get().my_player.getRangeItem(),MainGame.get().my_player.getMoveItem());
                }
                if(MainGame.get().my_player.id.equals("2")) {
                    pp.writeNewUser("2", "2", MainGame.get().my_player.HP, (double) p.getFirst(), (double) p.getSecond(),
                            MainGame.get().my_player.getShieldItem(), MainGame.get().my_player.getRangeItem(), MainGame.get().my_player.getMoveItem());
                }

            }
            other.setPlayerInfo(PosPair.getFirst(), PosPair.getSecond(), R.mipmap.tank_enemy);
            add(Layer.player.ordinal(), other);
            score.setScore((int)pr.HP);

            if( pr.HP>=3){
                Log.d(TAG, "drawResult: loslsoeloselsoelosel");
                loseButton = new CustomButton(R.mipmap.lose, w/2, h/2+300);
                add(Layer.ui, loseButton);
            }
            if(MainGame.get().my_player.HP>= 3){
                loseButton = new CustomButton(R.mipmap.win, w/2, h/2+300);
                add(Layer.ui, loseButton);
            }

        }
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

                Log.d(TAG, "onTouchEvent: "+pr.packetRead);
                if(selectButton != null){
                    boolean btsCheck = checkButton(selectButton,event.getX(),event.getY());
                    if(btsCheck){

                        selectButton.changeBitmap(R.mipmap.select_btn_clicked);

                    }

                }

                break;
            case MotionEvent.ACTION_UP:

                if(resultButton != null){
                    boolean btsCheck = checkButton(resultButton,event.getX(),event.getY());
                    if(btsCheck){

                        drawResult();

                    }
                }

                if(loseButton != null){
                    boolean btsCheck = checkButton(loseButton,event.getX(),event.getY());
                    if(btsCheck){

                        BaseGame.get().popThreeScene();

                    }
                }
                if(selectButton != null){
                    boolean btsCheck = selectButton.getIsSelected();
                    if(btsCheck){
                        MainGame.get().my_Symbol.setPos(100000,100000);
                        MainGame.get().my_player.setMoveCount(4);
                        remove(pr);
                        MainGame.get().popTwoScene();
                    }
                }
                break;

        }
        return  true;
    }
}
