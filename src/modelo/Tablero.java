package modelo;

import java.util.ArrayList;
import modelo.Ficha;

public class Tablero{
  Jugador j1,j2;
  ArrayList<ArrayList<Ficha>> tablero;
  boolean gameOver;
  boolean turno;
  ArraList<Ficha> bolsaFichas;

  public Tablero(Jugador j1, Jugador j2){
    this.j1 = j1;
    this.j2 = j2;
    gameOver = false;
    turno = false;
    bolsaFichas = new ArrayList<Ficha>();
    tablero = new ArrayList<ArrayList<Ficha>>();

    for (int i = 0; i < 11; i++) {
      tablero.add(new ArrayList<Ficha>());
    }

    for (int i=0;i<11;i++ ) {
      if(i<6){
        for (int j=0;j<i+6;j++) {
          tablero.get(i).add(new Ficha(i,j,null,0));
        }
      }else{
        for (int j=0;j<16-i;j++) {
          tablero.get(i).add(new Ficha(i,j,null,0));

        }
      }
    }

    tablero.get(0).get(0).setColor(1);
    tablero.get(0).get(5).setColor(2);
    tablero.get(5).get(0).setColor(3);
    tablero.get(5).get(10).setColor(4);
    tablero.get(10).get(0).setColor(5);
    tablero.get(10).get(5).setColor(6);

  }
}
