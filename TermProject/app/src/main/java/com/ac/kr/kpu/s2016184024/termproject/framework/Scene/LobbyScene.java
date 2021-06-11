package com.ac.kr.kpu.s2016184024.termproject.framework.Scene;

import android.util.Log;
import android.view.MotionEvent;

import com.ac.kr.kpu.s2016184024.termproject.Background;
import com.ac.kr.kpu.s2016184024.termproject.CheckSymbol;
import com.ac.kr.kpu.s2016184024.termproject.CustomButton;
import com.ac.kr.kpu.s2016184024.termproject.MainGame;
import com.ac.kr.kpu.s2016184024.termproject.Pair;
import com.ac.kr.kpu.s2016184024.termproject.Player;
import com.ac.kr.kpu.s2016184024.termproject.PlayerPacket;
import com.ac.kr.kpu.s2016184024.termproject.R;
import com.ac.kr.kpu.s2016184024.termproject.framework.game.Scene;
import com.ac.kr.kpu.s2016184024.termproject.framework.iface.GameObject;
import com.ac.kr.kpu.s2016184024.termproject.framework.view.GameView;

public class LobbyScene extends Scene {


    private static final String TAG = LobbyScene.class.getSimpleName();
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

        MainGame.get().my_player = new Player();

        MainGame.get().my_Symbol =new CheckSymbol(R.mipmap.check, 100000, 100000);
        MainGame.get().my_Packet = new PlayerPacket();
        MainGame.get().other_Packet = new PlayerPacket();

        int w = GameView.view.getWidth();
        int h = GameView.view.getHeight();

        MainGame.get().s_btn = new CustomButton(R.mipmap.shield_button,w/2-400, h/2-1000,1);
        MainGame.get().r_btn = new CustomButton(R.mipmap.move_button,w/2, h/2-1000,1);
        MainGame.get().m_btn = new CustomButton(R.mipmap.randge_button,w/2+400, h/2-1000,1);
        initLayers(LobbyScene.Layer.LAYER_COUNT.ordinal());
        add(LobbyScene.Layer.bg, new Background(R.mipmap.background, w/2, h/2,0));


        add(LobbyScene.Layer.ui, new Background(R.mipmap.game_title, w/2, h-1700,1));


        p1_Button = new CustomButton(R.mipmap.p1_button, w/2, h-700,0);
        add(LobbyScene.Layer.ui, p1_Button);

        p2_Button = new CustomButton(R.mipmap.p2_button, w/2, h-200,0);
        add(LobbyScene.Layer.ui, p2_Button);

        resetButton = new CustomButton(R.mipmap.reset_button, w/2, h-1200,0);
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
                    MainGame.get().my_Packet.readUser("1");
                    MainGame.get().other_Packet.readUser("2");

                    if(!MainGame.get().my_Packet.packets.isEmpty()){
                       if(MainGame.get().my_Packet.packets.get(0).getUserID().equals( "1") &&MainGame.get().other_Packet.packets.get(0).getUserID().equals("2"))
                       {
                           resetButton.changeBitmap(R.mipmap.resetbutton_clicked);
                           PlayerPacket pp = new PlayerPacket();
                           pp.writeNewUser("1", "0",0,0,0,false,false,false);
                           pp.writeNewUser("2", "0",0,0,0,false,false,false);
                       }
                    }
                }
                break;
            case MotionEvent.ACTION_UP:

                p1_btsCheck = p1_Button.getIsSelected();
                if(p1_btsCheck){
                    PlayerPacket pp = new PlayerPacket();
                    pp.writeNewUser("1", "1",0,0,0,false,false,false);
                    MainGame.get().my_player.setPlayerId("1");
                    Log.d(TAG, "onTouchEvent: "+ MainGame.get().my_player.id);
                    MainGame.get().push(new MoveScene());
                }

                p2_btsCheck = p2_Button.getIsSelected();
                if(p2_btsCheck){
                    PlayerPacket pp = new PlayerPacket();
                    pp.writeNewUser("2", "2",0,0,0,false,false,false);

                    MainGame.get().my_player.setPlayerId("2");

                    MainGame.get().push(new MoveScene());

                }

              break;
        }
        return  true;
    }
}
