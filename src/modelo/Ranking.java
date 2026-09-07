package modelo;

import java.util.ArrayList;

public class Ranking {
    private static ArrayList<Partida> partidas4x4 = new ArrayList<>();
    private static ArrayList<Partida> partidas5x5 = new ArrayList<>();
    private static ArrayList<Partida> partidas6x6 = new ArrayList<>();
    
    
    public static void registrarPartida(Partida partida) {
   	 String nivel = partida.getNivel();
   	    if (nivel.equals("Clásico 4x4")) {
   	        partidas4x4.add(partida);
   	        
   	    } else if (nivel.equals("Extra 5x5")) {
   	        partidas5x5.add(partida);
   	    
   	    } else if (nivel.equals("Supremo 6x6")) {
   	        partidas6x6.add(partida);
   	    }
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
    

}
