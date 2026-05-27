package Persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import modelo.Cursos;
import modelo.Datos;

public class PersistenciaCursos {

    private static final String ARCHIVO = "cursos.csv";

    // GUARDAR
    public static void guardarCursos() {

        try {

            BufferedWriter bw = new BufferedWriter(
                    new FileWriter(ARCHIVO));

            for (Cursos c : Datos.listaCursos) {

                bw.write(
                        c.getCodigo() + "," +
                        c.getNombre() + "," +
                        c.getCreditos() + "," +
                        c.getCupo()
                );

                bw.newLine();
            }

            bw.close();

        } catch (IOException e) {

            System.out.println(
                    "Error al guardar cursos");

            e.printStackTrace();
        }
    }

    // CARGAR
    public static void cargarCursos() {

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

                    Cursos c = new Cursos(
                            datos[0],
                            datos[1],
                            Integer.parseInt(datos[2]),
                            Integer.parseInt(datos[3])
                    );

                    Datos.listaCursos.add(c);
                }
            }

            br.close();

        } catch (IOException e) {

            System.out.println(
                    "Error al cargar cursos");

            e.printStackTrace();
        }
    }
}