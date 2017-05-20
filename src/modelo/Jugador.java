package modelo;

import java.util.ArrayList;

public class Jugador {
    int puntosColor[];
    ArrayList<Ficha> fichasActuales;
    
    public Jugador() {
        puntosColor = new int[6];
        for(int i = 0; i < puntosColor.length; i++) puntosColor[i] = 0;
        fichasActuales = null;
    }

    public Jugador(ArrayList<Ficha> fichasActuales) {
        puntosColor = new int[6];
        for(int i = 0; i < puntosColor.length; i++) puntosColor[i] = 0;
        this.fichasActuales = fichasActuales;
    }
    
    public int[] getPuntosColor() {
        return puntosColor;
    }

    public void setPuntosColor(int[] puntosColor) {
        this.puntosColor = puntosColor;
    }

    public ArrayList<Ficha> getFichasActuales() {
        return fichasActuales;
    }

    public void setFichasActuales(ArrayList<Ficha> fichasActuales) {
        this.fichasActuales = fichasActuales;
    }
    
    Ficha mover(Ficha ficha1, int fila1, int columna1, int fila2, int columna2){
        ficha1.setFila(fila1);
        ficha1.setColumna(columna1);
        Ficha ficha2 = ficha1.getPareja();
        ficha2.setFila(fila2);
        ficha2.setColumna(columna2);
        fichasActuales.remove(ficha1);
        fichasActuales.remove(ficha2);
        return ficha1;   
    } 
    
    void actualizarPuntaje(int color, int puntaje){
        puntosColor[color] += puntaje;
    } 
}
