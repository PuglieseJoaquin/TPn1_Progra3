package logica;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;


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
    
    public static ArrayList<Usuario> getUsuarios() {   
        return usuarios;
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
    	jugadorActual.setValorMaximo(tablero.getValorMaximo());
    	return this.puntaje;
    }
    
    public String getMovimientoSugerido() {
    	return tablero.movimientoSugerido();
    }
    
    public static ArrayList<Usuario> getTop5() {
    	ArrayList<Usuario> sinRepetidos = quedarseConElMejorPorNombre();
        return obtenerMejores(sinRepetidos, 5);
    }

    private static ArrayList<Usuario> quedarseConElMejorPorNombre() {
        HashMap<String, Usuario> mejoresPorNombre = new HashMap<>();
        
        for (Usuario u : usuarios) {
            
        	Usuario JugadorExistente = mejoresPorNombre.get(u.getNombre());
            
            if (JugadorExistente == null || u.getPuntaje() > JugadorExistente.getPuntaje()) {
                mejoresPorNombre.put(u.getNombre(), u);
            }
        }
        return new ArrayList<>(mejoresPorNombre.values());
    }
    
    
    private static ArrayList<Usuario> obtenerMejores(ArrayList<Usuario> lista, int cantidad) {
        ArrayList<Usuario> JugadoresAElegir = new ArrayList<>(lista);
        ArrayList<Usuario> RankingFinal = new ArrayList<>();

        int cantidadAMostrar;
        
        if (JugadoresAElegir.size() < cantidad) {
            cantidadAMostrar = JugadoresAElegir.size();
        } else {
            cantidadAMostrar = cantidad;
        }

        for (int i = 0; i < cantidadAMostrar; i++) {
            Usuario mejor = encontrarMejor(JugadoresAElegir);
            RankingFinal.add(mejor);
            JugadoresAElegir.remove(mejor);
        }

        return RankingFinal;
    }

    private static Usuario encontrarMejor(ArrayList<Usuario> lista) {
        Usuario mejor = lista.get(0);
        for (Usuario u : lista) {
            if (u.getPuntaje() > mejor.getPuntaje()) {
                mejor = u;
            }
        }
        return mejor;
    }
}