package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Persistencia.PersistenciaEvaluaciones;

import modelo.Cursos;
import modelo.Datos;
import modelo.Estudiante;
import modelo.Evaluacion;
import modelo.ExamenEscrito;
import modelo.Inscripcion;
import modelo.Laboratorio;
import modelo.Proyecto;

public class FrmEvaluacion extends JFrame {

    private int filaSeleccionada = -1;

    public FrmEvaluacion() {

        setTitle("Campus Lite - Evaluaciones");
        setSize(1020, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel lblCampus = new JLabel("Campus Lite");
        lblCampus.setBounds(40, 20, 150, 30);
        getContentPane().add(lblCampus);

        JComboBox<String> cbCarrera = new JComboBox<>();
        cbCarrera.setBounds(40, 70, 140, 30);
        cbCarrera.addItem("Carrera");
        cbCarrera.addItem("Ingeniería en Sistemas");
        cbCarrera.addItem("Derecho");
        cbCarrera.addItem("Administración");
        cbCarrera.addItem("Medicina");
        cbCarrera.addItem("Arquitectura");
        getContentPane().add(cbCarrera);

        JComboBox<String> cbCurso = new JComboBox<>();
        cbCurso.setBounds(200, 70, 160, 30);
        cbCurso.addItem("Curso");
        getContentPane().add(cbCurso);

        JComboBox<String> cbTipo = new JComboBox<>();
        cbTipo.setBounds(380, 70, 160, 30);
        cbTipo.addItem("Tipo de Evaluación");
        cbTipo.addItem("Examenes");
        cbTipo.addItem("Laboratorio");
        cbTipo.addItem("Proyecto");
        getContentPane().add(cbTipo);

        JComboBox<String> cbEstudiante = new JComboBox<>();
        cbEstudiante.setBounds(560, 70, 180, 30);
        cbEstudiante.addItem("Estudiante");
        getContentPane().add(cbEstudiante);

        cbCarrera.addActionListener(e -> {

            cbCurso.removeAllItems();
            cbCurso.addItem("Curso");

            cbEstudiante.removeAllItems();
            cbEstudiante.addItem("Estudiante");

            String carreraSeleccionada =
                    cbCarrera.getSelectedItem().toString();

            for (Cursos c : Datos.listaCursos) {

                if (c.getCreditos()
                        == cbCarrera.getSelectedIndex()) {

                    cbCurso.addItem(c.getNombre());
                }
            }

            for (Inscripcion i :
                    Datos.listaInscripciones) {

                if (i.getCurso()
                        .getNombre()
                        .equals(carreraSeleccionada)) {

                    Estudiante e1 =
                            i.getEstudiante();

                    String nombreCompleto =
                            e1.getNombre()
                                    + " "
                                    + e1.getApellidos();

                    boolean existe = false;

                    for (int j = 0;
                         j < cbEstudiante.getItemCount();
                         j++) {

                        if (cbEstudiante.getItemAt(j)
                                .equals(nombreCompleto)) {

                            existe = true;
                            break;
                        }
                    }

                    if (!existe) {

                        cbEstudiante.addItem(
                                nombreCompleto);
                    }
                }
            }
        });

        JTextField txtNota = new JTextField();
        txtNota.setBounds(760, 70, 70, 30);
        txtNota.setText("Nota");
        txtNota.setEditable(false);
        getContentPane().add(txtNota);

        JTextField txtPonderacion = new JTextField();
        txtPonderacion.setBounds(850, 70, 110, 30);
        txtPonderacion.setText("Ponderación");
        getContentPane().add(txtPonderacion);

        txtPonderacion.addFocusListener(
                new java.awt.event.FocusAdapter() {

                    public void focusGained(
                            java.awt.event.FocusEvent evt) {

                        if (txtPonderacion.getText()
                                .equals("Ponderación")) {

                            txtPonderacion.setText("");
                        }
                    }

                    public void focusLost(
                            java.awt.event.FocusEvent evt) {

                        if (txtPonderacion.getText()
                                .isEmpty()) {

                            txtPonderacion.setText("Ponderación");
                        }
                    }
                });

        cbTipo.addActionListener(e -> {

            String tipo =
                    cbTipo.getSelectedItem().toString();

            if (tipo.equals("Examenes")) {

                txtNota.setText("65");

            } else if (tipo.equals("Laboratorio")) {

                txtNota.setText("15");

            } else if (tipo.equals("Proyecto")) {

                txtNota.setText("20");

            } else {

                txtNota.setText("Nota");
            }
        });

        JLabel lblLista =
                new JLabel("Lista de Evaluaciones");

        lblLista.setBounds(40, 120, 200, 30);
        getContentPane().add(lblLista);

        DefaultTableModel modelo =
                new DefaultTableModel();

        modelo.addColumn("Carrera");
        modelo.addColumn("Curso");
        modelo.addColumn("Tipo");
        modelo.addColumn("Estudiante");
        modelo.addColumn("Nota");
        modelo.addColumn("Ponderación");

        JTable tabla = new JTable(modelo);

        JScrollPane scroll =
                new JScrollPane(tabla);

        scroll.setBounds(40, 160, 920, 220);
        getContentPane().add(scroll);

        // CARGAR EVALUACIONES EXISTENTES EN TABLA
        for (Evaluacion ev : Datos.listaEvaluaciones) {

            String tipo = "";

            if (ev instanceof ExamenEscrito) {
                tipo = "Examenes";
            } else if (ev instanceof Laboratorio) {
                tipo = "Laboratorio";
            } else if (ev instanceof Proyecto) {
                tipo = "Proyecto";
            }

            modelo.addRow(new Object[]{
                    "",
                    "",
                    tipo,
                    ev.getNombre(),
                    ev.getNota(),
                    ev.getPorcentaje()
            });
        }

        JButton btnAgregar =
                new JButton("Agregar");

        btnAgregar.setBounds(220, 402, 90, 30);
        getContentPane().add(btnAgregar);

        JButton btnEditar =
                new JButton("Editar");

        btnEditar.setBounds(330, 402, 90, 30);
        getContentPane().add(btnEditar);

        JButton btnEliminar =
                new JButton("Eliminar");

        btnEliminar.setBounds(440, 402, 100, 30);
        getContentPane().add(btnEliminar);

        JButton btnLimpiar =
                new JButton("Limpiar");

        btnLimpiar.setBounds(560, 402, 90, 30);
        getContentPane().add(btnLimpiar);

        JButton btnRegresar =
                new JButton("↩");

        btnRegresar.setBounds(680, 402, 60, 30);
        getContentPane().add(btnRegresar);

        tabla.getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()) {

                filaSeleccionada =
                        tabla.getSelectedRow();

                if (filaSeleccionada >= 0) {

                    cbCarrera.setSelectedItem(
                            modelo.getValueAt(
                                    filaSeleccionada,
                                    0).toString());

                    cbCurso.setSelectedItem(
                            modelo.getValueAt(
                                    filaSeleccionada,
                                    1).toString());

                    cbTipo.setSelectedItem(
                            modelo.getValueAt(
                                    filaSeleccionada,
                                    2).toString());

                    cbEstudiante.setSelectedItem(
                            modelo.getValueAt(
                                    filaSeleccionada,
                                    3).toString());

                    txtNota.setText(
                            modelo.getValueAt(
                                    filaSeleccionada,
                                    4).toString());

                    txtPonderacion.setText(
                            modelo.getValueAt(
                                    filaSeleccionada,
                                    5).toString());
                }
            }
        });

        btnAgregar.addActionListener(e -> {

            if (!validarCampos(
                    cbCurso,
                    cbTipo,
                    cbEstudiante,
                    txtNota,
                    txtPonderacion)) {

                return;
            }

            String carrera =
                    cbCarrera.getSelectedItem().toString();

            String curso =
                    cbCurso.getSelectedItem().toString();

            String tipo =
                    cbTipo.getSelectedItem().toString();

            String estudiante =
                    cbEstudiante.getSelectedItem().toString();

            double nota =
                    Double.parseDouble(
                            txtNota.getText());

            double ponderacion =
                    Double.parseDouble(
                            txtPonderacion.getText());

            if (tipo.equals("Examenes")
                    && ponderacion > 65) {

                JOptionPane.showMessageDialog(
                        null,
                        "Examenes solo permite hasta 65 puntos");

                return;
            }

            if (tipo.equals("Laboratorio")
                    && ponderacion > 15) {

                JOptionPane.showMessageDialog(
                        null,
                        "Laboratorio solo permite hasta 15 puntos");

                return;
            }

            if (tipo.equals("Proyecto")
                    && ponderacion > 20) {

                JOptionPane.showMessageDialog(
                        null,
                        "Proyecto solo permite hasta 20 puntos");

                return;
            }

            if (ponderacion < 0) {

                JOptionPane.showMessageDialog(
                        null,
                        "No se permiten números negativos");

                return;
            }

            for (int i = 0;
                 i < modelo.getRowCount();
                 i++) {

                String estudianteTabla =
                        modelo.getValueAt(i, 3).toString();

                String tipoTabla =
                        modelo.getValueAt(i, 2).toString();

                if (i == filaSeleccionada) {
                    continue;
                }

                if (estudianteTabla.equals(estudiante)
                        && tipoTabla.equals(tipo)) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Ese estudiante ya tiene registrada esa evaluación");

                    return;
                }
            }

            Evaluacion evaluacion =
                    crearEvaluacion(
                            tipo,
                            estudiante,
                            nota,
                            ponderacion);

            if (filaSeleccionada >= 0) {

                modelo.setValueAt(
                        carrera,
                        filaSeleccionada,
                        0);

                modelo.setValueAt(
                        curso,
                        filaSeleccionada,
                        1);

                modelo.setValueAt(
                        tipo,
                        filaSeleccionada,
                        2);

                modelo.setValueAt(
                        estudiante,
                        filaSeleccionada,
                        3);

                modelo.setValueAt(
                        nota,
                        filaSeleccionada,
                        4);

                modelo.setValueAt(
                        ponderacion,
                        filaSeleccionada,
                        5);

                if (filaSeleccionada
                        < Datos.listaEvaluaciones.size()) {

                    Datos.listaEvaluaciones.set(
                            filaSeleccionada,
                            evaluacion);
                }

                PersistenciaEvaluaciones.guardarEvaluaciones();

                JOptionPane.showMessageDialog(
                        null,
                        "Evaluación actualizada correctamente");

            } else {

                Datos.listaEvaluaciones.add(
                        evaluacion);

                PersistenciaEvaluaciones.guardarEvaluaciones();

                modelo.addRow(new Object[]{
                        carrera,
                        curso,
                        tipo,
                        estudiante,
                        nota,
                        ponderacion
                });

                JOptionPane.showMessageDialog(
                        null,
                        "Evaluación agregada correctamente");
            }

            limpiar(
                    cbCarrera,
                    cbCurso,
                    cbTipo,
                    cbEstudiante,
                    txtNota,
                    txtPonderacion,
                    tabla);
        });

        btnEditar.addActionListener(e -> {

            if (filaSeleccionada < 0) {

                JOptionPane.showMessageDialog(
                        null,
                        "Seleccione una fila para editar");

                return;
            }

            JOptionPane.showMessageDialog(
                    null,
                    "Modifique los datos arriba y presione Agregar para actualizar");
        });

        btnEliminar.addActionListener(e -> {

            int fila =
                    tabla.getSelectedRow();

            if (fila < 0) {

                JOptionPane.showMessageDialog(
                        null,
                        "Seleccione una fila para eliminar");

                return;
            }

            if (fila
                    < Datos.listaEvaluaciones.size()) {

                Datos.listaEvaluaciones.remove(fila);
            }

            PersistenciaEvaluaciones.guardarEvaluaciones();

            modelo.removeRow(fila);

            filaSeleccionada = -1;

            tabla.clearSelection();

            JOptionPane.showMessageDialog(
                    null,
                    "Evaluación eliminada correctamente");

            limpiar(
                    cbCarrera,
                    cbCurso,
                    cbTipo,
                    cbEstudiante,
                    txtNota,
                    txtPonderacion,
                    tabla);
        });

        btnLimpiar.addActionListener(e -> {

            limpiar(
                    cbCarrera,
                    cbCurso,
                    cbTipo,
                    cbEstudiante,
                    txtNota,
                    txtPonderacion,
                    tabla);
        });

        btnRegresar.addActionListener(e -> {

            dispose();
        });

        setVisible(true);
    }

    private Evaluacion crearEvaluacion(
            String tipo,
            String estudiante,
            double nota,
            double ponderacion) {

        if (tipo.equals("Examenes")) {

            return new ExamenEscrito(
                    estudiante,
                    nota,
                    ponderacion);

        } else if (tipo.equals("Laboratorio")) {

            return new Laboratorio(
                    estudiante,
                    nota,
                    ponderacion);

        } else {

            return new Proyecto(
                    estudiante,
                    nota,
                    ponderacion);
        }
    }

    private boolean validarCampos(
            JComboBox<String> cbCurso,
            JComboBox<String> cbTipo,
            JComboBox<String> cbEstudiante,
            JTextField txtNota,
            JTextField txtPonderacion) {

        if (cbCurso.getSelectedIndex() == 0) {

            JOptionPane.showMessageDialog(
                    null,
                    "Seleccione un curso");

            return false;
        }

        if (cbTipo.getSelectedIndex() == 0) {

            JOptionPane.showMessageDialog(
                    null,
                    "Seleccione un tipo de evaluación");

            return false;
        }

        if (cbEstudiante.getSelectedIndex() == 0) {

            JOptionPane.showMessageDialog(
                    null,
                    "Seleccione un estudiante");

            return false;
        }

        if (txtNota.getText().isEmpty()
                || txtNota.getText().equals("Nota")
                || txtPonderacion.getText().isEmpty()
                || txtPonderacion.getText().equals("Ponderación")) {

            JOptionPane.showMessageDialog(
                    null,
                    "No deje campos vacíos");

            return false;
        }

        try {

            double nota =
                    Double.parseDouble(
                            txtNota.getText());

            double ponderacion =
                    Double.parseDouble(
                            txtPonderacion.getText());

            if (nota < 0) {

                JOptionPane.showMessageDialog(
                        null,
                        "La nota no puede ser negativa");

                return false;
            }

            if (ponderacion < 0) {

                JOptionPane.showMessageDialog(
                        null,
                        "La ponderación no puede ser negativa");

                return false;
            }

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    null,
                    "Solo se permiten números en ponderación");

            return false;
        }

        return true;
    }

    private void limpiar(
            JComboBox<String> cbCarrera,
            JComboBox<String> cbCurso,
            JComboBox<String> cbTipo,
            JComboBox<String> cbEstudiante,
            JTextField txtNota,
            JTextField txtPonderacion,
            JTable tabla) {

        tabla.clearSelection();

        cbCarrera.setSelectedIndex(0);

        cbCurso.removeAllItems();
        cbCurso.addItem("Curso");

        cbEstudiante.removeAllItems();
        cbEstudiante.addItem("Estudiante");

        cbTipo.setSelectedIndex(0);

        txtNota.setText("Nota");

        txtPonderacion.setText("Ponderación");

        filaSeleccionada = -1;
    }
}