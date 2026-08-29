package gui;

import java.awt.*;
import javax.swing.*;

public class PantallaPerdiste extends JFrame {
    public PantallaPerdiste(String nombre, int puntajePartida, int tamañoMatriz, int dimensionVentana, String nivel) {
        
        setTitle("Threes! — Game Over");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(30, 41, 59)); 
        setContentPane(panel);
        panel.setLayout(null);
        
        // Título GAME OVER 
        JLabel Titulo = new JLabel("GAME OVER", SwingConstants.CENTER);
        Titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        Titulo.setForeground(new Color(239, 68, 68));
        Titulo.setBounds(50, 35, 500, 35);
        panel.add(Titulo);

        // Información de usuario y puntaje
        JLabel Nombre = new JLabel("Jugador: " + nombre, SwingConstants.CENTER);
        Nombre.setFont(new Font("Segoe UI", Font.BOLD, 15));
        Nombre.setForeground(new Color(203, 213, 225));
        Nombre.setBounds(50, 95, 500, 25);
        panel.add(Nombre);

        JLabel Puntaje = new JLabel("Puntaje Final: " + puntajePartida, SwingConstants.CENTER);
        Puntaje.setFont(new Font("Segoe UI", Font.BOLD, 15));
        Puntaje.setForeground(new Color(203, 213, 225));
        Puntaje.setBounds(50, 130, 500, 25);
        panel.add(Puntaje);
        
        // Botón Jugar de Nuevo 
        JButton btnJugarDeNuevo = new JButton("Jugar de nuevo");
        btnJugarDeNuevo.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnJugarDeNuevo.setForeground(Color.WHITE);
        btnJugarDeNuevo.setBackground(new Color(16, 185, 129));
        btnJugarDeNuevo.setBounds(160, 205, 280, 42);

        btnJugarDeNuevo.addActionListener(e -> {
            logica.ReproductorMusica.reproducirLoop("/audio/backSound.wav");
            
            PantallaJuego nuevaPartida = new PantallaJuego(nombre, tamañoMatriz, dimensionVentana, nivel);
            nuevaPartida.setResizable(false);
            nuevaPartida.setVisible(true);
            dispose();
        });
        panel.add(btnJugarDeNuevo);

        // Botón Volver al Menú
        JButton btnVolverMenu = new JButton("Volver al menú");
        btnVolverMenu.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnVolverMenu.setForeground(new Color(241, 245, 249));
        btnVolverMenu.setBackground(new Color(51, 65, 85));
        btnVolverMenu.setBounds(160, 260, 280, 38);
        
        btnVolverMenu.addActionListener(e -> {
            PantallaInicial inicio = new PantallaInicial();
            inicio.setVisible(true);
            dispose();
        });
        panel.add(btnVolverMenu);

        setVisible(true);
    }
}
