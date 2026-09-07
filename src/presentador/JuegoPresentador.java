package presentador;

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
