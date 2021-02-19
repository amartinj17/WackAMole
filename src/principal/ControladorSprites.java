package principal;

import java.awt.Color;
import java.util.ArrayList;

import pantallas.PantallaDeJuego;

public class ControladorSprites implements Runnable{

    //Lista de cuadrados
    private ArrayList<Sprite> lSprite ;
    //Objeto del pantallaDeJuego
    private PantallaDeJuego panelDeJuego;
    //Objeto del pantallaDeFin

    public ControladorSprites(PantallaDeJuego paneldeJuego){
        this.panelDeJuego = paneldeJuego;
        this.lSprite = paneldeJuego.lSprite;
        //Inicia el hilo que cambia el color de los Sprites
        new Thread(this).start();
    }
    
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            for(Sprite cuadrado : lSprite){
                cuadrado.cambiarImagen();
            }
            panelDeJuego.logPuntos = 0;//Quita el valor de logPunto de la pantalla
        }
    }
}
