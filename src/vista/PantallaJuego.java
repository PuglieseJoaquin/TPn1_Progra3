package vista;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

import presentador.JuegoPresentador;

public class PantallaJuego extends JFrame implements JuegoVista {

	private JuegoPresentador juegoPresentador;

	private JPanel panelFondo;
	private JPanel panelJuego;
	private JLabel[] cuadrados;
	private JLabel lblProximaFichaValor;
	private JLabel lblMovimientoSugerido;
	private JLabel lblSuerte;
	private JLabel lblNombreUsuario;
	private JLabel lblProximaFicha;
	private JLabel lblJugadaSugerida;
	private JLabel lblLinea1;
	private JLabel lblLinea2;
	private int tamanioMatriz;

	private JPanel panelMatriz;

	public PantallaJuego(GestorPantallas gestorPantallas, String nombreJugador, int tamanioMatriz, String nivel) {
		this.tamanioMatriz = tamanioMatriz;
		juegoPresentador = new JuegoPresentador(this, gestorPantallas, nombreJugador, tamanioMatriz, nivel);

		configurarPantalla(nivel);
		crearLblSuerte();
		crearLblNombreJugador(nombreJugador);
		crearLblProximaFicha();
		crearLblProximaFichaValor();
		crearLblJugadaSugerida();
		crearLblMovimientoSugerido();
		crearLblLinea1();
		crearLblLinea2();
		crearMatrizVisual();
	}
	
	private void configurarPantalla(String nivel) {
		setTitle("Threes! — Partida (" + nivel + ")");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 420);
		setLocationRelativeTo(null);
		setResizable(false);

		panelFondo = new JPanel();
		setContentPane(panelFondo);
		panelFondo.setLayout(new BorderLayout(0, 0));
		panelFondo.setFocusable(true);
		panelFondo.requestFocusInWindow();

		panelJuego = new JPanel();
		panelJuego.setPreferredSize(new Dimension(210, 0));
		panelJuego.setBackground(new Color(30, 41, 59));
		panelFondo.add(panelJuego, BorderLayout.EAST);
		panelJuego.setLayout(null);
	}
	
	private void crearMatrizVisual() {
		panelMatriz = new JPanel();
		panelFondo.add(panelMatriz, BorderLayout.CENTER);
		panelMatriz.setLayout(new GridLayout(tamanioMatriz, tamanioMatriz, 6, 6));
		panelMatriz.setBackground(new Color(15, 23, 42));
		panelMatriz.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		agregarListener();
		crearCuadradosDeColores();

		juegoPresentador.actualizarVista();
	}
	
	private void agregarListener() {
		panelFondo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int evento = e.getKeyCode();
				juegoPresentador.manejarEventoTeclaDeMovimiento(evento);
			}
		});
	}
	
	private void crearCuadradosDeColores() {
		int matrizTotal = tamanioMatriz * tamanioMatriz;

		cuadrados = new JLabel[matrizTotal];
		for (int i = 0; i < matrizTotal; i++) {
			JLabel cuadrado = new JLabel();
			cuadrado.setHorizontalAlignment(SwingConstants.CENTER);
			cuadrado.setFont(new Font("Segoe UI", Font.BOLD, 22));
			cuadrado.setOpaque(true);
			cuadrado.setBackground(new Color(241, 245, 249));
			cuadrado.setBorder(BorderFactory.createLineBorder(new Color(51, 65, 85), 1));

			cuadrados[i] = cuadrado;
			panelMatriz.add(cuadrado);
		}
	}

	private void crearLblLinea1() {
		lblLinea1 = new JLabel("Usa las flechas");
		lblLinea1.setForeground(new Color(148, 163, 184));
		lblLinea1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblLinea1.setHorizontalAlignment(SwingConstants.CENTER);
		lblLinea1.setBounds(15, 250, 180, 20);
		panelJuego.add(lblLinea1);
	}	
	
	private void crearLblLinea2() {
		lblLinea2 = new JLabel("para deslizar");
		lblLinea2.setForeground(new Color(148, 163, 184));
		lblLinea2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblLinea2.setHorizontalAlignment(SwingConstants.CENTER);
		lblLinea2.setBounds(15, 270, 180, 20);
		panelJuego.add(lblLinea2);
	}

	private void crearLblMovimientoSugerido() {
		lblMovimientoSugerido = new JLabel();
		lblMovimientoSugerido.setForeground(new Color(241, 245, 249));
		lblMovimientoSugerido.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblMovimientoSugerido.setHorizontalAlignment(SwingConstants.CENTER);
		lblMovimientoSugerido.setVerticalAlignment(SwingConstants.CENTER);
		lblMovimientoSugerido.setBackground(new Color(15, 23, 42));

		lblMovimientoSugerido.setBounds(15, 195, 180, 38);
		panelJuego.add(lblMovimientoSugerido);
	}
	
	@Override
	public void mostrarMovimientoSugerido(String movimiento) {
		lblMovimientoSugerido.setText(movimiento);
	}

	private void crearLblJugadaSugerida() {
		lblJugadaSugerida = new JLabel("Jugada Sugerida:");
		lblJugadaSugerida.setForeground(new Color(203, 213, 225));
		lblJugadaSugerida.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblJugadaSugerida.setBounds(15, 170, 180, 20);
		panelJuego.add(lblJugadaSugerida);
	}

	private void crearLblProximaFichaValor() {
		lblProximaFichaValor = new JLabel();
		lblProximaFichaValor.setForeground(new Color(15, 23, 42));
		lblProximaFichaValor.setHorizontalAlignment(SwingConstants.CENTER);
		lblProximaFichaValor.setVerticalAlignment(SwingConstants.CENTER);
		lblProximaFichaValor.setOpaque(true);
		lblProximaFichaValor.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblProximaFichaValor.setBounds(15, 115, 180, 38);
		lblProximaFichaValor.setBorder(BorderFactory.createLineBorder(new Color(51, 65, 85)));
		panelJuego.add(lblProximaFichaValor);
	}

	private void crearLblProximaFicha() {
		lblProximaFicha = new JLabel("Próxima Ficha:");
		lblProximaFicha.setForeground(new Color(203, 213, 225));
		lblProximaFicha.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblProximaFicha.setBounds(15, 90, 180, 20);
		panelJuego.add(lblProximaFicha);
	}

	private void crearLblNombreJugador(String nombreJugador) {
		lblNombreUsuario = new JLabel(nombreJugador);
		lblNombreUsuario.setForeground(new Color(16, 185, 129));
		lblNombreUsuario.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNombreUsuario.setBackground(new Color(15, 23, 42));
		lblNombreUsuario.setOpaque(true);
		lblNombreUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreUsuario.setVerticalAlignment(SwingConstants.CENTER);
		lblNombreUsuario.setBounds(15, 42, 180, 32);
		lblNombreUsuario.setBorder(BorderFactory.createLineBorder(new Color(51, 65, 85)));
		panelJuego.add(lblNombreUsuario);
	}

	private void crearLblSuerte() {
		lblSuerte = new JLabel("¡A Jugar!");
		lblSuerte.setForeground(new Color(248, 250, 252));
		lblSuerte.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblSuerte.setBounds(15, 12, 180, 25);
		panelJuego.add(lblSuerte);
	}

	@Override
	public void mostrarTablero(int[][] valoresDeFichas) {

        int tamanio = valoresDeFichas.length;
        
        for (int fila = 0; fila < tamanio; fila++) {
            for (int col = 0; col < tamanio; col++) {
                int valor = valoresDeFichas[fila][col];
                int index = fila * tamanio + col;

                if (valor == 0) {
                    cuadrados[index].setText("");
                    cuadrados[index].setBackground(new Color(241, 245, 249));
                } else {
                	cuadrados[index].setText(String.valueOf(valor));
                	cuadrados[index].setBackground(colorParaValor(valor));
                	cuadrados[index].setForeground(colorTextoParaValor(valor));
                }
            }
        }
	}
        
    @Override
    public void mostrarProximaFicha(int valor) {
    		lblProximaFichaValor.setText(String.valueOf(valor));
    		lblProximaFichaValor.setBackground(colorParaValor(valor));
    		lblProximaFichaValor.setForeground(colorTextoParaValor(valor));
    		}

	private Color colorParaValor(int valor) {
		switch (valor) {
		case 1:
			return new Color(224, 242, 254);
		case 2:
			return new Color(253, 232, 232);
		case 3:
			return new Color(59, 130, 246);
		case 6:
			return new Color(239, 68, 68);
		case 12:
			return new Color(16, 185, 129);
		case 24:
			return new Color(245, 158, 11);
		case 48:
			return new Color(139, 92, 246);
		case 96:
			return new Color(236, 72, 153);
		case 192:
			return new Color(14, 165, 233);
		case 384:
			return new Color(202, 138, 4);
		case 768:
			return new Color(217, 70, 239);
		case 1536:
			return new Color(79, 70, 229);
		default:
			return new Color(100, 116, 139);
		}
	}

	private Color colorTextoParaValor(int valor) {
		switch (valor) {
		case 1:
			return new Color(37, 99, 235);
		case 2:
			return new Color(225, 29, 72);
		default:
			return Color.WHITE;
		}
	}
}