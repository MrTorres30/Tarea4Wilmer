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

public class LoginVentana extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    private JButton btnEntrar;
    private JButton btnRegistrarse;

    private UsuarioDAO usuarioDAO;

    public LoginVentana() {
        usuarioDAO = new UsuarioDAO();

        setTitle("Login de Usuarios");
        setSize(450, 320);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        iniciarComponentes();
    }

    private void iniciarComponentes() {
        setLayout(new BorderLayout());

        // Aqui pongo los colores principales de la interfaz
        Color fondoPrincipal = new Color(11, 25, 44);
        Color fondoPanel = new Color(21, 38, 63);
        Color textoClaro = new Color(210, 215, 220);
        Color azulBoton = new Color(58, 95, 145);
        Color azulBotonHover = new Color(72, 115, 175);
        Color fondoCampos = new Color(235, 238, 243);

        getContentPane().setBackground(fondoPrincipal);

        JLabel lblTitulo = new JLabel("INICIAR SESION", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 26));
        lblTitulo.setForeground(textoClaro);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));

        JPanel panelCentro = new JPanel(new BorderLayout());
        panelCentro.setBackground(fondoPrincipal);
        panelCentro.setBorder(BorderFactory.createEmptyBorder(10, 25, 25, 25));

        // Panel donde se colocan los campos y botones del login configurando las coords
        JPanel panelFormulario = new JPanel(new GridLayout(4, 2, 12, 12));
        panelFormulario.setBackground(fondoPanel);
        panelFormulario.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(45, 65, 95), 1),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        JLabel lblUsuario = new JLabel("Nombre de Usuario:");
        lblUsuario.setForeground(textoClaro);
        lblUsuario.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setForeground(textoClaro);
        lblContrasena.setFont(new Font("Arial", Font.BOLD, 14));

        txtUsuario = new JTextField();
        txtUsuario.setBackground(fondoCampos);
        txtUsuario.setForeground(Color.BLACK);
        txtUsuario.setFont(new Font("Arial", Font.PLAIN, 14));
        txtUsuario.setBorder(BorderFactory.createEmptyBorder(6, 8, 6, 8));

        txtContrasena = new JPasswordField();
        txtContrasena.setBackground(fondoCampos);
        txtContrasena.setForeground(Color.BLACK);
        txtContrasena.setFont(new Font("Arial", Font.PLAIN, 14));
        txtContrasena.setBorder(BorderFactory.createEmptyBorder(6, 8, 6, 8));

        btnEntrar = new JButton("Entrar");
        btnEntrar.setBackground(azulBoton);
        btnEntrar.setForeground(textoClaro);
        btnEntrar.setFont(new Font("Arial", Font.BOLD, 14));
        btnEntrar.setFocusPainted(false);
        btnEntrar.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        btnRegistrarse = new JButton("Registrarse");
        btnRegistrarse.setBackground(azulBotonHover);
        btnRegistrarse.setForeground(textoClaro);
        btnRegistrarse.setFont(new Font("Arial", Font.BOLD, 14));
        btnRegistrarse.setFocusPainted(false);
        btnRegistrarse.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        panelFormulario.add(lblUsuario);
        panelFormulario.add(txtUsuario);
        panelFormulario.add(lblContrasena);
        panelFormulario.add(txtContrasena);
        panelFormulario.add(btnEntrar);
        panelFormulario.add(btnRegistrarse);

        panelCentro.add(panelFormulario, BorderLayout.CENTER);

        add(lblTitulo, BorderLayout.NORTH);
        add(panelCentro, BorderLayout.CENTER);

        // Acciones de los botones
        btnEntrar.addActionListener(e -> iniciarSesion());
        btnRegistrarse.addActionListener(e -> abrirRegistro());
    }

    private void iniciarSesion() {
        String usuario = txtUsuario.getText().trim();
        String contrasena = new String(txtContrasena.getPassword()).trim();

        // Valida que los campos no estén vacíos
        if (usuario.isEmpty() || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Debe ingresar su usuario y contraseña, si no está registrado debe registrarse");
            return;
        }

        // Busca el usuario en la base de datos
        Usuario usuarioEncontrado = usuarioDAO.validarLogin(usuario, contrasena);

        if (usuarioEncontrado != null) {
            dispose();
            new VentanaPrincipal().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.");
        }
    }
    // Metodo para abrir la ventana de registro
    private void abrirRegistro() {
        dispose();
        new VentanaRegistro(this).setVisible(true);
    }
}