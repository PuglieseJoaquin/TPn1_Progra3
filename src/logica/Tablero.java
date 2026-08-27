package logica;

import java.util.Random;

public class Tablero {

	public boolean hayFichaBordeSuperior = false;
	public boolean hayFichaBordeInferior = false;
	public boolean hayFichaBordeDerecho = false;
	public boolean hayFichaBordeIzquierdo = false;

	private Random posicionRandom;
	private Random valorRandom;

	private Ficha[][] fichas;
	private int tamaño;
	private int contadorFichas;

	public Tablero(int tamañoMatriz) {
		this.tamaño = tamañoMatriz;
		this.fichas = new Ficha[tamañoMatriz][tamañoMatriz];

		// seteado en dos porque tenemos 2 fichas al comenzar
		this.contadorFichas = 2;
		// inicia tablero con Fichas null
		fichas = new Ficha[tamañoMatriz][tamañoMatriz];

		// hardcodeado
		this.fichas[1][1] = new Ficha(1);
		this.fichas[2][2] = new Ficha(2);
	
	}

	// metodo innecesario, solo es de control
	public int getContadorFichas() {
		return contadorFichas;
	}

	public void moverDerecha() {
		if (ladoDerechoVacio()) {
			moverFilasALaDerecha();

		} else {
			if (hayMovimientoALaDerecha()) {
				moverFilasALaDerecha();
				fichaRandomIzquierda();
			}
		}
	}

	public void moverIzquierda() {
		if (ladoIzquierdoVacio()) {
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
		if (ladoAbajoVacio()) {
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
		if (ladoArribaVacio()) {
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
		for (int fila = 0; fila < tamaño; fila++) {
			for (int col = 0; col < tamaño-1; col++) {
				
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
	
	public boolean ladoDerechoVacio() {
		for (int fila = 0; fila < tamaño; fila++) {
			if (fichas[fila][tamaño - 1] != null) {
				return false;
			}
		}
		return true;
	}

	public boolean ladoIzquierdoVacio() {
		for (int fila = 0; fila < tamaño; fila++) {
			if (fichas[fila][0] != null) {
				return false;
			}
		}
		return true;
	}

	public boolean ladoArribaVacio() {
		for (int col = 0; col < tamaño; col++) {
			if (fichas[0][col] != null) {
				return false;
			}
		}
		return true;
	}

	public boolean ladoAbajoVacio() {
		for (int col = 0; col < tamaño; col++) {
			if (fichas[tamaño - 1][col] != null) {
				return false;
			}
		}
		return true;
	}

	public void moverFilasALaDerecha() {
		for (int fila = 0; fila < tamaño; fila++) {
			moverFilaDerecha(fila);
		}
	}

	public void moverFilaDerecha(int fila) {
		boolean yaSeFusiono = false;

		for (int col = tamaño - 2; col >= 0; col--) {
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
				}
			} else {
				actual = null;
				;
			}
		}
	}

	private void invertirFilas() {
		for (int fila = 0; fila < tamaño; fila++) {
			for (int col = 0; col < tamaño / 2; col++) {
				Ficha temp = fichas[fila][col];
				fichas[fila][col] = fichas[fila][tamaño - 1 - col];
				fichas[fila][tamaño - 1 - col] = temp;
			}
		}
	}

	// hago que col sea cambia a fila, para que pueda manipularlo moverFilaDerecha
	private void columnaAFila() {
		Ficha[][] nueva = new Ficha[tamaño][tamaño];
		for (int fila = 0; fila < tamaño; fila++) {
			for (int col = 0; col < tamaño; col++) {
				nueva[col][fila] = fichas[fila][col];
			}
		}
		fichas = nueva;
	}

	private void fichaRandomIzquierda(){
		
		int posicion = generarPosicionRandom();
		
		if (fichas[posicion][0]==null) {
			int valor = generarValorRandom();
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
		int posicion = posicionRandom.nextInt(tamaño);
		
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

	public int getTamaño() {
		return tamaño;
	}

	public boolean estaCompleto() {
		int cantidadEspacios = tamaño * tamaño;
		return (contadorFichas == cantidadEspacios);
	}

	public boolean hayMovimientosPosibles() {
		return hayMovimientoALaDerecha() || hayMovimientoALaIzquierda() ||
				hayMovimientoHaciaArriba() || hayMovimientoHaciaAbajo();
	}
}
