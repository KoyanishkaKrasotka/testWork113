package jm.task.core.jdbc.util;


import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {

    public static void main(String[] args) {
        getConnection();
    }

    public static Connection getConnection() {
        Properties properties = new Properties();
        try {
            InputStream inputStream = Util.class.getClassLoader().getResourceAsStream("application.properties");
            properties.load(inputStream);
            String URL = properties.getProperty("db.url");
            String USERNAME = properties.getProperty("db.username");
            String PASSWORD = properties.getProperty("db.password");
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Не подключилось", e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
