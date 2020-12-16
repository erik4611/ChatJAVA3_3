package server;

import java.sql.*;

public class PreparesForDB {
    private static Connection connection;
    private static PreparedStatement psGetNickname;
    private static PreparedStatement psRegistration;
    private static PreparedStatement psChangeNick;

    public static boolean connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:main.db");
            prepareAllStatements();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static void prepareAllStatements() throws SQLException {
        psGetNickname = connection.prepareStatement("SELECT nickname FROM auto WHERE login = ? AND password = ?;");
        psRegistration = connection.prepareStatement("INSERT INTO auto(login, password, nickname) VALUES (? ,? ,? );");
        psChangeNick = connection.prepareStatement("UPDATE auto SET nickname = ? WHERE nickname = ?;");
    }

    public static String getNicknameByLoginAndPassword(String login, String password) {
        String nickN = null;
        try {
            psGetNickname.setString(1, login);
            psGetNickname.setString(2, password);
            ResultSet rs = psGetNickname.executeQuery();
            if (rs.next()) {
                nickN = rs.getString(1);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nickN;
    }

    public static boolean registration(String login, String password, String nickname) {
        try {
            psRegistration.setString(1, login);
            psRegistration.setString(2, password);
            psRegistration.setString(3, nickname);
            psRegistration.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean changeNick(String oldNickname, String newNickname) {
        try {
            psChangeNick.setString(1, newNickname);
            psChangeNick.setString(2, oldNickname);
            psChangeNick.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static void disconnect() {
        try {
            psRegistration.close();
            psChangeNick.close();
            psGetNickname.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
