package Persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import modelo.Cursos;
import modelo.Datos;
import modelo.Estudiante;
import modelo.Inscripcion;

public class PersistenciaInscripciones {

    private static final String ARCHIVO =
            "inscripciones.csv";

    // GUARDAR
    public static void guardarInscripciones() {

        try {

            BufferedWriter bw =
                    new BufferedWriter(
                            new FileWriter(ARCHIVO));

            for (Inscripcion i :
                    Datos.listaInscripciones) {

                bw.write(
                        i.getEstudiante().getCarnet()
                                + ","
                                + i.getCurso().getCodigo()
                );

                bw.newLine();
            }

            bw.close();

        } catch (IOException e) {

            System.out.println(
                    "Error al guardar inscripciones");

            e.printStackTrace();
        }
    }

    // CARGAR
    public static void cargarInscripciones() {

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

                if (datos.length == 2) {

                    String carnet = datos[0];
                    String codigoCurso = datos[1];

                    Estudiante estudiante = null;
                    Cursos curso = null;

                    // BUSCAR ESTUDIANTE
                    for (Estudiante e :
                            Datos.listaEstudiantes) {

                        if (e.getCarnet()
                                .equals(carnet)) {

                            estudiante = e;
                            break;
                        }
                    }

                    // BUSCAR CURSO
                    for (Cursos c :
                            Datos.listaCursos) {

                        if (c.getCodigo()
                                .equals(codigoCurso)) {

                            curso = c;
                            break;
                        }
                    }

                    // CREAR INSCRIPCION
                    if (estudiante != null
                            && curso != null) {

                        Inscripcion i =
                                new Inscripcion(
                                        estudiante,
                                        curso);

                        Datos.listaInscripciones.add(i);
                    }
                }
            }

            br.close();

        } catch (IOException e) {

            System.out.println(
                    "Error al cargar inscripciones");

            e.printStackTrace();
        }
    }
}