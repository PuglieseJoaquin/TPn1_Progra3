package presenter;

import gui.GestorPantallas;
import gui.JuegoVista;
import logica.Juego;

public class JuegoPresenter {
	private GestorPantallas gestorPantallas;
	private Juego juego;
	private JuegoVista juegoVista;
	private 	String nombreJugador;
	private int tamanioMatriz;
	private String nivel;
	
	public JuegoPresenter(JuegoVista vista, GestorPantallas gestorPantallas, String nombreJugador, int tamanioMatriz, String nivel) {
		this.gestorPantallas = gestorPantallas;
		this.juegoVista = vista;
		this.juego = new Juego(tamanioMatriz, nombreJugador, nivel);
		this.nombreJugador = nombreJugador;
		this.tamanioMatriz = tamanioMatriz;
		this.nivel = nivel;
	}
		
	public void manejarEventoTeclaDeMovimiento(int codigoTecla) {
		juego.mover(codigoTecla);
		actualizarVista();
		
		if (juego.isGameOver()) {
			int puntaje = juego.getPuntaje();
			gestorPantallas.crearPantallaPartidaTerminada(nombreJugador, puntaje, tamanioMatriz, nivel);
		}
	}

	public void actualizarVista() {
		juegoVista.mostrarTablero(juego.getMatrizValoresDeFichas());
		juegoVista.mostrarProximaFicha(juego.getProximoValorFicha());
		juegoVista.mostrarMovimientoSugerido(juego.getMovimientoSugerido());
	}
}
