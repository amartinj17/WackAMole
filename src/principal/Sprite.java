

import java.awt.event.*;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Image;

public class Sprite{
    /**
     *
     */
    //CONSTANTES DE LAS IMAGENES
    private static final String RUTA_TOPODENTRO = "Imagenes/topo-dentro.png";
    private static final String RUTA_TOPOFUERA = "Imagenes/topo-fuera.png";


    // CONSTANTES DE LOS COLORES
    private static final Color[] LISTA_COLORES = { Color.RED, Color.GREEN };
    // Color del sprite
    private Color color;

    // Imagen del Sprite
    private BufferedImage buffer;
    // Dimensiones del Sprite
    private int ancho;
    private int alto;
    //Coordenadas ACTUALES del sprite
    private int posX;
    private int posY;
    //Imagen del Sprite 
    private String rutaImage;
    //Guarda si el topo está fuera
    private boolean estaFuera;



    public Sprite(String rutaImage, int ancho, int alto) {
        this.ancho = ancho;
        this.alto = alto;
        this.rutaImage = rutaImage;

        inicializarBuffer(rutaImage);
    }


    public boolean getEstaFuera() {
        return this.estaFuera;
    }

    public void setEstaFuera(boolean estaFuera) {
        this.estaFuera = estaFuera;
    }
    

    public void inicializarBuffer(String ruta) {
        BufferedImage imagenSprite = null;
        
        buffer = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);
        try{
            imagenSprite = ImageIO.read(new File(ruta));
            Graphics g = buffer.getGraphics();

            g.drawImage(imagenSprite.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH), 0, 0, null);

            g.dispose();     
        }catch(IOException e){
            e.printStackTrace(); 
        }
        

    }

    public void setColor(Color color) { 
        this.color = color;
    }

    public int getAncho() {
        return this.ancho;
    }

    public int getAlto() {
        return this.alto;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }
    public void setPosY(int posY) {
        this.posY = posY;
    }
    public int getPosX() {
        return posX;
    }
    public int getPosY() {
        return posY;
    }

    public Color getColor() {
        return color;
    }

    /**
     * Método que cambia el color al recibido por parámetros
     */
    public void cambiarColor(Color color) {
        buffer = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);
        Graphics g = buffer.getGraphics();

        this.setColor(color); 
        g.setColor(color);
        g.fillRect(0, 0, ancho, alto);
        g.dispose();
    }

    /**
     * Método que cambia la imagen del Sprite de forma aleatoria
     */
    public void cambiarImagen() {
        Random random = new Random();
        int intAleatorio = random.nextInt(3);
        if (intAleatorio == 0) {
            inicializarBuffer(RUTA_TOPOFUERA);
            estaFuera = true;
        } else {
            inicializarBuffer(RUTA_TOPODENTRO);
            estaFuera = false;
        }
    }

    /**
     * Método que cambia el color a rojo, cuando este ha sido pulsado
     */
    public void quitarFuera() {
        inicializarBuffer(RUTA_TOPODENTRO);
        estaFuera = false;
    }

    /**
     * Pinta el Sprite
     * 
     * @param g
     */
    public void estampar(Graphics g, double posicX, int posicY) {
        int intPosX = (int) posicX;
        g.drawImage(buffer, intPosX, posicY, null);
    }

    

}
