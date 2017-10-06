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
public class TestRespPacket extends APacket{
    //Type,Length,1 (constant)
    public static char[] respData = {1};
    
    public TestRespPacket(char[] data) {
        super(2, data);
    }
    public TestRespPacket(){
      super(2,respData);
    }
}
