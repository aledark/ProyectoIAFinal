package minMax;

import java.util.ArrayList;
import modelo.Ficha;

public class Nodo {
    ArrayList<ArrayList<Ficha>> tablero;
    int profundidad;
    Nodo padre;
    ArrayList<Nodo> hijos;

    public void setFichasUsadas(ArrayList<Ficha> fichasUsadas) {
        this.fichasUsadas = fichasUsadas;
    }
    int puntajeUtilidad;
    int tipoNodo; //Min o Max
    int fila;
    int columna;
    boolean revisado;
    Ficha ficha;
    ArrayList<Ficha> fichasUsadas;
    int contador = 0;
    
    public Nodo(){
        tablero = new  ArrayList<ArrayList<Ficha>>();
        profundidad = 0;
        padre = null;
        hijos = new ArrayList<Nodo>();
        puntajeUtilidad = 0;
        tipoNodo = 0; //Nodo no inicializado con tipo, max es 1 y min es 2
        revisado = false; 
        fila = 0;
        columna = 0;
        ficha = new Ficha();
        fichasUsadas = new ArrayList<Ficha>();
        
    }
    
    public void agregarHijo(Nodo hijo){
        hijos.add(hijo);
    }
    
    public void actualizarPuntaje(int puntaje, int fila, int columna, Ficha ficha){
        contador++;
        if(tipoNodo == 1){
            if(puntaje > puntajeUtilidad){
                puntajeUtilidad = puntaje;
                this.fila = fila;
                this.columna = columna;
                this.ficha = ficha;
            }
        }else{
            if(puntaje < puntajeUtilidad){
                puntajeUtilidad = puntaje;
                this.fila = fila;
                this.columna = columna;
                this.ficha = ficha;
            }
        }
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

    public Ficha getFicha() {
        return ficha;
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }

    public ArrayList<Ficha> getFichasUsadas() {
        return fichasUsadas;
    }

    public void agregarFichaUsada(Ficha ficha){
        fichasUsadas.add(ficha);
    }
    
    public ArrayList<ArrayList<Ficha>> getTablero() {
        return tablero;
    }

    public void setTablero(ArrayList<ArrayList<Ficha>> tablero) {
        this.tablero = tablero;
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
            puntajeUtilidad = Integer.MIN_VALUE;
        }else{
            puntajeUtilidad = Integer.MAX_VALUE;
        }
        this.tipoNodo = tipoNodo;
    }

    public boolean isRevisado() {
        return revisado;
    }

    public void setRevisado(boolean revisado) {
        this.revisado = revisado;
    }
}
