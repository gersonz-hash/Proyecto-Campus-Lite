package modelo;

public class Cursos {
	private String codigo, nombre;
	private int creditos, cupo;
	
	public Cursos(String codigo, String nombre, int creditos, int cupo) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.creditos = creditos;
		this.cupo = cupo;
	}
	//Getters and Setters

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCreditos() {
		return creditos;
	}

	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}

	public int getCupo() {
		return cupo;
	}

	public void setCupo(int cupo) {
		this.cupo = cupo;
	}
	public void mostrar() {
		System.out.println("Código: "+codigo);
		System.out.println("Nombre: "+nombre);
		System.out.println("Creditos: "+creditos);
		System.out.println("Cupo: "+cupo);
		
	}
	
	

}
