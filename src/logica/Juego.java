package logica;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Juego {
    private Tablero tablero;
    private int puntaje;
    private boolean gameOver;
    private static Map<String, Usuario> usuarios = new HashMap<>();
    private Partida partidaActual;
    private Usuario jugadorActual;
    private String nombreJugador;
    private String nivel;
    private int valorFichaMaximo;

    public Juego(int tamanioMatriz, String nombreJugador, String nivel) {
        this.tablero = new Tablero(tamanioMatriz);
        this.puntaje = 0;
        this.gameOver = false;
        this.nombreJugador = nombreJugador;
        this.nivel = nivel;
        this.partidaActual = new Partida(nombreJugador, puntaje, valorFichaMaximo, nivel);
        this.jugadorActual = crearUsuario();  
        this.valorFichaMaximo = 0;

    }
    
    private Usuario crearUsuario() {
    	Usuario usuarioActual = usuarios.get(nombreJugador);
    	
    	if (usuarioActual == null) {
    		usuarioActual = new Usuario(nombreJugador);
    		usuarios.put(nombreJugador, usuarioActual);
    	}

    	usuarioActual.agregarPartida(partidaActual);
    	return usuarioActual;
    }
    
    public Usuario getJugadorActual() {
    	return this.jugadorActual;
    }
    
    public int getTamanioArrayUsuario() {
    	return usuarios.size();
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
            
        }
    }

    public Tablero getTablero() {
        return tablero;
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
    
    public static ArrayList<Partida> getTop5Puntajes() {
        ArrayList<Partida> todasLasPartidas = new ArrayList<>();

        for (Usuario u : usuarios.values()) {
        	todasLasPartidas.addAll(u.getPartidas());
        }

        ArrayList<Partida> top5 = obtenerMejores(todasLasPartidas, 5);
        return top5;
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
}