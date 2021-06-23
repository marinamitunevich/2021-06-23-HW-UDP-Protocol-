package telranClients;

import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Properties;
import java.util.Scanner;

public class OtherClient {
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader("config.props"));
        InetAddress inetAddress = InetAddress.getByName(properties.getProperty("SERVER_HOST"));

        DatagramSocket clientUdp = new DatagramSocket();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter post to Server: ");
        byte [] outData = scanner.nextLine().getBytes();

        DatagramPacket packetToServer = new DatagramPacket(outData,outData.length,inetAddress,
                Integer.valueOf(properties.getProperty("SERVER_PORT")));

        clientUdp.send(packetToServer);

        byte [] dataIn = new byte[Integer.valueOf(properties.getProperty("DATA_SIZE"))];
        DatagramPacket paketFromServer = new DatagramPacket(dataIn,dataIn.length);

        clientUdp.receive(paketFromServer);
        System.out.println(new String(dataIn,0,paketFromServer.getLength()));
    }
}
