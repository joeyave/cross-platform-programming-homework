package edu.dnu.fpm.pz.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

public class ServiceProviderConfig {
    public static Connection getConnection() throws SQLException {
        Properties properties = new Properties();

        InputStream input;
        try {
            input = ClassLoader
                    .getSystemClassLoader()
                    .getResourceAsStream("db/database.properties");
            properties.load(input);

        } catch (IOException io) {
            io.printStackTrace();
        }

        String drivers = properties.getProperty("driver");
        if (Objects.nonNull(drivers)) {
            System.setProperty("driver", drivers);
        }

        String url = properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");

        return DriverManager.getConnection(url, username, password);
    }
}
