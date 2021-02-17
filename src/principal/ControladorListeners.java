

import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;

public class ControladorListeners extends MouseAdapter implements Runnable{
//CONSTANTES DE LA PUNTIACIÓN
    //Acierto 
    private static final int ACIERTO = 1;
    //Fallo
    private static final int FALLO = -1;

    //Lista de cuadrados
    private ArrayList<Sprite> lSprite ;
    //Objeto del pantallaDeJuego
    private PantallaDeJuego panelDeJuego;
    //Objeto del pantallaDeFin
    private PantallaDeFin pantallaDeFin;
    //Objeto del pantallaDeInicio
    private PantallaDeInicio pantallaDeInicio;

    //Indica en qué pantalla está para no liar los listeners
    private int pantalla;

    ControladorListeners(PantallaDeJuego paneldeJuego){
        this.panelDeJuego = paneldeJuego;
        this.lSprite = paneldeJuego.lSprites;
        pantalla = 1;
        //Inicia el hilo que cambia el color de los Sprites
        new Thread(this).start();
    }

    ControladorListeners(PantallaDeFin pantallaDeFin){
        this.pantallaDeFin = pantallaDeFin;
        pantalla = 2;
    }

    ControladorListeners(PantallaDeInicio pantallaDeInicio){
        this.pantallaDeInicio = pantallaDeInicio;
        pantalla = 0;
    }
    /**
     * Al hacer click, calcula si en las cordeenadas, hay un Sprite o no
     */
    @Override
    public void mouseClicked(MouseEvent e){
        switch(pantalla){
            case 0:{
                pantallaDeInicio.panelJuego.cambiarPantalla(new PantallaDeJuego(pantallaDeInicio.panelJuego));
                System.out.println("pulsado");
                break;
            }
            case 1:{
                for(int i=0 ; i<lSprite.size() ; i++){
                    if(coliEjeX(lSprite.get(i), e.getX())){
                        if(coliEjeY(lSprite.get(i), e.getY())){
                            if(lSprite.get(i).getColor() == Color.GREEN){
                                lSprite.get(i).quitarColorVerde();
                                panelDeJuego.puntos = panelDeJuego.puntos + ACIERTO;
                                panelDeJuego.logPuntos = 1;
                            }else{
                                if(lSprite.get(i).getColor() == Color.RED){
                                    panelDeJuego.puntos = panelDeJuego.puntos + FALLO; 
                                    panelDeJuego.logPuntos = -1; 
                                }
                            }
                        }
                    }
                }
                break;
            }
            case 2:{
                pantallaDeFin.panelJuego.cambiarPantalla(new PantallaDeJuego(pantallaDeFin.panelJuego));
                break;
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
            panelDeJuego.logPuntos = 0;//Quita el valor de logPunto de la pantalla
        }
    }
}
