package gui;

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

public class PantallaBienvenida extends JFrame {

    private JPanel panelFondo;

    public PantallaBienvenida() {
        setTitle("Threes! — Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400); 
        setLocationRelativeTo(null);
        setResizable(false);

        panelFondo = new JPanel();
        panelFondo.setBackground(Color.WHITE);
        setContentPane(panelFondo);
        panelFondo.setLayout(null);

        // Buscamos archivo y adaptamos la imagen 
        JLabel lblImagen = new JLabel();
        lblImagen.setHorizontalAlignment(SwingConstants.CENTER);

        ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/imagenes/threes.jpg"));
        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(600, 292, Image.SCALE_SMOOTH); // Evitamos que salga pixeleado
        lblImagen.setIcon(new ImageIcon(imagenEscalada)); // Asignamos imagen a jlabel
        lblImagen.setBounds(0, 10, 600, 172);
        panelFondo.add(lblImagen);

        // Botón Jugar
        JButton btnJugar = new JButton("JUGAR");
        btnJugar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnJugar.setForeground(Color.WHITE);
        btnJugar.setBackground(new Color(37, 99, 235)); // Azul 
        btnJugar.setFocusPainted(false);
        btnJugar.setBounds(160, 200, 280, 40);
        btnJugar.setOpaque(true);
        btnJugar.setBorderPainted(false);
        
        btnJugar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PantallaInicial registro = new PantallaInicial(); 
                registro.setVisible(true);
                dispose();
            }
        });
        panelFondo.add(btnJugar);

        // Botón Salir
        JButton btnSalir = new JButton("SALIR");
        btnSalir.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnSalir.setForeground(new Color(100, 100, 100));
        btnSalir.setBackground(new Color(220, 220, 220));
        btnSalir.setFocusPainted(false);
        btnSalir.setBounds(225, 250, 150, 35);
        
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        panelFondo.add(btnSalir);

        // Pie de página
        JLabel lblFooter = new JLabel("Combina los tres • Desliza para combinar • Suerte!", SwingConstants.CENTER);
        lblFooter.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblFooter.setForeground(Color.GRAY);
        lblFooter.setBounds(100, 320, 400, 20);
        panelFondo.add(lblFooter);
    }
}