
package element;

import element.Element;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
/**
 * Permet de stocker et récupérer les actualités du jour
 * @author p2000154 VADUREL Benjamin
 * @version 1.2 Corrections de bugs pour la version PC
 */
public class InformationDuJour extends Element {
    private ArrayList<Info> listInfo;
    
    /**
     * Constructeur par défault
    */
     public InformationDuJour() {
         super(0,0,0,0,false);
         listInfo = new ArrayList<Info>();
    }

    /**
     *Constructeur par paramètre
     * @param coordX Positon en X de l'Element
     * @param coordY Positon en Y de l'Element
     * @param longueur Longueur de l'Element 
     * @param hauteur Hauteur de l'Element
     * @param actif True si doit apparaitre à l'écran, sinon false
     */
    public InformationDuJour(int coordX, int coordY, int longueur, int hauteur, boolean actif) {
        super(coordX, coordY, longueur, hauteur, actif);
        listInfo = new ArrayList<Info>();
    }
    
    /**
     *Constructeur par paramètre simplifié
     * @param e Définit les coordonées de l'élément
     */
    public InformationDuJour(Element e){
        super(e);
        listInfo = new ArrayList<Info>();
    }
     
     

     /**
     * Rempli l'attribut listInfo avec des informations récupérées au lien : https://news.upday.com/fr/
     * Ne renvoie rien
    */
    public void recupInfo() {
        String siteInfo =  "https://news.upday.com/fr/";
        try {
            URL lien = new URL (siteInfo);
            URLConnection connexion = lien.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(connexion.getInputStream()));
            String tmp = "";
            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                if (inputLine.contains("<a class=\"a--unstyled\"")){
                    tmp = retirerBaliseLien(inputLine);
                }
                else if(inputLine.contains("<h2>")){
                    inputLine = retirerBaliseTexte(inputLine);
                    listInfo.add(new Info(inputLine, tmp));
                }
            }
            br.close();
        } catch (Exception ex){
                System.out.println("Error");
                
                 for (int i=0;i<5;i++)
                 {
                     listInfo.add(new Info("Pas de connexion a internet",""));
                 }
        }
    }
    
    
     /**
     * retourne la liste d'information du rang index
     * @param index Rang de l'info a retourner
     * @return Info
    */
    public Info getInfoNumero(int index)
    {
        return listInfo.get(index);
    }

    /**
     * Reçoit une chaine de caractère et retire les balises HTML du type : Titre, paragraphe, etc...
     * @param chaine Une ligne en HTML
     * @return La chaine transformée
     */
    private String retirerBaliseTexte (String chaine){
        String result;
        int index1;
        index1 = chaine.indexOf('>');
        int index2;
        index2 = chaine.indexOf('<',index1);
        result = chaine.substring(index1+1, index2);
        return result;
    }
    
    /**
     * Reçoit une chaine de caractère et récupère le lien à partir d'une balise a href en HTML
     * @param chaine Une ligne de code en HTML
     * @return La chaine transformée
     */
    private String retirerBaliseLien(String chaine){
        String result;
        int index1, index2;
        index1 = chaine.indexOf("href=");
        index2 = chaine.indexOf('"', index1+6);
        result = chaine.substring(index1+6, index2);
        return result;
    }

    @Override
    public String toString() {
        String result ="";
        for (int i =  0; i < listInfo.size(); i++){
            result += "Info n"+i+" : "+ listInfo.get(i) + '\n';
        }
        return result;
    }
    
    
    
    
    /**
    * Ajouter une information à la liste
     * @param f Information à ajouter dans listInfo
    */
    public void addInfo(Info f)
    {
        listInfo.add(f);
    }
    
    
    
    /**
    * Enlever une information de la liste
     * @param index Index de l'information à retirer
    */
    public void removeInfo(int index)
    {
        listInfo.remove(index);
    }
    
    
}
