package presentador;

import java.util.ArrayList;
import java.util.List;
import modelo.Ranking;
import modelo.Partida;
import vista.GestorPantallas;

public class RankingPresentador {
	private GestorPantallas gestorPantallas;


	public RankingPresentador(GestorPantallas gestorPantallas) {
		this.gestorPantallas = gestorPantallas;	
	}

	public void manejarClickVolverAlMenu() {
		gestorPantallas.crearPantallaMenu();	
	}

	public List<Object[]> getFilasRanking(String nivel) {
		
	    ArrayList<Partida> partidas = Ranking.getTop5Puntajes(nivel);
	    
	    List<Object[]> filas = new ArrayList<>();
	    
	    for (int i = 0; i < partidas.size(); i++) {
	        Partida p = partidas.get(i);
	        filas.add(new Object[]{i+1, p.getNombreJugador(), p.getPuntaje(), p.getValorFichaMaximo(), p.getNivel()});
	    }
	    return filas;
	}
}
