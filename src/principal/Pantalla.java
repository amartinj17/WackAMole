
import java.awt.Graphics;

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
     * 
     */
    public void ejecutarFrame();

}