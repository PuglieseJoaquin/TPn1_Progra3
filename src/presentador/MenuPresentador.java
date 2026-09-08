package presentador;

import modelo.ReproductorMusica;
import vista.GestorPantallas;
import vista.MenuVista;

public class MenuPresentador {
	private GestorPantallas gestorPantallas;
	private MenuVista menuVista;
	
	public MenuPresentador(MenuVista menuVista, GestorPantallas gestorPantallas) {
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
		if(nivel==0) {
			gestorPantallas.crearPantallaJuego(nombre, 4, "Clásico 4x4");
		}
		else if (nivel==1) {
			gestorPantallas.crearPantallaJuego(nombre, 5, "Extra 5x5");
	    
	    } else if (nivel==2) {
	    	gestorPantallas.crearPantallaJuego(nombre, 6, "Supremo 6x6");
	    }
	}		

	private static boolean esNombreValido(String nombre) {
        return (nombre.length() >= 3);
    }

	public void manejarClickBotonRanking() {
		gestorPantallas.crearPantallaRanking();		
	}
}
