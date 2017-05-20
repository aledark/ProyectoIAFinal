package modelo;
import java.util.ArrayList;

public class Tablero{
	ArrayList<ArrayList<Ficha>> tablero; 
	ArrayList<Ficha> fichasBolsa;
	Jugador j1;
	Jugador j2;
	boolean gameover;
	boolean turno;

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
