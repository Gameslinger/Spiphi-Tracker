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
public class PingPacket extends Packet{
    public static char[] pingReq = {1};
    public PingPacket() {
        super(1,pingReq);
    }
    
}
