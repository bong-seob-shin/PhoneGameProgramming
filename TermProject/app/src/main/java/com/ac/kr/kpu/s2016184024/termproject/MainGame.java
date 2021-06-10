package com.ac.kr.kpu.s2016184024.termproject;

import android.util.Log;
import android.view.MotionEvent;

import com.ac.kr.kpu.s2016184024.termproject.framework.game.BaseGame;
import com.ac.kr.kpu.s2016184024.termproject.framework.iface.GameObject;
import com.ac.kr.kpu.s2016184024.termproject.framework.view.GameView;

public class MainGame extends BaseGame {


    private static final String TAG = MainGame.class.getSimpleName();
    private boolean initialized;
    public Player my_player = new Player();
    public CheckSymbol my_Symbol;

    public static MainGame get(){
        return (MainGame) instance;
    }

    @Override
    public boolean initResources() {
        if(initialized){
            return false;
        }
        my_Symbol =new CheckSymbol(R.mipmap.check, 100000, 100000);

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
