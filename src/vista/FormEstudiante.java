package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import modelo.Estudiante;

public class FormEstudiante extends JFrame implements ActionListener {

    JLabel lblTitulo;

    JLabel lblCarnet;
    JLabel lblNombre;
    JLabel lblApellidos;
    JLabel lblCorreo;

    JTextField txtCarnet;
    JTextField txtNombre;
    JTextField txtApellidos;
    JTextField txtCorreo;

    JButton btnGuardar;
    JButton btnLimpiar;
    JButton btnMenu;
    JButton btnEditar;
    JButton btnEliminar;

    JTable tablaEstudiantes;
    DefaultTableModel modeloTabla;
    JScrollPane scroll;

    int filaSeleccionada = -1;

    public FormEstudiante() {

        setTitle("Formulario Estudiante");
        setSize(550, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        lblTitulo = new JLabel("ESTUDIANTES");
        lblTitulo.setBounds(190, 10, 200, 30);
        add(lblTitulo);

        lblCarnet = new JLabel("Carnet:");
        lblCarnet.setBounds(50, 60, 100, 30);
        add(lblCarnet);

        lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(50, 110, 100, 30);
        add(lblNombre);

        lblApellidos = new JLabel("Apellidos:");
        lblApellidos.setBounds(50, 160, 100, 30);
        add(lblApellidos);

        lblCorreo = new JLabel("Correo:");
        lblCorreo.setBounds(50, 210, 100, 30);
        add(lblCorreo);

        txtCarnet = new JTextField();
        txtCarnet.setBounds(150, 60, 200, 30);
        add(txtCarnet);

        txtNombre = new JTextField();
        txtNombre.setBounds(150, 110, 200, 30);
        add(txtNombre);

        txtApellidos = new JTextField();
        txtApellidos.setBounds(150, 160, 200, 30);
        add(txtApellidos);

        txtCorreo = new JTextField();
        txtCorreo.setBounds(150, 210, 200, 30);
        add(txtCorreo);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(50, 290, 100, 40);
        add(btnGuardar);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(180, 290, 100, 40);
        add(btnLimpiar);

        // 🔥 BOTÓN MENU AGREGADO
        btnMenu = new JButton("Menu");
        btnMenu.setBounds(310, 290, 100, 40);
        add(btnMenu);

        btnEditar = new JButton("Editar");
        btnEditar.setBounds(50, 340, 100, 40);
        add(btnEditar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(180, 340, 100, 40);
        add(btnEliminar);

        modeloTabla = new DefaultTableModel();

        modeloTabla.addColumn("Carnet");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Apellidos");
        modeloTabla.addColumn("Correo");

        tablaEstudiantes = new JTable(modeloTabla);

        scroll = new JScrollPane(tablaEstudiantes);

        scroll.setBounds(20, 400, 500, 120);

        add(scroll);

        tablaEstudiantes.getSelectionModel().addListSelectionListener(e -> {

            filaSeleccionada = tablaEstudiantes.getSelectedRow();

            if (filaSeleccionada >= 0) {

                txtCarnet.setText(modeloTabla.getValueAt(filaSeleccionada, 0).toString());
                txtNombre.setText(modeloTabla.getValueAt(filaSeleccionada, 1).toString());
                txtApellidos.setText(modeloTabla.getValueAt(filaSeleccionada, 2).toString());
                txtCorreo.setText(modeloTabla.getValueAt(filaSeleccionada, 3).toString());
            }
        });

        btnGuardar.addActionListener(this);
        btnLimpiar.addActionListener(this);
        btnEditar.addActionListener(this);
        btnEliminar.addActionListener(this);

        // 🔥 IMPORTANTE: agregar listener del menú
        btnMenu.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnMenu) {

            this.dispose(); // cerrar ventana actual
            new FrmPrincipal(); // volver al menú
        }

        if (e.getSource() == btnGuardar) {

            String carnet = txtCarnet.getText();
            String nombre = txtNombre.getText();
            String apellidos = txtApellidos.getText();
            String correo = txtCorreo.getText();

            if (carnet.isEmpty() || nombre.isEmpty()
                    || apellidos.isEmpty() || correo.isEmpty()) {

                JOptionPane.showMessageDialog(null,
                        "No deje campos vacios");
                return;
            }

            if (!carnet.matches("\\d{4}-\\d{2}-\\d{4}")) {
                JOptionPane.showMessageDialog(null,
                        "Formato de carnet invalido. Ejemplo: 0905-25-2522");
                return;
            }

            if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
                JOptionPane.showMessageDialog(null,
                        "El nombre solo debe contener letras");
                return;
            }

            if (!apellidos.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
                JOptionPane.showMessageDialog(null,
                        "Los apellidos solo deben contener letras");
                return;
            }

            modeloTabla.addRow(new Object[]{
                carnet,
                nombre,
                apellidos,
                correo
            });

            JOptionPane.showMessageDialog(null,
                    "Estudiante guardado correctamente");

            limpiar();
        }

        if (e.getSource() == btnEditar) {

            if (filaSeleccionada >= 0) {

                modeloTabla.setValueAt(txtCarnet.getText(), filaSeleccionada, 0);
                modeloTabla.setValueAt(txtNombre.getText(), filaSeleccionada, 1);
                modeloTabla.setValueAt(txtApellidos.getText(), filaSeleccionada, 2);
                modeloTabla.setValueAt(txtCorreo.getText(), filaSeleccionada, 3);

                JOptionPane.showMessageDialog(null, "Estudiante actualizado");
            }
        }

        if (e.getSource() == btnEliminar) {

            if (filaSeleccionada >= 0) {

                modeloTabla.removeRow(filaSeleccionada);

                JOptionPane.showMessageDialog(null, "Estudiante eliminado");
                limpiar();
            }
        }

        if (e.getSource() == btnLimpiar) {
            limpiar();
        }
    }

    void limpiar() {
        txtCarnet.setText("");
        txtNombre.setText("");
        txtApellidos.setText("");
        txtCorreo.setText("");
        filaSeleccionada = -1;
    }
}