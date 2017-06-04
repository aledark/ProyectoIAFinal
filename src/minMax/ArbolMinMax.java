package minMax;

import java.util.ArrayList;
import modelo.Ficha;

public class ArbolMinMax {
    Nodo raiz;
    ArrayList<Nodo> colaPrioridad;//Cola de prioridad por profundidad
    Nodo actual;
    int limiteProfundidad;
    int fila;
    int columna;
    Ficha fichaRespuesta;
    ArrayList<Ficha> fichasManoIA;
    ArrayList<Ficha> fichasManoJ;
    ArrayList<ArrayList<Ficha>> tablero;

    
    public ArbolMinMax(ArrayList<ArrayList<Ficha>> tablero, int limiteProfundidad,  ArrayList<Ficha> fichasManoIA, 
        ArrayList<Ficha> fichasManoJ, int puntosColorIA[], int puntosColorJ[]){
        raiz = new Nodo();
        raiz.setProfundidad(0);
        this.tablero = tablero;
        raiz.setPuntosColorIA(clonarPuntos(puntosColorIA));
        raiz.setPuntosColorJ(clonarPuntos(puntosColorJ));
        raiz.setTipoNodo(1);
        colaPrioridad = new ArrayList<Nodo>();
        colaPrioridad.add(raiz);
        this.limiteProfundidad = limiteProfundidad;
        this.fichasManoIA = fichasManoIA;
        this.fichasManoJ = fichasManoJ;
    }
    
     public void funcionPrincipal(){
        actual = colaPrioridad.remove(0);
        expandir(actual);//Expandir nodo
        while(colaPrioridad.size() > 0){
            actual = colaPrioridad.remove(0);
            expandir(actual);
        }
        //Recorrer los nodos que son hijos de la raiz pasando el valor a la raiz
        ArrayList<Nodo> nodosHijos = raiz.getHijos();
        for (int i = 0; i < nodosHijos.size(); i++) {
            raiz.actualizarPuntaje(nodosHijos.get(i).getPuntajeUtilidad(), nodosHijos.get(i).getFila(),
                    nodosHijos.get(i).getColumna(), nodosHijos.get(i).getFila2(),nodosHijos.get(i).getColumna2(), nodosHijos.get(i).getFicha());      
            //System.out.println(nodosHijos.get(i).getPuntajeUtilidad());
        }
    }   
    
    public int[] clonarPuntos(int puntos[]){
        int ps[] = new int[puntos.length];
        for (int i = 0; i < ps.length; i++) {
            ps[i] = puntos[i];
        }
        return ps;
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
                    ArrayList<ArrayList<Ficha>> nuevoTablero = clonarTablero(raiz.getTablero());
                    //Se actualiza el color y la posicion de la ficha
                    fichasManoIA.get(j).setFila(alternativas.get(i)[0]);
                    fichasManoIA.get(j).setColumna(alternativas.get(i)[1]);
                    fichasManoIA.get(j).getPareja().setFila(alternativas.get(i)[2]);
                    fichasManoIA.get(j).getPareja().setColumna(alternativas.get(i)[3]);
                    
                     //Se crea copia del puntje y se actualiza la copia con el nuevo puntaje
                    nodoNuevo.setTablero(nuevoTablero);
                    nodoNuevo.setPuntosColorIA(clonarPuntos(raiz.getPuntosColorIA()));
                    nodoNuevo.setPuntosColorJ(clonarPuntos(raiz.getPuntosColorJ()));
                    int n = nodoNuevo.actualizarPuntajeFicha(fichasManoIA.get(j));
                    int n1 = nodoNuevo.actualizarPuntajeFicha( fichasManoIA.get(j).getPareja());
                    
                    nodoNuevo.actualizarPuntajeIA(fichasManoIA.get(j).getColor(), n);
                    nodoNuevo.actualizarPuntajeIA(fichasManoIA.get(j).getPareja().getColor(), n1);
                    //Se almacena en el tablero
                    nuevoTablero.get(alternativas.get(i)[0]).get(alternativas.get(i)[1]).setColor(fichasManoIA.get(j).getColor());
                    nuevoTablero.get(alternativas.get(i)[0]).get(alternativas.get(i)[1]).setFila(alternativas.get(i)[0]);
                    nuevoTablero.get(alternativas.get(i)[0]).get(alternativas.get(i)[1]).setColumna(alternativas.get(i)[1]);
                    nuevoTablero.get(alternativas.get(i)[0]).get(alternativas.get(i)[1]).setPareja(raiz.getTablero().get(alternativas.get(i)[2]).get(alternativas.get(i)[3]));            
                    nuevoTablero.get(alternativas.get(i)[2]).get(alternativas.get(i)[3]).setColor(fichasManoIA.get(j).getPareja().getColor());
                    nuevoTablero.get(alternativas.get(i)[2]).get(alternativas.get(i)[3]).setFila(alternativas.get(i)[2]);
                    nuevoTablero.get(alternativas.get(i)[2]).get(alternativas.get(i)[3]).setColumna(alternativas.get(i)[3]);
                    nuevoTablero.get(alternativas.get(i)[2]).get(alternativas.get(i)[3]).setPareja(raiz.getTablero().get(alternativas.get(i)[0]).get(alternativas.get(i)[1]));

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
                    nodoNuevo.setFicha(j);
                    agregarNodo(nodoNuevo);
                    System.out.println(colaPrioridad.size());
                }
            } 
        }else if(nodo.getProfundidad() == 1){
            //Se expanden las alternativas de MIN
            ArrayList<int[]> alternativas = posiblesPosiciones(nodo.getTablero());
            for (int i = 0; i < alternativas.size(); i++) {
                for (int j = 0; j < fichasManoJ.size(); j++) {
                    //Se crea el nodo
                    Nodo nodoNuevo = new Nodo();
                    //Se crea copia de la matriz
                    ArrayList<ArrayList<Ficha>> nuevoTablero = clonarTablero(nodo.getTablero());
                    //Se actualiza el color y la posicion de la ficha
                    fichasManoJ.get(j).setFila(alternativas.get(i)[0]);
                    fichasManoJ.get(j).setColumna(alternativas.get(i)[1]);
                    fichasManoJ.get(j).getPareja().setFila(alternativas.get(i)[2]);
                    fichasManoJ.get(j).getPareja().setColumna(alternativas.get(i)[3]);
                    
                     //Se crea copia del puntje y se actualiza la copia con el nuevo puntaje
                    nodoNuevo.setTablero(nuevoTablero);
                    nodoNuevo.setPuntosColorIA(clonarPuntos(nodo.getPuntosColorIA()));
                    nodoNuevo.setPuntosColorJ(clonarPuntos(nodo.getPuntosColorJ()));
                    int n = nodoNuevo.actualizarPuntajeFicha(fichasManoJ.get(j));
                    int n1 = nodoNuevo.actualizarPuntajeFicha( fichasManoJ.get(j).getPareja());
                    
                    nodoNuevo.actualizarPuntajeJ(fichasManoJ.get(j).getColor(), n);
                    nodoNuevo.actualizarPuntajeJ(fichasManoJ.get(j).getPareja().getColor(), n1);
                    //Se almacena en el tablero
                    nuevoTablero.get(alternativas.get(i)[0]).get(alternativas.get(i)[1]).setColor(fichasManoJ.get(j).getColor());
                    nuevoTablero.get(alternativas.get(i)[0]).get(alternativas.get(i)[1]).setFila(alternativas.get(i)[0]);
                    nuevoTablero.get(alternativas.get(i)[0]).get(alternativas.get(i)[1]).setColumna(alternativas.get(i)[1]);
                    nuevoTablero.get(alternativas.get(i)[0]).get(alternativas.get(i)[1]).setPareja(nodo.getTablero().get(alternativas.get(i)[2]).get(alternativas.get(i)[3]));            
                    nuevoTablero.get(alternativas.get(i)[2]).get(alternativas.get(i)[3]).setColor(fichasManoJ.get(j).getPareja().getColor());
                    nuevoTablero.get(alternativas.get(i)[2]).get(alternativas.get(i)[3]).setFila(alternativas.get(i)[2]);
                    nuevoTablero.get(alternativas.get(i)[2]).get(alternativas.get(i)[3]).setColumna(alternativas.get(i)[3]);
                    nuevoTablero.get(alternativas.get(i)[2]).get(alternativas.get(i)[3]).setPareja(nodo.getTablero().get(alternativas.get(i)[0]).get(alternativas.get(i)[1]));

                     //Se setea la ficha en el nodo (posicion en el arreglo de fichas en mano) y se setea la posicion de la ficha
                    nodoNuevo.setFila(alternativas.get(i)[0]);
                    nodoNuevo.setColumna(alternativas.get(i)[1]);
                    nodoNuevo.setFila2(alternativas.get(i)[2]);
                    nodoNuevo.setColumna2(alternativas.get(i)[3]);

                    //Se setea la profundidad
                    nodoNuevo.setProfundidad(2);
                    //Se actualiza puntero al padre
                    nodoNuevo.setPadre(nodo);
                    //Se setea el tipo de nodo
                    nodoNuevo.setTipoNodo(1);
                    //Se agreaga el nuevo hijo al padre
                    nodo.agregarHijo(nodoNuevo);
                    nodoNuevo.setFicha(j);
                    agregarNodo(nodoNuevo);
                }
            }
        }else{
            //Se calcula la utilidad y se envia al padre
            int puntaje = nodo.calcularUtilidad();
            nodo.getPadre().actualizarPuntaje(puntaje, nodo.getFila(), nodo.getColumna(), nodo.getFila2(),
                    nodo.getColumna2(), nodo.getFicha());
            //Pasar variable revisado a true
            nodo.setRevisado(true);
        }
    }
    
<<<<<<< HEAD
    public void agregarNodo(Nodo nodo){
        boolean nodoIngresado = false;
        for (int i = 0; i < colaPrioridad.size() && !nodoIngresado; i++) {
            if(nodo.getProfundidad() > colaPrioridad.get(i).getProfundidad()){
                colaPrioridad.add(i, nodo);
                nodoIngresado = true;
            }
        }
        if(nodoIngresado == false){
            colaPrioridad.add(nodo);
        }
    }
    
=======
>>>>>>> c023aaa433f2b7c7ab092ad598697b5ec3a337bb
    public ArrayList<int[]> posiblesPosiciones(ArrayList<ArrayList<Ficha>> t) {
        ArrayList<int[]> respuesta = new ArrayList<int[]>();
        for(int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++) {
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
<<<<<<< HEAD
                    int posiciones [] = new int[4];
                    //System.out.println("Superior izquierda " + fila + " " + columna +" "+(fila-1) + " " + (columna-1));
                    posiciones[2] = fila-1;
                    posiciones[3] = columna-1;
                    posiciones[0] = fila;
                    posiciones[1] = columna;
=======
                    posiciones[0] = fila-1;
                    posiciones[1] = columna-1;
                    posiciones[2] = fila;
                    posiciones[3] = columna;
>>>>>>> c023aaa433f2b7c7ab092ad598697b5ec3a337bb
                    respuesta.add(posiciones);
                }
            }
            //Revisar superior derecha
            if(fila > 0){
                if(columna < tablero.get(fila-1).size()){
                     if(tablero.get(fila-1).get(columna).getColor() == 0){
<<<<<<< HEAD
                        int posiciones [] = new int[4];
                        //System.out.println("superior derecha "+ fila + " " + columna + " "+ (fila-1) + " " + columna);
                        posiciones[2] = fila-1;
=======
                        posiciones[0] = fila-1;
                        posiciones[1] = columna;
                        posiciones[2] = fila;
>>>>>>> c023aaa433f2b7c7ab092ad598697b5ec3a337bb
                        posiciones[3] = columna;
                        posiciones[0] = fila;
                        posiciones[1] = columna;
                        respuesta.add(posiciones);
                    }
                }
            }
            //Revisar Izquierda
            if(columna > 0){
                if(tablero.get(fila).get(columna-1).getColor() == 0){
<<<<<<< HEAD
                    int posiciones [] = new int[4];
                    //System.out.println("Izquierda " +fila + " " + columna + " " + fila + " " + (columna-1));
=======
                    posiciones[0] = fila;
                    posiciones[1] = columna-1;
>>>>>>> c023aaa433f2b7c7ab092ad598697b5ec3a337bb
                    posiciones[2] = fila;
                    posiciones[3] = columna-1;
                    posiciones[0] = fila;
                    posiciones[1] = columna;
                    respuesta.add(posiciones);
                }   
            }
            //Revisar inferior izquierda
            if(fila+1 < tablero.size() && columna < tablero.get(fila).size()){
                if(tablero.get(fila+1).get(columna).getColor() == 0){
<<<<<<< HEAD
                    int posiciones [] = new int[4];
                    //System.out.println("Inferior izquierda "+ fila + " " + columna + " "+ (fila+1) + " " + columna);
                    posiciones[2] = fila+1;
=======
                    posiciones[0] = fila+1;
                    posiciones[1] = columna;
                    posiciones[2] = fila;
>>>>>>> c023aaa433f2b7c7ab092ad598697b5ec3a337bb
                    posiciones[3] = columna;
                    posiciones[0] = fila;
                    posiciones[1] = columna;
                    respuesta.add(posiciones);
                }
            }
            //Revisar inferior derecha
            if(fila+1 < tablero.size() && columna < tablero.get(fila).size()){
                if(tablero.get(fila+1).get(columna+1).getColor() == 0){
<<<<<<< HEAD
                    int posiciones [] = new int[4];
                    //System.out.println("Inferior derecha " + fila + " " + columna + " "+ (fila+1) + " " + (columna+1));
                    posiciones[2] = fila+1;
                    posiciones[3] = columna+1;
                    posiciones[0] = fila;
                    posiciones[1] = columna;
=======
                    posiciones[0] = fila+1;
                    posiciones[1] = columna+1;
                    posiciones[2] = fila;
                    posiciones[3] = columna;
>>>>>>> c023aaa433f2b7c7ab092ad598697b5ec3a337bb
                    respuesta.add(posiciones);
                }
            }
            //Revisar  derecha
            if(columna+1 < tablero.get(fila).size()){
                if(tablero.get(fila).get(columna+1).getColor() == 0){
<<<<<<< HEAD
                    int posiciones [] = new int[4];
                    //System.out.println("Derecha "+ fila + " " + columna + " " + fila + " " + (columna+1));
=======
                    posiciones[0] = fila;
                    posiciones[1] = columna+1;
>>>>>>> c023aaa433f2b7c7ab092ad598697b5ec3a337bb
                    posiciones[2] = fila;
                    posiciones[3] = columna+1;
                    posiciones[0] = fila;
                    posiciones[1] = columna;
                    respuesta.add(posiciones);
                }
            }
        }
        else if(fila >= 6){
            //Revisar superior izquierda
            if(fila > 0 && columna >= 0){
                if(tablero.get(fila-1).get(columna).getColor() == 0){
<<<<<<< HEAD
                    int posiciones [] = new int[4];
                    //System.out.println("Superior izquierda " + fila + " " + columna +" "+(fila-1) + " " + columna);
                    posiciones[2] = fila-1;
=======
                    posiciones[0] = fila-1;
                    posiciones[1] = columna;
                    posiciones[2] = fila;
>>>>>>> c023aaa433f2b7c7ab092ad598697b5ec3a337bb
                    posiciones[3] = columna;
                    posiciones[0] = fila;
                    posiciones[1] = columna;
                    respuesta.add(posiciones);
                }
            }
            //Revisar superior derecha
            if(fila > 0 && columna < tablero.get(fila).size()){
                if(columna+1 < tablero.get(fila-1).size()){
                    if(tablero.get(fila-1).get(columna+1).getColor() == 0){
<<<<<<< HEAD
                        int posiciones [] = new int[4];
                        //System.out.println("Superior derecha " + fila + " " + columna +" "+(fila-1) + " " + (columna+1));
                        posiciones[2] = fila-1;
                        posiciones[3] = columna+1;
                        posiciones[0] = fila;
                        posiciones[1] = columna;
=======
                        posiciones[0] = fila-1;
                        posiciones[1] = columna+1;
                        posiciones[2] = fila;
                        posiciones[3] = columna;
>>>>>>> c023aaa433f2b7c7ab092ad598697b5ec3a337bb
                        respuesta.add(posiciones);
                    }
                }
            }
            //Revisar Izquierda
            if(columna > 0){
                if(tablero.get(fila).get(columna-1).getColor() == 0){
<<<<<<< HEAD
                    int posiciones [] = new int[4];
                    //System.out.println("Izquierda " + fila + " " + columna +" "+fila + " " + (columna-1));
=======
                    posiciones[0] = fila;
                    posiciones[1] = columna-1;
>>>>>>> c023aaa433f2b7c7ab092ad598697b5ec3a337bb
                    posiciones[2] = fila;
                    posiciones[3] = columna-1;
                    posiciones[0] = fila;
                    posiciones[1] = columna;
                    respuesta.add(posiciones);
                }
            }
            //Revisar inferior izquierda
            if(fila+1 < tablero.size() && columna > 0){
                if(tablero.get(fila+1).get(columna-1).getColor() == 0){
<<<<<<< HEAD
                    int posiciones [] = new int[4];
                    //System.out.println("Inferior izquierda " + fila + " " + columna +" "+(fila+1) + " " + (columna-1));
                    posiciones[2] = fila+1;
                    posiciones[3] = columna-1;
                    posiciones[0] = fila;
                    posiciones[1] = columna;
=======
                    posiciones[0] = fila+1;
                    posiciones[1] = columna-1;
                    posiciones[2] = fila;
                    posiciones[3] = columna;
>>>>>>> c023aaa433f2b7c7ab092ad598697b5ec3a337bb
                    respuesta.add(posiciones);
                }
            }
            //Revisar inferior derecha
            if(fila+1 < tablero.size()){
                if(columna < tablero.get(fila+1).size()){
                    if(tablero.get(fila+1).get(columna).getColor() == 0){
<<<<<<< HEAD
                        int posiciones [] = new int[4];
                        //System.out.println("Inferior derecha " + fila + " " + columna +" "+(fila+1) + " " + columna);
                        posiciones[2] = fila+1;
=======
                        posiciones[0] = fila+1;
                        posiciones[1] = columna;
                        posiciones[2] = fila;
>>>>>>> c023aaa433f2b7c7ab092ad598697b5ec3a337bb
                        posiciones[3] = columna;
                        posiciones[0] = fila;
                        posiciones[1] = columna;
                        respuesta.add(posiciones);
                    }
                }
            }
            //Revisar  derecha
            if(columna+1 < tablero.get(fila).size()){
                if(tablero.get(fila).get(columna+1).getColor() == 0) {
<<<<<<< HEAD
                    int posiciones [] = new int[4];
                    //System.out.println("Dereccha " + fila + " " + columna +" "+fila + " " + (columna+1));
=======
                    posiciones[0] = fila;
                    posiciones[1] = columna+1;
>>>>>>> c023aaa433f2b7c7ab092ad598697b5ec3a337bb
                    posiciones[2] = fila;
                    posiciones[3] = columna+1;
                    posiciones[0] = fila;
                    posiciones[1] = columna;
                    respuesta.add(posiciones);
                }
            }
        }
        else if(fila == 5){
            //Revisar superior izquierda
            if(fila > 0 && columna > 0){    
                if(tablero.get(fila-1).get(columna-1).getColor()==0){
<<<<<<< HEAD
                    int posiciones [] = new int[4];
                    //System.out.println("Superior izquierda " + fila + " " + columna +" "+(fila-1) + " " + (columna-1));
                    posiciones[2] = fila-1;
                    posiciones[3] = columna-1;
                    posiciones[0] = fila;
                    posiciones[1] = columna;
=======
                    posiciones[0] = fila-1;
                    posiciones[1] = columna-1;
                    posiciones[2] = fila;
                    posiciones[3] = columna;
>>>>>>> c023aaa433f2b7c7ab092ad598697b5ec3a337bb
                    respuesta.add(posiciones);
                }
            }
            //Revisar superior derecha
            if(fila > 0){
                if(columna < tablero.get(fila-1).size()){
                    if(tablero.get(fila-1).get(columna).getColor() == 0){
<<<<<<< HEAD
                        int posiciones [] = new int[4];
                        //System.out.println("Superior derecha " + fila + " " + columna +" "+(fila-1) + " " + columna);
                        posiciones[2] = fila-1;
=======
                        posiciones[0] = fila-1;
                        posiciones[1] = columna;
                        posiciones[2] = fila;
>>>>>>> c023aaa433f2b7c7ab092ad598697b5ec3a337bb
                        posiciones[3] = columna;
                        posiciones[0] = fila;
                        posiciones[1] = columna;
                        respuesta.add(posiciones);
                    }
                }
            }
            //Revisar Izquierda
            if(columna > 0){
                if(tablero.get(fila).get(columna-1).getColor() == 0){
<<<<<<< HEAD
                    int posiciones [] = new int[4];
                    //System.out.println("Izquierda " + fila + " " + columna +" "+fila + " " + (columna-1));
=======
                    posiciones[0] = fila;
                    posiciones[1] = columna-1;
>>>>>>> c023aaa433f2b7c7ab092ad598697b5ec3a337bb
                    posiciones[2] = fila;
                    posiciones[3] = columna-1;
                    posiciones[0] = fila;
                    posiciones[1] = columna;
                    respuesta.add(posiciones);
                }
            }
            //Revisar inferior izquierda
            if(fila+1 < tablero.size() && columna > 0){
                if(tablero.get(fila+1).get(columna-1).getColor() == 0){
<<<<<<< HEAD
                    int posiciones [] = new int[4];
                    //System.out.println("Inferior izquierda " + fila + " " + columna +" "+(fila+1) + " " + (columna-1));
                    posiciones[2] = fila+1;
                    posiciones[3] = columna-1;
                    posiciones[0] = fila;
                    posiciones[1] = columna;
=======
                    posiciones[0] = fila+1;
                    posiciones[1] = columna-1;
                    posiciones[2] = fila;
                    posiciones[3] = columna;
>>>>>>> c023aaa433f2b7c7ab092ad598697b5ec3a337bb
                    respuesta.add(posiciones);
                }
            }
            //Revisar inferior derecha
            if(fila+1 < tablero.size()){
                if(columna < tablero.get(fila+1).size()){
                    if(tablero.get(fila+1).get(columna).getColor() == 0){
<<<<<<< HEAD
                        int posiciones [] = new int[4];
                        //System.out.println("Inferior derecha " + fila + " " + columna +" "+(fila+1) + " " + columna);
                        posiciones[2] = fila+1;
=======
                        posiciones[0] = fila+1;
                        posiciones[1] = columna;
                        posiciones[2] = fila;
>>>>>>> c023aaa433f2b7c7ab092ad598697b5ec3a337bb
                        posiciones[3] = columna;
                        posiciones[0] = fila;
                        posiciones[1] = columna;
                        respuesta.add(posiciones);
                    }
                }
            }
            //Revisar  derecha
            if(columna+1 < tablero.get(fila).size()){
                if(tablero.get(fila).get(columna+1).getColor() == 0){
<<<<<<< HEAD
                    int posiciones [] = new int[4];
                    //System.out.println("Derecha " + fila + " " + columna +" "+ fila + " " + (columna+1));
=======
                    posiciones[0] = fila;
                    posiciones[1] = columna+1;
>>>>>>> c023aaa433f2b7c7ab092ad598697b5ec3a337bb
                    posiciones[2] = fila;
                    posiciones[3] = columna+1;
                    posiciones[0] = fila;
                    posiciones[1] = columna;
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

<<<<<<< HEAD
=======
    public ArrayList<ArrayList<Ficha>> getTablero() {
        return tablero;
    }

    public void setTablero(ArrayList<ArrayList<Ficha>> tablero) {
        this.tablero = tablero;
    }

>>>>>>> c023aaa433f2b7c7ab092ad598697b5ec3a337bb
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
