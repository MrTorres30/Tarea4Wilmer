package data; // Paquete donde está la clase de conexión

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    //El URL de la base de datos, usuario y contraseña de MySQL
    private static final String URL = "jdbc:mysql://localhost:3306/tarea4_login?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "Mysql.902230##tig$";

    // Aqui con una única instancia de la clase
    private static Conexion instancia;

    // Usamos un constructor privado para aplicar Singleton
    private Conexion() {
    }

    // Devuelve la única instancia de la clase
    public static Conexion getInstancia() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

    // Abre y devuelve la conexión con la base de datos
    public Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}