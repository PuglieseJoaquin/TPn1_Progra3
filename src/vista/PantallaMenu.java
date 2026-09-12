package vista;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import presentador.MenuPresentador;

public class PantallaMenu extends JFrame implements MenuVista{

    private JPanel panelFondo;
    private JTextField textNombre;
    private MenuPresentador menuPresentador;
	private JLabel lblNombre;
	private JLabel lblTitulo;
	private JButton btnSalir;
	private JComboBox<String> comboBoxLevels;
	private JButton btnStart;
	private JButton btnRanking;
	private JLabel lblNivel;

    public PantallaMenu(GestorPantallas gestorPantallas) {
    		menuPresentador = new MenuPresentador(this, gestorPantallas);
    		
        configurarPantalla();
        crearLblNombre();
        crearTextNombre();
        crearLblNivel();
        comboBoxLevels = crearComboNiveles();
        crearBtnEmpezarJuego();
        crearBtnRanking();
        crearBtnSalir();
    }
    
    
	private void configurarPantalla() {
		setTitle("Threes! — Menú Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        setLocationRelativeTo(null);
        setResizable(false);

        panelFondo = new JPanel();
        panelFondo.setBackground(new Color(30, 41, 59));
        setContentPane(panelFondo);
        panelFondo.setLayout(null);

        lblTitulo = new JLabel("TRABAJO PRÁCTICO 1 : THREES");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(248, 250, 252));
        lblTitulo.setBounds(50, 30, 500, 35);
        panelFondo.add(lblTitulo);
	}
	
	
	private void crearLblNombre() {
		lblNombre = new JLabel("INGRESE SU NOMBRE:");
        lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblNombre.setForeground(new Color(203, 213, 225));
        lblNombre.setBounds(60, 95, 200, 22);
        panelFondo.add(lblNombre);
	}
	
	
	private void crearTextNombre() {
		textNombre = new JTextField();
        textNombre.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        textNombre.setForeground(new Color(15, 23, 42));
        textNombre.setBackground(new Color(241, 245, 249));
        textNombre.setHorizontalAlignment(SwingConstants.CENTER);
        textNombre.setBounds(60, 122, 200, 38);
        
        agregarListenerTextNombre();
	}

	
	private void agregarListenerTextNombre() {
        textNombre.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (textNombre.getText().length() == 10) {
                    e.consume();
                }
            }
        });
        panelFondo.add(textNombre);
	}
	
	
	private void crearLblNivel() {
		lblNivel = new JLabel("ELEGIR NIVEL");
        lblNivel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblNivel.setForeground(new Color(203, 213, 225));
        lblNivel.setBounds(340, 95, 180, 22);
        panelFondo.add(lblNivel);
	}
	
	
	private JComboBox<String> crearComboNiveles() {
		comboBoxLevels = new JComboBox<String>();
        comboBoxLevels.setFont(new Font("Segoe UI", Font.BOLD, 14));
        comboBoxLevels.setForeground(new Color(15, 23, 42));
        comboBoxLevels.setBackground(new Color(241, 245, 249));
        comboBoxLevels.setModel(new DefaultComboBoxModel<String>(new String[] {"Clásico 4x4", "Extra 5x5", "Supremo 6x6"}));
        comboBoxLevels.setBounds(340, 122, 180, 38);
        
        DefaultListCellRenderer centerRenderer = new DefaultListCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        comboBoxLevels.setRenderer(centerRenderer);
        panelFondo.add(comboBoxLevels);
		return comboBoxLevels;
	}
	
	
	private void crearBtnEmpezarJuego() {
		btnStart = new JButton("Empezar el Juego");
        btnStart.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnStart.setForeground(Color.WHITE);
        btnStart.setBackground(new Color(16, 185, 129));
        btnStart.setFocusPainted(false);
        btnStart.setBorderPainted(false);
        btnStart.setBounds(160, 210, 280, 42);
        btnStart.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        agregarListenerBtnStart();
        panelFondo.add(btnStart);
	}
	
	
	private void crearBtnRanking() {
		btnRanking = new JButton("Ranking");
        btnRanking.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnRanking.setForeground(Color.WHITE); 
        btnRanking.setBackground(new Color(51, 65, 85));   
        btnRanking.setBounds(160, 265, 280, 38);
        
        agregarListenerBtnRanking();
        panelFondo.add(btnRanking);
	}

	
	private void agregarListenerBtnRanking() {
        btnRanking.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menuPresentador.manejarClickBotonRanking();
            }
        });
	}

	
	private void agregarListenerBtnStart() {
        btnStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	String nombreJugador = textNombre.getText();
            	int nivelElegido = comboBoxLevels.getSelectedIndex();
            	
            	menuPresentador.manejarClickBotonComenzarJuego(nombreJugador, nivelElegido);
            }
        });
	}
	
	
	private void crearBtnSalir() {
		btnSalir = new JButton("SALIR");
        btnSalir.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnSalir.setForeground(new Color(100, 100, 100));
        btnSalir.setBackground(new Color(220, 220, 220));
        btnSalir.setFocusPainted(false);
        btnSalir.setBounds(221, 315, 150, 35);
        
        agregarListenerBtnSalir();
        panelFondo.add(btnSalir);	
	}

	
	private void agregarListenerBtnSalir() {
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menuPresentador.manejarClickBotonSalir();
            }
        });
	}

	
	@Override
	public void mostrarMensajeError() {
        JOptionPane.showMessageDialog(null, "Tu nombre debe tener al menos 3 letras.", "Nombre no válido",
                JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void mostrarMensajeReglas() {
		JOptionPane.showMessageDialog(null,
                "Suma 1 + 2 para comenzar.\nLuego... combina múltiplos de 3!",
                "Reglas del juego", JOptionPane.INFORMATION_MESSAGE);	
	}
}