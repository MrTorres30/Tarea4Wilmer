package data;

import java.sql.Connection;  //esto es para concetar con el SQL
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;  //importamos/llamamos para poder usar arrys
import java.util.List;

import Modelo.Usuario;

public class UsuarioDAO {

    // Inserta un nuevo usuario en la base de datos
    public boolean insertarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuario (nombre_usuario, nombre, apellido, telefono, correo, contrasena) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexion.getInstancia().conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usuario.getNombreUsuario());
            ps.setString(2, usuario.getNombre());
            ps.setString(3, usuario.getApellido());
            ps.setString(4, usuario.getTelefono());
            ps.setString(5, usuario.getCorreo());
            ps.setString(6, usuario.getContrasena());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Error al insertar usuario: " + e.getMessage());
            return false;
        }
    }

    // Valida si el usuario y la contraseña existen en la base de datos
    public Usuario validarLogin(String nombreUsuario, String contrasena) {
        String sql = "SELECT * FROM usuario WHERE nombre_usuario = ? AND contrasena = ?";

        try (Connection con = Conexion.getInstancia().conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombreUsuario);
            ps.setString(2, contrasena);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNombreUsuario(rs.getString("nombre_usuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setContrasena(rs.getString("contrasena"));
                return usuario;
            }

        } catch (Exception e) {
            System.out.println("Error al validar login: " + e.getMessage());
        }

        return null;
    }

    // Obtiene la lista completa de usuarios registrados
    public List<Usuario> obtenerTodos() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuario ORDER BY id DESC";

        try (Connection con = Conexion.getInstancia().conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNombreUsuario(rs.getString("nombre_usuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setContrasena(rs.getString("contrasena"));
                lista.add(usuario);
            }

        } catch (Exception e) {
            System.out.println("Error al obtener usuarios: " + e.getMessage());
        }

        return lista;
    }

    // Actualiza los datos de un usuario según su id
    public boolean actualizarUsuario(Usuario usuario) {
        String sql = "UPDATE usuario SET nombre_usuario = ?, nombre = ?, apellido = ?, telefono = ?, correo = ?, contrasena = ? WHERE id = ?";

        try (Connection con = Conexion.getInstancia().conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usuario.getNombreUsuario());
            ps.setString(2, usuario.getNombre());
            ps.setString(3, usuario.getApellido());
            ps.setString(4, usuario.getTelefono());
            ps.setString(5, usuario.getCorreo());
            ps.setString(6, usuario.getContrasena());
            ps.setInt(7, usuario.getId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Error al actualizar usuario: " + e.getMessage());
            return false;
        }
    }

    // Elimina un usuario de la base de datos por su id
    public boolean eliminarUsuario(int id) {
        String sql = "DELETE FROM usuario WHERE id = ?";

        try (Connection con = Conexion.getInstancia().conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Error al eliminar usuario: " + e.getMessage());
            return false;
        }
    }

    // Verifica si ya existe un nombre de usuario registrado
    public boolean existeUsuario(String nombreUsuario) {
        String sql = "SELECT id FROM usuario WHERE nombre_usuario = ?";

        try (Connection con = Conexion.getInstancia().conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombreUsuario);
            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (Exception e) {
            System.out.println("Error al verificar usuario: " + e.getMessage());
            return false;
        }
    }

    // Verifica si ya existe un correo registrado
    public boolean existeCorreo(String correo) {
        String sql = "SELECT id FROM usuario WHERE correo = ?";

        try (Connection con = Conexion.getInstancia().conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, correo);
            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (Exception e) {
            System.out.println("Error al verificar correo: " + e.getMessage());
            return false;
        }
    }

    // Verifica si el nombre de usuario existe en otro registro distinto al actual
    public boolean existeUsuarioOtro(int id, String nombreUsuario) {
        String sql = "SELECT id FROM usuario WHERE nombre_usuario = ? AND id <> ?";

        try (Connection con = Conexion.getInstancia().conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombreUsuario);
            ps.setInt(2, id);
            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (Exception e) {
            System.out.println("Error al verificar usuario repetido: " + e.getMessage());
            return false;
        }
    }

    // Verifica si el correo existe en otro registro distinto al actual
    public boolean existeCorreoOtro(int id, String correo) {
        String sql = "SELECT id FROM usuario WHERE correo = ? AND id <> ?";

        try (Connection con = Conexion.getInstancia().conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, correo);
            ps.setInt(2, id);
            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (Exception e) {
            System.out.println("Error al verificar correo repetido: " + e.getMessage());
            return false;
        }
    }

    // Busca un usuario específico por su id
    public Usuario buscarPorId(int id) {
        String sql = "SELECT * FROM usuario WHERE id = ?";

        try (Connection con = Conexion.getInstancia().conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNombreUsuario(rs.getString("nombre_usuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setContrasena(rs.getString("contrasena"));
                return usuario;
            }

        } catch (Exception e) {
            System.out.println("Error al buscar usuario por id: " + e.getMessage());
        }

        return null;
    }
}