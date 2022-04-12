package com.ac.kr.kpu.s2016184024.termproject.framework.Scene;

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

    public  PacketReader packetReader = new PacketReader();
    private boolean onResult;
    int w = GameView.view.getWidth();
    int h = GameView.view.getHeight();
    public enum Layer{
        Background, PacketReader,Tile,player,ui,fire, LAYER_COUNT
    }


    public void add(Layer layer, GameObject obj){
        add(layer.ordinal(), obj);
    }

    @Override
    public void start(){

        super.start();

        other =new Player(0);
        initLayers(Layer.LAYER_COUNT.ordinal());
        add(Layer.Background, new Background(R.mipmap.background, w/2, h/2,0));
        packetReader = new PacketReader();
        isFinish =false;
        onResult =false;
        float tw = w/2- Tiles.TILE_WIDTH*2;
        float ty = h/2- Tiles.TILE_HEIGHT*2;
        for(int i = 0; i<5; i++){
            for(int j = 0; j<5; j++){
                MainGame.get().tiles[i][j].SetTile(tw+Tiles.TILE_WIDTH*i, ty+Tiles.TILE_HEIGHT*j,i,j);
                if(MainGame.get().playTurns>4){
                    if(i == 0 || i == 4 || j== 0 || j==4)
                        MainGame.get().tiles[i][j].ChangeTile(R.mipmap.hole);
                }
                add(Layer.Tile, MainGame.get().tiles[i][j]);
            }
        }

        add(Layer.PacketReader.ordinal(), packetReader);
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
        if(packetReader.packetRead) {
            if (packetReader.isResultPhase && MainGame.get().my_player.isResultPhase) {
                Pair attackPair = new Pair(MainGame.get().my_Symbol.getPos().getFirst(), MainGame.get().my_Symbol.getPos().getSecond());
                Pair PosPair = new Pair((float) packetReader.posX, (float) packetReader.posY);
                fireEffect.SetPos(attackPair.getFirst(), attackPair.getSecond());
                add(Layer.fire, fireEffect);
                PlayerPacket playerPacket = new PlayerPacket();

                if(MainGame.get().my_player.getRangeItem()) {
                    fireEffect_r.SetPos(attackPair.getFirst() - Tiles.TILE_WIDTH, attackPair.getSecond());
                    fireEffect_l.SetPos(attackPair.getFirst() + Tiles.TILE_WIDTH, attackPair.getSecond());
                    add(Layer.fire,  fireEffect_r );
                    add(Layer.fire,  fireEffect_l);
                }
                if (attackPair.equals(PosPair)) {
                    playerPacket = new PlayerPacket();
                    if (!packetReader.shieldItem) {
                        MainGame.get().my_player.HP++;
                    }
                    Pair p = MainGame.get().my_player.getPos();
                    if (MainGame.get().my_player.id.equals("1")) {
                        playerPacket.writeNewUser("1", "1", MainGame.get().my_player.HP, (double) p.getFirst(), (double) p.getSecond(),
                                MainGame.get().my_player.getShieldItem(), MainGame.get().my_player.getRangeItem(), MainGame.get().my_player.getMoveItem(), true);
                    }
                    if (MainGame.get().my_player.id.equals("2")) {
                        playerPacket.writeNewUser("2", "2", MainGame.get().my_player.HP, (double) p.getFirst(), (double) p.getSecond(),
                                MainGame.get().my_player.getShieldItem(), MainGame.get().my_player.getRangeItem(), MainGame.get().my_player.getMoveItem(), true);
                    }
                }

                if (MainGame.get().my_player.getRangeItem()){MainGame.get().my_player.setRangeItem(false);

                    if(PosPair.equals(new Pair(attackPair.getFirst()-Tiles.TILE_WIDTH, attackPair.getSecond())))
                    {
                        if (!packetReader.shieldItem) {
                            MainGame.get().my_player.HP++;
                        }

                        Pair p = MainGame.get().my_player.getPos();
                        if (MainGame.get().my_player.id.equals("1")) {
                            playerPacket.writeNewUser("1", "1", MainGame.get().my_player.HP, (double) p.getFirst(), (double) p.getSecond(),
                                    MainGame.get().my_player.getShieldItem(), MainGame.get().my_player.getRangeItem(), MainGame.get().my_player.getMoveItem(), true);
                        }
                        if (MainGame.get().my_player.id.equals("2")) {
                            playerPacket.writeNewUser("2", "2", MainGame.get().my_player.HP, (double) p.getFirst(), (double) p.getSecond(),
                                    MainGame.get().my_player.getShieldItem(), MainGame.get().my_player.getRangeItem(), MainGame.get().my_player.getMoveItem(), true);
                        }
                    }

                    if(PosPair.equals(new Pair(attackPair.getFirst()+Tiles.TILE_WIDTH, attackPair.getSecond())))
                    {
                        if (!packetReader.shieldItem) {
                            MainGame.get().my_player.HP++;
                        }

                        Pair p = MainGame.get().my_player.getPos();
                        if (MainGame.get().my_player.id.equals("1")) {
                            playerPacket.writeNewUser("1", "1", MainGame.get().my_player.HP, (double) p.getFirst(), (double) p.getSecond(),
                                    MainGame.get().my_player.getShieldItem(), MainGame.get().my_player.getRangeItem(), MainGame.get().my_player.getMoveItem(), true);
                        }
                        if (MainGame.get().my_player.id.equals("2")) {
                            playerPacket.writeNewUser("2", "2", MainGame.get().my_player.HP, (double) p.getFirst(), (double) p.getSecond(),
                                    MainGame.get().my_player.getShieldItem(), MainGame.get().my_player.getRangeItem(), MainGame.get().my_player.getMoveItem(), true);
                        }
                    }
                }

                if (packetReader.shieldItem) {
                    add(Layer.fire, new GameEffect(PosPair.getFirst(), PosPair.getSecond(), R.mipmap.shield_anim, 4));
                    packetReader.shieldItem = false;
                    playerPacket = new PlayerPacket();
                    if (MainGame.get().my_player.id.equals("1")) {
                        playerPacket.writeNewUser("2", "2", packetReader.HP, (double) PosPair.getFirst(), (double) PosPair.getSecond(),
                                packetReader.shieldItem, packetReader.rangeItem, packetReader.moveItem, packetReader.isResultPhase);
                    }
                    if (MainGame.get().my_player.id.equals("2")) {
                        playerPacket.writeNewUser("1", "1", packetReader.HP, (double) PosPair.getFirst(), (double) PosPair.getSecond(),
                                packetReader.shieldItem, packetReader.rangeItem, packetReader.moveItem, packetReader.isResultPhase);
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


                if(selectButton != null){
                    if (packetReader.isResultPhase && MainGame.get().my_player.isResultPhase&&onResult) {
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

                        BaseGame.get().popAllScene();
                        MainGame.get().push(new LobbyScene());

                    }
                }
                if(selectButton != null) {
                    if (packetReader.isResultPhase && MainGame.get().my_player.isResultPhase&&onResult) {
                        boolean btsCheck = selectButton.getIsSelected();
                        if (btsCheck) {

                            remove(fireEffect);
                            if (packetReader.HP >= 3) {
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

                                MainGame.get().playTurns++;

                                if(MainGame.get().playTurns>4){
                                    MainGame.get().my_player.setMoveCount(2);
                                }
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
