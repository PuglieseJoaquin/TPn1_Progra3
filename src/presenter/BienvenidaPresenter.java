package presenter;

import gui.GestorPantallas;

public class BienvenidaPresenter {
	private GestorPantallas gestorPantallas;
	
	public BienvenidaPresenter(GestorPantallas gestorPantallas) {
		this.gestorPantallas = gestorPantallas;	
	}

	public void manejarClickBotonSalir() {
		System.exit(0);	
	}

	public void manejarClickBotonMenu() {
		gestorPantallas.crearPantallaMenu();
	}

}
