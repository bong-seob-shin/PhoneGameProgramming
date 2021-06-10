package com.ac.kr.kpu.s2016184024.termproject.framework.Scene;

import android.view.MotionEvent;

import com.ac.kr.kpu.s2016184024.termproject.Background;
import com.ac.kr.kpu.s2016184024.termproject.CustomButton;
import com.ac.kr.kpu.s2016184024.termproject.MainGame;
import com.ac.kr.kpu.s2016184024.termproject.Pair;
import com.ac.kr.kpu.s2016184024.termproject.R;
import com.ac.kr.kpu.s2016184024.termproject.framework.game.Scene;
import com.ac.kr.kpu.s2016184024.termproject.framework.iface.GameObject;
import com.ac.kr.kpu.s2016184024.termproject.framework.view.GameView;

public class LobbyScene extends Scene {




    public static LobbyScene scene;
    public void add(LobbyScene.Layer layer, GameObject obj){
        add(layer.ordinal(), obj);
    }

    private CustomButton p1_Button;
    private CustomButton p2_Button;
    private CustomButton resetButton;


    public enum Layer{
        bg,ui, LAYER_COUNT
    }

    public void start(){
        scene =this;
        super.start();

//        PlayerPacket pp = new PlayerPacket();
//        pp.writeNewUser("2",3.0,0,0,0,0,false,false,false);

        int w = GameView.view.getWidth();
        int h = GameView.view.getHeight();

        initLayers(LobbyScene.Layer.LAYER_COUNT.ordinal());
        add(LobbyScene.Layer.bg, new Background(R.mipmap.background, w/2, h/2,0));


        add(LobbyScene.Layer.ui, new Background(R.mipmap.game_title, w/2, h-1700,1));



        p1_Button = new CustomButton(R.mipmap.p1_button, w/2, h-700);
        add(LobbyScene.Layer.ui, p1_Button);

        p2_Button = new CustomButton(R.mipmap.p2_button, w/2, h-200);
        add(LobbyScene.Layer.ui, p2_Button);

        resetButton = new CustomButton(R.mipmap.reset_button, w/2, h-1200);
        add(LobbyScene.Layer.ui, resetButton);


    }
    public boolean checkButton(CustomButton bts,float x, float y){
        if(bts.getIsSelected()){
            return false;
        }

        Pair btsPos = bts.getPos();

        if(btsPos.getFirst()-CustomButton.TILE_WIDTH/2<x&&btsPos.getFirst()+CustomButton.TILE_WIDTH/2>x){
            if(btsPos.getSecond()-CustomButton.TILE_HEIGHT/2<y&&btsPos.getSecond()+CustomButton.TILE_HEIGHT/2>y){

                return true;
            }
        }

        return  false;
    }
    public boolean onTouchEvent(MotionEvent event) {



        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN :

                boolean p1_btsCheck = checkButton(p1_Button,event.getX(),event.getY());
                if(p1_btsCheck){
                    p1_Button.changeBitmap(R.mipmap.p1_btn_clicked);

                }
                boolean p2_btsCheck = checkButton(p2_Button,event.getX(),event.getY());
                if(p2_btsCheck){
                    p2_Button.changeBitmap(R.mipmap.p2_btn_clicked);

                }
                boolean reset_btsCheck = checkButton(resetButton,event.getX(),event.getY());
                if(reset_btsCheck){
                    resetButton.changeBitmap(R.mipmap.resetbutton_clicked);

                }
                break;
            case MotionEvent.ACTION_UP:

                p1_btsCheck = p1_Button.getIsSelected();
                if(p1_btsCheck){
                    MainGame.get().my_player.setPlayerId("1");
                    MainGame.get().push(new MoveScene());
                }

                p2_btsCheck = p2_Button.getIsSelected();
                if(p2_btsCheck){
                    MainGame.get().my_player.setPlayerId("2");

                    MainGame.get().push(new MoveScene());

                }

              break;
        }
        return  true;
    }
}