import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ClienteUDP {
    public void ClienteUDP() {}
    public String sendMessageUDPtoServerPrincipal(String mensagem){
        // args fornecem a mensagem e o endere�o do servidor.

        int serverPort = 6789;
        String message;
        try (DatagramSocket aSocket = new DatagramSocket()) {
            byte[] m = mensagem.getBytes();
            InetAddress aHost = InetAddress.getByName("localhost");
            DatagramPacket request = new DatagramPacket(m, mensagem.length(), aHost, serverPort);
            aSocket.send(request);

            byte[] buffer = new byte[1000];

            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            aSocket.receive(reply);
            message = new String(reply.getData()).trim();

            return message;
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }

        return "";
    }
}
