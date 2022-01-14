
package element;

/**
 * Permet de représenter une actualité
 * @author p2026386 GRANDY Guillaume
 * @version 1.0
 */
public class Info {
    private String titre;
    private String source;

    /**
     * créer une info vide
    */
    public Info() {
        titre = "";
        source = "";
    }

    /**
     * créer une info avec un titre et une source
     * @param titre Titre de l'info
     * @param source Source de l'info
    */
    public Info(String titre, String source) {
        this.titre = titre;
        this.source = source;
    }

    /**
     * retourne le titre
     * @return String
    */
    public String getTitre() {
        return "          "+titre;
    }

    /**
     * attribut le titre
     * @param titre Titre de l'info
    */
    public void setTitre(String titre) {
        this.titre = titre;
    }

    /**
     * retourne la source
     * @return String
    */
    public String getSource() {
        return source;
    }

    /**
     * attribut la source
     * @param source Source de l'info
    */
    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return titre + "   src : " + source;
    }
    
}
