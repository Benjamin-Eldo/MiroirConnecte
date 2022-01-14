
package statique;

import com.pi4j.io.i2c.I2CFactory;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import periph.capteur.AnalogInput;

/**
 * Classe Statique
 * Représente le capteur de luminosité
 * @author p2000154 VADUREL Benjamin
 * @version 1.5 Luminosité manuelle
 */
public class luminosite {
    //Attributs:
    static double luminositeManuel;

    //Méthodes:
    /**
     * Calcul la luminosité à l'aide du capteur de luminosité
     * @return Un pourcentage de luminosité
     */
    public static double getLuminositeAuto(){
        AnalogInput in;
        
        try {
         in = new AnalogInput(0);
         System.out.println(" luminosité: "+in.mesure());
         return in.mesure();
        } catch (IOException ex) {
            System.out.println("pas de capteur");
        } catch (I2CFactory.UnsupportedBusNumberException ex) {
            System.out.println("pas de capteur");
        }
        return 100;
    }
    
    public static double getLuminositeManuel() {
        return luminositeManuel;
    }

    public static void setLuminositeManuel(double luminositeManuel) {
        luminosite.luminositeManuel = luminositeManuel;
    }
    
}
