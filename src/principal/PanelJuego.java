package principal;

import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.ietf.jgss.GSSException;
import org.w3c.dom.css.RGBColor;
import org.w3c.dom.css.Rect;


import java.awt.image.BufferedImage;

import java.awt.*;

public class PanelJuego extends JPanel implements Runnable,ComponentListener{
    
    private static final long serialVersionUID = 1L;
//CONSTANTES DE LOS SPRITES
    //Constantes que guarda el numero de sprites
    private static final int CUADRADOS_NUMEROS = 4;
    //Alto
    private static final int CUADRADOS_WIDTH = 40;
    //Ancho
    private static final int CUADRADOS_HEIGTH = 40;
//CONSTANTE DEL CRONÓMETRO
    //Tiempo máximo de la partida en segundos
    private static final int TIEMPO_PARTIDA = 60;
//CONSTANTES DEL FONDO
    private static final Color COLOR_FONDO = Color.LIGHT_GRAY;
//CONTROL DEL TIEMPO
    private long tiempoInicial = 30000; 
    //Guarda el timepo que ha pasado
    private double contadorTiempo;
    //Fuente del cronometro
    private Font fuente;
    //Almacena si hay o no cronometro
    private boolean hayCronometro;
//CONTROL DE LOS PUNTOS
    //controla el marcador de los puntos
    public int puntos;
    //Log de los puntos
    public int logPuntos;

//Guarda la lista de los Sprite 
    public ArrayList<Sprite> lSprites;
    private Sprite cuadrado;

    /**
     * Constructor de PanelJuego 
     */
    public PanelJuego() {

        lSprites = new ArrayList<>();
        for(int i=0 ; i<CUADRADOS_NUMEROS ; i++){
            cuadrado = new Sprite(CUADRADOS_WIDTH, CUADRADOS_HEIGTH); 
            lSprites.add(cuadrado); 
        }   
        
        this.addMouseListener(new ControladorSprites(this));

        tiempoInicial = System.currentTimeMillis();
        hayCronometro = true;
        fuente = new Font("Arial",Font.BOLD,30);
        //Inicializa el log
        logPuntos = 0;
        //Inicia el hilo
        new Thread(this).start();
    }

    /**
     * Método que se llama automáticamente para pintar el componente.
     */
    @Override
    public void paintComponent(Graphics g){
        rellenarFondo(g); 
        colocarSprites(g);
        //Cronómetro
        if(hayCronometro){
            //Pinta el recuadro del cronómetro
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, 110, 60);
            g.setColor(COLOR_FONDO); 
            g.fillRect(5, 5, 100, 50);
            //Pinta los numeros del cronómetro  
            g.setColor(Color.BLACK);
            g.setFont(fuente);
            g.drawString(String.format("%.2f",TIEMPO_PARTIDA - contadorTiempo/1000) , 7, 40);
        }
        g.setColor(Color.BLACK);
        g.fillRect(getWidth()-210, 0, 220, 60);
        
        g.setColor(COLOR_FONDO);
        g.fillRect(getWidth()-205, 5, 200, 50);
        g.setColor(Color.BLACK);
        g.drawString("PUNTOS: "+puntos, getWidth()-200, 45);
        //Pinta el log de los aciertos o fallos
        g.setColor(COLOR_FONDO); 
        g.fillRect(getWidth()-200, 60,200,90);
        if(logPuntos != 0){
            if(logPuntos == 1){
                g.setColor(new Color(0, 227, 40));
                g.drawString("+"+logPuntos, getWidth()-200, 90);
            }else{
                if(logPuntos == -1){
                    g.setColor(Color.RED);
                    g.drawString(""+logPuntos, getWidth()-200, 90);
                }
            }
        }
        
    }    

    /**
     * repinta el fondo
     */ 
    private void rellenarFondo(Graphics g) {
        g.setColor(COLOR_FONDO);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    /**
     * Coloca los cuadrados
     */
    public void colocarSprites(Graphics g){
        for(int i=0 ; i<lSprites.size() ; i++){
            lSprites.get(i).setPosX((int) ((getWidth()/CUADRADOS_NUMEROS)*(i+0.5)));
            lSprites.get(i).setPosY(getHeight()/2);
            lSprites.get(i).estampar(g, lSprites.get(i).getPosX(), lSprites.get(i).getPosY()); 
        }
    }

    /**
     * Método run
     */
    @Override
    public void run() {
        
        while (true) {//Sin sleep para que coja tods
            contadorTiempo = (System.currentTimeMillis() - tiempoInicial);
            if(contadorTiempo/1000 >= TIEMPO_PARTIDA){
                hayCronometro = false;
            }

            repaint();
            Toolkit.getDefaultToolkit().sync();
        }
    }

    @Override
    public void componentResized(ComponentEvent e) {

    }

    @Override
    public void componentMoved(ComponentEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void componentShown(ComponentEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void componentHidden(ComponentEvent e) {
        // TODO Auto-generated method stub

    }
    
    

    /**
     * Método que cambia la pantalla
     * @param nuevaPantalla
     
	public void cambiarPantalla(Pantalla nuevaPantalla) {
        nuevaPantalla.inicializarPantalla();
        pantallaActual = nuevaPantalla;
	}*/
}
