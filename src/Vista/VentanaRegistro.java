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

    // Campos del formulario
    private JTextField txtNombreUsuario;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtTelefono;
    private JTextField txtCorreo;
    private JPasswordField txtContrasena;
    private JPasswordField txtConfirmarContrasena;

    // Botones de la ventana
    private JButton btnGuardar;
    private JButton btnVolver;

    // Objetos necesarios para manejar el registro y la actualizacion
    private UsuarioDAO usuarioDAO;
    private Usuario usuarioEditar;
    private VentanaPrincipal ventanaPrincipal;
    private LoginVentana ventanaLogin;

    // Constructor usado cuando la ventana se abre desde el login
    public VentanaRegistro(LoginVentana ventanaLogin) {
        this.usuarioDAO = new UsuarioDAO();
        this.ventanaLogin = ventanaLogin;
        configurarVentana("Registro de Usuario");
        iniciarComponentes();
    }

    // Constructor usado cuando se quiere registrar desde la ventana principal
    public VentanaRegistro(VentanaPrincipal ventanaPrincipal) {
        this.usuarioDAO = new UsuarioDAO();
        this.ventanaPrincipal = ventanaPrincipal;
        configurarVentana("Registro de Usuario");
        iniciarComponentes();
    }

    // Constructor usado cuando se quiere editar un usuario existente
    public VentanaRegistro(VentanaPrincipal ventanaPrincipal, Usuario usuarioEditar) {
        this.usuarioDAO = new UsuarioDAO();
        this.ventanaPrincipal = ventanaPrincipal;
        this.usuarioEditar = usuarioEditar;
        configurarVentana("Actualizar Usuario");
        iniciarComponentes();
        cargarDatosUsuario();
    }

    // Configuracion general de la ventana
    private void configurarVentana(String titulo) {
        setTitle(titulo);
        setSize(620, 560);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    private void iniciarComponentes() {
        setLayout(new BorderLayout());

        // Colores de la interfaz
        Color fondoPrincipal = new Color(236, 240, 245);
        Color fondoPanel = new Color(248, 250, 252);
        Color textoOscuro = new Color(18, 30, 48);
        Color botonOscuro = new Color(20, 38, 65);
        Color botonOscuro2 = new Color(35, 60, 95);
        Color fondoCampos = new Color(220, 228, 236);
        Color bordePanel = new Color(90, 110, 135);

        // Fuentes utilizadas en esta ventana
        Font fuenteTitulo = new Font("Consolas", Font.BOLD, 28);
        Font fuenteTexto = new Font("Consolas", Font.BOLD, 14);
        Font fuenteCampos = new Font("Consolas", Font.PLAIN, 14);
        Font fuenteBotones = new Font("Consolas", Font.BOLD, 14);

        getContentPane().setBackground(fondoPrincipal);

        // El titulo cambia dependiendo de si se esta registrando o actualizando
        JLabel lblTitulo = new JLabel(
                usuarioEditar == null ? "REGISTRO DE USUARIO" : "ACTUALIZAR USUARIO",
                SwingConstants.CENTER);
        lblTitulo.setFont(fuenteTitulo);
        lblTitulo.setForeground(textoOscuro);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));

        JPanel panelCentro = new JPanel(new BorderLayout());
        panelCentro.setBackground(fondoPrincipal);
        panelCentro.setBorder(BorderFactory.createEmptyBorder(10, 30, 25, 30));

        // Panel donde se colocan todos los campos del formulario
        JPanel panelFormulario = new JPanel(new GridLayout(8, 2, 14, 14));
        panelFormulario.setBackground(fondoPanel);
        panelFormulario.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(bordePanel, 2),
                BorderFactory.createEmptyBorder(25, 25, 25, 25)
        ));

        // Creacion de campos
        txtNombreUsuario = new JTextField();
        txtNombre = new JTextField();
        txtApellido = new JTextField();
        txtTelefono = new JTextField();
        txtCorreo = new JTextField();
        txtContrasena = new JPasswordField();
        txtConfirmarContrasena = new JPasswordField();

        // Etiquetas del formulario
        JLabel lblNombreUsuario = new JLabel("USUARIO:");
        JLabel lblNombre = new JLabel("NOMBRE:");
        JLabel lblApellido = new JLabel("APELLIDO:");
        JLabel lblTelefono = new JLabel("TELEFONO:");
        JLabel lblCorreo = new JLabel("CORREO:");
        JLabel lblContrasena = new JLabel("CONTRASEÑA:");
        JLabel lblConfirmar = new JLabel("CONFIRMAR:");

        JLabel[] labels = {
            lblNombreUsuario, lblNombre, lblApellido, lblTelefono,
            lblCorreo, lblContrasena, lblConfirmar
        };

        // Se aplica el mismo estilo a todas las etiquetas
        for (JLabel lbl : labels) {
            lbl.setForeground(textoOscuro);
            lbl.setFont(fuenteTexto);
        }

        JTextField[] camposTexto = {
            txtNombreUsuario, txtNombre, txtApellido, txtTelefono, txtCorreo
        };

        // Se aplica el mismo estilo a los campos de texto normales
        for (JTextField campo : camposTexto) {
            campo.setBackground(fondoCampos);
            campo.setForeground(Color.BLACK);
            campo.setFont(fuenteCampos);
            campo.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
        }

        // Estilo de los campos de contraseña
        txtContrasena.setBackground(fondoCampos);
        txtContrasena.setForeground(Color.BLACK);
        txtContrasena.setFont(fuenteCampos);
        txtContrasena.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));

        txtConfirmarContrasena.setBackground(fondoCampos);
        txtConfirmarContrasena.setForeground(Color.BLACK);
        txtConfirmarContrasena.setFont(fuenteCampos);
        txtConfirmarContrasena.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));

        // El texto del boton cambia si se esta registrando o actualizando
        btnGuardar = new JButton(usuarioEditar == null ? "GUARDAR" : "ACTUALIZAR");
        btnVolver = new JButton("VOLVER");

        btnGuardar.setBackground(botonOscuro);
        btnGuardar.setForeground(new Color(235, 240, 245));
        btnGuardar.setFont(fuenteBotones);
        btnGuardar.setFocusPainted(false);
        btnGuardar.setBorder(BorderFactory.createEmptyBorder(12, 15, 12, 15));

        btnVolver.setBackground(botonOscuro2);
        btnVolver.setForeground(new Color(235, 240, 245));
        btnVolver.setFont(fuenteBotones);
        btnVolver.setFocusPainted(false);
        btnVolver.setBorder(BorderFactory.createEmptyBorder(12, 15, 12, 15));

        // Se agregan los componentes al formulario
        panelFormulario.add(lblNombreUsuario);
        panelFormulario.add(txtNombreUsuario);

        panelFormulario.add(lblNombre);
        panelFormulario.add(txtNombre);

        panelFormulario.add(lblApellido);
        panelFormulario.add(txtApellido);

        panelFormulario.add(lblTelefono);
        panelFormulario.add(txtTelefono);

        panelFormulario.add(lblCorreo);
        panelFormulario.add(txtCorreo);

        panelFormulario.add(lblContrasena);
        panelFormulario.add(txtContrasena);

        panelFormulario.add(lblConfirmar);
        panelFormulario.add(txtConfirmarContrasena);

        panelFormulario.add(btnGuardar);
        panelFormulario.add(btnVolver);

        panelCentro.add(panelFormulario, BorderLayout.CENTER);

        add(lblTitulo, BorderLayout.NORTH);
        add(panelCentro, BorderLayout.CENTER);

        // Eventos de los botones
        btnGuardar.addActionListener(e -> guardarUsuario());
        btnVolver.addActionListener(e -> volver());
    }

    // Carga la informacion del usuario cuando se entra en modo edicion
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
        // Se obtienen los datos escritos en el formulario
        String nombreUsuario = txtNombreUsuario.getText().trim();
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String correo = txtCorreo.getText().trim();
        String contrasena = new String(txtContrasena.getPassword()).trim();
        String confirmarContrasena = new String(txtConfirmarContrasena.getPassword()).trim();

        // Validaciones para asegurarse de que no falten campos
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

        // Se verifica que ambas contraseñas coincidan
        if (!contrasena.equals(confirmarContrasena)) {
            JOptionPane.showMessageDialog(this, "La contraseña y la confirmación de la contraseña no coinciden.");
            return;
        }

        // Si usuarioEditar es null, se registra un usuario nuevo
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

                // Si se abrio desde la principal, se actualiza la tabla
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
            // Si usuarioEditar tiene valor, entonces se esta actualizando un registro existente
            if (usuarioDAO.existeUsuarioOtro(usuarioEditar.getId(), nombreUsuario)) {
                JOptionPane.showMessageDialog(this, "Ese nombre de usuario ya existe en otro registro.");
                return;
            }

            if (usuarioDAO.existeCorreoOtro(usuarioEditar.getId(), correo)) {
                JOptionPane.showMessageDialog(this, "Ese correo ya existe en otro registro.");
                return;
            }

            // Se actualizan los datos del objeto antes de enviarlos al DAO
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