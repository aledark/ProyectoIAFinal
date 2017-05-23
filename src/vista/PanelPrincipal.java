/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.Controlador;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author Jennifer
 */
public class PanelPrincipal extends javax.swing.JPanel {

    /**
     * Creates new form PanelPrincipal
     */
    ArrayList<ArrayList<BotonHexagonal>> hexagonos;
    Controlador controlador;
    ImageIcon casillaVacia;
    BotonHexagonal boton;
    public PanelPrincipal() {
        initComponents();
        casillaVacia = new ImageIcon(getClass().getResource("/imagenes/hexagonoBase.png"));
        controlador = new Controlador(this);
        crearTablero(controlador);
    }
    public ArrayList<ArrayList<BotonHexagonal>> getHexagonos(){
        return hexagonos;
    }
    public void crearTablero(Controlador controlador){
        boton = new BotonHexagonal(Color.red, Color.red, casillaVacia);
        boton.setBounds(50, 50, 100, 100);
        boton.addActionListener(controlador);
        this.add(boton);
    }
    public BotonHexagonal getBoton(){
        return boton;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 980, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 780, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
