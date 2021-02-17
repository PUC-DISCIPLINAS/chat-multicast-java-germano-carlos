import com.sun.org.apache.xml.internal.serializer.ToHTMLSAXHandler;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTCP {
    public static void main(String[] args) {
        ServerSocket listenSocket = null;
        int port = 7896;
        try {
            listenSocket = new ServerSocket(port);
            System.out.println("Servidor ouvindo na TCP/" + port);

            //noinspection InfiniteLoopStatement
            while(true)
            {
                Socket clienteSocket = listenSocket.accept();
                new Connection(clienteSocket);
            }
        } catch (IOException e) {
            System.out.println(" Listen Socket: " + e.getMessage());
        } catch (Exception e) {
            System.out.println(" Exception: " + e.getMessage());
        } finally {
            if( listenSocket != null)
            {
                try {
                    listenSocket.close();
                    System.out.println("Servidor liberando porta TCP" + port);
                } catch (IOException e) {
                    /* close falhou */
                }
            }
        }
    }
}

class Connection extends Thread {
    DataInputStream in;
    DataOutputStream out;
    Socket clienteSocket;

    public Connection(Socket aClienteSocket) {
        try {
            clienteSocket = aClienteSocket;
            in = new DataInputStream(clienteSocket.getInputStream());
            out = new DataOutputStream(clienteSocket.getOutputStream());
            this.start();
        }
        catch (IOException e) {
            System.out.println("Conexão" + e.getMessage());
        }
    }

    public void run()
    {
        try{
            String data = in.readUTF();
            System.out.println("Recebido" + data);
            out.writeUTF(data);
        } catch (EOFException e){
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("readline" + e.getMessage());
        }finally {
            try{
                clienteSocket.close();
                System.out.println("Fechando conexão com cliente");
            }
            catch (IOException e)
            {
                /* Fechar conexão falhou */
            }
        }


    }
}
