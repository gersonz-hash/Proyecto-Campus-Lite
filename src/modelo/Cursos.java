package modelo;

public class Cursos {

    private String codigo;
    private String nombre;
    private String carrera;
    private int cupo;

    public Cursos(String codigo, String nombre, String carrera, int cupo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.carrera = carrera;
        this.cupo = cupo;
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getCarrera() { return carrera; }
    public int getCupo() { return cupo; }

    public void setCodigo(String codigo) { this.codigo = codigo; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setCarrera(String carrera) { this.carrera = carrera; }
    public void setCupo(int cupo) { this.cupo = cupo; }
}