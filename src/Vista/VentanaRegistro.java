package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import data.UsuarioDAO;
import Modelo.Usuario;

public class VentanaRegistro extends JFrame {

    private JTextField txtNombreUsuario;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtTelefono;
    private JTextField txtCorreo;
    private JPasswordField txtContrasena;
    private JPasswordField txtConfirmarContrasena;

    private JButton btnGuardar;
    private JButton btnVolver;

    private UsuarioDAO usuarioDAO;
    private Usuario usuarioEditar;
    private VentanaPrincipal ventanaPrincipal;
    private LoginVentana loginVentana;

    public VentanaRegistro(LoginVentana ventanaLogin) {
        this.usuarioDAO = new UsuarioDAO();
        this.loginVentana = loginVentana;
        configurarVentana("Registro de Usuario");
        iniciarComponentes();
    }

    public VentanaRegistro(VentanaPrincipal ventanaPrincipal) {
        this.usuarioDAO = new UsuarioDAO();
        this.ventanaPrincipal = ventanaPrincipal;
        configurarVentana("Registro de Usuario");
        iniciarComponentes();
    }

    public VentanaRegistro(VentanaPrincipal ventanaPrincipal, Usuario usuarioEditar) {
        this.usuarioDAO = new UsuarioDAO();
        this.ventanaPrincipal = ventanaPrincipal;
        this.usuarioEditar = usuarioEditar;
        configurarVentana("Actualizar Usuario");
        iniciarComponentes();
        cargarDatosUsuario();
    }

    private void configurarVentana(String titulo) {
        setTitle(titulo);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    private void iniciarComponentes() {
        setLayout(new BorderLayout());

        JLabel lblTitulo = new JLabel(
                usuarioEditar == null ? "REGISTRO DE USUARIO" : "ACTUALIZAR USUARIO",
                SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));

        JPanel panelFormulario = new JPanel(new GridLayout(8, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        txtNombreUsuario = new JTextField();
        txtNombre = new JTextField();
        txtApellido = new JTextField();
        txtTelefono = new JTextField();
        txtCorreo = new JTextField();
        txtContrasena = new JPasswordField();
        txtConfirmarContrasena = new JPasswordField();

        btnGuardar = new JButton(usuarioEditar == null ? "Registrar" : "Actualizar");
        btnVolver = new JButton("Volver");

        btnGuardar.setBackground(new Color(52, 104, 188));
        btnGuardar.setForeground(Color.BLACK);

        btnVolver.setBackground(Color.DARK_GRAY);
        btnVolver.setForeground(Color.WHITE);

        panelFormulario.add(new JLabel("Nombre de Usuario:"));
        panelFormulario.add(txtNombreUsuario);

        panelFormulario.add(new JLabel("Nombre:"));
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Apellido:"));
        panelFormulario.add(txtApellido);

        panelFormulario.add(new JLabel("Telefono:"));
        panelFormulario.add(txtTelefono);

        panelFormulario.add(new JLabel("Correo Electronico:"));
        panelFormulario.add(txtCorreo);

        panelFormulario.add(new JLabel("Contraseña:"));
        panelFormulario.add(txtContrasena);

        panelFormulario.add(new JLabel("Confirmar Contraseña:"));
        panelFormulario.add(txtConfirmarContrasena);

        panelFormulario.add(btnGuardar);
        panelFormulario.add(btnVolver);

        add(lblTitulo, BorderLayout.NORTH);
        add(panelFormulario, BorderLayout.CENTER);

        btnGuardar.addActionListener(e -> guardarUsuario());
        btnVolver.addActionListener(e -> volver());
    }

    private void cargarDatosUsuario() {
        txtNombreUsuario.setText(usuarioEditar.getNombreUsuario());
        txtNombre.setText(usuarioEditar.getNombre());
        txtApellido.setText(usuarioEditar.getApellido());
        txtTelefono.setText(usuarioEditar.getTelefono());
        txtCorreo.setText(usuarioEditar.getCorreo());
        txtContrasena.setText(usuarioEditar.getContrasena());
        txtConfirmarContrasena.setText(usuarioEditar.getContrasena());
    }

    private void guardarUsuario() {
        String nombreUsuario = txtNombreUsuario.getText().trim();
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String correo = txtCorreo.getText().trim();
        String contrasena = new String(txtContrasena.getPassword()).trim();
        String confirmarContrasena = new String(txtConfirmarContrasena.getPassword()).trim();

        if (nombreUsuario.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Falta el campo: Nombre de Usuario");
            return;
        }
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Falta el campo: Nombre");
            return;
        }
        if (apellido.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Falta el campo: Apellido");
            return;
        }
        if (telefono.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Falta el campo: Telefono");
            return;
        }
        if (correo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Falta el campo: Correo Electronico");
            return;
        }
        if (contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Falta el campo: Contraseña");
            return;
        }
        if (confirmarContrasena.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Falta el campo: Confirmar Contraseña");
            return;
        }

        if (!contrasena.equals(confirmarContrasena)) {
            JOptionPane.showMessageDialog(this, "La contraseña y la confirmación de la contraseña no coinciden.");
            return;
        }

        if (usuarioEditar == null) {
            if (usuarioDAO.existeUsuario(nombreUsuario)) {
                JOptionPane.showMessageDialog(this, "Ese nombre de usuario ya existe.");
                return;
            }

            if (usuarioDAO.existeCorreo(correo)) {
                JOptionPane.showMessageDialog(this, "Ese correo ya existe.");
                return;
            }

            Usuario usuario = new Usuario(nombreUsuario, nombre, apellido, telefono, correo, contrasena);

            if (usuarioDAO.insertarUsuario(usuario)) {
                JOptionPane.showMessageDialog(this, "Usuario registrado correctamente.");
                dispose();

                if (ventanaPrincipal != null) {
                    ventanaPrincipal.cargarUsuariosEnTabla();
                    ventanaPrincipal.setVisible(true);
                } else {
                    new LoginVentana().setVisible(true);
                }
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo registrar el usuario.");
            }

        } else {
            if (usuarioDAO.existeUsuarioOtro(usuarioEditar.getId(), nombreUsuario)) {
                JOptionPane.showMessageDialog(this, "Ese nombre de usuario ya existe en otro registro.");
                return;
            }

            if (usuarioDAO.existeCorreoOtro(usuarioEditar.getId(), correo)) {
                JOptionPane.showMessageDialog(this, "Ese correo ya existe en otro registro.");
                return;
            }

            usuarioEditar.setNombreUsuario(nombreUsuario);
            usuarioEditar.setNombre(nombre);
            usuarioEditar.setApellido(apellido);
            usuarioEditar.setTelefono(telefono);
            usuarioEditar.setCorreo(correo);
            usuarioEditar.setContrasena(contrasena);

            if (usuarioDAO.actualizarUsuario(usuarioEditar)) {
                JOptionPane.showMessageDialog(this, "Usuario actualizado correctamente.");
                ventanaPrincipal.cargarUsuariosEnTabla();
                dispose();
                ventanaPrincipal.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo actualizar el usuario.");
            }
        }
    }

    private void volver() {
        dispose();

        if (usuarioEditar != null && ventanaPrincipal != null) {
            ventanaPrincipal.setVisible(true);
        } else if (ventanaPrincipal != null) {
            ventanaPrincipal.setVisible(true);
        } else {
            new LoginVentana().setVisible(true);
        }
    }
}