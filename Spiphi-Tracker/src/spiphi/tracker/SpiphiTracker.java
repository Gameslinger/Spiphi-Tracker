/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spiphi.tracker;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Gabriel.Maxfield
 */
public class SpiphiTracker {

    static int serverListenerSocket = 4198;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ServerSocket listener = null;
        try {
            listener = new ServerSocket(serverListenerSocket);
        } catch (IOException ex) {
            System.out.println("Could not bind to port: " + serverListenerSocket);
        }
        System.out.println("Starting Server");
        while (true) {
            try {
                Socket socket = listener.accept();
                TrackerThread tracker = new TrackerThread(socket);
                tracker.run();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
