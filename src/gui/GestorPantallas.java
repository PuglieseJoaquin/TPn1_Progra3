package gui;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class GestorPantallas {

	static PantallaBienvenida bienvenida = crearPantallaBienvenida();
	static PantallaMenu menu = crearPantallaMenu();
	static PantallaJuego juego = null;
	static PantallaPerdiste perdiste = null;
	static PantallaRanking ranking = null;
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			}	catch(Exception e){System.out.println(e);
				}
	
		mostrarPantalla(bienvenida);

			
		}
	
	
	
	
	
	
	
	
	public static void clickBtnJuego(String nombre, int nivel) {
		
        if (!esNombreValido(nombre)) {
            JOptionPane.showMessageDialog(null, "Tu nombre debe tener al menos 3 letras.", "Nombre no válido",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,
                    "Suma 1 + 2 para comenzar.\nLuego... combina múltiplos de 3!",
                    "Reglas del juego", JOptionPane.INFORMATION_MESSAGE);
            	
            // Estoy hay que pàsarlo a la logica, un metodo que sea "inciarMusica" y otro "iniciarJuego" donde le pasa el indice y la logica le devuelve la pantalla?
            // Reproducimos la musica al inicial juego	
            logica.ReproductorMusica.reproducirLoop("/audio/backSound.wav");
            
            switch (nivel) {
            case 0: //"Clásico 4x4"
                PantallaJuego clasico = new PantallaJuego(nombre, 4, 750, "Clásico");
                clasico.setResizable(false);
                clasico.setVisible(true);
                break;
            case 1: //"Extra 5x5"
                PantallaJuego extra = new PantallaJuego(nombre, 5, 750, "Extra");
                extra.setResizable(false);
                extra.setVisible(true);
                break;
            case 2: //"Supremo 6x6"
                PantallaJuego supremo = new PantallaJuego(nombre, 6, 750, "Supremo");
                supremo.setResizable(false);
                supremo.setVisible(true);
                break;
            default:
                break;
            }
        }
		
	}
	
	
	
	public static void clickBtnVolverAlMenu() {
		
	}
	
	
	public static void clickBtnMenu() {
		mostrarPantalla(menu);
		ocultarPantallasExcepto(menu);
	}
	
	private static PantallaMenu crearPantallaMenu() {
		 PantallaMenu menu = new PantallaMenu(); 
         return menu;
	}

	private static PantallaBienvenida crearPantallaBienvenida() {
		PantallaBienvenida bienvenida = new PantallaBienvenida();
		return  bienvenida;
	}		
	
	private static JFrame mostrarPantalla (JFrame pantalla) {
		pantalla.setVisible(true);
		return pantalla;
	}
	
	private static JFrame ocultarPantalla(JFrame pantalla) {
		pantalla.setVisible(false);
		return pantalla;
	}
	
	private static boolean esNombreValido(String nombre) {
        return (nombre.length() > 3 && !nombre.isEmpty());
    }
	
	private static void ocultarPantallasExcepto (JFrame pantallaMantener) {
		JFrame[] todasLasPantallas = {bienvenida, menu, juego, perdiste, ranking};
			
		for (JFrame pantalla : todasLasPantallas) {
			if (pantalla != null && pantalla != pantallaMantener)
			pantalla.setVisible(false);	
		}
	}
	
	
}
