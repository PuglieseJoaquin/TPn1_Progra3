package gui;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class GestorPantallas implements GestorInterfaz {

	private PantallaBienvenida bienvenida;
	private PantallaMenu menu;
	private PantallaJuego juego;
	private PantallaPartidaTerminada partidaTerminada;
	private PantallaRanking ranking;
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			}	catch(Exception e){System.out.println(e);
				}
		
		GestorPantallas gestorPantalla = new GestorPantallas();
		gestorPantalla.crearPantallaBienvenida();	
		
		}
	
	public void crearPantallaMenu() {
		 menu = new PantallaMenu(this);
		 mostrarPantalla(menu); 
	}
	
	public void crearPantallaJuego(String nombre, int tamanioMatriz, String nivel) {
		 juego = new PantallaJuego(this, nombre, tamanioMatriz, nivel); 
		 mostrarPantalla(juego);
	}
	
	public void crearPantallaPartidaTerminada(String nombreJugador, int puntaje, int tamanioMatriz, String nivel) {
		partidaTerminada = new PantallaPartidaTerminada(this, nombreJugador, puntaje, tamanioMatriz, nivel); 
		mostrarPantalla(partidaTerminada);
	}
	
	public void crearPantallaBienvenida() {
		 bienvenida = new PantallaBienvenida(this);
		 mostrarPantalla(bienvenida);
	}		
	
	public void crearPantallaRanking() {
		ranking = new PantallaRanking(this);
		mostrarPantalla(ranking);
	}
	
	public JFrame mostrarPantalla(JFrame pantalla) {
		pantalla.setVisible(true);
		pantalla.setResizable(false);
		ocultarPantallas(pantalla);
		return pantalla;
	}	
	
	public void ocultarPantallas(JFrame pantallaActual) {
		JFrame[] todasLasPantallas = {bienvenida, menu, juego, partidaTerminada, ranking};
			
		for (JFrame pantalla : todasLasPantallas) {
			if(pantalla != pantallaActual && pantalla != null) {
				pantalla.setVisible(false);
			}
		}
	}
}
