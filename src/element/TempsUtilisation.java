
package element;

import com.pi4j.io.gpio.RaspiBcmPin;
import element.DateEtHeure;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import miroir.Enregistrement;
import periph.capteur.DigitaBCMGpioListener;

/**
 * Permet d'afficher le temps d'utilisation de l'application
 * @author p2000154 VADUREL Benjamin
 * @version 1.5 Changement de l'affichage de TempsUtilisation
 */
public class TempsUtilisation extends Element implements Enregistrement {
    //Atributs Concernant le temps d'utilisation
    private double[] semaine;
    
    private double tempsTotal;              //Temps total passé devant le miroir pendant la journée (reset à minuit)
    private int nbJour;
    private int duration;
    
    private DateEtHeure heureArrive;        //Représente l'heure à laquelle l'utilisateur arrive devant le miroir
    private DateEtHeure heureDepart;
    private DateEtHeure heureSortie;
    private boolean firstUse;
    private DateEtHeure instant;
    
    //Attribut concernant la présence de l'utilisateur sur l'appareil
    private boolean isGone;
    private boolean presence;
    
    private DigitaBCMGpioListener detecteur;        //Détecteur de mouvement

    
    /**
     * Constructeur par défault
    */
    public TempsUtilisation() {
        semaine=new double[7];
        for (int i = 0; i < 7;i++){
            semaine [i] = 0;
        }
        presence = true;
        heureArrive = new DateEtHeure();
        heureArrive.getTimeNow();
        heureDepart = new DateEtHeure();
        heureSortie = new DateEtHeure();
        instant = new DateEtHeure();
        isGone = false;
        presence = true;
        firstUse = false;
        duration = 0;
    }

    /**
     * Constructeur par paramètre
     * @param coordX Positon en X de l'Element
     * @param coordY Positon en Y de l'Element
     * @param longueur Longueur de l'Element 
     * @param hauteur Hauteur de l'Element
     * @param actif True si doit apparaitre à l'écran, sinon false
     */
    public TempsUtilisation(int coordX, int coordY, int longueur, int hauteur, boolean actif) {
        super(coordX, coordY, longueur, hauteur, actif);
        semaine=new double[7];
        for (int i = 0; i < 7;i++){
            semaine [i] = 0;
        }
        presence = true;
        heureArrive = new DateEtHeure();
        heureArrive.getTimeNow();
        heureDepart = new DateEtHeure();
        heureSortie = new DateEtHeure();
        instant = new DateEtHeure();
        isGone = false;
        presence = true;
        firstUse = false;
        duration = 0;
    }
    
    /**
     *Initialise le capteur de mouvement (PIR Sensor)
     */
    public void init_capteur (){
        if (System.getProperty("os.name").toString().equals("Linux")){
            try{
                detecteur= new DigitaBCMGpioListener (RaspiBcmPin.GPIO_05);
            }catch(UnsatisfiedLinkError ex){
                System.out.println("Erreur de capteur");
            }
        }
    }

    /**
     *Accesseur de l'attribut : double semaine[]
     * @param index Index de l'attribut souhaité
     * @return Une variable de type double : semaine[index]
     */
    public double getJourSemaine(int index){
        return semaine[index];
    }


    /**
     * @return Retourne le temps d'utilisation total du miroir
    */
    public double getTempsTotal() {
        return tempsTotal;
    }



    /**
     * Accesseur de l'attribut tempsTotal
     *  @param tempsTotal Temps que l'on veut attribuer
    */
    public void setTempsTotal(double tempsTotal) {
        this.tempsTotal = tempsTotal;
    }

    
    /**
     * Met à jour le temps passé devant le miroir
    */
    public void updateTempsJour()
    {
        double tmp = 0;
        if(!firstUse||!instant.estMemeJour()){
            nbJour++;
            for (int i = 0; i < instant.difference().getJour();i++){
                updateJours();
            }
            
            firstUse = true;
            instant.getTimeNow();
        }
        if (presence){
            tmp = (((double)((heureArrive.difference().calcul_duree())/60.0))/120.0)/120.0;
            semaine[0]+=tmp;
            heureArrive.getTimeNow();
        }
        tmp = 0;
        for (int i = 0; i < 7; i++){
            tmp+= semaine[i];
        }
        tempsTotal = tmp;
    }
    
     /**
     * Met à jour l'objet tout les jours
    */
    public void updateJours()
    {
        for (int i = 6;i <= 1; i--){
            semaine[i] = semaine[i-1];
        }
        semaine[0] = 0;
    }
    
    /**
     * calcul le temps moyen passer par jour depuis l'achat du mirroir
     * @return Un affichage du tempsMoyen depuis le début
    */
    public String tempsMoyen()
    {
        double tmp = tempsTotal/nbJour;
        return ""+(int)tmp+" min "+calcul_seconde(tmp) + " sec";
    }

    public boolean isPresence() {
        return presence;
    }

    public boolean isGone() {
        return isGone;
    }
    
    /**
     * Observe si il y a une présence devant le miroir et met à jour plusieurs attributs :
     * isGone : Détecte un mouvement ou non
     * presence : Mode veille ou non
     * Le mode veille est activé après 30 secondes dans présence détectée
     * Utilise le PIR Motion Sensor
     */
    public void updatePresence(){
        if (detecteur != null){
            boolean tmp = detecteur.mesure();
            if (tmp){
                if(isGone){
                    if (!presence || heureArrive == null){
                        presence = true;
                        heureArrive.getTimeNow();
                    }
                    isGone = false;
                }
            }
            if (!tmp){
                if(!isGone){
                    isGone = true;
                    heureDepart.getTimeNow();
                    heureSortie.getTimeNow();
                    duration = 0;
                }
                else {
                    DateEtHeure duree = heureSortie.difference();
                    duration += (duree.calcul_duree()/60.0/60.0);
                    if (duration>=90){
                        updateTempsJour();
                        presence = false;
                    }
                }
            }
        }
        else {
            presence = true;
            isGone = true;
        }
    }

    /**
     *Permet l'affichage de l'objet
     * @return Une chaine de caractère sous la forme : tempsTotal=DOUBLE \n nbJour=INT  \n liste des jours associés aux nomhres de minute passé sur le miroir
     */
       @Override
    public String toString() {
        DateEtHeure dtmp =  new DateEtHeure(false);
        dtmp.getTimeNow();
        String result;
        result = "tempsTotal = " + tempsTotal+"\n";
        result += "nbJour = " + nbJour+"\n";
        for (int i = 0; i < 7; i++){
            dtmp.retirer_jour(1);
            result+=dtmp.getDate()+":"+ semaine[i]+"\n";
        }
        result+="instant = " + instant.generer_sauvegarde()+"\n";
        result+="firstUse = " + firstUse;
        return result;
    }
    
    /**
     * @param n Nombre de jour en arrière (max : 7)
     * @return Un affichage du temps d'utilisation pour n jours en arrière OU une chaine vide si n supperieur a 7
     */
    public String afficheJour(int n){
        if (n<7){
            int tmp = (int)semaine[n];
            return ""+tmp+" min "+calcul_seconde(semaine[n])+" sec";
        }
        return "";
    }
    
    /**
     * Calcul la moyenne pour la dernière semaine
     * @return L'affichage de la moyenne
     */
    public String moyenneSemaine(){
        double tmp = 0;
        for (int i = 0; i < 7;i++){
            tmp += semaine[i];
        }
        tmp = tmp/7.0;
        return (int)tmp+" min " + calcul_seconde(tmp) + " sec";
    }

    /**
     *Enregistre les attriuts : TempsTotal, nbJour et semaine dans un fichier
     */
    @Override
    public void saveData() {
        if(System.getProperty("os.name").equals("Windows 10") && System.getProperty("os.name").equals("Windows 11")){
            File dossier = new File("dist");
            if(!dossier.exists()){
                dossier.mkdir();
            }
        }
        String filename;
        try {
            if(System.getProperty("os.name").equals("Windows 10") && System.getProperty("os.name").equals("Windows 11")){
                filename = "dist\\utilisation.txt";

            }
            else {
                filename = "utilisation.txt";
            }
            FileWriter writer = new FileWriter(filename);
            writer.write(this.toString());
            writer.close();
        } catch (IOException e) {
            System.out.println("Erreur écriture");
        }
    }

    /**
     *Récupère les attriuts : TempsTotal, nbJour et semaine dans un fichier
     */
    public void recoverData() {
        String filename;
        int index;
        try {
            //Initialisation
            if(System.getProperty("os.name").equals("Windows 10")&& System.getProperty("os.name").equals("Windows 11")){
                filename = "dist\\utilisation.txt";

            }
            else {
                filename = "utilisation.txt";
            }
            File fichier = new File(filename);
            Scanner reader = new Scanner(fichier);

            //Lecture
            String data = reader.nextLine();
            index = data.indexOf("=");
            tempsTotal = Double.parseDouble(data.substring(index+1));

            data = reader.nextLine();
            index = data.indexOf("=");
            nbJour = Integer.parseInt(data.substring(index+2));

            semaine = new double [7];

            for (int i = 0; i < 7 && reader.hasNextLine();i++){
                data = reader.nextLine();
                index = data.indexOf(":");
                semaine[i] = Double.parseDouble(data.substring(index+1));;
            }
            data = reader.nextLine();
            index = data.indexOf("=");
            instant.read_time(data.substring(index+2));
            instant.getDateHeure().setDate(instant.getDateHeure().getDate()-1);
            instant.getDateHeure().setMonth(instant.getDateHeure().getMonth()-1);
            
            data = reader.nextLine();
            index = data.indexOf("=");
            data = data.substring(index+2);
            if (data.equals("true")){
                firstUse = true;
            }
            else {
                firstUse = false;
            }


            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Pas de save");
        }
    }
    
    /**
     * Converti un temps en seconde
     * @param valeur Valeur a convertir
     * @return 
     */
    private int calcul_seconde(double valeur){
        int arrondi = (int) valeur;
        
        return (int) ((valeur - arrondi)*60);
    }
    
}
