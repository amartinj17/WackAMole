import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.graalvm.compiler.hotspot.stubs.NullPointerExceptionStub;

import java.awt.Font;

public class PantallaDeFin implements Pantalla {


    private PanelJuego panelJuego;
//CONSTANTES DE TEXTO
    private static final String TEXTO_FIN = "FIN DEL TIEMPO";
    private static final String TEXTO_PUNTUACION = "PUNTOS: ";
    private static final String TEXTO_RANKING = "RANKING: ";
    //Fuente de los textos
    private Font fuente; 
    private Font fuentePt; 
    private Font fuenteRan;

//Dirección del fichero de texto
    private File ficheroRanking;
    //Guarda la información del fichero de texto
    private ArrayList<Integer> topRanking;
    //Guarda la puntiación de la partida más reciente
    public int puntosPartida;
    

    PantallaDeFin(PanelJuego panelJuego){
        this.panelJuego = panelJuego;
        this.puntosPartida = PantallaDeJuego.puntos;
    }

    @Override
    public void inicializarPantalla() {
        fuente = new Font("Arial", Font.BOLD, 50);
        fuentePt = new Font("Arial", Font.BOLD, 40);
        fuenteRan = new Font("Arial", Font.BOLD, 20);

        ficheroRanking = new File("src/datos/ranking.txt");
        //Crea el fichero si no existe
        try {
            ficheroRanking.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        topRanking = leerElRanking(); 
        topRanking.add(puntosPartida);
        Collections.sort(topRanking, Integer::compareTo);
        reescribirRanking();
    }

    @Override
    public void pintarComponentes(Graphics g) {
        
        rellenarFondo(g); 
        //Texto de fin del tiempo
        g.setColor(Color.BLACK);
        g.setFont(fuente);
        g.drawString(TEXTO_FIN, panelJuego.getWidth()/2-170, panelJuego.getHeight()/4);
        
        //Texto de los puntos actuales
        g.setFont(fuentePt);
        g.drawString(TEXTO_PUNTUACION, panelJuego.getWidth()/20, (panelJuego.getHeight()/10)*7);
        g.drawString(puntosPartida+"", panelJuego.getWidth()/18, (panelJuego.getHeight()/10)*9);
                 
        //Separador
        g.drawLine(panelJuego.getWidth()/2, panelJuego.getHeight()/2, panelJuego.getWidth()/2, panelJuego.getHeight());
        
        //Texto del ranking
        g.drawString(TEXTO_RANKING, (panelJuego.getWidth()/5)*3, (panelJuego.getHeight()/10)*7);
        g.setFont(fuenteRan);
        
        try{
            g.drawString("1º.............. "+topRanking.get(topRanking.size()-1), (panelJuego.getWidth()/5)*3,(panelJuego.getHeight()/12)*9 );
        }catch(IndexOutOfBoundsException e){
            g.drawString("1º.............. "+puntosPartida, (panelJuego.getWidth()/5)*3,(panelJuego.getHeight()/12)*9 );
        }
        try{
            g.drawString("2º.............. "+topRanking.get(topRanking.size()-2), (panelJuego.getWidth()/5)*3,(panelJuego.getHeight()/12)*10 );
        }catch(IndexOutOfBoundsException e){
            g.drawString("2º.............. ______", (panelJuego.getWidth()/5)*3,(panelJuego.getHeight()/12)*10 );
        }

        try{
            g.drawString("3º.............. "+topRanking.get(topRanking.size()-3), (panelJuego.getWidth()/5)*3,(panelJuego.getHeight()/12)*11 );
        }catch(IndexOutOfBoundsException e){
            g.drawString("3º.............. ______", (panelJuego.getWidth()/5)*3,(panelJuego.getHeight()/12)*11 );
        }
        
       
    }

    @Override
    public void ejecutarFrame() {}
    
    /**
     * 
     * @return ArrayList con las puntuaciones almacenadas en el fichero
     */
    public ArrayList<Integer> leerElRanking(){
        ArrayList<Integer> ranking = new ArrayList<>();

        int num;
        try{
            BufferedReader br = new BufferedReader(new FileReader(ficheroRanking));
            String linea;
            while( (linea = br.readLine()) != null){
                try{
                    num = Integer.parseInt(linea);
                    ranking.add(num);
                }catch(NumberFormatException e){
                    
                }
            }
            br.close();

            //Ordena el ArrayList del ranking
            Collections.sort(ranking,Integer::compareTo);

            System.out.println(ranking.toString());

        }catch(IOException io){
            System.out.println("Error en la lectura del fichero: "+io.getMessage());
        }


        return ranking;
    }
    
    /**
     * Añade en el fichero del ranking la puntuación de la partida 
     */
    public void reescribirRanking(){ 
        try { 
            BufferedWriter bw = new BufferedWriter(new FileWriter(ficheroRanking));
            
            for(int i=0 ; i<topRanking.size() ; i++){
                bw.write(topRanking.get(i)+"");
                bw.newLine();
            }
            
            bw.close();

        } catch (IOException io) {
            System.out.println("Error en la E/S: " + io.getMessage());
        }
    }

    /**
     * repinta el fondo
     */ 
    private void rellenarFondo(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, panelJuego.getWidth(), panelJuego.getHeight());
    }
}
