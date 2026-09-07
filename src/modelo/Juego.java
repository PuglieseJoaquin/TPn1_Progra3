package modelo;

import java.awt.event.KeyEvent;


public class Juego {
    private Tablero tablero;
    private int puntaje;
    private boolean gameOver;
    private Partida partidaActual;
    private int valorFichaMaximo;
    private int tamanioMatriz;
    
   
    

    public Juego(int tamanioMatriz, String nombreJugador, String nivel) {
        this.tablero = new Tablero(tamanioMatriz);
        this.puntaje = 0;
        this.gameOver = false;
        this.partidaActual = new Partida(nombreJugador, puntaje, valorFichaMaximo, nivel);
        this.valorFichaMaximo = 0;
        this.tamanioMatriz = tamanioMatriz;
    }
    
    
	public boolean isGameOver() {
        return gameOver;
    }
	
    public void mover(int codigoTecla) {
    	
        if (!gameOver && (!tablero.estaCompleto() || tablero.hayMovimientosPosibles())) {
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
            puntaje = calcularPuntaje();
            Ranking.registrarPartida(partidaActual);
            ReproductorMusica.detener();
        }
    }

    public Tablero getTablero() {
        return tablero;
    }
    
    public int[][] getMatrizValoresDeFichas() {  	
    		
    		int[][] valores = new int[tamanioMatriz][tamanioMatriz];
    		
    		for (int fila = 0; fila < tamanioMatriz; fila++) {
    			for (int col = 0; col < tamanioMatriz; col++) {
    				Ficha ficha = tablero.getFicha(fila, col);
    				valores[fila][col] = (ficha == null) ? 0 : ficha.getValor();
    			}
    		}
    		return valores;
    }
    
    public int getProximoValorFicha() {
    		return tablero.getProximoValorFicha();
    }
    
    public int getPuntaje() {
    	return puntaje;
    }

    public int calcularPuntaje() {
    	puntaje = tablero.calcularPuntaje();
    	partidaActual.setPuntaje(puntaje);
    	partidaActual.setValorFichaMaximo(tablero.getValorMaximo());
    	return this.puntaje;
    }
    
    public String getMovimientoSugerido() {
    	return tablero.movimientoSugerido();
    }
    

   
}