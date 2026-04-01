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

    // Esta es la tabla donde se mostraran los usuarios registrados
    private JTable tablaUsuarios;
    private DefaultTableModel modeloTabla;

    // Botones principales de la ventana
    private JButton btnNuevo;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnCerrarSesion;

    // Objeto que conecta esta ventana con la base de datos
    private UsuarioDAO usuarioDAO;

    public VentanaPrincipal() {
        // Se inicializa el DAO para poder consultar, actualizar y eliminar los usuarios
        usuarioDAO = new UsuarioDAO();

        // Configuracion basica de la ventana
        setTitle("Pantalla Principal");
        setSize(980, 560);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Se construye la interfaz y luego se cargan los datos en la tabla
        iniciarComponentes();
        cargarUsuariosEnTabla();
    }

    private void iniciarComponentes() {
        setLayout(new BorderLayout());

        // Colores puestos para la interfaz
        Color fondoPrincipal = new Color(12, 24, 42);
        Color fondoPanel = new Color(22, 39, 66);
        Color textoClaro = new Color(225, 230, 235);
        Color azulBoton = new Color(55, 95, 150);
        Color azulBoton2 = new Color(72, 118, 178);
        Color rojoBoton = new Color(170, 70, 70);
        Color grisBoton = new Color(80, 90, 110);
        Color bordePanel = new Color(55, 80, 115);

        // Fuentes para titulo, botones y tabla
        Font fuenteTitulo = new Font("Consolas", Font.BOLD, 28);
        Font fuenteBotones = new Font("Consolas", Font.BOLD, 14);
        Font fuenteTabla = new Font("Consolas", Font.PLAIN, 13);
        Font fuenteHeader = new Font("Consolas", Font.BOLD, 13);

        getContentPane().setBackground(fondoPrincipal);

        JLabel lblTitulo = new JLabel("CLIENTES REGISTRADOS", SwingConstants.CENTER);
        lblTitulo.setFont(fuenteTitulo);
        lblTitulo.setForeground(textoClaro);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));

        // Esto aqui define las columnas que se van a mostrar
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Apellido");
        modeloTabla.addColumn("Telefono");
        modeloTabla.addColumn("Correo Electronico");
        modeloTabla.addColumn("Usuario");

        // Tabla principal donde se listan los registros
        tablaUsuarios = new JTable(modeloTabla);
        tablaUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Solo permite seleccionar una fila
        tablaUsuarios.setRowHeight(24);
        tablaUsuarios.setFont(fuenteTabla);
        tablaUsuarios.setBackground(new Color(235, 240, 246));
        tablaUsuarios.setForeground(Color.BLACK);
        tablaUsuarios.setGridColor(new Color(180, 190, 205));
        tablaUsuarios.setSelectionBackground(new Color(90, 130, 190));
        tablaUsuarios.setSelectionForeground(Color.WHITE);

        // Personalizamos el encabezado de la tabla
        tablaUsuarios.getTableHeader().setFont(fuenteHeader);
        tablaUsuarios.getTableHeader().setBackground(fondoPanel);
        tablaUsuarios.getTableHeader().setForeground(textoClaro);
        tablaUsuarios.getTableHeader().setReorderingAllowed(false);

        // La columna ID se oculta visualmente, pero sigue disponible para usarla internamente
        tablaUsuarios.getColumnModel().getColumn(0).setMinWidth(0);
        tablaUsuarios.getColumnModel().getColumn(0).setMaxWidth(0);
        tablaUsuarios.getColumnModel().getColumn(0).setWidth(0);

        // Scroll para poder mover la tabla si hay muchos registros
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

        //La creacion de botones
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
        // Este metodo evita repetir el mismo codigo de estilo en cada boton
        boton.setBackground(fondo);
        boton.setForeground(texto);
        boton.setFont(fuente);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createEmptyBorder(12, 18, 12, 18));
    }

    public void cargarUsuariosEnTabla() {
        // Limpia la tabla antes de volver a cargar los datos
        modeloTabla.setRowCount(0);

        // Se obtienen todos los usuarios desde la base de datos
        List<Usuario> lista = usuarioDAO.obtenerTodos();

        // Cada usuario se agrega como una nueva fila en la tabla
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
        // Cierra esta ventana y abre la de registro
        dispose();
        new VentanaRegistro(this).setVisible(true);
    }

    private void actualizarUsuario() {
        // Obtiene la fila seleccionada en la tabla
        int fila = tablaUsuarios.getSelectedRow();

        // Si no se selecciona nada, no se puede actualizar
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario para actualizar.");
            return;
        }

        // Se obtiene el ID del usuario seleccionado
        int id = Integer.parseInt(modeloTabla.getValueAt(fila, 0).toString());
        Usuario usuario = usuarioDAO.buscarPorId(id);

        // Si el usuario existe, se abre la ventana de registro en modo edicion
        if (usuario != null) {
            dispose();
            new VentanaRegistro(this, usuario).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo cargar el usuario seleccionado.");
        }
    }

    private void eliminarUsuario() {
        // Obtiene la fila seleccionada
        int fila = tablaUsuarios.getSelectedRow();

        // Verifica que el usuario haya seleccionado un registro
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario para eliminar.");
            return;
        }

        // Se toma el ID y el nombre del usuario seleccionado
        int id = Integer.parseInt(modeloTabla.getValueAt(fila, 0).toString());
        String nombre = modeloTabla.getValueAt(fila, 1).toString();

        // Se pide confirmacion antes de eliminar
        int confirmacion = JOptionPane.showConfirmDialog(
                this,
                "¿Seguro que desea eliminar al usuario " + nombre + "?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
        );

        // Si el usuario confirma, se intenta eliminar de la base de datos
        if (confirmacion == JOptionPane.YES_OPTION) {
            if (usuarioDAO.eliminarUsuario(id)) {
                JOptionPane.showMessageDialog(this, "Usuario eliminado correctamente.");
                cargarUsuariosEnTabla(); // Se actualiza la tabla despues de eliminar
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo eliminar el usuario.");
            }
        }
    }

    private void cerrarSesion() {
        // Cierra la ventana actual y regresa al login
        dispose();
        new LoginVentana().setVisible(true);
    }
}