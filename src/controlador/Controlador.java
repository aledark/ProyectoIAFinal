/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import modelo.Ficha;
import modelo.Tablero;
import vista.BotonHexagonal;
import vista.PanelPrincipal;

/**
 *
 * @author Jennifer
 */
public class Controlador implements MouseListener{
    PanelPrincipal panel;
    Ficha fichaSeleccionada;
    int lado;
    BotonHexagonal botonAnterior;
    Ficha fichaAnterior;
    BotonHexagonal botonParejaAnterior;
    Ficha fichaParejaAnterior;
    int fila1, columna1, fila2, columna2;
    MouseEvent e;
    Tablero juego;
    public Controlador(PanelPrincipal panel){
        this.panel = panel;
        fichaSeleccionada = null;
        botonAnterior = null;
        fichaAnterior = null;
        botonParejaAnterior = null;
        fichaParejaAnterior = null;
        lado = 0;
        fila1 = -1;
        fila2 = -1;
        columna1 = -1;
        columna2 = -1;
        e = null;
        juego = panel.getJuego();
    }

    @Override
    public void mouseClicked(MouseEvent e){
        if(e.getButton() == 1){
            if(botonParejaAnterior != null){
                if(!juego.isGameOver()){
                    int posiciones[] = {fila1, columna1, fila2, columna2};
                    fichaSeleccionada = juego.getJ1().mover(fichaSeleccionada, posiciones);
                    juego.getJ1().agregarFicha(juego.entregarFichaBolsa());
                    juego.agregarFichasAlTablero(fichaSeleccionada);
                    juego.getJ1().actualizarPuntaje(fichaSeleccionada.getColor(), juego.calcularPuntaje(fichaSeleccionada));
                    juego.getJ1().actualizarPuntaje(fichaSeleccionada.getPareja().getColor(), juego.calcularPuntaje(fichaSeleccionada.getPareja()));
                    juego.actualizarTablero(fichaSeleccionada);
                    juego.validarGameOver();
                    juego.cambiarTurno();
                    panel.dibujarFichasMano();
                    panel.actualizarTablerosPuntuacion();
                    //panel.getHexagonos().get(fichaSeleccionada.getFila()).get(fichaSeleccionada.getColumna()).setIcon(panel.getImage(fichaSeleccionada.getColor()));
                    //panel.getHexagonos().get(fichaSeleccionada.getPareja().getFila()).get(fichaSeleccionada.getPareja().getColumna()).setIcon(panel.getImage(fichaSeleccionada.getPareja().getColor()));
                    fichaSeleccionada = null;
                    /*
                    //Jugador 2
                    Ficha fichaSeleccionadaJ2 = juego.getJ2().getFichasActuales().get(0);
                    int posicionesJ2[] = buscarEspacioLibre();
                    fichaSeleccionadaJ2 = juego.getJ2().mover(fichaSeleccionadaJ2, posicionesJ2);
                    juego.getJ2().agregarFicha(juego.entregarFichaBolsa());
                    juego.actualizarTablero(fichaSeleccionadaJ2);
                    juego.agregarFichasAlTablero(fichaSeleccionadaJ2);
                    juego.getJ2().actualizarPuntaje(fichaSeleccionadaJ2.getColor(), juego.calcularPuntaje(fichaSeleccionadaJ2));
                    juego.getJ2().actualizarPuntaje(fichaSeleccionadaJ2.getPareja().getColor(), juego.calcularPuntaje(fichaSeleccionadaJ2.getPareja()));
                    juego.validarGameOver();
                    juego.cambiarTurno();
                    panel.dibujarFichasMano();
                    panel.actualizarTableros();*/
                } 
            } 
            ArrayList<BotonHexagonal> fichasJugador = panel.getFichasMano();
            ArrayList<Ficha> fichasManoJugador = panel.getFichasManoJugador();
            for (int i = 0; i < fichasJugador.size(); i++) {
                if(e.getSource() == fichasJugador.get(i)){
                    fichaSeleccionada = fichasManoJugador.get(Math.floorDiv(i, 2));
                    System.out.println("ficha seleccionada");
                }
            }
        }
        else{
            lado = (lado+1)%6;
            mouseEntered(e);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.e = e;
        ArrayList<ArrayList<BotonHexagonal>> botones = panel.getBotones();
        ArrayList<ArrayList<Ficha>> tablero = panel.getTablero();
        if(botonAnterior != null){
            botonAnterior.setIcon(panel.getImage(fichaAnterior.getColor()));
        }
        for(int i = 0; i < botones.size(); i++){
            for (int j = 0; j < botones.get(i).size(); j++) {
                if(e.getSource() == botones.get(i).get(j)){
                    if(fichaSeleccionada != null && tablero.get(i).get(j).getColor() == 0){
                        botonAnterior = botones.get(i).get(j);
                        fichaAnterior = tablero.get(i).get(j);
                        botones.get(i).get(j).setIcon(panel.getImage(fichaSeleccionada.getColor()));
                        dibujarPareja(i,j, botones, tablero);  
                        fila1 = i;
                        columna1 = j;
                    }
                    else{
                         if(botonParejaAnterior != null && botonParejaAnterior != botonAnterior){
                            botonParejaAnterior.setIcon(panel.getImage(fichaParejaAnterior.getColor()));
                        }
                        botonAnterior = null;
                        botonParejaAnterior = null;
                    }
                }
            }
        } 
    }
    public void dibujarPareja(int i, int j, ArrayList<ArrayList<BotonHexagonal>> botones, ArrayList<ArrayList<Ficha>> tablero){
        if(botonParejaAnterior != null && botonParejaAnterior != botonAnterior){
            botonParejaAnterior.setIcon(panel.getImage(fichaParejaAnterior.getColor()));
        }
       
        if(i <=4){
            if(lado == 0 && i > 0 && j > 0){
                if(tablero.get(i-1).get(j-1).getColor() == 0){
                    botonParejaAnterior = botones.get(i-1).get(j-1);
                    fichaParejaAnterior = tablero.get(i-1).get(j-1);
                    botones.get(i-1).get(j-1).setIcon(panel.getImage(fichaSeleccionada.getPareja().getColor()));
                    fila2 = i-1;
                    columna2=j-1;
                }
                else{
                    botonParejaAnterior = null;
                }
            }           
            else if(lado == 1 && i > 0){
                if(j < tablero.get(i-1).size()){
                   if(tablero.get(i-1).get(j).getColor() == 0){
                        botonParejaAnterior = botones.get(i-1).get(j);
                        fichaParejaAnterior = tablero.get(i-1).get(j);
                        botones.get(i-1).get(j).setIcon(panel.getImage(fichaSeleccionada.getPareja().getColor()));
                        fila2 = i-1;
                        columna2=j;
                    }
                    else{
                        botonParejaAnterior = null;
                    } 
                }
                    
            }
            else if(lado == 5 && j > 0){
                if(tablero.get(i).get(j-1).getColor() == 0){
                        botonParejaAnterior = botones.get(i).get(j-1);
                        fichaParejaAnterior = tablero.get(i).get(j-1);
                        botones.get(i).get(j-1).setIcon(panel.getImage(fichaSeleccionada.getPareja().getColor()));
                        fila2 = i;
                        columna2=j-1;
                    }
                    else{
                        botonParejaAnterior = null;
                    } 
            }
            else  if(lado == 4 && i < tablero.size()){
                if(j < tablero.get(i).size()){
                    if(tablero.get(i+1).get(j).getColor() == 0){
                        botonParejaAnterior = botones.get(i+1).get(j);
                        fichaParejaAnterior = tablero.get(i+1).get(j);
                        botones.get(i+1).get(j).setIcon(panel.getImage(fichaSeleccionada.getPareja().getColor()));
                        fila2 = i+1;
                        columna2=j;
                    }
                    else{
                        botonParejaAnterior = null;
                    } 
                }
                else{
                    botonParejaAnterior = null;
                }
            }
            else if(lado == 3 && i < tablero.size()-1){
                if(j < tablero.get(i+1).size()){
                    if(tablero.get(i+1).get(j+1).getColor() == 0){
                        botonParejaAnterior = botones.get(i+1).get(j+1);
                        fichaParejaAnterior = tablero.get(i+1).get(j+1);
                        botones.get(i+1).get(j+1).setIcon(panel.getImage(fichaSeleccionada.getPareja().getColor()));
                        fila2 = i+1;
                        columna2=j+1;
                    }
                    else{
                        botonParejaAnterior = null;
                    } 
                }
            }
            else if(lado == 2 && i < tablero.size()){
                if(j < tablero.get(i).size()-1){
                    if(tablero.get(i).get(j+1).getColor() == 0){
                        botonParejaAnterior = botones.get(i).get(j+1);
                        fichaParejaAnterior = tablero.get(i).get(j+1);
                        botones.get(i).get(j+1).setIcon(panel.getImage(fichaSeleccionada.getPareja().getColor()));
                        fila2 = i;
                        columna2=j+1;
                    }
                    else{
                        botonParejaAnterior = null;
                    } 
                }
                else{
                    botonParejaAnterior=null;
                }
            }
            else{
                botonParejaAnterior = null;
            }
            
        }else if(i >= 6){
            if(lado == 0 && i > 0 && j >= 0){
                if(tablero.get(i-1).get(j).getColor() == 0){
                    botonParejaAnterior = botones.get(i-1).get(j);
                    fichaParejaAnterior = tablero.get(i-1).get(j);
                    botones.get(i-1).get(j).setIcon(panel.getImage(fichaSeleccionada.getPareja().getColor()));
                    fila2 = i-1;
                    columna2=j;
                }else{
                    botonParejaAnterior = null;
                }
            }
            else if(lado == 1 && i > 0){
                if(j < tablero.get(i-1).size()){
                   if(tablero.get(i-1).get(j+1).getColor() == 0){
                        botonParejaAnterior = botones.get(i-1).get(j+1);
                        fichaParejaAnterior = tablero.get(i-1).get(j+1);
                        botones.get(i-1).get(j+1).setIcon(panel.getImage(fichaSeleccionada.getPareja().getColor()));
                        fila2 = i-1;
                        columna2=j+1;
                    }else{
                        botonParejaAnterior = null;
                    } 
                }
                else{
                    botonParejaAnterior = null;
                }
            }
            else if(lado == 5 && j > 0){
                if(tablero.get(i).get(j-1).getColor() == 0){
                        botonParejaAnterior = botones.get(i).get(j-1);
                        fichaParejaAnterior = tablero.get(i).get(j-1);
                        botones.get(i).get(j-1).setIcon(panel.getImage(fichaSeleccionada.getPareja().getColor()));
                        fila2 = i;
                        columna2=j-1;
                }else{
                    botonParejaAnterior = null;
                } 
            }
            else if(lado == 4 && i < tablero.size()-1 && j > 0){
                if(tablero.get(i+1).get(j-1).getColor() == 0){
                    botonParejaAnterior = botones.get(i+1).get(j-1);
                    fichaParejaAnterior = tablero.get(i+1).get(j-1);
                    botones.get(i+1).get(j-1).setIcon(panel.getImage(fichaSeleccionada.getPareja().getColor()));
                    fila2 = i+1;
                    columna2=j-1;
                }else{
                    botonParejaAnterior = null;
                } 
            }
            else if(lado == 3 && i < tablero.size()-1){
                if(j < tablero.get(i+1).size()){
                    if(tablero.get(i+1).get(j).getColor() == 0){
                        botonParejaAnterior = botones.get(i+1).get(j);
                        fichaParejaAnterior = tablero.get(i+1).get(j);
                        botones.get(i+1).get(j).setIcon(panel.getImage(fichaSeleccionada.getPareja().getColor()));
                        fila2 = i+1;
                        columna2=j;
                    }else{
                        botonParejaAnterior = null;
                    } 
                }
                else{
                    botonParejaAnterior = null;
                }
            }
            else if(lado == 2 && i < tablero.size() && j < tablero.get(i).size()-1){
                if(tablero.get(i).get(j+1).getColor() == 0){
                    botonParejaAnterior = botones.get(i).get(j+1);
                    fichaParejaAnterior = tablero.get(i).get(j+1);
                    botones.get(i).get(j+1).setIcon(panel.getImage(fichaSeleccionada.getPareja().getColor()));
                    fila2 = i;
                    columna2=j+1;
                }else{
                    botonParejaAnterior = null;
                }       
            }
            else{
                botonParejaAnterior = null;
            }
        }   
        else{
             if(lado == 0 && i > 0 && j > 0){
                if(tablero.get(i-1).get(j-1).getColor() == 0){
                    botonParejaAnterior = botones.get(i-1).get(j-1);
                    fichaParejaAnterior = tablero.get(i-1).get(j-1);
                    botones.get(i-1).get(j-1).setIcon(panel.getImage(fichaSeleccionada.getPareja().getColor()));
                    fila2 = i-1;
                    columna2=j-1;
                }
                else{
                    botonParejaAnterior = null;
                }
            }           
            else if(lado == 1 && i > 0){
                if(j < tablero.get(i-1).size()){
                   if(tablero.get(i-1).get(j).getColor() == 0){
                        botonParejaAnterior = botones.get(i-1).get(j);
                        fichaParejaAnterior = tablero.get(i-1).get(j);
                        botones.get(i-1).get(j).setIcon(panel.getImage(fichaSeleccionada.getPareja().getColor()));
                        fila2 = i-1;
                        columna2=j;
                    }
                    else{
                        botonParejaAnterior = null;
                    } 
                }
                    
            }
            else if(lado == 5 && j > 0){
                if(tablero.get(i).get(j-1).getColor() == 0){
                        botonParejaAnterior = botones.get(i).get(j-1);
                        fichaParejaAnterior = tablero.get(i).get(j-1);
                        botones.get(i).get(j-1).setIcon(panel.getImage(fichaSeleccionada.getPareja().getColor()));
                        fila2 = i;
                        columna2=j-1;
                    }
                    else{
                        botonParejaAnterior = null;
                    } 
            }
             else if(lado == 4 && i < tablero.size()-1 && j > 0){
                if(tablero.get(i+1).get(j-1).getColor() == 0){
                    botonParejaAnterior = botones.get(i+1).get(j-1);
                    fichaParejaAnterior = tablero.get(i+1).get(j-1);
                    botones.get(i+1).get(j-1).setIcon(panel.getImage(fichaSeleccionada.getPareja().getColor()));
                    fila2 = i+1;
                    columna2=j-1;
                }else{
                    botonParejaAnterior = null;
                } 
            }
            else if(lado == 3 && i < tablero.size()-1){
                if(j < tablero.get(i+1).size()){
                    if(tablero.get(i+1).get(j).getColor() == 0){
                        botonParejaAnterior = botones.get(i+1).get(j);
                        fichaParejaAnterior = tablero.get(i+1).get(j);
                        botones.get(i+1).get(j).setIcon(panel.getImage(fichaSeleccionada.getPareja().getColor()));
                        fila2 = i+1;
                        columna2=j;
                    }else{
                        botonParejaAnterior = null;
                    } 
                }
                else{
                    botonParejaAnterior = null;
                }
            }
            else if(lado == 2 && i < tablero.size() && j < tablero.get(i).size()-1){
                if(tablero.get(i).get(j+1).getColor() == 0){
                    botonParejaAnterior = botones.get(i).get(j+1);
                    fichaParejaAnterior = tablero.get(i).get(j+1);
                    botones.get(i).get(j+1).setIcon(panel.getImage(fichaSeleccionada.getPareja().getColor()));
                    fila2 = i;
                    columna2=j+1;
                }else{
                    botonParejaAnterior = null;
                }       
            }
            else{
                botonParejaAnterior = null;
            }
        }
    }
     
    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int[] buscarEspacioLibre() {
        ArrayList<ArrayList<Ficha>> t = juego.getTablero();
        boolean encontrado = false;
        int posicionPareja[] = null;
        int i = 0;
        int j = 0;
        for(i = 0; i < t.size() && !encontrado; i++){
            for (j = 0; j < t.get(i).size(); j++) {
                if(t.get(i).get(j).getColor() == 0){
                     posicionPareja = validarEspacio(t.get(i).get(j));
                    if(posicionPareja != null) encontrado = true;
                }
            }
        }
        if(posicionPareja == null) return null;
        else {
            int respuesta[] = {   i,j,posicionPareja[0], posicionPareja[1]};
            return respuesta;
        }
    }
    public int[] validarEspacio(Ficha ficha){
        ArrayList<ArrayList<Ficha>> tablero = juego.getTablero();
        int posiciones [] = new int[2];
        int fila = ficha.getFila();
        int columna = ficha.getColumna();
        boolean encontro = false;
        if(fila <=4){
            //Revisar superior izquierda
            if(fila > 0 && columna > 0){    
                if(tablero.get(fila-1).get(columna-1).getColor()==0){
                    posiciones[0] = fila-1;
                    posiciones[1] = columna-1;
                    encontro = true;
                }
            }
            //Revisar superior derecha
            if(fila > 0 && columna < tablero.get(fila).size()){
                if(tablero.get(fila-1).get(columna).getColor() == 0){
                    posiciones[0] = fila-1;
                    posiciones[1] = columna;
                    encontro = true;
                }
            }
            //Revisar Izquierda
            if(columna > 0){
                if(tablero.get(fila).get(columna-1).getColor() == 0){
                    posiciones[0] = fila;
                    posiciones[1] = columna-1;
                    encontro = true;
                }
            }
            //Revisar inferior izquierda
            if(fila < tablero.size() && columna < tablero.get(fila).size()){
                if(tablero.get(fila+1).get(columna).getColor() == 0){
                    posiciones[0] = fila+1;
                    posiciones[1] = columna;
                    encontro = true;
                }
            }
            //Revisar inferior derecha
            if(fila < tablero.size() && columna < tablero.get(fila).size()){
                if(tablero.get(fila+1).get(columna+1).getColor() == 0){
                    posiciones[0] = fila+1;
                    posiciones[1] = columna+1;
                    encontro = true;
                }
            }
            //Revisar  derecha
            if(columna < tablero.get(fila).size()){
                if(tablero.get(fila).get(columna+1).getColor() == 0){
                    posiciones[0] = fila;
                    posiciones[1] = columna+1;
                    encontro = true;
                }
            }
        }
        if(fila >= 6){
            //Revisar superior izquierda
            if(fila > 0 && columna > 0){
                if(tablero.get(fila-1).get(columna).getColor() == 0){
                    posiciones[0] = fila-1;
                    posiciones[1] = columna;
                    encontro = true;
                }
            }
            //Revisar superior derecha
            if(fila > 0 && columna < tablero.get(fila).size()){
                if(tablero.get(fila-1).get(columna+1).getColor() == 0){
                    posiciones[0] = fila-1;
                    posiciones[1] = columna+1;
                    encontro = true;
                }
            }
            //Revisar Izquierda
            if(columna > 0){
                if(tablero.get(fila).get(columna-1).getColor() == 0){
                    posiciones[0] = fila;
                    posiciones[1] = columna-1;
                    encontro = true;
                }
            }
            //Revisar inferior izquierda
            if(fila < tablero.size() && columna > 0){
                if(tablero.get(fila+1).get(columna-1).getColor() == 0){
                    posiciones[0] = fila+1;
                    posiciones[1] = columna-1;
                    encontro = true;
                }
            }
            //Revisar inferior derecha
            if(fila < tablero.size() && columna < tablero.get(fila).size()){
                if(tablero.get(fila+1).get(columna).getColor() == 0){
                    posiciones[0] = fila+1;
                    posiciones[1] = columna;
                    encontro = true;
                }
            }
            //Revisar  derecha
            if(columna < tablero.get(fila).size()){
                if(tablero.get(fila).get(columna+1).getColor() == 0) {
                    posiciones[0] = fila;
                    posiciones[1] = columna+1;
                    encontro = true;
                }
            }
        }
        else{
            //Revisar superior izquierda
            if(fila > 0 && columna > 0){    
                if(tablero.get(fila-1).get(columna-1).getColor()==0){
                    posiciones[0] = fila-1;
                    posiciones[1] = columna-1;
                    encontro = true;
                }
            }
            //Revisar superior derecha
            if(fila > 0 && columna < tablero.get(fila).size()){
                if(tablero.get(fila-1).get(columna).getColor() == 0){
                    posiciones[0] = fila-1;
                    posiciones[1] = columna;
                    encontro = true;
                }
            }
            //Revisar Izquierda
            if(columna > 0){
                if(tablero.get(fila).get(columna-1).getColor() == 0){
                    posiciones[0] = fila;
                    posiciones[1] = columna-1;
                    encontro = true;
                }
            }
            //Revisar inferior izquierda
            if(fila < tablero.size() && columna > 0){
                if(tablero.get(fila+1).get(columna-1).getColor() == 0){
                    posiciones[0] = fila+1;
                    posiciones[1] = columna-1;
                    encontro = true;
                }
            }
            //Revisar inferior derecha
            if(fila < tablero.size() && columna < tablero.get(fila).size()){
                if(tablero.get(fila+1).get(columna).getColor() == 0){
                    posiciones[0] = fila+1;
                    posiciones[1] = columna;
                    encontro = true;
                }
            }
            //Revisar  derecha
            if(columna < tablero.get(fila).size()){
                if(tablero.get(fila).get(columna+1).getColor() == 0){
                    posiciones[0] = fila;
                    posiciones[1] = columna+1;
                    encontro = true;
                }
            }
        }
        if(encontro == true) return posiciones;
        else return null;
    }

}
