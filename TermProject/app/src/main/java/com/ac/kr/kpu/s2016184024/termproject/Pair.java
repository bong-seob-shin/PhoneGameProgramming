package com.ac.kr.kpu.s2016184024.termproject;

import com.ac.kr.kpu.s2016184024.termproject.framework.view.GameView;

public class Pair {
    private final float first;
    private final float second;
    private float boundLength = 30;
    public Pair(float first, float second){
        this.first = first;
        this.second = second;
    }

    public float getFirst(){
        return this.first;
    }

    public float getSecond(){
        return  this.second;
    }

    public boolean equals(Pair p){
        if(first-boundLength* GameView.MULTIPLIER>p.getFirst()||first+boundLength* GameView.MULTIPLIER<p.getFirst()){
            return false;
        }
        if(second-boundLength*2.5* GameView.MULTIPLIER>p.getSecond()||second+boundLength*2.5* GameView.MULTIPLIER<p.getSecond()){
            return false;
        }
        return true;
    }
}
