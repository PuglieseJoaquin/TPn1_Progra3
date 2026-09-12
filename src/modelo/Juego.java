package modelo;

public class Juego {
	
    private Tablero tablero;
    private int puntaje;
    private boolean gameOver;
    private Partida partidaActual;
    private int valorFichaMaximo;  
    
    public Juego(int tamanioMatriz, String nombreJugador, String nivel) {	
        this.tablero = new Tablero(tamanioMatriz);
        this.puntaje = 0;
        this.gameOver = false;
        this.partidaActual = new Partida(nombreJugador, puntaje, valorFichaMaximo, nivel);
        this.valorFichaMaximo = 0;
    }
     
	public boolean isGameOver() {
        return gameOver;
    }
		
    public void mover(Direccion direccion) {
    	
        if (esPartidaAunValida()) tablero.mover(direccion);
        else configurarPartidaTerminada();
    }    
        
    public int getProximoValorFicha() {
    		return tablero.getProximoValorFicha();
    }   
    
    public int getPuntaje() {
    		return puntaje;
    }
    
    public int calcularPuntaje() {
	    	puntaje = calcularPuntajeTableroActual();
	    	partidaActual.setPuntaje(puntaje);
	    	partidaActual.setValorFichaMaximo(valorFichaMaximo);
	    	return this.puntaje;
    }
       
    private int calcularPuntajeTableroActual() {
	    int cont = 0;
	    int valorMaxFicha = 0;

	    for (int fila = 0; fila < tablero.getTamanio(); fila++) {
	        for (int col = 0; col < tablero.getTamanio(); col++) {
	            Ficha f = tablero.getFicha(fila, col);
	            if (f != null && f.getValor() != 1 && f.getValor() != 2) {
	                cont += puntajeDeFicha(f.getValor());

	                if (f.getValor() > valorMaxFicha) 
	                	valorMaxFicha = f.getValor();
	            }
	        }
	    }
	    this.valorFichaMaximo = valorMaxFicha;
	    return cont;
	}
    
	private int puntajeDeFicha(int valor) {     
        int puntajeAcumulado = 3;
        int fichaSimulada = 3;
        
        while (fichaSimulada < valor) {
            fichaSimulada = fichaSimulada * 2;
            puntajeAcumulado = puntajeAcumulado * 3;
        }
        return puntajeAcumulado;
    }

	public String getMovimientoSugerido() {
    		return tablero.movimientoSugerido();
    }
    
    private boolean esPartidaAunValida() {
    		return !gameOver && (!tablero.estaCompleto() || tablero.hayMovimientosPosibles());
    }
    
    private void configurarPartidaTerminada() {
        gameOver = true;
        puntaje = calcularPuntaje();
        Ranking.registrarPartida(partidaActual);
        ReproductorMusica.detener();
    }

	public Tablero getTablero() {
		return this.tablero;
	}
    
}