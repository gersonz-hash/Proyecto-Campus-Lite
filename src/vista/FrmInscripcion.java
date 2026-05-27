package vista;

import Persistencia.PersistenciaInscripciones;
import modelo.Cursos;
import modelo.Datos;
import modelo.Estudiante;
import modelo.Inscripcion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Font;

public class FrmInscripcion extends JFrame {

    private JPanel panel;

    public FrmInscripcion() {

        setTitle("Inscripciones");
        setSize(780, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(230, 230, 230));

        agregarComponentes();

        add(panel);
    }

    private void agregarComponentes() {

        JLabel titulo = new JLabel("INSCRIPCIONES");
        titulo.setBounds(280, 25, 300, 40);
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        panel.add(titulo);

        // CARNET
        JLabel lblCarnet = new JLabel("Carnet:");
        lblCarnet.setBounds(60, 90, 100, 30);
        lblCarnet.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(lblCarnet);

        JComboBox<String> cbCarnet = new JComboBox<>();
        cbCarnet.setBounds(160, 90, 170, 30);

        cbCarnet.addItem("Seleccione");

        for (Estudiante estudiante : Datos.listaEstudiantes) {

            cbCarnet.addItem(
                    estudiante.getCarnet());
        }

        panel.add(cbCarnet);

        // ESTUDIANTE
        JLabel lblEstudiante = new JLabel("Estudiante:");
        lblEstudiante.setBounds(360, 90, 120, 30);
        lblEstudiante.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(lblEstudiante);

        JTextField txtEstudiante = new JTextField();
        txtEstudiante.setBounds(470, 90, 220, 30);
        txtEstudiante.setFont(new Font("Arial", Font.PLAIN, 15));
        txtEstudiante.setEditable(false);
        panel.add(txtEstudiante);

        // CURSOS REALES
        JLabel lblCurso = new JLabel("Curso:");
        lblCurso.setBounds(60, 140, 100, 30);
        lblCurso.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(lblCurso);

        JComboBox<String> cbCurso = new JComboBox<>();
        cbCurso.setBounds(160, 140, 250, 30);
        cbCurso.setFont(new Font("Arial", Font.PLAIN, 15));

        cbCurso.addItem("Seleccione");

        for (Cursos curso : Datos.listaCursos) {

            cbCurso.addItem(
                    curso.getCodigo()
                            + " - "
                            + curso.getNombre());
        }

        panel.add(cbCurso);

        String[] columnas = {
                "Carnet",
                "Estudiante",
                "Curso"
        };

        DefaultTableModel modelo =
                new DefaultTableModel(null, columnas);

        JTable tabla = new JTable(modelo);

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(60, 210, 650, 180);
        panel.add(scroll);

        // CARGAR INSCRIPCIONES EXISTENTES
        for (Inscripcion i : Datos.listaInscripciones) {

            modelo.addRow(new Object[]{
                    i.getEstudiante().getCarnet(),
                    i.getEstudiante().getNombre()
                            + " "
                            + i.getEstudiante().getApellidos(),
                    i.getCurso().getNombre()
            });
        }

        JButton btnInscribir = new JButton("INSCRIBIR");
        btnInscribir.setBounds(200, 415, 120, 30);
        panel.add(btnInscribir);

        JButton btnLimpiar = new JButton("LIMPIAR");
        btnLimpiar.setBounds(340, 415, 120, 30);
        panel.add(btnLimpiar);

        JButton btnRegresar = new JButton("↩");
        btnRegresar.setBounds(480, 415, 70, 30);
        panel.add(btnRegresar);

        // MOSTRAR NOMBRE
        cbCarnet.addActionListener(e -> {

            int indice = cbCarnet.getSelectedIndex();

            if (indice > 0) {

                Estudiante estudiante =
                        Datos.listaEstudiantes.get(indice - 1);

                txtEstudiante.setText(
                        estudiante.getNombre()
                                + " "
                                + estudiante.getApellidos());

            } else {

                txtEstudiante.setText("");
            }
        });

        // INSCRIBIR
        btnInscribir.addActionListener(e -> {

            if (cbCarnet.getSelectedIndex() == 0) {

                JOptionPane.showMessageDialog(
                        null,
                        "Seleccione un carnet");

                return;
            }

            if (cbCurso.getSelectedIndex() == 0) {

                JOptionPane.showMessageDialog(
                        null,
                        "Seleccione un curso");

                return;
            }

            Estudiante estudiante =
                    Datos.listaEstudiantes.get(
                            cbCarnet.getSelectedIndex() - 1);

            Cursos curso =
                    Datos.listaCursos.get(
                            cbCurso.getSelectedIndex() - 1);

            // VALIDAR DUPLICADO
            for (Inscripcion i : Datos.listaInscripciones) {

                if (i.getEstudiante()
                        .getCarnet()
                        .equals(estudiante.getCarnet())

                        &&

                        i.getCurso()
                                .getCodigo()
                                .equals(curso.getCodigo())) {

                    JOptionPane.showMessageDialog(
                            null,
                            "El estudiante ya está inscrito");

                    return;
                }
            }

            // VALIDAR CUPO
            int contador = 0;

            for (Inscripcion i : Datos.listaInscripciones) {

                if (i.getCurso()
                        .getCodigo()
                        .equals(curso.getCodigo())) {

                    contador++;
                }
            }

            if (contador >= curso.getCupo()) {

                JOptionPane.showMessageDialog(
                        null,
                        "No hay cupo disponible");

                return;
            }

            // CREAR INSCRIPCION
            Inscripcion inscripcion =
                    new Inscripcion(estudiante, curso);

            Datos.listaInscripciones.add(inscripcion);
            PersistenciaInscripciones.guardarInscripciones();

            modelo.addRow(new Object[]{
                    estudiante.getCarnet(),
                    estudiante.getNombre()
                            + " "
                            + estudiante.getApellidos(),
                    curso.getNombre()
            });

            JOptionPane.showMessageDialog(
                    null,
                    "Inscripción realizada");

            cbCarnet.setSelectedIndex(0);
            cbCurso.setSelectedIndex(0);

            txtEstudiante.setText("");
        });

        // LIMPIAR
        btnLimpiar.addActionListener(e -> {

            cbCarnet.setSelectedIndex(0);
            cbCurso.setSelectedIndex(0);

            txtEstudiante.setText("");
        });

        // REGRESAR
        btnRegresar.addActionListener(e -> {

            dispose();
        });
    }
}