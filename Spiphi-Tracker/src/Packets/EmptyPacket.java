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
public class EmptyPacket extends Packet{
    
    public EmptyPacket() {
        super(0, new char[]{});
    }
    
}
