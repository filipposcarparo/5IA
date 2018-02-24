package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Il seguente file SQLHelper.java esegue le operazioni sul database richieste
 *
 * @author Federico Doria
 * @date 19/01/2018
 */
public class SQLHelper {

    private static Connection conn = null;
    private static final String PERCORSO = "Account.db";

    private static synchronized Connection getConnection() {
        if (conn == null) {
            try {
                Class.forName("org.sqlite.JDBC");
                conn = DriverManager.getConnection("jdbc:sqlite:" + PERCORSO);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(SQLHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return conn;
    }

    public static synchronized boolean compareAccount(String u, String p) {
        boolean ris = false;
        String query = "SELECT * FROM ACCOUNT";
        try {
            PreparedStatement c = getConnection().prepareStatement(query);
            ResultSet rs = c.executeQuery();
            while (!ris && rs.next()) {
                if (u.compareTo(rs.getString("Username")) == 0 && p.compareTo(rs.getString("Password")) == 0) {
                    ris = true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQLHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ris;
    }

    /* public static ArrayList <String> showUsers(){
        ArrayList <String> ris=new ArrayList<>();
        String query = "SELECT Username FROM ACCOUNT";
        try {
            PreparedStatement c = getConnection().prepareStatement(query);
            ResultSet rs = c.executeQuery();
            while (rs.next()) {
                ris.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQLHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ris;
    }*/
    public static synchronized boolean existsAccount(String u) {
        boolean ris = false;
        String query = "SELECT * FROM ACCOUNT";
        try {
            PreparedStatement c = getConnection().prepareStatement(query);
            ResultSet rs = c.executeQuery();
            while (!ris && rs.next()) {
                if (u.compareTo(rs.getString("Username")) == 0) {
                    ris = true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQLHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ris;
    }

    public static synchronized void addAccount(String username, String password) {
        String query = "INSERT INTO ACCOUNT (Username, Password) "
                + "VALUES (?,?) ";
        try {
            PreparedStatement ps = getConnection().prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(SQLHelper.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
}
