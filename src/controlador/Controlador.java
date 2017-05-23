/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        if(e.getSource() == panel.getBoton()){
            System.out.println("Me espachurraron");
        }
    }
    
}
