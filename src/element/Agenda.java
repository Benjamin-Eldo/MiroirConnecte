

package element;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import miroir.Enregistrement;

/**
 * Permet de stocker,sauvegarder et récupérer les évenements
 * @author p2000154 VADUREL Benjamin
 * @version 1.3 Corrections de bugs
 */
public class Agenda extends Element implements Enregistrement {
     /**
      * @param listRappel - Liste qui stocke tout les évènement de l'agenda
     */
     private ArrayList<Rappel> listRappel;

    /**
     *Constructeur par défault
     * @param coordX - Positon en X de l'Element
     * @param coordY - Positon en Y de l'Element
     * @param longueur - Longueur de l'Element 
     * @param hauteur - Hauteur de l'Element
     * @param actif - True si doit apparaitre à l'écran, sinon false
     */
    public Agenda(int coordX, int coordY, int longueur, int hauteur, boolean actif) {
        super(coordX, coordY, longueur, hauteur, actif);
        listRappel = new ArrayList<Rappel>();
    }
    
    /**
     *Constructeur par défault
     */
    public Agenda(){
        super(0,0,0,0,false);
        listRappel = new ArrayList<Rappel>();
    }

    @Override
    public String toString() {
        String result = "";
        Rappel tmp;
        for (int i = 0; i < listRappel.size();i++){
            tmp = listRappel.get(i);
            result+= i + ":" + tmp.getDescription() + '\t' + tmp.getDateheure().toString()+'\n';
        }
        return result;
    }

    /**
     *Retourne les rappels
     * @return Une arrayList de rappel
     */
    public ArrayList<Rappel> getListRappel() {
        return listRappel;
    }
    
    

     
     
    
    
    
     /**
     * Ajoute un événement à la multimap de l’agenda
     * @param date - Date du Rappel
     * @param description - Description du Rappel 
     */
    public void addEvenement(DateEtHeure date,String description)
    {
        listRappel.add(new Rappel(description,date));
    }
    
    public void addEvenement(Rappel r){
        listRappel.add(r);
    }
    
    /**
     * Supprime l’événement donné en paramètre de la multimap de l’agenda
     * @param r - Rappel a retirer
     */
    public void removeEvenement(Rappel r)
    {
        listRappel.remove(r);
    }
    
    /**
     *Sauvegarde les rappels dans le fichier : agenda.txt
     * Appelé à chaque nouveau rappel crée
     */
    @Override
    public void saveData() {
        //Créer le répertoire (si nécessaire
        if(System.getProperty("os.name").toString().equals("Windows 10")&& System.getProperty("os.name").equals("Windows 11")){
            File dossier = new File("dist");
            if(!dossier.exists()){
                dossier.mkdir();
            }
        }
        String filename;
        //Ecrire dans le fichier
        try {
            if(System.getProperty("os.name").toString().equals("Windows 10")&& System.getProperty("os.name").equals("Windows 11")){
                filename = "dist\\agenda.txt";

            }
            else {
                filename = "agenda.txt";
            }
            FileWriter writer = new FileWriter(filename);
            writer.write(this.toString());
            writer.close();
            System.out.println("Sauvegardé à " + filename);
        }catch (IOException e) {
            System.out.println("Erreur écriture");
        }
    }

    /**
     *Récupère les données sauvegardée 
     *Appelé au début du programme
     */
    public void recoverData() {
        try {
            //Initialisation
            listRappel = new ArrayList<>();
            int index1,index2;
            String line;
            String tmp;
            String tmp2;
            String filename;
            if(System.getProperty("os.name").toString().equals("Windows 10")&& System.getProperty("os.name").equals("Windows 11")){
                filename = "dist\\agenda.txt";
            }
            else {
                filename = "agenda.txt";
            }
            File myObj = new File(filename);
                Scanner reader = new Scanner(myObj);
                while (reader.hasNextLine()){
                    line = reader.nextLine();
                    index1 = line.indexOf(":");
                    index2 =  line.indexOf("\t");
                    tmp = line.substring(index1+1, index2);
                    index1 = index2 +1;
                    tmp2 =line.substring(index1);
                    DateEtHeure dtmp = new DateEtHeure();
                    dtmp.read_time(tmp2);
                    //dtmp.getDateHeure().setMonth(dtmp.getDateHeure().getMonth()+1);
                    listRappel.add(new Rappel(tmp,dtmp));
                }   
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Pas de sauvegarde");
        }
    }
    
    /**
     *Met à jour l'instance de la classe lorsque on change de jour et retire les rappels passés (appelé une fois par jour)
     */
    public void updateJourna(){
        for (int i = 0; i < listRappel.size();i++){
            if(listRappel.get(i).getDateheure().isBefore()){
                listRappel.remove(i);
            }
        }
    }
}
