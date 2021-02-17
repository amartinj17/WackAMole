import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class PantallaDeInicio implements Pantalla {

    // Instancia del panelJuego
    public PanelJuego panelJuego;
    // Constantes del texto
    public static final String TEXTO_INICIO = "WACK A MOLE";
    public static final String TEXTO_AUTOR = "ALBERTO MARTÍN JIMÉNEZ";
    public static final String TEXTO_INFORMACION = "Haz click sobre los topos";
    public static final String TEXTO_INFORMACION_2 = "¡CUIDADO SI NO HAN SALIDO!";
    // fuentes
    private Font funeteIni;
    private Font funeteInformacion;
    private Font funeteAutor;

    PantallaDeInicio(PanelJuego panelJuego) {
        this.panelJuego = panelJuego;
    }

    @Override
    public void inicializarPantalla() {
        funeteIni = new Font("Arial", Font.BOLD, 50);
        funeteInformacion = new Font("Arial", Font.BOLD, 15);
        funeteAutor = new Font("Arial", Font.BOLD, 20);
        ;
    }

    @Override
    public void pintarComponentes(Graphics g) {
        g.setColor(Color.BLACK);
        // Texto grande del medio
        g.setFont(funeteIni);
        g.drawString(TEXTO_INICIO, panelJuego.getWidth() / 2 - 200, panelJuego.getHeight() / 2);
        // Texto abajo izquierda
        g.setColor(Color.GRAY);
        g.setFont(funeteInformacion);
        g.drawString(TEXTO_INFORMACION, 25, panelJuego.getHeight() - 20);
        g.drawString(TEXTO_INFORMACION_2, 5, panelJuego.getHeight() - 5);
        // Texto abajo derecha
        g.setFont(funeteAutor);
        g.drawString(TEXTO_AUTOR, panelJuego.getWidth() - 270, panelJuego.getHeight() - 5);

    }

    @Override
    public void ejecutarFrame() {
    }

    @Override
    public void pulsarRaton(MouseEvent e) {
        panelJuego.cambiarPantalla(new PantallaDeJuego(panelJuego));
    }

    @Override
    public void redimensionarPantalla(ComponentEvent e) {
        // TODO Auto-generated method stub

    }

    
}
