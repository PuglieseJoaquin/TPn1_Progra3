package gui;

import java.awt.Color;

import java.awt.Font;

import javax.swing.BorderFactory;
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

public class PantallaJuego extends JFrame {

    private JPanel panelFondo;
    private JPanel panelJuego;

    public PantallaJuego(String nombre, int tamañoMatriz, int dimensionVentana, String nivel) {
		setTitle("Programación three...s");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 400);
		setLocationRelativeTo(null);
		panelFondo = new JPanel();
		setContentPane(panelFondo);
		panelFondo.setLayout(new BorderLayout(0, 0));
		
		JPanel panelJuego = new JPanel();
		panelJuego.setPreferredSize(new Dimension(200, 0));
		panelJuego.setBackground(new Color(60, 179, 113));
		panelFondo.add(panelJuego, BorderLayout.EAST);
		panelJuego.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Buena Suerte!!!");
		lblNewLabel.setBackground(new Color(102, 205, 170));
		lblNewLabel.setForeground(new Color(178, 34, 34));
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 10, 161, 45);
		panelJuego.add(lblNewLabel);
		
		JLabel lblProximaFicha = new JLabel("Proxima Ficha");
		lblProximaFicha.setForeground(new Color(178, 34, 34));
		lblProximaFicha.setFont(new Font("Verdana", Font.BOLD, 18));
		lblProximaFicha.setBackground(new Color(102, 205, 170));
		lblProximaFicha.setBounds(10, 83, 161, 45);
		panelJuego.add(lblProximaFicha);
		
		JLabel lblJugadaSugerida = new JLabel("Jugada Sugerida");
		lblJugadaSugerida.setForeground(new Color(178, 34, 34));
		lblJugadaSugerida.setFont(new Font("Verdana", Font.BOLD, 18));
		lblJugadaSugerida.setBackground(new Color(102, 205, 170));
		lblJugadaSugerida.setBounds(10, 179, 180, 45);
		panelJuego.add(lblJugadaSugerida);
		
		JLabel lblProximaFicha_1 = new JLabel("Proxima Ficha");
		lblProximaFicha_1.setForeground(new Color(178, 34, 34));
		lblProximaFicha_1.setFont(new Font("Verdana", Font.BOLD, 18));
		lblProximaFicha_1.setBackground(new Color(102, 205, 170));
		lblProximaFicha_1.setBounds(53, 134, 80, 45);
		panelJuego.add(lblProximaFicha_1);
		
		JLabel lblProximaFicha_2 = new JLabel("Proxima Ficha");
		lblProximaFicha_2.setForeground(new Color(178, 34, 34));
		lblProximaFicha_2.setFont(new Font("Verdana", Font.BOLD, 18));
		lblProximaFicha_2.setBackground(new Color(102, 205, 170));
		lblProximaFicha_2.setBounds(59, 221, 74, 45);
		panelJuego.add(lblProximaFicha_2);
		
		JLabel lblProximaFicha_1_1 = new JLabel("Proxima Ficha");
		lblProximaFicha_1_1.setForeground(new Color(178, 34, 34));
		lblProximaFicha_1_1.setFont(new Font("Verdana", Font.BOLD, 18));
		lblProximaFicha_1_1.setBackground(new Color(102, 205, 170));
		lblProximaFicha_1_1.setBounds(53, 46, 80, 45);
		panelJuego.add(lblProximaFicha_1_1);
		
		JLabel lblEligeTuMovimiento = new JLabel("Elige tu movimiento");
		lblEligeTuMovimiento.setForeground(new Color(178, 34, 34));
		lblEligeTuMovimiento.setFont(new Font("Verdana", Font.BOLD, 18));
		lblEligeTuMovimiento.setBackground(new Color(102, 205, 170));
		lblEligeTuMovimiento.setBounds(10, 257, 190, 50);
		panelJuego.add(lblEligeTuMovimiento);
		
		JPanel panelMatriz = new JPanel();
		panelFondo.add(panelMatriz, BorderLayout.CENTER);
		panelMatriz.setLayout(new GridLayout(4, 4, 10, 10));
		panelMatriz.setBackground(new Color(0, 128, 128));

		// Agrego los cuadrados
		for (int i = 0; i < tamañoMatriz * tamañoMatriz; i++) {
		    JLabel cuadrado = new JLabel();
		    cuadrado.setHorizontalAlignment(SwingConstants.CENTER);
		    cuadrado.setFont(new Font("Tahoma", Font.BOLD, 25));
		    cuadrado.setOpaque(true);
		    cuadrado.setBackground(Color.GREEN);
		    cuadrado.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		    panelMatriz.add(cuadrado);
		}
		
    }
}
