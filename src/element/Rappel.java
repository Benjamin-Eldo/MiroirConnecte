
package element;

import java.util.Date;

/**
 * Permet de stocker un Rappel
 * Aucune méthode particulière (en dehors des constructeurs, etc…).
 * @author p2004100 FORESTIER Alexis
 * @version 1.2 Corrections de Bugs
 */
public class Rappel {
    private String description;
    private DateEtHeure dateheure;

    public Rappel() {
        this.description = null;
        this.dateheure = null;
    }
    
    /**
     * Consructeur par paramètre
     * @param description Description du Rappel
     * @param dateheure Date du Rappel
     */
    public Rappel(String description, DateEtHeure dateheure) {
        dateheure.getDateHeure().setMonth(dateheure.getDateHeure().getMonth()-1);
        this.description = description;
        this.dateheure = dateheure;
    }

    /**
     * Consructeur par Copie
     * @param r Rappel
     */
    public Rappel(Rappel r) {
        this.description = r.getDescription();
        this.dateheure=r.getDateheure();
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateheure(DateEtHeure dateheure) {
        this.dateheure = dateheure;
    }

    public String getDescription() {
        return description;
    }

    public DateEtHeure getDateheure() {
        return dateheure;
    }
}
