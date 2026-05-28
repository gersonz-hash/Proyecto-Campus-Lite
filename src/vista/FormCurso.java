package vista;

import Persistencia.PersistenciaCursos;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import modelo.*;

public class FormCurso extends JFrame implements ActionListener {

    JTextField txtCodigo, txtNombre, txtCupo;
    JComboBox<String> cbCarrera;

    JButton btnGuardar, btnLimpiar, btnMenu, btnEditar, btnEliminar;

    JTable tablaCursos;
    DefaultTableModel modeloTabla;

    int filaSeleccionada = -1;

    public FormCurso() {

        setTitle("Formulario Curso");
        setSize(600, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        getContentPane().setBackground(new Color(240, 248, 255));

        JLabel lblTitulo = new JLabel("GESTION DE CURSOS");
        lblTitulo.setBounds(150, 10, 350, 40);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(lblTitulo);

        JLabel lblCodigo = new JLabel("Código:");
        lblCodigo.setBounds(50, 70, 150, 30);
        add(lblCodigo);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(50, 120, 150, 30);
        add(lblNombre);

        JLabel lblCarrera = new JLabel("Carrera:");
        lblCarrera.setBounds(50, 170, 150, 30);
        add(lblCarrera);

        JLabel lblCupo = new JLabel("Cupo:");
        lblCupo.setBounds(50, 220, 150, 30);
        add(lblCupo);

        txtCodigo = new JTextField();
        txtCodigo.setBounds(180, 70, 250, 30);
        add(txtCodigo);

        txtNombre = new JTextField();
        txtNombre.setBounds(180, 120, 250, 30);
        add(txtNombre);

        txtCupo = new JTextField();
        txtCupo.setBounds(180, 220, 250, 30);
        add(txtCupo);

        cbCarrera = new JComboBox<>();
        cbCarrera.setBounds(180, 170, 250, 30);
        cbCarrera.addItem("Seleccione");
        cbCarrera.addItem("Ingeniería en Sistemas");
        cbCarrera.addItem("Derecho");
        cbCarrera.addItem("Administración");
        cbCarrera.addItem("Medicina");
        cbCarrera.addItem("Arquitectura");
        add(cbCarrera);

        btnGuardar = new JButton("Guardar");
        btnEditar = new JButton("Editar");
        btnEliminar = new JButton("Eliminar");
        btnLimpiar = new JButton("Limpiar");
        btnMenu = new JButton("Menu");

        btnGuardar.setBounds(40, 300, 100, 40);
        btnEditar.setBounds(150, 300, 100, 40);
        btnEliminar.setBounds(260, 300, 120, 40);
        btnLimpiar.setBounds(390, 300, 100, 40);
        btnMenu.setBounds(220, 360, 100, 40);

        add(btnGuardar);
        add(btnEditar);
        add(btnEliminar);
        add(btnLimpiar);
        add(btnMenu);

        modeloTabla = new DefaultTableModel(
                new String[]{"Código", "Nombre", "Carrera", "Cupo"}, 0
        );

        tablaCursos = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tablaCursos);
        scroll.setBounds(20, 430, 550, 140);
        add(scroll);

        // 🔥 CARGA SEGURA
        PersistenciaCursos.cargarCursos();
        refrescarTabla();

        tablaCursos.getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()) {

                filaSeleccionada = tablaCursos.getSelectedRow();

                if (filaSeleccionada >= 0) {

                    txtCodigo.setText(modeloTabla.getValueAt(filaSeleccionada, 0).toString());
                    txtNombre.setText(modeloTabla.getValueAt(filaSeleccionada, 1).toString());
                    cbCarrera.setSelectedItem(modeloTabla.getValueAt(filaSeleccionada, 2).toString());
                    txtCupo.setText(modeloTabla.getValueAt(filaSeleccionada, 3).toString());
                }
            }
        });

        btnGuardar.addActionListener(this);
        btnEditar.addActionListener(this);
        btnEliminar.addActionListener(this);
        btnLimpiar.addActionListener(this);
        btnMenu.addActionListener(this);

        setVisible(true);
    }

    void refrescarTabla() {

        modeloTabla.setRowCount(0);

        for (Cursos c : Datos.listaCursos) {
            modeloTabla.addRow(new Object[]{
                    c.getCodigo(),
                    c.getNombre(),
                    c.getCarrera(),
                    c.getCupo()
            });
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnMenu) {
            dispose();
            new FrmPrincipal();
        }

        if (e.getSource() == btnGuardar) {

            if (cbCarrera.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "Seleccione carrera");
                return;
            }

            try {

                Cursos c = new Cursos(
                        txtCodigo.getText().trim(),
                        txtNombre.getText().trim(),
                        cbCarrera.getSelectedItem().toString(),
                        Integer.parseInt(txtCupo.getText().trim())
                );

                Datos.listaCursos.add(c);
                PersistenciaCursos.guardarCursos();
                refrescarTabla();
                limpiar();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Datos inválidos");
            }
        }

        if (e.getSource() == btnEditar) {

            if (filaSeleccionada < 0) {
                JOptionPane.showMessageDialog(null, "Seleccione un curso");
                return;
            }

            try {

                Cursos c = Datos.listaCursos.get(filaSeleccionada);

                c.setCodigo(txtCodigo.getText().trim());
                c.setNombre(txtNombre.getText().trim());

                if (cbCarrera.getSelectedIndex() != 0) {
                    c.setCarrera(cbCarrera.getSelectedItem().toString());
                }

                c.setCupo(Integer.parseInt(txtCupo.getText().trim()));

                PersistenciaCursos.guardarCursos();
                refrescarTabla();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al editar");
            }
        }

        if (e.getSource() == btnEliminar) {

            if (filaSeleccionada < 0) {
                JOptionPane.showMessageDialog(null, "Seleccione un curso");
                return;
            }

            Datos.listaCursos.remove(filaSeleccionada);
            PersistenciaCursos.guardarCursos();
            refrescarTabla();
            limpiar();
        }

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