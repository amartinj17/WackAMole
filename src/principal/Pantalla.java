
package principal;


import java.awt.Graphics;

public interface Pantalla{
    /**
     * Pinta los componentes en el panel
     */
    public void pintarComponentes(Graphics g);

    /**
     * Lo que hace el hilo
     */
    public void accionHilo();

    /**
     * Inicializa la pantalla
     */
    public void inicializarPantalla();
}