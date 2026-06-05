package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import modelo.*;
import persistencia.PersistenciaCursos;
import persistencia.PersistenciaEstudiantes;
import persistencia.PersistenciaEvaluaciones;

public class FrmEvaluacion extends JFrame {

    private boolean cargando = false;

    public FrmEvaluacion() {

        setTitle("Campus Lite - Evaluaciones");
        setSize(1020, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);
        getContentPane().setBackground(new Color(240, 248, 255));

        PersistenciaCursos.cargarCursos();
        PersistenciaEstudiantes.cargarEstudiantes();

        JLabel lblCampus = new JLabel("CAMPUS LITE - EVALUACIONES");
        lblCampus.setBounds(330, 20, 400, 30);
        lblCampus.setFont(new Font("Arial", Font.BOLD, 22));
        lblCampus.setForeground(new Color(20, 33, 61));
        getContentPane().add(lblCampus);

        JComboBox<String> cbCarrera = new JComboBox<>();
        cbCarrera.setBounds(40, 70, 170, 32);
        cbCarrera.addItem("Carrera");
        cbCarrera.addItem("Ingeniería en Sistemas");
        cbCarrera.addItem("Derecho");
        cbCarrera.addItem("Administración");
        cbCarrera.addItem("Medicina");
        cbCarrera.addItem("Arquitectura");
        cbCarrera.setFont(new Font("Arial", Font.PLAIN, 13));
        cbCarrera.setBackground(Color.WHITE);
        getContentPane().add(cbCarrera);

        JComboBox<String> cbCurso = new JComboBox<>();
        cbCurso.setBounds(230, 70, 180, 32);
        cbCurso.addItem("Curso");
        cbCurso.setFont(new Font("Arial", Font.PLAIN, 13));
        cbCurso.setBackground(Color.WHITE);
        getContentPane().add(cbCurso);

        JComboBox<String> cbTipo = new JComboBox<>();
        cbTipo.setBounds(430, 70, 170, 32);
        cbTipo.addItem("Tipo de Evaluación");
        cbTipo.addItem("Examenes");
        cbTipo.addItem("Laboratorio");
        cbTipo.addItem("Proyecto");
        cbTipo.setFont(new Font("Arial", Font.PLAIN, 13));
        cbTipo.setBackground(Color.WHITE);
        getContentPane().add(cbTipo);

        JComboBox<String> cbEstudiante = new JComboBox<>();
        cbEstudiante.setBounds(620, 70, 180, 32);
        cbEstudiante.addItem("Estudiante");
        cbEstudiante.setFont(new Font("Arial", Font.PLAIN, 13));
        cbEstudiante.setBackground(Color.WHITE);
        getContentPane().add(cbEstudiante);

        JTextField txtNota = new JTextField();
        txtNota.setBounds(812, 69, 70, 32);
        txtNota.setText("Nota");
        txtNota.setFont(new Font("Arial", Font.PLAIN, 13));
        txtNota.setBackground(Color.WHITE);
        txtNota.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 1));
        getContentPane().add(txtNota);

        JTextField txtPonderacion = new JTextField();
        txtPonderacion.setBounds(894, 69, 100, 32);
        txtPonderacion.setText("Ponderación");
        txtPonderacion.setFont(new Font("Arial", Font.PLAIN, 13));
        txtPonderacion.setBackground(new Color(230, 240, 250));
        txtPonderacion.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 1));
        txtPonderacion.setEditable(false);
        getContentPane().add(txtPonderacion);

        txtNota.addFocusListener(
                new java.awt.event.FocusAdapter() {

                    public void focusGained(
                            java.awt.event.FocusEvent evt) {

                        if (txtNota.getText()
                                .equals("Nota")) {

                            txtNota.setText("");
                        }
                    }

                    public void focusLost(
                            java.awt.event.FocusEvent evt) {

                        if (txtNota.getText()
                                .isEmpty()) {

                            txtNota.setText("Nota");
                        }
                    }
                });

        cbCarrera.addActionListener(e -> {

            cargando = true;

            cbCurso.removeAllItems();
            cbCurso.addItem("Curso");

            cbEstudiante.removeAllItems();
            cbEstudiante.addItem("Estudiante");

            String carrera = (String) cbCarrera.getSelectedItem();

            if (carrera == null || carrera.equals("Carrera")) {
                cargando = false;
                return;
            }

            for (Cursos c : Datos.listaCursos) {

                if (c != null &&
                        c.getCarrera() != null &&
                        c.getNombre() != null &&
                        c.getCarrera().trim().equalsIgnoreCase(carrera.trim())) {

                    cbCurso.addItem(c.getNombre());
                }
            }

            for (Inscripcion i : Datos.listaInscripciones) {

                if (i != null &&
                        i.getCurso() != null &&
                        i.getCurso().getNombre() != null &&
                        i.getCurso().getNombre().trim().equalsIgnoreCase(carrera.trim())) {

                    Estudiante est = i.getEstudiante();

                    if (est != null) {

                        String nombreCompleto =
                                est.getNombre() + " " + est.getApellidos();

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
                            cbEstudiante.addItem(nombreCompleto);
                        }
                    }
                }
            }

            cargando = false;
        });

        cbTipo.addActionListener(e -> {

            String tipo = (String) cbTipo.getSelectedItem();

            if (tipo == null) return;

            switch (tipo) {
                case "Examenes":
                    txtPonderacion.setText("65");
                    break;
                case "Laboratorio":
                    txtPonderacion.setText("15");
                    break;
                case "Proyecto":
                    txtPonderacion.setText("20");
                    break;
                default:
                    txtPonderacion.setText("Ponderación");
                    txtNota.setText("Nota");
            }
        });

        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Carrera");
        modelo.addColumn("Curso");
        modelo.addColumn("Tipo");
        modelo.addColumn("Estudiante");
        modelo.addColumn("Nota");
        modelo.addColumn("Ponderación");

        JTable tabla = new JTable(modelo);
        tabla.setFont(new Font("Arial", Font.PLAIN, 13));
        tabla.setRowHeight(26);
        tabla.setGridColor(new Color(180, 205, 225));
        tabla.setSelectionBackground(new Color(173, 216, 230));
        tabla.setSelectionForeground(Color.BLACK);
        tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        tabla.getTableHeader().setBackground(new Color(20, 33, 61));
        tabla.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(40, 160, 930, 220);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(20, 33, 61), 2));
        getContentPane().add(scroll);

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
                    ev.getCarrera(),
                    ev.getCurso(),
                    tipo,
                    ev.getNombre(),
                    ev.getNota(),
                    ev.getPorcentaje()
            });
        }

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(260, 400, 100, 32);
        btnAgregar.setBackground(new Color(46, 139, 87));
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setFont(new Font("Arial", Font.BOLD, 13));
        btnAgregar.setOpaque(true);
        btnAgregar.setContentAreaFilled(true);
        btnAgregar.setBorderPainted(false);
        getContentPane().add(btnAgregar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(390, 400, 100, 32);
        btnEliminar.setBackground(new Color(220, 20, 60));
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFont(new Font("Arial", Font.BOLD, 13));
        btnEliminar.setOpaque(true);
        btnEliminar.setContentAreaFilled(true);
        btnEliminar.setBorderPainted(false);
        getContentPane().add(btnEliminar);

        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(520, 400, 100, 32);
        btnLimpiar.setBackground(new Color(70, 130, 180));
        btnLimpiar.setForeground(Color.WHITE);
        btnLimpiar.setFont(new Font("Arial", Font.BOLD, 13));
        btnLimpiar.setOpaque(true);
        btnLimpiar.setContentAreaFilled(true);
        btnLimpiar.setBorderPainted(false);
        getContentPane().add(btnLimpiar);

        JButton btnRegresar = new JButton("↩");
        btnRegresar.setBounds(650, 400, 70, 32);
        btnRegresar.setBackground(new Color(20, 33, 61));
        btnRegresar.setForeground(Color.WHITE);
        btnRegresar.setFont(new Font("Arial", Font.BOLD, 15));
        btnRegresar.setOpaque(true);
        btnRegresar.setContentAreaFilled(true);
        btnRegresar.setBorderPainted(false);
        getContentPane().add(btnRegresar);

        btnAgregar.addActionListener(e -> {

            if (cbCarrera.getSelectedIndex() == 0 ||
                    cbCurso.getSelectedIndex() == 0 ||
                    cbTipo.getSelectedIndex() == 0 ||
                    cbEstudiante.getSelectedIndex() == 0) {

                JOptionPane.showMessageDialog(null, "Complete todos los campos");
                return;
            }

            double nota;

            try {
                nota = Double.parseDouble(txtNota.getText());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Nota inválida");
                return;
            }

            String tipo = (String) cbTipo.getSelectedItem();

            String estudianteSeleccionado =
                    cbEstudiante.getSelectedItem().toString();

            for (Evaluacion evaluacionGuardada : Datos.listaEvaluaciones) {

                String tipoGuardado =
                        evaluacionGuardada.getClass().getSimpleName();

                String tipoSeleccionado =
                        tipo.equals("Examenes") ? "ExamenEscrito" : tipo;

                if (evaluacionGuardada.getNombre().equals(estudianteSeleccionado)
                        && tipoGuardado.equals(tipoSeleccionado)) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Este estudiante ya tiene registrada esta evaluación");

                    return;
                }
            }

            if (tipo.equals("Examenes") && nota > 65) return;
            if (tipo.equals("Laboratorio") && nota > 15) return;
            if (tipo.equals("Proyecto") && nota > 20) return;

            double ponderacion = Double.parseDouble(txtPonderacion.getText());

            Evaluacion ev = crearEvaluacion(
                    cbCarrera.getSelectedItem().toString(),
                    cbCurso.getSelectedItem().toString(),
                    tipo,
                    estudianteSeleccionado,
                    nota,
                    ponderacion
            );

            Datos.listaEvaluaciones.add(ev);
            PersistenciaEvaluaciones.guardarEvaluaciones();

            modelo.addRow(new Object[]{
                    ev.getCarrera(),
                    ev.getCurso(),
                    tipo,
                    ev.getNombre(),
                    ev.getNota(),
                    ev.getPorcentaje()
            });

            txtNota.setText("Nota");
            txtPonderacion.setText("Ponderación");
        });

        btnEliminar.addActionListener(e -> {

            int fila = tabla.getSelectedRow();

            if (fila < 0) {
                JOptionPane.showMessageDialog(null, "Seleccione fila");
                return;
            }

            Datos.listaEvaluaciones.remove(fila);
            modelo.removeRow(fila);
            PersistenciaEvaluaciones.guardarEvaluaciones();
        });

        btnLimpiar.addActionListener(e -> {

            cargando = true;

            cbCarrera.setSelectedIndex(0);

            cbCurso.removeAllItems();
            cbCurso.addItem("Curso");

            cbEstudiante.removeAllItems();
            cbEstudiante.addItem("Estudiante");

            cbTipo.setSelectedIndex(0);

            txtNota.setText("Nota");
            txtPonderacion.setText("Ponderación");

            cargando = false;
        });

        btnRegresar.addActionListener(e -> dispose());

        setVisible(true);
    }

    private Evaluacion crearEvaluacion(
            String carrera,
            String curso,
            String tipo,
            String estudiante,
            double nota,
            double ponderacion) {

        if (tipo.equals("Examenes")) {
            return new ExamenEscrito(carrera, curso, estudiante, nota, ponderacion);
        } else if (tipo.equals("Laboratorio")) {
            return new Laboratorio(carrera, curso, estudiante, nota, ponderacion);
        } else {
            return new Proyecto(carrera, curso, estudiante, nota, ponderacion);
        }
    }
}