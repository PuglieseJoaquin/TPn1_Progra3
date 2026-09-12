package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Ranking {
	
    private static ArrayList<Partida> partidas4x4 = new ArrayList<>();
    private static ArrayList<Partida> partidas5x5 = new ArrayList<>();
    private static ArrayList<Partida> partidas6x6 = new ArrayList<>();
    
    
    public static void registrarPartida(Partida partida) {
   	 	String nivel = partida.getNivel();
   	    if (nivel.equals("Clásico 4x4")) partidas4x4.add(partida);
   	    else if (nivel.equals("Extra 5x5")) partidas5x5.add(partida);
   	    else if (nivel.equals("Supremo 6x6")) partidas6x6.add(partida);
    		} 
    
    public static ArrayList<Partida> getTop5Puntajes(String nivel) {
	    	if(nivel.equals("Clásico 4x4")) return obtenerMejores(partidas4x4);
	    	else if (nivel.equals("Extra 5x5")) return obtenerMejores(partidas5x5);
	    	else return obtenerMejores(partidas6x6);	    	
	    }	       
      
    private static ArrayList<Partida> obtenerMejores(ArrayList<Partida> lista) {
        ArrayList<Partida> partidasAElegir = new ArrayList<>(lista);
        ArrayList<Partida> rankingFinal = new ArrayList<>();

        int cantidadMaximaPorMostrar = 5;
        int cantidadAMostrar = Math.min(partidasAElegir.size(), cantidadMaximaPorMostrar);

        for (int i = 0; i < cantidadAMostrar; i++) {
            Partida mejor = encontrarMejor(partidasAElegir);
            rankingFinal.add(mejor);
            partidasAElegir.remove(mejor);
        }

        return rankingFinal;
    }
    
    private static Partida encontrarMejor(ArrayList<Partida> lista) {
        return Collections.max(lista, new Comparator<Partida>() {
        		@Override
        		public int compare(Partida p1, Partida p2) {
        			return Integer.compare(p1.getPuntaje(), p2.getPuntaje());
        		}
        });
    }
}