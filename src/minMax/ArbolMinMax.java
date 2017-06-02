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

    
    public ArbolMinMax(ArrayList<ArrayList<Ficha>> tablero, int limiteProfundidad,  ArrayList<Ficha> fichasManoIA, ArrayList<Ficha> fichasManoJ,
        int puntosColorIA[], int puntosColorJ[]){
        this.tablero = tablero;
        raiz = new Nodo();
        raiz.setProfundidad(0);
        raiz.setTablero(tablero);
        raiz.setPuntosColorIA(puntosColorIA);
        raiz.setPuntosColorJ(puntosColorJ);
        colaPrioridad = new ArrayList<Nodo>();
        colaPrioridad.add(raiz);
        this.limiteProfundidad = limiteProfundidad;
        this.fichasManoIA = fichasManoIA;
        this.fichasManoJ = fichasManoJ;
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
            ArrayList<int[]> alternativas = posiblesPosiciones(raiz.getTablero());
            for (int i = 0; i < alternativas.size(); i++) {
                for (int j = 0; j < fichasManoIA.size(); j++) {
                    //Se crea el nodo
                    Nodo nodoNuevo = new Nodo();
                    //Se crea copia de la matriz
                    ArrayList<ArrayList<Ficha>> nuevoTablero = (ArrayList<ArrayList<Ficha>>) raiz.getTablero().clone();
                    //Se actualiza el color y la posicion de la ficha
                    fichasManoIA.get(j).setFila(alternativas.get(i)[0]);
                    fichasManoIA.get(j).setColumna(alternativas.get(i)[1]);
                    fichasManoIA.get(j).getPareja().setFila(alternativas.get(i)[2]);
                    fichasManoIA.get(j).getPareja().setColumna(alternativas.get(i)[3]);
                    
                     //Se crea copia del puntje y se actualiza la copia con el nuevo puntaje
                    nodoNuevo.setTablero(nuevoTablero);
                    nodoNuevo.setPuntosColorIA(raiz.getPuntosColorIA().clone());
                    nodoNuevo.setPuntosColorJ(raiz.getPuntosColorJ().clone());
                    int n = nodoNuevo.actualizarPuntajeFicha(fichasManoIA.get(j));
                    int n1 = nodoNuevo.actualizarPuntajeFicha( fichasManoIA.get(j).getPareja());
                    
                    nodoNuevo.actualizarPuntajeIA(fichasManoIA.get(j).getColor(), n);
                    nodoNuevo.actualizarPuntajeIA(fichasManoIA.get(j).getPareja().getColor(), n1);
                    //Se almacena en el tablero
                    nuevoTablero.get(alternativas.get(i)[0]).get(alternativas.get(i)[1]).setColor(fichasManoIA.get(j).getColor());
                    nuevoTablero.get(alternativas.get(i)[0]).get(alternativas.get(i)[1]).setFila(alternativas.get(i)[0]);
                    nuevoTablero.get(alternativas.get(i)[0]).get(alternativas.get(i)[1]).setColumna(alternativas.get(i)[1]);
                    nuevoTablero.get(alternativas.get(i)[0]).get(alternativas.get(i)[1]).setPareja(tablero.get(alternativas.get(i)[2]).get(alternativas.get(i)[3]));            
                    nuevoTablero.get(alternativas.get(i)[2]).get(alternativas.get(i)[3]).setColor(fichasManoIA.get(j).getPareja().getColor());
                    nuevoTablero.get(alternativas.get(i)[2]).get(alternativas.get(i)[3]).setFila(alternativas.get(i)[2]);
                    nuevoTablero.get(alternativas.get(i)[2]).get(alternativas.get(i)[3]).setColumna(alternativas.get(i)[3]);
                    nuevoTablero.get(alternativas.get(i)[2]).get(alternativas.get(i)[3]).setPareja(tablero.get(alternativas.get(i)[0]).get(alternativas.get(i)[1]));

                     //Se setea la ficha en el nodo (posicion en el arreglo de fichas en mano) y se setea la posicion de la ficha
                    nodoNuevo.setFila(alternativas.get(i)[0]);
                    nodoNuevo.setColumna(alternativas.get(i)[1]);
                    nodoNuevo.setFila2(alternativas.get(i)[2]);
                    nodoNuevo.setColumna2(alternativas.get(i)[3]);

                    //Se setea la profundidad
                    nodoNuevo.setProfundidad(1);
                    //Se actualiza puntero al padre
                    nodoNuevo.setPadre(raiz);
                    //Se setea el tipo de nodo
                    nodoNuevo.setTipoNodo(2);
                    //Se agreaga el nuevo hijo al padre
                    raiz.agregarHijo(nodoNuevo);
                }
            }
            
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
        //fichaRespuesta = raiz.getFicha();
    }
    
    public ArrayList<int[]> posiblesPosiciones(ArrayList<ArrayList<Ficha>> t) {
        ArrayList<int[]> respuesta = new ArrayList<int[]>();
        for(int i = 0; i < t.size(); i++){
            for (int j = 0; j < t.get(i).size(); j++) {
                if(t.get(i).get(j).getColor() == 0){
                    validarEspacio(t.get(i).get(j), t, respuesta);
                }
            }
        }
        return respuesta;
    }
    
    public void validarEspacio(Ficha ficha, ArrayList<ArrayList<Ficha>> tablero, ArrayList<int[]> respuesta){
        int fila = ficha.getFila();
        int columna = ficha.getColumna();
        int posiciones [] = new int[4];
        if(fila <= 4){
            //Revisar superior izquierda
            if(fila > 0 && columna > 0){    
                if(tablero.get(fila-1).get(columna-1).getColor()==0){
                    posiciones[0] = fila-1;
                    posiciones[1] = columna-1;
                    posiciones[2] = fila;
                    posiciones[3] = columna;
                    respuesta.add(posiciones);
                }
            }
            //Revisar superior derecha
            if(fila > 0){
                if(columna < tablero.get(fila-1).size()){
                     if(tablero.get(fila-1).get(columna).getColor() == 0){
                        posiciones[0] = fila-1;
                        posiciones[1] = columna;
                        posiciones[2] = fila;
                        posiciones[3] = columna;
                        respuesta.add(posiciones);
                    }
                }
            }
            //Revisar Izquierda
            if(columna > 0){
                if(tablero.get(fila).get(columna-1).getColor() == 0){
                    posiciones[0] = fila;
                    posiciones[1] = columna-1;
                    posiciones[2] = fila;
                    posiciones[3] = columna;
                    respuesta.add(posiciones);
                }
            }
            //Revisar inferior izquierda
            if(fila+1 < tablero.size() && columna < tablero.get(fila).size()){
                if(tablero.get(fila+1).get(columna).getColor() == 0){
                    posiciones[0] = fila+1;
                    posiciones[1] = columna;
                    posiciones[2] = fila;
                    posiciones[3] = columna;
                    respuesta.add(posiciones);
                }
            }
            //Revisar inferior derecha
            if(fila+1 < tablero.size() && columna < tablero.get(fila).size()){
                if(tablero.get(fila+1).get(columna+1).getColor() == 0){
                    posiciones[0] = fila+1;
                    posiciones[1] = columna+1;
                    posiciones[2] = fila;
                    posiciones[3] = columna;
                    respuesta.add(posiciones);
                }
            }
            //Revisar  derecha
            if(columna+1 < tablero.get(fila).size()){
                if(tablero.get(fila).get(columna+1).getColor() == 0){
                    posiciones[0] = fila;
                    posiciones[1] = columna+1;
                    posiciones[2] = fila;
                    posiciones[3] = columna;
                    respuesta.add(posiciones);
                }
            }
        }
        if(fila >= 6){
            //Revisar superior izquierda
            if(fila > 0 && columna > 0){
                if(tablero.get(fila-1).get(columna).getColor() == 0){
                    posiciones[0] = fila-1;
                    posiciones[1] = columna;
                    posiciones[2] = fila;
                    posiciones[3] = columna;
                    respuesta.add(posiciones);
                }
            }
            //Revisar superior derecha
            if(fila > 0 && columna < tablero.get(fila).size()){
                if(columna+1 < tablero.get(fila-1).size()){
                    if(tablero.get(fila-1).get(columna+1).getColor() == 0){
                        posiciones[0] = fila-1;
                        posiciones[1] = columna+1;
                        posiciones[2] = fila;
                        posiciones[3] = columna;
                        respuesta.add(posiciones);
                    }
                }
            }
            //Revisar Izquierda
            if(columna > 0){
                if(tablero.get(fila).get(columna-1).getColor() == 0){
                    posiciones[0] = fila;
                    posiciones[1] = columna-1;
                    posiciones[2] = fila;
                    posiciones[3] = columna;
                    respuesta.add(posiciones);
                }
            }
            //Revisar inferior izquierda
            if(fila+1 < tablero.size() && columna > 0){
                if(tablero.get(fila+1).get(columna-1).getColor() == 0){
                    posiciones[0] = fila+1;
                    posiciones[1] = columna-1;
                    posiciones[2] = fila;
                    posiciones[3] = columna;
                    respuesta.add(posiciones);
                }
            }
            //Revisar inferior derecha
            if(fila+1 < tablero.size()){
                if(columna < tablero.get(fila+1).size()){
                    if(tablero.get(fila+1).get(columna).getColor() == 0){
                        posiciones[0] = fila+1;
                        posiciones[1] = columna;
                        posiciones[2] = fila;
                        posiciones[3] = columna;
                        respuesta.add(posiciones);
                    }
                }
            }
            //Revisar  derecha
            if(columna+1 < tablero.get(fila).size()){
                if(tablero.get(fila).get(columna+1).getColor() == 0) {
                    posiciones[0] = fila;
                    posiciones[1] = columna+1;
                    posiciones[2] = fila;
                    posiciones[3] = columna;
                    respuesta.add(posiciones);
                }
            }
        }
        else{
            //Revisar superior izquierda
            if(fila > 0 && columna > 0){    
                if(tablero.get(fila-1).get(columna-1).getColor()==0){
                    posiciones[0] = fila-1;
                    posiciones[1] = columna-1;
                    posiciones[2] = fila;
                    posiciones[3] = columna;
                    respuesta.add(posiciones);
                }
            }
            //Revisar superior derecha
            if(fila > 0){
                if(columna < tablero.get(fila-1).size()){
                    if(tablero.get(fila-1).get(columna).getColor() == 0){
                        posiciones[0] = fila-1;
                        posiciones[1] = columna;
                        posiciones[2] = fila;
                        posiciones[3] = columna;
                        respuesta.add(posiciones);
                    }
                }
            }
            //Revisar Izquierda
            if(columna > 0){
                if(tablero.get(fila).get(columna-1).getColor() == 0){
                    posiciones[0] = fila;
                    posiciones[1] = columna-1;
                    posiciones[2] = fila;
                    posiciones[3] = columna;
                    respuesta.add(posiciones);
                }
            }
            //Revisar inferior izquierda
            if(fila+1 < tablero.size() && columna > 0){
                if(tablero.get(fila+1).get(columna-1).getColor() == 0){
                    posiciones[0] = fila+1;
                    posiciones[1] = columna-1;
                    posiciones[2] = fila;
                    posiciones[3] = columna;
                    respuesta.add(posiciones);
                }
            }
            //Revisar inferior derecha
            if(fila+1 < tablero.size() && columna < tablero.get(fila).size()){
                if(columna < tablero.get(fila+1).size()){
                    if(tablero.get(fila+1).get(columna).getColor() == 0){
                        posiciones[0] = fila+1;
                        posiciones[1] = columna;
                        posiciones[2] = fila;
                        posiciones[3] = columna;
                        respuesta.add(posiciones);
                    }
                }
            }
            //Revisar  derecha
            if(columna+1 < tablero.get(fila).size()){
                if(tablero.get(fila).get(columna+1).getColor() == 0){
                    posiciones[0] = fila;
                    posiciones[1] = columna+1;
                    posiciones[2] = fila;
                    posiciones[3] = columna;
                    respuesta.add(posiciones);
                }
            }
        }
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
