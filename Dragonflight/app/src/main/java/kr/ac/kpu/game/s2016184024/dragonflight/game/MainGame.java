package kr.ac.kpu.game.s2016184024.dragonflight.game;

import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

import kr.ac.kpu.game.s2016184024.dragonflight.R;
import kr.ac.kpu.game.s2016184024.dragonflight.framework.BoxCollidable;
import kr.ac.kpu.game.s2016184024.dragonflight.framework.GameObject;
import kr.ac.kpu.game.s2016184024.dragonflight.framework.Recyclable;
import kr.ac.kpu.game.s2016184024.dragonflight.ui.view.GameView;
import kr.ac.kpu.game.s2016184024.dragonflight.utils.CollisionHelper;

public class MainGame {
    public static final int BALL_COUNT = 10;


    //singleton
    static MainGame instance;
    private Player player;
    private Score score;

    public static MainGame get(){
        if(instance ==null){
            instance = new MainGame();
        }
        return  instance;
    }
    public float frameTime;

    private boolean initialized;
//    Player player;

    ArrayList<ArrayList<GameObject>> layers;
    private static HashMap<Class, ArrayList<GameObject>> recycleBin = new HashMap<>();

    public void recycle(GameObject object){
        Class clazz = object.getClass();
        ArrayList<GameObject> array = recycleBin.get(clazz);
        if(array ==null){
            array = new ArrayList<>();
            recycleBin.put(clazz,array);
        }
        array.add(object);
    }

    public GameObject get(Class clazz){
        ArrayList<GameObject> array = recycleBin.get(clazz);
        if(array == null||array.isEmpty()) return null;

        return array.remove(0);
    }

    public enum Layer{
        bg1,enemy, bullet, player,bg2,ui, controller,ENEMY_COUNT
    }
    public boolean initResources() {

        if(initialized){
            return false;
        }
        int w = GameView.view.getWidth();
        int h = GameView.view.getHeight();

        initLayers(Layer.ENEMY_COUNT.ordinal());

        player = new Player(w/2,h-300);
        add(Layer.player, player);
        add(Layer.controller, new EnemyGenerator());

        int margin = (int) (20*GameView.MULTIPLIER);
        score = new Score(w - margin, margin);
        score.setScore(0);
        add(Layer.ui, score);

        HorizonTalScrolBackground bg = new HorizonTalScrolBackground(R.mipmap.bg_city, 10);
        add(Layer.bg1, bg);

        HorizonTalScrolBackground clouds = new HorizonTalScrolBackground(R.mipmap.clouds,20);
        add(Layer.bg2, clouds);

        initialized = true;
        return true;
    }

    private void initLayers(int layerCount) {
        layers = new ArrayList<>();
        for(int i = 0; i<layerCount; i++){
            layers.add(new ArrayList<>());
        }
    }

    public void update() {
//        if(!initialized){
//            return;
//        }
        for(ArrayList<GameObject> objects : layers){
            for(GameObject go: objects){
                go.update();
            }
        }

        ArrayList<GameObject> bullets = layers.get(Layer.bullet.ordinal());
        ArrayList<GameObject> enemies = layers.get(Layer.enemy.ordinal());
        for (GameObject o1 : enemies){
            Enemy enemy = (Enemy) o1;
            boolean collided = false;
            for(GameObject o2 : bullets){
                Bullet bullet = (Bullet) o2;
                if(CollisionHelper.collides(enemy,bullet)){
                    remove(bullet, false);
                    remove(enemy, false);
                    score.addScore(10);
                    collided = true;
                    break;
                }
            }
            if(collided){
                break;
            }
        }
//
//
//        }
    }

    public void draw(Canvas canvas) {
//        if(!initialized){
//            return;
//        }
        for(ArrayList<GameObject> objects : layers){
            for(GameObject go: objects){
                go.draw(canvas);

            }

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

    public void add(Layer layer, GameObject gameObject) {
        GameView.view.post(new Runnable() {
            @Override
            public void run() {
                ArrayList<GameObject> objects = layers.get(layer.ordinal());
                objects.add(gameObject);
            }
        });

    }

    public void remove(GameObject gameObject) {
        remove(gameObject, true);
    }
    public void remove(GameObject gameObject, boolean delayed) {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (ArrayList<GameObject> objects: layers) {
                    boolean removed = objects.remove(gameObject);
                    if (removed) {
                        if (gameObject instanceof Recyclable) {
                            ((Recyclable) gameObject).recycle();
                            recycle(gameObject);
                        }
                        //Log.d(TAG, "Removed: " + gameObject);
                        break;
                    }
                }
            }
        };
        if (delayed) {
            GameView.view.post(runnable);
        } else {
            runnable.run();
        }
    }
}
