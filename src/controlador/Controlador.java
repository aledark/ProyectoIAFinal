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
    int colorAnterior;
    BotonHexagonal botonParejaAnterior;
    int colorParejaAnterior;
    int fila1, columna1, fila2, columna2;
    MouseEvent e;
    public Controlador(PanelPrincipal panel){
        this.panel = panel;
        fichaSeleccionada = null;
        botonAnterior = null;
        colorAnterior = 0;
        botonParejaAnterior = null;
        colorParejaAnterior = 0;
        lado = 0;
        fila1 = -1;
        fila2 = -1;
        columna1 = -1;
        columna2 = -1;
        e = null;
    }

    @Override
    public void mouseClicked(MouseEvent e){
        if(e.getButton() == 1){
            ArrayList<ArrayList<BotonHexagonal>> botones = panel.getBotones();
            /*for(int i = 0; i < botones.size(); i++){
                for (int j = 0; j < botones.get(i).size(); j++) {
                    if(e.getSource() == botones.get(i).get(j)){
                        System.out.println("Usted esta espachurrando el boton "+i+" "+j);
                    }*/
                    if(botonParejaAnterior != null){
                        System.out.println(fila1+" "+columna1);
                        System.out.println(fila2+" "+columna2);
                    //}
                    
                //}
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
            botonAnterior.setIcon(panel.getImage(colorAnterior));
        }
        for(int i = 0; i < botones.size(); i++){
            for (int j = 0; j < botones.get(i).size(); j++) {
                if(e.getSource() == botones.get(i).get(j)){
                    if(fichaSeleccionada != null && tablero.get(i).get(j).getColor() == 0){
                        botonAnterior = botones.get(i).get(j);
                        colorAnterior = tablero.get(i).get(j).getColor();
                        botones.get(i).get(j).setIcon(panel.getImage(fichaSeleccionada.getColor()));
                        dibujarPareja(i,j, botones, tablero);  
                        fila1 = i;
                        columna1 = j;
                    }
                    else{
                         if(botonParejaAnterior != null && botonParejaAnterior != botonAnterior){
                            botonParejaAnterior.setIcon(panel.getImage(colorParejaAnterior));
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
            botonParejaAnterior.setIcon(panel.getImage(colorParejaAnterior));
        }
       
        if(i <=4){
            if(lado == 0 && i > 0 && j > 0){
                if(tablero.get(i-1).get(j-1).getColor() == 0){
                    botonParejaAnterior = botones.get(i-1).get(j-1);
                    colorParejaAnterior = tablero.get(i-1).get(j-1).getColor();
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
                        colorParejaAnterior = tablero.get(i-1).get(j).getColor();
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
                        colorParejaAnterior = tablero.get(i).get(j-1).getColor();
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
                        colorParejaAnterior = tablero.get(i+1).get(j).getColor();
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
                        colorParejaAnterior = tablero.get(i+1).get(j+1).getColor();
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
                        colorParejaAnterior = tablero.get(i).get(j+1).getColor();
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
                    colorParejaAnterior = tablero.get(i-1).get(j).getColor();
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
                        colorParejaAnterior = tablero.get(i-1).get(j+1).getColor();
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
                        colorParejaAnterior = tablero.get(i).get(j-1).getColor();
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
                    colorParejaAnterior = tablero.get(i+1).get(j-1).getColor();
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
                        colorParejaAnterior = tablero.get(i+1).get(j).getColor();
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
                    colorParejaAnterior = tablero.get(i).get(j+1).getColor();
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
                    colorParejaAnterior = tablero.get(i-1).get(j-1).getColor();
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
                        colorParejaAnterior = tablero.get(i-1).get(j).getColor();
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
                        colorParejaAnterior = tablero.get(i).get(j-1).getColor();
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
                    colorParejaAnterior = tablero.get(i+1).get(j-1).getColor();
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
                        colorParejaAnterior = tablero.get(i+1).get(j).getColor();
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
                    colorParejaAnterior = tablero.get(i).get(j+1).getColor();
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
    
}
