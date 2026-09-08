package presentador;

import java.awt.event.KeyEvent;

import modelo.Juego;
import vista.GestorPantallas;
import vista.JuegoVista;

public class JuegoPresentador {
	private GestorPantallas gestorPantallas;
	private Juego juego;
	private JuegoVista juegoVista;
	private String nombreJugador;
	private int tamanioMatriz;
	private String nivel;
	
	public JuegoPresentador(JuegoVista vista, GestorPantallas gestorPantallas, String nombreJugador, int tamanioMatriz, String nivel) {
		this.gestorPantallas = gestorPantallas;
		this.juegoVista = vista;
		this.juego = new Juego(tamanioMatriz, nombreJugador, nivel);
		this.nombreJugador = nombreJugador;
		this.tamanioMatriz = tamanioMatriz;
		this.nivel = nivel;
	}
		
	public void manejarEventoTeclaDeMovimiento(int codigoTecla) {
		if (esTeclaValida(codigoTecla)) {
			juego.mover(codigoTecla);
			actualizarVista();
		}	
		
		if (juego.isGameOver()) {
			int puntaje = juego.getPuntaje();
			gestorPantallas.crearPantallaPartidaTerminada(nombreJugador, puntaje, tamanioMatriz, nivel);
		}
	}
	
	public boolean esTeclaValida(int codigoTecla) {
		return codigoTecla == KeyEvent.VK_RIGHT ||
	           codigoTecla == KeyEvent.VK_LEFT ||
	           codigoTecla == KeyEvent.VK_UP ||
	           codigoTecla == KeyEvent.VK_DOWN;
	}

	public void actualizarVista() {
		juegoVista.mostrarTablero(juego.getMatrizValoresDeFichas());
		juegoVista.mostrarProximaFicha(juego.getProximoValorFicha());
		juegoVista.mostrarMovimientoSugerido(juego.getMovimientoSugerido());
	}
}
