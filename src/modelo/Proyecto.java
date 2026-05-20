package modelo;

public class Proyecto extends Evaluacion {

    public Proyecto(String nombre, double nota, double porcentaje) {
        super(nombre, nota, porcentaje);
    }

    @Override
    public double calcularNotaFinal() {
        return nota * (porcentaje / 100);
    }
}