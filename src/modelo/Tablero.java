package modelo;
import java.util.ArrayList;

import java.util.ArrayList;
import modelo.Ficha;

public class Tablero{
  Jugador j1,j2;
  ArrayList<ArrayList<Ficha>> tablero;
  boolean gameOver;
  boolean turno;
  ArrayList<Ficha> bolsaFichas;

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

	//Funciones:
	//Funcion principal (se manejan los turnos y la jugada)
	/*void juego(){
		while(!gameover){
			if(turno == true){
				Ficha fichaJugada = seleccionarFicha(j1);
				int posiciones[] = ingresarTurno();
				fichaJugada = j1.mover(fichaJugada, posiciones);
				j1.agregarFicha(generarFichaBolsa());
				//Calcular puntaje y sumarlo
				//Revisar si hay gameover
				//Cambiar turno
			}
		}
	}*/
	//Validar puntos obtenidos dado una jugada (ficha obtenida del metodo mover en jugador)
	//Validar gameover
	//Obtener jugador ganador
/*  public Jugador jugadorGanador(){
      Jugador ganador = new Jugador();
      if(gameOver){
          int puntajeJugador1[] = j1.getPuntosColor();
          int puntajeTotalJ1 = Integer.MAX_VALUE;
          int puntajeJugador2[] = j2.getPuntosColor();
          int puntajeTotalJ2 = Integer.MAX_VALUE;

          for(int i = 0; i < puntajeJugador1.length;i++){
              if(!(puntajeTotalJ1 < puntajeJugador1[i])){
                 puntajeTotalJ1 = puntajeJugador1[i];
              }
              if(!(puntajeTotalJ2 < puntajeJugador2[i])){
                 puntajeTotalJ2 = puntajeJugador2[i];
              }
          }

          if(puntajeTotalJ1 > puntajeTotalJ2){
              ganador = j1;
          }else{
              ganador = j2;
          }
      }
      return ganador;
  }
*/
	//Setters y getters
/*  public Jugador getJ1() {
      return j1;
  }

  public void setJ1(Jugador j1) {
      this.j1 = j1;
  }

  public Jugador getJ2() {
      return j2;
  }

  public void setJ2(Jugador j2) {
      this.j2 = j2;
  }

  public ArrayList<ArrayList<Ficha>> getTablero() {
      return tablero;
  }

  public void setTablero(ArrayList<ArrayList<Ficha>> tablero) {
      this.tablero = tablero;
  }

  public boolean isGameOver() {
      return gameOver;
  }

  public void setGameOver(boolean gameOver) {
      this.gameOver = gameOver;
  }

  public boolean isTurno() {
      return turno;
  }

  public void setTurno(boolean turno) {
      this.turno = turno;
  }

  public ArrayList<Ficha> getBolsaFichas() {
      return bolsaFichas;
  }

  public void setBolsaFichas(ArrayList<Ficha> bolsaFichas) {
      this.bolsaFichas = bolsaFichas;
  }
*/
	//Funcion que permite seleccionar una ficha dentro de las fichas que tiene el jugador en la mano (le entra el jugador y retorna la ficha seleccionada)
	//Funcion que permite que un jugador ingrese las posiciones a mover (Es la que se va a llamar desde la vista, ingresa x1,y1,x2,y2)
	//Funcion que valida si un movimiento es valido
	//Funcion que genere, de la bolsa de fichas, una nueva ficha
	//Funcion que llene la bolsa de fichas segun la distribucion que hay en el pdf
}
