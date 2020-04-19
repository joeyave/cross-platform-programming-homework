package edu.dnu.fpm.pz.core;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Provider {
    public static Connection getConnection() throws SQLException, IOException {
        Properties properties = new Properties();

        try (InputStream in = Provider.class.getClassLoader()
                             .getResourceAsStream("db/database.properties")) {
            properties.load(in);
        }

        String drivers = properties.getProperty("driver");
        if (drivers != null) {
            System.setProperty("driver", drivers);
            try {
                Class.forName(drivers);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        String url = properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");

        return DriverManager.getConnection(url, username, password);
    }
}
