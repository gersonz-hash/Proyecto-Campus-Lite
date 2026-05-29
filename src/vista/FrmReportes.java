package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Font;

import modelo.Datos;
import modelo.Evaluacion;
import modelo.ExamenEscrito;
import modelo.Laboratorio;
import modelo.Proyecto;

public class FrmReportes extends JFrame {

    public FrmReportes() {

        setTitle("Reportes");
        setSize(1000, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(
                new Color(240, 248, 255));

        JLabel lblTitulo =
                new JLabel("REPORTE DE EVALUACIONES");

        lblTitulo.setBounds(325, 20, 400, 30);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(20, 33, 61));

        add(lblTitulo);

        String[] columnas = {
                "Carrera",
                "Curso",
                "Estudiante",
                "Examen",
                "Laboratorio",
                "Proyecto",
                "Total"
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

        JScrollPane scroll =
                new JScrollPane(tabla);

        scroll.setBounds(30, 80, 920, 280);
        scroll.setBorder(
                BorderFactory.createLineBorder(
                        new Color(20, 33, 61),
                        2));

        add(scroll);

        for (Evaluacion e : Datos.listaEvaluaciones) {

            String carrera =
                    e.getCarrera();

            String curso =
                    e.getCurso();

            String estudiante =
                    e.getNombre();

            boolean existe = false;

            for (int i = 0;
                 i < modelo.getRowCount();
                 i++) {

                String estudianteTabla =
                        modelo.getValueAt(i, 2).toString();

                String cursoTabla =
                        modelo.getValueAt(i, 1).toString();

                if (estudianteTabla.equals(estudiante)
                        && cursoTabla.equals(curso)) {

                    double examen =
                            Double.parseDouble(
                                    modelo.getValueAt(i, 3).toString());

                    double laboratorio =
                            Double.parseDouble(
                                    modelo.getValueAt(i, 4).toString());

                    double proyecto =
                            Double.parseDouble(
                                    modelo.getValueAt(i, 5).toString());

                    if (e instanceof ExamenEscrito) {

                        examen = e.getNota();

                        modelo.setValueAt(
                                examen,
                                i,
                                3);

                    } else if (e instanceof Laboratorio) {

                        laboratorio = e.getNota();

                        modelo.setValueAt(
                                laboratorio,
                                i,
                                4);

                    } else if (e instanceof Proyecto) {

                        proyecto = e.getNota();

                        modelo.setValueAt(
                                proyecto,
                                i,
                                5);
                    }

                    double total =
                            examen
                                    + laboratorio
                                    + proyecto;

                    modelo.setValueAt(
                            total,
                            i,
                            6);

                    existe = true;

                    break;
                }
            }

            if (!existe) {

                double examen = 0;
                double laboratorio = 0;
                double proyecto = 0;

                if (e instanceof ExamenEscrito) {

                    examen = e.getNota();

                } else if (e instanceof Laboratorio) {

                    laboratorio = e.getNota();

                } else if (e instanceof Proyecto) {

                    proyecto = e.getNota();
                }

                double total =
                        examen
                                + laboratorio
                                + proyecto;

                modelo.addRow(new Object[]{

                        carrera,
                        curso,
                        estudiante,
                        examen,
                        laboratorio,
                        proyecto,
                        total
                });
            }
        }

        JButton btnCerrar =
                new JButton("Cerrar");

        btnCerrar.setBounds(420, 390, 120, 35);
        btnCerrar.setBackground(new Color(20, 33, 61));
        btnCerrar.setForeground(Color.WHITE);
        btnCerrar.setFont(new Font("Arial", Font.BOLD, 14));
        btnCerrar.setOpaque(true);
        btnCerrar.setContentAreaFilled(true);
        btnCerrar.setBorderPainted(false);

        add(btnCerrar);

        btnCerrar.addActionListener(e -> {

            dispose();
        });

        setVisible(true);
    }
}