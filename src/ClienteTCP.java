import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClienteTCP {
    public static void main(String args[]) {

        Socket s = null;

        try{
            int port = 7896;
            s = new Socket(args[1], port);
            DataInputStream in = new DataInputStream(s.getInputStream());
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            out.writeUTF(args[0]);
            String data = in.readUTF();
            System.out.println("Recebido: "+ data);

        } catch (UnknownHostException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (EOFException e) {
            System.out.println("EOF: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("readline: " + e.getMessage());
        } finally {
            if( s != null){
                try{
                    s.close();
                } catch (IOException e)
                {
                    System.out.println("close: " + e.getMessage());
                }
            }
        }
    }
}
