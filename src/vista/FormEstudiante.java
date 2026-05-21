package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

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

    ArrayList<Estudiante> listaEstudiantes = new ArrayList<>();

    public FormEstudiante() {

        setTitle("Formulario Estudiante");
        setSize(550, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
        btnMenu.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnGuardar) {

            String carnet = txtCarnet.getText();
            String nombre = txtNombre.getText();
            String apellidos = txtApellidos.getText();
            String correo = txtCorreo.getText();

            if (carnet.isEmpty() || nombre.isEmpty() || apellidos.isEmpty() || correo.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No deje campos vacios");
                return;
            }

            if (!carnet.matches("\\d{4}-\\d{2}-\\d{4}")) {
                JOptionPane.showMessageDialog(null, "Carnet invalido");
                return;
            }

            if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
                JOptionPane.showMessageDialog(null, "Nombre invalido");
                return;
            }

            Estudiante e1 = new Estudiante(carnet, nombre, apellidos, correo);

            listaEstudiantes.add(e1);

            modeloTabla.addRow(new Object[]{
                e1.getCarnet(),
                e1.getNombre(),
                e1.getApellidos(),
                e1.getCorreo()
            });

            JOptionPane.showMessageDialog(null, "Estudiante guardado");
            limpiar();
        }

        if (e.getSource() == btnEditar) {

            if (filaSeleccionada >= 0) {

                Estudiante e1 = listaEstudiantes.get(filaSeleccionada);

                e1.setCarnet(txtCarnet.getText());
                e1.setNombre(txtNombre.getText());
                e1.setApellidos(txtApellidos.getText());
                e1.setCorreo(txtCorreo.getText());

                modeloTabla.setValueAt(e1.getCarnet(), filaSeleccionada, 0);
                modeloTabla.setValueAt(e1.getNombre(), filaSeleccionada, 1);
                modeloTabla.setValueAt(e1.getApellidos(), filaSeleccionada, 2);
                modeloTabla.setValueAt(e1.getCorreo(), filaSeleccionada, 3);

                JOptionPane.showMessageDialog(null, "Actualizado");
            }
        }

        if (e.getSource() == btnEliminar) {

            if (filaSeleccionada >= 0) {

                listaEstudiantes.remove(filaSeleccionada);
                modeloTabla.removeRow(filaSeleccionada);

                JOptionPane.showMessageDialog(null, "Eliminado");
                limpiar();
            }
        }

        if (e.getSource() == btnLimpiar) {
            limpiar();
        }

        if (e.getSource() == btnMenu) {
            this.dispose();
            new FrmPrincipal();
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