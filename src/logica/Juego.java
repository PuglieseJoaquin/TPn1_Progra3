package logica;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Juego {
    private Tablero tablero;
    private int puntaje;
    private boolean gameOver;
    private static ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
    private Usuario jugadorActual;
    private String nombreJugador;

    public Juego(int tamañoMatriz, String nombreJugador) {
        this.tablero = new Tablero(tamañoMatriz);
        this.puntaje = 0;
        this.gameOver = false;
        this.nombreJugador = nombreJugador;
        this.jugadorActual = crearUsuario();
    }
    
    private Usuario crearUsuario() {
    	Usuario usuarioActual = new Usuario(nombreJugador);
    	usuarios.add(usuarioActual);
    	return usuarioActual;
    }
    
    public Usuario getJugadorActual() {
    	return this.jugadorActual;
    }
    
    //print de prueba
    public String getPuntajes() {
        StringBuilder sb = new StringBuilder();
        for (Usuario u : usuarios) {
            sb.append(u.getNombre())
              .append(" - ")
              .append(u.getPuntaje())
              .append("\n");
        }
        return sb.toString();
    }
    
    public int getTamañoArrayUsuario() {
    	return usuarios.size();
    }
    
	public boolean isGameOver() {
        return gameOver;
    }
	
    public void mover(int codigoTecla) {
    	
        if (!gameOver && (!tablero.estaCompleto() || tablero.hayMovimientosPosibles())) {
        	System.out.println(tablero.getContadorFichas());
        	System.out.println(tablero.hayMovimientosPosibles());
            switch (codigoTecla) {
                case KeyEvent.VK_RIGHT:
                    tablero.moverDerecha();
                    break;
                case KeyEvent.VK_LEFT:
                    tablero.moverIzquierda();
                    break;
                case KeyEvent.VK_UP:
                    tablero.moverArriba();
                    break;
                case KeyEvent.VK_DOWN:
                    tablero.moverAbajo();
                    break;
            }
        } else {
            gameOver = true;
        }
    }

    public Tablero getTablero() {
        return tablero;
    }

    public int getPuntaje() {
    	puntaje = tablero.calcularPuntaje();
    	jugadorActual.setPuntaje(puntaje);
    	return this.puntaje;
    }
    
    public String getMovimientoSugerido() {
    	return tablero.movimientoSugerido();
    }
}