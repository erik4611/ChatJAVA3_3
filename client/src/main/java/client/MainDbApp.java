package client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MainDbApp {

    private static Connection connection;
    private static Statement stmt;

    public static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:main.db");
        stmt = connection.createStatement();

    }

    public static void main(String[] args) {
        try {
            connect();
            stmt.executeUpdate("INSERT INTO auto (login, password) VALUES('Bob', 'qwerty');");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    private static void disconnect() {
        try {
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
