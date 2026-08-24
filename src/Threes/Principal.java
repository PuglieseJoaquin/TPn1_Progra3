package Threes;

import javax.swing.UIManager;
import gui.PantallaInicial;

public class Principal {

	public static void main(String[] args) {
		try {
			
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
		 } catch(Exception e){
	            System.out.println(e);
	        }
		PantallaInicial menu = new PantallaInicial();
		menu.setResizable(false);
		menu.setVisible(true);
		menu.setLocationRelativeTo(null);
	}		
		
		
		/* GRUPO N°5
		. Joa: pugliesejoaquinsebastian214@gmail.com
		mial: 
		. Julian: julianponceflores93@gmail.com
		. Juan: juanspereyra@live.com
		. Rami: ramisalv777@gmail.com
		 */
}
