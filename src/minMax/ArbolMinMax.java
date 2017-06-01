package minMax;

import java.util.ArrayList;
import modelo.Ficha;

public class ArbolMinMax {
    Nodo raiz;
    ArrayList<Nodo> colaPrioridad;//Cola de prioridad por profundidad
    Nodo actual;
    ArrayList<ArrayList<Ficha>> tablero;
    int limiteProfundidad;
    int fila;
    int columna;
    Ficha fichaRespuesta;
    ArrayList<Ficha> fichasManoIA;
    ArrayList<Ficha> fichasManoJ;
    int puntosColorIA[];
    int puntosColorJ[];
    
    
    public ArbolMinMax(ArrayList<ArrayList<Ficha>> tablero, int limiteProfundidad,  ArrayList<Ficha> fichasManoIA, ArrayList<Ficha> fichasManoJ,
            int puntosColorIA[], int puntosColorJ[]){
        this.tablero = tablero;
        raiz = new Nodo();
        raiz.setProfundidad(0);
        raiz.setTablero(tablero);
        colaPrioridad = new ArrayList<Nodo>();
        colaPrioridad.add(raiz);
        this.limiteProfundidad = limiteProfundidad;
        this.fichasManoIA = fichasManoIA;
        this.fichasManoJ = fichasManoJ;
        this.puntosColorIA = puntosColorIA;
        this.puntosColorJ = puntosColorJ;
    }

    public void expandir(Nodo nodo){
        /*if(nodo.getProfundidad() == limiteProfundidad){
            //Calcular utilidad y pasarla al padre
            //Asiganr boolean revisado como true
        }else{
            //Generar todos los hijos del nodo e ingresarlos a la cola
            //Si el nodo es Max se generan los hijos segun la baraja de la IA en mano, de otro modo, con la baraja de la persona
            //Se hereda la lista de los padres y se agrega la ficha del nodo a las fichas usadas 
            //Se crea una matriz auxiliar con la jugada posible y se guarda en el nodo
            //La profundidad de estos nodos es la profundidad del padre mas uno
            //Al momento de crearse los hijos hay que actualizar el puntero del padre
        }*/
        if(nodo.getProfundidad() == 0){
            //Se expanden las alternativas de MAX
        }else if(nodo.getProfundidad() == 1){
            //Se expanden las alternativas de MIN
        }else{
            //Se calcula la utilidad y se envia al padre
            //Pasar variable revisado a true
            //Si el contador del padre es 5 entonces actualizar utlidad al abuelo
        }
    }
    public void funcionPrincipal(){
        actual = colaPrioridad.remove(0);
        expandir(actual);//Expandir nodo
        while(colaPrioridad.size() > 0){
            actual = colaPrioridad.remove(0);
            expandir(actual);
        }
        //Recorrer los nodos que tienen la variable revisado en false buscando pasando el valor a la raiz
        fila = raiz.getFila();
        columna = raiz.getColumna();
        fichaRespuesta = raiz.getFicha();
    }
    
    public Nodo getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }

    public ArrayList<Nodo> getColaPrioridad() {
        return colaPrioridad;
    }

    public void setColaPrioridad(ArrayList<Nodo> colaPrioridad) {
        this.colaPrioridad = colaPrioridad;
    }

    public Nodo getActual() {
        return actual;
    }

    public void setActual(Nodo actual) {
        this.actual = actual;
    }

    public ArrayList<ArrayList<Ficha>> getTablero() {
        return tablero;
    }

    public void setTablero(ArrayList<ArrayList<Ficha>> tablero) {
        this.tablero = tablero;
    }

    public int getLimiteProfundidad() {
        return limiteProfundidad;
    }

    public void setLimiteProfundidad(int limiteProfundidad) {
        this.limiteProfundidad = limiteProfundidad;
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

    public Ficha getFichaRespuesta() {
        return fichaRespuesta;
    }

    public void setFichaRespuesta(Ficha fichaRespuesta) {
        this.fichaRespuesta = fichaRespuesta;
    }
}
