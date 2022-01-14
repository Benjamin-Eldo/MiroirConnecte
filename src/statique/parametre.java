
package statique;

/**
 * Classe Statique
 * Représente les parametres de l'application
 * @author p2026386 GRANDY Guillaume
 * @version 1.3 Modifiaction du constructeur
 */
public class parametre {
    //Attributs:
    static boolean afficher[];
    static boolean continuer=true;
    
    //Méthodes:

    public parametre() {
        afficher=new boolean[6];
        for(int i=0;i<afficher.length;i++)
        {
            afficher[i]=true;
        }
    }
    

    public static boolean[] getAfficher() {
        return afficher;
    }
    
    public static void setAfficherNum(int i,boolean afficher)
    {
        parametre.afficher[i]=afficher;
    }
    
     public static boolean getAfficherNum(int i)
    {
        return parametre.afficher[i];
    }

    public static void setAfficher(boolean[] afficher) {
        parametre.afficher = afficher;
    }
    
    
    public static void setAfficher(int index, boolean val){
        //Change la paramètre pour l'index
        afficher[index]=val;
    }
    public static boolean getAfficher(int index){
        //Change la paramètre pour l'index
        return afficher[index];
    }
}
