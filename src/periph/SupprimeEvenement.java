
package periph;

import element.Agenda;
import element.Rappel;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Permet de supprimer un evenement existant
 * @author p2026386 GRANDY Guillaume
 * @version 1.1 Correction d'un bug
 */
public class SupprimeEvenement extends JDialog implements ActionListener{
    JButton btAnnuler;
    JButton btValider;
    JComboBox selection;
    JLabel texte;
    Rappel r;
    Agenda agenda;
    JLabel date;
    JLabel commentaire;

    
    public SupprimeEvenement(Interface owner) 
{
    super(owner,true);
    this.setUndecorated(true);
    
    btAnnuler=new JButton("Annuler");
    btAnnuler.setBackground(Color.RED);
    
    btValider=new JButton("Supprimer");
    btValider.setBackground(Color.GREEN);
    
    selection=new JComboBox();
    selection.setForeground(owner.getCouleur());
    selection.setBackground(Color.BLACK);
    selection.setForeground(owner.getCouleur());

    texte=new JLabel("Choisir l'événement à supprimer");
    texte.setForeground(owner.getCouleur());
    
    agenda=owner.getAgenda();
   
    btValider.addActionListener(this);
    btAnnuler.addActionListener(this);
    
   for(int i=0;i<agenda.getListRappel().size();i++)
   {
        selection.addItem(agenda.getListRappel().get(i).getDescription());
   }   
    
    JPanel pano = new JPanel();
    pano.setLayout(new GridBagLayout());
    pano.setBorder(BorderFactory.createLineBorder(Color.WHITE));
    pano.setBackground(new Color(20,20,20));
    
    GridBagConstraints cont = new GridBagConstraints();
    cont.insets.bottom=2;
    cont.insets.top=2;
    cont.insets.right=2;
    cont.insets.left=2;
    
    cont.fill=GridBagConstraints.BOTH;
    
    cont.gridwidth=2;
    cont.gridx = 0;
    cont.gridy = 0;
    pano.add(texte, cont);
    
    cont.gridx = 0;
    cont.gridy = 1;
    pano.add(selection, cont);
    
    cont.gridwidth=1;
    cont.gridx = 0;
    cont.gridy = 2;
    pano.add(btAnnuler, cont);
    
     cont.gridx = 1;
    cont.gridy = 2;
    pano.add(btValider, cont);

    this.setContentPane(pano);
    this.pack();
    }
    
    /**
     * Fait apparaitre la fenetre SupprimeEvenement
     * @return Rappel
     */
    public Rappel showDialog(){
    this.setVisible(true); 
    return r;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
    if (e.getSource() == btAnnuler){
    r=null; 
    this.setVisible(false); 
    }

    if (e.getSource() == btValider){
        if(agenda.getListRappel().isEmpty()){
            r=null;
        }
        else{
            r=agenda.getListRappel().get(selection.getSelectedIndex());
        }
    this.setVisible(false); 
    }
 }
    
    
}
