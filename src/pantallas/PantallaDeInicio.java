import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class PantallaDeInicio implements Pantalla {

    // Instancia del panelJuego
    public PanelJuego panelJuego;
    // Constantes del texto
    public static final String TEXTO_INICIO = "INICIO";
    public static final String TEXTO_INFORMACION = "Haz click sobre los topos que estén fuera, ¡CUIDADO CON PULSAR SI NO HAN SALIDO!";

    PantallaDeInicio(PanelJuego panelJuego) {
        this.panelJuego = panelJuego;
    }

    @Override
    public void inicializarPantalla() {

    }

    @Override
    public void pintarComponentes(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, panelJuego.getWidth(), panelJuego.getHeight());

    }

    @Override
    public void ejecutarFrame() {
        // TODO Auto-generated method stub

    }

    @Override
    public void pulsarRaton(MouseEvent e) {
        panelJuego.cambiarPantalla(new PantallaDeJuego(panelJuego));

    }

    
}
