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
        setSize(420, 280);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        iniciarComponentes();
    }

    private void iniciarComponentes() {
        setLayout(new BorderLayout());

        JLabel lblTitulo = new JLabel("LOGIN", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));

        JPanel panelFormulario = new JPanel(new GridLayout(4, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel lblUsuario = new JLabel("Nombre de Usuario:");
        JLabel lblContrasena = new JLabel("Contraseña:");

        txtUsuario = new JTextField();
        txtContrasena = new JPasswordField();

        btnEntrar = new JButton("Entrar");
        btnRegistrarse = new JButton("Registrarse");

        btnEntrar.setBackground(new Color(52, 104, 188));
        btnEntrar.setForeground(Color.WHITE);

        btnRegistrarse.setBackground(new Color(52, 104, 188));
        btnRegistrarse.setForeground(Color.WHITE);

        panelFormulario.add(lblUsuario);
        panelFormulario.add(txtUsuario);
        panelFormulario.add(lblContrasena);
        panelFormulario.add(txtContrasena);
        panelFormulario.add(btnEntrar);
        panelFormulario.add(btnRegistrarse);

        add(lblTitulo, BorderLayout.NORTH);
        add(panelFormulario, BorderLayout.CENTER);

        btnEntrar.addActionListener(e -> iniciarSesion());
        btnRegistrarse.addActionListener(e -> abrirRegistro());
    }

    private void iniciarSesion() {
        String usuario = txtUsuario.getText().trim();
        String contrasena = new String(txtContrasena.getPassword()).trim();

        if (usuario.isEmpty() || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Debe ingresar su usuario y contraseña, si no está registrado debe registrarse");
            return;
        }

        Usuario usuarioEncontrado = usuarioDAO.validarLogin(usuario, contrasena);

        if (usuarioEncontrado != null) {
            dispose();
            new VentanaPrincipal().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.");
        }
    }

    private void abrirRegistro() {
        dispose();
        new VentanaRegistro(this).setVisible(true);
    }
}