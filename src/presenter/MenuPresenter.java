package presenter;

import gui.GestorPantallas;
import gui.MenuVista;
import logica.ReproductorMusica;

public class MenuPresenter {
	private GestorPantallas gestorPantallas;
	private MenuVista menuVista;
	
	public MenuPresenter(MenuVista menuVista, GestorPantallas gestorPantallas) {
		this.gestorPantallas = gestorPantallas;
		this.menuVista = menuVista;
		
	}
	
	public void manejarClickBotonSalir() {
		System.exit(0);	
	}

	public void manejarClickBotonComenzarJuego(String nombre, int nivel) {
		if (!esNombreValido(nombre)) {
			menuVista.mostrarMensajeError();
			return;
		} else {
			menuVista.mostrarMensajeReglas();
		}
        
		ReproductorMusica.reproducirLoop("/audio/backSound.wav");

		comenzarNivel(nombre, nivel);
	}

	private void comenzarNivel(String nombre, int nivel) {
		
		switch(nivel) {

	    case 0: //"Clásico 4x4"
	    		gestorPantallas.crearPantallaJuego(nombre, 4, "Clásico");
	        break;
	    case 1: //"Extra 5x5"
	    		gestorPantallas.crearPantallaJuego(nombre, 5, "Extra");
	        break;
	    case 2: //"Supremo 6x6"
	    		gestorPantallas.crearPantallaJuego(nombre, 6, "Supremo");
	        break;
	    default:
	        break;
    }
}		

	private static boolean esNombreValido(String nombre) {
        return (nombre.length() > 3 && !nombre.isEmpty());
    }

	public void manejarClickBotonRanking() {
		gestorPantallas.crearPantallaRanking();		
	}
}
