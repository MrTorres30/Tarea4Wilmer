package Modelo;

public class Usuario extends Cuenta {
    
    // Atributos propios del usuario
    private int id;
    private String nombre;
    private String apellido;
    private String telefono;
    private String correo;

    public Usuario() {
    }

    // Constructor que inicializa solo el nombre de usuario y la contraseña
    public Usuario(String nombreUsuario, String contrasena) {
        super(nombreUsuario, contrasena);
    }

    // Constructor usado al registrar un nuevo usuario
    public Usuario(String nombreUsuario, String nombre, String apellido, String telefono, String correo, String contrasena) {
        super(nombreUsuario, contrasena);
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.correo = correo;
    }

    // Constructor usado cuando también se necesita manejar el id del usuario
    public Usuario(int id, String nombreUsuario, String nombre, String apellido, String telefono, String correo, String contrasena) {
        super(nombreUsuario, contrasena);
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.correo = correo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }   

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    // esto nos indica que este método está siendo sobrescrito
    @Override
    public String mostrarTipoCuenta() {
        return "Usuario del sistema";
    }
}