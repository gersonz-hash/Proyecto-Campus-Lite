package modelo;

public class ExamenEscrito extends Evaluacion {

    public ExamenEscrito(String carrera,
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