package Persistencia;

import java.io.*;
import modelo.*;

public class PersistenciaCursos {

    private static final String ARCHIVO = "cursos.csv";

    public static void guardarCursos() {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO))) {

            for (Cursos c : Datos.listaCursos) {

                bw.write(
                        c.getCodigo() + "," +
                        c.getNombre() + "," +
                        c.getCarrera() + "," +
                        c.getCupo()
                );

                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error al guardar cursos");
        }
    }

    public static void cargarCursos() {

        Datos.listaCursos.clear(); // 🔥 IMPORTANTE

        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] d = linea.split(",");

                if (d.length == 4) {

                    Cursos c = new Cursos(
                            d[0],
                            d[1],
                            d[2],
                            Integer.parseInt(d[3])
                    );

                    Datos.listaCursos.add(c);
                }
            }

        } catch (IOException e) {
            System.out.println("Error al cargar cursos");
        }
    }
}