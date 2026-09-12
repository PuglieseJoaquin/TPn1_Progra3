package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import modelo.Direccion;

public class Tablero {

	public String movimientoSugerido;
	private Random posicionRandom;
	private Random valorRandom;
	private Ficha[][] fichas;
	private int tamanio;
	private int contadorFichas;
	private int proximoValorFicha;
	private int valorMaximo;

	
	public Tablero(int tamanioMatriz) {
		this.tamanio = tamanioMatriz;
		this.fichas = new Ficha[tamanioMatriz][tamanioMatriz];
		this.fichas[1][1] = new Ficha(1);
		this.fichas[2][2] = new Ficha(2);
		this.contadorFichas = 2;
		this.proximoValorFicha = generarValorRandom();
		this.movimientoSugerido = movimientoSugerido();
	}

	public void mover(Direccion direccion) {
	    switch (direccion) {
	        case DERECHA:
	            moverDerecha();
	            break;
	        case IZQUIERDA:
	            moverIzquierda();
	            break;
	        case ABAJO:
	            moverAbajo();
	            break;
	        case ARRIBA:
	            moverArriba();
	            break;
	    }
	}
	
	public boolean bordeIzquierdoVacio() {
		for (int fila = 0; fila < tamanio; fila++) {
			if (fichas[fila][0] != null)
				return false;
		}
		return true;
	}

	public boolean bordeAbajoVacio() {
		for (int col = 0; col < tamanio; col++) {
			if (fichas[tamanio - 1][col] != null)
				return false;
		}
		return true;
	}

	public boolean bordeArribaVacio() {
		for (int col = 0; col < tamanio; col++) {
			if (fichas[0][col] != null)
				return false;
		}
		return true;
	}

	public int getProximoValorFicha() {
		return proximoValorFicha;
	}
	
	public void generarFichaEnLugarEncontrado(int fila, int col) {
		int valor = proximoValorFicha;
		proximoValorFicha = generarValorRandom();
		Ficha fichaRandom = new Ficha(valor);
		fichas[fila][col] = fichaRandom;
		contadorFichas++;
	}

	public Ficha getFicha(int fila, int col) {
		return fichas[fila][col];
	}

	public int getTamanio() {
		return tamanio;
	}

	public boolean estaCompleto() {
		int cantidadEspacios = tamanio * tamanio;
		return (contadorFichas == cantidadEspacios);
	}

	public boolean hayMovimientosPosibles() {
		return hayMovimientoALaDerecha() || hayMovimientoALaIzquierda() ||
				hayMovimientoHaciaArriba() || hayMovimientoHaciaAbajo();
	}

	public int calcularPuntaje() {
	    int cont = 0;
	    int valorMaxFicha = 0;

	    for (int fila = 0; fila < tamanio; fila++) {
	        for (int col = 0; col < tamanio; col++) {
	            Ficha f = fichas[fila][col];
	            if (f != null && f.getValor() != 1 && f.getValor() != 2) {
	                cont += puntajeDeFicha(f.getValor());

	                if (f.getValor() > valorMaxFicha) 
	                	valorMaxFicha = f.getValor();
	            }
	        }
	    }
	    this.valorMaximo = valorMaxFicha;
	    return cont;
	}
    
    public String movimientoSugerido() {
    	List<String> posibles = new ArrayList<>();

    	if (hayMovimientoALaDerecha()) posibles.add("DERECHA");
    	if (hayMovimientoALaIzquierda()) posibles.add("IZQUIERDA");
    	if (hayMovimientoHaciaAbajo()) posibles.add("ABAJO");
    	if (hayMovimientoHaciaArriba()) posibles.add("ARRIBA");

    	if (!posibles.isEmpty()) {
    	    Random r = new Random();
    	    return posibles.get(r.nextInt(posibles.size()));
    	} else {
    		return "Error 404 :P";
    		}
    }
    
    public int getValorMaximo() {
    	return valorMaximo;
    }
    
    private void subrutinaIzquierda() {
		invertirFilas();
		moverFilasALaDerecha();
		invertirFilas();
	}
   
    private void subrutinaAbajo() {
		columnaAFila();
		moverFilasALaDerecha();
		columnaAFila();
	}
    
    private void subrutinaArriba() {
		columnaAFila();
		subrutinaIzquierda();
		columnaAFila();
	}
    
    private void moverFilasALaDerecha() {
		for (int fila = 0; fila < tamanio; fila++) {
			moverFilaDerecha(fila);
		}
	}

	private void moverFilaDerecha(int fila) {
		boolean yaSeFusiono = false;

		for (int col = tamanio - 2; col >= 0; col--) {
			Ficha actual = fichas[fila][col];

			if (actual != null) {
				int destino = col + 1;
				Ficha vecina = fichas[fila][destino];

				if (vecina == null) {
					fichas[fila][destino] = actual;
					fichas[fila][col] = null;

				} else if (!yaSeFusiono && actual.puedeFusionarseCon(vecina)) {
					fichas[fila][destino] = actual.fusionarCon(vecina);
					fichas[fila][col] = null;
					yaSeFusiono = true;
					contadorFichas--;
					
					ReproductorMusica.reproducirEfecto("/audio/punch.wav");
				}
			}
		}
	}

	private void invertirFilas() {
		for (int fila = 0; fila < tamanio; fila++) {
			for (int col = 0; col < tamanio / 2; col++) {
				Ficha temp = fichas[fila][col];
				fichas[fila][col] = fichas[fila][tamanio - 1 - col];
				fichas[fila][tamanio - 1 - col] = temp;
			}
		}
	}

	private void columnaAFila() {
		Ficha[][] nueva = new Ficha[tamanio][tamanio];
		for (int fila = 0; fila < tamanio; fila++) {
			for (int col = 0; col < tamanio; col++) {
				nueva[col][fila] = fichas[fila][col];
			}
		}
		fichas = nueva;
	}

	private boolean puedenMoverse (Ficha a, Ficha b) {
		return a==null || b==null || a.puedeFusionarseCon(b);
	}
	
	private boolean hayMovimientoALaDerecha() {
		for (int fila = 0; fila < tamanio; fila++) {
			for (int col = 0; col < tamanio-1; col++) {
				
				Ficha actual = fichas[fila][col];
				Ficha derecha = fichas[fila][col+1];
				
				if (puedenMoverse(actual, derecha)) return true;
			}	
		}
		return false;
	}

	private boolean hayMovimientoALaIzquierda() {
		invertirFilas();
		boolean hayMovimiento = hayMovimientoALaDerecha();
		invertirFilas();
		
		return hayMovimiento;
	}

	private boolean hayMovimientoHaciaAbajo() {
		columnaAFila();
		boolean hayMovimiento = hayMovimientoALaDerecha();
		columnaAFila();
		
		return hayMovimiento;
	}

	private boolean hayMovimientoHaciaArriba() {
		columnaAFila();
		boolean hayMovimiento = hayMovimientoALaIzquierda();
		columnaAFila();
		
		return hayMovimiento;
	}
	
	private boolean bordeDerechoVacio() {
		for (int fila = 0; fila < tamanio; fila++) {
			if (fichas[fila][tamanio - 1] != null)
				return false;
		}
		return true;
	}
	
	private void fichaRandomIzquierda(){		
		int posicion = generarPosicionRandom();
		
		if (fichas[posicion][0] == null)
			generarFichaEnLugarEncontrado(posicion, 0);
		else fichaRandomIzquierda();
	}
	
	private void fichaRandomDerecha(){
		int posicion = generarPosicionRandom();
		
		if (fichas[posicion][tamanio-1] == null)
			generarFichaEnLugarEncontrado(posicion, tamanio-1);	
		else fichaRandomDerecha();
	}
	
	private void fichaRandomArriba(){
		int posicion = generarPosicionRandom();
		
		if (fichas[0][posicion] == null)
			generarFichaEnLugarEncontrado(0, posicion);
		else fichaRandomArriba();
	}
	
	private void fichaRandomAbajo(){	
		int posicion = generarPosicionRandom();
		
		if (fichas[tamanio-1][posicion] == null)
			generarFichaEnLugarEncontrado(tamanio-1, posicion);			
		else fichaRandomAbajo();		
	}
	
	private int generarPosicionRandom() {
		posicionRandom = new Random();
		int posicion = posicionRandom.nextInt(tamanio);
		
		return posicion;
	}
	
	private int generarValorRandom() {
		valorRandom = new Random();
		int valor = valorRandom.nextInt(1, 4);
		
		return valor;
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
	
	private void moverDerecha() {
		if (bordeDerechoVacio()) moverFilasALaDerecha();
		else {
			if (hayMovimientoALaDerecha()) {
				moverFilasALaDerecha();
				fichaRandomIzquierda();
			}
		}
	}

	private void moverIzquierda() {
		if (bordeIzquierdoVacio()) subrutinaIzquierda();
		else {
			if (hayMovimientoALaIzquierda()) {
				subrutinaIzquierda();
				fichaRandomDerecha();
			}
		}
	}
	
	private void moverAbajo() {
		if (bordeAbajoVacio()) subrutinaAbajo();
		else {
			if (hayMovimientoHaciaAbajo()) {
				subrutinaAbajo();
				fichaRandomArriba();
			}
		}
	}
	
	private void moverArriba() {
		if (bordeArribaVacio()) subrutinaArriba();
		else {
			if (hayMovimientoHaciaArriba()) {
				subrutinaArriba();
				fichaRandomAbajo();
			}
		}
	}
}
