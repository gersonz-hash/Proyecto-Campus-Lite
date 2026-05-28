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

        setVisible(true);
    }

    private void agregarComponentes() {

        JLabel titulo = new JLabel("INSCRIPCIONES");
        titulo.setBounds(280, 25, 300, 40);
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        panel.add(titulo);

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

        JLabel lblEstudiante =
                new JLabel("Estudiante:");

        lblEstudiante.setBounds(360, 90, 120, 30);
        lblEstudiante.setFont(
                new Font("Arial", Font.BOLD, 16));

        panel.add(lblEstudiante);

        JTextField txtEstudiante =
                new JTextField();

        txtEstudiante.setBounds(470, 90, 220, 30);
        txtEstudiante.setFont(
                new Font("Arial", Font.PLAIN, 15));

        txtEstudiante.setEditable(false);

        panel.add(txtEstudiante);

        JLabel lblCurso =
                new JLabel("Curso:");

        lblCurso.setBounds(60, 140, 100, 30);
        lblCurso.setFont(
                new Font("Arial", Font.BOLD, 16));

        panel.add(lblCurso);

        JComboBox<String> cbCurso =
                new JComboBox<>();

        cbCurso.setBounds(160, 140, 250, 30);

        cbCurso.setFont(
                new Font("Arial", Font.PLAIN, 15));

        cbCurso.addItem("Seleccione");

        // CARGAR CURSOS REALES
        for (Cursos c : Datos.listaCursos) {

            cbCurso.addItem(c.getNombre());
        }

        panel.add(cbCurso);

        String[] columnas = {
                "Carnet",
                "Estudiante",
                "Curso"
        };

        DefaultTableModel modelo =
                new DefaultTableModel(
                        null,
                        columnas);

        JTable tabla = new JTable(modelo);

        JScrollPane scroll =
                new JScrollPane(tabla);

        scroll.setBounds(60, 210, 650, 180);

        panel.add(scroll);

        // CARGAR INSCRIPCIONES
        for (Inscripcion i :
                Datos.listaInscripciones) {

            modelo.addRow(new Object[]{

                    i.getEstudiante().getCarnet(),

                    i.getEstudiante().getNombre()
                            + " "
                            + i.getEstudiante().getApellidos(),

                    i.getCurso().getNombre()
            });
        }

        JButton btnInscribir =
                new JButton("INSCRIBIR");

        btnInscribir.setBounds(130, 415, 120, 30);

        panel.add(btnInscribir);

        JButton btnLimpiar =
                new JButton("LIMPIAR");

        btnLimpiar.setBounds(270, 415, 120, 30);

        panel.add(btnLimpiar);

        JButton btnEliminar =
                new JButton("ELIMINAR");

        btnEliminar.setBounds(410, 415, 120, 30);

        panel.add(btnEliminar);

        JButton btnRegresar =
                new JButton("↩");

        btnRegresar.setBounds(550, 415, 70, 30);

        panel.add(btnRegresar);

        // MOSTRAR ESTUDIANTE
        cbCarnet.addActionListener(e -> {

            int indice =
                    cbCarnet.getSelectedIndex();

            if (indice > 0) {

                Estudiante estudiante =
                        Datos.listaEstudiantes.get(
                                indice - 1);

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

            String nombreCurso =
                    cbCurso.getSelectedItem().toString();

            Cursos cursoSeleccionado = null;

            // BUSCAR CURSO REAL
            for (Cursos c :
                    Datos.listaCursos) {

                if (c.getNombre()
                        .equals(nombreCurso)) {

                    cursoSeleccionado = c;
                    break;
                }
            }

            if (cursoSeleccionado == null) {

                JOptionPane.showMessageDialog(
                        null,
                        "Curso no encontrado");

                return;
            }

            // VALIDAR DUPLICADO
            for (Inscripcion i :
                    Datos.listaInscripciones) {

                if (i.getEstudiante()
                        .getCarnet()
                        .equals(estudiante.getCarnet())

                        &&

                        i.getCurso()
                                .getNombre()
                                .equals(nombreCurso)) {

                    JOptionPane.showMessageDialog(
                            null,
                            "El estudiante ya está inscrito");

                    return;
                }
            }

            Inscripcion inscripcion =
                    new Inscripcion(
                            estudiante,
                            cursoSeleccionado);

            Datos.listaInscripciones.add(
                    inscripcion);

            PersistenciaInscripciones.guardarInscripciones();

            modelo.addRow(new Object[]{

                    estudiante.getCarnet(),

                    estudiante.getNombre()
                            + " "
                            + estudiante.getApellidos(),

                    nombreCurso
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

        // ELIMINAR
        btnEliminar.addActionListener(e -> {

            int fila =
                    tabla.getSelectedRow();

            if (fila < 0) {

                JOptionPane.showMessageDialog(
                        null,
                        "Seleccione una inscripción");

                return;
            }

            Datos.listaInscripciones.remove(fila);

            PersistenciaInscripciones.guardarInscripciones();

            modelo.removeRow(fila);

            JOptionPane.showMessageDialog(
                    null,
                    "Inscripción eliminada");
        });

        // REGRESAR
        btnRegresar.addActionListener(e -> {

            dispose();
        });
    }
}