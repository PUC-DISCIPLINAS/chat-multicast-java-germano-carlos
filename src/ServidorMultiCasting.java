import java.io.IOException;
import java.net.*;

public class ServidorMultiCasting extends Thread {
    public static void sendUDPMessage(String message, String ipAddress, int port) throws IOException {
        DatagramSocket socket = new DatagramSocket();
        InetAddress group = InetAddress.getByName(ipAddress);
        byte[] msg = message.getBytes();
        DatagramPacket packet = new DatagramPacket(msg, msg.length, group, port);
        socket.send(packet);
        socket.close();
    }
    public static void initialize(String message, String ip, int port ) throws IOException {
        sendUDPMessage(message, ip, port);
    }
}
