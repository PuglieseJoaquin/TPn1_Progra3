package presentador;

import java.awt.event.KeyEvent;
import modelo.Direccion;
import modelo.Ficha;
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
		Direccion direccion = traducirTecla(codigoTecla);
		
		if (direccion != null) {
			juego.mover(direccion);
			actualizarVista();
		}	
		
		if (juego.isGameOver()) {
			int puntaje = juego.getPuntaje();
			gestorPantallas.crearPantallaPartidaTerminada(nombreJugador, puntaje, tamanioMatriz, nivel);
		}
	}
	
	public void actualizarVista() {
		juegoVista.mostrarTablero(obtenerValoresSimplificados());
		juegoVista.mostrarProximaFicha(juego.getProximoValorFicha());
		juegoVista.mostrarMovimientoSugerido(juego.getMovimientoSugerido());
	}
	
	
	
	private int[][] obtenerValoresSimplificados() {
		int[][] valores = new int[tamanioMatriz][tamanioMatriz];
		
		for (int fila = 0; fila < tamanioMatriz; fila++) {
			for (int col = 0; col < tamanioMatriz; col++) {
				Ficha ficha = juego.getTablero().getFicha(fila, col);
				valores[fila][col] = (ficha == null) ? 0 : ficha.getValor();
			}
		}
		return valores;
}


	private Direccion traducirTecla(int codigoTecla) {
	    switch (codigoTecla) {
	        case KeyEvent.VK_RIGHT: return Direccion.DERECHA;
	        case KeyEvent.VK_LEFT:  return Direccion.IZQUIERDA;
	        case KeyEvent.VK_UP:    return Direccion.ARRIBA;
	        case KeyEvent.VK_DOWN:  return Direccion.ABAJO;
	        default: return null;
	    }
	}
}
