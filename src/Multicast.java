import com.sun.org.apache.xpath.internal.operations.Mult;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class Multicast implements Runnable {
    protected String ip;
    protected int port;

    public Multicast(String ip, int port, boolean startThread) {
        this.ip = ip;
        this.port = port;

        if(startThread)
        {
            Thread t = new Thread(this);
            t.start();
        }
    }

    public void receiveUDPMessage(String ip, int port) throws IOException, IOException {
        byte[] buffer = new byte[1024];
        MulticastSocket socket = new MulticastSocket(port);
        InetAddress group = InetAddress.getByName(ip);
        socket.joinGroup(group);
        while (true) {
            System.out.println("Waiting for multicast message...");
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            String msg = new String(packet.getData(), packet.getOffset(), packet.getLength());
            System.out.println("[Multicast UDP message received] >> " + msg);
            if ("OK".equals(msg)) {
                System.out.println("No more message. Exiting : " + msg);
                break;
            }
        }
        socket.leaveGroup(group);
        socket.close();
    }

    @Override
    public void run() {
        try {
            receiveUDPMessage(this.ip, this.port);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
