package presentador;

import vista.GestorPantallas;

public class BienvenidaPresentador {
	private GestorPantallas gestorPantallas;
	
	public BienvenidaPresentador(GestorPantallas gestorPantallas) {
		this.gestorPantallas = gestorPantallas;	
	}

	public void manejarClickBotonSalir() {
		System.exit(0);	
	}

	public void manejarClickBotonMenu() {
		gestorPantallas.crearPantallaMenu();
	}

}
