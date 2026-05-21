package modelo;
import java.time.LocalDate;

public class Estudiante {
	private  String carnet, nombre, apellidos, correo;
	private LocalDate fechaNacimiento;
	
	
	
	public Estudiante(String carnet, String nombre, String apellidos, String correo, LocalDate fechaNacimiento) {
		this.carnet = carnet;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.correo = correo;
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getCarnet() {
		return carnet;
	}

	public void setCarnet(String carnet) {
		this.carnet = carnet;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}


	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public void mostrar() {
		System.out.println("Carnet: "+carnet);
		System.out.println("Nombre: "+nombre);
		System.out.println("Apellidos: "+apellidos);
		System.out.println("Correo: "+correo);
	}
	
	
}
