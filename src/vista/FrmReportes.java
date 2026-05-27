package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import modelo.Datos;
import modelo.Evaluacion;

public class FrmReportes extends JFrame {

    public FrmReportes() {

        setTitle("Reportes");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel lblTitulo =
                new JLabel("REPORTE DE EVALUACIONES");

        lblTitulo.setBounds(200, 20, 300, 30);

        add(lblTitulo);

        // TABLA
        String[] columnas = {
                "Tipo",
                "Nombre",
                "Nota",
                "Porcentaje",
                "Nota Final"
        };

        DefaultTableModel modelo =
                new DefaultTableModel(null, columnas);

        JTable tabla = new JTable(modelo);

        JScrollPane scroll =
                new JScrollPane(tabla);

        scroll.setBounds(40, 80, 600, 250);

        add(scroll);

        // TOTAL
        JLabel lblPromedio =
                new JLabel("Promedio Final: 0");

        lblPromedio.setBounds(40, 360, 300, 30);

        add(lblPromedio);

        double total = 0;

        // CARGAR DATOS
        for (Evaluacion e :
                Datos.listaEvaluaciones) {

            double notaFinal =
                    e.calcularNotaFinal();

            total += notaFinal;

            modelo.addRow(new Object[]{

                    e.getClass().getSimpleName(),

                    e.getNombre(),

                    e.getNota(),

                    e.getPorcentaje(),

                    notaFinal
            });
        }

        lblPromedio.setText(
                "Promedio Final: "
                        + total);

        // BOTON REGRESAR
        JButton btnCerrar =
                new JButton("Cerrar");

        btnCerrar.setBounds(260, 400, 120, 35);

        add(btnCerrar);

        btnCerrar.addActionListener(e -> {

            dispose();
        });

        setVisible(true);
    }
}