package modelo;

public class Proyecto extends Evaluacion {

    public Proyecto(String carrera,
                    String curso,
                    String nombre,
                    double nota,
                    double porcentaje) {

        super(carrera, curso, nombre, nota, porcentaje);
    }

    @Override
    public double calcularNotaFinal() {

        return nota;
    }
}