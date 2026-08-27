package logica;

import java.awt.event.KeyEvent;

public class Juego {
    private Tablero tablero;
    private int puntaje;
    private boolean gameOver;

    public Juego(int tamañoMatriz) {
        this.tablero = new Tablero(tamañoMatriz);
        this.puntaje = 0;
        this.gameOver = false;
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
        	System.out.println("PERDISTE");
            gameOver = true;
        }
    }

    public Tablero getTablero() {
        return tablero;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public boolean isGameOver() {
        return gameOver;
    }
}