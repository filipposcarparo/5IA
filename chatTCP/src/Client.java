
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import sql.SQLHelper;

/**
 * Il seguente file Client.java gestisce la parte client di una comunicazione
 * attraverso Socket TCP
 *
 * @author Federico Doria
 * @date 19/01/2018
 */
public class Client {

    static BufferedReader in;
    private static PrintWriter out;
    private static BufferedReader stdIn;

    public static void connectToServer() throws IOException {

        String serverAddress = "127.0.0.1"; //ip server
        String userInput;

        Socket socket = new Socket(serverAddress, 9898);
        // in = new BufferedReader(new InputStreamReader(System.in));
        in = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
        out = new PrintWriter(socket.getOutputStream(), true);
        stdIn = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 3; i++) { //parte iniziale
            System.out.println(in.readLine());
        }
        boolean isLogged = false;
        String sread;
        while (!isLogged) {
            sread = in.readLine();
            if (sread.contains("corretti")) {
                isLogged = true;
                System.out.println("Ok, ora per comunicare devi inserire il nome del destinatario e successivamente potrai scrivere il messaggio:");
            } else if (!sread.contains("sbagliati")) {
                System.out.println(sread);
                out.println(stdIn.readLine());
            } else {
                System.out.println(sread);
            }
        }
        new Listener().start();
        while ((userInput = stdIn.readLine()) != null) {
            out.println(userInput);
            //System.out.println("server responds: " + in.readLine());
        }
    }

    public static void main(String[] args) throws Exception {
        Client client = new Client();
        client.connectToServer();
    }

    static class Listener extends Thread {

        public void run() {
            while (true) {
                try {
                    System.out.println(in.readLine());
                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }
}
