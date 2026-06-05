package main;

import persistencia.PersistenciaCursos;
import persistencia.PersistenciaEstudiantes;
import persistencia.PersistenciaEvaluaciones;
import persistencia.PersistenciaInscripciones;
import vista.FrmPrincipal;

public class Main {

    public static void main(String[] args) {

        // CARGAR ESTUDIANTES
        PersistenciaEstudiantes.cargarEstudiantes();

        // CARGAR CURSOS
        PersistenciaCursos.cargarCursos();

        // CARGAR INSCRIPCIONES
        PersistenciaInscripciones.cargarInscripciones();

        // CARGAR EVALUACIONES
        PersistenciaEvaluaciones.cargarEvaluaciones();

        // ABRIR SISTEMA
        FrmPrincipal frm = new FrmPrincipal();
        frm.setVisible(true);
    }
}