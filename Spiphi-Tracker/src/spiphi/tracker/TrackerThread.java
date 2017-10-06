/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spiphi.tracker;

import PacketManager.BufferedManager;
import PacketManager.IPacketManager;
import Packets.Test.TestRespPacket;
import Packets.*;
import Packets.IpLookUp.IpLookupPacket;
import Packets.IpLookUp.IpLookupRespPacket;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Gabriel.Maxfield, Bennett.DenBleyker
 */
public class TrackerThread implements Runnable {

    private final Socket socket;
    private static final Map<Integer, String> IPMAP = new HashMap<>();
    private IPacketManager man;
    
    static{
        IPMAP.put(1234, "1.2.3.4");
        IPMAP.put(2222, "2.2.2.2");
        IPMAP.put(666, "6.6.6.6");
    }
    public TrackerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            man = new BufferedManager(this.socket);
            APacket packet;
            while ((packet = man.readPacket())!=null) {
                APacket outPacket = handlePacket(packet);
                man.sendPacket(outPacket);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            man.close();
        }
    }

    private void updateIdToIP(int id, String ip) {
        IPMAP.put(id, ip);
    }
    private String getIpFromId(int id){
        String resp = IPMAP.get(id);
        if(resp == null) resp = "0.0.0.0";
        return resp;
    }

    private APacket handlePacket(APacket packet) {
        switch (packet.type) {
            case 1://Test
                return new TestRespPacket();
            case 3:
                return new IpLookupRespPacket(getIpFromId(((IpLookupPacket)packet).id));
            default:
                return new ErrorPacket();
        }
    }

}
