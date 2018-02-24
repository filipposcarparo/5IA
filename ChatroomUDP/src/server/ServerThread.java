package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Federico Doria
 * @date 20/1/2018
 */
class ServerThread extends Thread {

    private final int RECEIVER = 4446; //porta destinatario
    private final int SENDER = 4444; //porta mittente

    private DatagramSocket receiver; // in
    private MulticastSocket sender; // out

    public ServerThread() {
        super("MulticastServerThread");
    }

    public void run() {
        try {
            byte[] buf;
            InetAddress group = InetAddress.getByName("224.1.0.1");
            receiver = new DatagramSocket(RECEIVER);
            sender = new MulticastSocket();
            while (true) {
                buf = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                receiver.receive(packet);
                System.out.println(new String(packet.getData(), 0, packet.getLength()));
                packet.setAddress(group);
                packet.setPort(SENDER);
                sender.send(packet);
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
