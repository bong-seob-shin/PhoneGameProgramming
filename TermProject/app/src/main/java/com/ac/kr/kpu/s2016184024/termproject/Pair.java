package com.ac.kr.kpu.s2016184024.termproject;

public class Pair {
    private final float first;
    private final float second;

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
        if(this.first == (float) p.first){
            if(this.second == (float)p.second){
                return true;
            }
        }

        return false;
    }
}
