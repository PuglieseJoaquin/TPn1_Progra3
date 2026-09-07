package vista;

public interface GestorInterfaz {
	void crearPantallaMenu();
	
	void crearPantallaJuego(String nombre, int tamanioMatriz, String nivel);
	
	void crearPantallaPartidaTerminada(String nombreJugador, int puntaje, int tamanioMatriz, String nivel);
	
	void crearPantallaBienvenida();
	
	void crearPantallaRanking();

}
