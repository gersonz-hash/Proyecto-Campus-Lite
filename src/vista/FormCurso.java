package vista;

import Persistencia.PersistenciaCursos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import modelo.Cursos;
import modelo.Datos;

public class FormCurso extends JFrame implements ActionListener {

    JLabel lblTitulo;

    JLabel lblCodigo;
    JLabel lblNombre;
    JLabel lblCarrera;
    JLabel lblCupo;

    JTextField txtCodigo;
    JTextField txtNombre;
    JTextField txtCupo;

    JComboBox<String> cbCarrera;

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
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        lblTitulo = new JLabel("CURSOS");
        lblTitulo.setBounds(200, 10, 200, 30);
        add(lblTitulo);

        lblCodigo = new JLabel("Código del curso:");
        lblCodigo.setBounds(50, 70, 150, 30);
        add(lblCodigo);

        lblNombre = new JLabel("Nombre del curso:");
        lblNombre.setBounds(50, 120, 160, 30);
        add(lblNombre);

        // CAMBIO DE CREDITOS A CARRERA
        lblCarrera = new JLabel("Carrera:");
        lblCarrera.setBounds(50, 170, 100, 30);
        add(lblCarrera);

        lblCupo = new JLabel("Cupo:");
        lblCupo.setBounds(50, 220, 100, 30);
        add(lblCupo);

        txtCodigo = new JTextField();
        txtCodigo.setBounds(180, 70, 200, 30);
        add(txtCodigo);

        txtNombre = new JTextField();
        txtNombre.setBounds(180, 120, 200, 30);
        add(txtNombre);

        // COMBOBOX DE CARRERAS
        cbCarrera = new JComboBox<>();
        cbCarrera.setBounds(180, 170, 200, 30);

        cbCarrera.addItem("Seleccione");

        cbCarrera.addItem("Ingeniería en Sistemas");
        cbCarrera.addItem("Derecho");
        cbCarrera.addItem("Administración");
        cbCarrera.addItem("Medicina");
        cbCarrera.addItem("Arquitectura");

        add(cbCarrera);

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

        modeloTabla.addColumn("Código del curso");
        modeloTabla.addColumn("Nombre del curso");
        modeloTabla.addColumn("Carrera");
        modeloTabla.addColumn("Cupo");

        tablaCursos = new JTable(modeloTabla);

        scroll = new JScrollPane(tablaCursos);
        scroll.setBounds(20, 410, 500, 120);
        add(scroll);

        // CARGAR DATOS EN TABLA
        for (Cursos c : Datos.listaCursos) {

            modeloTabla.addRow(new Object[]{
                c.getCodigo(),
                c.getNombre(),
                c.getCreditos(),
                c.getCupo()
            });
        }

        tablaCursos.getSelectionModel().addListSelectionListener(e -> {

            filaSeleccionada = tablaCursos.getSelectedRow();

            if (filaSeleccionada >= 0) {

                txtCodigo.setText(
                        modeloTabla.getValueAt(
                                filaSeleccionada,
                                0).toString());

                txtNombre.setText(
                        modeloTabla.getValueAt(
                                filaSeleccionada,
                                1).toString());

                cbCarrera.setSelectedItem(
                        modeloTabla.getValueAt(
                                filaSeleccionada,
                                2).toString());

                txtCupo.setText(
                        modeloTabla.getValueAt(
                                filaSeleccionada,
                                3).toString());
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

        if (e.getSource() == btnMenu) {

            this.dispose();

            new FrmPrincipal();
        }

        // GUARDAR
        if (e.getSource() == btnGuardar) {

            String codigo = txtCodigo.getText();
            String nombreCurso = txtNombre.getText();
            String carrera = cbCarrera.getSelectedItem().toString();
            String cupo = txtCupo.getText();

            if (codigo.isEmpty()
                    || nombreCurso.isEmpty()
                    || cupo.isEmpty()
                    || cbCarrera.getSelectedIndex() == 0) {

                JOptionPane.showMessageDialog(
                        null,
                        "No deje campos vacíos");

                return;
            }

            if (!codigo.matches("[a-zA-Z0-9-]+")) {

                JOptionPane.showMessageDialog(
                        null,
                        "Código inválido");

                return;
            }

            if (!nombreCurso.matches(
                    "[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {

                JOptionPane.showMessageDialog(
                        null,
                        "Nombre inválido");

                return;
            }

            // VALIDAR DUPLICADO
            for (Cursos cu : Datos.listaCursos) {

                if (cu.getCodigo().equals(codigo)) {

                    JOptionPane.showMessageDialog(
                            null,
                            "El código ya existe");

                    return;
                }
            }

            Cursos c = new Cursos(
                    codigo,
                    nombreCurso,
                    cbCarrera.getSelectedIndex(),
                    Integer.parseInt(cupo)
            );

            Datos.listaCursos.add(c);

            PersistenciaCursos.guardarCursos();

            modeloTabla.addRow(new Object[]{
                c.getCodigo(),
                c.getNombre(),
                carrera,
                c.getCupo()
            });

            JOptionPane.showMessageDialog(
                    null,
                    "Curso guardado correctamente");

            limpiar();
        }

        // EDITAR
        if (e.getSource() == btnEditar) {

            if (filaSeleccionada >= 0) {

                Cursos c =
                        Datos.listaCursos.get(filaSeleccionada);

                c.setCodigo(txtCodigo.getText());

                c.setNombre(txtNombre.getText());

                c.setCreditos(
                        cbCarrera.getSelectedIndex());

                c.setCupo(
                        Integer.parseInt(
                                txtCupo.getText()));

                modeloTabla.setValueAt(
                        c.getCodigo(),
                        filaSeleccionada,
                        0);

                modeloTabla.setValueAt(
                        c.getNombre(),
                        filaSeleccionada,
                        1);

                modeloTabla.setValueAt(
                        cbCarrera.getSelectedItem().toString(),
                        filaSeleccionada,
                        2);

                modeloTabla.setValueAt(
                        c.getCupo(),
                        filaSeleccionada,
                        3);

                PersistenciaCursos.guardarCursos();

                JOptionPane.showMessageDialog(
                        null,
                        "Curso actualizado");
            }
        }

        // ELIMINAR
        if (e.getSource() == btnEliminar) {

            if (filaSeleccionada >= 0) {

                Datos.listaCursos.remove(filaSeleccionada);

                PersistenciaCursos.guardarCursos();

                modeloTabla.removeRow(filaSeleccionada);

                JOptionPane.showMessageDialog(
                        null,
                        "Curso eliminado");

                limpiar();
            }
        }

        // LIMPIAR
        if (e.getSource() == btnLimpiar) {
            limpiar();
        }
    }

    void limpiar() {

        txtCodigo.setText("");
        txtNombre.setText("");
        txtCupo.setText("");

        cbCarrera.setSelectedIndex(0);

        filaSeleccionada = -1;
    }
}