package Logica;

public class TableroTest {
	
	public static void main(String[] args) {
        testDerecha();
        testIzquierda();
        testAbajo();
        testArriba();
    }

    static void testDerecha() {
        System.out.println("DERECHA");
        Tablero t = new Tablero();
        t.setCelda(0, 1, 1);
        t.setCelda(0, 2, 3);
        t.setCelda(0, 3, 3);
        

        System.out.println("Antes:");
        t.imprimir();
        t.mover("DERECHA");
        System.out.println("Después:");
        t.imprimir();
    }

    static void testIzquierda() {
        System.out.println("IZQUIERDA");
        Tablero t = new Tablero();
        t.setCelda(0, 0, 6);
        t.setCelda(0, 1, 6);
        t.setCelda(0, 2, 2);
        

        System.out.println("Antes:");
        t.imprimir();
        t.mover("IZQUIERDA");
        System.out.println("Después:");
        t.imprimir();
    }

    static void testAbajo() {
        System.out.println("ABAJO");
        Tablero t = new Tablero();
        t.setCelda(0, 0, 1);
        t.setCelda(1, 0, 2);
        t.setCelda(2, 0, 12);
        

        System.out.println("Antes:");
        t.imprimir();
        t.mover("ABAJO");
        System.out.println("Después:");
        t.imprimir();
    }

    static void testArriba() {
        System.out.println("ARRIBA");
        Tablero t = new Tablero();
        t.setCelda(3, 2, 3);
        t.setCelda(2, 2, 3);
        t.setCelda(1, 2, 1);
        

        System.out.println("Antes:");
        t.imprimir();
        t.mover("ARRIBA");
        System.out.println("Después:");
        t.imprimir();
    }


}
