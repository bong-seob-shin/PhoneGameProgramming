package com.ac.kr.kpu.s2016184024.termproject;

import android.graphics.Canvas;
import android.util.Log;

import com.ac.kr.kpu.s2016184024.termproject.framework.Scene.MoveScene;
import com.ac.kr.kpu.s2016184024.termproject.framework.Scene.ResultScene;
import com.ac.kr.kpu.s2016184024.termproject.framework.iface.GameObject;

public class PacketReader implements GameObject {
    private static final String  TAG = PacketReader.class.getSimpleName();
    public PacketReader(){}

    public boolean packetRead =false;

    public String UserID;
    public double HP;
    public double posX;
    public double posY;
  
    public boolean shieldItem;
    public boolean rangeItem;
    public boolean moveItem;

    public void SetValue(PlayerPacket p){
        this.UserID =p.getUserID();
        this.HP = p.getHP();
        this.posX =p.getPosX();
        this.posY =p.getPosY();

        this.shieldItem= p.isShieldItem();
        this.rangeItem= p.isRangeItem();
        this.moveItem= p.isMoveItem();
    }
    @Override
    public void update() {


            if (MainGame.get().my_player.id.equals("1")) {


                MainGame.get().other_Packet.readUser("2");
                if (!MainGame.get().other_Packet.packets.isEmpty()) {

                    if(MainGame.get().other_Packet.packets.get(0) != null) {
                        SetValue(MainGame.get().other_Packet.packets.get(0));
                        packetRead = true;
                    }
                }
            }

            if (MainGame.get().my_player.id.equals("2")) {
                MainGame.get().other_Packet.readUser("1");
                if (!MainGame.get().other_Packet.packets.isEmpty()) {
                    if(MainGame.get().other_Packet.packets.get(0) != null) {
                        SetValue(MainGame.get().other_Packet.packets.get(0));
                        packetRead = true;

                    }
                }
            }


    }

    @Override
    public void draw(Canvas canvas) {

    }
}
