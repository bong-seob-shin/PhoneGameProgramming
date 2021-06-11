package com.ac.kr.kpu.s2016184024.termproject.framework.game;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

import com.ac.kr.kpu.s2016184024.termproject.framework.iface.GameObject;
import com.ac.kr.kpu.s2016184024.termproject.framework.iface.Recyclable;
import com.ac.kr.kpu.s2016184024.termproject.framework.view.GameView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;



public class BaseGame {
    public static final int BALL_COUNT = 10;

    private static final String TAG = BaseGame.class.getSimpleName();
    //singleton
    protected static BaseGame instance;

    public DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    public static BaseGame get(){

        return  instance;
    }
    public float frameTime;


    protected BaseGame(){
        instance = this;
    }
//    Player player;

    //ArrayList<ArrayList<GameObject>> layers;
    private static HashMap<Class, ArrayList<GameObject>> recycleBin = new HashMap<>();

    ArrayList<Scene> sceneStack = new ArrayList<>();
    public Scene getTopScene() {
        int lastIndex = sceneStack.size() - 1;
        if (lastIndex < 0) return null;
        return sceneStack.get(lastIndex);
    }
    public void start(Scene scene) {
        int lastIndex = sceneStack.size() - 1;
        if (lastIndex >= 0) {
            Scene top = sceneStack.remove(lastIndex);
            Log.d(TAG, "Ending(in start): " + top);
            top.end();
            sceneStack.set(lastIndex, scene);
        } else {
            sceneStack.add(scene);
        }
        Log.d(TAG, "Starting(in start): " + scene);
        scene.start();
    }
    public void push(Scene scene) {
        int lastIndex = sceneStack.size() - 1;
        if (lastIndex >= 0) {
            Scene top = sceneStack.get(lastIndex);
            Log.d(TAG, "Pausing: " + top);
            top.pause();
        }
        sceneStack.add(scene);
        Log.d(TAG, "Starting(in push): " + scene);
        scene.start();
    }
    public void popScene() {
        int lastIndex = sceneStack.size() - 1;
        if (lastIndex >= 0) {
            Scene top = sceneStack.remove(lastIndex);
            Log.d(TAG, "Ending(in pop): " + top);
            top.end();
        }
        lastIndex--;
        if (lastIndex >= 0) {
            Scene top = sceneStack.get(lastIndex);
            Log.d(TAG, "Resuming: " + top);
            top.resume();
        } else {
            Log.e(TAG, "should end app in popScene()");
        }
    }

    public void popTwoScene() {
        int lastIndex = sceneStack.size() - 1;
        if (lastIndex >= 0) {
            Scene top = sceneStack.remove(lastIndex);
            Log.d(TAG, "Ending(in pop): " + top);
            top.end();
        }
        lastIndex--;

        if (lastIndex >= 0) {
            Scene top = sceneStack.remove(lastIndex);
            Log.d(TAG, "Ending(in pop): " + top);
            top.end();
        }
        lastIndex--;
        if (lastIndex >= 0) {
            Scene top = sceneStack.get(lastIndex);
            Log.d(TAG, "Resuming: " + top);
            top.resume();
        } else {
            Log.e(TAG, "should end app in popScene()");
        }
    }

    public void popThreeScene() {
        int lastIndex = sceneStack.size() - 1;
        if (lastIndex >= 0) {
            Scene top = sceneStack.remove(lastIndex);
            Log.d(TAG, "Ending(in pop): " + top);
            top.end();
        }
        lastIndex--;

        if (lastIndex >= 0) {
            Scene top = sceneStack.remove(lastIndex);
            Log.d(TAG, "Ending(in pop): " + top);
            top.end();
        }
        lastIndex--;
        if (lastIndex >= 0) {
            Scene top = sceneStack.remove(lastIndex);
            Log.d(TAG, "Ending(in pop): " + top);
            top.end();
        }
        lastIndex--;
        if (lastIndex >= 0) {
            Scene top = sceneStack.get(lastIndex);
            Log.d(TAG, "Resuming: " + top);
            top.resume();
        } else {
            Log.e(TAG, "should end app in popScene()");
        }
    }

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


    public void update() {
        ArrayList<ArrayList<GameObject>> layers = getTopScene().getLayers();
        for(ArrayList<GameObject> objects : layers){
            for(GameObject go: objects){
                go.update();
            }
        }
//
//
    }

    public void draw(Canvas canvas) {
//        if(!initialized){
//            return;
//        }
        ArrayList<ArrayList<GameObject>> layers = getTopScene().getLayers();
        for(ArrayList<GameObject> objects : layers){
            for(GameObject go: objects){
                go.draw(canvas);

            }

        }


    }

    public boolean onTouchEvent(MotionEvent event) {
        return getTopScene().onTouchEvent(event);

    }
}
