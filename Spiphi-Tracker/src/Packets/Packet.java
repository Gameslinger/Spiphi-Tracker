/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Packets;

/**
 *
 * @author Gabriel.Maxfield
 */
public class Packet {

    public final int type;
    public final char[] data;
    public Packet(int type,char[] data) {
        this.type = type;
        this.data = data;
    }
    public static Packet parse(int type,char[] data){
        
        switch(type){
            case 1:
                return new PingPacket();
            case 2:
                return new PingResponsePacket(data); 
        }
        //Not Good
        return new EmptyPacket();
    }
    public static char[] serialize(Packet packet){
                                            //int-type,int length
        char out[] = new char[packet.data.length+4];
                //Ugly: grab higher byte from int
        out[0] = (char)((packet.type>>8) & 0xFF);
                //Get lower byte of int
        out[1] = (char)(packet.type & 0xFF);
        
        out[2] = (char)((packet.data.length>>8)&0xFF);
        out[3] = (char)(packet.data.length & 0xFF);
        for(int i = 0; i < packet.data.length; i++){
            out[i+4] = packet.data[i];
        }
        return out;
    }
}
