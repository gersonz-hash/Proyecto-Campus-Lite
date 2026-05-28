package modelo;

public class Laboratorio extends Evaluacion {

    public Laboratorio(String carrera,
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