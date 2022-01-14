
package periph;

import com.hopding.jrpicam.RPiCamera;
import com.hopding.jrpicam.exceptions.FailedToRunRaspistillException;
import element.Agenda;
import element.DateEtHeure;
import element.Element;
import element.InformationDuJour;
import element.MeteoJour;
import element.Rappel;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import miroir.Composant;
import element.TempsUtilisation;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import statique.luminosite;
import statique.parametre;


/**
 * Classe Principale
 * Permettre d'afficher tous les éléments graphiquement sur l'application
 * @author p2026386 GRANDY Guillaume
 * @version 1.8 Ajout d'un dégradé sur l'arrière plan de l'application
 */
public class Interface extends JFrame implements ActionListener,MouseListener,MouseMotionListener,ChangeListener{
    //attributs

    //divers
    private int selectionneNum;
    private int imageActu=0;
    private RPiCamera cam;
    private ScreenShot screenshot;
    private Color couleur;
    private Composant fondPara;
    private JLayeredPane elementsGra;
    private parametre para;
    private TempsUtilisation temps;
    private LED led;
    private Gribouilleur dessin;
    private JSlider luminositeManuel;
    private JComboBox ville;
    
    //JLabel avec texte
    private JLabel heure;
    private JLabel date;
    private JLabel meteo; 
   
    
    //JLabel pour les images
    private JLabel imageMeteo_nuage;
    private JLabel imageMeteo_pluit;
    private JLabel imageMeteo_soleil;
    private JLabel imageMeteo_orage;
    private JLabel imageMeteo_neige;
    private JLabel imageMeteo_brouillard;
    private JLabel imagePhotoDeplacement;
    private JLabel imageDegradeJaune;
    private JLabel imageDegradeBleu;
    private JLabel imageDegradeGris;
    private JLabel image;
    private JLabel rienPrevu;
    
    
    //JButton
    private JButton imageParametre;
    private JButton imagePhoto;
    private JButton sortie;
    private JButton modifierPara;
    private JButton luminositeAuto;
    private JButton valider;
    private JButton ajouteEvenement;
    private JButton supprimeEvenement;
    private JButton photo;
    private JButton gauche;
    private JButton droite;
    private JButton sortir;
    private JButton imageParametreInverse;
    private JButton modifierImg;
    
    //ArrayList
    private ArrayList<JLabel> evenement;
    private ArrayList<JLabel> actualite;
    private ArrayList<JLabel> elementsPara;
    private ArrayList<JCheckBox> case_coche;
    private ArrayList<Element> elements;
    private ArrayList<JButton> boutonDessin;
    
    //Boolean
    private boolean ispara=false;
    private boolean isModif=false;
    private boolean selectionne=false;
    private boolean tourne=true;


    public Interface() throws HeadlessException {
        
        try 
        {
            //Init Caméra
            cam = new RPiCamera("/home/pi/Pictures");
            cam.turnOnPreview(0,0,480,800);
            cam.setTimeout(5000);
            cam.setDateTimeOn();
        }
        catch (FailedToRunRaspistillException ex) 
        {
            System.out.println("Pas de caméra");
        }
        
        //init ScreenShot
        screenshot = new ScreenShot("/home/pi/Pictures");
        
        //création des attributs
        heure=new JLabel();
        date=new JLabel();
        meteo=new JLabel();
        evenement=new ArrayList<>();
        actualite=new ArrayList<>();
        elementsGra=new JLayeredPane();
        elements=new ArrayList<>();
        elementsGra=new JLayeredPane();
        para=new parametre();
        temps=new TempsUtilisation();
        led=new LED(22,false);
        boutonDessin=new ArrayList();
        
        //création des classes filles d'élément
        
        //0.Agenda
        //1.info du jour
        //2.appareil photo
        //3.jour
        //4.heure
        //5.météo
        
        temps.init_capteur();
        
        Agenda a=new Agenda(280,580,180,200,true);
        elements.add(a);
        
        InformationDuJour b=new InformationDuJour(10,580,180,200,true);
        elements.add(b);
        
        Element c=new Element(215,730, 50,50,true);
        elements.add(c);
        
        DateEtHeure d=new DateEtHeure(10,10, 250, 30,true);
        elements.add(d);
        
        DateEtHeure e=new DateEtHeure(10,50, 175, 55,true);
        elements.add(e);
        
        MeteoJour f=new MeteoJour(10,110,150,50,true);
        elements.add(f);
        
        //récupération des images
        
        try
        {
            ImageIcon i= new ImageIcon(getClass().getResource("parametre.png"));
            imageParametre=new JButton(i);
            imageParametre.setBorderPainted(false);
        }
        
        catch(Exception x){System.err.println("pb dans l'ouverture du logo paramètre");}
        
        try
        {
            ImageIcon i= new ImageIcon(getClass().getResource("meteo_nuage.png"));
            imageMeteo_nuage=new JLabel(i);
        }
        
        catch(Exception x){System.err.println("pb dans l'ouverture du logo meteo nuage");}
        
        try
        {
            ImageIcon i= new ImageIcon(getClass().getResource("meteo_orage.png"));
            imageMeteo_orage=new JLabel(i);
        }
        
        catch(Exception x){ System.err.println("pb dans l'ouverture du logo meteo orage");}
        
        try
        {
            ImageIcon i= new ImageIcon(getClass().getResource("meteo_neige.png"));
            imageMeteo_neige=new JLabel(i);
        }
        
        catch(Exception x){System.err.println("pb dans l'ouverture du logo neige");}
        
        try
        {
            ImageIcon i= new ImageIcon(getClass().getResource("meteo_brouillard.png"));
            imageMeteo_brouillard=new JLabel(i);
        }
        
        catch(Exception x){System.err.println("pb dans l'ouverture du logo brouillard");}
        
        try
        {
            ImageIcon i= new ImageIcon(getClass().getResource("meteo_pluit.png"));
            imageMeteo_pluit=new JLabel(i);
        }
        
        catch(Exception x){System.err.println("pb dans l'ouverture du logo pluit");}
        
        try
        {
            ImageIcon i= new ImageIcon(getClass().getResource("meteo_soleil.png"));
            imageMeteo_soleil=new JLabel(i);
        }  
        
        catch(Exception x){System.err.println("pb dans l'ouverture du logo soleil");}
          
        try
        {
            ImageIcon i= new ImageIcon(getClass().getResource("photo.png"));
            imagePhoto=new JButton(i);
            imagePhotoDeplacement=new JLabel(i);
            imagePhoto.setBorderPainted(false);
        }  
        
        catch(Exception x){System.err.println("pb dans l'ouverture du logo photo");}
        
        try
        {
            ImageIcon i= new ImageIcon(getClass().getResource("parametre-inverse.png"));
            imageParametreInverse=new JButton(i);
        }  
        
        catch(Exception x){System.err.println("pb dans l'ouverture du logo parametre inversé");}
        
        try
        {
            ImageIcon j= new ImageIcon(getClass().getResource("degradeBleu.png"));
            imageDegradeBleu=new JLabel(j);
        }  
        
        catch(Exception x){System.err.println("pb dans l'ouverture du dégradé Bleu");}
         
        try
        {
            ImageIcon k= new ImageIcon(getClass().getResource("degradeGris.png"));
            imageDegradeGris=new JLabel(k);
        }  
        
        catch(Exception x){System.err.println("pb dans l'ouverture du logo dégradé Gris");}
          
        try
        {
            ImageIcon l= new ImageIcon(getClass().getResource("degradeJaune.png"));
            imageDegradeJaune=new JLabel(l);
        }  
        
        catch(Exception x){System.err.println("pb dans l'ouverture du dégradé Jaune");}
        
        //Action listener
        imageParametre.addActionListener(this);
        imagePhoto.addActionListener(this);        
        
        //Foucus listener
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        
        initialisation();
    }
    
    /**
    * initialise l'interface
    */
    public void initialisation(){
        
        System.out.println("Début de l'initialisation initialisation");
        
        //paramètre de la fenètre (JFrame)
        this.setSize(480, 800);
        this.getContentPane().setBackground(Color.BLACK);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setUndecorated(true);
        
        //Curseur
        if(System.getProperty("os.name").equals("Linux")){
            BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
            Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
            this.getContentPane().setCursor(blankCursor);
        }
        
        //Temps d'utilisation
        temps.recoverData();
        
        //paramètre la couleur
        System.out.println("Paramètre de la couleur");
        setCouleur();
        
        //paramètre de l'heure
         System.out.println("Paramètre de l'heure");
        initHeure();

        //paramètre de le date
         System.out.println("Paramètre jour");
        initDate();
        
        //paramètre du bouton paramètre
         System.out.println("Paramètre du bouton paramètre");
        imageParametre.setBounds(430, 10, 40, 40); 
        imageParametre.setOpaque(false);
        imageParametre.setContentAreaFilled(false);
        imageParametre.setBorderPainted(false);
        elementsGra.add(imageParametre,Integer.valueOf(1));
        
        //paramètre de la température
         System.out.println("Paramètre de la meteo");
        initMeteo();
        
        //paramètre de la liste d'actu
         System.out.println("Paramètre de l'actualité");
        initActu();
        
        //paramètre de la photoa
         System.out.println("Paramètre du bouton photo");
        initPhoto();
        
        //paramètre des rappels
         System.out.println("Paramètre des rappels");
         ((Agenda)elements.get(0)).recoverData();
         initEvenement();
        
        //paramètre de l'affichage
        System.out.println("Paramètre du panneau paramètre");
        initParametre();
        
        //implémentation dans la fenètre
        this.add(elementsGra);
    }

    /**
     * Initialisation de l'heure
     */
    public void initHeure()
    {
        heure.setText(((DateEtHeure)elements.get(4)).getHeure());
        Font font = new Font("Adobe Fangsong Std R",Font.BOLD,55);
        
        heure.setFont(font);
        heure.setOpaque(true);
        heure.setBounds(elements.get(4).getCoordX(),elements.get(4).getCoordY(), elements.get(4).getLongueur(), elements.get(4).getHauteur());
        heure.setForeground(couleur);
        heure.setOpaque(false);
        afficheHeure();
    }
    
    /**
     * Affichage de l'heure
     */
    public void afficheHeure()
    {
         elementsGra.add(heure,Integer.valueOf(2));
         elementsGra.repaint();
    }
    
    /**
     * Initialisation de la date
     */
    public void initDate()
    {
       date.setText(((DateEtHeure)elements.get(3)).getDate());
        Font font = new Font("Adobe Fangsong Std R",Font.BOLD,22);
        date.setFont(font);
        date.setOpaque(true);
        date.setBounds(elements.get(3).getCoordX(), elements.get(3).getCoordY(), elements.get(3).getLongueur(), elements.get(3).getHauteur());
        date.setForeground(couleur);
        date.setOpaque(false);
        afficheDate();
    }
    
    /**
     * Affichage de la date
     */
    public void afficheDate()
    {
        elementsGra.add(date,Integer.valueOf(2));
        elementsGra.repaint();
    }
    
    /**
     * Initialisation de la météo
     */
    private void initMeteo() 
    {
        effaceMeteo();
        ((MeteoJour)elements.get(5)).recupMeteoNow("Bourg-en-Bresse");

        meteo.setText(((MeteoJour)elements.get(5)).afficheTemperature());
        
        meteo.setFont(new Font("Adobe Fangsong Std R",Font.BOLD,30));
        meteo.setOpaque(true);
        meteo.setBounds(elements.get(5).getCoordX()+50, elements.get(5).getCoordY(), elements.get(5).getLongueur(),elements.get(5).getHauteur());
        meteo.setForeground(couleur);
        meteo.setOpaque(false);
        afficheMeteo();
     }
    
    /**
     * Efface la météo
     */
    public void effaceMeteo()
    {
        elementsGra.remove(imageMeteo_soleil);
        elementsGra.remove(imageMeteo_nuage);
        elementsGra.remove(imageMeteo_pluit);
        elementsGra.remove(imageMeteo_orage);
        elementsGra.remove(imageMeteo_neige);     
        elementsGra.remove(imageMeteo_brouillard);
        elementsGra.remove(meteo);
    }
    
    /**
     * Affichage de la météo
     */
    public void afficheMeteo()
    {
        EffaceDegrade();
        afficheDecrade();
        getIconMeteo();
        elementsGra.add(meteo,Integer.valueOf(2));
        elementsGra.repaint();
    }
    
    /**
     * Initialisation de l'actualité
     */
    public void initActu()
    {
        Font titre = new Font("Adobe Fangsong Std R",Font.BOLD,20);
        
        JLabel t=new JLabel("Infos du jour");
        actualite.add(t);
        t.setBounds(elements.get(1).getCoordX(), elements.get(1).getCoordY(), elements.get(1).getLongueur(), 40);
        t.setFont(titre);
        t.setOpaque(false);
        t.setForeground(couleur);
        
        ((InformationDuJour)(elements.get(1))).recupInfo();
        
        for(int i=0;i<5;i++)
        {
        JLabel n=new JLabel(((InformationDuJour)(elements.get(1))).getInfoNumero(i).getTitre());
        actualite.add(n);
        n.setBounds(elements.get(1).getCoordX(),elements.get(1).getCoordY()+40+30*i, elements.get(1).getLongueur(), 30);
        n.setForeground(couleur);
        n.setOpaque(false);
        }
        afficheActu();
    }
    
    /**
     * Affichage de l'actualité
     */
    public void afficheActu()
    {     
        try
        {
        for(int i=0;i<actualite.size();i++)
        {
             elementsGra.add(actualite.get(i),Integer.valueOf(2));
        }
        elementsGra.repaint();
        }
        catch(Exception x){System.out.println("problème d'affichage des événements");}
    }
    
    /**
     * Défilement de l'actualité
     */
    public void rotationActu()
    {
        elementsGra.remove(actualite.get(0));

        
        for(int i=1;i<actualite.size();i++)
        {
            elementsGra.remove(actualite.get(i));
            actualite.get(i).setText(actualite.get(i).getText().substring(3,actualite.get(i).getText().length())+actualite.get(i).getText().substring(0,3));
        }
        afficheActu();
    }

    /**
     * Initialisation du bouton photo
     */
    public void initPhoto()
    {
        imagePhoto.setBounds(elements.get(2).getCoordX(),elements.get(2).getCoordY(), elements.get(2).getLongueur(), elements.get(2).getHauteur()); 
        imagePhoto.setOpaque(false);
        imagePhoto.setContentAreaFilled(false);
        imagePhoto.setBorderPainted(false);
        elementsGra.add(imagePhoto,Integer.valueOf(3));
    }
    
    /**
     * Initialisation des événements
     */
    public void initEvenement()
    {
        Font titre = new Font("Adobe Fangsong Std R",Font.BOLD,20);
        
        evenement.clear();
                     
 
        JLabel n=new JLabel("Vos prochains événements");
        evenement.add(n);
        n.setBounds(elements.get(0).getCoordX(),elements.get(0).getCoordY(), elements.get(0).getLongueur(),30);
        n.setOpaque(false);
        n.setForeground(couleur);
        
        if(((Agenda)elements.get(0)).getListRappel().size()==0)
        {
            rienPrevu=new JLabel("Vous n'avez rien de prévu ;)");
            evenement.add(rienPrevu);
            rienPrevu.setBounds(elements.get(0).getCoordX(),elements.get(0).getCoordY()+30, elements.get(0).getLongueur(),30);
            rienPrevu.setOpaque(false);
            rienPrevu.setForeground(couleur);
        }
        
        else if(((Agenda)elements.get(0)).getListRappel().size()==1)
        {
            try{
            elementsGra.remove(rienPrevu);
            evenement.remove(rienPrevu);
            }
            catch(Exception x){System.out.println("1 élément dans la liste");}
            
            JLabel l=new JLabel(((Agenda)elements.get(0)).getListRappel().get(0).getDateheure().toString());
            evenement.add(l);
            l.setBounds(elements.get(0).getCoordX(),elements.get(0).getCoordY()+30, elements.get(0).getLongueur(),30);
            l.setOpaque(false);
            l.setForeground(couleur);
         
            
            JLabel m=new JLabel(((Agenda)elements.get(0)).getListRappel().get(0).getDescription());
            evenement.add(m);
            m.setBounds(elements.get(0).getCoordX(),elements.get(0).getCoordY()+60, elements.get(0).getLongueur(),30);
            m.setOpaque(false);
            m.setForeground(couleur);
        }
        
        else if(((Agenda)elements.get(0)).getListRappel().size()<4)
        {
            for(int i=0;i<((Agenda)elements.get(0)).getListRappel().size();i++)
            {
                 JLabel l=new JLabel(((Agenda)elements.get(0)).getListRappel().get(i).getDateheure().toString());
                evenement.add(l);
                l.setBounds(elements.get(0).getCoordX(),elements.get(0).getCoordY()+30+60*i, elements.get(0).getLongueur(),30);
                l.setOpaque(false);
                l.setForeground(couleur);
            
                JLabel m=new JLabel(((Agenda)elements.get(0)).getListRappel().get(i).getDescription());
                evenement.add(m);
                m.setBounds(elements.get(0).getCoordX(),elements.get(0).getCoordY()+60+60*i, elements.get(0).getLongueur(),30);
                m.setOpaque(false);
                m.setForeground(couleur);
            }
        }
        
        else
        {
            for(int i=0;i<3;i++)
            {
                JLabel m=new JLabel(((Agenda)elements.get(0)).getListRappel().get(i).getDescription());
                evenement.add(m);
                m.setBounds(elements.get(0).getCoordX(),elements.get(0).getCoordY()+30+30*i, elements.get(0).getLongueur(),30);
                m.setOpaque(false);
                m.setForeground(couleur);
            }
        }
        
        afficheEvenement();
    }
    
    /**
     * Affichage des événements
     */
    public void afficheEvenement()
    {
        for(int i=0;i<evenement.size();i++)
        {
            elementsGra.add(evenement.get(i));
        }
        elementsGra.repaint();
    }
    
    /**
     * Efface les événements
     */
    public void effaceEvenement()
    {
       try{
          for(int i=0;i<evenement.size();i++)
            {
                elementsGra.remove(evenement.get(i));
            }
        }
        catch(Exception x){System.out.println("pas assez d'éléments");}
    }
    
    /**
     * Initialisation de la fenetre des parametres
     */
    public void initParametre()
    {
       elementsPara=new ArrayList<>();
       case_coche=new ArrayList<>();
       sortie=new JButton();
       luminositeAuto=new JButton();
       luminositeAuto.addActionListener(this);
       ajouteEvenement=new JButton();
       ajouteEvenement.addActionListener(this);
       supprimeEvenement=new JButton();
       supprimeEvenement.addActionListener(this);
       photo=new JButton();
       photo.addActionListener(this);
       luminositeManuel=new JSlider();
       luminositeManuel.addChangeListener(this);
       
       valider=new JButton();
       valider.setText("VALIDER");
       valider.setBounds(0, 780, 480, 20);
       valider.setBackground(Color.GREEN);
       valider.addActionListener(this);
       
       fondPara= new Composant(590,250);
       fondPara.setBounds(230, 0, 250, 590);
       fondPara.setBackground(Color.RED);
       
       imageParametreInverse.setBounds(430, 10, 40, 40);
       imageParametreInverse.setOpaque(false);
       imageParametreInverse.setContentAreaFilled(false);
       imageParametreInverse.setBorderPainted(false);
       imageParametreInverse.addActionListener(this);
       
       JLabel a=new JLabel();
       a.setText("Afficher la date");
       elementsPara.add(a);
       
       JLabel b=new JLabel();
       b.setText("Afficher l'heure");
       elementsPara.add(b);
       
       JLabel c=new JLabel();
       c.setText("Afficher la météo");
       elementsPara.add(c);
       
       JLabel g=new JLabel();
       g.setText("Afficher les actualitées");
       elementsPara.add(g);
       
       JLabel d=new JLabel();
       d.setText("Afficher les évènements");
       elementsPara.add(d);
       
       JLabel e=new JLabel();
       e.setText("Afficher le bouton photo");
       elementsPara.add(e);
       
       JLabel h=new JLabel();
       h.setText("Météo: choisir votre ville");
       elementsPara.add(h);
       
       
       ville=new JComboBox();
       ville.addItem("Bourg-en-Bresse");
       ville.addItem("Paris");
       ville.addItem("Marseille");
       ville.addItem("Lyon");
       ville.addItem("Toulouse");
       ville.addItem("Nice");
       ville.addItem("Nantes");
       ville.addItem("Montpellier");
       ville.addItem("Strasbourg");
       ville.addItem("Bordeaux");
       ville.addItem("Lille");
       ville.addItem("Rennes");
       ville.addItem("Saint-Etienne");
       ville.addItem("Toulon");
       ville.addItem("Grenoble");
       ville.addItem("Dijon");
       ville.addItem("Angers");
       ville.addItem("Nîmes");
       ville.addItem("Villeurbanne");
       ville.addItem("Village du Père Noël");
       ville.setBounds(240, 240, 230, 30);
       ville.setBackground(Color.BLACK);
       ville.setForeground(couleur);
       ville.addActionListener(this);
       
       ajouteEvenement.setText("Ajouter un rappel");
       ajouteEvenement.setBounds(240, 270, 230, 30);
       ajouteEvenement.setBackground(new Color(20,20,20));
       ajouteEvenement.setForeground(couleur);
       ajouteEvenement.setBorderPainted(false);
       
       supprimeEvenement.setText("Supprimer un rappel");
       supprimeEvenement.setBounds(240, 300, 230, 30);
       supprimeEvenement.setBackground(new Color(20,20,20));
       supprimeEvenement.setForeground(couleur);
       supprimeEvenement.setBorderPainted(false);
       
       photo.setText("Voir les photos");
       photo.setBounds(240, 330, 230, 30);
       photo.setForeground(couleur);
       photo.setOpaque(false);
       photo.setContentAreaFilled(false);
       photo.setBorderPainted(false);
       
       luminositeAuto.setText("Luminosité auto de l'écran");
       luminositeAuto.setBounds(240, 360, 230, 30);
       luminositeAuto.setForeground(couleur);
       luminositeAuto.setOpaque(false);
       luminositeAuto.setContentAreaFilled(false);
       luminositeAuto.setBorderPainted(false);
       
       luminositeManuel.setBounds(240, 390, 230, 30);
       luminositeManuel.setForeground(couleur);
       luminositeManuel.setOpaque(false);
       
       modifierPara=new JButton();
       modifierPara.setText("Modifier la disposition");
       modifierPara.setBounds(250, 420, 210, 30);
       modifierPara.setForeground(couleur);
       modifierPara.setBackground(new Color(20,20,20));
       modifierPara.setBorderPainted(false);
       modifierPara.addActionListener(this);
       modifierPara.setOpaque(false);
       modifierPara.setContentAreaFilled(false);
       
       JLabel i=new JLabel();
       i.setText("Temps d'utilisation");
       i.setOpaque(false);
       i.setForeground(couleur);
       i.setBounds(300, 450, 150, 30);
       elementsPara.add(i);
       
       Font heure=new Font("Adobe Fangsong Std R",Font.BOLD,11);

       JLabel j=new JLabel();
       j.setText("Aujourd'hui:");
       j.setFont(heure);
       j.setOpaque(false);
       j.setForeground(couleur);
       j.setBounds(250, 480, 480, 30);
       elementsPara.add(j);
       
       JLabel f=new JLabel();
       f.setText(temps.afficheJour(0));
       f.setFont(heure);
       f.setOpaque(false);
       f.setForeground(couleur);
       f.setBounds(360, 540, 480, 30);
       elementsPara.add(f);
       
       JLabel o=new JLabel();
       o.setText("Moyenne hebdomadaire:");
       o.setFont(heure);
       o.setOpaque(false);
       o.setForeground(couleur);
       o.setBounds(250, 510, 480, 30);
       elementsPara.add(o);
       
       JLabel l=new JLabel();
       l.setText(temps.moyenneSemaine());
       l.setFont(heure);
       l.setOpaque(false);
       l.setForeground(couleur);
       l.setBounds(410, 510, 480, 30);
       elementsPara.add(l);
       
       JLabel m=new JLabel();
       m.setText("Moyenne tolale:");
       m.setFont(heure);
       m.setOpaque(false);
       m.setForeground(couleur);
       m.setBounds(250, 540, 480, 30);
       elementsPara.add(m);
       
       JLabel n=new JLabel();
       n.setText(temps.tempsMoyen());
       n.setFont(heure);
       n.setOpaque(false);
       n.setForeground(couleur);
       n.setBounds(340, 480, 480, 30);
       elementsPara.add(n);
       
       for(int k=0;k<elementsPara.size()-7;k++)
       {
           elementsPara.get(k).setForeground(couleur);
           elementsPara.get(k).setBounds(280, 30+30*k,180, 30);
           elementsPara.get(k).setOpaque(false);
       }
    
       for(int k=0;k<elementsPara.size()-8;k++)
       {
           case_coche.add(caseCoche(true));
           case_coche.get(k).setBounds(245, 25+30*k, 190, 30);
           case_coche.get(k).setBackground(new Color(20,20,20));
           case_coche.get(k).addActionListener(this);
           case_coche.get(k).setOpaque(false);
           case_coche.get(k).setContentAreaFilled(false);
       }
       
       sortie.setText("sortie");
       sortie.setBounds(310, 570, 100, 30);
       sortie.setForeground(new Color(20,20,20));
       sortie.setBackground(new Color(20,20,20));
       sortie.setBorderPainted(false);
       sortie.addActionListener(this);
       sortie.setOpaque(false);
       sortie.setContentAreaFilled(false);
    }
    
    /**
    * Affiche la fenêtre pour modifier les paramètre.
    */
    public void afficheParametre()
    {       
       elementsGra.add(fondPara,Integer.valueOf(3));
       elementsGra.add(imageParametreInverse,Integer.valueOf(4));
       elementsGra.add(sortie,Integer.valueOf(4));
       elementsGra.add(modifierPara,Integer.valueOf(4)); 
       elementsGra.add(luminositeAuto,Integer.valueOf(4));
       elementsGra.add(ajouteEvenement,Integer.valueOf(4));
       elementsGra.add(supprimeEvenement,Integer.valueOf(4));
       elementsGra.add(photo,Integer.valueOf(4));
       elementsGra.add(luminositeManuel,Integer.valueOf(4));
       elementsGra.add(ville,Integer.valueOf(4));

       
       for(int k=0;k<elementsPara.size();k++)
       {
           elementsGra.add(elementsPara.get(k),Integer.valueOf(4));
       }
    
       for(int k=0;k<elementsPara.size()-8;k++)
       {
           elementsGra.add(case_coche.get((k)),Integer.valueOf(4));
       }
       
       elementsGra.repaint();
    }
    
    /**
     * Cache les paramètres
     */
    public void effaceParametre()
    {
       elementsGra.remove(fondPara);
       elementsGra.remove(imageParametreInverse);
       elementsGra.remove(sortie);
       elementsGra.remove(modifierPara); 
       elementsGra.remove(luminositeAuto);
       elementsGra.remove(ajouteEvenement);
       elementsGra.remove(supprimeEvenement);
       elementsGra.remove(photo);
       elementsGra.remove(luminositeManuel);
       elementsGra.remove(ville);
              
       for(int k=0;k<elementsPara.size();k++)
       {
           elementsGra.remove(elementsPara.get(k));
       }
    
       for(int k=0;k<elementsPara.size()-8;k++)
       {
           elementsGra.remove(case_coche.get((k)));
       }
       
       elementsGra.repaint();
    } 
    
    /**
     * Initialise des JCheckBox
     * @param t - Etat booleen de la JCheckBox
     * @return JCheckbox
     */
    public JCheckBox caseCoche(boolean t)
    {
        JCheckBox b=new JCheckBox();  
        
        if(t==true)
        {
            b.setSelected(true);
        }
        else
        {
            b.setSelected(false);
        }
        
        return b;
    }

    /**
     * Affiche ou cache les éléments choisit
     * @param b - Affiche si true et cache si false
     * @param i - Rang de l'élément à afficher/cacher dans l'arraylist elements
     */
    public void affiche(boolean b,int i)
    {
         switch(i)
         {
                 case 0:
                    if(b==true)
                    {initDate();}
                     else
                    {elementsGra.remove(date);}
                       break;
                      
                 case 1:
                    if(b==true)                     
                    {initHeure();}
                     else
                    {elementsGra.remove(heure);}                     
                       break;
                       
                 case 2:
                    if(b==true)                     
                    {afficheMeteo();}
                     else
                    {
                    effaceMeteo();
                    }

                      break;
                      
                 case 3:
                    if(b==true) 
                    {
                        afficheActu();
                    }
                    else
                    {
                        for(int k=0;k<actualite.size();k++)
                        {
                            elementsGra.remove(actualite.get(k));
                        }
                    }          
                      break;
                      
                 case 4:
                     if(b==true)
                     {
                       afficheEvenement(); 
                     }
                     else
                     {
                     for(int k=0;k<evenement.size();k++)
                        {
                            elementsGra.remove(evenement.get(k));
                        }
                     }
                    break;
                      
                 case 5:
                    if(b==true)                     
                    {elementsGra.add(imagePhoto);}
                     else
                    {elementsGra.remove(imagePhoto);}
                      break;
                      
                case 6:
                    //led ambiance
                      break;
                      
                      
                 default: 
         }
        elementsGra.repaint();
    }
    
    /**
     * Affiche tout l'interface principal
     */
    public void affiche2()
    {
        if(para.getAfficherNum(0))
        {
           afficheDate();
        }
        
        if(para.getAfficherNum(1))
        {
           afficheHeure();
        }

        if(para.getAfficherNum(2))
        {
           afficheMeteo();
        }
        
        if(para.getAfficherNum(3))
        {
           afficheActu();
        }
        
        if(para.getAfficherNum(4))
        {
           afficheEvenement();
        }
        
        if(para.getAfficherNum(5))
        {
        elementsGra.add(imagePhoto,Integer.valueOf(2));
        }

        elementsGra.add(imageParametre,Integer.valueOf(2));
        elementsGra.repaint();
    }

    /**
     * Renvoie l'icone de météo adapter selon la météo
     */
    public void getIconMeteo()
    {
        switch(((MeteoJour)elements.get(5)).getEtat())
         {
                 case pluie:
                      imageMeteo_pluit.setBounds(elements.get(5).getCoordX()-50, elements.get(5).getCoordY(), elements.get(5).getLongueur(), elements.get(5).getHauteur()); 
                      elementsGra.add(imageMeteo_pluit,Integer.valueOf(2));
                      break;
                      
                 case nuage:
                       imageMeteo_nuage.setBounds(elements.get(5).getCoordX()-50, elements.get(5).getCoordY(), elements.get(5).getLongueur(), elements.get(5).getHauteur()); 
                       elementsGra.add(imageMeteo_nuage,Integer.valueOf(2));
                       break;
                       
                 case neige:
                     imageMeteo_neige.setBounds(elements.get(5).getCoordX()-50, elements.get(5).getCoordY(), elements.get(5).getLongueur(), elements.get(5).getHauteur()); 
                     elementsGra.add(imageMeteo_neige,Integer.valueOf(2));
                      break;
                      
                 case soleil:
                     imageMeteo_soleil.setBounds(elements.get(5).getCoordX()-50, elements.get(5).getCoordY(), elements.get(5).getLongueur(), elements.get(5).getHauteur()); 
                     elementsGra.add(imageMeteo_soleil,Integer.valueOf(2));
                      break;
                      
                 case orage:
                     imageMeteo_orage.setBounds(elements.get(5).getCoordX()-50, elements.get(5).getCoordY(), elements.get(5).getLongueur(), elements.get(5).getHauteur()); 
                     elementsGra.add(imageMeteo_orage,Integer.valueOf(2));
                      break;
                      
                 case brouillard:
                     imageMeteo_brouillard.setBounds(elements.get(5).getCoordX()-50, elements.get(5).getCoordY(), elements.get(5).getLongueur(), elements.get(5).getHauteur()); 
                     elementsGra.add(imageMeteo_brouillard,Integer.valueOf(2));
                      break;
                      
                 default:
         }
    }


    public JLayeredPane getElementsGra() {
        return elementsGra;
    }

    public ArrayList<Element> getElements() {
        return elements;
    }

    public boolean isSelectionne() {
        return selectionne;
    }

    public ArrayList<JLabel> getElementsPara() {
        return elementsPara;
    }

    public boolean isTourne() {
        return tourne;
    }

    public TempsUtilisation getTemps() {
        return temps;
    }

    public boolean isIspara() {
        return ispara;
    }

    public JSlider getLuminositeManuel() {
        return luminositeManuel;
    }

    public JComboBox getVille() {
        return ville;
    }

    public JLabel getMeteo() {
        return meteo;
    }
    
    public JLabel getHeure() {
        return heure;
    }

    public JLabel getDate() {
        return date;
    }

    public Gribouilleur getDessin() {
        return dessin;
    }

    public parametre getPara() {
        return para;
    }

    public Color getCouleur() {
        return couleur;
    }
    
    public Agenda getAgenda()
    {
        return ((Agenda)elements.get(0));
    }
    
    /**
    * Modifie la couleur du texte selon la luminosite.
    */
    public void setCouleur()
    {
        try
        {
            int c=(int)luminosite.getLuminositeAuto();
            
            
            c=c*2+55;
            couleur=new Color(c,c,c);
        }
        
        catch(Exception x)
        {System.err.println("composant Red Green Blue >255 ou <0");
        couleur=new Color(255,255,255);}
    }
    
    /**
     * Actualise la couleur des textes en fonction de la luminosité
     * @param manuel - Booleen luminosité manuelle ou non
     */
    public void actuCouleur(boolean manuel)
    {
        if(manuel==false)
        {
        setCouleur();
        }
        else
        {
            int c=luminositeManuel.getValue()*2+55;
            couleur=new Color(c,c,c);
        }

        elementsGra.removeAll();
        
        heure.setForeground(couleur);
        if(para.getAfficherNum(1))
        {
           afficheHeure();
        }
        
        date.setForeground(couleur);
        if(para.getAfficherNum(0))
        {
           afficheDate();
        }
        
        meteo.setForeground(couleur);

        if(para.getAfficherNum(2))
        {
           afficheMeteo();
        }
        
        for(int i=0;i<evenement.size();i++)
        {
            evenement.get(i).setForeground(couleur);
        }
        
        for(int i=0;i<actualite.size();i++)
        {
            actualite.get(i).setForeground(couleur);
        }
        
        if(para.getAfficherNum(3))
        {
          afficheActu();
        }
        
        for(int i=0;i<elementsPara.size();i++)
        {
            elementsPara.get(i).setForeground(couleur);
        }
        
        if(para.getAfficherNum(4))
        {
           afficheEvenement();
        }
        
        if(para.getAfficherNum(5))
        {
          elementsGra.add(imagePhoto,Integer.valueOf(2));
        }
        
        ajouteEvenement.setForeground(couleur);
        supprimeEvenement.setForeground(couleur);
        photo.setForeground(couleur);
        modifierPara.setForeground(couleur);
        luminositeAuto.setForeground(couleur);
        ville.setForeground(couleur);
 
        afficheParametre();
    }
    
    /**
     * Initialise la visualisation des photos
     */
    public void initVoirPhotoPrise()
    {
        ispara=false;
        
        gauche=new JButton();
        gauche.addActionListener(this);
        droite=new JButton();
        droite.addActionListener(this);
        sortir=new JButton();
        sortir.addActionListener(this);
        modifierImg=new JButton();
        modifierImg.addActionListener(this);

        Font bouton= new Font("Adobe Fangsong Std R",Font.BOLD,55);
        
        gauche.setText("<<");
        gauche.setForeground(couleur);
        gauche.setBorderPainted(false);
        gauche.setFont(bouton);
        gauche.setOpaque(false);
        gauche.setContentAreaFilled(false);
        gauche.setBounds(0, 330, 130, 75);
        
        droite.setText(">>");
        droite.setForeground(couleur);
        droite.setBorderPainted(false);
        droite.setFont(bouton);
        droite.setOpaque(false);
        droite.setContentAreaFilled(false);
        droite.setBounds(380, 330, 130, 75);
        
        sortir.setText("retour");
        sortir.setBackground(Color.RED);
        sortir.setForeground(couleur);
        sortir.setBorderPainted(false);
        sortir.setBounds(0, 780, 480, 20);
        
        modifierImg.setText("Modifier l'image");
        modifierImg.setForeground(couleur);
        modifierImg.setBorderPainted(false);
        modifierImg.setOpaque(false);
        modifierImg.setContentAreaFilled(false);
        modifierImg.setBounds(0, 0, 480, 20);
        
        elementsGra.add(gauche,Integer.valueOf(3));
        elementsGra.add(droite,Integer.valueOf(3));
        elementsGra.add(sortir,Integer.valueOf(3));
        elementsGra.add(modifierImg,Integer.valueOf(3));
        
        affichePhotoPrise(0);
    }
    
    /**
     * Affichage des photos prises
     * @param a - Rang de l'image a afficher
     */
    public void affichePhotoPrise(int a)
    {

        if(System.getProperty("os.name").toString().equals("Windows 10")&& System.getProperty("os.name").equals("Windows 11"))
            {
              File directoryPath = new File("C:\\Users\\guill\\OneDrive\\Images\\Captures d’écran");
              String contents[] = directoryPath.list();
                try
                {
                    System.out.println("C:\\Users\\guill\\OneDrive\\Images\\Captures d’écran\\"+contents[imageActu]);
                    image=new JLabel(new ImageIcon("C:\\Users\\guill\\OneDrive\\Images\\Captures d’écran\\"+contents[imageActu]));

                    image.setBounds(0,0,480,800);

                    elementsGra.add(image,Integer.valueOf(2));
                }
                catch(Exception x){System.out.println("pas d'image à modifier windows");}
            }


            else
            {
                File directoryPath = new File("/home/pi/Pictures");
               String contents[] = directoryPath.list();
                try
                {
                image=new JLabel(new ImageIcon("/home/pi/Pictures/"+contents[imageActu]));

                image.setBounds(0,0,480,800);

                elementsGra.add(image,Integer.valueOf(2));
                }
                catch(Exception x){System.out.println("pas d'image à modifier raspberry");}
            }
            
            elementsGra.repaint();
    }
    
    
    /**
     * Initialise la modification des photos
     */
    public void initModif()
    {
            elementsGra.remove(gauche);
            elementsGra.remove(droite);
            elementsGra.remove(sortir);
            elementsGra.remove(modifierImg);
            
            JButton a=new JButton();
            a.setText("retour");
            a.setBounds(0,780, 120,20);
            a.setBackground(Color.RED);
            a.setForeground(couleur);
            a.addActionListener(this);
            boutonDessin.add(a);
            elementsGra.add(a,Integer.valueOf(4));
            
            JButton b=new JButton();
            b.setText("Sup tout");
            b.setBackground(new Color(20,20,20));
            b.setBounds(121,780, 120,20);
            b.setForeground(couleur);
            b.addActionListener(this);
            boutonDessin.add(b);            
            elementsGra.add(b,Integer.valueOf(4));
            
            JButton c=new JButton();
            c.setText("Sup der");
            c.setBackground(new Color(20,20,20));
            c.setBounds(241,780, 120,20);
            c.setForeground(couleur);
            c.addActionListener(this);
            boutonDessin.add(c);
            elementsGra.add(c,Integer.valueOf(4));
            
            JButton f=new JButton();
            f.setText("Valider");
            f.setBackground(Color.GREEN);
            f.setBounds(361,780, 120,20);
            f.setForeground(couleur);
            f.addActionListener(this);
            boutonDessin.add(f);
            elementsGra.add(f,Integer.valueOf(4));
            
            elementsGra.repaint();
            
            if(System.getProperty("os.name").toString().equals("Windows 10")&& System.getProperty("os.name").equals("Windows 11"))
            {
              File directoryPath = new File("C:\\Users\\guill\\OneDrive\\Images\\Captures d’écran");
              String contents[] = directoryPath.list();
                try
                {
                    System.out.println("C:\\Users\\guill\\OneDrive\\Images\\Captures d’écran\\"+contents[imageActu]);
                    dessin=new Gribouilleur(new ImageIcon("C:\\Users\\guill\\OneDrive\\Images\\Captures d’écran\\"+contents[imageActu]));

                    dessin.setBounds(0,0,480,800);

                    elementsGra.add(dessin,Integer.valueOf(4));
                }
                catch(Exception x){System.out.println("pas d'image à modifier windows");}
            }


            else
            {
            File directoryPath = new File("/home/pi/Pictures");
            String contents[] = directoryPath.list();
                try
                {
                dessin=new Gribouilleur(new ImageIcon("/home/pi/Pictures/"+contents[imageActu]));

                dessin.setBounds(0,0,480,800);

                elementsGra.add(dessin,Integer.valueOf(4));
                }
                catch(Exception x){System.out.println("pas d'image à modifier raspberry");}
            }
            
            elementsGra.repaint();     
    }
    
    /**
     * Cache le menu de modification
     */
    public void effaceModif()
    {
        for(int i=0;i<boutonDessin.size();i++)
        {
           elementsGra.remove(boutonDessin.get(i));
        } 

        boutonDessin.clear();

        try{
        elementsGra.remove(dessin);
        }
        catch(Exception x){System.out.println("version PC: sortie du mode mode modification de photo");}

         elementsGra.add(gauche);
         elementsGra.add(droite);
         elementsGra.add(sortir);
         elementsGra.add(modifierImg);
         elementsGra.repaint();
    }
    
    /**
     * Affiche le dégradé de couleur en arrière plan du menu principal
     */
    public void afficheDecrade()
    {
        switch(((MeteoJour)elements.get(5)).getEtat())
         {
                 case pluie:
                      imageDegradeBleu.setBounds(0, 0, 480, 200); 
                      elementsGra.add(imageDegradeBleu,Integer.valueOf(0));
                      break;
                      
                 case nuage:
                       imageDegradeGris.setBounds(0, 0, 480, 200); 
                       elementsGra.add(imageDegradeGris,Integer.valueOf(0));
                       break;
                       
                 case neige:
                     imageDegradeGris.setBounds(0, 0, 480, 200); 
                     elementsGra.add(imageDegradeGris,Integer.valueOf(0));
                      break;
                      
                 case soleil:
                     imageDegradeJaune.setBounds(0, 0, 480, 200); 
                     elementsGra.add(imageDegradeJaune,Integer.valueOf(0));
                      break;
                      
                 case orage:
                     imageDegradeBleu.setBounds(0, 0, 480, 200); 
                     elementsGra.add(imageDegradeBleu,Integer.valueOf(0));
                      break;
                      
                 case brouillard:
                     imageDegradeGris.setBounds(0, 0, 480, 200); 
                     elementsGra.add(imageDegradeGris,Integer.valueOf(0));
                      break;
                      
                 default:
         }
        
    }
    
    /**
     * Cache le dégradé de couleur
     */
    public void EffaceDegrade()
    {
        elementsGra.remove(imageDegradeBleu);
        elementsGra.remove(imageDegradeGris);
        elementsGra.remove(imageDegradeJaune);
    }
    
    /**
     * Prend un screenshot
     */
    void screenShot()
    {
        for(int i=0;i<boutonDessin.size();i++)
        {
           elementsGra.remove(boutonDessin.get(i));
        } 

        boutonDessin.clear();
        elementsGra.repaint();

        BufferedImage bufImage = new BufferedImage(elementsGra.getSize().width, elementsGra.getSize().height,BufferedImage.TYPE_INT_ARGB);

        elementsGra.paint(bufImage.createGraphics());

        elementsGra.repaint();

        File directoryPath;
        
        if(System.getProperty("os.name").toString().equals("Windows 10")&& System.getProperty("os.name").equals("Windows 11"))
        {
            directoryPath = new File("C:\\Users\\guill\\OneDrive\\Images\\Captures d’écran");
        }
        else
        {
           directoryPath = new File("/home/pi/Pictures");
        }

        String contents[] = directoryPath.list();
        try{
        screenshot.takeScreenShot(contents[imageActu].replace(".png", "_modif.png"));
        }catch(Exception ex){
            System.out.println("problème de screenshot");
        }

        try{
        elementsGra.remove(dessin);
        }
        catch(Exception x){System.out.println("version PC: sortie du mode mode modification de photo");}

         elementsGra.add(gauche);
         elementsGra.add(droite);
         elementsGra.add(sortir);
         elementsGra.add(modifierImg);
         elementsGra.repaint();
    }
            

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource()==imageParametre)
        {
             System.out.println("Ouverture Paramètre");
             elementsGra.remove(imageParametre);
             afficheParametre();
             ispara=true;
        }
        
        else if(e.getSource()==imagePhoto)
        {
            System.out.println("Prend une Photo");
            if(System.getProperty("os.name").toString().equals("Linux")){
                try {
                    if(luminosite.getLuminositeAuto()<25)
                    {
                        led.ON();
                    }
                    
                    cam.takeStill("%d.png", 480, 800); 
                    
                    led.OFF();

                } 
                catch (IOException ex) {
                    System.err.println("Erreur: impossible de prendre une image");
                } catch (InterruptedException ex) {
                    System.err.println("Erreur: impossible de prendre une image");
                }
                
            }
            else {
                System.out.println("Photo Prise !");
            }
        }
      
        else if(e.getSource()==sortie)
        {
            try {
                Runtime run = Runtime.getRuntime();
                run.exec("vcgencmd display_power 1");
            } catch (IOException ex) {
                System.out.println("Erreur : Retirer le mode veille");
            }
                temps.saveData();
                System.exit(0);
            
        }

        else if(e.getSource()==luminositeAuto)
        {
            actuCouleur(false);
            System.out.println("Calucl de la luminosité automatique");
        }
        
        else if(e.getSource()==modifierPara)
        {
            System.out.println("Début des modifications");
            
            elementsGra.add(valider,Integer.valueOf(3));
            
            elementsGra.remove(imagePhoto);
            
            imagePhotoDeplacement.setBounds(elements.get(2).getCoordX(),elements.get(2).getCoordY(),elements.get(2).getLongueur(), elements.get(2).getHauteur());
            elementsGra.add(imagePhotoDeplacement,Integer.valueOf(2));
            
            isModif=true;
            ispara=false;

            effaceParametre();
        }
        
        else if(e.getSource()==valider)
        {
            isModif=false;
            elementsGra.remove(valider);
            elementsGra.add(imageParametre,Integer.valueOf(3));
            imagePhoto.setBounds(elements.get(2).getCoordX(),elements.get(2).getCoordY(),50,50);
            elementsGra.add(imagePhoto,Integer.valueOf(2));
            elementsGra.remove(imagePhotoDeplacement);
            elementsGra.repaint();
        }
        
        else if(e.getSource()==ajouteEvenement)
        {
             AjoutEvenement fe = new AjoutEvenement(this);
             Rappel r=fe.showDialog();
            
             
             if(r!=null)
             {   
              ((Agenda)elements.get(0)).addEvenement(r);
              effaceEvenement();
              initEvenement();
             }
             ((Agenda)elements.get(0)).saveData();
        }
        
        
        else if(e.getSource()==supprimeEvenement)
        {
             SupprimeEvenement fe = new SupprimeEvenement(this);
             Rappel r=fe.showDialog();
            
             if(r!=null)
             {   
              ((Agenda)elements.get(0)).removeEvenement(r);
              effaceEvenement();
              initEvenement();
             }
             ((Agenda)elements.get(0)).saveData();
        }
        
        
        
        else if(e.getSource()==photo)
        {
            tourne=false;
            elementsGra.removeAll();
            initVoirPhotoPrise();
        }
        
        if(e.getSource()==gauche)
        {
            File directoryPath ;
            if(System.getProperty("os.name").toString().equals("Windows 10")&& System.getProperty("os.name").equals("Windows 11"))
            {
              directoryPath = new File("C:\\Users\\guill\\OneDrive\\Images\\Captures d’écran");
            }
            else
            {
              directoryPath = new File("/home/pi/Pictures");
            }
            
            try{
            if(imageActu==0)
            {
             imageActu=directoryPath.list().length-1;
            }
            else
            {
                imageActu--;
            }
                       
            elementsGra.remove(image);
            affichePhotoPrise(imageActu);
            }
            catch(Exception x){System.out.println("impossible d'aller a gauche");}
        }
        
        if(e.getSource()==droite)
        {    
            File directoryPath;
            if(System.getProperty("os.name").toString().equals("Windows 10")&& System.getProperty("os.name").equals("Windows 11"))
            {
              directoryPath = new File("C:\\Users\\guill\\OneDrive\\Images\\Captures d’écran");
            }
            else
            {
              directoryPath = new File("/home/pi/Pictures");
            }
             
            
            if(imageActu< directoryPath.list().length-2)            
            {
            imageActu++;
            }
            else
            {
                imageActu=0;
            }
            
            elementsGra.remove(image);   

            
            affichePhotoPrise(imageActu);
        }
        
        else if(e.getSource()==sortir)
        {
            elementsGra.remove(gauche);
            elementsGra.remove(droite);
            elementsGra.remove(sortir);
            elementsGra.remove(modifierImg);
            
            try
            {
            elementsGra.remove(image); 
            }
            catch(Exception x){}
            tourne=true;

            affiche2();
        }
        
        else if(e.getSource()==imageParametreInverse)
        {
             effaceParametre();
             elementsGra.add(imageParametre);
            
             ispara=false;
        }
        
        else if(e.getSource()==modifierImg)
        {
            selectionne=false;  
            initModif();
        }
        
        else if(boutonDessin.size()!=0)
        {
            if(e.getSource()==boutonDessin.get(0))
            {
                effaceModif();
            }

            else if(e.getSource()==boutonDessin.get(1))
            {
                try{
                dessin.getPoint().clear();
                dessin.repaint();
                
                if(Math.random()<0.01)
                {
                dessin.getPoint().add(125);
                dessin.getPoint().add(600);
                
                dessin.getPoint().add(425);
                dessin.getPoint().add(400);
                
                dessin.getPoint().add(75);
                dessin.getPoint().add(400);
                
                dessin.getPoint().add(375);
                dessin.getPoint().add(600);
                
                dessin.getPoint().add(250);
                dessin.getPoint().add(300);
                
                dessin.getPoint().add(125);
                dessin.getPoint().add(600);
                
                dessin.getPoint().add(375);
                dessin.getPoint().add(600);
                
                dessin.getPoint().add(425);
                dessin.getPoint().add(400);
                
                dessin.getPoint().add(250);
                dessin.getPoint().add(300);
                
                dessin.getPoint().add(75);
                dessin.getPoint().add(400);
                
                dessin.getPoint().add(125);
                dessin.getPoint().add(600);
                
                dessin.getPoint().add(0);
                dessin.getPoint().add(0);
                
                JOptionPane.showMessageDialog(rootPane, "Easter egg : satan est partout !");
                dessin.repaint();
                }
                
                
                }
                catch(Exception x){System.out.println("version PC: tout supprimer");}
            }

            else if(e.getSource()==boutonDessin.get(2))
            {
                try
                {
                dessin.getPoint().remove(dessin.getPoint().size()-1);
                dessin.getPoint().remove(dessin.getPoint().size()-1);
                dessin.repaint();
                }
                catch(Exception x){System.out.println("version PC: supprmer le dernier point");}
            }

            else if(e.getSource()==boutonDessin.get(3))
            {
                screenShot();
                System.out.println("screnn");
            }
        }
        
        else if(e.getSource()==ville)
        {
            System.out.println("changement du lieu de la meteo");
            ((MeteoJour)elements.get(5)).recupMeteoNow((String)ville.getSelectedItem());
            effaceMeteo();
            meteo.setText(((MeteoJour)elements.get(5)).afficheTemperature());
            afficheMeteo();
        }
        
        for (int i=0;i<case_coche.size();i++)
        {
            if(e.getSource()==case_coche.get(i))
            {
                System.out.println("Appuie sur la case "+i);
                parametre.setAfficherNum(i,case_coche.get(i).isSelected());
                affiche(case_coche.get(i).isSelected(),i);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) 
    {
        
        if(ispara==true)
        {
            if(e.getX()<250 ||e.getY()>500)
            {
                effaceParametre();
                elementsGra.add(imageParametre);
            
                ispara=false;
            }
            
       
            if(selectionne==false)
            {
                for(int i=0;i<elements.size();i++)
                {
                    if(e.getX()>=elements.get(i).getCoordX() && e.getX()<=elements.get(i).getCoordX()+elements.get(i).getLongueur() && e.getY()>=elements.get(i).getCoordY() && e.getY()<=elements.get(i).getCoordY()+elements.get(i).getHauteur())
                    {
                        selectionne=true;
                        selectionneNum=i;
                        System.out.println("l'élément "+i+" est sélectionné");
                    }
                }
            }
            
            else
            {
              selectionne=false;  
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(isModif==true)
        {
            selectionne=false;  
            tourne=true;
            System.out.println("pauser");
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) 
    {
        
        if(isModif==true)
        {
            if(selectionne==false)
            {
                for(int i=0;i<elements.size();i++)
                {
                    if(e.getX()>=elements.get(i).getCoordX() && e.getX()<=elements.get(i).getCoordX()+elements.get(i).getLongueur() && e.getY()>=elements.get(i).getCoordY() && e.getY()<=elements.get(i).getCoordY()+elements.get(i).getHauteur())
                    {
                        selectionne=true;
                        selectionneNum=i;
                        System.out.println("l'élément "+i+" est sélectionné");
                    }
                }
            }
        }
        
         if(selectionne==true)
       {
           if(selectionneNum==0)
           {
               if(e.getX()>elements.get(0).getLongueur()/2 && e.getX()<480-elements.get(0).getLongueur()/2 && e.getY()>elements.get(0).getHauteur()/2 && e.getY()<800-elements.get(0).getHauteur()/2)
               {
                  for(int i=0;i<evenement.size();i++)
                  {
                      elementsGra.remove(evenement.get(i));
                  }
                           
                   elements.get(0).setCoordX(e.getX()-elements.get(0).getLongueur()/2);
                   elements.get(0).setCoordY(e.getY()-elements.get(0).getHauteur()/2);
               
                  for(int i=0;i<evenement.size();i++)
                  {
                      evenement.get(i).setBounds(elements.get(0).getCoordX(),elements.get(0).getCoordY()+30*i,175, 55);
                  }
                  afficheEvenement();
               }
           }
           
           
           else if(selectionneNum==1)
           {
                if(e.getX()>elements.get(1).getLongueur()/2 && e.getX()<480-elements.get(1).getLongueur()/2 && e.getY()>elements.get(1).getHauteur()/2 && e.getY()<800-elements.get(1).getHauteur()/2)
               {
                  for(int i=0;i<actualite.size();i++)
                  {
                      elementsGra.remove(actualite.get(i));
                  }
                           
                   elements.get(1).setCoordX(e.getX()-elements.get(1).getLongueur()/2);
                   elements.get(1).setCoordY(e.getY()-elements.get(1).getHauteur()/2);
               
                  for(int i=0;i<actualite.size();i++)
                  {
                      actualite.get(i).setBounds(elements.get(1).getCoordX(),elements.get(1).getCoordY()+30*i,175, 55);
                  }
                  afficheActu();
               }
           }
           
           
           else if(selectionneNum==2)
           {
               if(e.getX()>elements.get(2).getLongueur()/2 && e.getX()<480-elements.get(2).getLongueur()/2 && e.getY()>elements.get(2).getHauteur()/2 && e.getY()<800-elements.get(2).getHauteur()/2)
               {
               elementsGra.remove(imagePhotoDeplacement);
               elements.get(2).setCoordX(e.getX()-elements.get(2).getLongueur()/2);
               elements.get(2).setCoordY(e.getY()-elements.get(2).getHauteur()/2);
               imagePhotoDeplacement.setBounds(elements.get(2).getCoordX(),elements.get(2).getCoordY(),50,50);
               elementsGra.add(imagePhotoDeplacement);
               }
           }
           
           
           else if(selectionneNum==3)
           {
               if(e.getX()>elements.get(3).getLongueur()/2 && e.getX()<480-elements.get(3).getLongueur()/2 && e.getY()>elements.get(3).getHauteur()/2 && e.getY()<800-elements.get(3).getHauteur()/2)
               {
               elementsGra.remove(date);
               elements.get(3).setCoordX(e.getX()-elements.get(3).getLongueur()/2);
               elements.get(3).setCoordY(e.getY()-elements.get(3).getHauteur()/2);
               date.setBounds(elements.get(3).getCoordX(),elements.get(3).getCoordY(),250, 30);
               afficheDate();
               }
           }
           
           
           else if(selectionneNum==4)
           {
               if(e.getX()>elements.get(4).getLongueur()/2 && e.getX()<480-elements.get(4).getLongueur()/2 && e.getY()>elements.get(4).getHauteur()/2 && e.getY()<800-elements.get(4).getHauteur()/2)
               {
               elementsGra.remove(heure);
               elements.get(4).setCoordX(e.getX()-elements.get(4).getLongueur()/2);
               elements.get(4).setCoordY(e.getY()-elements.get(4).getHauteur()/2);
               heure.setBounds(elements.get(4).getCoordX(),elements.get(4).getCoordY(),175, 55);
               afficheHeure();
               }
           }
           
           else if(selectionneNum==5)
           {
               if(e.getX()>elements.get(5).getLongueur()/2 && e.getX()<480-elements.get(5).getLongueur()/2 && e.getY()>elements.get(5).getHauteur()/2 && e.getY()<800-elements.get(5).getHauteur()/2)
               {
               effaceMeteo();
               elements.get(5).setCoordX(e.getX()-elements.get(5).getLongueur()/2);
               elements.get(5).setCoordY(e.getY()-elements.get(5).getHauteur()/2);
               
               meteo.setBounds(elements.get(5).getCoordX()+50,elements.get(5).getCoordY(),150,50);
               
               imageMeteo_soleil.setBounds(elements.get(5).getCoordX(),elements.get(5).getCoordY(),150,50);
               imageMeteo_nuage.setBounds(elements.get(5).getCoordX(),elements.get(5).getCoordY(),150,50);
               imageMeteo_pluit.setBounds(elements.get(5).getCoordX(),elements.get(5).getCoordY(),150,50);
               imageMeteo_orage.setBounds(elements.get(5).getCoordX(),elements.get(5).getCoordY(),150,50);
               imageMeteo_neige.setBounds(elements.get(5).getCoordX(),elements.get(5).getCoordY(),150,50);
               imageMeteo_brouillard.setBounds(elements.get(5).getCoordX(),elements.get(5).getCoordY(),150,50);
               afficheMeteo();
               }
           }
           
           elementsGra.repaint();
       }
    }

    @Override
    public void mouseMoved(MouseEvent e) 
    {
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if(e.getSource()==luminositeManuel)
        {
            tourne=false;

            actuCouleur(true);

            tourne=true;            
        }
    }
}
