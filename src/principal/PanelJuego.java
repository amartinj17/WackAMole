
import javax.swing.JPanel;
import java.awt.*;

public class PanelJuego extends JPanel implements Runnable{
    
    private static final long serialVersionUID = 1L;

    //Variable que controla la pantalla que se ejecuta en este momento
    private Pantalla pantallaActual;

    /**
     * Constructor de PanelJuego 
     */
    public PanelJuego() {

        pantallaActual = new PanelDeJuego(this);
        pantallaActual.inicializarPantalla();
       
        //Inicia el hilo
        new Thread(this).start();
    }

    /**
     * Método que se llama automáticamente para pintar el componente.
     */
    @Override
    public void paintComponent(Graphics g){
        pantallaActual.pintarComponentes(g);
    }    

    /**
     * Método run
     */
    @Override
    public void run() {
        
        while (true) {//Sin sleep para que coja tods
            pantallaActual.ejecutarFrame();

            repaint();
            Toolkit.getDefaultToolkit().sync();
        }
    }

   /**
     * Método que cambia la pantalla
     * @param nuevaPantalla
     */
	public void cambiarPantalla(Pantalla nuevaPantalla) {
        nuevaPantalla.inicializarPantalla();
        pantallaActual = nuevaPantalla;
	}
    

    
}
