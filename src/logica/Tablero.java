package logica;

public class Tablero {
	
		public boolean hayFichaBordeSuperior = false;
		public boolean hayFichaBordeInferior = false;
		public boolean hayFichaBordeDerecho = false;
		public boolean hayFichaBordeIzquierdo = false;
		
		private Ficha[][] fichas;
		private int tamaño;
		
		public Tablero (int tamañoMatriz) {
			this.tamaño = tamañoMatriz;
			this.fichas = new Ficha[tamañoMatriz][tamañoMatriz];
			
			//inicia tablero con Fichas null
			fichas = new Ficha[tamañoMatriz][tamañoMatriz];
			
			//hardcodeado
			this.fichas[1][1] = new Ficha(60);
			this.fichas[2][2] = new Ficha(60);
			this.fichas[0][0] = new Ficha(1);
			this.fichas[2][1] = new Ficha(2);
//			this.fichas[1][2] = new Ficha(1);
//			this.fichas[2][3] = new Ficha(2);
//			this.fichas[3][1] = new Ficha(1);
//			this.fichas[3][2] = new Ficha(2);
//			insertarFichaAleatoria();
//			insertarFichaAleatoria();
//			
		}

		public void moverDerecha() {	
			if (ladoDerechoVacio()) {
				moverFilasALaDerecha();
			} else {
				moverFilasALaDerecha();
				System.out.println("aca va la llamada a agregar randomIzquierdo");
			}
		}
		
		public void moverIzquierda() {	
			if (ladoIzquierdoVacio()) {
		        invertirFilas();
		        moverFilasALaDerecha();
		        invertirFilas();
			} else {
		        invertirFilas();
		        moverFilasALaDerecha();
		        invertirFilas();
				System.out.println("aca va la llamada a agregar randomDerecho");
			}
		}
		
		public void moverAbajo() {	
			if (ladoAbajoVacio()) {
		        columnaAFila();
		        moverFilasALaDerecha();
		        columnaAFila();
			} else {
		        columnaAFila();
		        moverFilasALaDerecha();
		        columnaAFila();
				System.out.println("aca va la llamada a agregar randomArriba");
			}
		}
		
		public void moverArriba() {	
			if (ladoArribaVacio()) {
				columnaAFila();
				invertirFilas();
				moverFilasALaDerecha();
				invertirFilas();
				columnaAFila();
			} else {  			
				columnaAFila();
		        invertirFilas();
		        moverFilasALaDerecha();
		        invertirFilas();
		        columnaAFila();				
		        System.out.println("aca va la llamada a agregar randomAbajo");
			}
		}
		
			
		public boolean ladoDerechoVacio() {
			for (int fila = 0; fila < tamaño; fila ++) {
				if (fichas[fila][tamaño-1] != null) {
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
		            }
		        } else {
		        	actual = null;;
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
		 
		    //hago que col sea cambia a fila, para que pueda manipularlo moverFilaDerecha
		 private void columnaAFila() {
		        Ficha[][] nueva = new Ficha[tamaño][tamaño];
		        for (int fila = 0; fila < tamaño; fila++) {
		            for (int col = 0; col < tamaño; col++) {
		                nueva[col][fila] = fichas[fila][col];
		            }
		        }
		        fichas = nueva;
		    }
		    


		public Ficha getFicha(int fila, int col) {
			return fichas[fila][col];
		}
		public int getTamaño() {
			return tamaño;
		}

		public boolean estaCompleto() {
			System.out.println("no estoy implementado estaCompleto");
			// TODO Auto-generated method stub
			return false;
		}

		public boolean hayMovimientosPosibles() {
			System.out.println("no estoy implementado hayMovimientosPosibles");

			// TODO Auto-generated method stub
			return true;
		}


}
