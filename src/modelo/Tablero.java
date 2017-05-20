package modelo;
import java.util.ArrayList;

import java.util.ArrayList;
import modelo.Ficha;

public class Tablero{
  Jugador j1,j2;
  ArrayList<ArrayList<Ficha>> tablero;
  boolean gameOver;
  boolean turno;
  ArraList<Ficha> bolsaFichas;

  public Tablero(){
    gameOver = false;
    turno = false;
    bolsaFichas = new ArrayList<Ficha>();
  }
  public Tablero(Jugador j1, Jugador j2){
    this.j1 = j1;
    this.j2 = j2;
    tablero = new ArrayList<ArrayList<Ficha>>();

    for (int i = 0; i < 11; i++) {
      tablero.add(new ArrayList<Ficha>());
    }

    for (int i=0;i<11;i++ ) {
      if(i<6){
        for (int j=0;j<i+6;j++) {
          if(i==0 && j==0){
            tablero.get(i).add(new Ficha(i,j,null,1));
          }else if(i==0 && j==5){
            tablero.get(i).add(new Ficha(i,j,null,2));
          }else if(i==5 && j==0){
            tablero.get(i).add(new Ficha(i,j,null,3));
          }else if(i==5 && j==10){
            tablero.get(i).add(new Ficha(i,j,null,4));
          }else{
            tablero.get(i).add(new Ficha(i,j,null,0));
          }
        }
      }else{
        for (int j=0;j<16-i;j++) {
          if(i==10 && j==0){
            tablero.get(i).add(new Ficha(i,j,null,5));
          }else if(i==10 && j==5){
            tablero.get(i).add(new Ficha(i,j,null,6));
          }else{
            tablero.get(i).add(new Ficha(i,j,null,0));
          }
        }
      }
    }
  }

	//Funciones:
	//Funcion principal (se manejan los turnos y la jugada)
	void juego(){
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
	}
	//Validar puntos obtenidos dado una jugada (ficha obtenida del metodo mover en jugador)
	//Validar gameover
	//Obtener jugador ganador
	//Setters y getters
	//Funcion que permite seleccionar una ficha dentro de las fichas que tiene el jugador en la mano (le entra el jugador y retorna la ficha seleccionada)
	//Funcion que permite que un jugador ingrese las posiciones a mover (Es la que se va a llamar desde la vista, ingresa x1,y1,x2,y2)
	//Funcion que valida si un movimiento es valido
	//Funcion que genere, de la bolsa de fichas, una nueva ficha
	//Funcion que llene la bolsa de fichas segun la distribucion que hay en el pdf 
}
