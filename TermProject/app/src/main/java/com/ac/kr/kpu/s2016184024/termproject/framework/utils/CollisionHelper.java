package com.ac.kr.kpu.s2016184024.termproject.framework.utils;

import android.graphics.RectF;

import com.ac.kr.kpu.s2016184024.termproject.framework.iface.BoxCollidable;


public class CollisionHelper {
    private static RectF rect1 = new RectF();
    private static RectF rect2 = new RectF();

    public static  boolean collides(BoxCollidable o1, BoxCollidable o2){



        o1.getBoundingRect(rect1);
        o2.getBoundingRect(rect2);

        if(rect1.left > rect2.right) return false;
        if (rect1.top > rect2.bottom) return false;
        if(rect1.right < rect2.left) return  false;
        if(rect1.bottom < rect2.top) return  false;

        return  true;
    }
}
