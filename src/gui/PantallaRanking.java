package gui;

import java.awt.*;
import java.util.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import logica.Juego;
import logica.Partida;
import javax.swing.border.MatteBorder;

public class PantallaRanking extends JFrame{
	public PantallaRanking() {
        setTitle("Ranking - Threes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panelFondo = new JPanel();
        panelFondo.setBackground(new Color(60, 179, 113));
        panelFondo.setLayout(new BorderLayout(10, 10));
        setContentPane(panelFondo);
		
		//titulo
        JLabel lblNewLabel = new JLabel("RANKING",SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 30));
        lblNewLabel.setForeground(new Color(255, 215, 0));
        panelFondo.add(lblNewLabel, BorderLayout.NORTH);
        setVisible(true);
        
		//boton volver al inicio
        JButton btnVolver = new JButton("Volver al menu");
        btnVolver.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnVolver.addActionListener(e -> {
            PantallaInicial inicio = new PantallaInicial();
            inicio.setVisible(true);
            dispose();
        });
        
        ArrayList<Partida> top5Puntajes = Juego.getTop5Puntajes();
        
        //tabla
        String[] columnas = {"Puesto", "Nombre", "Puntaje", "Ficha Mayor", "Nivel"};
        DefaultTableModel modeloDeTabla = new DefaultTableModel(columnas, 0);

        for (int i = 0; i < top5Puntajes.size(); i++) {
        	Partida p = top5Puntajes.get(i);
        	modeloDeTabla.addRow(new Object[]{i + 1, p.getNombreJugador(), p.getPuntaje(), p.getValorFichaMaximo(), p.getNivel()});
        }
            
        JTable tabla = new JTable(modeloDeTabla);
        tabla.setShowVerticalLines(false);
        tabla.setBackground(new Color(245, 222, 179));
        tabla.setFont(new Font("Tahoma", Font.BOLD, 16));
        tabla.setRowHeight(40);
        tabla.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 16));
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < tabla.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setViewportBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        panelFondo.add(scroll, BorderLayout.CENTER);
               
        JPanel panelBoton = new JPanel();
        panelBoton.setBackground(new Color(60, 179, 113));
        panelBoton.add(btnVolver);
        panelFondo.add(panelBoton, BorderLayout.SOUTH);
	}
}
	
	
