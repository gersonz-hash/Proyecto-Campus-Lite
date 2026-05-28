package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Persistencia.PersistenciaCursos;
import Persistencia.PersistenciaEstudiantes;
import Persistencia.PersistenciaEvaluaciones;
import modelo.*;

public class FrmEvaluacion extends JFrame {

    private boolean cargando = false;

    public FrmEvaluacion() {

        setTitle("Campus Lite - Evaluaciones");
        setSize(1020, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        // CORRECCIÓN: cargar datos al abrir
        PersistenciaCursos.cargarCursos();
        PersistenciaEstudiantes.cargarEstudiantes();

        JLabel lblCampus = new JLabel("Campus Lite");
        lblCampus.setBounds(40, 20, 150, 30);
        getContentPane().add(lblCampus);

        JComboBox<String> cbCarrera = new JComboBox<>();
        cbCarrera.setBounds(40, 70, 170, 30);
        cbCarrera.addItem("Carrera");
        cbCarrera.addItem("Ingeniería en Sistemas");
        cbCarrera.addItem("Derecho");
        cbCarrera.addItem("Administración");
        cbCarrera.addItem("Medicina");
        cbCarrera.addItem("Arquitectura");
        getContentPane().add(cbCarrera);

        JComboBox<String> cbCurso = new JComboBox<>();
        cbCurso.setBounds(230, 70, 180, 30);
        cbCurso.addItem("Curso");
        getContentPane().add(cbCurso);

        JComboBox<String> cbTipo = new JComboBox<>();
        cbTipo.setBounds(430, 70, 170, 30);
        cbTipo.addItem("Tipo de Evaluación");
        cbTipo.addItem("Examenes");
        cbTipo.addItem("Laboratorio");
        cbTipo.addItem("Proyecto");
        getContentPane().add(cbTipo);

        JComboBox<String> cbEstudiante = new JComboBox<>();
        cbEstudiante.setBounds(620, 70, 180, 30);
        cbEstudiante.addItem("Estudiante");
        getContentPane().add(cbEstudiante);

        JTextField txtNota = new JTextField();
        txtNota.setBounds(820, 70, 70, 30);
        getContentPane().add(txtNota);

        JTextField txtPonderacion = new JTextField();
        txtPonderacion.setBounds(900, 70, 70, 30);
        txtPonderacion.setEditable(false);
        getContentPane().add(txtPonderacion);

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

            cargando = false;
        });

        cbCurso.addActionListener(e -> {

            if (cargando) return;

            cbEstudiante.removeAllItems();
            cbEstudiante.addItem("Estudiante");

            if (cbCurso.getSelectedIndex() == 0) return;

            String curso = (String) cbCurso.getSelectedItem();
            if (curso == null) return;

            for (Inscripcion i : Datos.listaInscripciones) {

                if (i != null &&
                        i.getCurso() != null &&
                        i.getCurso().getNombre() != null &&
                        i.getCurso().getNombre().trim().equalsIgnoreCase(curso.trim())) {

                    Estudiante est = i.getEstudiante();

                    if (est != null) {
                        cbEstudiante.addItem(
                                est.getNombre() + " " + est.getApellidos()
                        );
                    }
                }
            }
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
                    txtPonderacion.setText("");
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
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(40, 160, 930, 220);
        getContentPane().add(scroll);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(260, 400, 100, 30);
        getContentPane().add(btnAgregar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(390, 400, 100, 30);
        getContentPane().add(btnEliminar);

        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(520, 400, 100, 30);
        getContentPane().add(btnLimpiar);

        JButton btnRegresar = new JButton("↩");
        btnRegresar.setBounds(650, 400, 70, 30);
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

            if (tipo.equals("Examenes") && nota > 65) return;
            if (tipo.equals("Laboratorio") && nota > 15) return;
            if (tipo.equals("Proyecto") && nota > 20) return;

            double ponderacion = Double.parseDouble(txtPonderacion.getText());

            Evaluacion ev = crearEvaluacion(
                    cbCarrera.getSelectedItem().toString(),
                    cbCurso.getSelectedItem().toString(),
                    tipo,
                    cbEstudiante.getSelectedItem().toString(),
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

            txtNota.setText("");
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

            txtNota.setText("");
            txtPonderacion.setText("");

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