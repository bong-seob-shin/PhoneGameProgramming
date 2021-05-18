package com.ac.kr.kpu.s2016184024.termproject.framework.game;

import android.graphics.Canvas;
import android.view.MotionEvent;

import com.ac.kr.kpu.s2016184024.termproject.framework.iface.GameObject;
import com.ac.kr.kpu.s2016184024.termproject.framework.iface.Recyclable;
import com.ac.kr.kpu.s2016184024.termproject.framework.view.GameView;

import java.util.ArrayList;
import java.util.HashMap;



public class BaseGame {
    public static final int BALL_COUNT = 10;


    //singleton
    static BaseGame instance;


    public static BaseGame get(){
//        if(instance ==null){
//            instance = new BaseGame();
//        }
        return  instance;
    }
    public float frameTime;


    protected BaseGame(){
        instance = this;
    }
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


    public boolean initResources() {
       return false;
    }

    protected void initLayers(int layerCount) {
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
//
//        ArrayList<GameObject> bullets = layers.get(Layer.bullet.ordinal());
//        ArrayList<GameObject> enemies = layers.get(Layer.enemy.ordinal());
//        for (GameObject o1 : enemies){
//            Enemy enemy = (Enemy) o1;
//            boolean collided = false;
//            for(GameObject o2 : bullets){
//                Bullet bullet = (Bullet) o2;
//                if(CollisionHelper.collides(enemy,bullet)){
//                    remove(bullet, false);
//                    remove(enemy, false);
//                    score.addScore(10);
//                    collided = true;
//                    break;
//                }
//            }
//            if(collided){
//                break;
//            }
//        }
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

        return false;
    }

    public void add(int layerIndex, GameObject gameObject) {
        GameView.view.post(new Runnable() {
            @Override
            public void run() {
                ArrayList<GameObject> objects = layers.get(layerIndex);
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

//    public ArrayList<GameObject> objectsAt(MainGame.Layer layer){
//        return layers.get(layer.ordinal());
//    }
}
