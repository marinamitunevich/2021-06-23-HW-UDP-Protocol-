package telranServer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Properties;

public class SendAnswerToClient implements Runnable {
    private Properties properties;
    private DatagramSocket socket;
    private DatagramPacket packetFromClient;
    private  byte [] dataIn;

    public SendAnswerToClient(Properties properties, DatagramSocket socket, DatagramPacket packetFromClient, byte[] dataIn) {
        this.properties = properties;
        this.socket = socket;
        this.packetFromClient = packetFromClient;
        this.dataIn = dataIn;
    }


    @Override
    public void run() {

        System.out.println("Thread name: " + Thread.currentThread().getName());

        String toClient = "Message from server: I get this from Client: "+ new String(dataIn,0,packetFromClient.getLength());
        byte [] outData = toClient.getBytes();

        DatagramPacket packetToClient = new DatagramPacket(outData,outData.length,packetFromClient.getAddress(),
                packetFromClient.getPort());

        try {
            socket.send(packetToClient);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
