package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import data.UsuarioDAO;
import Modelo.Usuario;

public class VentanaPrincipal extends JFrame {

    private JTable tablaUsuarios;
    private DefaultTableModel modeloTabla;

    private JButton btnNuevo;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnCerrarSesion;

    private UsuarioDAO usuarioDAO;

    public VentanaPrincipal() {
        usuarioDAO = new UsuarioDAO();

        setTitle("Pantalla Principal");
        setSize(980, 560);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        iniciarComponentes();
        cargarUsuariosEnTabla();
    }

    private void iniciarComponentes() {
        setLayout(new BorderLayout());

        Color fondoPrincipal = new Color(12, 24, 42);
        Color fondoPanel = new Color(22, 39, 66);
        Color textoClaro = new Color(225, 230, 235);
        Color azulBoton = new Color(55, 95, 150);
        Color azulBoton2 = new Color(72, 118, 178);
        Color rojoBoton = new Color(170, 70, 70);
        Color grisBoton = new Color(80, 90, 110);
        Color bordePanel = new Color(55, 80, 115);

        Font fuenteTitulo = new Font("Consolas", Font.BOLD, 28);
        Font fuenteBotones = new Font("Consolas", Font.BOLD, 14);
        Font fuenteTabla = new Font("Consolas", Font.PLAIN, 13);
        Font fuenteHeader = new Font("Consolas", Font.BOLD, 13);

        getContentPane().setBackground(fondoPrincipal);

        JLabel lblTitulo = new JLabel("CLIENTES REGISTRADOS", SwingConstants.CENTER);
        lblTitulo.setFont(fuenteTitulo);
        lblTitulo.setForeground(textoClaro);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));

        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Apellido");
        modeloTabla.addColumn("Telefono");
        modeloTabla.addColumn("Correo Electronico");
        modeloTabla.addColumn("Usuario");

        tablaUsuarios = new JTable(modeloTabla);
        tablaUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaUsuarios.setRowHeight(24);
        tablaUsuarios.setFont(fuenteTabla);
        tablaUsuarios.setBackground(new Color(235, 240, 246));
        tablaUsuarios.setForeground(Color.BLACK);
        tablaUsuarios.setGridColor(new Color(180, 190, 205));
        tablaUsuarios.setSelectionBackground(new Color(90, 130, 190));
        tablaUsuarios.setSelectionForeground(Color.WHITE);

        tablaUsuarios.getTableHeader().setFont(fuenteHeader);
        tablaUsuarios.getTableHeader().setBackground(fondoPanel);
        tablaUsuarios.getTableHeader().setForeground(textoClaro);
        tablaUsuarios.getTableHeader().setReorderingAllowed(false);

        tablaUsuarios.getColumnModel().getColumn(0).setMinWidth(0);
        tablaUsuarios.getColumnModel().getColumn(0).setMaxWidth(0);
        tablaUsuarios.getColumnModel().getColumn(0).setWidth(0);

        JScrollPane scroll = new JScrollPane(tablaUsuarios);
        scroll.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(bordePanel, 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        scroll.getViewport().setBackground(new Color(235, 240, 246));

        JPanel panelCentro = new JPanel(new BorderLayout());
        panelCentro.setBackground(fondoPrincipal);
        panelCentro.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panelCentro.add(scroll, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        panelBotones.setBackground(fondoPrincipal);

        btnNuevo = new JButton("NUEVO");
        btnActualizar = new JButton("ACTUALIZAR");
        btnEliminar = new JButton("ELIMINAR");
        btnCerrarSesion = new JButton("CERRAR SESION");

        estilizarBoton(btnNuevo, azulBoton, textoClaro, fuenteBotones);
        estilizarBoton(btnActualizar, azulBoton2, textoClaro, fuenteBotones);
        estilizarBoton(btnEliminar, rojoBoton, textoClaro, fuenteBotones);
        estilizarBoton(btnCerrarSesion, grisBoton, textoClaro, fuenteBotones);

        panelBotones.add(btnNuevo);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnCerrarSesion);

        add(lblTitulo, BorderLayout.NORTH);
        add(panelCentro, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        btnNuevo.addActionListener(e -> abrirNuevoRegistro());
        btnActualizar.addActionListener(e -> actualizarUsuario());
        btnEliminar.addActionListener(e -> eliminarUsuario());
        btnCerrarSesion.addActionListener(e -> cerrarSesion());
    }

    private void estilizarBoton(JButton boton, Color fondo, Color texto, Font fuente) {
        boton.setBackground(fondo);
        boton.setForeground(texto);
        boton.setFont(fuente);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createEmptyBorder(12, 18, 12, 18));
    }

    public void cargarUsuariosEnTabla() {
        modeloTabla.setRowCount(0);

        List<Usuario> lista = usuarioDAO.obtenerTodos();

        for (Usuario usuario : lista) {
            modeloTabla.addRow(new Object[] {
                    usuario.getId(),
                    usuario.getNombre(),
                    usuario.getApellido(),
                    usuario.getTelefono(),
                    usuario.getCorreo(),
                    usuario.getNombreUsuario()
            });
        }
    }

    private void abrirNuevoRegistro() {
        dispose();
        new VentanaRegistro(this).setVisible(true);
    }

    private void actualizarUsuario() {
        int fila = tablaUsuarios.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario para actualizar.");
            return;
        }

        int id = Integer.parseInt(modeloTabla.getValueAt(fila, 0).toString());
        Usuario usuario = usuarioDAO.buscarPorId(id);

        if (usuario != null) {
            dispose();
            new VentanaRegistro(this, usuario).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo cargar el usuario seleccionado.");
        }
    }

    private void eliminarUsuario() {
        int fila = tablaUsuarios.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario para eliminar.");
            return;
        }

        int id = Integer.parseInt(modeloTabla.getValueAt(fila, 0).toString());
        String nombre = modeloTabla.getValueAt(fila, 1).toString();

        int confirmacion = JOptionPane.showConfirmDialog(
                this,
                "¿Seguro que desea eliminar al usuario " + nombre + "?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmacion == JOptionPane.YES_OPTION) {
            if (usuarioDAO.eliminarUsuario(id)) {
                JOptionPane.showMessageDialog(this, "Usuario eliminado correctamente.");
                cargarUsuariosEnTabla();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo eliminar el usuario.");
            }
        }
    }

    private void cerrarSesion() {
        dispose();
        new LoginVentana().setVisible(true);
    }
}