package pl.lublin.wsei.java.cwiczenia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MyDB {

    private Connection conn = null;
    private String dbHost = null;
    private Number dbPort = 0;
    private String dbName = null;
    private String userName = null;
    private String userPassword = null;

    public MyDB(String host, Number port, String name) {

        dbHost = host;
        dbPort = port;
        dbName = name;

    }

    public void setUser(String user) {

        userName = user;

    }

    public void setPassword(String password) {

        userPassword = password;

    }

    public void connect() {

        Properties connectionProps = new Properties();
        connectionProps.put("user", userName);
        connectionProps.put("password", userPassword);
        connectionProps.put("serverTimezone", "Europe/Warsaw");
        String jdbcString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

        try {

            conn = DriverManager.getConnection(jdbcString,connectionProps);

        }catch(SQLException e) {

            System.out.println("Błąd połączenia do bazy:" + jdbcString);
            System.out.println("Komunikat błędu: " + e.getMessage());
            conn = null;

        }
        System.out.println("Connected to database " + dbName);



    }

    public Connection getConnection() {

        if(conn == null)
            connect();
        return conn;

    }

    public void closeConnection() {

        if(conn != null) {

            try{

                conn.close();

            }catch(SQLException e) {

                System.out.println("Błąd przy zamykaniu połączenia bazodanowego: "
                        + e.getMessage());

            }
            conn = null;
        }

    }
}
