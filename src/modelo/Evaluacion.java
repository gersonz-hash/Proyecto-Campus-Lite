package modelo;

public abstract class Evaluacion {

    protected String carrera;
    protected String curso;
    protected String nombre;
    protected double nota;
    protected double porcentaje;

    public Evaluacion(String carrera,
                      String curso,
                      String nombre,
                      double nota,
                      double porcentaje) {

        this.carrera = carrera;
        this.curso = curso;
        this.nombre = nombre;
        this.nota = nota;
        this.porcentaje = porcentaje;
    }

    public String getCarrera() {
        return carrera;
    }

    public String getCurso() {
        return curso;
    }

    public String getNombre() {
        return nombre;
    }

    public double getNota() {
        return nota;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public abstract double calcularNotaFinal();
}