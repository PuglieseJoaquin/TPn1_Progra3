package threes;

import javax.swing.UIManager;
import gui.PantallaBienvenida; 

public class Principal {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
		} catch(Exception e){
	            System.out.println(e);
	        }
		
		PantallaBienvenida bienvenida = new PantallaBienvenida();
		bienvenida.setResizable(false);
		bienvenida.setVisible(true);
		bienvenida.setLocationRelativeTo(null);
	}		
}