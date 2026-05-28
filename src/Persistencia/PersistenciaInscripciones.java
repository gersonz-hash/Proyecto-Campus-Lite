package Persistencia;

import java.io.*;
import modelo.*;

public class PersistenciaInscripciones {

    private static final String ARCHIVO = "inscripciones.csv";

    public static void guardarInscripciones() {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO))) {

            for (Inscripcion i : Datos.listaInscripciones) {

                bw.write(
                        i.getEstudiante().getCarnet() + "," +
                        i.getCurso().getNombre()
                );

                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error al guardar inscripciones");
        }
    }

    public static void cargarInscripciones() {

        Datos.listaInscripciones.clear();

        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] d = linea.split(",");

                if (d.length == 2) {

                    String carnet = d[0];
                    String carrera = d[1];

                    Estudiante est = null;

                    for (Estudiante e : Datos.listaEstudiantes) {
                        if (e.getCarnet().equals(carnet)) {
                            est = e;
                            break;
                        }
                    }

                    if (est != null) {

                        Cursos carreraSeleccionada =
                                new Cursos(
                                        carrera,
                                        carrera,
                                        carrera,
                                        1000);

                        Datos.listaInscripciones.add(
                                new Inscripcion(
                                        est,
                                        carreraSeleccionada));
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Error al cargar inscripciones");
        }
    }
}