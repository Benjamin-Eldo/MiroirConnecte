
package element;

import java.util.ArrayList;

/**
 * Permet de stocker les photos
 * @author p2026386 GRANDY Guillaume
 * @version 1.0
 */
public class Album extends Element{
    private ArrayList<Image> photos; 
    
    /**
     * créer un album vide
     */
    public Album() 
    {
        photos=new  ArrayList<Image>();
    }
        
    /**
     * Prend une photo et l’ajoute dans l’album
     * @param img - Image du répertoire
     */
    public void addImage(Image img){
        photos.add(img);
    }
        
    
    /**
     * enlève l'image a l'index donner
     * @param index - Rang de l'image a supprimer
     */
    public void removeImage(int index)
    {
        photos.remove(index);
    }
}
