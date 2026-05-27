package modelo;

public class Laboratorio extends Evaluacion {

    public Laboratorio(String nombre, double nota, double porcentaje) {
		super(nombre, nota, porcentaje);
	}

	@Override
    public double calcularNotaFinal() {
        return nota * (porcentaje / 100);
    }
}