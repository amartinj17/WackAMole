package principal;

import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;

public class ControladorSprites extends MouseAdapter implements Runnable{
//CONSTANTES DE LA PUNTIACIÓN
    //Acierto 
    private static final int ACIERTO = 1;
    //Fallo
    private static final int FALLO = -1;

    //Lista de cuadrados
    private ArrayList<Sprite> lSprite ;
    //Objeto del panelJuego
    private PanelJuego panelJuego;

    ControladorSprites(PanelJuego panelJuego){
        this.panelJuego = panelJuego;
        this.lSprite = panelJuego.lSprites;
        //Inicia el hilo que cambia el color de los Sprites
        new Thread(this).start();
    }
    /**
     * Al hacer click, calcula si en las cordeenadas, hay un Sprite o no
     */
    @Override
    public void mouseClicked(MouseEvent e){
        System.out.println(e.getX()+" "+e.getY());
        for(int i=0 ; i<lSprite.size() ; i++){
            if(coliEjeX(lSprite.get(i), e.getX())){
                if(coliEjeY(lSprite.get(i), e.getY())){
                    if(lSprite.get(i).getColor() == Color.GREEN){
                        lSprite.get(i).quitarColorVerde();
                        panelJuego.puntos = panelJuego.puntos + ACIERTO;
                        panelJuego.logPuntos = 1;
                    }else{
                        if(lSprite.get(i).getColor() == Color.RED){
                            panelJuego.puntos = panelJuego.puntos + FALLO; 
                            panelJuego.logPuntos = -1; 
                        }
                    }
                }
            }
        }
    }

    public boolean coliEjeX(Sprite cuadrado , int posRatonX){
        boolean colisionEjeX;

        if(posRatonX < cuadrado.getPosX()){
            colisionEjeX = (posRatonX >= cuadrado.getPosX());  
        }else{
            colisionEjeX = ((cuadrado.getPosX() + cuadrado.getAncho()) >= posRatonX);
        }

        return colisionEjeX;
    }

    public boolean coliEjeY(Sprite cuadrado , int posRatonY) {
        boolean colisionEjeY;
        if(posRatonY < cuadrado.getPosY()){
            colisionEjeY = posRatonY >= cuadrado.getPosY();
        }else{
            colisionEjeY = cuadrado.getPosY() + cuadrado.getAncho() >= posRatonY; 
        }
        return colisionEjeY;
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
                cuadrado.cambiarColorAleatorio();
            }
            panelJuego.logPuntos = 0;//Quita el valor de logPunto de la pantalla
        }
    }
}
