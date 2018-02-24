package client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Federico Doria
 * @date 20/1/2018
 */
public class ClientChatroom {

    private static final int SENDER = 4446; //porta di destinazione

    public static void main(String[] args) {
        try {
            Scanner t = new Scanner(System.in);
            System.out.print("Inserisci il tuo nickname: ");
            String nome = (t.nextLine() + ": ").toUpperCase();
            InetAddress address = InetAddress.getByName("127.0.0.1");
            DatagramSocket socket = new DatagramSocket();
            new Listener().start();
            DatagramPacket packet;

            while (true) {
                String invio = nome + t.nextLine();
                byte[] buf = new byte[1024];
                buf = invio.getBytes();
                packet = new DatagramPacket(buf, buf.length, address, SENDER);
                socket.send(packet);
            }
        } catch (IOException ex) {
            Logger.getLogger(ClientChatroom.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

class Listener extends Thread {

    private MulticastSocket socket;
    private final int RECEIVER = 4444; //porta da ascoltare attraverso il socket
    private InetAddress address;

    public Listener() throws IOException {
        socket = new MulticastSocket(RECEIVER);
        address = InetAddress.getByName("224.1.0.1"); //ip per la comunicazione
        socket.joinGroup(address);
    }

    @Override
    public void run() {
        byte[] buf;//buffer per la trasmissione del messaggio
        DatagramPacket packet; //oggetto datagramma
        try {
            while (true) {
                buf = new byte[1024];
                packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength());
                System.out.println(received);
            }
        } catch (IOException ex) {
            Logger.getLogger(Listener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
