
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import sql.SQLHelper;
import static sql.SQLHelper.existsAccount;

/**
 * Il seguente file Server.java gestisce la parte server di una comunicazione
 * attraverso Socket TCP
 *
 * @author Federico Doria
 * @date 19/01/2018
 */
public class Server {

    static HashMap<String, PrintWriter> clientsList;

    public static void main(String[] args) throws Exception {
        clientsList = new HashMap<>();
        System.out.println("Il gestore della chat è online.");
        int clientNumber = 0;
        ServerSocket listener = new ServerSocket(9898);
        try {
            while (true) {
                // crea il thread e lo lancia
                new Gestore(listener.accept(), clientNumber++).start();
            }
        } finally {
            listener.close();
        }
    }

    private static class Gestore extends Thread {

        private Socket socket;
        private int clientNumber;
        private String userid = "admin", passwordid = "password";

        public Gestore(Socket socket, int clientNumber) {
            this.socket = socket;
            this.clientNumber = clientNumber;
            myLog("Nuova connessione con l'utente numero #" + clientNumber + " a " + socket);

        }

        public void run() {
            try {

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                boolean isReg = false, isLogged = false;
                out.println("Benvenuto sei l'utente numero #" + clientNumber + ".");
                out.println("Inizia a digitare\n");
                String u = "", p = "", w;
                while (!isLogged) {
                    out.println("Inserisci il nome utente:");
                    u = in.readLine();
                    out.println("Inserisci la password:");
                    p = in.readLine();
                    out.println("Inserisci a se vuoi accedere, r se vuoi registrarti");
                    w = in.readLine();
                    if (w.compareTo("a") == 0) {
                        if (SQLHelper.compareAccount(u, p)) {
                            out.println("dati corretti");
                            isLogged = true;

                        } else {
                            out.println("dati sbagliati");
                        }
                    } else if (w.compareTo("r") == 0) {
                        if (!SQLHelper.existsAccount(u)) {
                            SQLHelper.addAccount(u, p);
                            out.println("dati corretti");
                            isLogged = true;
                        } else {
                            out.println("dati sbagliati");
                        }
                    }
                }
                userid = u.trim();
                passwordid = p;
                clientsList.put(userid, out);
                while (true) {
                    String utente = in.readLine();
                    //   out.println("Utenti ->"+SQLHelper.showUsers());
                    utente = utente.trim();
                    String messaggio = in.readLine();
                    if (utente == null || messaggio.equals(".")) {
                        out.println("Connessione chiusa per l'utente numero #" + clientNumber + ".");
                        socket.close();
                        break;
                    }
                    if (!clientsList.containsKey(utente)) {
                        out = clientsList.get(userid);
                        out.println("Utente non registrato o non raggiungibile");
                    } else {
                        out = clientsList.get(utente);
                        out.println(messaggio.toUpperCase());
                    }
                }
            } catch (IOException e) {
                myLog("Errore nell'utente numero # " + clientNumber + ": " + e);
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    // log("Couldn't close a socket, what's going on?");
                }
                myLog("Connessione con l'utente numero# " + clientNumber + " chiusa");
            }
        }

        private void myLog(String message) {
            System.out.println(message);
        }
    }
}
