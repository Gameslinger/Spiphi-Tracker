/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PacketManager;

import Packets.APacket;
import java.io.IOException;

/**
 *
 * @author Owner
 */
public interface IPacketManager {

    public APacket readPacket() throws IOException;
    
    public char[] serializePacket(APacket packet);
    
    public void sendPacket(APacket packet) throws IOException;

    public APacket parsePacket(int type, char arr[]);
    public void close();
}
