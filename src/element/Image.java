
package element;

import java.util.Date;

/**
 * Permet de gérer des Images
 * @author p2000154 VADUREL Benjamin
 * @version 1.0
 */
public class Image {
    private String id;
    private Date dateHeure;

    /**
     * créer une image vide
    */
    public Image() 
    {
        
    }

    /**
     * créer une image avec un id et une date/heure
     * @param id - Nom de l'image
     * @param dateHeure - Date de l'image
    */
    public Image(String id, Date dateHeure) 
    {
        this.id = id;
        this.dateHeure = dateHeure;
    }

    /**
     * retourne l'id
     * @return String
    */
    public String getId() {
        return id;
    }

     /**
     * modifie l'id
     * @param id - Nom de l'image
    */
    public void setId(String id) {
        this.id = id;
    }

     /**
     * retourne la date et l'heure
     * @return Date
    */
    public Date getDateHeure() {
        return dateHeure;
    }
    
     /**
     * modifie la date et l'heure de l'image
     * @param dateHeure - date de l'image
    */
    public void setDateHeure(Date dateHeure) {
        this.dateHeure = dateHeure;
    }
    
     /**
     * Affiche l’image sur l’écran
    */
    public void afficheImage()
    {
        //a coder
    }
    
    /**
    * dessine une image dans l’album
    */
    public void dessin()
    {
            //a coder
    }
    
}
