package logica;

public class Usuario {
	private String nombre;
	private int puntaje;
	
	public Usuario(String nombre) {
		this.nombre = nombre;
		this.puntaje = 0;
	}

	public String getNombre() {
		return this.nombre;
	}
	
	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}
	
	public int getPuntaje() {
		return this.puntaje;
	}
}
