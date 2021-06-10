package com.ac.kr.kpu.s2016184024.termproject;

import android.util.Log;

import androidx.annotation.NonNull;

import com.ac.kr.kpu.s2016184024.termproject.framework.game.BaseGame;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PlayerPacket {
    private static final String TAG = PlayerPacket.class.getSimpleName();

    public ArrayList<PlayerPacket> packets =new ArrayList<>();

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

    public PlayerPacket(String userId, double HP,double posX,double posY, double attackPosX, double attackPosY,boolean shieldItem,boolean rangeItem,boolean moveItem){
        this.UserID =userId;
        this.HP = HP;
        this.posX =posX;
        this.posY =posY;
        this.attackPosX =attackPosX;
        this.attackPosY= attackPosY;
        this.shieldItem= shieldItem;
        this.rangeItem= rangeItem;
        this.moveItem= moveItem;

    }

    public void SetValue(PlayerPacket p){
        this.UserID =p.UserID;
        this.HP = p.HP;
        this.posX =p.posX;
        this.posY =p.posY;
        this.attackPosX =p.attackPosX;
        this.attackPosY= p.attackPosY;
        this.shieldItem= p.shieldItem;
        this.rangeItem= p.rangeItem;
        this.moveItem= p.moveItem;
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



    public void writeNewUser(String index, String userId, double HP,double posX,double posY, double attackPosX, double attackPosY,boolean shieldItem,boolean rangeItem,boolean moveItem) {
        PlayerPacket user = new PlayerPacket(userId , HP, posX,  posY,  attackPosX, attackPosY, shieldItem, rangeItem, moveItem);

        BaseGame.get().mDatabase.child(index).setValue(user)
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

    public void readUser(String index){


        BaseGame.get().mDatabase.child(index).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                // Get Post object and use the values to update the UI
                if(!task.isSuccessful()){
                    Log.d(TAG, "onFail: noData");
                } else {


                    PlayerPacket post = task.getResult().getValue(PlayerPacket.class);
                    packets.add(post);

                    Log.d(TAG, "onSuccess: change "+packets.get(0).getUserID());
                }
            }


        });


    }
}
