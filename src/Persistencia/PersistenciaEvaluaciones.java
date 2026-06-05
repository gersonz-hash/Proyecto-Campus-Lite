package persistencia;

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
                                + e.getCarrera()
                                + ","
                                + e.getCurso()
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

                if (datos.length == 6) {

                    String tipo = datos[0];
                    String carrera = datos[1];
                    String curso = datos[2];
                    String estudiante = datos[3];

                    double nota =
                            Double.parseDouble(datos[4]);

                    double ponderacion =
                            Double.parseDouble(datos[5]);

                    Evaluacion evaluacion = null;

                    if (tipo.equals("ExamenEscrito")) {

                        evaluacion =
                                new ExamenEscrito(
                                        carrera,
                                        curso,
                                        estudiante,
                                        nota,
                                        ponderacion);

                    } else if (tipo.equals("Laboratorio")) {

                        evaluacion =
                                new Laboratorio(
                                        carrera,
                                        curso,
                                        estudiante,
                                        nota,
                                        ponderacion);

                    } else if (tipo.equals("Proyecto")) {

                        evaluacion =
                                new Proyecto(
                                        carrera,
                                        curso,
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