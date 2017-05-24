/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import vista.BotonHexagonal;
import vista.PanelPrincipal;
import vista.VistaPrincipal;

/**
 *
 * @author Jennifer
 */
public class Controlador implements ActionListener{
    PanelPrincipal panel;
    public Controlador(PanelPrincipal panel){
        this.panel = panel;
    }
    @Override
    public void actionPerformed(ActionEvent e) { 
        
        ArrayList<ArrayList<BotonHexagonal>> botones = panel.getBotones();
        for(int i = 0; i < botones.size(); i++){
            for (int j = 0; j < botones.get(i).size(); j++) {
                if(e.getSource() == botones.get(i).get(j)){
                    System.out.println("Usted esta espachurrando el boton "+i+" "+j);
                }
            }
        }        
    }
    
}
