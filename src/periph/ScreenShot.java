package periph;

import java.io.IOException;

/**
 * Permet de prendre des captures d'écran facilement sur Raspberry Pi via l'application "Scrot"
 * Au préalable veuillez installer l'application "Scrot" sur votre Raspberry pour pouvoir utiliser cette classe java.
 * Commande d'installation : "sudo apt-get install scrot"
 * @author p2004100 FORESTIER Alexis
 * @version 1.0
 */
public class ScreenShot {
    private Runtime runtime;
    private String path;
    
    public ScreenShot() {
        this.runtime = Runtime.getRuntime();
        this.path = "";
    }

    /**
     * Constructeur par paramètre
     * @param path - Chemin d'accès
     */
    public ScreenShot(String path) {
        this.runtime = Runtime.getRuntime();
        this.path = path;
    }
    
    /**
     * Permet de changer le chemin d'accès au dossier qui stockera le fichier
     * @param newpath - Nouveau chemin d'accès
     */
    public void setPath(String newpath){
        this.path = newpath;
    }
    
    /**
     * Prend un ScreenShot de l'écran de la Raspberry
     * @param namefile - Nom du fichier contenant le ScreenShot
     */
    public void takeScreenShot(String namefile){
        try {
            runtime.exec("scrot "+path+"/"+namefile);
        } catch (IOException ex) {
            System.out.println("Erreur : ScreenShot impossible,\nVeuillez vérifier que l'application \"Scrot\" soit bien installé sur votre Raspberry Pi");
        }
    }
    
    
}
