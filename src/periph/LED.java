
package periph;

import java.io.IOException;

/**
 * Permet de gérer une LED via les commandes gpio de la Raspberry Pi
 * @author p2004100 FORESTIER Alexis
 * @version 1.1 ajout d'un accesseur à l'état de la LED
 */
public class LED {
    
    Runtime runtime;
    
    private int gpio_port;
    private boolean state; // true = allumé         false = éteint

    /**
     * Constructeur par paramètre
     * @param gpio_port - Port Gpio sur lequel la LED est branché
     * @param state - État de la LED à son initialisation
     */
    public LED(int gpio_port, boolean state){
        this.gpio_port = gpio_port;
        this.state = state;
        runtime = Runtime.getRuntime();
        try{
            runtime.exec("gpio -g mode "+gpio_port+" out");
            runtime.exec("gpio -g write "+gpio_port+" "+(state ? 1 : 0));
        }catch(IOException e){
            System.out.println("Erreur : Aucun port Gpio, la led n'a pas été initialisée");
        }
    }
    
    /**
     * La LED s'allume
     */
    public void ON(){
        this.state = true;
        try{
            runtime.exec("gpio -g write "+gpio_port+" "+(state ? 1 : 0));
        }catch(IOException e){
            System.out.println("Erreur : Aucun port Gpio, la led ne s'est pas allumée");
        }
    }
    
    /**
     * La LED s'éteint
     */
    public void OFF(){
        this.state = false;
        try{
            runtime.exec("gpio -g write "+gpio_port+" "+(state ? 1 : 0));
        }catch(IOException e){
            System.out.println("Erreur : Aucun port Gpio, la led ne s'est pas éteinte");
        }
    }

    public boolean isState() {
        return state;
    }
    
    /**
     * Le programme attend n second
     * @param n - Nombre de seconde
     */
    public void tempo(int n){
        try {
            Thread.sleep(n*1000);
        } catch (InterruptedException ex) {
            System.out.println("Erreur : Tempo");
        }
    }
    
    
    
}
