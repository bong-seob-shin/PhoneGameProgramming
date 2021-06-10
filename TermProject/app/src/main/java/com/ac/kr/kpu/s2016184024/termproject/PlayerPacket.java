package com.ac.kr.kpu.s2016184024.termproject;

import android.util.Log;

import androidx.annotation.NonNull;

import com.ac.kr.kpu.s2016184024.termproject.framework.game.BaseGame;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class PlayerPacket {
    private static final String TAG = PlayerPacket.class.getSimpleName();

    private String UserID;
    private double HP;
    private double posX;
    private double posY;
    private double attackPosX;
    private double attackPosY;
    private boolean shieldItem;
    private boolean rangeItem;
    private boolean moveItem;

    //    UserID,
//    HP,
//    posX,
//    poxY,
//    attackPosX,
//    attackPosY,
//    shieldItem,
//    rangeItem,
//    moveItem,

    public PlayerPacket(){}

    public PlayerPacket(String userId, double HP,double posX,double poxY, double attackPosX, double attackPosY,boolean shieldItem,boolean rangeItem,boolean moveItem){
        this.UserID =userId;
        this.HP = HP;
        this.posX =posX;
        this.posY =poxY;
        this.attackPosX =attackPosX;
        this.attackPosY= attackPosY;
        this.shieldItem= shieldItem;
        this.rangeItem= rangeItem;
        this.moveItem= moveItem;
    }
    public String getUserID() {
        return UserID;
    }

    public double getHP() {
        return HP;
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    public double getAttackPosX() {
        return attackPosX;
    }

    public double getAttackPosY() {
        return attackPosY;
    }

    public boolean isShieldItem() {
        return shieldItem;
    }

    public boolean isRangeItem() {
        return rangeItem;
    }

    public boolean isMoveItem() {
        return moveItem;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public void setHP(long HP) {
        this.HP = HP;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public void setAttackPosX(double attackPosX) {
        this.attackPosX = attackPosX;
    }

    public void setAttackPosY(double attackPosY) {
        this.attackPosY = attackPosY;
    }

    public void setShieldItem(boolean shieldItem) {
        this.shieldItem = shieldItem;
    }

    public void setRangeItem(boolean rangeItem) {
        this.rangeItem = rangeItem;
    }

    public void setMoveItem(boolean moveItem) {
        this.moveItem = moveItem;
    }



    public void writeNewUser(String userId, double HP,double posX,double poxY, double attackPosX, double attackPosY,boolean shieldItem,boolean rangeItem,boolean moveItem) {
        PlayerPacket user = new PlayerPacket(userId , HP, posX,  poxY,  attackPosX, attackPosY, shieldItem, rangeItem, moveItem);

        BaseGame.get().mDatabase.child(userId).setValue(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Write was successful!
                        Log.d(TAG, "onSuccess: success ");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Write failed
                        Log.d(TAG, "onFail: fail");

                    }
                });

    }


}
