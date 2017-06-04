package minMax;

import java.util.ArrayList;
import modelo.Ficha;

public class Nodo {
    int profundidad;
    Nodo padre;
    ArrayList<Nodo> hijos;
    int puntajeUtilidad;
    int tipoNodo; //Min o Max
    int ficha;
    int fila;
    int columna;
    int fila2;
    int columna2;
    int fichaJ;
    int filaJ;
    int columnaJ;
    int fila2J;
    int columna2J;
    int puntosColorIA[];
    int puntosColorJ[];
    boolean revisado;
    int contador = 0;
    
    
    public Nodo(){
        profundidad = 0;
        padre = null;
        hijos = new ArrayList<Nodo>();
        puntajeUtilidad = 0;
        tipoNodo = 0; //Nodo no inicializado con tipo, max es 1 y min es 2
        revisado = false; 
        fila = 0;
        columna = 0;
        fila2 = 0;
        columna2 = 0;
        ficha = -1;
        filaJ = 0;
        columnaJ = 0;
        fila2J = 0;
        columna2J = 0;
        fichaJ = -1;
    }
    
    public void actualizarPuntaje(int puntaje, int fila, int columna, int fila2, int columna2, int ficha){
        contador++;
        if(tipoNodo == 1){
            if(puntaje > puntajeUtilidad){
                puntajeUtilidad = puntaje;
                this.fila = fila;
                this.columna = columna;
                this.fila2 = fila2;
                this.columna2 = columna2;
                this.ficha = ficha;
            }
        }else{
            if(puntaje < puntajeUtilidad){
                puntajeUtilidad = puntaje;
                this.fila = fila;
                this.columna = columna;
                this.fila2 = fila2;
                this.columna2 = columna2;
                this.ficha = ficha;
            }
        }
    }

    public int getFichaJ() {
        return fichaJ;
    }

    public void setFichaJ(int fichaJ) {
        this.fichaJ = fichaJ;
    }

    public int getFilaJ() {
        return filaJ;
    }

    public void setFilaJ(int filaJ) {
        this.filaJ = filaJ;
    }

    public int getColumnaJ() {
        return columnaJ;
    }

    public void setColumnaJ(int columnaJ) {
        this.columnaJ = columnaJ;
    }

    public int getFila2J() {
        return fila2J;
    }

    public void setFila2J(int fila2J) {
        this.fila2J = fila2J;
    }

    public int getColumna2J() {
        return columna2J;
    }

    public void setColumna2J(int columna2J) {
        this.columna2J = columna2J;
    }

    public int actualizarPuntajeFicha(Ficha fichaJugada, ArrayList<ArrayList<Ficha>> tablero) {
        int fila = fichaJugada.getFila();
        int columna = fichaJugada.getColumna();
        int puntos = 0;
        boolean fin = false;
        int filaAux = 0;
        int columnaAux = 0;
        for(int i = 0; i < 6; i++){
            filaAux = fila;
            columnaAux = columna;
            fin = false;
            while(!fin){//0 = superior izquierda, 1 = superior derecha, 2 = derecha, 3 = inferior derecha, 4 = inferior izquierda, 5 = izquierda
                if(filaAux <= 4){
                    //Revisar superior izquierda
                    if(i == 0 && filaAux-1 >= 0 && columnaAux-1>=0){
                        if(tablero.get(filaAux-1).get(columnaAux-1).getColor() == fichaJugada.getColor()){
                            puntos++;
                            filaAux--;
                            columnaAux--;
                        }
                        else fin = true;                        
                    }
                    //Revisar superior derecha
                    else if(i == 1 && filaAux-1 >= 0){
                        if(columnaAux < tablero.get(filaAux-1).size()){
                            if(tablero.get(filaAux-1).get(columnaAux).getColor() == fichaJugada.getColor()){
                                filaAux--;
                                puntos++;
                            }
                            else fin = true; 
                        }
                        else fin = true;
                    }
                    //Revisar izquierda
                    else if(i == 5 && columnaAux-1 >= 0){
                        if(tablero.get(filaAux).get(columnaAux-1).getColor() == fichaJugada.getColor()){
                            columnaAux--;
                            puntos++;
                        }
                        else fin = true;
                    }
                    //Revisar inferior izquierda
                    else if(i == 4 && filaAux+1 < tablero.size()){
                        if(columnaAux < tablero.get(filaAux+1).size()){
                            if(tablero.get(filaAux+1).get(columnaAux).getColor() == fichaJugada.getColor()){
                                filaAux++;
                                puntos++;
                            }
                            else fin = true;
                        }
                        else fin = true;
                    }
                    //Revisar inferior derecha
                    else if(i == 3 && filaAux+1 < tablero.size()){
                        if(columnaAux+1 < tablero.get(filaAux+1).size()){
                            if(tablero.get(filaAux+1).get(columnaAux+1).getColor() == fichaJugada.getColor()){
                                filaAux++;
                                columnaAux++;
                                puntos++;
                            }
                            else fin = true;
                        }
                        else fin = true;
                    }
                    //Revisar derecha
                    else if(i == 2 && columnaAux+1 < tablero.get(filaAux).size()){
                        if(tablero.get(filaAux).get(columnaAux+1).getColor() == fichaJugada.getColor()){
                            columnaAux++;
                            puntos++;
                        }
                        else fin = true;
                    }
                    else fin = true;
                }else if(filaAux >= 6){
                    //Revisar superior izquierda
                    if(i == 0 && filaAux-1 >= 0 && columnaAux >= 0){
                        if(tablero.get(filaAux-1).get(columnaAux).getColor() == fichaJugada.getColor()){
                            filaAux--;
                            puntos++;
                        }
                        else fin = true;
                    }
                    //Revisar superior derecha 
                    else if(i == 1 && filaAux-1 >= 0){
                        if(columnaAux+1 < tablero.get(filaAux-1).size()){
                            if(tablero.get(filaAux-1).get(columnaAux+1).getColor() == fichaJugada.getColor()){
                                filaAux--;
                                columnaAux++;
                                puntos++;
                            }
                            else fin = true;
                        }
                        else fin = true;
                    }
                    //Revisar izquierda
                    else if(i == 5 && columnaAux-1 >= 0){
                        if(tablero.get(filaAux).get(columnaAux-1).getColor() == fichaJugada.getColor()){
                            columnaAux--;
                            puntos++;
                        }
                        else fin = true;
                    }
                    //Revisar inferior izquierda
                    else if(i == 4 && filaAux+1 < tablero.size()){
                        if(columnaAux-1 >= 0){
                            if(tablero.get(filaAux+1).get(columnaAux-1).getColor() == fichaJugada.getColor()){
                                filaAux++;
                                columnaAux--;
                                puntos++;
                            }
                            else fin = true;
                        }
                        else fin = true;
                    }
                    //Revisar inferior derecha
                    else if(i == 3 && filaAux+1 < tablero.size()){
                        if(columnaAux < tablero.get(filaAux+1).size()){
                            if(tablero.get(filaAux+1).get(columnaAux).getColor() == fichaJugada.getColor()){
                                filaAux++;
                                puntos++;
                            }
                            else fin = true;
                        }
                        else fin = true;
                    }
                    //Revisar derecha
                    else if(i == 2 && columnaAux+1 < tablero.get(filaAux).size()){
                        if(tablero.get(filaAux).get(columnaAux+1).getColor() == fichaJugada.getColor()){
                            columnaAux++;
                            puntos++;
                        }
                        else fin = true;
                    }
                    else fin = true;
                }else{
                    //Revisar superior izquierda
                    if(i == 0 && filaAux-1 >= 0 && columnaAux-1>=0){
                        if(tablero.get(filaAux-1).get(columnaAux-1).getColor() == fichaJugada.getColor()){
                            puntos++;
                            filaAux--;
                            columnaAux--;
                        }
                        else fin = true;                        
                    }
                    //Revisar superior derecha
                    else if(i == 1 && filaAux-1 >= 0){
                        if(columnaAux < tablero.get(filaAux-1).size()){
                            if(tablero.get(filaAux-1).get(columnaAux).getColor() == fichaJugada.getColor()){
                                filaAux--;
                                puntos++;
                            }
                            else fin = true; 
                        }
                        else fin = true;
                    }
                    //Revisar izquierda
                    else if(i == 5 && columnaAux-1 >= 0){
                        if(tablero.get(filaAux).get(columnaAux-1).getColor() == fichaJugada.getColor()){
                            columnaAux--;
                            puntos++;
                        }
                        else fin = true;
                    }
                    //Revisar inferior izquierda
                    else if(i == 4 && filaAux+1 < tablero.size()){
                        if(columnaAux-1 >= 0){
                            if(tablero.get(filaAux+1).get(columnaAux-1).getColor() == fichaJugada.getColor()){
                                filaAux++;
                                columnaAux--;
                                puntos++;
                            }
                            else fin = true;
                        }
                        else fin = true;
                    }
                    //Revisar inferior derecha
                    else if(i == 3 && filaAux+1 < tablero.size()){
                        if(columnaAux < tablero.get(filaAux+1).size()){
                            if(tablero.get(filaAux+1).get(columnaAux).getColor() == fichaJugada.getColor()){
                                filaAux++;
                                puntos++;
                            }
                            else fin = true;
                        }
                        else fin = true;
                    }
                    //Revisar derecha
                    else if(i == 2 && columnaAux+1 < tablero.get(filaAux).size()){
                        if(tablero.get(filaAux).get(columnaAux+1).getColor() == fichaJugada.getColor()){
                            columnaAux++;
                            puntos++;
                        }
                        else fin = true;
                    }
                    else fin = true;
                }
            }
        }
        return puntos;
    }
    
    public void actualizarPuntajeIA(int color, int puntaje){
        puntosColorIA[color-1] += puntaje;
        if(puntosColorIA[color-1] > 18)  puntosColorIA[color-1] = 18;
    }
    public void actualizarPuntajeJ(int color, int puntaje){
        puntosColorJ[color-1] += puntaje;
        if(puntosColorJ[color-1] > 18)  puntosColorJ[color-1] = 18;
    }
    
    public int getFila2() {
        return fila2;
    }

    public void setFila2(int fila2) {
        this.fila2 = fila2;
    }

    public int getColumna2() {
        return columna2;
    }

    public void setColumna2(int columna2) {
        this.columna2 = columna2;
    }

    public int[] getPuntosColorIA() {
        return puntosColorIA;
    }

    public void setPuntosColorIA(int[] puntosColorIA) {
        this.puntosColorIA = puntosColorIA;
    }

    public int[] getPuntosColorJ() {
        return puntosColorJ;
    }

    public void setPuntosColorJ(int[] puntosColorJ) {
        this.puntosColorJ = puntosColorJ;
    }
    
    public void agregarHijo(Nodo hijo){
        hijos.add(hijo);
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }
    
    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public int getFicha() {
        return ficha;
    }

    public void setFicha(int ficha) {
        this.ficha = ficha;
    }
    
    public int getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(int profundidad) {
        this.profundidad = profundidad;
    }

    public Nodo getPadre() {
        return padre;
    }

    public void setPadre(Nodo padre) {
        this.padre = padre;
    }

    public ArrayList<Nodo> getHijos() {
        return hijos;
    }

    public void setHijos(ArrayList<Nodo> hijos) {
        this.hijos = hijos;
    }

    public int getPuntajeUtilidad() {
        return puntajeUtilidad;
    }

    public void setPuntajeUtilidad(int puntajeUtilidad) {
        this.puntajeUtilidad = puntajeUtilidad;
    }

    public int getTipoNodo() {
        return tipoNodo;
    }

    public void setTipoNodo(int tipoNodo) {
        if(tipoNodo == 1){
            puntajeUtilidad = -1;
        }else{
            puntajeUtilidad = 400;
        }
        this.tipoNodo = tipoNodo;
    }

    public boolean isRevisado() {
        return revisado;
    }

    public void setRevisado(boolean revisado) {
        this.revisado = revisado;
    }

    public int calcularUtilidad() {
        int utilidad = 0;
        int menorIA = 19;
        int menorJ = 19;
        for (int i = 0; i < puntosColorIA.length; i++) {
            utilidad += puntosColorIA[i];
            utilidad -= puntosColorJ[i];
            if(menorIA > puntosColorIA[i]) {
                menorIA = puntosColorIA[i];
            }
            if(menorJ > puntosColorJ[i]){
                menorJ = puntosColorJ[i];
            }
        }
        utilidad = (utilidad/10) +  20*(menorIA-menorJ);
        puntajeUtilidad = utilidad;
        return utilidad;
    }
}
