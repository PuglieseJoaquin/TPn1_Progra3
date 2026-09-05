package gui;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import logica.Juego;
import logica.Partida;

public class PantallaRanking extends JFrame {
	
    private JPanel panelFondo;
    private JPanel panelBoton;
    private JButton btnVolverAlMenu;

    public PantallaRanking() {
        configurarPantalla(); 
        crearLblTitulo();
        
        ArrayList<Partida> top5Puntajes = Juego.getTop5Puntajes(); //Aca la estructura de datos vive en la VIEW, hay que moverlo a la logica
        
        crearTabla(top5Puntajes);
        crearBtnVolverAlMenu();
        
        
    }

	private void crearBtnVolverAlMenu() {
		btnVolverAlMenu = new JButton("Volver al menú");
        btnVolverAlMenu.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnVolverAlMenu.setForeground(new Color(241, 245, 249));
        btnVolverAlMenu.setBackground(new Color(51, 65, 85));

        btnVolverAlMenu.addActionListener(e -> {
            GestorPantallas.clickBtnMenu();
        });
        
        panelBoton = new JPanel();
        panelBoton.setBackground(new Color(30, 41, 59));
        panelBoton.add(btnVolverAlMenu);
        panelFondo.add(panelBoton, BorderLayout.SOUTH);
        setVisible(true);
	}

	private void crearTabla(ArrayList<Partida> top5Puntajes) {
        String[] columnas = {"Puesto", "Nombre", "Puntaje", "Ficha Mayor", "Nivel"};
        DefaultTableModel modeloDeTabla = new DefaultTableModel(columnas, 0);

        for (int i = 0; i < top5Puntajes.size(); i++) {
            Partida p = top5Puntajes.get(i);
            modeloDeTabla.addRow(new Object[]{i + 1, p.getNombreJugador(), p.getPuntaje(), p.getValorFichaMaximo(), p.getNivel()});
        }
            
        JTable tabla = new JTable(modeloDeTabla);
        tabla.setShowVerticalLines(false);
        tabla.setGridColor(new Color(51, 65, 85));
        tabla.setBackground(new Color(15, 23, 42));
        tabla.setForeground(new Color(241, 245, 249));
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tabla.setRowHeight(32);
        
        // Encabezado de la tabla
        tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tabla.getTableHeader().setBackground(new Color(51, 65, 85));
        tabla.getTableHeader().setForeground(new Color(248, 250, 252));
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        centerRenderer.setBackground(new Color(15, 23, 42));
        centerRenderer.setForeground(new Color(241, 245, 249));

        for (int i = 0; i < tabla.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.getViewport().setBackground(new Color(15, 23, 42));
        scroll.setBorder(BorderFactory.createLineBorder(new Color(51, 65, 85)));
        panelFondo.add(scroll, BorderLayout.CENTER);
	}

	private void crearLblTitulo() {
		JLabel lblRanking = new JLabel("RANKING", SwingConstants.CENTER);
        lblRanking.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblRanking.setForeground(new Color(248, 250, 252));
        panelFondo.add(lblRanking, BorderLayout.NORTH);
	}

	private void configurarPantalla() {
		setTitle("Threes! — Ranking");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        setLocationRelativeTo(null);
        setResizable(false);

        panelFondo = new JPanel();
        panelFondo.setBackground(new Color(30, 41, 59)); 
        panelFondo.setLayout(new BorderLayout(10, 10));
        panelFondo.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(panelFondo);
	}
}