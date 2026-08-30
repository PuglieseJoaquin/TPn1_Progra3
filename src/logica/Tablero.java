package logica;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


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
		this.proximoValorFicha = generarValorRandom();
		this.movimientoSugerido = movimientoSugerido();

		// seteado en dos porque tenemos 2 fichas al comenzar
		this.contadorFichas = 2;
		// inicia tablero con Fichas null
		fichas = new Ficha[tamanioMatriz][tamanioMatriz];

		// hardcodeado
		this.fichas[1][1] = new Ficha(1);
		this.fichas[2][2] = new Ficha(2);
	
	}

	public void moverDerecha() {
		if (bordeDerechoVacio()) {
			moverFilasALaDerecha();

		} else {
			if (hayMovimientoALaDerecha()) {
				moverFilasALaDerecha();
				fichaRandomIzquierda();
			}
		}
	}

	public void moverIzquierda() {
		if (bordeIzquierdoVacio()) {
			subrutinaIzquierda();

		} else {
			if (hayMovimientoALaIzquierda()) {
			subrutinaIzquierda();
			fichaRandomDerecha();
			}
		}
	}

	private void subrutinaIzquierda() {
		invertirFilas();
		moverFilasALaDerecha();
		invertirFilas();
	}

	public void moverAbajo() {
		if (bordeAbajoVacio()) {
			subrutinaAbajo();

		} else {
			if (hayMovimientoHaciaAbajo()) {
			subrutinaAbajo();
			fichaRandomAbajo();
			}
		}
	}

	private void subrutinaAbajo() {
		columnaAFila();
		moverFilasALaDerecha();
		columnaAFila();
	}

	public void moverArriba() {
		if (bordeArribaVacio()) {
			subrutinaArriba();

		} else {
			if (hayMovimientoHaciaArriba()) {
			subrutinaArriba();
			fichaRandomArriba();
			}
		}
	}

	private void subrutinaArriba() {
		columnaAFila();
		subrutinaIzquierda();
		columnaAFila();
	}

	private boolean puedenMoverse (Ficha a, Ficha b) {
		return a==null || b==null || a.puedeFusionarseCon(b);
	}
	
	private boolean hayMovimientoALaDerecha() {
		for (int fila = 0; fila < tamanio; fila++) {
			for (int col = 0; col < tamanio-1; col++) {
				
				Ficha actual = fichas[fila][col];
				Ficha derecha = fichas[fila][col+1];
				
				if (puedenMoverse(actual, derecha)) {
					return true;
				}
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
	
	public boolean bordeDerechoVacio() {
		for (int fila = 0; fila < tamanio; fila++) {
			if (fichas[fila][tamanio - 1] != null) {
				return false;
			}
		}
		return true;
	}

	public boolean bordeIzquierdoVacio() {
		for (int fila = 0; fila < tamanio; fila++) {
			if (fichas[fila][0] != null) {
				return false;
			}
		}
		return true;
	}

	public boolean bordeArribaVacio() {
		for (int col = 0; col < tamanio; col++) {
			if (fichas[0][col] != null) {
				return false;
			}
		}
		return true;
	}

	public boolean bordeAbajoVacio() {
		for (int col = 0; col < tamanio; col++) {
			if (fichas[tamanio - 1][col] != null) {
				return false;
			}
		}
		return true;
	}
	
	public void moverFilasALaDerecha() {
		for (int fila = 0; fila < tamanio; fila++) {
			moverFilaDerecha(fila);
		}
	}

	public void moverFilaDerecha(int fila) {
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
			} else {
				actual = null;
				;
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

	public int getProximoValorFicha() {
		return proximoValorFicha;
	}
	
	private void fichaRandomIzquierda(){
		
		int posicion = generarPosicionRandom();
		
		if (fichas[posicion][0]==null) {
			int valor = proximoValorFicha;
			proximoValorFicha = generarValorRandom();
			Ficha fichaRandom = new Ficha(valor);
			fichas[posicion][0] = fichaRandom;
			contadorFichas++;
			
		} else {
			fichaRandomIzquierda();
		}
	}
	
	private void fichaRandomDerecha() {
		invertirFilas();
		fichaRandomIzquierda();
		invertirFilas();
	}
	
	private void fichaRandomAbajo() {
		columnaAFila();
		fichaRandomIzquierda();
		columnaAFila();
	}
	
	private void fichaRandomArriba() {
		columnaAFila();
		fichaRandomDerecha();
		columnaAFila();
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

	                if (f.getValor() > valorMaxFicha) {
	                	valorMaxFicha = f.getValor();
	                }
	            }
	        }
	    }
	    this.valorMaximo = valorMaxFicha;
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

    public String movimientoSugerido() {
    	List<String> posibles = new ArrayList<>();

    	if (hayMovimientoALaDerecha()) posibles.add("DERECHA");
    	if (hayMovimientoALaIzquierda()) posibles.add("IZQUIERDA");
    	if (hayMovimientoHaciaAbajo()) posibles.add("ARRIBA");
    	if (hayMovimientoHaciaArriba()) posibles.add("ABAJO");

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
   
}
