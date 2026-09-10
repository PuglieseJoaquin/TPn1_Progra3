package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import presentador.BienvenidaPresentador;

public class PantallaBienvenida extends JFrame {
	
	private BienvenidaPresentador bienvenidaPresentador;
    private JPanel panelFondo;
	private JButton btnMenu;
	private JButton btnSalir;
	private JLabel lblFooter;
	private JLabel lblImagen;

    public PantallaBienvenida(GestorPantallas gestorPantallas) {
    		bienvenidaPresentador = new BienvenidaPresentador(gestorPantallas);
    	
        configurarPantalla();
        crearBtnMenu();
        crearBtnSalir();
        crearFooter();
    }
    
    
    private void configurarPantalla() {
		setTitle("Threes! — Bienvenida");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400); 
        setLocationRelativeTo(null);
        setResizable(false);

        panelFondo = new JPanel();
        panelFondo.setBackground(Color.WHITE);
        setContentPane(panelFondo);
        panelFondo.setLayout(null);

        lblImagen = new JLabel();
        lblImagen.setHorizontalAlignment(SwingConstants.CENTER);

        ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/imagenes/threes.jpg"));
        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(600, 292, Image.SCALE_SMOOTH);
        lblImagen.setIcon(new ImageIcon(imagenEscalada));
        lblImagen.setBounds(0, 10, 600, 172);
        panelFondo.add(lblImagen);
	}
    
    
    private void crearBtnMenu() {
    		btnMenu = new JButton("MENU");
        btnMenu.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnMenu.setForeground(Color.WHITE);
        btnMenu.setBackground(new Color(37, 99, 235));
        btnMenu.setFocusPainted(false);
        btnMenu.setBounds(160, 200, 280, 40);
        btnMenu.setOpaque(true);
        btnMenu.setBorderPainted(false);
        
        agregarListenerBtnMenu();
	}

    
	private void agregarListenerBtnMenu() {
        btnMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            		bienvenidaPresentador.manejarClickBotonMenu();
            }
        });
        panelFondo.add(btnMenu);
	}

	
	private void crearBtnSalir() {
		btnSalir = new JButton("SALIR");
        btnSalir.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnSalir.setForeground(new Color(100, 100, 100));
        btnSalir.setBackground(new Color(220, 220, 220));
        btnSalir.setFocusPainted(false);
        btnSalir.setBounds(225, 250, 150, 35);
        
        agregarListenerBtnSalir();
	}

	
	private void agregarListenerBtnSalir() {
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bienvenidaPresentador.manejarClickBotonSalir();
            }
        });
        panelFondo.add(btnSalir);		
	}

	
	private void crearFooter() {
		lblFooter = new JLabel("Combina los tres • Desliza para combinar • Suerte!", SwingConstants.CENTER);
        lblFooter.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblFooter.setForeground(Color.GRAY);
        lblFooter.setBounds(100, 320, 400, 20);
        panelFondo.add(lblFooter);
	}
}