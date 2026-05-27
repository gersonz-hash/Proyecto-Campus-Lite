package Persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import modelo.Datos;
import modelo.Evaluacion;
import modelo.ExamenEscrito;
import modelo.Laboratorio;
import modelo.Proyecto;

public class PersistenciaEvaluaciones {

    private static final String ARCHIVO = "evaluaciones.csv";

    public static void guardarEvaluaciones() {

        try {

            BufferedWriter bw =
                    new BufferedWriter(
                            new FileWriter(ARCHIVO));

            for (Evaluacion e : Datos.listaEvaluaciones) {

                bw.write(
                        e.getClass().getSimpleName()
                                + ","
                                + e.getNombre()
                                + ","
                                + e.getNota()
                                + ","
                                + e.getPorcentaje()
                );

                bw.newLine();
            }

            bw.close();

        } catch (IOException e) {

            System.out.println("Error al guardar evaluaciones");
            e.printStackTrace();
        }
    }

    public static void cargarEvaluaciones() {

        try {

            File archivo = new File(ARCHIVO);

            if (!archivo.exists()) {
                return;
            }

            BufferedReader br =
                    new BufferedReader(
                            new FileReader(archivo));

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split(",");

                if (datos.length == 4) {

                    String tipo = datos[0];
                    String estudiante = datos[1];

                    double nota =
                            Double.parseDouble(datos[2]);

                    double ponderacion =
                            Double.parseDouble(datos[3]);

                    Evaluacion evaluacion = null;

                    if (tipo.equals("ExamenEscrito")) {

                        evaluacion =
                                new ExamenEscrito(
                                        estudiante,
                                        nota,
                                        ponderacion);

                    } else if (tipo.equals("Laboratorio")) {

                        evaluacion =
                                new Laboratorio(
                                        estudiante,
                                        nota,
                                        ponderacion);

                    } else if (tipo.equals("Proyecto")) {

                        evaluacion =
                                new Proyecto(
                                        estudiante,
                                        nota,
                                        ponderacion);
                    }

                    if (evaluacion != null) {

                        Datos.listaEvaluaciones.add(
                                evaluacion);
                    }
                }
            }

            br.close();

        } catch (IOException e) {

            System.out.println("Error al cargar evaluaciones");
            e.printStackTrace();
        }
    }
}