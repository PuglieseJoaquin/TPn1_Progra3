package gui;

import java.awt.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import presenter.RankingPresenter;

public class PantallaRanking extends JFrame {
	
    private JPanel panelFondo;
    private JPanel panelBoton;
    private JButton btnVolverAlMenu;
    private RankingPresenter rankingPresenter;

    public PantallaRanking(GestorPantallas gestorPantallas) {
    	
    		rankingPresenter = new RankingPresenter(gestorPantallas);
        configurarPantalla(); 
        crearLblTitulo();
                
        crearTabla();
        crearBtnVolverAlMenu();
        
    }

	private void crearBtnVolverAlMenu() {
		btnVolverAlMenu = new JButton("Volver al menú");
        btnVolverAlMenu.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnVolverAlMenu.setForeground(new Color(241, 245, 249));
        btnVolverAlMenu.setBackground(new Color(51, 65, 85));
        
        btnVolverAlMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	rankingPresenter.manejarClickVolverAlMenu();
            }
        });
        
        panelBoton = new JPanel();
        panelBoton.setBackground(new Color(30, 41, 59));
        panelBoton.add(btnVolverAlMenu);
        panelFondo.add(panelBoton, BorderLayout.SOUTH);
        setVisible(true);
	}

	private void crearTabla() {
        List<Object[]> filas = rankingPresenter.getFilasRanking();
        DefaultTableModel modeloDeTabla = new DefaultTableModel(
            new String[]{"Puesto", "Jugador", "Puntaje", "Ficha Máxima", "Nivel"}, 0
        );

        for (Object[] fila : filas) {
        		modeloDeTabla.addRow(fila);
        }

        JTable tabla = new JTable(modeloDeTabla);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

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