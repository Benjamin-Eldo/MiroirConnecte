
package miroir;

import element.DateEtHeure;
import element.MeteoJour;
import java.io.IOException;
import periph.Interface;


/**
 * Main de l'application "Miroir Connecté"
 * @author p2000154 VADUREL Benjamin
 * @author p2026386 GRANDY Guillaume
 * @author p2004100 FORESTIER Alexis
 * @version 1.6 ajout d'un arrière plan dégradé à l'application
 */
public class Miroir {
    /**
     * Main
     * @param args - The command line arguments
     * @throws java.lang.InterruptedException Error
     */
    public static void main(String[] args) throws InterruptedException {
        Runtime runtime = Runtime.getRuntime();
        boolean display_state = true;
        boolean continuer=true;
        boolean present=true;
        int minute,date;
        
        System.out.println("START...");
        
        Interface fenetre= new Interface();
        fenetre.setVisible(true);
       
        
        try
        {
        fenetre.getAgenda().recoverData();
        }
        catch(Exception x){}
        
        minute=((DateEtHeure)(fenetre.getElements().get(4))).getMinutes();
        date=((DateEtHeure)(fenetre.getElements().get(4))).getJour(); 
        
        System.out.println("retour main");
         
        while(continuer==true)
        {
            Thread.sleep(1000);
            
            if(fenetre.isIspara()==true)
            {
            fenetre.getElementsGra().remove(fenetre.getElementsPara().get(fenetre.getElementsPara().size()-1));
            fenetre.getElementsGra().remove(fenetre.getElementsPara().get(fenetre.getElementsPara().size()-3));
            fenetre.getElementsGra().remove(fenetre.getElementsPara().get(fenetre.getElementsPara().size()-5));
            }
            
            fenetre.getTemps().updatePresence();          
            fenetre.getTemps().updateTempsJour();
            fenetre.getElementsPara().get(fenetre.getElementsPara().size()-1).setText(fenetre.getTemps().afficheJour(0));
            fenetre.getElementsPara().get(fenetre.getElementsPara().size()-3).setText(""+fenetre.getTemps().moyenneSemaine());
            fenetre.getElementsPara().get(fenetre.getElementsPara().size()-5).setText(""+fenetre.getTemps().tempsMoyen());


            
            if(fenetre.isIspara()==true)
            {
            fenetre.getElementsGra().add(fenetre.getElementsPara().get(fenetre.getElementsPara().size()-1),Integer.valueOf(4));
            fenetre.getElementsGra().add(fenetre.getElementsPara().get(fenetre.getElementsPara().size()-3),Integer.valueOf(4));
            fenetre.getElementsGra().add(fenetre.getElementsPara().get(fenetre.getElementsPara().size()-5),Integer.valueOf(4));
            }

            
            if(fenetre.getTemps().isPresence()==true && present==true && fenetre.isTourne()==true)
            {
                
                if(fenetre.getPara().getAfficherNum(3)==true && fenetre.isSelectionne()==false)
                {
                    fenetre.rotationActu();
                }

                ((DateEtHeure)(fenetre.getElements().get(4))).getTimeNow();
                if(minute!=((DateEtHeure)(fenetre.getElements().get(4))).getMinutes() && fenetre.getPara().getAfficherNum(1)==true && fenetre.isSelectionne()==false)
                {

                    minute=((DateEtHeure)(fenetre.getElements().get(4))).getMinutes();
                    fenetre.getElementsGra().remove(fenetre.getHeure());
                    ((DateEtHeure)(fenetre.getElements().get(4))).getTimeNow();
                    fenetre.initHeure();
                }

                    if(minute==0)
                    {
                        
                       if(date!=((DateEtHeure)(fenetre.getElements().get(4))).getJour() && fenetre.getPara().getAfficherNum(0)==true)
                       {
                           date=((DateEtHeure)(fenetre.getElements().get(4))).getJour();
                           fenetre.getElementsGra().remove(fenetre.getDate());
                           ((DateEtHeure)(fenetre.getElements().get(4))).getTimeNow();
                           fenetre.initDate();
                           fenetre.getTemps().updateJours();
                       }
                    }
                fenetre.getElementsGra().repaint();
            }
        
            else if(present==true && fenetre.getTemps().isPresence()==false)
            {
                present=false;
                if(display_state == true){
                    try{
                    display_state = false;
                    runtime.exec("vcgencmd display_power "+(display_state ? 1 : 0));
                    }catch(IOException e){
                    System.out.println("commande impossible");
                    }
                }
            }
            
            else if(present==false && fenetre.getTemps().isPresence()==true)
            {
                if(display_state == false){
                    try{
                    display_state = true;
                    runtime.exec("vcgencmd display_power "+(display_state ? 1 : 0));
                    }catch(IOException e){
                    System.out.println("commande impossible");
                    }
                }
                
                ((MeteoJour)fenetre.getElements().get(5)).recupMeteoNow((String)fenetre.getVille().getSelectedItem());
                fenetre.effaceMeteo();
                fenetre.getMeteo().setText(((MeteoJour)fenetre.getElements().get(5)).afficheTemperature());
                fenetre.afficheMeteo();
                
                present=true;
            }
        }
                fenetre.getAgenda().recoverData();
                fenetre.getTemps().recoverData();

        System.out.println("STOP...");
        
    }    
}
