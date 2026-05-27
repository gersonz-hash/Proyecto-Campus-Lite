package Persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import modelo.Datos;
import modelo.Estudiante;

public class PersistenciaEstudiantes {

    private static final String ARCHIVO = "estudiantes.csv";

    // GUARDAR
    public static void guardarEstudiantes() {

        try {

            BufferedWriter bw = new BufferedWriter(
                    new FileWriter(ARCHIVO));

            for (Estudiante e : Datos.listaEstudiantes) {

                bw.write(
                        e.getCarnet() + "," +
                        e.getNombre() + "," +
                        e.getApellidos() + "," +
                        e.getCorreo()
                );

                bw.newLine();
            }

            bw.close();

        } catch (IOException e) {

            System.out.println(
                    "Error al guardar estudiantes");

            e.printStackTrace();
        }
    }

    // CARGAR
    public static void cargarEstudiantes() {

        try {

            File archivo = new File(ARCHIVO);

            if (!archivo.exists()) {
                return;
            }

            BufferedReader br = new BufferedReader(
                    new FileReader(archivo));

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split(",");

                if (datos.length == 4) {

                    Estudiante e = new Estudiante(
                            datos[0],
                            datos[1],
                            datos[2],
                            datos[3]
                    );

                    Datos.listaEstudiantes.add(e);
                }
            }

            br.close();

        } catch (IOException e) {

            System.out.println(
                    "Error al cargar estudiantes");

            e.printStackTrace();
        }
    }
}