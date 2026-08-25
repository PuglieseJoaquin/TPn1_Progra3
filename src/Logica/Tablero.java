package Logica;

public class Tablero {
	
		public static final String ARRIBA = "ARRIBA";
	    public static final String ABAJO = "ABAJO";
	    public static final String IZQUIERDA = "IZQUIERDA";
	    public static final String DERECHA = "DERECHA";
		private Ficha[][] celdas = new Ficha[4][4];
		//private int puntaje;
		//private Random random = new Random();
		public Tablero() {
		    //agregarFichaNueva();
		    //agregarFichaNueva();
		}
		
		//aca se puede cambiar por los enum
		public boolean mover(String direc) {
		    Ficha[][] copia = copiarCeldas();
		
		    if (direc.equals(DERECHA)) {
		        moverFilasDerecha();
		    } else if (direc.equals(IZQUIERDA)) {
		        invertirFilas();
		        moverFilasDerecha();
		        invertirFilas();
		    } else if (direc.equals(ABAJO)) {
		        FilaAColumna();
		        moverFilasDerecha();
		        FilaAColumna();
		    } else if (direc.equals(ARRIBA)) {
		    	FilaAColumna();
		        invertirFilas();
		        moverFilasDerecha();
		        invertirFilas();
		        FilaAColumna();
		    }
		
		    boolean huboMovimiento = !tableroIgual(copia, celdas); //compara tableros
		    if (huboMovimiento) {
		        //agregarFichaNueva();
		    }
		    return huboMovimiento;
		}
		
		 //SI UTILIZAMOS LA CLASE ENUM//
		
		//public boolean mover(Direccion direc) {
		//  Ficha[][] copia = copiarCeldas();
		//
		//  switch (direc) {
		//      case DERECHA:
		//          moverFilasDerecha();
		//          break;
		//      case IZQUIERDA:
		//          invertirFilas();
		//          moverFilasDerecha();
		//          invertirFilas();
		//          break;
		//      case ABAJO:
		//          FilaAColumna();
		//          moverFilasDerecha();
		//          FilaAColumna();
		//          break;
		//      case ARRIBA:
		//          FilaAColumna();
		//          invertirFilas();
		//          moverFilasDerecha();
		//          invertirFilas();
		//          FilaAColumna();
		//          break;
		//  }
		
		
		
		
		private boolean tableroIgual(Ficha[][] a, Ficha[][] b) {
		    for (int fila = 0; fila < 4; fila++) {
		        for (int col = 0; col < 4; col++) {
		        	//tablero a
		            Ficha fa = a[fila][col];
		            //tablero b
		            Ficha fb = b[fila][col];
		            
		            if (fa == null && fb == null) {
		                // ambas nulas, están "iguales" en esta celda, no hacemos nada
		            } else if (fa == null || fb == null) {
		                return false;
		            } else if (fa.getValor() != fb.getValor()) {
		                return false;
		            }
		        }
		    }
		    return true;
		}
		
		
		public void moverFilasDerecha() {
		    for (int fila = 0; fila < 4; fila++) {
		        moverFilaDerecha(fila);
		    }
		}
		public void moverFilaDerecha(int fila) {
			//inicializa [false][false][false][false]
		    yaSeFusionoEnEstaJugada = new boolean[4];
		    //empiezo de la col 2 porque col 3 es el borde
		    for (int col = 2; col >= 0; col--) {
		    	//agarro la ficha actual, la primera es [0][2]
		        Ficha actual = celdas[fila][col];
		        //si donde estoy parado tiene un valor
		        if (actual != null) {
		            Ficha vecina = celdas[fila][col + 1];
		            
		            //si es null cambio valores
		            if (vecina == null) {
		                celdas[fila][col + 1] = actual;
		                celdas[fila][col] = null;
		                //pregunto si puedo fucionar actual con vecina y si no se fusiono en la jugada anterior
		                //misma fila
		            } else if (actual.puedeFusionarseCon(vecina) && !yaSeFusionoEnEstaJugada[col + 1]) {
		                //si puedo hago el cambio
		            	celdas[fila][col + 1] = actual.fusionarCon(vecina); // clase ficha
		                celdas[fila][col] = null;
		                //marco true para que no puedo hacer mas fusiones en esta fila
		                yaSeFusionoEnEstaJugada[col + 1] = true;
				            }
				        }
				    }
				}
				
		
		
		
		
		
		 private void invertirFilas() {
		        for (int fila = 0; fila < 4; fila++) {
		            Ficha temp = celdas[fila][0];
		            celdas[fila][0] = celdas[fila][3];
		            celdas[fila][3] = temp;
		
		            temp = celdas[fila][1];
		            celdas[fila][1] = celdas[fila][2];
		            celdas[fila][2] = temp;
		        }
		    }
		    //hago que col sea cambia a fila, para que pueda manipularlo moverFilaDerecha
		 private void FilaAColumna() {
		        Ficha[][] nueva = new Ficha[4][4];
		        for (int fila = 0; fila < 4; fila++) {
		            for (int col = 0; col < 4; col++) {
		                nueva[col][fila] = celdas[fila][col];
		            }
		        }
		        celdas = nueva;
		    }
		    
		    
		    //hace una copia de la matriz
		 private Ficha[][] copiarCeldas() {
		        Ficha[][] copia = new Ficha[4][4];
		        for (int fila = 0; fila < 4; fila++) {
		            for (int col = 0; col < 4; col++) {
		                copia[fila][col] = celdas[fila][col];
		            }
		        }
		        return copia;
		    }
		    
		 private boolean[] yaSeFusionoEnEstaJugada = new boolean[4];
		
		    
		    
		    
		    //METODOS PARA VERIFICAR POR CONSOLA, LO HIZO LA IA
		 public void imprimir() { 
			for (int fila = 0; fila < 4; fila++) { 
				for (int col = 0; col < 4; col++) { 
					if (celdas[fila][col] == null) 
						System.out.print(".\t"); 
					else System.out.print(celdas[fila][col].getValor() + "\t");
					} 
				System.out.println();
				} 
			System.out.println(); 
			}
		
		
		 public void setCelda(int fila, int col, int valor) { 
		    	
		    	celdas[fila][col] = (valor == 0) ? null : new Ficha(valor); 
		    	
		    }





}
