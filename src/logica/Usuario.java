package logica;

public class Usuario {
	private String nombre;
	private int puntaje;
	private int valorMaximo;
	private String nivel;
	
	
	public Usuario(String nombre) {
		this.nombre = nombre;
		this.puntaje = 0;
		this.valorMaximo=0;
	}

	public String getNombre() {
		return this.nombre;
	}
	
	public int getPuntaje() {
		return this.puntaje;
	}
	
	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	public int getValorMaximo() {
		return valorMaximo;
	}

	public void setValorMaximo(int valorMaximo) {   
	    this.valorMaximo = valorMaximo;
	}
	
	public String getNivel() {
        return this.nivel;
    }
	
	public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    

}
