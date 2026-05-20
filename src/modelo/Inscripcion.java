package modelo;

public class Inscripcion {

    private Estudiante estudiante;
    private Cursos curso;

    public Inscripcion(Estudiante estudiante, Cursos curso) {
        this.estudiante = estudiante;
        this.curso = curso;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public Cursos getCurso() {
        return curso;
    }
}