
package element;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Permet de gérer la météo du jour
 * @author p2000154 VADUREL Benjamin
 * @version 1.5 Possiblité de choisir la ville et ajout d'illustration
 */
public class MeteoJour extends Element{
    //Attributs
    private Meteo etat;
    private int temperature;
    private String recommandation;
    
    //Méthodes
    /**
     * Constructeur par paramètre
     * @param etat Etat de la météo
     * @param temperature Temperature de météo
     */
    public MeteoJour(Meteo etat, int temperature) {
        this.etat = etat;
        this.temperature = temperature;
    }
    /**
     * Constructeur par défaut
     */
    public MeteoJour() {
        this.temperature = 0;
        this.recommandation = "";
    }

    /**
     * Constructeur par paramètre
     * @param coordX Positon en X de l'Element
     * @param coordY Positon en Y de l'Element
     * @param longueur Longueur de l'Element 
     * @param hauteur Hauteur de l'Element
     * @param actif True si doit apparaitre à l'écran, sinon false
     */
    public MeteoJour(int coordX, int coordY, int longueur, int hauteur, boolean actif) {
        super(coordX, coordY, longueur, hauteur, actif);
         this.temperature = 0;
        this.recommandation = "";
    }
    
    public Meteo getEtat() {
        return etat;
    }

    public void setEtat(Meteo etat) {
        this.etat = etat;
    }

    public int getTemperature() {
        return temperature;
    }
    
    public String afficheTemperature()
    {
        return temperature+"°C";
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public String getRecommandation() {
        return recommandation;
    }

    public void setRecommandation(String recommandation) {
        this.recommandation = recommandation;
    }
    
    /**
     * Retourne la météo au moment de l’appel de la fonction 
     * @param ville Ville Cible
     * @return True si la météo a été récupérée, sinon false
     */
    public boolean recupMeteoNow(String ville){
        String siteMeteo =  "http://api.openweathermap.org/data/2.5/weather?appid=0c42f7f6b53b244c78a418f4f181282a&q="+ville;
        try {
            URL lien = new URL (siteMeteo);
            URLConnection connexion = lien.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(connexion.getInputStream()));
            String inputLine;
            inputLine = br.readLine();
            recupTemperature(inputLine);
            recupState(inputLine);
            br.close();
             return true;
        } catch (Exception ex){
            System.out.println("Erreur Météo");
            etat=Meteo.neige;
            temperature=-35;
            return false;
        }
    }
    
    /**
     * Retourne la température
     * @param chaine Température
     */
    private void recupTemperature (String chaine){
        String result;
        int index1, index2;
        index1 = chaine.indexOf("temp");
        index2 = chaine.indexOf(',',index1);
        result = chaine.substring(index1+6, index2);
        double db = Double.parseDouble(result)-273;
        temperature = (int)db;
    }
    
    /**
     * Retourne un état météo
     * @param chaine État de météo
     */
    private void recupState(String chaine){
        String result="";
        int index1, index2;
        index1 = chaine.indexOf("description");
        index2 = chaine.indexOf(',',index1);
        result = chaine.substring(index1+14, index2-1);
        if (result.equals("clear sky")){
            etat = Meteo.soleil;
        }
        else if (result.equals("rain")||result.equals("shower rain") ||result.equals("light rain") ||result.equals("moderate rain") ||result.equals("heavy intensity rain") ){
            etat = Meteo.pluie;
        }
        else if (result.equals("thunderstorm")){
            etat = Meteo.orage;
        }
        else if (result.equals("snow")){
            etat = Meteo.neige;
        }
        else if (result.equals("few clouds") || result.equals("scattered clouds") || result.equals("broken clouds") ||result.equals("overcast clouds")){
            etat = Meteo.nuage;
        }
        else if (result.equals("mist")|| result.equals("fog")){
            etat = Meteo.brouillard;
        }
        else {
            System.out.println("par defaut");
            etat = Meteo.soleil;
        }
    }



    @Override
    public String toString() {
        return "etat=" + etat + '\n'+ "temperature=" + temperature;
    }

    
    
    
}
