package gui;

public interface JuegoVista {
	void mostrarTablero(int[][] valoresDeFichas);
	
	void mostrarProximaFicha(int valor);
	
	void mostrarMovimientoSugerido(String movimiento);
}
