/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Packets.IpLookUp;

import Packets.APacket;

/**
 *
 * @author Gabriel.Maxfield
 */
public class IpLookupPacket extends APacket {
    public final int id;
    public IpLookupPacket(int id) {
        super(3, new char[]{(char) ((id >> 8) & 0xFF), (char) (id & 0xFF)});
        this.id = id;
    }
    public IpLookupPacket(char[] data) {
        super(3, data);
        this.id = (data[0]<<8) | data[1];     
    }

}
