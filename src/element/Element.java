
package element;

/**
 * Permet de gérer tous les éléments de l'application
 * @author p2026386 GRANDY Guillaume
 * @author p2000154 VADUREL Benjamin
 * @version 1.0
 */
public class Element {
    //Attributs:
    private int coordX;
    private int coordY;
    private int longueur;
    private int hauteur;
    private boolean actif;
    
    
    //Méthodes:
    /**
     * constructeur par paramètre
     * @param coordX - Positon en X de l'Element
     * @param coordY - Positon en Y de l'Element
     * @param longueur - Longueur de l'Element 
     * @param hauteur - Hauteur de l'Element
     * @param actif - True si doit apparaitre à l'écran, sinon false
     */
    public Element(int coordX, int coordY, int longueur, int hauteur, boolean actif) {
        this.coordX = coordX;
        this.coordY = coordY;
        this.longueur = longueur;
        this.hauteur = hauteur;
        this.actif = actif;
    }
    /**
     * Constructeur par défault
     */
    public Element() {
        this.coordX = 0;
        this.coordY = 0;
        this.longueur = 0;
        this.hauteur = 0;
        this.actif = false;
    }
    
    /**
     *Constructeur par copie
     * @param e - Element à copier
     */
    public Element(Element e){
        this.coordX = e.coordX;
        this.coordY = e.coordY;
        this.longueur = e.longueur;
        this.hauteur = e.hauteur;
        this.actif = e.actif;
    }
    
    public int getCoordX() {
        return coordX;
    }

    public void setCoordX(int coordX) {
        this.coordX = coordX;
    }

    public int getCoordY() {
        return coordY;
    }

    public void setCoordY(int coordY) {
        this.coordY = coordY;
    }

    public int getLongueur() {
        return longueur;
    }

    public void setLongueur(int longueur) {
        this.longueur = longueur;
    }

    public int getHauteur() {
        return hauteur;
    }

    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }
    
}
