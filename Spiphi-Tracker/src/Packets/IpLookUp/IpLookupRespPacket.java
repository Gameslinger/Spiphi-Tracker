/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Packets.IpLookUp;

import Packets.Packet;
import java.util.Arrays;

/**
 *
 * @author Gabriel.Maxfield
 */
public class IpLookupRespPacket extends Packet{
    public final String ip;
    public IpLookupRespPacket(String ip) {
        super(4, new char[4]);
        this.ip = ip;
        String tokens[] = ip.split("[.]");
        for(int i = 0; i < tokens.length; i++){
            this.data[i] = (char) Integer.parseInt(tokens[i]);
        }
    }
    public IpLookupRespPacket(char data[]) {
        super(4, data);
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < data.length; i++){
            str.append((int)data[i]);
            if(i!=data.length-1)str.append('.');
        }
        this.ip = str.toString();
    }
    
    @Override
    public String toString() {
        return super.toString() +" Ip: " + ip;
    }
    
}
