package com.ac.kr.kpu.s2016184024.termproject.framework.Scene;

import android.util.Log;
import android.view.MotionEvent;

import com.ac.kr.kpu.s2016184024.termproject.Background;
import com.ac.kr.kpu.s2016184024.termproject.CustomButton;
import com.ac.kr.kpu.s2016184024.termproject.MainGame;
import com.ac.kr.kpu.s2016184024.termproject.Pair;
import com.ac.kr.kpu.s2016184024.termproject.Player;
import com.ac.kr.kpu.s2016184024.termproject.PlayerPacket;
import com.ac.kr.kpu.s2016184024.termproject.R;
import com.ac.kr.kpu.s2016184024.termproject.Score;
import com.ac.kr.kpu.s2016184024.termproject.Tiles;
import com.ac.kr.kpu.s2016184024.termproject.framework.game.Scene;
import com.ac.kr.kpu.s2016184024.termproject.framework.iface.GameObject;
import com.ac.kr.kpu.s2016184024.termproject.framework.view.GameView;

public class MoveScene extends Scene {
    private static final String TAG = MoveScene.class.getSimpleName();
    private Player player;
    private Score score;
    private CustomButton selectButton;
    private float clickStartPosX =0;
    private float clickEndPosX =0;
    private float clickStartPosY =0;
    private float clickEndPosY =0;


    public enum Layer{
        bg, Tile,player,ui, LAYER_COUNT
    }

    public static MoveScene scene;
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
        Log.d(TAG, "start plz: "+MainGame.get().playTurns);
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
        player = MainGame.get().my_player;
        player.setMoveCount(4);
        if(MainGame.get().playTurns>4){
            player.setPos(w/2, h/2);
            player.setMoveRange(1);
            player.setMoveCount(2);
        }
        add(Layer.player, player);
        selectButton = new CustomButton(R.mipmap.button, w/2, h-200,0);
        selectButton.changeBitmap(R.mipmap.button);
        add(Layer.ui, selectButton);
        if(!MainGame.get().m_btn.getIsSelected()) {
            add(Layer.ui, MainGame.get().m_btn);
        }
        score = new Score(w/2+100,  GameView.view.getTop()+100);
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
        int swipeDistance = 100;
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN :
                clickStartPosX = event.getX();
                clickStartPosY = event.getY();
                boolean btnCheck = checkButton(selectButton,event.getX(),event.getY());
                if(btnCheck){
                    selectButton.changeBitmap(R.mipmap.select_btn_clicked);
                }
                break;
            case MotionEvent.ACTION_UP:
                clickEndPosX = event.getX();
                clickEndPosY = event.getY();
                float distX = clickEndPosX - clickStartPosX;
                float distY = clickEndPosY - clickStartPosY;
                if(player.getMoveCount()>0){
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
                }
                if(MainGame.get().m_btn !=null) {
                    boolean moveBtnCheck = checkButton(MainGame.get().m_btn, event.getX(), event.getY());
                    if (moveBtnCheck) {
                        player.setMoveCount(player.getMoveCount() + 4);
                        player.setMoveItem(true);
                        MainGame.get().m_btn.disableButton();
                        MainGame.get().m_btn.setIsSelected(true);
                        remove(MainGame.get().m_btn);
                    }
                }
                btnCheck = checkButton(selectButton,event.getX(),event.getY());
                if(btnCheck) {
                    //씬바꾸고 플레이어 정보 업데이트, 패킷 업데이트
                    Pair pair = player.getPos();
                    PlayerPacket playerPacket = new PlayerPacket();
                    if (player.id.equals("1")) {
                        playerPacket.writeNewUser("1", "1", player.HP, pair.getFirst(), pair.getSecond(), player.getShieldItem(), player.getRangeItem(),
                                player.getMoveItem(),false);
                    }
                    if (player.id.equals("2")) {
                        playerPacket.writeNewUser("2", "2", player.HP, pair.getFirst(), pair.getSecond(), player.getShieldItem(), player.getRangeItem(),
                                player.getMoveItem(),false);
                    }
                    MainGame.get().my_player = player;
                    MainGame.get().push(new AttackScene());
                }
                    clickStartPosX = 0;
                    clickEndPosX = 0;
                    clickStartPosY = 0;
                    clickEndPosY = 0;
                    break;
        }
        return  true;
    }
}
