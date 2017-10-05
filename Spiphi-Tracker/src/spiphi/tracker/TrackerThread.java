/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spiphi.tracker;

import Packets.Ping.PingResponsePacket;
import Packets.*;
import Packets.IpLookUp.IpLookupRespPacket;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Gabriel.Maxfield, Bennett.DenBleyker
 */
public class TrackerThread implements Runnable {

    private final Socket socket;
    private Map<Integer,String> idToIp = new HashMap(); 

    public TrackerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("Connecting to client");
        try {
            //Set up Streams
            System.out.println("Creating Streams");
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //Begin Communication
            //Type of Packet
            System.out.println("Reading Packet");
            //Parses it
            Packet packet = Packet.parse(input);
            System.out.println("Sending Packet");
            Packet outPacket;
            switch (packet.type) {
                case 1://Ping 1
                    outPacket = new PingResponsePacket();//Type 2
                    break;
                case 3: //IpLookup
                    updateIdToIP();
                    outPacket = new IpLookupRespPacket(idToIp.get(Integer.valueOf(String.valueOf(packet.data))));
                    break;
                default:
                    outPacket = new EmptyPacket();
            }
            System.out.println("Sent Packet "+outPacket);
            out.write(Packet.serialize(outPacket));
            out.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException ex) {
                System.out.println("Failed to Close Socket");
                ex.printStackTrace();
            }
        }
    }
    
    private void updateIdToIP() {
        String idtoip = "1:127.0.0.1\n1234:123.231.223.243";
        for (String s : idtoip.split("\n")) {
            idToIp.put(Integer.valueOf(s.split(":")[0]), s.split(":")[1]);
        }
    }

}
