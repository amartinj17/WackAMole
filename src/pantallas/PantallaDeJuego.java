
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.MouseEvent;

public class PantallaDeJuego implements Pantalla {
//CONSTANTES DE LA PUNTIACIÓN
    //Acierto 
    private static final int ACIERTO = 1;
    //Fallo
    private static final int FALLO = -1;
// CONSTANTES DE LOS SPRITES
    // Constantes que guarda el numero de sprites
    private static final int CUADRADOS_NUMEROS = 4;
    // Alto
    private static final int CUADRADOS_WIDTH = 40;
    // Ancho
    private static final int CUADRADOS_HEIGTH = 40;
    // CONSTANTE DEL CRONÓMETRO
    // Tiempo máximo de la partida en segundos
    private static final int TIEMPO_PARTIDA = 5;                           //PONER A 90!!!!!!!!!!!!!!!!
    // CONSTANTES DEL FONDO
    private static final Color COLOR_FONDO = Color.LIGHT_GRAY;
    // CONTROL DEL TIEMPO
    private long tiempoInicial = 30000;
    // Guarda el timepo que ha pasado
    private double contadorTiempo;
    // Fuente del cronometro
    private Font fuente;
    // CONTROL DE LOS PUNTOS
    // controla el marcador de los puntos
    public static int puntos;
    // Log de los puntos
    public int logPuntos;

    // Guarda la lista de los Sprite
    public ArrayList<Sprite> lSprite;
    private Sprite cuadrado;

    // Guarda el panel actual
    public PanelJuego panelJuego;

    public PantallaDeJuego(PanelJuego panelJuego) {
        this.panelJuego = panelJuego;
    }

    @Override
    public void inicializarPantalla() {
        lSprite = new ArrayList<>();
        for (int i = 0; i < CUADRADOS_NUMEROS; i++) {
            cuadrado = new Sprite(CUADRADOS_WIDTH, CUADRADOS_HEIGTH);
            lSprite.add(cuadrado);
        }

        // Lanza el hilo de control de los Sprites 
        new ControladorSprites(this);

        tiempoInicial = System.currentTimeMillis();
        fuente = new Font("Arial", Font.BOLD, 30);
        // Inicializa el log
        logPuntos = 0;
    }

    @Override
    public void pintarComponentes(Graphics g) {
        rellenarFondo(g);
        colocarSprites(g);

        // Pinta el recuadro del cronómetro
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 110, 60);
        g.setColor(COLOR_FONDO);
        g.fillRect(5, 5, 100, 50);
        // Pinta los numeros del cronómetro
        g.setColor(Color.BLACK);
        g.setFont(fuente);
        g.drawString(String.format("%.2f", TIEMPO_PARTIDA - contadorTiempo / 1000), 10, 40);
        
        g.setColor(Color.BLACK);
        g.fillRect(panelJuego.getWidth() - 210, 0, 220, 60);

        g.setColor(COLOR_FONDO);
        g.fillRect(panelJuego.getWidth() - 205, 5, 200, 50);
        g.setColor(Color.BLACK);
        g.drawString("PUNTOS: " + puntos, panelJuego.getWidth() - 200, 45);
        // Pinta el log de los aciertos o fallos
        if (logPuntos != 0) {
            if (logPuntos == 1) {
                g.setColor(new Color(0, 227, 40));
                g.drawString("+" + logPuntos, panelJuego.getWidth() - 200, 90);
            } else {
                if (logPuntos == -1) {
                    g.setColor(Color.RED);
                    g.drawString("" + logPuntos, panelJuego.getWidth() - 200, 90);
                }
            }
        }

    }

    @Override
    public void ejecutarFrame() {
        contadorTiempo = (System.currentTimeMillis() - tiempoInicial);

        if (contadorTiempo / 1000 >= TIEMPO_PARTIDA) {
            panelJuego.cambiarPantalla(new PantallaDeFin(panelJuego));
        }

    }

    @Override
    public void pulsarRaton(MouseEvent e) {
        for(int i=0 ; i<lSprite.size() ; i++){
            if(coliEjeX(lSprite.get(i), e.getX())){
                if(coliEjeY(lSprite.get(i), e.getY())){
                    if(lSprite.get(i).getColor() == Color.GREEN){
                        lSprite.get(i).quitarColorVerde();
                        PantallaDeJuego.puntos = PantallaDeJuego.puntos + ACIERTO;
                        logPuntos = 1;
                    }else{
                        if(lSprite.get(i).getColor() == Color.RED){
                            puntos = puntos + FALLO; 
                            logPuntos = -1; 
                        }
                    }
                }
            }
        }
    }

    /**
     * Coloca los cuadrados
     */
    public void colocarSprites(Graphics g) {
        for (int i = 0; i < lSprite.size(); i++) {
            lSprite.get(i).setPosX((int) ((panelJuego.getWidth() / CUADRADOS_NUMEROS) * (i + 0.5)));
            lSprite.get(i).setPosY(panelJuego.getHeight() / 2);
            lSprite.get(i).estampar(g, lSprite.get(i).getPosX(), lSprite.get(i).getPosY());
        }
    }

    /**
     * repinta el fondo
     */
    private void rellenarFondo(Graphics g) {
        g.setColor(COLOR_FONDO);
        g.fillRect(0, 0, panelJuego.getWidth(), panelJuego.getHeight());
    }

    /**
     * 
     * @param cuadrado  
     * @param posRatonX
     * @return true si la posicion X del ratón, está entre las coordenadas X de el Sprite, false si no se cumple.
     */
    public boolean coliEjeX(Sprite cuadrado , int posRatonX){
        boolean colisionEjeX;

        if(posRatonX < cuadrado.getPosX()){
            colisionEjeX = (posRatonX >= cuadrado.getPosX());  
        }else{
            colisionEjeX = ((cuadrado.getPosX() + cuadrado.getAncho()) >= posRatonX);
        }

        return colisionEjeX;
    }

    /**
     * 
     * @param cuadrado  
     * @param posRatonY
     * @return true si la posicion Y del ratón, está entre las coordenadas Y de el Sprite, false si no se cumple.
     */
    public boolean coliEjeY(Sprite cuadrado , int posRatonY) {
        boolean colisionEjeY;
        if(posRatonY < cuadrado.getPosY()){
            colisionEjeY = posRatonY >= cuadrado.getPosY();
        }else{
            colisionEjeY = cuadrado.getPosY() + cuadrado.getAncho() >= posRatonY; 
        }
        return colisionEjeY;
    }

    
}
