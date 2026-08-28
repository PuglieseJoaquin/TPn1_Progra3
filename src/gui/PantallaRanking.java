package gui;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import logica.Juego;
import logica.Usuario;

import javax.swing.JTextField;
import java.awt.Cursor;
import javax.swing.JComboBox;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JTable;

public class PantallaRanking extends JFrame{
	public PantallaRanking() {
        setTitle("Ranking - Threes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 400);
        setLocationRelativeTo(null);

        JPanel panelFondo = new JPanel();
        panelFondo.setBackground(new Color(60, 179, 113));
        panelFondo.setLayout(new BorderLayout(10, 10));
        setContentPane(panelFondo);
		
		//titulo
        JLabel lblNewLabel = new JLabel("RANKING",SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 24));
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
        
        //obtengo juegadores
        ArrayList<Usuario> top5 = Juego.getTop5();
        
        
        //tabla
        String[] columnas = {"Puesto", "Nombre", "Puntaje", "Logro", "Nivel"};
        DefaultTableModel modeloDeTabla = new DefaultTableModel(columnas, 0);

        for (int i = 0; i < top5.size(); i++) {
            Usuario u = top5.get(i);
            modeloDeTabla.addRow(new Object[]{i + 1, u.getNombre(), u.getPuntaje(), u.getValorMaximo(), u.getNivel()});
        }
        
        
        JTable tabla = new JTable(modeloDeTabla);
        tabla.setFont(new Font("Tahoma", Font.PLAIN, 16));
        tabla.setRowHeight(30);
        tabla.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 16));

        JScrollPane scroll = new JScrollPane(tabla);
        panelFondo.add(scroll, BorderLayout.CENTER);
        
        
        JPanel panelBoton = new JPanel();
        panelBoton.setBackground(new Color(60, 179, 113));
        panelBoton.add(btnVolver);
        panelFondo.add(panelBoton, BorderLayout.SOUTH);
        
        
	}
	
}
	
	
