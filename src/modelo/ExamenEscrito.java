package modelo;

public class ExamenEscrito extends Evaluacion {

    public ExamenEscrito(String nombre, double nota, double porcentaje) {
        super(nombre, nota, porcentaje);
    }

    @Override
    public double calcularNotaFinal() {
        return nota * (porcentaje / 100);
    }
}