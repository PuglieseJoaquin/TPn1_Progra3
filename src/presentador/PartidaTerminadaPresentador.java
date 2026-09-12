package presentador;

import vista.GestorPantallas;

public class PartidaTerminadaPresentador {
	
	private GestorPantallas gestorPantallas;

	public PartidaTerminadaPresentador(GestorPantallas gestorPantallas) {
		this.gestorPantallas = gestorPantallas;	
	}
	
	public void manejarClickBotonJugarDeNuevo(String nombre, int tamanioMatriz, String nivel) {
		gestorPantallas.crearPantallaJuego(nombre, tamanioMatriz, nivel);
	}
	
	public void manejarClickVolverAlMenu() {
		gestorPantallas.crearPantallaMenu();	
	}	
}