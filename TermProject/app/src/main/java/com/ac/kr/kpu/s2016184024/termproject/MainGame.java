package com.ac.kr.kpu.s2016184024.termproject;

import com.ac.kr.kpu.s2016184024.termproject.framework.Scene.LobbyScene;
import com.ac.kr.kpu.s2016184024.termproject.framework.game.BaseGame;

public class MainGame extends BaseGame {


    private static final String TAG = MainGame.class.getSimpleName();
    private boolean initialized;
    public Player my_player;

    public CheckSymbol my_Symbol;
    public PlayerPacket my_Packet;
    public PlayerPacket other_Packet;

    public static MainGame get(){
        return (MainGame) instance;
    }

    @Override
    public boolean initResources() {
        if(initialized){
            return false;
        }
        my_player = new Player();

        my_Symbol =new CheckSymbol(R.mipmap.check, 100000, 100000);
        my_Packet = new PlayerPacket();
        other_Packet = new PlayerPacket();

        push(new LobbyScene());



        initialized = true;
        return true;
    }





    @Override
    public void update() {
        super.update();


        //collision detect
    }


}
