package logica;

import java.util.ArrayList;

public class Usuario {
	private String nombre;
    private ArrayList<Partida> partidas;;
	
	public Usuario(String nombre) {
		this.nombre = nombre;
		this.partidas = new ArrayList<>();
	}

	public String getNombre() {
		return this.nombre;
	}
	
	public void agregarPartida(Partida partida) {
		partidas.add(partida);
	}
	
	public ArrayList<Partida> getPartidas(){
		return partidas;
	}
}
