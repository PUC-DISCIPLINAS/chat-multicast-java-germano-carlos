
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class LULU {
    public static void main(String args[]) {

        DatagramSocket aSocket = null;

        String message;

        try {

            aSocket = new DatagramSocket(6789);

            System.out.println("Servidor: ouvindo porta UDP/6789.");
            byte[] buffer = new byte[1000];

            while (true) {
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(request);
                message = new String(request.getData()).trim();
                System.out.println("Servidor: recebido \'" + message  + "\'.");

                DatagramPacket reply = new DatagramPacket("TESTE RETORNO".getBytes(), request.getLength(), request.getAddress(),
                        request.getPort());
                aSocket.send(reply);
            }
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            if (aSocket != null)
                aSocket.close();
        }
    }
}
