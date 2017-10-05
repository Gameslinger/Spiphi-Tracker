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
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Gabriel.Maxfield
 */
public class TrackerThread implements Runnable {

    final Socket socket;

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
                case 3: //IpLookup TODO: Moc lookup
                    outPacket = new IpLookupRespPacket("123.231.223.243");
                    
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

}
