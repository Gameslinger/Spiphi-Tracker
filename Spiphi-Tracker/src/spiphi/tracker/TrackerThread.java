/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spiphi.tracker;

import Packets.*;
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

    public TrackerThread(Socket socket) {
        System.out.println("Connecting to client");
        try {
            //Set up Streams
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //Begin Communication
            //Type of Packet
            int type = input.read();
            //Length of data
            int dataLen = input.read();
            //Read data
            char data[] = new char[dataLen];
            input.read(data);
            //Parses it
            Packet packet = Packet.parse(type,data);
            Packet outPacket = null;
            switch(packet.type){
                case 1://Ping
                    outPacket = new PingResponsePacket();
            }
            out.write(Packet.serialize(outPacket));
        } catch(IOException ex){
            ex.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (IOException ex) {
                System.out.println("Failed to Close Socket");
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void run() {

    }

}
