import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.awt.event.ComponentEvent;

public class PantallaDeInicio implements Pantalla {

    // IMAGEN DE FONDO
    // Almacena la imagen redimensionada
    private Image fondoRedimensionado;
    // BufferedImage del fondo
    private BufferedImage fondo;

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

        fondo = null;
        try {
            fondo = ImageIO.read(new File("Imagenes/fondo2.jpg"));
        } catch (IOException p) {
            p.printStackTrace();
        }
    }

    @Override
    public void pintarComponentes(Graphics g) {
        rellenarFondo(g);

        g.setColor(Color.BLACK);
        // Texto grande del medio
        g.setFont(funeteIni);
        g.drawString(TEXTO_INICIO, panelJuego.getWidth() / 2 - 200, panelJuego.getHeight() / 2);
        // Texto abajo izquierda
        
        g.setFont(funeteInformacion);
        g.drawString(TEXTO_INFORMACION, 25, panelJuego.getHeight() - 20);
        g.drawString(TEXTO_INFORMACION_2, 5, panelJuego.getHeight() - 5);
        // Texto abajo derecha
        g.setFont(funeteAutor);
        g.drawString(TEXTO_AUTOR, panelJuego.getWidth() - 270, panelJuego.getHeight() - 5);

    }

    @Override
    public void ejecutarFrame() {
        redimensionarFondo();
    }

    @Override
    public void pulsarRaton(MouseEvent e) {
        panelJuego.cambiarPantalla(new PantallaDeJuego(panelJuego));
    }

    @Override
    public void redimensionarPantalla(ComponentEvent e) {
        redimensionarFondo();
    }

    /**
     * Método para rellenar el fondo de la pantalla de inicio
     * 
     * @param g
     */
    private void rellenarFondo(Graphics g) {
        g.drawImage(fondoRedimensionado, 0, 0, null);
    }

    /**
     * Redimensiona el fondo
     */
    private void redimensionarFondo() {
        try {
            Thread.sleep(100);//Para que no de error, aunque tarda un poco más en refrescarse en la pantalla de inicio
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        fondoRedimensionado = fondo.getScaledInstance(panelJuego.getWidth(),panelJuego.getHeight(), Image.SCALE_SMOOTH);
    }
    
}
