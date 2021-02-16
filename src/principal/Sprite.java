

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

    public Sprite(int ancho, int alto) { 
        this.ancho = ancho; 
        this.alto = alto;
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
     * Método que cambia de color entre rojo y verde de forma aleatoria
     */
    public void cambiarColorAleatorio() {
        Random random = new Random();
        int intAleatorio = random.nextInt(4);
        if (intAleatorio == 1) {
            cambiarColor(LISTA_COLORES[1]);
        } else {
            cambiarColor(LISTA_COLORES[0]);
        }
    }

    /**
     * Método que cambia el color a rojo, cuando este ha sido pulsado
     */
    public void quitarColorVerde() {
        cambiarColor(LISTA_COLORES[0]);  
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
