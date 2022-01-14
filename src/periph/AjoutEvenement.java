
package periph;

import element.DateEtHeure;
import element.Rappel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Permet d'ajouter un evenement
 * @author p2026386 GRANDY Guillaume
 * @version 1.2 Ajout d'un clavier virtuel
 */
public class AjoutEvenement extends JDialog implements ActionListener{

    KeyBoard kb;
    
    JButton keyboard;
    JButton btAnnuler;
    JButton btValider;
    JComboBox jour;
    JComboBox mois;
    JComboBox annee;
    JComboBox heure;
    JComboBox minute;
    JComboBox type;
    JTextField commentaire;
    ArrayList<JLabel> texte;
    Rappel retour;

    
    public AjoutEvenement(Interface owner) 
{
    super(owner,true);
    
    kb = new KeyBoard(this);
    
    keyboard=new JButton("Clavier");
    keyboard.setBackground(Color.BLACK);
    keyboard.setForeground(Color.WHITE);
    btAnnuler=new JButton("Annuler");
    btAnnuler.setBackground(Color.red);
    btValider=new JButton("Valider");
    btValider.setBackground(Color.GREEN);
    
    jour=new JComboBox();
    jour.setBackground(new Color(20,20,20));
    jour.setForeground(owner.getCouleur());
    
    for(int i=0;i<31;i++)
    {
    jour.addItem(i+1);
    }
    
    mois=new JComboBox();
    mois.setBackground(new Color(20,20,20));
    mois.setForeground(owner.getCouleur());
    
    for(int i=0;i<12;i++)
    {
    mois.addItem(i+1);
    }
    
    annee=new JComboBox();
    annee.setBackground(new Color(20,20,20));
    annee.setForeground(owner.getCouleur());
    
    for(int i=2021;i<2031;i++)
    {
    annee.addItem(i);
    }
    
    heure=new JComboBox();
    heure.setBackground(new Color(20,20,20));
    heure.setForeground(owner.getCouleur());
    
    for(int i=0;i<24;i++)
    {
    heure.addItem(i);
    }
    
    minute=new JComboBox();
    minute.setBackground(new Color(20,20,20));
    minute.setForeground(owner.getCouleur());
    
    for(int i=0;i<12;i++)
    {
    minute.addItem(i*5);
    }
    
    type=new JComboBox();
    type.setBackground(new Color(20,20,20));
    type.setForeground(owner.getCouleur());
    type.addItem("rdv");
    type.addItem("anniv");
    type.addItem("sortie");
    type.addItem("travaille");
    type.addItem("fête");
    type.addItem("tache");
    
    commentaire=new JTextField();
    commentaire.setBackground(new Color(20,20,20));
    commentaire.setForeground(owner.getCouleur());
    
    if(Math.random()<0.02)
    {
        commentaire.setText("tu as trouvé un secret bien caché");
    }

    if(Math.random()<0.02)
    {
        commentaire.setText("tu as trouvé un secret bien caché");
    }
    
    texte=new ArrayList<>();
    
    texte.add(new JLabel("Ajouter un événement"));
    texte.add(new JLabel("Choisissez la date"));
    texte.add(new JLabel("/"));
    texte.add(new JLabel("/"));
    texte.add(new JLabel("Choisissez l'heure"));
    texte.add(new JLabel(":"));
    texte.add(new JLabel("Choisissez le type et entrez un commentaire"));
    
    for(int i=0;i<texte.size();i++)
    {
        texte.get(i).setForeground(owner.getCouleur());
    }
    
    keyboard.addActionListener(this);
    btValider.addActionListener(this);
    btAnnuler.addActionListener(this);
     
    JPanel pano = new JPanel();
    pano.setLayout(new GridBagLayout());
    GridBagConstraints cont = new GridBagConstraints();
    cont.insets.bottom=2;
    cont.insets.top=2;
    cont.insets.right=2;
    cont.insets.left=2;
    
    pano.setBackground(Color.BLACK);
    pano.setBorder(BorderFactory.createLineBorder(Color.WHITE));
    
    this.repaint();
//    this.setUndecorated(true);
    
    cont.gridwidth=6;
    cont.fill=GridBagConstraints.VERTICAL;
    
    cont.gridx = 0;
    cont.gridy = 0;
    pano.add(texte.get(0),cont);
    
    cont.fill=GridBagConstraints.BOTH;
    cont.gridx = 0;
    cont.gridy = 1;
    pano.add(texte.get(1),cont);
    
    cont.gridx = 0;
    cont.gridy = 3;
    pano.add(texte.get(4),cont);
    
    cont.gridx = 0;
    cont.gridy = 5;
    pano.add(texte.get(6),cont);
    
    
    cont.gridwidth=3;
    
    cont.gridx = 0;
    cont.gridy = 6;
    pano.add(type,cont);
    
    cont.gridx = 3;
    cont.gridy = 6;
    pano.add(commentaire,cont);
    
    cont.gridx = 3;
    cont.gridy = 7;
    pano.add(keyboard, cont);
    
    cont.gridx = 0;
    cont.gridy = 8;
    pano.add(btAnnuler, cont);
    
    cont.gridx = 3;
    cont.gridy = 8;
    pano.add(btValider, cont);
    
    
    
    cont.gridwidth=1;
    
    cont.gridx = 0;
    cont.gridy = 2;
    pano.add(jour, cont);
    
    cont.gridx = 1;
    cont.gridy = 2;
    pano.add(texte.get(2), cont);
    
    cont.gridx = 2;
    cont.gridy = 2;
    pano.add(mois, cont);
    
    cont.gridx = 3;
    cont.gridy = 2;
    pano.add(texte.get(3), cont);
    
    cont.gridx = 4;
    cont.gridy = 2;
    pano.add(annee, cont);
    
    cont.gridx = 0;
    cont.gridy = 4;
    pano.add(heure, cont);
    
    cont.gridx = 1;
    cont.gridy = 4;
    pano.add(texte.get(5), cont);
    
    cont.gridx = 2;
    cont.gridy = 4;
    pano.add(minute, cont);
    
    
    this.setContentPane(pano);
    this.pack();
    }

    /**
     * Fait apparaitre la fenetre AjoutEvenement
     * @return Rappel
     */
    public Rappel showDialog(){
    this.setVisible(true); 
    return retour;
    }
    

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        switch(e.getActionCommand()){
            case "Annuler":
                retour=null;
                this.setVisible(false); 
                break;
                
            case "Valider":
                retour= new Rappel((String)type.getSelectedItem()+" "+commentaire.getText(),new DateEtHeure((int)annee.getSelectedItem(),(int)mois.getSelectedItem(),(int)jour.getSelectedItem(),(int)heure.getSelectedItem(),(int)minute.getSelectedItem()));
                this.setVisible(false); 
                break;
                
            case "Clavier":
                kb.Open(commentaire);
                break;
        }
    }    
}

