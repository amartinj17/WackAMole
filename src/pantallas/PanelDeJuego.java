package pantallas;

import principal.ControladorSprites;
import principal.PanelJuego;
import principal.Pantalla;
import principal.Sprite;

import java.awt.Graphics;

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
public class PanelDeJuego implements Pantalla {

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
    //PanelJuego 
    private PanelJuego panelJuego;

    PanelDeJuego(PanelJuego pj){
        panelJuego = pj;
    }

    @Override
    public void pintarComponentes(Graphics g) {
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
        g.fillRect(panelJuego.getWidth()-210, 0, 220, 60);
        
        g.setColor(COLOR_FONDO);
        g.fillRect(panelJuego.getWidth()-205, 5, 200, 50);
        g.setColor(Color.BLACK);
        g.drawString("PUNTOS: "+puntos, panelJuego.getWidth()-200, 45);
        //Pinta el log de los aciertos o fallos
        g.setColor(COLOR_FONDO); 
        g.fillRect(panelJuego.getWidth()-200, 60,200,90);
        if(logPuntos != 0){
            if(logPuntos == 1){
                g.setColor(new Color(0, 227, 40));
                g.drawString("+"+logPuntos, panelJuego.getWidth()-200, 90);
            }else{
                if(logPuntos == -1){
                    g.setColor(Color.RED);
                    g.drawString(""+logPuntos, panelJuego.getWidth()-200, 90);
                }
            }
        }
    }

    @Override
    public void accionHilo() {
        while (true) {//Sin sleep para que coja tods
            contadorTiempo = (System.currentTimeMillis() - tiempoInicial);
            if(contadorTiempo/1000 >= TIEMPO_PARTIDA){
                hayCronometro = false;
            }

            panelJuego.repaint();
            Toolkit.getDefaultToolkit().sync();
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
     * Coloca los cuadrados
     */
    public void colocarSprites(Graphics g){
        for(int i=0 ; i<lSprites.size() ; i++){
            lSprites.get(i).setPosX((int) ((panelJuego.getWidth()/CUADRADOS_NUMEROS)*(i+0.5)));
            lSprites.get(i).setPosY(panelJuego.getHeight()/2);
            lSprites.get(i).estampar(g, lSprites.get(i).getPosX(), lSprites.get(i).getPosY()); 
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

    @Override
    public void inicializarPantalla() {
        lSprites = new ArrayList<>();
        for(int i=0 ; i<CUADRADOS_NUMEROS ; i++){
            cuadrado = new Sprite(CUADRADOS_WIDTH, CUADRADOS_HEIGTH); 
            lSprites.add(cuadrado); 
        }   
        
        panelJuego.addMouseListener(new ControladorSprites(this));

        tiempoInicial = System.currentTimeMillis();
        hayCronometro = true;
        fuente = new Font("Arial",Font.BOLD,30); 
        //Inicializa el log
        logPuntos = 0;
        //Inicia el hilo
        new Thread(panelJuego).start();
    }

}
