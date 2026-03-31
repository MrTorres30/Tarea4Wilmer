package Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import Data.UsuarioDAO;
import modelo.Usuario;
import java.awt.*;
import java.util.ArrayList;

public class VentanaPrincipal extends JFrame {
    private JTable tabla;
    private DefaultTableModel modelo;
    private UsuarioDAO dao = new UsuarioDAO();

    public VentanaPrincipal() {
        setTitle("Listado de Usuarios"); [cite: 4, 52]
        setSize(600, 400);
        setLayout(new BorderLayout());

        modelo = new DefaultTableModel(new String[]{"Usuario", "Nombre", "Teléfono", "Correo"}, 0);
        tabla = new JTable(modelo);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        JPanel p = new JPanel();
        JButton btnDel = new JButton("Eliminar"); [cite: 60]
        JButton btnOut = new JButton("Cerrar Sección"); [cite: 61]
        
        p.add(btnDel); p.add(btnOut);
        add(p, BorderLayout.SOUTH);

        cargar();

        btnDel.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if(fila != -1) {
                try {
                    dao.eliminar(modelo.getValueAt(fila, 0).toString());
                    cargar(); // Actualización automática [cite: 15]
                } catch (Exception ex) { ex.printStackTrace(); }
            }
        });

        btnOut.addActionListener(e -> { [cite: 11, 12]
            new VentanaLogin().setVisible(true);
            this.dispose();
        });
    }

    private void cargar() {
        modelo.setRowCount(0);
        try {
            ArrayList<Usuario> lista = dao.listar();
            for(Usuario u : lista) modelo.addRow(new Object[]{u.getUsername(), u.getNombre(), u.getTelefono(), u.getCorreo()});
        } catch (Exception e) { e.printStackTrace(); }
    }
}