package vista;

import modelo.Cursos;
import modelo.Datos;
import modelo.Estudiante;
import modelo.Inscripcion;
import persistencia.PersistenciaInscripciones;

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
        panel.setBackground(new Color(240, 248, 255));

        agregarComponentes();

        add(panel);

        setVisible(true);
    }

    private void agregarComponentes() {

        JLabel titulo = new JLabel("INSCRIPCIONES");
        titulo.setBounds(260, 25, 300, 40);
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        titulo.setForeground(new Color(20, 33, 61));
        panel.add(titulo);

        JLabel lblCarnet = new JLabel("Carnet:");
        lblCarnet.setBounds(60, 90, 100, 30);
        lblCarnet.setFont(new Font("Arial", Font.BOLD, 16));
        lblCarnet.setForeground(new Color(20, 33, 61));
        panel.add(lblCarnet);

        JComboBox<String> cbCarnet = new JComboBox<>();
        cbCarnet.setBounds(160, 90, 170, 32);
        cbCarnet.setFont(new Font("Arial", Font.PLAIN, 14));
        cbCarnet.setBackground(Color.WHITE);
        cbCarnet.addItem("Seleccione");

        for (Estudiante estudiante : Datos.listaEstudiantes) {
            cbCarnet.addItem(estudiante.getCarnet());
        }

        panel.add(cbCarnet);

        JLabel lblEstudiante = new JLabel("Estudiante:");
        lblEstudiante.setBounds(360, 90, 120, 30);
        lblEstudiante.setFont(new Font("Arial", Font.BOLD, 16));
        lblEstudiante.setForeground(new Color(20, 33, 61));
        panel.add(lblEstudiante);

        JTextField txtEstudiante = new JTextField();
        txtEstudiante.setBounds(470, 90, 220, 32);
        txtEstudiante.setFont(new Font("Arial", Font.PLAIN, 14));
        txtEstudiante.setBackground(new Color(230, 240, 250));
        txtEstudiante.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 1));
        txtEstudiante.setEditable(false);
        panel.add(txtEstudiante);

        JLabel lblCurso = new JLabel("Carrera:");
        lblCurso.setBounds(60, 140, 100, 30);
        lblCurso.setFont(new Font("Arial", Font.BOLD, 16));
        lblCurso.setForeground(new Color(20, 33, 61));
        panel.add(lblCurso);

        JComboBox<String> cbCurso = new JComboBox<>();
        cbCurso.setBounds(160, 140, 250, 32);
        cbCurso.setFont(new Font("Arial", Font.PLAIN, 14));
        cbCurso.setBackground(Color.WHITE);

        cbCurso.addItem("Seleccione");
        cbCurso.addItem("Ingeniería en Sistemas");
        cbCurso.addItem("Derecho");
        cbCurso.addItem("Administración");
        cbCurso.addItem("Medicina");
        cbCurso.addItem("Arquitectura");

        panel.add(cbCurso);

        String[] columnas = {
                "Carnet",
                "Estudiante",
                "Carrera"
        };

        DefaultTableModel modelo =
                new DefaultTableModel(null, columnas);

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
        scroll.setBounds(60, 210, 650, 180);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(20, 33, 61), 2));
        panel.add(scroll);

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
        btnInscribir.setBounds(130, 415, 120, 32);
        btnInscribir.setBackground(new Color(46, 139, 87));
        btnInscribir.setForeground(Color.WHITE);
        btnInscribir.setFont(new Font("Arial", Font.BOLD, 13));
        btnInscribir.setOpaque(true);
        btnInscribir.setContentAreaFilled(true);
        btnInscribir.setBorderPainted(false);
        panel.add(btnInscribir);

        JButton btnLimpiar = new JButton("LIMPIAR");
        btnLimpiar.setBounds(270, 415, 120, 32);
        btnLimpiar.setBackground(new Color(70, 130, 180));
        btnLimpiar.setForeground(Color.WHITE);
        btnLimpiar.setFont(new Font("Arial", Font.BOLD, 13));
        btnLimpiar.setOpaque(true);
        btnLimpiar.setContentAreaFilled(true);
        btnLimpiar.setBorderPainted(false);
        panel.add(btnLimpiar);

        JButton btnEliminar = new JButton("ELIMINAR");
        btnEliminar.setBounds(410, 415, 120, 32);
        btnEliminar.setBackground(new Color(220, 20, 60));
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFont(new Font("Arial", Font.BOLD, 13));
        btnEliminar.setOpaque(true);
        btnEliminar.setContentAreaFilled(true);
        btnEliminar.setBorderPainted(false);
        panel.add(btnEliminar);

        JButton btnRegresar = new JButton("↩");
        btnRegresar.setBounds(550, 415, 70, 32);
        btnRegresar.setBackground(new Color(20, 33, 61));
        btnRegresar.setForeground(Color.WHITE);
        btnRegresar.setFont(new Font("Arial", Font.BOLD, 15));
        btnRegresar.setOpaque(true);
        btnRegresar.setContentAreaFilled(true);
        btnRegresar.setBorderPainted(false);
        panel.add(btnRegresar);

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
                        "Seleccione una carrera");

                return;
            }

            Estudiante estudiante =
                    Datos.listaEstudiantes.get(
                            cbCarnet.getSelectedIndex() - 1);

            String nombreCurso =
                    cbCurso.getSelectedItem().toString();

            Cursos cursoSeleccionado =
                    new Cursos(
                            nombreCurso,
                            nombreCurso,
                            nombreCurso,
                            1000);

            for (Inscripcion i : Datos.listaInscripciones) {

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

            Datos.listaInscripciones.add(inscripcion);

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

        btnLimpiar.addActionListener(e -> {

            cbCarnet.setSelectedIndex(0);
            cbCurso.setSelectedIndex(0);
            txtEstudiante.setText("");
        });

        btnEliminar.addActionListener(e -> {

            int fila = tabla.getSelectedRow();

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

        btnRegresar.addActionListener(e -> {
            dispose();
        });
    }
}