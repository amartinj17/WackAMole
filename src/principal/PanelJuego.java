package principal;

import javax.swing.JPanel;

import pantallas.PantallaDeInicio;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class PanelJuego extends JPanel implements Runnable, MouseListener, ComponentListener {

    private static final long serialVersionUID = 1L;

    // Variable que controla la pantalla que se ejecuta en este momento
    private Pantalla pantallaActual;

    /**
     * Constructor de PanelJuego
     */
    public PanelJuego() {

        pantallaActual = new PantallaDeInicio(this);
        pantallaActual.inicializarPantalla();

        // Inicia el hilo
        new Thread(this).start();
        this.addMouseListener(this);
    }

    /**
     * Método que se llama automáticamente para pintar el componente.
     */
    @Override
    public void paintComponent(Graphics g) {
        pantallaActual.pintarComponentes(g);
    }

    /**
     * Método run
     */
    @Override
    public void run() {

        while (true) {// Sin sleep para que refresque rápido
            pantallaActual.ejecutarFrame();

            repaint();
            Toolkit.getDefaultToolkit().sync();
        }
    }

    /**
     * Método que cambia la pantalla
     * 
     * @param nuevaPantalla
     */
    public void cambiarPantalla(Pantalla nuevaPantalla) {
        nuevaPantalla.inicializarPantalla();
        pantallaActual = nuevaPantalla;
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {
        pantallaActual.pulsarRaton(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void componentResized(ComponentEvent e) {
        pantallaActual.redimensionarPantalla(e);
    }

    @Override
    public void componentMoved(ComponentEvent e) {}

    @Override
    public void componentShown(ComponentEvent e) {}

    @Override
    public void componentHidden(ComponentEvent e) {}
    

    
}
