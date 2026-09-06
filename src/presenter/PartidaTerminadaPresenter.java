package presenter;

import gui.GestorPantallas;

public class PartidaTerminadaPresenter {
	private GestorPantallas gestorPantallas;


	public PartidaTerminadaPresenter(GestorPantallas gestorPantallas) {
		this.gestorPantallas = gestorPantallas;	
	}

	public void manejarClickBotonJugarDeNuevo(String nombre, int tamanioMatriz, String nivel) {
		gestorPantallas.crearPantallaJuego(nombre, tamanioMatriz, nivel);
	}

	public void manejarClickVolverAlMenu() {
		gestorPantallas.crearPantallaMenu();	
	}
	
	
}
