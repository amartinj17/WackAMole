
import java.awt.Graphics;
import java.awt.event.MouseEvent;

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
}