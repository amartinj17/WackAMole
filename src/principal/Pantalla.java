
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.ComponentEvent;

public interface Pantalla{
    
    /**
     * Inicializa la pantalla
     */
    public void inicializarPantalla();

     /**
     * Pinta los componentes
     */
    public void pintarComponentes(Graphics g);

    /**
     * Ejecuta un frame
     */
    public void ejecutarFrame();

    /**
     * Al hace click
     */
    public void pulsarRaton(MouseEvent e);

    /**
     * Acción al redimensionar la pantalla
     */
    public void redimensionarPantalla(ComponentEvent e);
}