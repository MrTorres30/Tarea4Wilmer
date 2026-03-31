package data;   //llamamos el paquete en el que está

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL = "jdbc:mysql://localhost:3306/tarea4_login?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "Mysql.902230##tig$";

    private static Conexion instancia;

    private Conexion() {
    }

    public static Conexion getInstancia() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

    public Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}