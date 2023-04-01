package com.supermercado.model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPostgres {
    private Connection connection;
    private static ConnectionPostgres instance;
    String url = "jdbc:postgresql://localhost/Supermercado";
    String user = "postgres";
    String password = "";
    private ConnectionPostgres(){
        try {
            this.connection = DriverManager.getConnection(url, user, password);
        }catch (java.sql.SQLException sqle) {
            System.out.println("Error " + sqle);
        }
    }
    public Connection getConnectionPostgres(){
        return connection;
    }

    public static ConnectionPostgres getInstance() throws SQLException {
        if (instance == null) {
            instance = new ConnectionPostgres();
        } else if (instance.getConnectionPostgres().isClosed()) {
            instance = new ConnectionPostgres();
        }
        return instance;
    }
}
