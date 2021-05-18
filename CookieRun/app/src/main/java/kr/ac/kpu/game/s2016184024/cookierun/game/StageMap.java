package kr.ac.kpu.game.s2016184024.cookierun.game;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.Random;

import kr.ac.kpu.game.s2016184024.cookierun.framework.game.BaseGame;
import kr.ac.kpu.game.s2016184024.cookierun.framework.iface.GameObject;
import kr.ac.kpu.game.s2016184024.cookierun.framework.view.GameView;

public class StageMap implements GameObject {
    @Override
    public void update() {
        MainGame game = (MainGame) BaseGame.get();
        ArrayList<GameObject> objects = game.objectsAt(MainGame.Layer.platform);

        float rightMost = 0;
        for (GameObject obj: objects){
             Platform platform = (Platform) obj;
             float right = platform.getRight();
             if(rightMost<right){
                 rightMost = right;
             }
        }
        float vw = GameView.view.getWidth();
        float vh = GameView.view.getHeight();
        if(rightMost<vw){

            float tx = rightMost, ty = vh - Platform.Type.T_2x2.height();
            Platform platform = new Platform(Platform.Type.RANDOM, tx, ty);
            game.add(MainGame.Layer.platform, platform);
            Random r = new Random();
            game.add(MainGame.Layer.item, new Jelly(r.nextInt(60), tx, r.nextInt((int)ty)));
        }
    }

    @Override
    public void draw(Canvas canvas) {

    }
}