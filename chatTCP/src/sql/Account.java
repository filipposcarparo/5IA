package sql;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

/**
 * Il seguente file Account.java aiuta la comunicazione tra Java e il database
 * SQLITE
 *
 * @author Federico Doria
 * @date 19/01/2018
 */
public class Account implements Serializable {

    private String username;
    private String password;
    private Socket s;
    private ObjectInputStream input;
    private ObjectOutputStream output;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Account(String username, String password, Socket s, ObjectOutputStream output, ObjectInputStream input) {
        this.username = username;
        this.password = password;
        this.s = s;
        this.output = output;
        this.input = input;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Socket getSocket() {
        return s;
    }

    public void setSocket(Socket s, ObjectOutputStream output, ObjectInputStream input) {
        this.s = s;
        this.output = output;
        this.input = input;
    }

    public ObjectInputStream getInput() {
        return input;
    }

    public ObjectOutputStream getOutput() {
        return output;
    }

    @Override
    public String toString() {
        return "Account: " + username;
    }
}
