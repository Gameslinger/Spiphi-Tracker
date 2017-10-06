/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Packets.Test;

import Packets.APacket;

/**
 *
 * @author Gabriel.Maxfield
 */
public class TestPacket extends APacket{
    public static char[] pingReq = {1};
    public TestPacket() {
        super(1,pingReq);
    }
    
}
