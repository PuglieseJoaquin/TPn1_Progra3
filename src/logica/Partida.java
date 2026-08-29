package logica;

public class Partida {
	private String nombreJugador;
	private int puntaje;
	private int valorFichaMaximo;
	private String nivel;
	
	public Partida(String nombreJugador, int puntaje, int valorFichaMaximo, String nivel) {
		this.nombreJugador = nombreJugador;
		this.puntaje = 0;
		this.valorFichaMaximo = 0;
		this.nivel = nivel;
	}

	public String getNombreJugador() {
		return nombreJugador;
	}

	public void setNombreJugador(String nombreJugador) {
		this.nombreJugador = nombreJugador;
	}

	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	public int getValorFichaMaximo() {
		return valorFichaMaximo;
	}

	public void setValorFichaMaximo(int valorFichaMaximo) {
		this.valorFichaMaximo = valorFichaMaximo;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	
	
}
