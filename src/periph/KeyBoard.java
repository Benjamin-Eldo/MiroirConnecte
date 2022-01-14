
package periph;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Clavier virtuel permettant d'écrire dans la fenetre AjoutEvenement
 * @author p2004100 FORESTIER Alexis
 * @version 1.1 Le language du clavier peut désormais être modifié
 */
public class KeyBoard extends JDialog implements ActionListener{
    
    AjoutEvenement window;
    
    private JTextField text;
    private JButton button[];  // 26 caracteres
    private JButton retour, espace, entrer, annuler;
    private JComboBox alpha;
    
    private JPanel pano;
    private GridBagConstraints cont;
    
    static final char[] ALPHABET = new char[]{'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    static final char[] AZERTY = new char[]{'a','z','e','r','t','y','u','i','o','p','q','s','d','f','g','h','j','k','l','m','w','x','c','v','b','n'};
    static final char[] QWERTY = new char[]{'q','w','e','r','t','y','u','i','o','p','a','s','d','f','g','h','j','k','l','z','x','c','v','b','n','m'};
    
    private char[] alphabet;
    
    boolean infoListener = false;
    
    /**
     * Constructeur par paramètre
     * @param f - fenetre JDialog
     */
    public KeyBoard(AjoutEvenement f){
        super(f,true);
        try{
            this.window = f;
            this.setTitle("Clavier Virtuel");
            this.alphabet = AZERTY;
            Init(this.alphabet);
        }catch(Exception ex){
            System.out.println("Erreur : Initialisation du Clavier Virtuel");
        }
        
    }

    /**
     * Modifie l'alphabet du clavier
     * @param alphabet - alphabet utilisé par le clavier virtuel
     */
    private void setAlphabet(char[] alphabet) {
        if(alphabet.length ==  26){
            this.alphabet = alphabet;
        }
        else{
            System.out.println("l'alphabet en parametre ne contient pas 26 caracteres");
        }
    }
    
    /**
     * Ouvre le clavier
     * @param txt - JTextField ciblé par le clavier virtuel
     */
    public void Open(JTextField txt){
        try{
            Affiche(txt.getText(),this.alphabet);
            this.setVisible(true);
        }catch(Exception ex){
            System.out.println("Erreur : Ouverture du Clavier Virtuel");
        }
        
    }
    
    /**
     * Initialisation du Clavier
     * @param alphabet - Alphabet du clavier virtuel
     */
    private void Init(char[] alphabet){
        this.text = new JTextField("");
        
        button = new JButton[26];
        
        alpha = new JComboBox();
        alpha.setBackground(Color.BLACK);
        alpha.setForeground(Color.WHITE);
        alpha.addItem("azerty");
        alpha.addItem("qwerty");
        alpha.addItem("classic");
        alpha.addActionListener(this);
        
        for (int i = 0; i < 26; i++) {
            button[i] = new JButton(String.format("%c", alphabet[i]));
            button[i].setBackground(Color.BLACK);
            button[i].setForeground(Color.WHITE);
            button[i].addActionListener(this);
        }
        
        espace = new JButton("espace");
        espace.setBackground(Color.BLACK);
        espace.setForeground(Color.WHITE);
        espace.addActionListener(this);
        
        retour = new JButton("retour");
        retour.setBackground(Color.BLACK);
        retour.setForeground(Color.WHITE);
        retour.addActionListener(this);
        
        entrer = new JButton("Entrée");
        entrer.setBackground(Color.GREEN);
        entrer.setForeground(Color.BLACK);
        entrer.addActionListener(this);
        
        annuler = new JButton("Annuler");
        annuler.setBackground(Color.RED);
        annuler.setForeground(Color.BLACK);
        annuler.addActionListener(this);
        
        pano = new JPanel();
        pano.setLayout(new GridBagLayout());
        cont = new GridBagConstraints();
        
        cont.fill=GridBagConstraints.BOTH;
        
        cont.insets = new Insets(5,0,3,0);
        cont.ipady = 10;
        cont.gridx = 0;
        cont.gridy = 0;
        cont.gridwidth = 12;
        pano.add(text,cont);
        
        cont.insets = new Insets(0,0,0,0);
        cont.gridwidth = 1;
        int k=0;
        for (int i = 1; i < 4; i++) {
            for (int j = 0; j < 10; j++) {
                if((i>=3 && j>5)||k>25){
                    break;
                }
                else{
                    cont.gridx = j;
                    cont.gridy = i;
                    pano.add(button[k],cont);
                    k++;
                }
                
            }
        }
        
        cont.gridx = 1;
        cont.gridy = 4;
        cont.gridwidth = 8;
        pano.add(espace,cont);
        
        cont.gridwidth = 1;
        cont.gridx = 11;
        cont.gridy = 1;
        pano.add(retour,cont);
        
        cont.gridx = 11;
        cont.gridy = 2;
        pano.add(entrer,cont);
        
        cont.gridx = 11;
        cont.gridy = 3;
        pano.add(annuler,cont);
        
        cont.gridx = 11;
        cont.gridy = 4;
        pano.add(alpha,cont);
        
        //Curseur
        if(System.getProperty("os.name").equals("Linux")){
            BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
            Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
            this.getContentPane().setCursor(blankCursor);
        }
        this.setLocation(0, 400);
        this.setUndecorated(true);
        this.setContentPane(pano);
        this.getContentPane().setBackground(Color.BLACK);
        this.pack();
    }
    
    /**
     * Affiche le clavier sans l'initialiser à nouveau
     * @param content_keyboard - Texte récupérer sur élement cible
     * @param alphabet - Alphabet utilisé par le clavier virtuel
     */
    public void Affiche(String content_keyboard, char[] alphabet){
        if(content_keyboard == null){
            text.setText("");
        }
        else{
            text.setText(content_keyboard);
        }
        
        for (int i = 0; i < 26; i++) {
            button[i].setText(String.format("%c", alphabet[i]));
        }
        
        int k=0;
        switch(alpha.getSelectedIndex()){
            case 2:
            case 0:
                for (int i = 1; i < 4; i++) {
                    for (int j = 0; j < 10; j++) {
                        if((i>=3 && j>5)||k>25){
                            break;
                        }
                        else{
                            cont.gridx = j;
                            cont.gridy = i;
                            pano.add(button[k],cont);
                            k++;
                        }
                    }
                }
                break;
            
            case 1:
                for (int i = 1; i < 4; i++) {
                    for (int j = 0; j < 10; j++) {
                        if((i==2 && j>8)||k>25){
                            break;
                        }
                        else if((i==3 && j>6)||k>25){
                            break;
                        }
                        else{
                            cont.gridx = j;
                            cont.gridy = i;
                            pano.add(button[k],cont);
                            k++;
                        }
                    }
                }
                break;
        }
        
        this.setContentPane(pano);
        this.getContentPane().setBackground(Color.BLACK);
        this.pack();
    }
    
    /**
     * Editable en fonction du programme
     * Envoie le contenu du clavier à la cible
     */
    public void sendKeyboardText(){
        window.commentaire.setText(this.text.getText());
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "a":
                text.setText(text.getText()+e.getActionCommand());
                break;
            case "b":
                text.setText(text.getText()+e.getActionCommand());
                break;
            case "c":
                text.setText(text.getText()+e.getActionCommand());
                break;
            case "d":
                text.setText(text.getText()+e.getActionCommand());
                break;
            case "e":
                text.setText(text.getText()+e.getActionCommand());
                break;
            case "f":
                text.setText(text.getText()+e.getActionCommand());
                break;
            case "g":
                text.setText(text.getText()+e.getActionCommand());
                break;
            case "h":
                text.setText(text.getText()+e.getActionCommand());
                break;
            case "i":
                text.setText(text.getText()+e.getActionCommand());
                break;
            case "j":
                text.setText(text.getText()+e.getActionCommand());
                break;
            case "k":
                text.setText(text.getText()+e.getActionCommand());
                break;
            case "l":
                text.setText(text.getText()+e.getActionCommand());
                break;
            case "m":
                text.setText(text.getText()+e.getActionCommand());
                break;
            case "n":
                text.setText(text.getText()+e.getActionCommand());
                break;
            case "o":
                text.setText(text.getText()+e.getActionCommand());
                break;
            case "p":
                text.setText(text.getText()+e.getActionCommand());
                break;
            case "q":
                text.setText(text.getText()+e.getActionCommand());
                break;
            case "r":
                text.setText(text.getText()+e.getActionCommand());
                break;
            case "s":
                text.setText(text.getText()+e.getActionCommand());
                break;
            case "t":
                text.setText(text.getText()+e.getActionCommand());
                break;
            case "u":
                text.setText(text.getText()+e.getActionCommand());
                break;
            case "v":
                text.setText(text.getText()+e.getActionCommand());
                break;
            case "w":
                text.setText(text.getText()+e.getActionCommand());
                break;
            case "x":
                text.setText(text.getText()+e.getActionCommand());
                break;
            case "y":
                text.setText(text.getText()+e.getActionCommand());
                break;
            case "z":
                text.setText(text.getText()+e.getActionCommand());
                break;
            case "espace":
                text.setText(text.getText()+" ");
                break;
            case "retour":
                if(!"".equals(text.getText())){
                    text.setText(text.getText().substring(0, text.getText().length()-1));
                }
                break;
            case "Annuler":
                this.setVisible(false);
                break;
            case "Entrée":
                this.sendKeyboardText();
                this.setVisible(false);
                break;
            case "comboBoxChanged":
                switch(alpha.getSelectedIndex()){
                    case 0:
                        this.setAlphabet(KeyBoard.AZERTY);
                        break;
                    case 1:
                        this.setAlphabet(KeyBoard.QWERTY);
                        break;
                    case 2:
                        this.setAlphabet(KeyBoard.ALPHABET);
                        break;
                }
                Affiche(text.getText(),this.alphabet);
                break;
                
        }
        if(this.infoListener){
            System.out.println("KeyBoard : "+e.getActionCommand());
        }
    }
    
}
