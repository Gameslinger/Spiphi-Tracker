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
public class PingResponsePacket extends Packet{
    //Type,Length,1 (constant)
    public static char[] respData = {1};
    
    public PingResponsePacket(char[] data) {
        super(2, data);
    }
    public PingResponsePacket(){
      super(2,respData);
    }
}
