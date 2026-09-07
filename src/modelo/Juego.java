package modelo;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Juego {
    private Tablero tablero;
    private int puntaje;
    private boolean gameOver;
    private Partida partidaActual;
    private String nombreJugador;
    private String nivel;
    private int valorFichaMaximo;
    private int tamanioMatriz;
    
    private static ArrayList<Partida> partidas4x4 = new ArrayList<>();
    private static ArrayList<Partida> partidas5x5 = new ArrayList<>();
    private static ArrayList<Partida> partidas6x6 = new ArrayList<>();
    

    public Juego(int tamanioMatriz, String nombreJugador, String nivel) {
        this.tablero = new Tablero(tamanioMatriz);
        this.puntaje = 0;
        this.gameOver = false;
        this.nombreJugador = nombreJugador;
        this.nivel = nivel;
        this.partidaActual = new Partida(nombreJugador, puntaje, valorFichaMaximo, nivel);
         
        this.valorFichaMaximo = 0;
        this.tamanioMatriz = tamanioMatriz;

        registrarPartida(partidaActual);
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
    

    public static ArrayList<Partida> getTop5Puntajes(String nivel) {
        ArrayList<Partida> partidasDelNivel = getPartidasPorNivel(nivel);
        return obtenerMejores(partidasDelNivel, 5);
    }
    
    private static ArrayList<Partida> getPartidasPorNivel(String nivel) {
    	if(nivel.equals("Clásico 4x4")) 
    		return partidas4x4;
    	else if (nivel.equals("Extra 5x5")) {
    		return partidas5x5;
		}
    	else if (nivel.equals("Supremo 6x6")) {
    		return partidas6x6;
		}
    	return new ArrayList<>();
    }
       
      
    private static ArrayList<Partida> obtenerMejores(ArrayList<Partida> lista, int cantidad) {
        ArrayList<Partida> partidasAElegir = new ArrayList<>(lista);
        ArrayList<Partida> rankingFinal = new ArrayList<>();

        int cantidadAMostrar = Math.min(partidasAElegir.size(), cantidad);

        for (int i = 0; i < cantidadAMostrar; i++) {
            Partida mejor = encontrarMejor(partidasAElegir);
            rankingFinal.add(mejor);
            partidasAElegir.remove(mejor);
        }

        return rankingFinal;
    }

    private static Partida encontrarMejor(ArrayList<Partida> lista) {
        Partida mejor = lista.get(0);
        for (Partida p : lista) {
            if (p.getPuntaje() > mejor.getPuntaje()) {
                mejor = p;
            }
        }
        return mejor;
    }
    
    private void registrarPartida(Partida partida) {
    	 String nivel = partida.getNivel();
    	    if (nivel.equals("Clásico 4x4")) {
    	        partidas4x4.add(partida);
    	        
    	    } else if (nivel.equals("Extra 5x5")) {
    	        partidas5x5.add(partida);
    	    
    	    } else if (nivel.equals("Supremo 6x6")) {
    	        partidas6x6.add(partida);
    	    }
    }
}