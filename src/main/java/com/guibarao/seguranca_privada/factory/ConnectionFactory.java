package com.guibarao.seguranca_privada.factory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import lombok.Getter;

@Component
@Getter
public class ConnectionFactory {

    private final String urlDB;

    private final String userDB;

    private final String passwordDB;


    public ConnectionFactory(@Value("${spring.datasource.url}") String urlDB,
                             @Value("${spring.datasource.username}") String userDB,
                             @Value("${spring.datasource.password}") String passwordDB)
    {
        this.urlDB = urlDB;
        this.userDB = userDB;
        this.passwordDB = passwordDB;


    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(urlDB, userDB, passwordDB);
    }


}
