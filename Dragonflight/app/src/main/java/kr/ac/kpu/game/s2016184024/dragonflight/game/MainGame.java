package kr.ac.kpu.game.s2016184024.dragonflight.game;

import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import kr.ac.kpu.game.s2016184024.dragonflight.framework.BoxCollidable;
import kr.ac.kpu.game.s2016184024.dragonflight.framework.GameObject;
import kr.ac.kpu.game.s2016184024.dragonflight.ui.view.GameView;
import kr.ac.kpu.game.s2016184024.dragonflight.utils.CollisionHelper;

public class MainGame {
    public static final int BALL_COUNT = 10;


    //singleton
    static MainGame instance;
    private Player player;

    public static MainGame get(){
        if(instance ==null){
            instance = new MainGame();
        }
        return  instance;
    }
    public float frameTime;

    private boolean initialized;
//    Player player;

    ArrayList<GameObject> objects = new ArrayList<>();

    public boolean initResources() {

        if(initialized){
            return false;
        }
        int w = GameView.view.getWidth();
        int h = GameView.view.getHeight();


        player = new Player(w/2,h-300);
        objects.add(player);

        objects.add(new EnemyGenerator());

        initialized = true;
        return true;
    }

    public void update() {
        if(!initialized){
            return;
        }
        for(GameObject go: objects){
            go.update();

        }
        
        for(GameObject o1 : objects){
            if(!(o1 instanceof  Enemy)){
                continue;
            }
            
            for (GameObject o2 : objects){
                if(!(o2 instanceof Bullet||o2 instanceof Player)){
                    continue;
                }
                
                if(CollisionHelper.collides((BoxCollidable) o1,(BoxCollidable) o2)){
                  //대충 충돌 로그  
                }
            }
        }
    }

    public void draw(Canvas canvas) {
        if(!initialized){
            return;
        }
        for(GameObject go: objects){
            go.draw(canvas);
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if(action == MotionEvent.ACTION_DOWN|| action == MotionEvent.ACTION_MOVE){
            player.moveTo(event.getX(), event.getY());
            return true;
        }
        return false;
    }

    public void add(GameObject gameObject) {
        GameView.view.post(new Runnable() {
            @Override
            public void run() {
                objects.add(gameObject);
            }
        });

    }

    public void remove(GameObject gameObject) {
        GameView.view.post(new Runnable() {
            @Override
            public void run() {
            objects.remove(gameObject);//lateUpdate 같은 느낌을 할 수 있음 update 끝나고 처리되는 함수

            }
        });
    }
}
