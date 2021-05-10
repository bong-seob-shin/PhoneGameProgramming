package kr.ac.kpu.game.s2016184024.dragonflight.game;

import android.graphics.Canvas;
import android.util.Log;

import java.util.Random;

import kr.ac.kpu.game.s2016184024.dragonflight.framework.GameObject;
import kr.ac.kpu.game.s2016184024.dragonflight.ui.view.GameView;

public class EnemyGenerator implements GameObject {

    private static final float INITAL_SPAWN_INTERVAL = 2.0f;
    private static final String TAG = EnemyGenerator.class.getSimpleName();
    private float time;
    private float spawnInterval;
    private int wave;

    public EnemyGenerator(){
        time = 0.0f;
        spawnInterval = INITAL_SPAWN_INTERVAL;
        wave =0;
    }
    @Override
    public void update() {
        MainGame game = MainGame.get();
        time += game.frameTime;
        if(time>= spawnInterval){
            generate();
            time -= spawnInterval;
        }

    }

    private void generate() {
        wave++;
        MainGame game = MainGame.get();

        int tenth = GameView.view.getWidth()/10;
        Random r = new Random();
        for(int i = 1; i<= 9; i += 2){
            int x = tenth *i;
            int y = 0;
            int level = wave/10 - r.nextInt(3);
            if(level<1) level = 1;
            if(level>20) level = 20;

            Enemy enemy = Enemy.get(level , x, y, 700);
            game.add(MainGame.Layer.enemy, enemy);
        }

    }

    @Override
    public void draw(Canvas canvas) {
        //draw none
    }
}
