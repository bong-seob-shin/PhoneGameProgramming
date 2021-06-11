package com.ac.kr.kpu.s2016184024.termproject;

import com.ac.kr.kpu.s2016184024.termproject.framework.Scene.LobbyScene;
import com.ac.kr.kpu.s2016184024.termproject.framework.game.BaseGame;
import com.ac.kr.kpu.s2016184024.termproject.framework.utils.Sound;

public class MainGame extends BaseGame {


    private static final String TAG = MainGame.class.getSimpleName();
    private boolean initialized;
    public Player my_player;

    public CheckSymbol my_Symbol;
    public PlayerPacket my_Packet;
    public PlayerPacket other_Packet;

    public CustomButton s_btn;
    public CustomButton r_btn;
    public CustomButton m_btn;
    public Tiles [][] tiles;
    public int playTurns;
    public static MainGame get(){
        return (MainGame) instance;
    }

    @Override
    public boolean initResources() {
        if(initialized){
            return false;
        }


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
