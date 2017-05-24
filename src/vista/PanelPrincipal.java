/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.Controlador;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import modelo.Ficha;
import modelo.Jugador;
import modelo.Tablero;
/**
 *
 * @author Jennifer
 */
public class PanelPrincipal extends javax.swing.JPanel {

    /**
     * Creates new form PanelPrincipal
     */
    ArrayList<ArrayList<BotonHexagonal>> hexagonos;
    ArrayList<BotonHexagonal> manoJugador;
    ArrayList<JButton> tableroJ1;
    ArrayList<JButton> tableroJ2;
    Controlador controlador;
    ImageIcon casillaVacia, fichaAmarilla, fichaRoja, fichaMorada, fichaAzul, fichaNaranja, fichaVerde;
    BotonHexagonal boton;
    Tablero tablero;
    Jugador j1;
    Jugador j2;
    public PanelPrincipal() {
        initComponents();
        this.setBackground(new Color(197, 197, 197));
        //Inicializar imagenes
        casillaVacia = new ImageIcon(getClass().getResource("/imagenes/hexagonoBase50.png"));
        fichaAmarilla = new ImageIcon(getClass().getResource("/imagenes/Amarillo50.png"));
        fichaRoja = new ImageIcon(getClass().getResource("/imagenes/Rojo50.png"));
        fichaMorada = new ImageIcon(getClass().getResource("/imagenes/Morado50.png"));
        fichaAzul = new ImageIcon(getClass().getResource("/imagenes/Azul50.png"));
        fichaNaranja = new ImageIcon(getClass().getResource("/imagenes/Naranja50.png"));
        fichaVerde = new ImageIcon(getClass().getResource("/imagenes/Verde50.png"));
        //Inicializar variables
        controlador = new Controlador(this);
        hexagonos = new ArrayList<ArrayList<BotonHexagonal>>();
        manoJugador = new ArrayList<BotonHexagonal>();
        tableroJ1 = new ArrayList<JButton>();
        tableroJ2 = new ArrayList<JButton>();
        j1 = new Jugador();
        j2 = new Jugador();
        tablero = new Tablero(j1, j2);
       
        //Margen para las fichas
        JLabel label = new JLabel();
        label.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        label.setBounds(30, 550, 550, 140);
        this.add(label);
        JLabel label2 = new JLabel("Fichas en mano:");
        label2.setBounds(30, 530, 150, 10);
        this.add(label2);
  
        crearTablero();
        tablero.juego();
        dibujarFichasMano();
        iniciarlizarTablero();
        actualizarTableros();
    }
    public ArrayList<ArrayList<BotonHexagonal>> getHexagonos(){
        return hexagonos;
    }
    public ArrayList<BotonHexagonal> getFichasMano(){
        return manoJugador;
    }
    public void crearTablero(){
        int x = 100;
        int y = 0;
        for (int i=0;i<11;i++ ) {
            if(i<6){
                ArrayList<BotonHexagonal> arreglo = new ArrayList<BotonHexagonal>();
                for (int j=0;j<i+6;j++) {
                    BotonHexagonal b = new BotonHexagonal(Color.red, Color.red, casillaVacia);
                    b.setBounds(50+x, 50+y, 50 , 50);
                    b.addActionListener(controlador);
                    arreglo.add(b);
                    this.add(b);
                    x+=52;
                }
                hexagonos.add(arreglo);
                y+=40;
                x=100-(25*(i+1));
                if(i==5) x+=50;
            }else{
                ArrayList<BotonHexagonal> arreglo = new ArrayList<BotonHexagonal>();
                for (int j=0;j<16-i;j++) {
                    BotonHexagonal b = new BotonHexagonal(Color.red, Color.red, casillaVacia);
                    b.setBounds(50+x, 50+y, 50 , 50);
                    b.addActionListener(controlador);
                    arreglo.add(b);
                    this.add(b);
                    x+=52;
                }
                hexagonos.add(arreglo);
                y+=40;
                x=0+(25*(i-5));
            }
        }
        hexagonos.get(0).get(0).setIcon(fichaAmarilla);
        hexagonos.get(0).get(5).setIcon(fichaMorada);
        hexagonos.get(5).get(0).setIcon(fichaAzul);
        hexagonos.get(5).get(10).setIcon(fichaRoja);
        hexagonos.get(10).get(0).setIcon(fichaNaranja);
        hexagonos.get(10).get(5).setIcon(fichaVerde);
    }
    public ArrayList<ArrayList<BotonHexagonal>> getBotones(){
        return hexagonos;
    }
    public void dibujarFichasMano(){
        ArrayList<Ficha> fichas = j1.getFichasActuales();
        int x = 0;
        int y = 510;
        for (int i = 0; i < fichas.size(); i++) {
            BotonHexagonal b = new BotonHexagonal(Color.red, Color.red, getImage(fichas.get(i).getColor()));
            b.setBounds(50+x, 50+y, 50 , 50);
            b.addActionListener(controlador);
            this.add(b);
            BotonHexagonal b1 = new BotonHexagonal(Color.red, Color.red, getImage(fichas.get(i).getPareja().getColor()));
            b1.setBounds(100+x, 50+y, 50 , 50);
            b1.addActionListener(controlador);
            manoJugador.add(b);
            manoJugador.add(b1);
            this.add(b1);
            x+=200;
            if(i == 2) {
                y+=70;
                x = 0;
            }
        }
    }
    public ImageIcon getImage(int color){
        if(color == 1) return fichaAmarilla;
        if(color == 2) return fichaMorada;
        if(color == 3) return fichaAzul;
        if(color == 4) return fichaRoja;
        if(color == 5) return fichaNaranja;
        if(color == 6) return fichaVerde;
        else return casillaVacia;
    }
    public void iniciarlizarTablero(){
        int x = 700;
        int y = 100;
        JLabel l = new JLabel("Puntos jugador 1:");
        l.setBounds(x, y-25, 100, 20);
        this.add(l);
        JLabel l2 = new JLabel("Puntos jugador 2:");
        l2.setBounds(x, y+175, 100, 20);
        this.add(l2);
        for(int j = 0; j < 2; j++){
           for(int i = 0; i < 19; i++){
                JButton l1 = new JButton(i+"");
                l1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
                
                //l1.setContentAreaFilled(false);
               // l1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
                l1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                l1.setBounds(x, y, 30, 20);
                l1.setBackground(Color.white);
                this.add(l1);
                x+=30;
            } 
            y+= 200;
            x=700;
        }      
        x = 700;
        y = 120;
        //Tablero jugador1
        for(int i = 0; i < 6; i++){
            for (int j = 0; j < 19; j++) {
                JLabel l1 = new JLabel();
                l1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
                l1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                l1.setBounds(x, y, 30, 20);
                this.add(l1);
                x+=30;
            }
            y+=20;
            x=700;
        }
        x = 700;
        y = 320;
        //Tablero jugador1
        for(int i = 0; i < 6; i++){
            for (int j = 0; j < 19; j++) {
                JLabel l1 = new JLabel();
                l1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
                l1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                l1.setBounds(x, y, 30, 20);
                this.add(l1);
                x+=30;
            }
            y+=20;
            x=700;
        }
        for(int i = 0; i < 2; i++){
            JButton b1 = new JButton();
            b1.setBackground(Color.YELLOW);   
            JButton b2 = new JButton();
            b2.setBackground(new Color(173,63, 174));
            JButton b3 = new JButton();
            b3.setBackground(Color.BLUE);
            JButton b4 = new JButton();
            b4.setBackground(Color.RED);
            JButton b5 = new JButton();
            b5.setBackground(Color.ORANGE);
            JButton b6 = new JButton();
            b6.setBackground(Color.GREEN);
            add(b1);
            add(b2);
            add(b3);
            add(b4);
            add(b5);
            add(b6);
            if(i == 0){
                tableroJ1.add(b1);
                tableroJ1.add(b2);
                tableroJ1.add(b3);
                tableroJ1.add(b4);
                tableroJ1.add(b5);
                tableroJ1.add(b6);
            }
            else{
                tableroJ2.add(b1);
                tableroJ2.add(b2);
                tableroJ2.add(b3);
                tableroJ2.add(b4);
                tableroJ2.add(b5);
                tableroJ2.add(b6);
            }
        }
        
        // El número depende del color, por ejemplo: 1 = Amarillo, 2 = Morado, 3 = Azul, 4 = Rojo
                // 5 = Naranja y 6 = Verde
    }
    public void actualizarTableros(){
        int puntosJ1[] = {10, 5, 0, 18, 6, 9};//j1.getPuntosColor();
        int puntosJ2[] = j2.getPuntosColor();
        int x = 700;
        int y = 120;
        
        for(int i = 0; i < puntosJ1.length; i++){
            tableroJ1.get(i).setBounds(x+(30*puntosJ1[i]), y+(20*i), 30, 20);
        }
        
        x = 700;
        y = 320;
        for(int i = 0; i < puntosJ1.length; i++){
            tableroJ2.get(i).setBounds(x+(30*puntosJ2[i]), y+(20*i), 30, 20);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buscame = new javax.swing.JButton();

        buscame.setText("jButton1");
        buscame.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        buscame.setBorderPainted(false);
        buscame.setContentAreaFilled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(393, 393, 393)
                .addComponent(buscame)
                .addContainerGap(912, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(257, 257, 257)
                .addComponent(buscame)
                .addContainerGap(424, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buscame;
    // End of variables declaration//GEN-END:variables
}
