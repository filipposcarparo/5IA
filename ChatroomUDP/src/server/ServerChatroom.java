package server;

import server.ServerThread;

/**
 *
 * @author Federico Doria
 * @date 20/1/2018
 */
public class ServerChatroom {

    public static void main(String[] args) {
        new ServerThread().start();
        System.out.println("Il server chè gestische la chatroom è online");
    }
}
