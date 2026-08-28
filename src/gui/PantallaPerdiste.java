package gui;

import java.awt.*;
import javax.swing.*;

	public class PantallaPerdiste extends JFrame {

	    public PantallaPerdiste(String nombre, int puntaje, int tamañoMatriz, int dimensionVentana, String nivel) {
	        setTitle("Game Over");
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setBounds(100, 100, 400, 300);
	        setLocationRelativeTo(null);
	        
	        JPanel panel = new JPanel();
	        panel.setLayout(new GridLayout(4, 1, 10, 10));
	        panel.setBackground(new Color(60, 179, 113));
	        
	        //labels ventana perdiste
	        JLabel Titulo = new JLabel("GAME OVER", SwingConstants.CENTER);
	        Titulo.setFont(new Font("Verdana", Font.BOLD, 24));
	        Titulo.setForeground(new Color(178, 35, 34));

	        JLabel Nombre = new JLabel("Nombre: " + nombre, SwingConstants.CENTER);
	        Nombre.setFont(new Font("Verdana", Font.PLAIN, 18));

	        JLabel Puntaje = new JLabel("Puntaje: " + puntaje, SwingConstants.CENTER);
	        Puntaje.setFont(new Font("Verdana", Font.PLAIN, 18));
	        
	        //botones
	        JPanel panelBotones = new JPanel();
	        panelBotones.setBackground(new Color(60, 179, 113));

	        JButton btnJugarDeNuevo = new JButton("Jugar de nuevo");
	        btnJugarDeNuevo.addActionListener(e -> {
	        	PantallaJuego nuevaPartida = new PantallaJuego(nombre, tamañoMatriz, dimensionVentana, nivel);
	            nuevaPartida.setVisible(true);
	            dispose();
	        });

	        JButton btnVolverMenu = new JButton("Volver al menu");
	        btnVolverMenu.addActionListener(e -> {
	        	PantallaInicial inicio = new PantallaInicial();
	            inicio.setVisible(true);
	            dispose();
	        });

	        panelBotones.add(btnJugarDeNuevo);
	        panelBotones.add(btnVolverMenu);

	        panel.add(Titulo);
	        panel.add(Nombre);
	        panel.add(Puntaje);
	        panel.add(panelBotones);

	        setContentPane(panel);
	        setVisible(true);
	    }
}
