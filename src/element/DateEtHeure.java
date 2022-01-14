
package element;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Permet de gérer les dates et les heures utilisées dans l'application
 * @author p2000154 VADUREL Benjamin
 * @version 1.5 Divers Corrections
 */
public class DateEtHeure extends Element {
    
    //Attribut
    private Date time;
    private boolean majCourant;
    
    //Méthodes 
    
    /**
     *Constructeur par défault
     */
    public DateEtHeure(){
        super(0,0,0,0,false);
        time = new Date (2021,1,1,0,0);
        this.majCourant = true;
    }
    
    public DateEtHeure(boolean majCourant){
        super(0,0,0,0,false);
        time = new Date (2021,1,1,0,0);
        this.majCourant = majCourant;
    }
    /**
     *Constructeur par paramètre (prend uniquement les paramètres pour l'Element) 
     * Constructeur utilisé uniquement dans le cadre du développement pour la classe Interface
     * @param coordX Positon en X de l'Element
     * @param coordY Positon en Y de l'Element
     * @param longueur Longueur de l'Element 
     * @param hauteur Hauteur de l'Element
     * @param actif True si doit apparaitre à l'écran, sinon false
     */
    public DateEtHeure(int coordX, int coordY, int longueur, int hauteur, boolean actif) {
        super(coordX, coordY, longueur, hauteur, actif);
        time = new Date ();
        this.majCourant = true;
    }
    
    /**
     *Constructeur par paramètre (prend uniquement les paramètres pour la date et l'heure)
     * Constructeur utilisé uniquement dans le cadre du développement pour la classe DateEtHeure
     * @param year Année
     * @param month Mois (de 1 à 12)
     * @param date Jour (de 1 à 31)
     * @param hrs Heure (de 0 à 23)
     * @param min Minute (de 0 à 59)
     */
    public DateEtHeure(int year,int month,int date,int hrs,int min){
        
        super(0,0,0,0,false);
        time = new Date (year,month,date,hrs,min);
        this.majCourant = true;
    }
    
    /**
     * Constructeur par paramètre
     * @param coordX Positon en X de l'Element
     * @param coordY Positon en Y de l'Element
     * @param longueur Longueur de l'Element 
     * @param hauteur Hauteur de l'Element
     * @param actif True si doit apparaitre à l'écran, sinon false
     * @param year Année
     * @param month Mois (de 1 à 12)
     * @param date Jour (de 1 à 31)
     * @param hrs Heure (de 0 à 23)
     * @param min Minute (de 0 à 59)
     */
    public DateEtHeure(int coordX, int coordY, int longueur, int hauteur, boolean actif,
                       int year,int month,int date,int hrs,int min ){
        
         super(coordX, coordY, longueur, hauteur, actif);
         time = new Date (year,month-1,date,hrs,min);
         this.majCourant = true;
    }
    
    /**
     *Constructeur par paramètre simplifié
     * @param e - Coordonées de l'élément
     */
    public DateEtHeure(Element e){
        super(e);
        time = new Date();
        this.majCourant = true;
    }
    
    /**
     *Constructeur par paramètre simplifié
     * @param e Définit les coordonées de l'élément
     * @param year Année
     * @param month Mois (de 1 à 12)
     * @param date Jour (de 1 à 31)
     * @param hrs Heure (de 0 à 23)
     * @param min Minute (de 0 à 59)
     */
    public DateEtHeure(Element e,int year,int month,int date,int hrs,int min){
        super(e);
        time = new Date (year,month-1,date,hrs,min);
        this.majCourant = true;
    }

    
    
    /**
     * retourne la date stocké
     * @return Attribut 'time'
     */
    public Date getDateHeure()
    {
        return time;
    }
    
    /**
     * Mutateur pour l'attribut time
     * @param year Année
     * @param month Mois (de 1 à 12)
     * @param date Jour (de 1 à 31)
     * @param hrs Heure (de 0 à 23)
     * @param min Minute (de 0 à 59)
     */
    public void setDateHeure(int year,int month,int date,int hrs,int min){
        time.setDate(date);
        time.setHours(hrs);
        time.setMinutes(min);
        time.setMonth(month);
        time.setYear(year);

    }
    
    /**
     *Convertit une LocalDateTime (utilisée pour récupérer l'heure actuelle)
     * @param temps Variable à convertir
     */
    private void setDateHeure(LocalDateTime temps){
        time.setDate(temps.getDayOfMonth());
        time.setHours(temps.getHour());
        time.setMinutes(temps.getMinute());
        time.setMonth(temps.getMonthValue()-1);
        time.setYear(temps.getYear());
    }
    
    /**
     *Récupère l'heure actuelle et remplit l'instance de la classe avec
     */
    public void getTimeNow(){
        LocalDateTime aujourdhui = java.time.LocalDateTime.now();
        setDateHeure(aujourdhui);
    }

    /**
     *Surcharge de la fonction toString()
     * @return DATE      HEURE
     */
    @Override
    public String toString() {
        String result="";
        if (time.getDate()<10){
            result+="0";
        }
        result+=time.getDate()+"/";
        if ((time.getMonth()+1)<10){
            result+="0";
        }
        result+= (time.getMonth()+1)+"/"+time.getYear()+"     ";
        if (time.getHours()<10){
            result+="0";
        }
        result+=time.getHours()+":";
        if(time.getMinutes()<10){
            result+="0";
        }
        result+=time.getMinutes();
        return result;
    }
    
    /**
     * Génère un String sous le bon format pour sauvegarder une date
     * @return Une chaine de caractère
     */
    public String generer_sauvegarde() {
        String result="";
        if ((time.getDate()+1)<10){
            result+="0";
        }
        result+= (time.getDate()+1)+"/";
        if ((time.getMonth()+1)<10){
            result+="0";
        }
        result+= (time.getMonth()+1)+"/"+time.getYear()+"     ";
        if (time.getHours()<10){
            result+="0";
        }
        result+=time.getHours()+":";
        if(time.getMinutes()<10){
            result+="0";
        }
        result+=time.getMinutes();
        return result;
    }
    
    /**
     *Fonction d'affichage (affiche seulement l'heure)
     * Utiliser la surcharge toString() pour avoir la date et l'heure
     * @return Heure sous le format HEURE:MINUTE stocké dans un String
     */
    public String getHeure(){
        String result ="";
        if (time.getHours()<10){
            result+="0";
        }
        result+=time.getHours()+":";
        if (time.getMinutes()<10){
            result+="0";
        }
        result+=time.getMinutes();
        return result;
    }

    public int getMinutes() 
    {
        return time.getMinutes();
    }
    
    public int getJour()
    {
        return time.getDay();
    }
    
   
  
    /**
     *Fonction d'affichage (affiche seulement la date) 
     * Utiliser la surchage toString() pour avoir la date et l'heure
     * Le jour et le mois sont écrits en toute lettre
     * @return Date sous le format JOUR DE LA SEMAINE     DATE     MOIS stocké dans un String
     */
    public String getDate(){
        String result = "";
        if (!majCourant){
            switch(time.getDay()){
                case 1:
                    result+="Dimanche ";
                    break;
                case 2:
                    result+="Lundi ";
                    break;
                case 3:
                    result+="Mardi ";
                    break;
                case 4 :
                    result+="Mercredi ";
                    break;
                case 5:
                    result+="Jeudi ";
                    break;
                case 6:
                    result+="Vendredi ";
                    break;
                case 0:
                    result+="Samedi ";
                    break;
                default:
                    result+="No Day ";
                    break;
            }
        }
        else {
            switch(time.getDay()){
                case 0:
                    result+="Dimanche ";
                    break;
                case 1:
                    result+="Lundi ";
                    break;
                case 2:
                    result+="Mardi ";
                    break;
                case 3 :
                    result+="Mercredi ";
                    break;
                case 4:
                    result+="Jeudi ";
                    break;
                case 5:
                    result+="Vendredi ";
                    break;
                case 6:
                    result+="Samedi ";
                    break;
                default:
                    result+="No Day ";
                    break;
            }
        }
        result += time.getDate();
        switch(time.getMonth()){
            case 0:
                result+=" Janvier ";
                break;
            case 1:
                result+=" Fevrier ";
                break;
            case 2:
                result+=" Mars ";
                break;
            case 3 :
                result+=" Avril ";
                break;
            case 4:
                result+=" Mai ";
                break;
            case 5:
                result+=" Juin ";
                break;
            case 6:
                result+=" Juillet ";
                break;
            case 7:
                result+=" Aout ";
                break;
            case 8:
                result+=" Septembre ";
                break;
            case 9:
                result+=" Octobre ";
                break;
            case 10:
                result+=" Novembre ";
                break;
            case 11:
                result+=" Decembre ";
                break;
            default:
                result+="No Month ";
                break;
        }
        return result;
    }
    
    public Date getTime(){
        return time;
    }
    
    //Méthodes crée pour la classe TempsUtilisation
    
    /**
     *Calcul la différence de temps entre la valeur de temps passée en paramètre et l'heure actuelle
     * @return Une différence de temps sous la forme d'une java.util.date
     */
    public DateEtHeure difference (){
            DateEtHeure mtn = new DateEtHeure();
            mtn.getTimeNow();
        //Gestion des mois
        // Cas Particulier : Changement d'année
        int month;
        if (time.getMonth()-mtn.getDateHeure().getMonth() < 0){
            month= time.getMonth()+12-mtn.getDateHeure().getMonth();
        }
        else{
            month = (time.getMonth()-mtn.getDateHeure().getMonth())-1;
        }
        
        //Gestion de la date (= le jour)
        //Cas particuliers : Changement de mois + Mois de février
        int date;
        if (time.getDate()-mtn.getDateHeure().getDate() < 0){
            //Gestion du mois de février
            if (time.getMonth()==2){
                date = time.getDate()+28-mtn.getDateHeure().getDate();
            }
            //Gestion des mois impairs (31 Jours)
            else if (time.getMonth()%2==0){
                date = time.getDate()+31-mtn.getDateHeure().getDate();
            }
            //Gestion des mois pairs (30 jours)
            else{
                date = time.getDate()+30-mtn.getDateHeure().getDate();
            }
        }
        else {
            date = time.getDate()-mtn.getDateHeure().getDate();
        }
        
        //Gestion de l'heure
        //Cas particulier : Changement de jour
        int heure;
        if (time.getHours() - mtn.getDateHeure().getHours() < 0){
            heure = time.getHours() + 24 - mtn.getDateHeure().getHours();
        }
        else {
            heure = time.getHours() - mtn.getDateHeure().getHours();
        }
        //Gestion des minutes
        //Cas particulier : Changement d'heure
        int minute;
        if (time.getMinutes() - mtn.getDateHeure().getMinutes() < 0){
            minute = time.getMinutes()+60 - mtn.getDateHeure().getMinutes();
        }
        else {
            minute =  time.getMinutes() - mtn.getDateHeure().getMinutes();
        }
        
        return new DateEtHeure (time.getYear() - mtn.getDateHeure().getYear(),month,date,heure,minute);
    }
    
    /**
     *Convertit la variable de type temps en une durée
     * @return Durée exprimée en minute
     */
    public int calcul_duree(){
        return time.getSeconds() + time.getMinutes()*60+time.getHours()*3600+time.getDay()*3600;
    }
    
    
    /**
     *Récupère une chaine de caractère et rempli les attributs avec
     * @param chaine - Chaine à lire
     */
    public void read_time(String chaine){
        int index1, index2;
        index1 = 0;
        index2 = chaine.indexOf("/");
        time.setDate(Integer.parseInt(chaine.substring(index1, index2)));
        index1 = index2 + 1;
        index2 = chaine.indexOf("/",index1);
        time.setMonth(Integer.parseInt(chaine.substring(index1, index2)));
        index1 = index2 + 1;
        index2 = chaine.indexOf(" ",index1);
        time.setYear(Integer.parseInt(chaine.substring(index1, index2)));
        index1 = index2 + 5;
        index2 = chaine.indexOf(":",index1);
        time.setHours(Integer.parseInt(chaine.substring(index1, index2)));
        index1 = index2 + 1;
        time.setMinutes(Integer.parseInt(chaine.substring(index1)));
    }
    
    /**
     *Retire nbJour à l'instance de la classe
     * Utilisé pour afficher le temps d'utilisation
     * @param nbJour Nombre de jour à retirer
     */
    public void retirer_jour(int nbJour){
        int year,month,day;
        month = time.getMonth();
        if (time.getDate()-nbJour<1){
            if(time.getMonth()==3){
                day = 28;
            }
            else if (time.getMonth()%2==0){
                day = 31;
            }
            else {
                day = 30;
            }
            if (time.getMonth()==1){
                month = 11;
            }
            else {
                month = time.getMonth()-1;
            }
        }
        else {
            day = time.getDate()-nbJour;
        }
        setDateHeure(time.getYear(), month, day, time.getHours(), time.getMinutes());
    }

    /**
     * Permet de savoir si une date se trouve avant maintenant
     * @return Retourne True si la date stockée dans l'instance de la classe se trouve AVANT l'heure de la machine, Sinon False
     */
    public boolean isBefore(){
        DateEtHeure dtmp = new DateEtHeure();
        dtmp.getTimeNow();
        if (time.getYear() < dtmp.getTime().getYear()){
            return true;
        }
        if (time.getYear() == dtmp.getTime().getYear()){
            if (time.getMonth()< dtmp.getTime().getMonth()){
                return true;
            }
            if (time.getMonth() == dtmp.getTime().getMonth()){
                
                if (time.getDate() < dtmp.getTime().getDate()){
                    return true;
                }
                if (time.getDate() == dtmp.getTime().getDate()){
                    
                    if (time.getHours() < dtmp.getTime().getHours()){
                        return true;
                    }
                    if(time.getHours()== dtmp.getTime().getHours()){
                        if(time.getMinutes() < dtmp.getTime().getMinutes()){
                            return true;
                        }
                        
                    }
                    
                }
                
                
            }
        }
        return false;
        
    }

    /**
     * Vérifie si la date contenue dans l'instance de la classe est la même que celle d'aujourd'hui
     * @return True si c'est la même, sinon False
     */
    public boolean estMemeJour(){
        DateEtHeure dtmp=new DateEtHeure();
        dtmp.getTimeNow();
        if (this.getDateHeure().getDate() != dtmp.getDateHeure().getDate()){
            return false;
        }
        if (this.getDateHeure().getMonth() != dtmp.getDateHeure().getMonth()){
            return false;
        }
        if (this.getDateHeure().getYear() != dtmp.getDateHeure().getYear()){
            return false;
        }
        return true;
    }
            
}
      
