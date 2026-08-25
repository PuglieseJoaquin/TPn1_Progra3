package Logica;

public class Ficha {
	
	private int valor;

    public Ficha(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public boolean puedeFusionarseCon(Ficha otra) {
        if (otra == null) return false;
        if ((this.valor == 1 && otra.valor == 2) || (this.valor == 2 && otra.valor == 1)) {
            return true;
        }
        return this.valor == otra.valor && this.valor >= 3;
    }

    public Ficha fusionarCon(Ficha otra) {
        return new Ficha(this.valor + otra.valor);
    }

}
