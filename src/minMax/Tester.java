package minMax;

import java.util.ArrayList;
import minMax.ArbolMinMax;
import modelo.Jugador;
import modelo.Tablero;

public class Tester {
    public static void main(String args[]) {
        Jugador j1 = new Jugador();
        Jugador j2 = new Jugador();
        Tablero t = new Tablero(j1, j2);
        t.iniciarJuego();
        ArbolMinMax arbolito = new ArbolMinMax(t.getTablero(),2, j1.getFichasActuales(),j2.getFichasActuales(),
                        j1.getPuntosColor(), j2.getPuntosColor());
        //ArrayList<int[]> posibilidades= arbolito.posiblesPosiciones(t.getTablero());
        //System.out.println(posibilidades.size());
        arbolito.funcionPrincipal();
    }
}
