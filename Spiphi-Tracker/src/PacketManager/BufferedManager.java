/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PacketManager;

import Packets.APacket;
import Packets.ErrorPacket;
import Packets.IpLookUp.IpLookupPacket;
import Packets.IpLookUp.IpLookupRespPacket;
import Packets.Test.TestPacket;
import Packets.Test.TestRespPacket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Owner
 */
public class BufferedManager implements IPacketManager {

    private final BufferedReader in;
    private final PrintWriter out;
    private final Socket socket;
    public BufferedManager(InputStream in, OutputStream out) {
        this.in = new BufferedReader(new InputStreamReader(in));
        this.out = new PrintWriter(out);
        this.socket = null;
    }

    public BufferedManager(Socket socket) throws IOException {
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream());
        this.socket = socket;
    }

    @Override
    public APacket readPacket() throws IOException {
        int type = this.readInt();
        int length = this.readInt();
        if(type == -1 || length == -1) return null;//End of Stream
        char packetArr[] = new char[length];
        in.read(packetArr);
        return parsePacket(type, packetArr);
    }

    private int readInt() throws IOException {
        int num = in.read();
        num <<= 8;
        num |= in.read() & 0xFF;
        return num;
    }

    private void writeIntsToCharArr(char arr[], int offset, int... nums) {
        for (int i = 0; offset < nums.length*2;) {
            arr[offset++] = (char) (nums[i] >> 8);
            arr[offset++] = (char) (nums[i++] & 0xFF);
        }
    }

    @Override
    public void sendPacket(APacket packet) throws IOException {
        out.write(serializePacket(packet));
        out.flush();
    }

    @Override
    public char[] serializePacket(APacket packet) {
        //int-type,int length
        char outArr[] = new char[packet.data.length + 4];
        writeIntsToCharArr(outArr, 0, packet.type, packet.data.length);
        System.arraycopy(packet.data, 0, outArr, 4, packet.data.length);
        return outArr;
    }
    
    @Override
    public APacket parsePacket(int type,char[] arr) {
       switch(type){
           case 1:
               return new TestPacket();
           case 2:
               return new TestRespPacket();
           case 3:
               return new IpLookupPacket(arr);
           case 4:
               return new IpLookupRespPacket(arr);
           default:
               return new ErrorPacket();
       }
    }

    @Override
    public void close(){
        try{
        this.in.close();
        this.out.close();
        if(socket!=null) socket.close();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

}

