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

import vista.FrmPrincipal;

public class FormCurso extends JFrame implements ActionListener {

    JLabel lblTitulo;

    JLabel lblCodigo;
    JLabel lblNombre;
    JLabel lblCreditos;
    JLabel lblCupo;

    JTextField txtCodigo;
    JTextField txtNombre;
    JTextField txtCreditos;
    JTextField txtCupo;

    JButton btnGuardar;
    JButton btnLimpiar;
    JButton btnMenu;
    JButton btnEditar;
    JButton btnEliminar;

    JTable tablaCursos;
    DefaultTableModel modeloTabla;
    JScrollPane scroll;

    int filaSeleccionada = -1;

    public FormCurso() {

        setTitle("Formulario Curso");
        setSize(550, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        lblTitulo = new JLabel("CURSOS");
        lblTitulo.setBounds(200, 10, 200, 30);
        add(lblTitulo);

        lblCodigo = new JLabel("Codigo:");
        lblCodigo.setBounds(50, 70, 100, 30);
        add(lblCodigo);

        lblNombre = new JLabel("Nombre del curso:");
        lblNombre.setBounds(50, 120, 150, 30);
        add(lblNombre);

        lblCreditos = new JLabel("Creditos:");
        lblCreditos.setBounds(50, 170, 100, 30);
        add(lblCreditos);

        lblCupo = new JLabel("Cupo:");
        lblCupo.setBounds(50, 220, 100, 30);
        add(lblCupo);

        txtCodigo = new JTextField();
        txtCodigo.setBounds(180, 70, 200, 30);
        add(txtCodigo);

        txtNombre = new JTextField();
        txtNombre.setBounds(180, 120, 200, 30);
        add(txtNombre);

        txtCreditos = new JTextField();
        txtCreditos.setBounds(180, 170, 200, 30);
        add(txtCreditos);

        txtCupo = new JTextField();
        txtCupo.setBounds(180, 220, 200, 30);
        add(txtCupo);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(50, 300, 100, 40);
        add(btnGuardar);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(180, 300, 100, 40);
        add(btnLimpiar);

        btnMenu = new JButton("Menu");
        btnMenu.setBounds(310, 300, 100, 40);
        add(btnMenu);

        btnEditar = new JButton("Editar");
        btnEditar.setBounds(50, 350, 100, 40);
        add(btnEditar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(180, 350, 100, 40);
        add(btnEliminar);

        modeloTabla = new DefaultTableModel();

        modeloTabla.addColumn("Codigo");
        modeloTabla.addColumn("Nombre del curso");
        modeloTabla.addColumn("Creditos");
        modeloTabla.addColumn("Cupo");

        tablaCursos = new JTable(modeloTabla);

        tablaCursos.getColumnModel().getColumn(0).setPreferredWidth(80);
        tablaCursos.getColumnModel().getColumn(1).setPreferredWidth(220);
        tablaCursos.getColumnModel().getColumn(2).setPreferredWidth(80);
        tablaCursos.getColumnModel().getColumn(3).setPreferredWidth(80);

        scroll = new JScrollPane(tablaCursos);
        scroll.setBounds(20, 410, 500, 120);
        add(scroll);

        tablaCursos.getSelectionModel().addListSelectionListener(e -> {

            filaSeleccionada = tablaCursos.getSelectedRow();

            if (filaSeleccionada >= 0) {

                txtCodigo.setText(modeloTabla.getValueAt(filaSeleccionada, 0).toString());
                txtNombre.setText(modeloTabla.getValueAt(filaSeleccionada, 1).toString());
                txtCreditos.setText(modeloTabla.getValueAt(filaSeleccionada, 2).toString());
                txtCupo.setText(modeloTabla.getValueAt(filaSeleccionada, 3).toString());
            }
        });

        btnGuardar.addActionListener(this);
        btnLimpiar.addActionListener(this);
        btnEditar.addActionListener(this);
        btnEliminar.addActionListener(this);
        btnMenu.addActionListener(this); // 🔥 AGREGADO

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnMenu) {

            this.dispose();
            new FrmPrincipal();
        }

        if (e.getSource() == btnGuardar) {

            String codigo = txtCodigo.getText();
            String nombre = txtNombre.getText();
            String creditos = txtCreditos.getText();
            String cupo = txtCupo.getText();

            if (codigo.isEmpty() || nombre.isEmpty()
                    || creditos.isEmpty() || cupo.isEmpty()) {

                JOptionPane.showMessageDialog(null,
                        "No deje campos vacios");
                return;
            }

            if (!codigo.matches("[a-zA-Z0-9-]+")) {
                JOptionPane.showMessageDialog(null,
                        "Codigo invalido (ej: INF-101)");
                return;
            }

            if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
                JOptionPane.showMessageDialog(null,
                        "El nombre del curso solo debe contener letras");
                return;
            }

            if (!creditos.matches("[0-9]+")) {
                JOptionPane.showMessageDialog(null,
                        "Creditos solo deben ser numeros");
                return;
            }

            if (!cupo.matches("[0-9]+")) {
                JOptionPane.showMessageDialog(null,
                        "Cupo solo debe ser numeros");
                return;
            }

            modeloTabla.addRow(new Object[]{
                codigo,
                nombre,
                creditos,
                cupo
            });

            JOptionPane.showMessageDialog(null,
                    "Curso guardado correctamente");

            limpiar();
        }

        if (e.getSource() == btnEditar) {

            if (filaSeleccionada >= 0) {

                modeloTabla.setValueAt(txtCodigo.getText(), filaSeleccionada, 0);
                modeloTabla.setValueAt(txtNombre.getText(), filaSeleccionada, 1);
                modeloTabla.setValueAt(txtCreditos.getText(), filaSeleccionada, 2);
                modeloTabla.setValueAt(txtCupo.getText(), filaSeleccionada, 3);

                JOptionPane.showMessageDialog(null,
                        "Curso actualizado");
            }
        }

        if (e.getSource() == btnEliminar) {

            if (filaSeleccionada >= 0) {

                modeloTabla.removeRow(filaSeleccionada);

                JOptionPane.showMessageDialog(null,
                        "Curso eliminado");

                limpiar();
            }
        }

        if (e.getSource() == btnLimpiar) {
            limpiar();
        }
    }

    void limpiar() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtCreditos.setText("");
        txtCupo.setText("");
        filaSeleccionada = -1;
    }
}