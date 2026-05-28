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
                        i.getCurso().getCodigo()
                );

                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error al guardar inscripciones");
        }
    }

    public static void cargarInscripciones() {

        Datos.listaInscripciones.clear(); // 🔥 IMPORTANTE

        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] d = linea.split(",");

                if (d.length == 2) {

                    String carnet = d[0];
                    String codigoCurso = d[1];

                    Estudiante est = null;
                    Cursos cur = null;

                    for (Estudiante e : Datos.listaEstudiantes) {
                        if (e.getCarnet().equals(carnet)) {
                            est = e;
                            break;
                        }
                    }

                    for (Cursos c : Datos.listaCursos) {
                        if (c.getCodigo().equals(codigoCurso)) {
                            cur = c;
                            break;
                        }
                    }

                    if (est != null && cur != null) {
                        Datos.listaInscripciones.add(new Inscripcion(est, cur));
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Error al cargar inscripciones");
        }
    }
}