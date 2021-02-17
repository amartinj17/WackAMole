
import java.util.ArrayList;
import java.awt.*;

public class PantallaDeJuego implements Pantalla{

    //CONSTANTES DE LOS SPRITES
        //Constantes que guarda el numero de sprites
        private static final int CUADRADOS_NUMEROS = 4;
        //Alto
        private static final int CUADRADOS_WIDTH = 40;
        //Ancho
        private static final int CUADRADOS_HEIGTH = 40;
    //CONSTANTE DEL CRONÓMETRO
        //Tiempo máximo de la partida en segundos
        private static final int TIEMPO_PARTIDA = 1;               //poner a 120!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
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
        public static int puntos;
        //Log de los puntos
        public int logPuntos;
    
    //Guarda la lista de los Sprite 
        public ArrayList<Sprite> lSprites;
        private Sprite cuadrado;
    
    //Guarda el panel actual
        public PanelJuego panelJuego;

    public PantallaDeJuego(PanelJuego panelJuego){
        this.panelJuego = panelJuego;
    }

    @Override
    public void inicializarPantalla()  { 
        lSprites = new ArrayList<>();
        for(int i=0 ; i<CUADRADOS_NUMEROS ; i++){
            cuadrado = new Sprite(CUADRADOS_WIDTH, CUADRADOS_HEIGTH); 
            lSprites.add(cuadrado); 
        }  

        //Añade el listener en el panel de Juego
        panelJuego.addMouseListener(new ControladorListeners(this));

        tiempoInicial = System.currentTimeMillis();
        hayCronometro = true;
        fuente = new Font("Arial",Font.BOLD,30);
        //Inicializa el log
        logPuntos = 0;
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
            g.drawString(String.format("%.2f",TIEMPO_PARTIDA - contadorTiempo/1000) , 10, 40);
        }
        g.setColor(Color.BLACK);
        g.fillRect(panelJuego.getWidth()-210, 0, 220, 60);
        
        g.setColor(COLOR_FONDO);
        g.fillRect(panelJuego.getWidth()-205, 5, 200, 50);
        g.setColor(Color.BLACK);
        g.drawString("PUNTOS: "+puntos, panelJuego.getWidth()-200, 45);
        //Pinta el log de los aciertos o fallos
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
    public void ejecutarFrame() {
        contadorTiempo = (System.currentTimeMillis() - tiempoInicial);

        if(contadorTiempo/1000 >= TIEMPO_PARTIDA){
            hayCronometro = false;
        }

        if(!hayCronometro){
            panelJuego.cambiarPantalla(new PantallaDeFin(panelJuego));
        }

        
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
     * repinta el fondo
     */ 
    private void rellenarFondo(Graphics g) {
        g.setColor(COLOR_FONDO);
        g.fillRect(0, 0, panelJuego.getWidth(), panelJuego.getHeight());
    }
    
}
