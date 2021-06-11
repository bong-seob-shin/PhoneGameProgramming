package com.ac.kr.kpu.s2016184024.termproject.framework.Scene;

import android.util.Log;
import android.view.MotionEvent;

import com.ac.kr.kpu.s2016184024.termproject.Background;
import com.ac.kr.kpu.s2016184024.termproject.CustomButton;

import com.ac.kr.kpu.s2016184024.termproject.GameEffect;
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
    private boolean isFinish;
    private GameEffect fireEffect;
    private GameEffect fireEffect_r;
    private GameEffect fireEffect_l;

    public  PacketReader pr = new PacketReader();
    private boolean onResult;
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
        pr = new PacketReader();
        isFinish =false;
        onResult =false;
        float tw = w/2- Tiles.TILE_WIDTH*2;
        float ty = h/2- Tiles.TILE_HEIGHT*2;
        for(int i = 0; i<5; i++){
            for(int j = 0; j<5; j++){
                add(Layer.Tile, new Tiles(tw+Tiles.TILE_WIDTH*i, ty+Tiles.TILE_HEIGHT*j,i,j ));
            }
        }

        add(Layer.PacketReader.ordinal(),pr);


        MainGame.get().my_player.isResultPhase =true;

        fireEffect = new GameEffect(1000000, 100000000,R.mipmap.fireshot,8);
        fireEffect_l = new GameEffect(1000000, 100000000,R.mipmap.fireshot,8);
        fireEffect_r = new GameEffect(1000000, 100000000,R.mipmap.fireshot,8);


        resultButton = new CustomButton(R.mipmap.result_button, w/2-300, h-200,0);
        add(Layer.ui, resultButton);
        selectButton = new CustomButton(R.mipmap.button, w/2+300, h-200,0);
        add(Layer.ui, selectButton);
        score = new Score(w/2+100,  GameView.view.getTop()+100);

        add(Layer.ui, score);




    }


    void drawResult(){
        if(pr.packetRead) {

            if (pr.isResultPhase && MainGame.get().my_player.isResultPhase) {

                Pair attackPair = new Pair(MainGame.get().my_Symbol.getPos().getFirst(), MainGame.get().my_Symbol.getPos().getSecond());
                Pair PosPair = new Pair((float) pr.posX, (float) pr.posY);

                fireEffect.SetPos(attackPair.getFirst(), attackPair.getSecond());

                add(Layer.fire, fireEffect);
                if(pr.rangeItem) {
                    fireEffect_r.SetPos(attackPair.getFirst() - Tiles.TILE_WIDTH, attackPair.getSecond());
                    fireEffect_l.SetPos(attackPair.getFirst() + Tiles.TILE_WIDTH, attackPair.getSecond());
                    add(Layer.fire,  fireEffect_r );
                    add(Layer.fire,  fireEffect_l);
                }
                PlayerPacket pp = new PlayerPacket();

                if (MainGame.get().my_player.id.equals("1")) {
                    pp.writeNewUser("2", "2", pr.HP, (double) PosPair.getFirst(), (double) PosPair.getSecond(),
                            pr.shieldItem, pr.rangeItem, pr.moveItem, true);
                }
                if (MainGame.get().my_player.id.equals("2")) {
                    pp.writeNewUser("1", "1", pr.HP, (double) PosPair.getFirst(), (double) PosPair.getSecond(),
                            pr.shieldItem, pr.rangeItem, pr.moveItem, true);
                }


                if (attackPair.equals(PosPair)) {
                    pp = new PlayerPacket();


                    if (!pr.shieldItem) {
                        MainGame.get().my_player.HP++;
                    }

                    Pair p = MainGame.get().my_player.getPos();
                    if (MainGame.get().my_player.id.equals("1")) {
                        pp.writeNewUser("1", "1", MainGame.get().my_player.HP, (double) p.getFirst(), (double) p.getSecond(),
                                MainGame.get().my_player.getShieldItem(), MainGame.get().my_player.getRangeItem(), MainGame.get().my_player.getMoveItem(), true);
                    }
                    if (MainGame.get().my_player.id.equals("2")) {
                        pp.writeNewUser("2", "2", MainGame.get().my_player.HP, (double) p.getFirst(), (double) p.getSecond(),
                                MainGame.get().my_player.getShieldItem(), MainGame.get().my_player.getRangeItem(), MainGame.get().my_player.getMoveItem(), true);
                    }

                }

                if (pr.rangeItem){
                    if(PosPair.equals(new Pair(attackPair.getFirst()-Tiles.TILE_WIDTH, attackPair.getSecond())))
                    {
                        if (!pr.shieldItem) {
                            MainGame.get().my_player.HP++;
                        }

                        Pair p = MainGame.get().my_player.getPos();
                        if (MainGame.get().my_player.id.equals("1")) {
                            pp.writeNewUser("1", "1", MainGame.get().my_player.HP, (double) p.getFirst(), (double) p.getSecond(),
                                    MainGame.get().my_player.getShieldItem(), MainGame.get().my_player.getRangeItem(), MainGame.get().my_player.getMoveItem(), true);
                        }
                        if (MainGame.get().my_player.id.equals("2")) {
                            pp.writeNewUser("2", "2", MainGame.get().my_player.HP, (double) p.getFirst(), (double) p.getSecond(),
                                    MainGame.get().my_player.getShieldItem(), MainGame.get().my_player.getRangeItem(), MainGame.get().my_player.getMoveItem(), true);
                        }
                    }

                    if(PosPair.equals(new Pair(attackPair.getFirst()+Tiles.TILE_WIDTH, attackPair.getSecond())))
                    {
                        if (!pr.shieldItem) {
                            MainGame.get().my_player.HP++;
                        }

                        Pair p = MainGame.get().my_player.getPos();
                        if (MainGame.get().my_player.id.equals("1")) {
                            pp.writeNewUser("1", "1", MainGame.get().my_player.HP, (double) p.getFirst(), (double) p.getSecond(),
                                    MainGame.get().my_player.getShieldItem(), MainGame.get().my_player.getRangeItem(), MainGame.get().my_player.getMoveItem(), true);
                        }
                        if (MainGame.get().my_player.id.equals("2")) {
                            pp.writeNewUser("2", "2", MainGame.get().my_player.HP, (double) p.getFirst(), (double) p.getSecond(),
                                    MainGame.get().my_player.getShieldItem(), MainGame.get().my_player.getRangeItem(), MainGame.get().my_player.getMoveItem(), true);
                        }
                    }
                }

                if (pr.shieldItem) {
                    add(Layer.fire, new GameEffect(PosPair.getFirst(), PosPair.getSecond(), R.mipmap.shield_anim, 4));
                    pr.shieldItem = false;
                    pp = new PlayerPacket();
                    if (MainGame.get().my_player.id.equals("1")) {
                        pp.writeNewUser("2", "2", pr.HP, (double) PosPair.getFirst(), (double) PosPair.getSecond(),
                                pr.shieldItem, pr.rangeItem, pr.moveItem, pr.isResultPhase);
                    }
                    if (MainGame.get().my_player.id.equals("2")) {
                        pp.writeNewUser("1", "1", pr.HP, (double) PosPair.getFirst(), (double) PosPair.getSecond(),
                                pr.shieldItem, pr.rangeItem, pr.moveItem, pr.isResultPhase);
                    }
                }
                other.setPlayerInfo(PosPair.getFirst(), PosPair.getSecond(), R.mipmap.tank_enemy);

                add(Layer.player.ordinal(), other);
                score.setScore((int) MainGame.get().my_player.HP);


                if (MainGame.get().my_player.getShieldItem()) {
                    MainGame.get().my_player.setShieldItem(false);
                }
                resultButton.setIsSelected(true);
                onResult =true;
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
                    if (pr.isResultPhase && MainGame.get().my_player.isResultPhase&&onResult) {
                        boolean btsCheck = checkButton(selectButton, event.getX(), event.getY());
                        if (btsCheck) {

                            selectButton.changeBitmap(R.mipmap.select_btn_clicked);

                        }
                    }
                }

                break;
            case MotionEvent.ACTION_UP:

                if(resultButton != null){
                    if(!resultButton.getIsSelected()) {
                        boolean btsCheck = checkButton(resultButton, event.getX(), event.getY());
                        if (btsCheck) {

                            drawResult();

                        }
                    }
                }

                if(loseButton != null){
                    boolean btsCheck = checkButton(loseButton,event.getX(),event.getY());
                    if(btsCheck){

                        BaseGame.get().popThreeScene();
                        MainGame.get().push(new LobbyScene());

                    }
                }
                if(selectButton != null) {
                    if (pr.isResultPhase && MainGame.get().my_player.isResultPhase&&onResult) {
                        boolean btsCheck = selectButton.getIsSelected();
                        if (btsCheck) {

                            remove(fireEffect);
                            if (pr.HP >= 3) {
                                loseButton = new CustomButton(R.mipmap.lose, w / 2, h / 2 + 300, 0);
                                add(Layer.ui, loseButton);
                                isFinish = true;
                            }
                            if (MainGame.get().my_player.HP >= 3) {
                                loseButton = new CustomButton(R.mipmap.win, w / 2, h / 2 + 300, 0);
                                add(Layer.ui, loseButton);
                                isFinish = true;
                            }
                            if (!isFinish) {
                                MainGame.get().my_Symbol.setPos(100000, 100000);
                                MainGame.get().my_player.setMoveCount(4);



                                MainGame.get().popTwoScene();
                            }
                        }
                    }
                }
                else{
                    selectButton.changeBitmap(R.mipmap.button);
                }
                break;

        }
        return  true;
    }
}
