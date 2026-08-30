package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

import logica.Ficha;
import logica.Juego;
import logica.Tablero;

public class PantallaJuego extends JFrame {

    private JPanel panelFondo;
    private JPanel panelJuego;
    private JLabel[] cuadrados;
    private Juego juego;
    private JLabel lblProximaFichaValor;
    private JLabel lblMovimientoSugerido;

    public PantallaJuego(String nombreJugador, int tamanioMatriz, int dimensionVentana, String nivel) {
        
        setTitle("Threes! — Partida (" + nivel + ")");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 650, 420);
        setLocationRelativeTo(null);
        setResizable(false);
        
        panelFondo = new JPanel();
        setContentPane(panelFondo);
        panelFondo.setLayout(new BorderLayout(0, 0));
        
        // Habilitamos al panel para recibir el foco del teclado
        panelFondo.setFocusable(true);
        panelFondo.requestFocusInWindow();
        
        // Panel lateral derecho 
        panelJuego = new JPanel();
        panelJuego.setPreferredSize(new Dimension(210, 0));
        panelJuego.setBackground(new Color(30, 41, 59));
        panelFondo.add(panelJuego, BorderLayout.EAST);
        panelJuego.setLayout(null);
        
        // Inicio lógica
        this.juego = new Juego(tamanioMatriz, nombreJugador, nivel);
        
        JLabel lblSuerte = new JLabel("¡A Jugar!");
        lblSuerte.setForeground(new Color(248, 250, 252));
        lblSuerte.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblSuerte.setBounds(15, 12, 180, 25);
        panelJuego.add(lblSuerte);
        
        // Nombre de usuario 
        JLabel lblNombreUsuario = new JLabel(nombreJugador);
        lblNombreUsuario.setForeground(new Color(16, 185, 129)); // Verde 
        lblNombreUsuario.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNombreUsuario.setBackground(new Color(15, 23, 42));
        lblNombreUsuario.setOpaque(true);
        lblNombreUsuario.setHorizontalAlignment(SwingConstants.CENTER);
        lblNombreUsuario.setVerticalAlignment(SwingConstants.CENTER);
        lblNombreUsuario.setBounds(15, 42, 180, 32);
        lblNombreUsuario.setBorder(BorderFactory.createLineBorder(new Color(51, 65, 85)));
        panelJuego.add(lblNombreUsuario);
        
        // Aviso de proxima ficha 
        JLabel lblProximaFicha = new JLabel("Próxima Ficha:");
        lblProximaFicha.setForeground(new Color(203, 213, 225));
        lblProximaFicha.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblProximaFicha.setBounds(15, 90, 180, 20);
        panelJuego.add(lblProximaFicha);
        
        // 
        lblProximaFichaValor = new JLabel();
        lblProximaFichaValor.setForeground(new Color(15, 23, 42));
        lblProximaFichaValor.setHorizontalAlignment(SwingConstants.CENTER);
        lblProximaFichaValor.setVerticalAlignment(SwingConstants.CENTER);
        lblProximaFichaValor.setOpaque(true);
        lblProximaFichaValor.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblProximaFichaValor.setBounds(15, 115, 180, 38);
        lblProximaFichaValor.setBorder(BorderFactory.createLineBorder(new Color(51, 65, 85)));
        panelJuego.add(lblProximaFichaValor);
        
        JLabel lblJugadaSugerida = new JLabel("Jugada Sugerida:");
        lblJugadaSugerida.setForeground(new Color(203, 213, 225));
        lblJugadaSugerida.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblJugadaSugerida.setBounds(15, 170, 180, 20);
        panelJuego.add(lblJugadaSugerida);
        
        // Valor dinamico del movimiento sugerido
        lblMovimientoSugerido = new JLabel();
        lblMovimientoSugerido.setForeground(new Color(241, 245, 249));
        lblMovimientoSugerido.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblMovimientoSugerido.setHorizontalAlignment(SwingConstants.CENTER);
        lblMovimientoSugerido.setVerticalAlignment(SwingConstants.CENTER);
        lblMovimientoSugerido.setBackground(new Color(15, 23, 42));
        lblMovimientoSugerido.setText(juego.getMovimientoSugerido());
        lblMovimientoSugerido.setBounds(15, 195, 180, 38);
        panelJuego.add(lblMovimientoSugerido);
        
        // Mensaje consejo
        JLabel lblLinea1 = new JLabel("Usa las flechas");
        lblLinea1.setForeground(new Color(148, 163, 184));
        lblLinea1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblLinea1.setHorizontalAlignment(SwingConstants.CENTER);
        lblLinea1.setBounds(15, 250, 180, 20);
        panelJuego.add(lblLinea1);

        JLabel lblLinea2 = new JLabel("para deslizar");
        lblLinea2.setForeground(new Color(148, 163, 184));
        lblLinea2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblLinea2.setHorizontalAlignment(SwingConstants.CENTER);
        lblLinea2.setBounds(15, 270, 180, 20);
        panelJuego.add(lblLinea2);
        
        // Panel central de la matriz de juego
        JPanel panelMatriz = new JPanel();
        panelFondo.add(panelMatriz, BorderLayout.CENTER);
        panelMatriz.setLayout(new GridLayout(tamanioMatriz, tamanioMatriz, 6, 6));
        panelMatriz.setBackground(new Color(15, 23, 42)); // Fondo oscuro exterior de la matriz
        panelMatriz.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        panelFondo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int evento = e.getKeyCode();
                juego.mover(evento);
                actualizarVista();
                
                if (juego.isGameOver()) {
                    logica.ReproductorMusica.detener(); 
                    int puntajePartida = juego.getPuntaje();
                    new PantallaPerdiste(nombreJugador, puntajePartida, tamanioMatriz, dimensionVentana, nivel);
                    dispose();
                }
            }
        });

        // Agrego los cuadrados
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
      
        actualizarVista();
    }

    private void actualizarVista() {
        Tablero tablero = juego.getTablero();
        int tamanio = tablero.getTamanio();
        
        for (int fila = 0; fila < tamanio; fila++) {
            for (int col = 0; col < tamanio; col++) {
                Ficha f = tablero.getFicha(fila, col);
                int index = fila * tamanio + col;

                if (f == null) {
                    cuadrados[index].setText("");
                    cuadrados[index].setBackground(new Color(241, 245, 249)); // Casilla vacía limpia
                } else {
                    int valor = f.getValor();
                    cuadrados[index].setText(String.valueOf(valor));
                    cuadrados[index].setBackground(colorParaValor(valor));
                    
                    // Ajuste dinámico de color (color de letra y fondo)
                    if (valor == 1) {
                        cuadrados[index].setForeground(new Color(37, 99, 235));
                    } else if (valor == 2) {
                        cuadrados[index].setForeground(new Color(225, 29, 72));
                    } else {
                        cuadrados[index].setForeground(Color.WHITE);
                    }
                }
            }
        }
        
        int proximoValor = juego.getTablero().getProximoValorFicha();
        lblProximaFichaValor.setText(String.valueOf(proximoValor));
        lblProximaFichaValor.setBackground(colorParaValor(proximoValor));
        // Ficha 1 y 2 usa fondo mas oscuro (las diferencio ya que son las fichas iniciales)
        if (proximoValor == 1) {
            lblProximaFichaValor.setForeground(new Color(37, 99, 235));
        } else if (proximoValor == 2) {
            lblProximaFichaValor.setForeground(new Color(225, 29, 72));
        } else {
            lblProximaFichaValor.setForeground(Color.WHITE);
        }
        
        lblMovimientoSugerido.setText(juego.getMovimientoSugerido());
    }
    
    private Color colorParaValor(int valor) {
        switch (valor) {
            case 1:  return new Color(224, 242, 254); // Azul muy claro para ficha 1
            case 2:  return new Color(253, 232, 232); // Rojo muy claro para ficha 2
            case 3:  return new Color(59, 130, 246);  // Azul 
            case 6:  return new Color(239, 68, 68);   // Rojo 
            case 12: return new Color(16, 185, 129);  // Esmeralda
            case 24: return new Color(245, 158, 11);  // Naranja
            case 48: return new Color(139, 92, 246);  // Violeta
            case 96: return new Color(236, 72, 153);  // Rosa
            case 192:return new Color(14, 165, 233);  // Celeste 
            case 384:return new Color(202, 138, 4);   // Dorado 
            case 768:return new Color(217, 70, 239);  // Fucsia
            case 1536:return new Color(79, 70, 229);  // Índigo 
            default: return new Color(100, 116, 139); // Gris por defecto
        }
    }
}