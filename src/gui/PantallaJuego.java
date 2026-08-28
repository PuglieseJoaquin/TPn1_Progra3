package gui;

import java.awt.Color;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.Timer;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Panel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.MatteBorder;
import java.awt.GridLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.BoxLayout;
import javax.swing.JTable;
import java.awt.BorderLayout;
import javax.swing.JInternalFrame;
import javax.swing.border.BevelBorder;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import logica.Ficha;
import logica.Juego;
import logica.Tablero;

public class PantallaJuego extends JFrame {

    private JPanel panelFondo;
    private JPanel panelJuego;
    private JLabel[] cuadrados;
    private Juego juego;
    private JLabel lblProximaFichaValor;
    private JLabel lblMovimientoSugerido;
   
   

    public PantallaJuego(String nombreJugador, int tamañoMatriz, int dimensionVentana, String nivel) {
    	
		setTitle("Programación three...s");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 400);
		setLocationRelativeTo(null);
		panelFondo = new JPanel();
		
//		panelFondo.addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyPressed(KeyEvent e) {
//				int evento = e.getKeyCode();
//				juego.mover(evento);
//				actualizarVista();
//				if (juego.isGameOver()) {
//					new PantallaPerdiste(nombre, juego.getPuntaje(), tamañoMatriz, dimensionVentana, nivel);
//				    dispose();
//				}
//			}
//		});
		
		setContentPane(panelFondo);
		panelFondo.setLayout(new BorderLayout(0, 0));
		
		panelFondo.setFocusable(true);
		panelFondo.requestFocusInWindow();
		
		panelJuego = new JPanel();
		panelJuego.setPreferredSize(new Dimension(200, 0));
		panelJuego.setBackground(new Color(60, 179, 113));
		panelFondo.add(panelJuego, BorderLayout.EAST);
		panelJuego.setLayout(null);
		
		//inicio logica
		this.juego = new Juego(tamañoMatriz, nombreJugador);
		
		JLabel lblSuerte = new JLabel("Buena Suerte!!!");
		lblSuerte.setBackground(new Color(102, 205, 170));
		lblSuerte.setForeground(new Color(0, 0, 139));
		lblSuerte.setFont(new Font("Verdana", Font.BOLD, 18));
		lblSuerte.setBounds(10, 10, 161, 45);
		panelJuego.add(lblSuerte);
		
		JLabel lblProximaFicha = new JLabel("Proxima Ficha");
		lblProximaFicha.setForeground(new Color(0, 0, 139));
		lblProximaFicha.setFont(new Font("Verdana", Font.BOLD, 18));
		lblProximaFicha.setBackground(new Color(102, 205, 170));
		lblProximaFicha.setBounds(10, 83, 161, 45);
		panelJuego.add(lblProximaFicha);
		
		JLabel lblJugadaSugerida = new JLabel("Jugada Sugerida");
		lblJugadaSugerida.setForeground(new Color(0, 0, 139));
		lblJugadaSugerida.setFont(new Font("Verdana", Font.BOLD, 18));
		lblJugadaSugerida.setBackground(new Color(102, 205, 170));
		lblJugadaSugerida.setBounds(10, 179, 180, 45);
		panelJuego.add(lblJugadaSugerida);
		
		lblProximaFichaValor = new JLabel("ficha futura");
//		lblProximaFichaValor.setText(String.valueOf(juego.getTablero().getProximoValorFicha()));
		lblProximaFichaValor.setForeground(Color.BLACK);
		lblProximaFichaValor.setHorizontalAlignment(SwingConstants.CENTER);
		lblProximaFichaValor.setVerticalAlignment(SwingConstants.CENTER);
		lblProximaFichaValor.setOpaque(true);
		lblProximaFichaValor.setFont(new Font("Verdana", Font.BOLD, 18));
//		lblProximaFichaValor.setBackground(colorParaValor(juego.getTablero().getProximoValorFicha()));
		lblProximaFichaValor.setBounds(39, 137, 80, 45);
		panelJuego.add(lblProximaFichaValor);
		
		lblMovimientoSugerido = new JLabel("sugerencia");
		lblMovimientoSugerido.setForeground(Color.BLACK);
		lblMovimientoSugerido.setFont(new Font("Verdana", Font.BOLD, 18));
		lblMovimientoSugerido.setHorizontalAlignment(SwingConstants.CENTER);
		lblMovimientoSugerido.setVerticalAlignment(SwingConstants.CENTER);
		lblMovimientoSugerido.setOpaque(true);
		lblMovimientoSugerido.setBackground(new Color(102, 205, 170));
		lblMovimientoSugerido.setText(juego.getMovimientoSugerido());
		lblMovimientoSugerido.setBounds(20, 221, 125, 45);
		panelJuego.add(lblMovimientoSugerido);
		
		JLabel lblNombreUsuario = new JLabel("nombreUsuario");
		lblNombreUsuario.setForeground(new Color(178, 34, 34));
		lblNombreUsuario.setFont(new Font("Verdana", Font.BOLD, 18));
		lblNombreUsuario.setBackground(new Color(102, 205, 170));
		lblNombreUsuario.setOpaque(true);
		lblNombreUsuario.setText(nombreJugador);
		lblNombreUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreUsuario.setVerticalAlignment(SwingConstants.CENTER);
		lblNombreUsuario.setBounds(20, 46, 125, 45);
		panelJuego.add(lblNombreUsuario);
		
		JLabel lblEligeTuMovimiento = new JLabel("Move!");
		lblEligeTuMovimiento.setForeground(new Color(0, 100, 0));
		lblEligeTuMovimiento.setFont(new Font("Verdana", Font.BOLD, 18));
		lblEligeTuMovimiento.setBackground(new Color(102, 205, 170));
		lblEligeTuMovimiento.setBounds(20, 264, 190, 62);
		panelJuego.add(lblEligeTuMovimiento);
		
		JPanel panelMatriz = new JPanel();
		panelFondo.add(panelMatriz, BorderLayout.CENTER);
		panelMatriz.setLayout(new GridLayout(tamañoMatriz, tamañoMatriz, 10, 10));
		panelMatriz.setBackground(new Color(0, 128, 128));
		
		panelFondo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int evento = e.getKeyCode();
				juego.mover(evento);
				actualizarVista();
				if (juego.isGameOver()) {
					new PantallaPerdiste(nombreJugador, juego.getPuntaje(), tamañoMatriz, dimensionVentana, nivel);
				    dispose();
				}
			}
		});

		// Agrego los cuadrados
		int matrizTotal = tamañoMatriz * tamañoMatriz;
		
		cuadrados = new JLabel[matrizTotal];
		for (int i = 0; i < matrizTotal; i++) {
		    JLabel cuadrado = new JLabel();
		    cuadrado.setHorizontalAlignment(SwingConstants.CENTER);
		    cuadrado.setFont(new Font("Tahoma", Font.BOLD, 25));
		    cuadrado.setOpaque(true);
		    cuadrado.setBackground(Color.GREEN);
		    cuadrado.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		    
		    cuadrados[i] = cuadrado;
		    panelMatriz.add(cuadrado);
		}
		actualizarVista();
    }

    private void actualizarVista() {
    	//print de prueba
    	System.out.println(juego.getPuntajes());
    	
		Tablero tablero = juego.getTablero();
		int tamaño = tablero.getTamaño();
		
	    for (int fila = 0; fila < tamaño; fila++) {
	        for (int col = 0; col < tamaño; col++) {
	            Ficha f = tablero.getFicha(fila, col);
	            int index = fila * tamaño + col;

	            if (f == null) {
	            	cuadrados[index].setText("");
	            	cuadrados[index].setBackground(Color.white);
	            } else {
	            	cuadrados[index].setText(String.valueOf(f.getValor()));
	            	cuadrados[index].setBackground(colorParaValor(f.getValor()));
	            }
	        }
	    }
		lblProximaFichaValor.setText(String.valueOf(juego.getTablero().getProximoValorFicha()));
		lblProximaFichaValor.setBackground(colorParaValor(juego.getTablero().getProximoValorFicha()));
		
		lblMovimientoSugerido.setText(juego.getMovimientoSugerido());
	}
	
	private Color colorParaValor(int valor) {
	    switch (valor) {
	        case 3: return Color.BLUE;
	        case 6: return Color.RED;
	        case 12: return Color.ORANGE;
	        case 24: return Color.magenta;
	        case 48: return Color.CYAN;
	        case 96: return Color.GREEN;
	        case 192: return Color.PINK;
	        case 384: return Color.yellow;
	        case 768: return Color.orange;
	        case 1536: return Color.blue;
	        default: return Color.GRAY;
	    }
	}
}
		


