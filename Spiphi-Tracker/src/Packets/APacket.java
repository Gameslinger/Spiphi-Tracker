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
public abstract class APacket {

    public final int type;
    public final char[] data;
    public APacket(int type, char[] data) {
        this.type = type;
        this.data = data;
    }
    
    @Override
    public String toString() {
        return "Packet Type: "+this.type+" Length: "+this.data.length;
    }

}
    
    
