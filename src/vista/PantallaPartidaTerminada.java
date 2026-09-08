package vista;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import presentador.PartidaTerminadaPresentador;

public class PantallaPartidaTerminada extends JFrame {
	
	private PartidaTerminadaPresentador partidaTerminadaPresentador;
	private JPanel panelFondo;
	private JButton btnJugarDeNuevo;
	private String nombre;
	private int puntajePartida;
	private int tamanioMatriz;
	private String nivel;
	private JButton btnVolverMenu;
	private JLabel titulo;
	private JLabel lblNombre;
	private JLabel lblPuntaje;
	
    public PantallaPartidaTerminada(GestorPantallas gestorPantallas, String nombre, int puntajePartida, int tamanioMatriz, String nivel) {
    		this.partidaTerminadaPresentador = new PartidaTerminadaPresentador(gestorPantallas);
    		this.nombre = nombre;
    		this.puntajePartida = puntajePartida;
    		this.tamanioMatriz = tamanioMatriz;
    		this.nivel = nivel;
    		
        configurarPantalla();
        crearTituloGameOver();
        crearLblNombre();
        crearLblPuntaje();
        crearBtnJugarDeNuevo();
        crearBtnVolverMenu();
    }

	private void configurarPantalla() {
		setTitle("Threes! — Game Over");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        
        panelFondo = new JPanel();
        panelFondo.setBackground(new Color(30, 41, 59)); 
        setContentPane(panelFondo);
        panelFondo.setLayout(null);
	}

	private void crearTituloGameOver() {
        titulo = new JLabel("GAME OVER", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setForeground(new Color(239, 68, 68));
        titulo.setBounds(50, 35, 500, 35);
        panelFondo.add(titulo);
	}

	private void crearLblNombre() {
        lblNombre = new JLabel("Jugador: " + nombre, SwingConstants.CENTER);
        lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblNombre.setForeground(new Color(203, 213, 225));
        lblNombre.setBounds(50, 95, 500, 25);
        panelFondo.add(lblNombre);
	}

	private void crearLblPuntaje() {
        lblPuntaje = new JLabel("Puntaje Final: " + puntajePartida, SwingConstants.CENTER);
        lblPuntaje.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblPuntaje.setForeground(new Color(203, 213, 225));
        lblPuntaje.setBounds(50, 130, 500, 25);
        panelFondo.add(lblPuntaje);
	}

	private void crearBtnJugarDeNuevo() {
        btnJugarDeNuevo = new JButton("Jugar de nuevo");
        btnJugarDeNuevo.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnJugarDeNuevo.setForeground(Color.WHITE);
        btnJugarDeNuevo.setBackground(new Color(16, 185, 129));
        btnJugarDeNuevo.setFocusPainted(false);
        btnJugarDeNuevo.setBounds(160, 205, 280, 42);

        agregarListenerBtnJugarDeNuevo();
        panelFondo.add(btnJugarDeNuevo);
	}

	private void agregarListenerBtnJugarDeNuevo() {
        btnJugarDeNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                partidaTerminadaPresentador.manejarClickBotonJugarDeNuevo(nombre, tamanioMatriz, nivel);
            }
        });
	}

	private void crearBtnVolverMenu() {
        btnVolverMenu = new JButton("Volver al menú");
        btnVolverMenu.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnVolverMenu.setForeground(new Color(241, 245, 249));
        btnVolverMenu.setBackground(new Color(51, 65, 85));
        btnVolverMenu.setFocusPainted(false);
        btnVolverMenu.setBounds(160, 260, 280, 38);
        
        agregarListenerBtnVolverMenu();
        panelFondo.add(btnVolverMenu);
	}

	private void agregarListenerBtnVolverMenu() {     
        btnVolverMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                partidaTerminadaPresentador.manejarClickVolverAlMenu();
            }
        });
	}
}