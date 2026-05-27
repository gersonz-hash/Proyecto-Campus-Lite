package main;

import Persistencia.PersistenciaCursos;
import Persistencia.PersistenciaEstudiantes;
import Persistencia.PersistenciaInscripciones;

import vista.FrmPrincipal;

public class Main {

    public static void main(String[] args) {

        // CARGAR ESTUDIANTES
        PersistenciaEstudiantes.cargarEstudiantes();

        // CARGAR CURSOS
        PersistenciaCursos.cargarCursos();

        // CARGAR INSCRIPCIONES
        PersistenciaInscripciones.cargarInscripciones();

        // ABRIR SISTEMA
        FrmPrincipal frm = new FrmPrincipal();
        frm.setVisible(true);
    }
}