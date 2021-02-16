import java.awt.Graphics;
import java.io.File;

public class PantallaDeFin implements Pantalla {


    private PanelJuego panelDeJuego;
//CONSTANTES DE TEXTO
    private static final String TEXTO_FIN = "FIN DEL TIEMPO";
    private static final String TEXTO_PUNTUACION = "PUNTOS: ";
    private static final String TEXTO_RANKING = "RANKING: ";

//Dirección del fichero de texto
    private File fichero ;

    PantallaDeFin(PanelJuego panelJuego){
        this.panelDeJuego = panelJuego;
    }

    @Override
    public void inicializarPantalla() {
        if(!fichero.exists()){
            fichero = new File("src/datos/ranking.txt");
        }
        anadirAlRanking();
        leerElRanking();
    }

    @Override
    public void pintarComponentes(Graphics g) {
        // TODO Auto-generated method stub

    }

    @Override
    public void ejecutarFrame() {
        // TODO Auto-generated method stub

    }
    
    
}
