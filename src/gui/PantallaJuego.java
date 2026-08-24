package gui;

import java.awt.Color;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
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

public class PantallaJuego extends JFrame {

	private JPanel panelFondo;
	
	//aca va una matriz VISUAL
	
	private Timer timer = null;

	public PantallaJuego(String nombre, int matSize, int dimensionVentana, String nivel) {

		setTitle("Threes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, dimensionVentana, dimensionVentana);
		setLocationRelativeTo(null);
		panelFondo = new JPanel();
		panelFondo.setBackground(Color.YELLOW);
		panelFondo.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelFondo);
	}
}
