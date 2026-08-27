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
        	
            gameOver = true;
        }
    }

    public Tablero getTablero() {
        return tablero;
    }

    public int getPuntaje() {
    	int cont = 0;
    	Tablero t=this.tablero;
	    for (int fila = 0; fila < t.getTamaño(); fila++) {
	        for (int col = 0; col < t.getTamaño(); col++) {
	            Ficha f = t.getFicha(fila, col);
	            if (f != null) {
	            	cont += puntajeDeFicha(f.getValor());
	            }
	        }
	    }
	    return cont;
	}
    

    private int puntajeDeFicha(int valor) {
        if (valor == 1 || valor == 2) {
            return 0;
        }
        
        int puntajeAcumulado = 3;
        int fichaSimulada = 3;
        
        while (fichaSimulada < valor) {
            fichaSimulada = fichaSimulada * 2;
            puntajeAcumulado = puntajeAcumulado * 3;
        }
        return puntajeAcumulado;
    }

	public boolean isGameOver() {
        return gameOver;
    }
}