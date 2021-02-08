package principal;

import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.ietf.jgss.GSSException;
import org.w3c.dom.css.RGBColor;
import org.w3c.dom.css.Rect;


import java.awt.image.BufferedImage;

import java.awt.*;

public class PanelJuego extends JPanel implements Runnable,ComponentListener{
    
    private static final long serialVersionUID = 1L;
    //Guarda la pantalla actual 
    private Pantalla pantallactual;

    /**
     * Constructor de PanelJuego 
     */
    public PanelJuego() {
        
    }

    /**
     * Método que se llama automáticamente para pintar el componente.
     */
    @Override
    public void paintComponent(Graphics g){
        
        
    }    

    /**
     * Método run
     */
    @Override
    public void run() {
        
    }

    @Override
    public void componentResized(ComponentEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void componentMoved(ComponentEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void componentShown(ComponentEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void componentHidden(ComponentEvent e) {
        // TODO Auto-generated method stub

    }
    
    

    
}
