package edu.dnu.fpm.pz.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

public class ServiceProviderConfig {
    public static Connection getConnection() throws SQLException, IOException {
        Properties properties = new Properties();

        try (InputStream in =
                     ServiceProviderConfig
                             .class
                             .getClassLoader()
                             .getResourceAsStream("src\\main\\resources\\db\\database.properties")) {
            properties.load(in);
        }

        String drivers = properties.getProperty("driver");
        if (Objects.nonNull(drivers)) {
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
