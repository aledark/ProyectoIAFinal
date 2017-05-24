package modelo;
import java.util.ArrayList;

import java.util.ArrayList;
import java.util.Random;
import modelo.Ficha;

public class Tablero{
    Jugador j1,j2;
    ArrayList<ArrayList<Ficha>> tablero;
    boolean gameOver;
    boolean turno;
    ArrayList<Ficha> bolsaFichas;
    ArrayList<Ficha> fichasEnTablero;

    public Tablero(Jugador j1, Jugador j2){
        this.j1 = j1;
        this.j2 = j2;
        gameOver = false;
        turno = false;
        bolsaFichas = new ArrayList<Ficha>();
        tablero = new ArrayList<ArrayList<Ficha>>();
        fichasEnTablero = new ArrayList<Ficha>();
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
        
        //tablero.get(10).get(5).setColor(4);
        tablero.get(10).get(2).setColor(4);
        tablero.get(10).get(1).setColor(4);
        tablero.get(10).get(3).setColor(4);
        tablero.get(9).get(4).setColor(4);
        tablero.get(9).get(0).setColor(4);
    }

  	//Funciones:
  	//Funcion principal (se manejan los turnos y la jugada)
    public void juego(){
        llenarBolsa();
        inicializarManos();
            /*while(!gameOver){
                    if(turno == true){
                            Ficha fichaJugada = new Ficha(); //= seleccionarFicha(j1);
                            int posiciones[] = null;//ingresarTurno();
                            fichaJugada = j1.mover(fichaJugada, posiciones);
                            j1.agregarFicha(entregarFichaBolsa());
                            actualizarTablero(fichaJugada);
                            fichasEnTablero.add(fichaJugada);
                            j1.actualizarPuntaje(fichaJugada.getColor(), calcularPuntaje(fichaJugada));
                            j1.actualizarPuntaje(fichaJugada.getPareja().getColor(), calcularPuntaje(fichaJugada.getPareja()));
                            validarGameOver();
                            turno = !turno;
                    }
                    else { //Lo mismo pero con j2
                        
                    }
            }*/
        
    }
    
    //Inicializar manos de los jugadores
    public void inicializarManos(){
        for(int i = 0; i < 6; i++){
            j1.agregarFicha(entregarFichaBolsa());
            j2.agregarFicha(entregarFichaBolsa());
        }
    }
    //Validar gameover
    public void validarGameOver(){
        boolean respuesta = false;
        for(int i = 0; i < tablero.size() && !respuesta; i++){
            for(int j = 0; j < tablero.get(i).size() && !respuesta; i++){
                if(tablero.get(i).get(j).getColor() == 0){
                    if(validarEspacio(tablero.get(i).get(j))) respuesta = true;
                }
            }
        }
    }
    
    public boolean validarEspacio(Ficha ficha){
        int fila = ficha.getFila();
        int columna = ficha.getColumna();
        if(fila <=4){
            //Revisar superior izquierda
            if(fila > 0 && columna > 0){    
                if(tablero.get(fila-1).get(columna-1).getColor()==0) return true;
            }
            //Revisar superior derecha
            if(fila > 0 && columna < tablero.get(fila).size()){
                if(tablero.get(fila-1).get(columna).getColor() == 0) return true;
            }
            //Revisar Izquierda
            if(columna > 0){
                if(tablero.get(fila).get(columna-1).getColor() == 0) return true;
            }
            //Revisar inferior izquierda
            if(fila < tablero.size() && columna < tablero.get(fila).size()){
                if(tablero.get(fila+1).get(columna).getColor() == 0) return true;
            }
            //Revisar inferior derecha
            if(fila < tablero.size() && columna < tablero.get(fila).size()){
                if(tablero.get(fila+1).get(columna+1).getColor() == 0) return true;
            }
            //Revisar  derecha
            if(columna < tablero.get(fila).size()){
                if(tablero.get(fila).get(columna).getColor() == 0) return true;
            }
        }
        if(fila >= 6){
            //Revisar superior izquierda
            if(fila > 0 && columna > 0){
                if(tablero.get(fila-1).get(columna).getColor() == 0) return true;
            }
            //Revisar superior derecha
            if(fila > 0 && columna < tablero.get(fila).size()){
                if(tablero.get(fila-1).get(columna+1).getColor() == 0) return true;
            }
            //Revisar Izquierda
            if(columna > 0){
                if(tablero.get(fila).get(columna-1).getColor() == 0) return true;
            }
            //Revisar inferior izquierda
            if(fila < tablero.size() && columna > 0){
                if(tablero.get(fila+1).get(columna-1).getColor() == 0) return true;
            }
            //Revisar inferior derecha
            if(fila < tablero.size() && columna < tablero.get(fila).size()){
                if(tablero.get(fila+1).get(columna).getColor() == 0) return true;
            }
            //Revisar  derecha
            if(columna < tablero.get(fila).size()){
                if(tablero.get(fila).get(columna+1).getColor() == 0) return true;
            }
        }
        else{
            //Revisar superior izquierda
            if(fila > 0 && columna > 0){    
                if(tablero.get(fila-1).get(columna-1).getColor()==0) return true;
            }
            //Revisar superior derecha
            if(fila > 0 && columna < tablero.get(fila).size()){
                if(tablero.get(fila-1).get(columna).getColor() == 0) return true;
            }
            //Revisar Izquierda
            if(columna > 0){
                if(tablero.get(fila).get(columna-1).getColor() == 0) return true;
            }
            //Revisar inferior izquierda
            if(fila < tablero.size() && columna > 0){
                if(tablero.get(fila+1).get(columna-1).getColor() == 0) return true;
            }
            //Revisar inferior derecha
            if(fila < tablero.size() && columna < tablero.get(fila).size()){
                if(tablero.get(fila+1).get(columna).getColor() == 0) return true;
            }
            //Revisar  derecha
            if(columna < tablero.get(fila).size()){
                if(tablero.get(fila).get(columna+1).getColor() == 0) return true;
            }
        }
        return false;
    }

    //Obtener jugador ganador
    public Jugador jugadorGanador(){
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

  //Setters y getters
    public Jugador getJ1() {
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

     //Funcion que permite seleccionar una ficha dentro de las fichas que tiene el jugador en la mano (le entra el jugador y retorna la ficha seleccionada)
    

    //Funcion que permite obtener las fichas que tiene en la mano un jugador
    public ArrayList<Ficha> getFichasJugador(int j){
        if(j == 1) return j1.getFichasActuales();
        else return j2.getFichasActuales();
    }
    
    //Funcion que permite obtener los puntos de un jugador
    public int[] getPuntosJugador(int j){
        if(j == 1) return j1.getPuntosColor();
        else return j2.getPuntosColor();
    }
    
    //Funcion que permite que un jugador ingrese las posiciones a mover (Es la que se va a llamar desde la vista, ingresa x1,y1,x2,y2) (cuando haya vista, esto va en la vista)

    //Funcion que entregue, de la bolsa de fichas, una nueva ficha a un jugador
    public Ficha entregarFichaBolsa(){
        Random rand = new Random(); 
        Ficha ficha = bolsaFichas.get((int)(rand.nextDouble()*bolsaFichas.size()));
        bolsaFichas.remove(ficha);
        return ficha;
    }
    
    //Funcion que llene la bolsa de fichas segun la distribucion que hay en el pdf
    // El número depende del color, por ejemplo: 1 = Amarillo, 2 = Morado, 3 = Azul, 4 = Rojo
    // 5 = Naranja y 6 = Verde
    public void llenarBolsa(){
        // Fichas del mismo simbolo
        for(int i = 0; i < 5; i++){  
            bolsaFichas.add(auxCrearPareja(1,1));//Amarillas 
            bolsaFichas.add(auxCrearPareja(2,2));//Moradas
            bolsaFichas.add(auxCrearPareja(3,3)); //Azules
            bolsaFichas.add(auxCrearPareja(4,4));//Rojas 
            bolsaFichas.add(auxCrearPareja(5,5));//Naranjas
            bolsaFichas.add(auxCrearPareja(6,6));//Verdes
        }
        for(int i = 0; i < 6; i++){
            //Rojo y otro color
            bolsaFichas.add(auxCrearPareja(4,1));//Amarilla
            bolsaFichas.add(auxCrearPareja(4,2));//Morada
            bolsaFichas.add(auxCrearPareja(4,3)); //Azul
            bolsaFichas.add(auxCrearPareja(4,5));//Naranja
            bolsaFichas.add(auxCrearPareja(4,6));//Verde
            //Azul y otro color
            bolsaFichas.add(auxCrearPareja(3,1));//Amarilla
            bolsaFichas.add(auxCrearPareja(3,2));//Morada
            bolsaFichas.add(auxCrearPareja(3,5));//Naranja
            bolsaFichas.add(auxCrearPareja(3,6));//Verde
            //Verde y otro color
            bolsaFichas.add(auxCrearPareja(6,1));//Amarilla
            bolsaFichas.add(auxCrearPareja(6,2));//Morada
            bolsaFichas.add(auxCrearPareja(6,5));//Naranja
            //Amarillo y otro color
            bolsaFichas.add(auxCrearPareja(1,2));//Morada
            bolsaFichas.add(auxCrearPareja(1,5));//Naranja
            //Naranja y morado
            bolsaFichas.add(auxCrearPareja(2,5));//Naranja
        }
    }

    public Ficha auxCrearPareja(int color1, int color2){
        Ficha ficha1 = new Ficha(-1,-1,null, color1);
        Ficha ficha2 = new Ficha(-1,-1,ficha1, color2);
        ficha1.setPareja(ficha2);
        return ficha1;
    }

    private int calcularPuntaje(Ficha fichaJugada) {
        int fila = fichaJugada.getFila();
        int columna = fichaJugada.getColumna();
        int puntos = 0;
        if(fila <=4){
             //Revisar superior izquierda
            int filaAux = fila-1;
            int columnaAux = columna-1;
            while(filaAux >= 0 && columna >= 0){
                if(tablero.get(filaAux).get(columnaAux).getColor() == fichaJugada.getColor()){
                    puntos++;
                    filaAux--;
                    columnaAux--;
                }
                else break;
            }
            //Revisar superior derecha
            filaAux = fila-1;
            columnaAux = columna;
            while(filaAux >= 0 && columnaAux < tablero.get(filaAux).size()){
                if(tablero.get(filaAux).get(columnaAux).getColor() == fichaJugada.getColor()){
                    puntos++;
                    filaAux--;
                }
                else break;
            }
            //Revisar Izquierda
            filaAux = fila;
            columnaAux = columna-1;
             while(columnaAux >= 0){
                if(tablero.get(filaAux).get(columnaAux).getColor() == fichaJugada.getColor()){
                    puntos++;
                    columnaAux--;
                }
                else break;
            }
            //Revisar inferior izquierda
            filaAux = fila+1;
            columnaAux = columna;
             while(filaAux < tablero.size() && columnaAux < tablero.get(filaAux).size()){
                if(tablero.get(filaAux).get(columnaAux).getColor() == fichaJugada.getColor()){
                    puntos++;
                    filaAux++;
                }
                else break;
            }
            //Revisar inferior derecha
            filaAux = fila+1;
            columnaAux = columna+1;
             while(filaAux < tablero.size() && columnaAux < tablero.get(filaAux).size()){
                if(tablero.get(filaAux).get(columnaAux).getColor() == fichaJugada.getColor()){
                    puntos++;
                    filaAux++;
                    columnaAux++;
                }
                else break;
            }
            //Revisar  derecha
            filaAux = fila;
            columnaAux = columna+1;
             while(columnaAux < tablero.get(filaAux).size()){
                if(tablero.get(filaAux).get(columnaAux).getColor() == fichaJugada.getColor()){
                    puntos++;
                    columnaAux++;
                }
                else break;
            }
        }
        else if(fila >=6){
            //Revisar superior izquierda
            int filaAux = fila-1;
            int columnaAux = columna;
            while(filaAux >= 0 && columna >= 0){
                if(tablero.get(filaAux).get(columnaAux).getColor() == fichaJugada.getColor()){
                    puntos++;
                    filaAux--;
                }
                else break;
            }
            //Revisar superior derecha
            filaAux = fila-1;
            columnaAux = columna+1;
            while(filaAux >= 0 && columnaAux < tablero.get(filaAux).size()){
                if(tablero.get(filaAux).get(columnaAux).getColor() == fichaJugada.getColor()){
                    puntos++;
                    filaAux--;
                    columnaAux++;
                }
                else break;
            }
            //Revisar Izquierda
            filaAux = fila;
            columnaAux = columna-1;
             while(columnaAux >= 0){
                if(tablero.get(filaAux).get(columnaAux).getColor() == fichaJugada.getColor()){
                    puntos++;
                    columnaAux--;
                }
                else break;
            }
            //Revisar inferior izquierda
            filaAux = fila+1;
            columnaAux = columna-1;
             while(filaAux < tablero.size() && columnaAux >= 0){
                if(tablero.get(filaAux).get(columnaAux).getColor() == fichaJugada.getColor()){
                    puntos++;
                    filaAux++;
                    columnaAux--;
                }
                else break;
            }
            //Revisar inferior derecha
            filaAux = fila+1;
            columnaAux = columna;
             while(filaAux < tablero.size() && columnaAux < tablero.get(filaAux).size()){
                if(tablero.get(filaAux).get(columnaAux).getColor() == fichaJugada.getColor()){
                    puntos++;
                    filaAux++;
                }
                else break;
            }
            //Revisar  derecha
            filaAux = fila;
            columnaAux = columna+1;
             while(columnaAux < tablero.get(filaAux).size()){
                if(tablero.get(filaAux).get(columnaAux).getColor() == fichaJugada.getColor()){
                    puntos++;
                    columnaAux++;
                }
                else break;
            }
        }
        else{
             //Revisar superior izquierda
            int filaAux = fila-1;
            int columnaAux = columna-1;
            while(filaAux >= 0 && columna >= 0){
                if(tablero.get(filaAux).get(columnaAux).getColor() == fichaJugada.getColor()){
                    puntos++;
                    filaAux--;
                    columnaAux--;
                }
                else break;
            }
            //Revisar superior derecha
            filaAux = fila-1;
            columnaAux = columna;
            while(filaAux >= 0 && columnaAux < tablero.get(filaAux).size()){
                if(tablero.get(filaAux).get(columnaAux).getColor() == fichaJugada.getColor()){
                    puntos++;
                    filaAux--;
                }
                else break;
            }
            //Revisar Izquierda
            filaAux = fila;
            columnaAux = columna-1;
             while(columnaAux >= 0){
                if(tablero.get(filaAux).get(columnaAux).getColor() == fichaJugada.getColor()){
                    puntos++;
                    columnaAux--;
                }
                else break;
            }
            //Revisar inferior izquierda
            filaAux = fila+1;
            columnaAux = columna-1;
             while(filaAux < tablero.size() && columnaAux >= 0){
                if(tablero.get(filaAux).get(columnaAux).getColor() == fichaJugada.getColor()){
                    puntos++;
                    filaAux++;
                    columnaAux--;
                }
                else break;
            }
            //Revisar inferior derecha
            filaAux = fila+1;
            columnaAux = columna;
             while(filaAux < tablero.size() && columnaAux < tablero.get(filaAux).size()){
                if(tablero.get(filaAux).get(columnaAux).getColor() == fichaJugada.getColor()){
                    puntos++;
                    filaAux++;
                }
                else break;
            }
            //Revisar  derecha
            filaAux = fila;
            columnaAux = columna+1;
             while(columnaAux < tablero.get(filaAux).size()){
                if(tablero.get(filaAux).get(columnaAux).getColor() == fichaJugada.getColor()){
                    puntos++;
                    columnaAux++;
                }
                else break;
            }
        }
       
        return puntos;
    }

    private void actualizarTablero(Ficha fichaJugada) {
        tablero.get(fichaJugada.getFila()).get(fichaJugada.getColumna()).setColor(fichaJugada.getColor());
        tablero.get(fichaJugada.getFila()).get(fichaJugada.getColumna()).setPareja(fichaJugada.getPareja());
        tablero.get(fichaJugada.getPareja().getFila()).get(fichaJugada.getPareja().getColumna()).setColor(fichaJugada.getPareja().getColor());
        tablero.get(fichaJugada.getPareja().getFila()).get(fichaJugada.getPareja().getColumna()).setPareja(fichaJugada);
    }
    
    public void prueba(Ficha f){
        System.out.println(calcularPuntaje(f));
    }
    
   /* public static void main(String args[]){
        Tablero t = new Tablero(null, null);
        Ficha f = new Ficha(10, 0, null, 4);
        t.prueba(f);
        
    }*/
}