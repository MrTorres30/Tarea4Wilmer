package Modelo;

public abstract class Cuenta {
    
    // Estos atributos guardan el usuario y la contraseña de la cuenta
    protected String nombreUsuario;
    protected String contrasena;

    public Cuenta() {
    }

    // Constructor que permite inicializar el usuario y la contraseña
    public Cuenta(String nombreUsuario, String contrasena) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    // Método abstracto que será definido en las clases hijas
    public abstract String mostrarTipoCuenta();
}