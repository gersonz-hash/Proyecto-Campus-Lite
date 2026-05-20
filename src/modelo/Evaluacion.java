package modelo;

public abstract class Evaluacion {

    protected String nombre;
    protected double nota;
    protected double porcentaje;

    public Evaluacion(String nombre, double nota, double porcentaje) {
        this.nombre = nombre;
        this.nota = nota;
        this.porcentaje = porcentaje;
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