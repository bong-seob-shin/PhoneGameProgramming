package com.ac.kr.kpu.s2016184024.termproject;

import android.util.Log;

import androidx.annotation.NonNull;

import com.ac.kr.kpu.s2016184024.termproject.framework.game.BaseGame;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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

    private boolean shieldItem;
    private boolean rangeItem;
    private boolean moveItem;
    private boolean isResultPhase;


    //    UserID,
//    HP,
//    posX,
//    poxY,
//    attackPosX,
//    attackPosY,
//    shieldItem,
//    rangeItem,
//    moveItem,

    public boolean isResultPhase() {
        return isResultPhase;
    }

    public void setResultPhase(boolean resultPhase) {
        isResultPhase = resultPhase;
    }

    public PlayerPacket(){}

    public PlayerPacket(String userId, double HP,double posX,double posY,boolean shieldItem,boolean rangeItem,boolean moveItem, boolean isResultPhase){
        this.UserID =userId;
        this.HP = HP;
        this.posX =posX;
        this.posY =posY;
        this.shieldItem= shieldItem;
        this.rangeItem= rangeItem;
        this.moveItem= moveItem;
        this.isResultPhase = isResultPhase;

    }

    public void SetValue(PlayerPacket p){
        this.UserID =p.UserID;
        this.HP = p.HP;
        this.posX =p.posX;
        this.posY =p.posY;
        this.shieldItem= p.shieldItem;
        this.rangeItem= p.rangeItem;
        this.moveItem= p.moveItem;
        this.isResultPhase = p.isResultPhase;

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

    public void setShieldItem(boolean shieldItem) {
        this.shieldItem = shieldItem;
    }

    public void setRangeItem(boolean rangeItem) {
        this.rangeItem = rangeItem;
    }

    public void setMoveItem(boolean moveItem) {
        this.moveItem = moveItem;
    }



    public void writeNewUser(String index, String userId, double HP,double posX,double posY, boolean shieldItem,boolean rangeItem,boolean moveItem, boolean isResultPhase) {
        PlayerPacket user = new PlayerPacket(userId , HP, posX,  posY, shieldItem, rangeItem, moveItem,isResultPhase);

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


        BaseGame.get().mDatabase.child(index).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                if(dataSnapshot.getValue(PlayerPacket.class) != null){
                    PlayerPacket post = dataSnapshot.getValue(PlayerPacket.class);
                    if(packets.isEmpty())
                        packets.add(post);
                    else
                        packets.set(0,post);


                    Log.d(TAG, "onSuccess: change "+packets.get(0).getUserID());
                } else {
                    Log.d(TAG, "onFail: noData");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("FireBaseData", "loadPost:onCancelled", databaseError.toException());
            }
        });


    }
}
