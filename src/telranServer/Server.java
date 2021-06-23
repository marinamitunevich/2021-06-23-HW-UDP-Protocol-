package telranServer;

import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader("config.props"));

        ExecutorService service = Executors.newFixedThreadPool(50);
        DatagramSocket serverUdp = new DatagramSocket(Integer.valueOf(properties.getProperty("SERVER_PORT")));

        while (true){
            byte [] dataIn = new byte[Integer.valueOf(properties.getProperty("DATA_SIZE"))];
            DatagramPacket paketFromClient = new DatagramPacket(dataIn,dataIn.length);

            serverUdp.receive(paketFromClient);
            System.out.println("I get new Connection");

            service.execute(new SendAnswerToClient(properties,serverUdp,paketFromClient,dataIn));
        }



    }
}
