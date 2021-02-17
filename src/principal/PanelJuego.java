
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PanelJuego extends JPanel implements Runnable, MouseListener{

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

        while (true) {// Sin sleep para que coja tods
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
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        pantallaActual.pulsarRaton(e);

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }
    

    
}
